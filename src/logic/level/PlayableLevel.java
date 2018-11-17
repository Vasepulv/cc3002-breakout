package logic.level;

import controller.Game;
import logic.brick.Brick;

import java.util.ArrayList;
import java.util.List;

public class PlayableLevel extends AbstractLevel {
    private int numberOfBricks;
    private String name;
    private List<Brick> bricks;
    private Level nextLevel;
    private Game game;

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed) {
        this.name=name;
        this.numberOfBricks=numberOfBricks;
        bricks=new ArrayList<>();
        nextLevel= new NullLevel();
    }

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, int seed) {
        this.name=name;
        this.numberOfBricks=numberOfBricks;
        bricks=new ArrayList<>();
        nextLevel= new NullLevel();
    }


    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    /**
     *
     * @return
     */
    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNextLevel() {
        return nextLevel.hasNextLevel();
    }

    /**
     *
     * @return
     */
    @Override
    public int getPoints() {
        return 0;
    }

    /**
     *
     * @param level the level to be added
     * @return
     */
    @Override
    public Level addPlayingLevel(Level level) {
        return null;
    }

    /**
     *
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {
        nextLevel=level;
    }

    /**
     *
     * @return
     */
    @Override
    public int getLevelPoints() {
        int conteo=0;
        for (Brick brick : bricks) {
            conteo = brick.getScore() + conteo;
        }
        return conteo;
    }
}
