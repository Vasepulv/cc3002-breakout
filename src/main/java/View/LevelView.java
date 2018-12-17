package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.BreakoutLevel;
import logic.level.Level;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelView {

    List<Entity> bricks;
    Level level;

    public LevelView(Level level){
        bricks=new ArrayList<>();
        this.level=level;
    }

    public void init(){
        int n=level.getNumberOfBricks();
        int k=n/10;
        Entity brick;
        int l=0;
        for(int i=0;i<10;i++){
            for(int j=0;j<k;j++){
                brick=GameFactory.newBrick();
                brick.setPosition(i*60+5,j*30+50);
                if(level.getBricks().get(l).getClass().equals(GlassBrick.class)){
                    brick.setView(new Rectangle(50,20, Color.LIGHTBLUE));
                }
                else if(level.getBricks().get(l).getClass().equals(WoodenBrick.class)){
                    brick.setView(new Rectangle(50,20, Color.BROWN));
                }
                else if(level.getBricks().get(l).getClass().equals(MetalBrick.class)){
                    brick.setView(new Rectangle(50,20, Color.GOLDENROD));
                }
                bricks.add(brick);
                l++;
                FXGL.getApp().getGameWorld().addEntity(brick);
                }
            }
        }

    }


