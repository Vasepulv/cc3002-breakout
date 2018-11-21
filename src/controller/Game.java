package controller;

import logic.GameElements;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.Level;
import logic.level.NullLevel;
import logic.level.PlayableLevel;

import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {

    private Level level;
    private int balls;
    private int score;

    public Game(int balls) {
        this.balls=balls;
        this.score=0;
        level=new NullLevel();
    }

    /**
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return false;
    }


    /**
     * Gets the current {@link Level}.
     *
     * @return the current level
     * @see Level
     */
    public Level getCurrentLevel() {
        return level;
    }

    /**
     * Pass to the next level of the current {@link Level}. Ignores all conditions and skip to the next level.
     */
    public void goNextLevel() {

        level= this.getCurrentLevel().getNextLevel();
    }

    /**
     * Sets a {@link Level} as the current playing level.
     *
     * @param level the level to be used as the current level
     * @see Level
     */
    public void setCurrentLevel(Level level) {
        this.level=level;
    }

    /**
     * Gets the current number of available balls to play.
     *
     * @return the number of available balls
     */
    public int getBallsLeft() {
        return balls;
    }

    /**
     * Reduces the count of available balls and returns the new number.
     *
     * @return the new number of available balls
     */
    public int dropBall() {
        if(balls>0){
            balls--;
        }
        return balls;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0 or the player won the game.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return winner() || (getBallsLeft()==0);
    }

    /**
     * Gets the accumulated points through all levels and current {@link Level}.
     *
     * @return the cumulative points
     */
    public int getCurrentPoints() {
        return score;
    }

    /**
     * Creates a new level with the given parameters.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param probOfMetal    the probability of a {@link logic.brick.MetalBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */
    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed, this);
    }

    /**
     * Creates a new level with the given parameters with no metal bricks.
     *
     * @param name           the name of the level
     * @param numberOfBricks the number of bricks in the level
     * @param probOfGlass    the probability of a {@link logic.brick.GlassBrick}
     * @param seed           the seed for the random number generator
     * @return a new level determined by the parameters
     * @see Level
     */
    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,seed,this);
    }

    /**
     * A method to update the parameters of the Game
     * @param o the object that notifies the Game
     * @param arg the argument the objects sends
     */
    @Override
    public void update(java.util.Observable o, Object arg) {
        if(o instanceof GameElements){
            ((GameElements) o).accept(this);
        }
    }

    /**
     * The action of a Glass Brick being destroyed.
     *
     * @param glassBrick the one that was destroyed
     */
    public void glassBrickIsDestroyed(GlassBrick glassBrick) {
        score=score+glassBrick.getScore();
    }

    /**
     * The action of a Wooden Brick being destroyed.
     *
     * @param woodenBrick the one that was destroyed.
     */
    public void woodenBrickIsDestroyed(WoodenBrick woodenBrick){
        score=score+woodenBrick.getScore();
    }

    /**
     * The action of a Metal Brick being destroyed.
     *
     * @param metalBrick the one that was destroyed.
     */
    public void metalBrickIsDestroyed(MetalBrick metalBrick){
        balls++;
    }
}
