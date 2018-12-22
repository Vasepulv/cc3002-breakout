import com.fasterxml.jackson.annotation.JsonTypeInfo;
import logic.brick.GoldenBrick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoldenBrickTest {

    private GoldenBrick goldenBrick;

    @Before
    public void setUp(){
        goldenBrick=new GoldenBrick();
    }

    @Test
    public void basicTest(){
        Assert.assertFalse(goldenBrick.isDestroyed());
        Assert.assertEquals(0,goldenBrick.getScore());
        Assert.assertEquals(10,goldenBrick.remainingHits());
    }

    @Test
    public void testBrickDestroyed(){
        goldenBrick.hit();
        Assert.assertEquals(9,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(8,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(7,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(6,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(5,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(4,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(3,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(2,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(1,goldenBrick.remainingHits());
        Assert.assertFalse(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(0,goldenBrick.remainingHits());
        Assert.assertTrue(goldenBrick.isDestroyed());

        goldenBrick.hit();
        Assert.assertEquals(0,goldenBrick.remainingHits());
        Assert.assertTrue(goldenBrick.isDestroyed());
    }
}
