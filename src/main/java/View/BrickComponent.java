package View;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;

public class BrickComponent extends Component {
    public Brick brick;

    public BrickComponent(){

    }


    public void hit(){
        getEntity().removeFromWorld();
    }

    public void destroy(){
        hit();
    }
}
