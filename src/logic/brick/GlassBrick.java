package logic.brick;


import controller.Game;

/**
 * The implementation of the Glass Brick.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class GlassBrick extends AbstractBrick {

    private int score;
    private int remainingHits;
    private boolean destroyed;

    public GlassBrick(){
        score=50;
        remainingHits=1;
        destroyed=false;
    }


    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    @Override
    public void hit() {
        remainingHits--;
        remainingHits();
        setChanged();
        if(isDestroyed() && !destroyed){
            destroyed=true;
            notifyObservers(this);
        }
    }

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    @Override
    public boolean isDestroyed() {
        return remainingHits==0;
    }

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    @Override
    public int remainingHits() {
        if(remainingHits<=0){
            remainingHits=0;
        }
        return remainingHits;
    }

    /**
     * The action of destroying a Glass Brick, using a double dispatch call.
     * @param game the game that contains the Glass Brick
     */
    @Override
    public void accept(Game game) {
        game.glassBrickIsDestroyed(this);
    }
}
