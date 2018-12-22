import logic.brick.AbstractBrick;
import logic.brick.MetalBrick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MetalBrickTest {

    private MetalBrick metalBrick;

    @Before
    public void setUp(){
        metalBrick=new MetalBrick();
    }

    @Test
    public void basicTest(){
        Assert.assertEquals(10,metalBrick.remainingHits());
        Assert.assertEquals(0,metalBrick.getScore());
        Assert.assertFalse(metalBrick.isDestroyed());
    }

    @Test
    public void testDestroyedBrick(){
        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(9,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(8,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(7,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(6,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(5,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(4,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(3,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(2,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertFalse(metalBrick.isDestroyed());
        Assert.assertEquals(1,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertTrue(metalBrick.isDestroyed());
        Assert.assertEquals(0,metalBrick.remainingHits());

        metalBrick.hit();
        Assert.assertTrue(metalBrick.isDestroyed());
        Assert.assertEquals(0,metalBrick.remainingHits());
    }
}
