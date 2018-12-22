package logic.update;

/**
 * This class represents the class Golden Brick update, which sends a notification to either Game or Level,
 * that a Golden Brick has been destroyed.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class GoldenBrickDestroyedUpdate implements BrickUpdate,LevelUpdate {

    @Override
    public void accept(BrickUpdateReceiver brickUpdateReceiver) {
        brickUpdateReceiver.goldenBrickDestroyedUpdate(this);
    }

    @Override
    public void accept(LevelUpdateReceiver levelUpdateReceiver) {
        levelUpdateReceiver.goldenBrickDestroyedUpdate(this);
    }
}
