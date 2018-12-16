package View;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;

public class BrickComponent extends Component {
    public Brick brick;

    public BrickComponent(Brick brick){
        this.brick=brick;
    }


    public void hit(){
        brick.hit();
        if(brick.isDestroyed()){

        }
    }
}
