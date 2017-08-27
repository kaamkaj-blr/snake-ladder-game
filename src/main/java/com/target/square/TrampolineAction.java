package com.target.square;

import com.target.Player;

/**
 * Created on 19/08/17, 2:02 AM
 * TrampolineAction.java
 *
 * @author gshankar
 */
public class TrampolineAction extends SquareAction {
    private int shift;

    public TrampolineAction(Square s, int shift) {
        super(s);
        assert shift > 0 : "A trampoline shift must be positive";
        this.shift = shift;
    }

    @Override
    public Square findLandingSquare() {
        System.out.println("Trampoline from " + (square.getPosition()) + " TO " + (destination().getPosition()));
        return destination().findLandingSquare();
    }

    private Square destination() {
        Square newSquare = square.findRelativeSquare(shift);

        if (square.isTrampoline() && newSquare.isSpring()) {
            System.out.println("LOOP detected between Trampoline and Spring for this move");
            System.out.println("Removing Spring action at square "+newSquare.getPosition());
            newSquare.setSquareAction(new RegularSquareAction(newSquare));
            return newSquare;
        }

        if (newSquare.isTrampoline()) {
            int newShift = shift;
            if (newSquare.getPosition() + shift > newSquare.getBoard().numberOfSquares()) {
                newShift = newSquare.getBoard().lastSquare().getPosition() - newSquare.getPosition();
            }
            newSquare.setSquareAction(new TrampolineAction(newSquare, newShift));
        }

        if (newSquare.isSpring()) {
            newSquare.setSquareAction(new SpringAction(newSquare, -shift));
        }

        return newSquare;
    }
}
