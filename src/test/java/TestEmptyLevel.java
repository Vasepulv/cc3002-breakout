
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

public class TestEmptyLevel {
    private EmptyLevel level;
    private BreakoutLevel level2;


    @Before
    public void setUp(){
        level=new EmptyLevel();
        level2 = new BreakoutLevel();
    }

    @Test
    public void testEmptyLevel(){
        Assert.assertEquals(0,level.getPoints());
        Assert.assertEquals("",level.getName());
        Assert.assertEquals(0,level.getNumberOfBricks());
        Assert.assertTrue(level.getBricks().isEmpty());
        Assert.assertFalse(level.isPlayableLevel());

        Assert.assertFalse(level.hasNextLevel());

       level.addPlayingLevel(level2);
       Assert.assertNotEquals(level.getNextLevel(),level2);

       level.setNextLevel(level2);
       Assert.assertNotEquals(level.getNextLevel(),level2);

       level.setGoldenBricks(10,1,1);
       Assert.assertEquals(0,level.getNumberOfBricks());

       level.brickDestroyedUpdate(new BrickDestroyedUpdate(new GlassBrick()));
       Assert.assertEquals(0,level.getPoints());

       level.brickDestroyedUpdate(new BrickDestroyedUpdate(new WoodenBrick()));
       Assert.assertEquals(0,level.getPoints());

       level.goldenBrickDestroyedUpdate(new GoldenBrickDestroyedUpdate());
       Assert.assertEquals(0,level.getPoints());

       level.metalBrickDestroyedUpdate(new MetalBrickDestroyedUpdate());
       Assert.assertEquals(0,level.getPoints());

    }
}
