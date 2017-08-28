package com.interview;

import com.interview.square.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18/08/17, 10:12 PM
 * Board.java
 *
 * @author gshankar
 */
public final class Board {
    private List<Square> squares = new ArrayList<>();

    public Board(int numSquares, int[][] snakesFromTo, int[][] laddersFromTo, int[] trampolines,
                 int[] springs, int[] pitstops) {
        buildSquares(numSquares);
        buildSnakes(snakesFromTo);
        buildLadders(laddersFromTo);
        buildTrampolines(trampolines);
        buildSprings(springs);
        buildPitstops(pitstops);
    }

    private void buildSquares(int numSquares) {
        System.out.println("Number of Squares: " + numSquares);

        for (int pos=1; pos<=numSquares ; pos++) {
            Square square = new Square(pos, this);
            squares.add(square);
            square.setSquareAction(new RegularSquareAction(square));
        }
        firstSquare().setSquareAction(new FirstSquareAction(firstSquare()));
        lastSquare().setSquareAction(new LastSquareAction(lastSquare()));
    }

    public Square firstSquare() {
        return squares.get(0);
    }

    public Square lastSquare() {
        return squares.get(squares.size() - 1);
    }

    private void buildSnakes(int[][] snakesFromTo) {
        for (int i=0; i<snakesFromTo.length; i++) {
            assert snakesFromTo[i].length == 2;

            int fromPos = snakesFromTo[i][0];
            int toPos = snakesFromTo[i][1];
            int shift = toPos - fromPos;

            Square snakeSquare = squares.get(fromPos - 1);
            snakeSquare.setSnake(true);
            snakeSquare.setSquareAction(new SnakeAction(snakeSquare, shift));
        }
    }

    private void buildLadders(int[][] laddersFromTo) {
        for (int i=0; i<laddersFromTo.length; i++) {
            assert laddersFromTo[i].length == 2;

            int fromPos = laddersFromTo[i][0];
            int toPos = laddersFromTo[i][1];
            int shift = toPos - fromPos;

            Square ladderSquare = squares.get(fromPos - 1);
            ladderSquare.setLadder(true);
            ladderSquare.setSquareAction(new LadderAction(ladderSquare, shift));
        }
    }

    private void buildTrampolines(int[] trampolines) {
        for (int i=0; i<trampolines.length; i++) {
            Square trampolineSqaure = squares.get(trampolines[i] - 1);
            trampolineSqaure.setTrampoline(true);
        }
    }

    private void buildSprings(int[] springs) {
        for (int i=0; i<springs.length; i++) {
            Square springSquare = squares.get(springs[i] - 1);
            springSquare.setSpring(true);
        }
    }

    private void buildPitstops(int[] pitstops) {
        for (int i=0; i<pitstops.length; i++) {
            Square pitstopSquare = squares.get(pitstops[i] - 1);
            pitstopSquare.setPitstop(true);
        }
    }

    public Square findSquare(int position) {
        assert (position > 0) && (position <= numberOfSquares()) : "square at position " + position+1 + " does not exists";
        return squares.get(position - 1);
    }

    public int numberOfSquares() {
        return squares.size();
    }
}
