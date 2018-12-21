package View;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import facade.HomeworkTwoFacade;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
    private LevelView levelView;
    private HomeworkTwoFacade controller;
    private boolean firstLevel;

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
        firstLevel=true;
        controller=new HomeworkTwoFacade();
        Level level=controller.getCurrentLevel();
        levelView =new LevelView(level);
        levelView.init();
    }

    @Override
    protected void initUI() {
        Label labelBalls = new Label("Balls");
        Label labelScore = new Label("Score");
        Label labelTotalScore = new Label("TotalScore");
        Label labelLevel = new Label("Level Name");

        Label score = new Label();
        Label balls = new Label();
        Label totalScore = new Label();
        Label actualLevel = new Label();

        HBox hBox=new HBox(20);
        score.textProperty().bind(getGameState().intProperty("Score").asString());
        balls.textProperty().bind(getGameState().intProperty("Balls").asString());
        totalScore.textProperty().bind(getGameState().intProperty("TotalScore").asString());
        actualLevel.textProperty().bind(getGameState().stringProperty("Level"));

       hBox.getChildren().addAll(labelScore, score, labelBalls, balls, labelTotalScore, totalScore, labelLevel, actualLevel);
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
    }

    private void nextLevel() {
        if (!firstLevel){
            getGameWorld().getEntitiesByType(GameType.BRICK).forEach(Entity::removeFromWorld);
            Level level2=controller.newLevelWithBricksFull("Level 2",20,1,0.5,1);
            controller.addPlayingLevel(level2);}
        else {
            Level level1=controller.newLevelWithBricksFull("Level 1",20,0.5,1,1);
            controller.setCurrentLevel(level1);
            levelView = new LevelView(controller.getCurrentLevel());
            levelView.init();
            getGameState().setValue("Level", controller.getCurrentLevel().getName());
            firstLevel = false;
        }
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
        if(controller.getBallsLeft()>0 && !getGameWorld().getSingleton(GameType.BALL).isPresent()){
            Entity player=getGameWorld().getSingleton(GameType.PLAYER).get();
            Entity ball=GameFactory.newBall(player.getX()+40,player.getY()-20);
            getGameWorld().addEntity(ball);
            controller.dropBall();
            getGameState().increment("Balls",-1);
        }
        else if(getGameWorld().getSingleton(GameType.BALL).isPresent()){

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
        getGameState().setValue("Score",scoreUpdate.getScore());
        getGameState().setValue("TotalScore",scoreUpdate.getScore());
    }

    @Override
    public void maxLevelScoreUpdate(MaxLevelScoreReachedUpdate maxLevelScoreReachedUpdate) {
       showWinner();
    }

    @Override
    public void metalBrickDestroyedUpdate(MetalBrickDestroyedUpdate metalBrickDestroyedUpdate) {
        getGameState().increment("Balls",1);
    }
}
