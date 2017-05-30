package de.loki.geometricspace;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 28.04.2017.
 */
public class ParticleType {

    public static Particle getRandomParticle(Vector2 position, float mass){
        switch (MathUtils.random(0, 2)){
            case 0:
                return new TriangleParticle(position, mass);
            case 1:
                return new RectangleParticle(position, mass);
            case 2:
                return new CircleParticle(position, mass);
            default:
                break;
        }

        return null;
    }

}
