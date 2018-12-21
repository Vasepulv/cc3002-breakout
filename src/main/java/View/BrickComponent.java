package View;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.Brick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import sun.security.util.ManifestEntryVerifier;

/**
 * This class represents the actions of the Bricks Entities.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class BrickComponent extends Component {
    public Brick brick;

    public BrickComponent(){
    }

    public void setBrick(Brick brick){
        this.brick=brick;
    }

    public void hit(){
        //FXGL.getApp().getAudioPlayer().playSound("boom8.wav");
        brick.hit();
        playEffect();
        if(brick.isDestroyed()){
            FXGL.getApp().getAudioPlayer().playSound("boom8.wav");
            getEntity().removeFromWorld();
        }
    }

    public void playEffect(){
        if (brick.getClass().equals(WoodenBrick.class)){
            FXGL.getApp().getAudioPlayer().playSound("");
        }
        else if(brick.getClass().equals(MetalBrick.class)){
            FXGL.getApp().getAudioPlayer().playSound("");
        }
    }


    public void changeMetalBrickFirstTime(){
        getEntity().setView(new Rectangle(50,20, Color.DIMGRAY));
    }

    public void changeMetalBrickSecondTime(){
        getEntity().setView(new Rectangle(50,20,Color.DARKSLATEGRAY));
    }

    public void changeWoodenBrick(){
        getEntity().setView(new Rectangle(50,20,Color.DARKRED));
    }
}
