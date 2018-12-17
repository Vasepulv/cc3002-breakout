package View;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;

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
        FXGL.getApp().getAudioPlayer().playSound("boom8.wav");
        //brick.hit();
        getEntity().removeFromWorld();
    }


}
