package logic.brick;

public class MetalBrick extends AbstractBrick {

    /**
     *
     */
    @Override
    public void hit() {

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDestroyed() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public int remainingHits() {
        return 0;
    }
}
