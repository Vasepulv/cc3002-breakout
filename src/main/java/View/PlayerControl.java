package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.scene.GameScene;
import controller.Game;
import javafx.geometry.Point2D;
import javafx.scene.control.Control;

/**
 * This class represents the actions of the Player entities.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class PlayerControl extends Component {
    private GameScene gameScene;
    private PhysicsComponent physics;
    private PositionComponent position;

    private double speed=0;

    private boolean isBallMoving(){
        Entity ball=FXGL.getApp().getGameWorld().getSingleton(GameType.BALL).get();
        if(!ball.getComponent(PhysicsComponent.class).isMoving()){
            return false;
        }
        return true;
    }

    public void left(){
        if(isBallMoving()&&position.getX()>0){
        getEntity().setScaleX(-1);
        Point2D point2D=new Point2D(position.getX()-5,position.getY());
        physics.reposition(point2D);
        }
    }

    public void right() {
        if (isBallMoving() && position.getX()<=500) {
            getEntity().setScaleX(1);
            Point2D point2D=new Point2D(position.getX()+5,position.getY());
            physics.reposition(point2D);
        }
    }

    public void reposition(){

    }

}
