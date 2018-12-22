package logic.brick;

import logic.update.GoldenBrickDestroyedUpdate;

/**
 * The new class invented for this part. This represents the logic of the Golden Brick.
 * The Golden Brick is a type of Brick that doesn't give points, but does make
 * the player's bar longer.
 *
 * @author Valentina Sepulveda
 *
 */
public class GoldenBrick extends AbstractBrick {

    public GoldenBrick(){
        super(10,0);
    }


    /**
     * Like the other methods in the other Bricks, this sends a notification to Level. Calling the Golden Brick Update.
     */
    @Override
    protected void sendUpdate() {
        notifyObservers(new GoldenBrickDestroyedUpdate());
    }
}
