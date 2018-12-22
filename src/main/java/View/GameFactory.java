package View;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the factory of the elements in the game, like Bricks, Player (the bar), the Walls and the Bricks.
 * @version 1.0
 * @author Valentina Sepulveda
 *
 */
public class GameFactory implements EntityFactory{

    @Spawns("Player")
    public static Entity newPlayer(double x, double y){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        return Entities.builder()
                .type(GameType.PLAYER)
                .at(x,y)
                .viewFromNodeWithBBox(new Rectangle(100,30,Color.BLUEVIOLET))
                .with(physics)
                .with(new PlayerControl())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("BackGround")
    public static Entity newBackground(){
        return Entities.builder()
                .viewFromNode(new Rectangle(600,600,Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    @Spawns("Ball")
    public static Entity newBall(double x, double y){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().restitution(1f).density(1f).friction(1f));
        return Entities.builder()
                .type(GameType.BALL)
                .at(x,y)
                .bbox(new HitBox("Ball",BoundingShape.circle(10)))
                .viewFromNode(new Circle(10,Color.LIGHTSALMON))
                .with(physics, new CollidableComponent(true))
                .with(new BallComponent())
                .build();
    }

    @Spawns("Wall")
    public static Entity newWalls(){
        Entity walls=Entities.makeScreenBounds(100);
        walls.setType(GameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    @Spawns("Brick")
    public static Entity newBrick(){
        PhysicsComponent physics=new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        return Entities.builder()
                .type(GameType.BRICK)
                .with(new CollidableComponent(true))
                .bbox(new HitBox(BoundingShape.box(50,20)))
                .with(physics)
                .with(new BrickComponent())
                .build();
    }

    public static Entity newParticles(){
        ParticleEmitter emitter=new ParticleEmitter();
        emitter.setStartColor(Color.web("ffffe0"));
        emitter.setSize(3,5);
        emitter.setNumParticles(8);

        ParticleComponent particleComponent=new ParticleComponent(emitter);
        particleComponent.setOnFinished(()->particleComponent.getEntity().removeFromWorld());
        return Entities.builder()
                .with(particleComponent)
                .build();
    }

}
