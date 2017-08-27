package com.target.square;

import com.target.Player;

/**
 * Created on 18/08/17, 11:05 PM
 * SquareAction.java
 *
 * @author gshankar
 */
public abstract class SquareAction {
    protected Square square;

    public SquareAction(Square square) {
        assert square != null : "Square is null, unable to create square action";
        this.square = square;
    }

    public boolean isLastSquare() {
        return false;
    }

    public boolean isFirstSquare() {
        return false;
    }

    public Square moveAndLand(Player player, int diceThrow) {
        int lastPosition = square.findLastSquare().getPosition();
        int presentPosition = square.getPosition();
        int shift = diceThrow;

        if (presentPosition + diceThrow > lastPosition) {
            System.out.println("Current move will extend beyond last square " + (lastPosition) + ", so don't move");
            return square;
        } else {
            Square relativeSquare = square.findRelativeSquare(diceThrow);
            System.out.println("move from " + (square.getPosition()) + " TO " + (relativeSquare.getPosition()));

            if (relativeSquare.isTrampoline()) {
                if (relativeSquare.getPosition() + shift > relativeSquare.getBoard().numberOfSquares()) {
                    shift = relativeSquare.getBoard().lastSquare().getPosition() - relativeSquare.getPosition();

                }
                relativeSquare.setSquareAction(new TrampolineAction(relativeSquare, shift));
            }

            if (relativeSquare.isSpring()) {
                relativeSquare.setSquareAction(new SpringAction(relativeSquare, -shift));
            }

            if (relativeSquare.isPitstop()) {
                relativeSquare.setSquareAction(new PitstopAction(relativeSquare, player));
            }

            Square landingSquare = relativeSquare.findLandingSquare();

            return landingSquare;
        }
    }

    public Square findLandingSquare() {
        return square;
    }
}
