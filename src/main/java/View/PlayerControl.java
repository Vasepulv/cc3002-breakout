package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.scene.GameScene;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    private boolean isBallMoving(){
        Entity ball=FXGL.getApp().getGameWorld().getSingleton(GameType.BALL).get();
        return ball.getComponent(PhysicsComponent.class).isMoving();
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

    public void change() {
        getEntity().setViewWithBBox(new Rectangle(200,30, Color.BLUEVIOLET));
    }
}
