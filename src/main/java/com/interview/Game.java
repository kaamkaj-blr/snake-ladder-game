package com.interview;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 18/08/17, 10:10 PM
 * Game.java
 *
 * @author gshankar
 */
public final class  Game {
    private LinkedList<Player> players = new LinkedList<>();

    private Board board;

    private Player winner;

    // hard code input for testing
    // private LinkedList<Integer> rolls = new LinkedList<>(Arrays.asList(6,3,5,1,1,4,5,6,3,4,6,5,2,3,5,5,4,6,3));
    // private LinkedList<Integer> rolls = new LinkedList<>(Arrays.asList(1,3,2,5));

    public Game(List<String> playerNames, int numSquares,
                int[][] snakesFromTo, int[][] laddersFromTo, int[] trampolines, int[] springs, int[] pitstops) {
        buildBoard(numSquares, snakesFromTo, laddersFromTo, trampolines, springs, pitstops);
        buildPlayers(numSquares, playerNames);
    }

    private void buildBoard(int numSquares, int[][] snakesFromTo, int[][] laddersFromTo, int[] trampolines,
                            int[] springs, int[] pitstops) {
        board = new Board(numSquares, snakesFromTo, laddersFromTo, trampolines, springs, pitstops);
    }

    private void buildPlayers(int numSquares, List<String> playerNames) {
        for (String name : playerNames) {
            Player player = new Player(name, numSquares/3);
            players.add(player);
            board.firstSquare().enterPlayer(player);
            player.setSquare(board.firstSquare());
        }
    }

    public void play() {
        winner = null;

        System.out.println("Initial state is :: \n" + this);
        while (winner == null) {
            movePlayer();
        }

        System.out.println("GAME OVER and winner is :: " +winner.getName());
    }

    private void movePlayer() {
        Player currentPlayer = players.remove();

        //check for energy
        if (currentPlayer.getCurrentEnergy() == 0) {
            currentPlayer.setSquare(board.firstSquare());
            board.firstSquare().addPlayer(currentPlayer);
            currentPlayer.setCurrentEnergy(board.numberOfSquares() / 3);
        }

        // random num between 1 to 6
        int roll = ThreadLocalRandom.current().nextInt(1, 7);
        //int roll = rolls.remove();

        System.out.println("Current player is: " + currentPlayer.getName() + ", and roll is: " + roll);

        currentPlayer.move(roll);

        players.add(currentPlayer);

        System.out.println("State is: \n" +this);

        if (currentPlayer.wins()) {
            winner = currentPlayer;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.getName() + " is at square " + (player.getPosition()) +
                    " and energy is " + player.getCurrentEnergy() +"\n");
        }

        return sb.toString();
    }
}
