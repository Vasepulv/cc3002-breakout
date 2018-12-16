package View;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.Level;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import controller.Game;
import facade.HomeworkTwoFacade;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.level.BreakoutLevel;

import java.util.Map;

public class GameApp extends GameApplication {
    private Level1 level1;
    private int balls;
    private HomeworkTwoFacade controller;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setHeight(600);
        gameSettings.setWidth(600);
        gameSettings.setTitle("BreakOut");
        gameSettings.setVersion("0.1");
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new GameFactory());
        Entity player=GameFactory.newPlayer(250,550);
        Entity background=GameFactory.newBackground();
        Entity walls=GameFactory.newWalls();
        Entity ball=GameFactory.newBall(290,530);
        getGameWorld().addEntities(player,background,walls,ball);
        initLevel();

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("Score",0);
        vars.put("Level",0);
        vars.put("Balls",3);
    }

    private void initLevel() {
        level1=new Level1();
        level1.init();

    }

    @Override
    protected void initUI() {
        controller=new HomeworkTwoFacade();


    }

    @Override
    protected void initInput() {
        Input input=getInput();
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(GameType.PLAYER).forEach(e->e.getComponent(PlayerControl.class).right());
            }
        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(GameType.PLAYER).forEach(e->e.getComponent(PlayerControl.class).left());
            }
        },KeyCode.A);

        input.addAction(new UserAction("Move Ball") {
            @Override
            protected void onActionBegin() {
                getGameWorld().getEntitiesByType(GameType.BALL).forEach(e->e.getComponent(BallComponent.class).releaseBall());
            }
        },KeyCode.SPACE);

        input.addAction(new UserAction("New Level") {
            @Override
            protected void onActionBegin() {
                nextLevel();
            }
        },KeyCode.N);

        input.addAction(new UserAction("New Ball") {
            @Override
            protected void onActionBegin() {
                addBall();
            }
        },KeyCode.M);
    }

    private void nextLevel() {

    }



    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameType.BALL,GameType.WALL) {
            @Override
            protected void onHitBoxTrigger(Entity ball, Entity wall, HitBox boxA, HitBox boxB) {
                if(boxB.getName().equals("BOT")){
                    ball.removeFromWorld();
                    addBall();
                }
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(GameType.BALL,GameType.BRICK) {
            @Override
            protected void onCollisionBegin(Entity ball, Entity brick) {

                //brick.getComponent(BrickComponent.class).hit();
            }
        });


    }

    private void addBall(){
        if(balls>0){

        }
        else{
            showGameOver();
        }
    }

    private void showGameOver(){
        getDisplay().showConfirmationBox("Game Over. Try Again?",yes->{
            if (yes){
                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
            }
            else{
                int score=getGameState().getInt("Score");
                //
            }
        });
    }


}
