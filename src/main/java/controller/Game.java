package controller;


import View.GameApp;
import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.Level;
import logic.update.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game extends Observable implements Observer, LevelUpdateReceiver {
    private Level currentLevel;
    private int cumulativeScore;
    private int ballsLeft;
    private boolean winner;
    private GameApp gameApp;

    public Game(int balls) {
        ballsLeft = balls;
        currentLevel = new EmptyLevel();
        cumulativeScore = 0;
        winner = false;
        gameApp=new GameApp();
        addObserver(gameApp);
    }

    public List<Brick> getBricks() {
        return currentLevel.getBricks();
    }

    public boolean hasNextLevel() {
        return currentLevel.hasNextLevel();
    }

    public void goNextLevel() {
        setLevel(currentLevel.getNextLevel());
    }

    public boolean hasCurrentLevel() {
        return currentLevel.isPlayableLevel();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        currentLevel = level;
        level.assignGame(this);
        notifyObservers();
    }

    public int getPoints() {
        return cumulativeScore;
    }

    public int getBalls() {
        return ballsLeft;
    }

    public void dropBall() {
        if (ballsLeft > 0) {
            ballsLeft--;
            setChanged();
            notifyObservers();
        }
    }

    public boolean playing() {
        return ballsLeft > 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof LevelUpdate) {
            ((LevelUpdate) arg).accept(this);
        }
    }

    @Override
    public void scoreUpdate(ScoreUpdate scoreUpdate) {
        cumulativeScore += scoreUpdate.getScore();
        setChanged();
        notifyObservers();
    }

    @Override
    public void maxLevelScoreUpdate(MaxLevelScoreReachedUpdate maxLevelScoreReachedUpdate) {
        goNextLevel();
        if (!hasCurrentLevel()) {
            winner = true;
            setChanged();
            notifyObservers();
        }

    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {
        ballsLeft++;
        setChanged();
        notifyObservers();
    }

    public void addPlayingLevel(Level level) {
        currentLevel.addPlayingLevel(level);
    }

    public boolean winner() {
        return winner;
    }

}
