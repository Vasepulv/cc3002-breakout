package View;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import controller.Game;
import facade.HomeworkTwoFacade;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import logic.level.Level;
import logic.update.*;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * This class represents the GUI of the game.
 *
 * @author Valentina Sepulveda
 * @version 1.0
 */
public class GameApp extends GameApplication implements Observer, LevelUpdateReceiver {
    private LevelView level1;
    private HomeworkTwoFacade controller;
    private Label score;
    private Label balls;
    private Label totalScore;
    private Label actualLevel;
    private Label labelLevel;
    private Label labelScore;
    private Label labelBalls;
    private Label labelTotalScore;


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
        vars.put("TotalScore",0);
        vars.put("Level","Level 0");
        vars.put("Balls",3);
    }

    private void initLevel() {
        controller=new HomeworkTwoFacade();
        Level level=controller.getCurrentLevel();
        level1=new LevelView(level);
        level1.init();
    }

    @Override
    protected void initUI() {
        labelBalls=new Label("Balls");
        labelScore=new Label("Score");
        labelTotalScore=new Label("TotalScore");
        labelLevel=new Label("Level Name");


        score=new Label();
        balls=new Label();
        totalScore=new Label();
        actualLevel=new Label();

        HBox hBox=new HBox(20);
        IntegerProperty integerProperty=new SimpleIntegerProperty(getGameState().getInt("Score"));
        score.textProperty().bind(integerProperty.asString());

        IntegerProperty integerProperty1=new SimpleIntegerProperty(getGameState().getInt("Balls"));
        balls.textProperty().bind(integerProperty1.asString());

        IntegerProperty integerProperty2=new SimpleIntegerProperty(getGameState().getInt("TotalScore"));
        totalScore.textProperty().bind(integerProperty2.asString());

        StringProperty stringProperty=new SimpleStringProperty(getGameState().getString("Level"));
        actualLevel.textProperty().bind(stringProperty);

       hBox.getChildren().addAll(labelScore,score,labelBalls,balls,labelTotalScore,totalScore,labelLevel,actualLevel);
        getGameScene().addUINode(hBox);
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

        input.addAction(new UserAction("Testing") {
            @Override
            protected void onActionBegin() {

            }
        },MouseButton.PRIMARY);
    }

    private void nextLevel() {
        Level level=controller.newLevelWithBricksFull("Level1",40,0.5,1,1);
        controller.setCurrentLevel(level);
        level1=new LevelView(level);
        level1.init();
        getGameState().setValue("Level",level.getName());

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
                brick.getComponent(BrickComponent.class).hit();
                ball.getComponent(PhysicsComponent.class).setLinearVelocity(60*5,-5*60);
            }
        });
    }





    private void addBall(){
        if(controller.getBallsLeft()>0){
            Entity player=getGameWorld().getSingleton(GameType.PLAYER).get();
            Entity ball=GameFactory.newBall(player.getX()+40,player.getY()-20);
            getGameWorld().addEntity(ball);
            controller.dropBall();
        }
        else{
            showGameOver();
        }
    }

    private void showGameOver(){
        getDisplay().showConfirmationBox("Game Over. Try Again?",yes->{
            if (yes){
                getGameWorld().getEntitiesByType(GameType.PLAYER,GameType.BRICK,GameType.WALL).forEach(Entity::removeFromWorld);
                startNewGame();
            }
            else{
                int score=getGameState().getInt("Score");
                exit();
            }
        });
    }

    private void showWinner(){
        getDisplay().showConfirmationBox("Congratulations. You have beaten the game. Try again?", yes ->{
            if (yes){
                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                startNewGame();
            }
            else{
                exit();
            }
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof LevelUpdate){
            ((LevelUpdate) arg).accept(this);
        }

    }


    @Override
    public void scoreUpdate(ScoreUpdate scoreUpdate) {
        getGameState().setValue("Score",scoreUpdate);
    }

    @Override
    public void maxLevelScoreUpdate(MaxLevelScoreReachedUpdate maxLevelScoreReachedUpdate) {
       showWinner();
    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {
        getGameState().setValue("Balls",controller.getBallsLeft());
    }
}
