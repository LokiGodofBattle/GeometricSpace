package de.loki.geometricspace;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 05.05.2017.
 */

public class Physics {

    private static float gravity;

    public static void init(){
        gravity = 5000;
    }

    public static void render(){

        for(int i = 0; i<Main.particles.size; i++)
        {
            Vector2 acceleration = Player.getPosition().cpy();
            acceleration.sub(Main.particles.get(i).getPosition().cpy());


            float force = (float) (gravity/ Math.pow((double) acceleration.len(), 2));

            acceleration.nor();
            acceleration.setLength(force*1000);

            Main.particles.get(i).applyForce(acceleration);
        }
    }

}
