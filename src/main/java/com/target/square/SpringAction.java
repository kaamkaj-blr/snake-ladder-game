package com.target.square;

/**
 * Created on 19/08/17, 2:06 AM
 * SpringAction.java
 *
 * @author gshankar
 */
public class SpringAction extends SquareAction {
    private int shift;

    public SpringAction(Square s, int shift) {
        super(s);
        assert shift < 0 : "A spring shift must be negative";
        this.shift = shift;
    }

    @Override
    public Square findLandingSquare() {
        System.out.println("Spring from " + (square.getPosition()) + " TO " + (destination().getPosition()));
        return destination().findLandingSquare();
    }

    private Square destination() {
        return square.findRelativeSquare(shift);
    }
}
