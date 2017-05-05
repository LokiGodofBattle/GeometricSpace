package de.loki.geometricspace;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 05.05.2017.
 */

public class Physics {

    private static float gravity;

    public static void init(){
        gravity = 1000;
    }

    public static void render(){

        for(int i = 0; i<Main.particles.size; i++)
        {
            Vector2 velocity = Player.getPosition().cpy();
            velocity.sub(Main.particles.get(i).getPosition().cpy());


            float force = (float) (gravity/ Math.pow((double) velocity.len()/10, 2));

            velocity.nor();
            velocity.setLength(force*100);

            Main.particles.get(i).getVelocity().set(velocity);
        }
    }

}
