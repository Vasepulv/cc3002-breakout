package logic;

import controller.Game;

import java.util.Observable;

/**
 * The implementation of elements that make a call to Game, which consists of Bricks.
 */
public abstract class GameElements extends Observable {

    /**
     * The action of destroying a Brick, using a double dispatch call.
     * @param game the game that contains the Brick.
     */
    public abstract void accept(Game game);
}
