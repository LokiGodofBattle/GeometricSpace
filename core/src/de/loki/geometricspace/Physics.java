package de.loki.geometricspace;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 05.05.2017.
 */

public class Physics {

    private static float gravity;
    private static float dragForce;
    private static float maxLength;

    public static void init(){
        gravity = 1000;
        dragForce = 0.01f;
        maxLength = 750;
    }

    public static void render(){

        //Auswirken der Gravitation des Spielers
        applyGravity(Player.getPosition().cpy());

        //Auswirken der Gravitation der Attractors
        for(Particle p : ParticleManagement.attractors){
            applyGravity(p.getPosition().cpy());
        }

        //DragForce, ähnlich wie Luftwiederstand
        for(Particle p : ParticleManagement.particles){
            applyDrag(p);
        }

    }

    private static void applyGravity(Vector2 position){
        for(Particle p : ParticleManagement.particles)
        {
            //Variable mit der gerechnet wird
            Vector2 acceleration = position.cpy().sub(p.getPosition().cpy());

            //Limitierung der Länge des Vektors
            float length = acceleration.len();
            length = MathUtils.clamp(length, 0, 350);

            //Formel für die Gravitations Stärke
            float force = (float) (gravity/ Math.pow(length, 2)*10000);

            //Auswirken der Stärke auf den Vektor
            acceleration.nor();
            acceleration.setLength(force);

            //Anwenden der Force
            p.applyForce(acceleration.cpy());


        }
    }

    private static void applyDrag(Particle p){

        //Formel für die Stärke des Wiederstandes
        float force = dragForce * p.getVelocity().cpy().len() * p.getVelocity().cpy().len();

        //Gegengesetzte Richtung zur Velocity berechnen und Force drauf anwenden
        Vector2 drag = p.getVelocity().cpy();
        drag.nor();
        drag.scl(force);
        drag.scl(-1);

        //Force anwenden
        p.applyForce(drag);
    }

}
