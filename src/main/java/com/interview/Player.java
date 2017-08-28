package com.interview;

import com.interview.square.Square;

import java.util.Objects;

/**
 * Created on 18/08/17, 10:12 PM
 * Player.java
 *
 * @author gshankar
 */
public final class Player {

    private Square square;

    private String name;

    private int currentEnergy;

    public Player(String name, int currentEnergy) {
        this.name = name;
        this.currentEnergy = currentEnergy;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        this.currentEnergy = currentEnergy;
    }

    public int getPosition() {
        return square.getPosition();
    }

    public boolean wins() {
        return square.isLastSquare();
    }

    public void move(int diceRoll) {
        this.setCurrentEnergy(currentEnergy - 1);

        Square landingSquare = square.moveAndLand(this, diceRoll);

        square.leavePlayer(this);
        landingSquare.enterPlayer(this);

        square = landingSquare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
