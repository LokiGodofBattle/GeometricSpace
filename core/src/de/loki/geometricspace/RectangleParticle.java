package de.loki.geometricspace;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 30.05.2017.
 */

public class RectangleParticle extends Particle {

    public RectangleParticle(Vector2 position, float mass){
        super(position);
        edge = (float) Math.sqrt((double) mass);
    }

    @Override
    public void draw(){
        Main.shapeRenderer.rect(position.x - edge / 2, position.y - edge / 2, edge, edge);
    }

}
