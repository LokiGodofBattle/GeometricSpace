package de.loki.geometricspace;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 30.05.2017.
 */

public class CircleParticle extends Particle {

    public CircleParticle(Vector2 position, float mass){
        super(position);
        edge = (float) Math.sqrt(mass/Math.PI);
    }

    @Override
    public void draw(){
        Main.shapeRenderer.circle(position.x, position.y, edge);
    }

}
