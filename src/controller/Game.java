package controller;

import logic.level.Level;
import logic.level.NullLevel;
import logic.level.PlayableLevel;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game {

    Level level;
    int balls;
    int score;

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
     *
     * @return
     */
    public Level getCurrentLevel() {
        return null;
    }

    /**
     *
     */
    public void goNextLevel() {
        level= this.getCurrentLevel().getNextLevel();
    }

    /**
     *
     * @param level
     */
    public void setCurrentLevel(Level level) {
        this.level=level;
    }

    /**
     *
     * @return
     */
    public int getBallsLeft() {
        return balls;
    }

    /**
     *
     * @return
     */
    public int dropBall() {
        balls--;
        return balls;
    }

    /**
     *
     * @return
     */
    public boolean isGameOver() {
        return winner() || (getBallsLeft()==0);
    }

    /**
     *
     * @return
     */
    public int getCurrentPoints() {
        return score;
    }

    /**
     *
     * @param name
     * @param numberOfBricks
     * @param probOfGlass
     * @param probOfMetal
     * @param seed
     * @return
     */
    public Level newLevelWithBricksFull(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,probOfMetal,seed);
    }

    /**
     *
     * @param name
     * @param numberOfBricks
     * @param probOfGlass
     * @param seed
     * @return
     */
    public Level newLevelWithBricksNoMetal(String name, int numberOfBricks, double probOfGlass, int seed) {
        return new PlayableLevel(name,numberOfBricks,probOfGlass,seed);
    }
}
