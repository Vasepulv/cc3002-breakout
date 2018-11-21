package logic.brick;

import controller.Game;
import logic.GameElements;

/**
 * The abstraction of the Brick classes, of the game.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public abstract class AbstractBrick extends GameElements implements Brick{

    public abstract void accept(Game game);


    @Override
    public int remainingHits() {
        return 0;
    }

}
