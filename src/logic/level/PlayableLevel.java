package logic.level;

import controller.Game;
import logic.GameElements;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

import java.util.*;

/**
 * The representation the Playable Levels of the game.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class PlayableLevel extends AbstractLevel {
    private String name;
    private List<Brick> bricks;
    private Level nextLevel;
    private Game game;

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed, Game game) {
        this.name=name;
        this.game=game;
        Brick brick;
        bricks=new ArrayList<>();
        Random number=new Random(seed);
        for(int i=0;i<numberOfBricks;i++){
            double number1=number.nextDouble();
            if(number1>probOfGlass){
                brick=new WoodenBrick();
                ((WoodenBrick) brick).addObserver(game);
                bricks.add(brick);
            }
            else {
                brick=new GlassBrick();
                ((GlassBrick) brick).addObserver(game);
                bricks.add(brick);
            }
        }

        for(int i=0;i<numberOfBricks;i++){
            double number2=number.nextDouble();
            if(number2<=probOfMetal){
                brick=new MetalBrick();
                ((MetalBrick) brick).addObserver(game);
                bricks.add(brick);
            }
        }
        nextLevel= new NullLevel();
    }

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, int seed, Game game) {
        this.name=name;
        Random number=new Random(seed);
        Brick brick;
        this.game=game;
        bricks=new ArrayList<>();
        for(int i=0;i<numberOfBricks;i++){
            double number1=number.nextDouble();
            if(number1>probOfGlass){
                brick=new WoodenBrick();
                ((WoodenBrick) brick).addObserver(game);
                bricks.add(brick);
            }
            else{
                brick=new GlassBrick();
                ((GlassBrick) brick).addObserver(game);
                bricks.add(brick);
            }
        }
        nextLevel= new NullLevel();
    }

    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    @Override
    public int getNumberOfBricks() {
        int conteo=0;
        for(int i=0;i<bricks.size();i++){
            conteo++;
        }
        return conteo;
    }

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    @Override
    public Level getNextLevel() {
        return nextLevel;
    }

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    @Override
    public boolean hasNextLevel() {
        return nextLevel.isPlayableLevel();
    }

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    @Override
    public int getPoints() {
        int conteo=0;
        for (Brick brick : bricks) {
            conteo = brick.getScore() + conteo;
        }
        return conteo;
    }

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        Level next=this;
        while(next.hasNextLevel()){
            next=next.getNextLevel();
        }
        next.setNextLevel(level);
        return level;
    }

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {
        nextLevel=level;
    }

}
