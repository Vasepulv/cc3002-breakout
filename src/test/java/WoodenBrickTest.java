import logic.brick.WoodenBrick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WoodenBrickTest {

    private WoodenBrick woodenBrick;

    @Before
    public void setUp(){
        woodenBrick=new WoodenBrick();
    }

    @Test
    public void basicTest(){
        Assert.assertFalse(woodenBrick.isDestroyed());
        Assert.assertEquals(3,woodenBrick.remainingHits());
        Assert.assertEquals(200,woodenBrick.getScore());
    }


    @Test
    public void testBrickDestroyed(){
        woodenBrick.hit();
        Assert.assertEquals(2,woodenBrick.remainingHits());
        Assert.assertEquals(200,woodenBrick.getScore());
        Assert.assertFalse(woodenBrick.isDestroyed());

        woodenBrick.hit();
        Assert.assertEquals(1,woodenBrick.remainingHits());
        Assert.assertFalse(woodenBrick.isDestroyed());

        woodenBrick.hit();
        Assert.assertEquals(0,woodenBrick.remainingHits());
        Assert.assertTrue(woodenBrick.isDestroyed());

        woodenBrick.hit();
        Assert.assertEquals(0,woodenBrick.remainingHits());
        Assert.assertTrue(woodenBrick.isDestroyed());
    }
}
