package de.loki.geometricspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Loki on 28.05.2017.
 */

public class Spawner extends CircleParticle {

    private Array<Vector2> movement;
    private int step;
    private float speed;

    public Spawner(float mass, Array<Vector2> movement){
        super(movement.get(0).cpy(), mass);

        this.movement = movement;
        this.step = 1;
        this.speed = 5f;
    }

    public Spawner(float mass, Array<Vector2> movement, float speed){
        super(movement.get(0).cpy(), mass);

        this.movement = movement;
        this.step = 1;
        this.speed = speed;
    }

    @Override
    public void render(){
        velocity.add(acceleration);
        position.add(velocity);

        Vector2 v = movement.get(step).cpy().sub(position.cpy());

        v.nor();
        v.scl(speed);

        acceleration = v.scl(Gdx.graphics.getDeltaTime());

        if(new Rectangle(movement.get(step).x-50, movement.get(step).y-50, 100, 100).contains(position)){
            step++;
            velocity.scl(0);
            if(step>=movement.size){
                ParticleManagement.toRemoveSpawners.add(this);
                step--;
            }
        }

    }
}
