package logic.ViewUpdates;

/**
 * This class is an adapter of the GoldBrickUpdate so the view can use it.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class GoldBrickAdapter implements LevelChangesUpdate {
    @Override
    public void accept(LevelChangesUpdateReceiver update) {
        update.goldBrickDestroyed(this);
    }
}
