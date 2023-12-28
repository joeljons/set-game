package se.timotej.set;

import org.apache.commons.lang3.tuple.Triple;
import se.timotej.set.player.Card;
import se.timotej.set.player.GameState;
import se.timotej.set.player.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static se.timotej.set.player.SetUtil.isSet;

public class Game {

    public static final int GAME_COUNT = 10_000;

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (args.length == 0) {
            System.out.println("Enter player classes as args");
            System.exit(1);
        }
        List<Player> players = new ArrayList<>();
        for (String arg : args) {
            @SuppressWarnings("unchecked")
            Class<Player> aClass = (Class<Player>) Class.forName(arg);
            Player player = aClass.getDeclaredConstructor().newInstance();
            System.out.println(player.getName());
            players.add(player);
        }
        int[] wins = new int[args.length];
        long[] totalTime = new long[args.length];
        long lastPrintout = 0L;
        for (int matchNr = 0; matchNr < GAME_COUNT; matchNr++) {
            List<Integer> scores = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                players.get(i).initGame(i);
                scores.add(0);
            }

            Deck deck = new Deck();
            List<Card> cardsOnTable = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                cardsOnTable.add(deck.take());
            }
            while (!cardsOnTable.isEmpty()) {
                GameState gameState = new GameState(cardsOnTable, scores);

                long bestTime = Long.MAX_VALUE;
                Triple<Card, Card, Card> bestPlay = null;
                int bestPlayer = -1;
                List<Integer> playerOrder = new ArrayList<>();
                for (int i = 0; i < players.size(); i++) {
                    playerOrder.add(i);
                }
                Collections.shuffle(playerOrder);
                for (Integer playerNum : playerOrder) {
                    Player player = players.get(playerNum);
                    long start = System.nanoTime();
                    Triple<Card, Card, Card> play = null;
                    try {
                        play = player.play(gameState);
                    } catch (Exception e) {
                        e.printStackTrace();
                        scores.set(playerNum, Math.max(scores.get(playerNum) - 1, 0));
                    }
                    long duration = System.nanoTime() - start;
                    totalTime[playerNum] += duration;
                    if (play != null && duration < bestTime) {
                        bestTime = duration;
                        bestPlay = play;
                        bestPlayer = playerNum;
                    }
                }
                boolean cardsPlayed = false;
                if (bestPlay != null) {
                    if (!isSet(bestPlay)) {
                        System.out.printf("Invalid play from player %d: %s%n", bestPlayer, bestPlay);
                        scores.set(bestPlayer, Math.max(scores.get(bestPlayer) - 1, 0));
                    } else if (!cardsOnTable.contains(bestPlay.getLeft()) || !cardsOnTable.contains(bestPlay.getMiddle()) || !cardsOnTable.contains(bestPlay.getRight())) {
                        System.out.printf("Played card not on table for player %d: %s%n", bestPlayer, bestPlay);
                        scores.set(bestPlayer, Math.max(scores.get(bestPlayer) - 1, 0));
                    } else {
                        //System.out.println("bestTime = " + bestTime);
                        scores.set(bestPlayer, scores.get(bestPlayer) + 1);
                        cardsOnTable.remove(bestPlay.getLeft());
                        cardsOnTable.remove(bestPlay.getMiddle());
                        cardsOnTable.remove(bestPlay.getRight());
                        cardsPlayed = true;
                    }
                }
                if ((bestPlay == null || (cardsPlayed && cardsOnTable.size() < 12)) && !deck.isEmpty()) {
                    for (int i = 0; i < 3; i++) {
                        cardsOnTable.add(deck.take());
                    }
                } else if (bestPlay == null && deck.isEmpty()) {
                    break;
                }
            }
            int bestScore = -1;
            int bestPlayer = -1;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i) > bestScore) {
                    bestScore = scores.get(i);
                    bestPlayer = i;
                } else if (scores.get(i) == bestScore) {
                    bestPlayer = -1;
                }
            }
            if (bestPlayer != -1) {
                wins[bestPlayer]++;
            }
            if (System.nanoTime() - lastPrintout > 10_000_000_000L) {
                System.out.println("wins = " + Arrays.toString(wins));
                lastPrintout = System.nanoTime();

            }
            //System.out.println("scores = " + scores);
        }
        System.out.println("wins = " + Arrays.toString(wins));

        for (int i = 0; i < players.size(); i++) {
            System.out.printf("%10d %-15s %d ns%n", wins[i], players.get(i).getName(), totalTime[i] / GAME_COUNT);
        }
    }
}