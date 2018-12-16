package logic.brick;

import logic.update.BrickDestroyedUpdate;

/**
 *
 * @author Juan-Pablo Silva
 */
public class WoodenBrick extends AbstractBrick {
    public WoodenBrick() {
        super(3, 200);
    }

    @Override
    protected void sendUpdate() {
        notifyObservers(new BrickDestroyedUpdate(this));
    }
}
