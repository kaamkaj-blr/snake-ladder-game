package com.interview.square;

import com.interview.Player;

/**
 * Created on 19/08/17, 2:07 AM
 * PitstopAction.java
 *
 * @author gshankar
 */
public class PitstopAction extends SquareAction {
    private Player player;

    public PitstopAction(Square s, Player p) {
        super(s);
        this.player = p;
    }

    @Override
    public Square findLandingSquare() {
        System.out.println("Pitstop at " + (square.getPosition()));
        player.setCurrentEnergy(player.getCurrentEnergy() + (square.getPosition()));
        return square;
    }

    public Player getPlayer() {
        return player;
    }
}
