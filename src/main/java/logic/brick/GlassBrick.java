package logic.brick;

import logic.update.BrickDestroyedUpdate;

/**
 *
 * @author Juan-Pablo Silva
 */
public class GlassBrick extends AbstractBrick {
    public GlassBrick() {
        super(1, 50);
    }

    @Override
    protected void sendUpdate() {
        notifyObservers(new BrickDestroyedUpdate(this));
    }
}
