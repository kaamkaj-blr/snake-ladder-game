package com.target.square;

/**
 * Created on 19/08/17, 1:09 AM
 * LadderAction.java
 *
 * @author gshankar
 */
public final class LadderAction extends SquareAction {

    private int shift;

    public LadderAction(Square square, int shift) {
        super(square);
        assert shift > 0 : "A ladder shift must be positive";
        this.shift = shift;
    }

    @Override
    public Square findLandingSquare() {
        System.out.println("Ladder from " + (square.getPosition()) + " TO " + (destination().getPosition()));
        return destination().findLandingSquare();
    }

    private Square destination() {
        return square.findRelativeSquare(shift);
    }
}
