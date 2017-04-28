package de.loki.geometricspace;

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

}
