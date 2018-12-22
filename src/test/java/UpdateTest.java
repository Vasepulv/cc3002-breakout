import controller.Game;
import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.level.Level;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdateTest {

    private Game game;
    private Level level;
    private Level level2;

    @Before
    public void setUp(){
        game=new Game(3);
        level=new HomeworkTwoFacade().newLevelWithBricksNoMetal("Level1",10,0.5,1);
        level2=new HomeworkTwoFacade().newLevelWithBricksFull("Level2",10,0.5,1,1);
    }

    @Test
    public void testUpdateScore(){
        game.setLevel(level);
        game.getBricks().forEach(e->e.hit());

        Assert.assertNotEquals(0,game.getPoints());
        Assert.assertEquals(50*4,game.getPoints());
        Assert.assertFalse(game.winner());

        game.getBricks().forEach(e->e.hit());
        game.getBricks().forEach(e->e.hit());
        Assert.assertEquals(50*4+200*6,game.getPoints());
        Assert.assertTrue(game.winner());
    }

    @Test
    public void testMetalBrick(){
        game.setLevel(level2);

        Brick brick=game.getBricks().get(10);
        for(int i=0;i<10;i++){
           brick.hit();
        }
        Assert.assertEquals(4,game.getBalls());

        brick=game.getBricks().get(11);
        for(int i=0;i<10;i++){
            brick.hit();
        }
        Assert.assertEquals(5,game.getBalls());
    }

    @Test
    public void testGoldenBrick(){
        level.setGoldenBricks(2,1,1);
        game.setLevel(level);

        Brick brick=game.getBricks().get(10);
        for(int i=0;i<10;i++){
            brick.hit();
        }
        Assert.assertEquals(3,game.getBalls());
        Assert.assertEquals(0,game.getPoints());

        brick=game.getBricks().get(11);
        for(int i=0;i<10;i++){
            brick.hit();
        }
        Assert.assertEquals(3,game.getBalls());
        Assert.assertEquals(0,game.getPoints());
    }
}
