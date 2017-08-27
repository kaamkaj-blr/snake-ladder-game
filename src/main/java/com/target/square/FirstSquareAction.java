package com.target.square;

import com.target.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18/08/17, 11:16 PM
 * FirstSquareAction.java
 *
 * @author gshankar
 */
public final class FirstSquareAction extends SquareAction {

    public FirstSquareAction(Square s) {
        super(s);
    }

    @Override
    public boolean isFirstSquare() {
        return true;
    }
}
