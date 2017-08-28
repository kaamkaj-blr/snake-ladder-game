package com.interview.square;

/**
 * Created on 18/08/17, 11:50 PM
 * LastSquareAction.java
 *
 * @author gshankar
 */
public final class LastSquareAction extends SquareAction {

    public LastSquareAction(Square s) {
        super(s);
    }

    @Override
    public boolean isLastSquare() {
        return true;
    }
}
