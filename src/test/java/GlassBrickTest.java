import logic.brick.GlassBrick;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GlassBrickTest {
    private GlassBrick glassBrick;

    @Before
    public void setUp(){
        glassBrick=new GlassBrick();
    }

    @Test
    public void basicTest(){
        Assert.assertFalse(glassBrick.isDestroyed());
        Assert.assertEquals(50,glassBrick.getScore());
        Assert.assertEquals(1,glassBrick.remainingHits());
    }

    @Test
    public void testBrickDestroyed(){
        glassBrick.hit();
        Assert.assertEquals(0,glassBrick.remainingHits());
        Assert.assertEquals(50,glassBrick.getScore());
        Assert.assertTrue(glassBrick.isDestroyed());

        glassBrick.hit();
        Assert.assertEquals(0,glassBrick.remainingHits());
        Assert.assertEquals(50,glassBrick.getScore());
        Assert.assertTrue(glassBrick.isDestroyed());
    }
}
