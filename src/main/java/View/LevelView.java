package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.*;
import logic.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents the view of the Levels. This class constructs the visual representation of the levels.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class LevelView {

    private List<Entity> bricks;
    private Level level;

    public LevelView(Level level){
        bricks=new ArrayList<>();
        this.level=level;
    }

    public void init(){
        int n=level.getNumberOfBricks();
        Collections.shuffle(level.getBricks());
        int k=n/10;
        Entity brick;
        int l=0;
        for(int i=0;i<10;i++){
            for(int j=0;j<k;j++){
                brick=GameFactory.newBrick();
                brick.setPosition(i*60+5,j*30+50);
                setBrickView(brick, l);
                l = getL(brick, l);
                FXGL.getApp().getGameWorld().addEntity(brick);
            }
        }
        if(bricks.size()<level.getBricks().size()){
            int resto=level.getBricks().size()-bricks.size();
            for(int i=0;i<resto;i++){
                brick=GameFactory.newBrick();
                brick.setPosition(i*60+5,k*30+60);
                l = getL(brick, l);
                FXGL.getApp().getGameWorld().addEntity(brick);
                }
            }
        }

    private int getL(Entity brick, int l) {
        brick.getComponent(BrickComponent.class).setBrick(level.getBricks().get(l));
        bricks.add(brick);
        l++;
        brick.getView().setOnMouseClicked(event -> brick.getComponent(BrickComponent.class).hit());
        return l;
    }

    private void setBrickView(Entity brick, int l) {
        if(level.getBricks().get(l).getClass().equals(GlassBrick.class)){
            brick.setView(new Rectangle(50,20, Color.LIGHTBLUE));
        }
        else if(level.getBricks().get(l).getClass().equals(WoodenBrick.class)){
            brick.setView(new Rectangle(50,20, Color.BROWN));
        }
        else if(level.getBricks().get(l).getClass().equals(MetalBrick.class)){
            brick.setView(new Rectangle(50,20, Color.GRAY));
        }
        else{
            brick.setView(new Rectangle(50,20,Color.GOLD));
        }
    }


}





