package com.target.square;

import com.target.Board;
import com.target.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 18/08/17, 10:14 PM
 * Square.java
 *
 * @author gshankar
 */
public class Square {

    private Board board;

    private List<Player> players = new ArrayList<>();

    private int position;

    private SquareAction squareAction;

    private boolean snake;

    private boolean ladder;

    private boolean trampoline;

    private boolean spring;

    private boolean pitstop;

    public Square(int position, Board board) {
        this.position = position;
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public int getPosition() {
        return position;
    }

    public void setSquareAction(SquareAction squareAction) {
        assert squareAction != null;
        this.squareAction = squareAction;
    }

    public boolean isLastSquare() {
        return squareAction.isLastSquare();
    }

    public void enterPlayer(Player p) {
        addPlayer(p);
    }

    public void leavePlayer(Player p) {
        removePlayer(p);
    }

    public Square moveAndLand(Player player, int diceThrow) {
        return squareAction.moveAndLand(player, diceThrow);
    }

    public Square findLastSquare() {
        return board.lastSquare();
    }

    public Square findRelativeSquare(int shift) {
        Square relativeSquare = board.findSquare(position + shift);

        return relativeSquare;
    }

    public Square findLandingSquare() {
        return squareAction.findLandingSquare();
    }

    public void setSnake(boolean snake) {
        this.snake = snake;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    public boolean isSnake() {
        return snake;
    }

    public boolean isLadder() {
        return ladder;
    }

    public boolean isTrampoline() {
        return trampoline;
    }

    public void setTrampoline(boolean trampoline) {
        this.trampoline = trampoline;
    }

    public boolean isSpring() {
        return spring;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    public boolean isPitstop() {
        return pitstop;
    }

    public void setPitstop(boolean pitstop) {
        this.pitstop = pitstop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return position == square.position &&
                Objects.equals(board, square.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, position);
    }
}
