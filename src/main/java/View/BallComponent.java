package View;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

/**
 * This class represents the actions of the Balls entities.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class BallComponent extends Component {
    private PhysicsComponent physics;

    public BallComponent(){
        physics=new PhysicsComponent();
    }

    public void releaseBall(){
        if(!physics.isMoving()){
            physics.setLinearVelocity(60*5,-5*60);
        }
    }
}
