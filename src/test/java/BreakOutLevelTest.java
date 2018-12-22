import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.WoodenBrick;
import logic.level.BreakoutLevel;
import logic.level.EmptyLevel;
import logic.update.BrickDestroyedUpdate;
import logic.update.GoldenBrickDestroyedUpdate;
import logic.update.MetalBrickDestroyedUpdate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BreakOutLevelTest {

    private BreakoutLevel level;
    private BreakoutLevel level2;
    private BreakoutLevel level3;

    @Before
    public void setUp(){
        level=new BreakoutLevel();
        level2=new BreakoutLevel();
        level3= (BreakoutLevel) new HomeworkTwoFacade().newLevelWithBricksFull("Level3",10,0.5,1,1);
    }

    @Test
    public void basicTest(){
        Assert.assertEquals(0,level.getNumberOfBricks());
        Assert.assertEquals(0,level.getPoints());
        Assert.assertEquals(EmptyLevel.class,level.getNextLevel().getClass());
        Assert.assertFalse(level.hasNextLevel());
        Assert.assertTrue(level.getBricks().isEmpty());
        Assert.assertTrue(level.isPlayableLevel());

        Assert.assertEquals(20,level3.getNumberOfBricks());
        Assert.assertFalse(level3.getBricks().isEmpty());
        Assert.assertNotEquals(0,level3.getPoints());
        Assert.assertEquals(4*50+6*200,level3.getPoints());
        Assert.assertNotEquals("",level3.getName());


    }

    @Test
    public void testBreakOutLevel(){
       level.setNextLevel(level2);
       Assert.assertTrue(level.hasNextLevel());
       Assert.assertTrue(level.getNextLevel().isPlayableLevel());
       Assert.assertEquals(level2,level.getNextLevel());

       level.addPlayingLevel(level3);
       Assert.assertTrue(level.getNextLevel().hasNextLevel());
       Assert.assertTrue(level.getNextLevel().getNextLevel().isPlayableLevel());
       Assert.assertEquals(level3,level.getNextLevel().getNextLevel());
       Assert.assertEquals(level3,level2.getNextLevel());
       Assert.assertEquals(0,level.getNextLevel().getNumberOfBricks());

       Assert.assertNotEquals(level,level.getNextLevel());
       Assert.assertNotEquals(level2,level.getNextLevel().getNextLevel());
       Assert.assertNotEquals(level3,level.getNextLevel());
       Assert.assertNotEquals(0,level.getNextLevel().getNextLevel().getPoints());
    }

    @Test
    public void testBricksUpdate(){
        BrickDestroyedUpdate brickDestroyedUpdate=new BrickDestroyedUpdate(new GlassBrick());
        level3.brickDestroyedUpdate(brickDestroyedUpdate);
        Assert.assertEquals(19,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new WoodenBrick()));
        Assert.assertEquals(18,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new GlassBrick()));
        Assert.assertEquals(17,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new GlassBrick()));
        Assert.assertEquals(16,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new WoodenBrick()));
        Assert.assertEquals(15,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new WoodenBrick()));
        Assert.assertEquals(14,level3.getNumberOfBricks());

        level3.brickDestroyedUpdate(new BrickDestroyedUpdate(new GlassBrick()));
        Assert.assertEquals(13,level3.getNumberOfBricks());

        level3.metalBrickDestroyedUpdate(new MetalBrickDestroyedUpdate());
        Assert.assertEquals(12,level3.getNumberOfBricks());

        level3.setGoldenBricks(2,1,1);
        Assert.assertEquals(14,level3.getNumberOfBricks());

        level3.goldenBrickDestroyedUpdate(new GoldenBrickDestroyedUpdate());
        Assert.assertEquals(13,level3.getNumberOfBricks());

    }
}
