package de.loki.geometricspace;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Loki on 28.05.2017.
 */

public class ParticleManagement {


    public static Array<Particle> particles;
    public static Array<Particle> attractors;
    public static Array<Spawner> spawners;
    public static Array<Particle> toRemoveParticles;
    public static Array<Spawner> toRemoveSpawners;
    public static EarClippingTriangulator triangulator;
    public static TextureRegion textureRegion;
    public static Pixmap pixmap;

    public static int movementComplexity;

    public static void init(){

        //Erzeugen der Beiden Listen
        particles = new Array<Particle>();
        attractors = new Array<Particle>();
        spawners = new Array<Spawner>();
        toRemoveParticles = new Array<Particle>();
        toRemoveSpawners = new Array<Spawner>();

        movementComplexity = 5;

        //Erzugen einer Triangulators, für das Polygon
        triangulator = new EarClippingTriangulator();

        //Erzeugen einer blauen Pixmap, mit dem Maßen 1x1
        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();

        //Erzeugen einer neuen TextureRegion, die Später der PolygonRegion übergeben wird
        textureRegion = new TextureRegion(new Texture(pixmap));

        //Füllen der Listen mit zufällig generierten Objekten
        for(int i = 0; i<3; i++){
            particles.add(ParticleType.getRandomParticle(new Vector2(MathUtils.random(0, Main.VIEWPORT_WIDTH), MathUtils.random(0, Main.getViewportHeight())), MathUtils.random(500, 1000)));
        }

    }

    public static void render(){

        for(Particle p : particles){
            p.render();
        }

        for(Particle p : attractors){
            p.render();
        }

        //Entgültiges Entfehrnen der Particle die dafür gekennzeichnet wurden
        for(Particle p : toRemoveParticles){
            particles.removeValue(p, true);
        }

        for(Spawner s : spawners){
            s.render();
        }
    }

    public static void draw(){

        //Zeichnen der Particle
        Main.shapeRenderer.setColor(Color.BLUE);
        for(Particle p : particles){
            p.draw();
        }

        //Zeichnen der Attractors
        Main.shapeRenderer.setColor(Color.RED);
        for(Particle p : attractors){
            p.draw();
        }

        Main.shapeRenderer.setColor(Color.GREEN);
        for(Spawner s : spawners){
            s.draw();
        }

    }

}
