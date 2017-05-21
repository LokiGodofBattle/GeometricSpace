package de.loki.geometricspace;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 05.05.2017.
 */

public class Physics {

    private static float gravity;
    private static float dragForce;
    private static float maxLength;

    public static void init(){
        gravity = 30000;
        dragForce = 0.01f;
        maxLength = 750;
    }

    public static void render(){

        applyGravity(Player.getPosition().cpy());

        for(Particle p : Main.attractors){
            applyGravity(p.getPosition().cpy());
        }

        for(Particle p : Main.particles){
            applyDrag(p);
        }

    }

    private static void applyGravity(Vector2 position){
        for(Particle p : Main.particles)
        {
            Vector2 acceleration = position.cpy().sub(p.getPosition().cpy());


            float length = acceleration.len();

            float force = (float) (gravity/ Math.pow(length, 2) *1000);

            acceleration.nor();
            acceleration.setLength(force);

            p.applyForce(acceleration.cpy());


        }
    }

    private static void applyDrag(Particle p){

            float force = dragForce * p.getVelocity().cpy().len() * p.getVelocity().cpy().len();

            Vector2 drag = p.getVelocity().cpy();
            drag.nor();
            drag.scl(force);
            drag.scl(-1);

            p.applyForce(drag);
    }

}
