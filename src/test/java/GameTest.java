import controller.Game;
import facade.HomeworkTwoFacade;
import logic.brick.GlassBrick;
import logic.brick.WoodenBrick;
import logic.level.BreakoutLevel;
import logic.level.EmptyLevel;
import logic.level.Level;
import logic.update.GoldenBrickDestroyedUpdate;
import logic.update.MaxLevelScoreReachedUpdate;
import logic.update.MetalBrickDestroyedUpdate;
import logic.update.ScoreUpdate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;
    private Level level;
    private Level level2;

    @Before
    public void setUp(){
        game=new Game(3);
        level=new BreakoutLevel();
        level2=new HomeworkTwoFacade().newLevelWithBricksNoMetal("Level2",20,0.5,1);
    }

    @Test
    public void basicTest(){
        Assert.assertEquals(3,game.getBalls());
        Assert.assertFalse(game.hasCurrentLevel());
        Assert.assertFalse(game.hasNextLevel());
        Assert.assertEquals(0,game.getPoints());
        Assert.assertEquals(EmptyLevel.class,game.getCurrentLevel().getClass());
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());
        Assert.assertTrue(game.getBricks().isEmpty());
    }

    @Test
    public void gameTest(){
        game.setLevel(level);
        Assert.assertTrue(game.hasCurrentLevel());
        Assert.assertFalse(game.hasNextLevel());

        game.addPlayingLevel(level2);
        Assert.assertTrue(game.hasCurrentLevel());
        Assert.assertTrue(game.hasNextLevel());

        Assert.assertEquals(level,game.getCurrentLevel());
        Assert.assertEquals(level2,game.getCurrentLevel().getNextLevel());
    }

    @Test
    public void testBalls(){
        game.dropBall();
        Assert.assertEquals(2,game.getBalls());
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());

        game.dropBall();
        Assert.assertEquals(1,game.getBalls());
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());

        game.dropBall();
        Assert.assertEquals(0,game.getBalls());
        Assert.assertFalse(game.playing());
        Assert.assertFalse(game.winner());

        game.dropBall();
        Assert.assertEquals(0,game.getBalls());
        Assert.assertFalse(game.playing());
        Assert.assertFalse(game.winner());
    }

    @Test
    public void testPassLevel(){
        game.setLevel(level);
        game.addPlayingLevel(level2);
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());

        game.goNextLevel();
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());

        game.goNextLevel();
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());

        game.goNextLevel();
        Assert.assertTrue(game.playing());
        Assert.assertFalse(game.winner());
    }

    @Test
    public void testUpdateMaxScore(){
        game.setLevel(level);
        game.addPlayingLevel(level2);
        game.maxLevelScoreUpdate(new MaxLevelScoreReachedUpdate());
        Assert.assertEquals(level2,game.getCurrentLevel());
        Assert.assertFalse(game.winner());

        game.maxLevelScoreUpdate(new MaxLevelScoreReachedUpdate());
        Assert.assertEquals(level2.getNextLevel(),game.getCurrentLevel());
        Assert.assertTrue(game.winner());
    }

    @Test
    public void testMetalBreakUpdate(){
        game.dropBall();
        Assert.assertEquals(2,game.getBalls());

        game.dropBall();
        Assert.assertEquals(1,game.getBalls());

        game.metalBrickDestroyedUpdate(new MetalBrickDestroyedUpdate());
        Assert.assertEquals(2,game.getBalls());

        game.metalBrickDestroyedUpdate(new MetalBrickDestroyedUpdate());
        Assert.assertEquals(3,game.getBalls());

        game.metalBrickDestroyedUpdate(new MetalBrickDestroyedUpdate());
        Assert.assertEquals(4,game.getBalls());
    }

    @Test
    public void testScoreUpdate(){
        Assert.assertEquals(0,game.getPoints());

        game.scoreUpdate(new ScoreUpdate(100));
        Assert.assertEquals(100,game.getPoints());

        game.scoreUpdate(new ScoreUpdate(new GlassBrick().getScore()));
        Assert.assertEquals(150,game.getPoints());

        game.scoreUpdate(new ScoreUpdate(new WoodenBrick().getScore()));
        Assert.assertEquals(350,game.getPoints());
    }

    @Test
    public void testGoldenBrickUpdate(){
        Assert.assertEquals(0,game.getPoints());
        Assert.assertEquals(3,game.getBalls());

        game.goldenBrickDestroyedUpdate(new GoldenBrickDestroyedUpdate());
        Assert.assertEquals(0,game.getPoints());
        Assert.assertEquals(3,game.getBalls());

        game.goldenBrickDestroyedUpdate(new GoldenBrickDestroyedUpdate());
        Assert.assertEquals(0,game.getPoints());
        Assert.assertEquals(3,game.getBalls());
    }

}
