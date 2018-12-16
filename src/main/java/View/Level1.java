package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.layout.Pane;
import logic.level.BreakoutLevel;

import java.util.ArrayList;
import java.util.List;

public class Level1 extends BreakoutLevel {

    List<Entity> bricks;
    private Pane pane;
    int numberOfBricks;

    public Level1(){
        bricks=new ArrayList<>();
        pane=new Pane();
        numberOfBricks=10;
    }

    public void init(){
        Entity brick;
        for(int i=0;i<6;i++){
            for(int j=0;j<5;j++){
                brick=GameFactory.newBrick();
                brick.setPosition(i*100,j*50+10);
                bricks.add(brick);
                FXGL.getApp().getGameWorld().addEntity(brick);
            }
        }

    }

}
