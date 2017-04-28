package de.loki.geometricspace;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by Loki on 28.04.2017.
 */
public class Particle {

    private float x;
    private float y;
    private float mass;
    private ParticleType type;
    private Polygon polygon;
    private float edge;

    public Particle(float x, float y, float mass, ParticleType type){
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.type = type;

        if(type == ParticleType.Rectangle){
            edge = (float) Math.sqrt((double) mass);
        }
        else if(type == ParticleType.Circle){
            edge = (float) Math.sqrt(mass/Math.PI);
        }
        else if(type == ParticleType.Triangle){
            edge = (float) Math.sqrt((mass/Math.sqrt(3))*4);

            float h = (float) (Math.sqrt(3) * (Math.pow(edge, 2)/2));

            polygon = new Polygon(new float[]{x-edge/2, y-h/2, x+edge/2, y-h/2, x, y+h/2});
        }
    }

}
