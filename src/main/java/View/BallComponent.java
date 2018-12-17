package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import logic.brick.Brick;

public class BallComponent extends Component {
    PhysicsComponent physics;

    public BallComponent(){
        physics=new PhysicsComponent();
    }

    public void releaseBall(){
        if(!physics.isMoving()){
        physics.setLinearVelocity(60*5,-5*60);
        }
    }
}
