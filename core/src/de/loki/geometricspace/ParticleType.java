package de.loki.geometricspace;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Loki on 28.04.2017.
 */
public enum ParticleType {

    Triangle(0),
    Rectangle(1),
    Circle(2);

    private int value;

    private ParticleType(int value) {
        this.value = value;
    }

    public static ParticleType getRandomParticleType(){
        switch (MathUtils.random(0, 2)){
            case 0:
                return Triangle;
            case 1:
                return Rectangle;
            case 2:
                return Circle;
            default:
                break;
        }

        return null;
    }

}
