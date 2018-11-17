package logic.level;

import logic.brick.Brick;

import java.util.List;

/**
 *
 */
public class NullLevel extends AbstractLevel {

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
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
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Level getNextLevel() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean hasNextLevel() {
        return false;
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

    }

    /**
     *
     * @return
     */
    @Override
    public int getLevelPoints() {
        return 0;
    }
}
