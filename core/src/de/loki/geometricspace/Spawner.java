package de.loki.geometricspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Loki on 28.05.2017.
 */

public class Spawner extends Particle {

    private Array<Vector2> movement;
    private int step;
    private float speed;

    public Spawner(float mass, ParticleType type, Array<Vector2> movement){
        super(movement.get(0).cpy(), mass, type);

        this.movement = movement;
        this.step = 1;
        this.speed = 5f;
    }

    public Spawner(float mass, ParticleType type, Array<Vector2> movement, float speed){
        super(movement.get(0).cpy(), mass, type);

        this.movement = movement;
        this.step = 1;
        this.speed = speed;
    }

    @Override
    public void render(){
        getVelocity().add(getAccelaration());
        getPosition().add(getVelocity());

        Vector2 v = movement.get(step).cpy().sub(getPosition().cpy());

        v.nor();
        v.scl(speed);

        getAccelaration().set(v.scl(Gdx.graphics.getDeltaTime()));

        if(new Rectangle(movement.get(step).x-50, movement.get(step).y-50, 100, 100).contains(getPosition())){
            step++;
            getVelocity().set(new Vector2());
            if(step>=movement.size){
                ParticleManagement.toRemoveSpawners.add(this);
                step--;
            }
        }

    }
}
