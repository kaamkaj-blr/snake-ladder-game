package com.target.square;

/**
 * Created on 19/08/17, 1:10 AM
 * SnakeAction.java
 *
 * @author gshankar
 */
public final class SnakeAction extends SquareAction {
    private int shift;

    public SnakeAction(Square square, int shift) {
        super(square);
        assert shift < 0 : "A snake shift must be negative";
        this.shift = shift;
    }

    @Override
    public Square findLandingSquare() {
        System.out.println("Snake from " + (square.getPosition()) + " TO " + (destination().getPosition()));
        return destination().findLandingSquare();
    }

    private Square destination() {
        return square.findRelativeSquare(shift);
    }
}
