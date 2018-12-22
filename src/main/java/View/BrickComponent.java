package View;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.particle.ParticleComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.Brick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

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
        brick.hit();
        playEffect();
        if(brick.isDestroyed()){
            FXGL.getApp().getAudioPlayer().playSound("boom8.wav");
            getEntity().removeFromWorld();
        }
    }

    public void playEffect(){
        if (brick.getClass().equals(WoodenBrick.class)){
            FXGL.getApp().getAudioPlayer().playSound("pumpkin_break_01.wav");
        }
        else if(brick.getClass().equals(MetalBrick.class)){
            FXGL.getApp().getAudioPlayer().playSound("blacksmith_1.wav");
        }
    }
}
