package de.loki.geometricspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 28.04.2017.
 */
public class Particle {

    protected Vector2 position;
    protected float edge;
    protected Vector2 velocity;
    protected Vector2 acceleration;

    public Particle(Vector2 position){
        this.position = position;

        velocity = new Vector2();
        acceleration = new Vector2();
    }

    //Zeichnen basierend auf dem ParticleTyp
    protected void draw(){
    }

    public void render(){

        // Velocity + Acceleration
        // Position + Velocity
        velocity.add(acceleration.cpy().scl(Gdx.graphics.getDeltaTime()));
        velocity.limit(300);
        position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()));

        //Rand Begrenzung
        if(position.x<0 || position.x>Main.VIEWPORT_WIDTH || position.y<0 || position.y>Main.getViewportHeight()) ParticleManagement.toRemoveParticles.add(this);

        //Zurücksetzen der Acceleration
        acceleration.scl(0);
    }

    //Einwirkung von Force
    public void applyForce(Vector2 force){
        acceleration.add(force);
    }


    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public Vector2 getAccelaration(){
        return acceleration;
    }

    public float getEdge() {
        return edge;
    }

    public void setEdge(float edge) {
        this.edge = edge;
    }

}
