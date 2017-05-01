package de.loki.geometricspace;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ShortArray;

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
    private PolygonRegion polyRegion;
    private PolygonSprite polygonSprite;

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

            float h = (float) (Math.sqrt(Math.pow(edge, 2)- Math.pow(edge/2, 2)));

            polygon = new Polygon(new float[]{x-edge/2, y-h/2, x+edge/2, y-h/2, x, y+h/2});
        }

    }

    public void draw(ShapeRenderer shapeRenderer, PolygonSpriteBatch polyBatch, EarClippingTriangulator triangulator, TextureRegion textureRegion){
        if(type == ParticleType.Rectangle) shapeRenderer.rect(x - edge / 2, y - edge / 2, edge, edge);
        else if(type == ParticleType.Circle) shapeRenderer.circle(x, y, edge);
        else if(type == ParticleType.Triangle){
            ShortArray indices = triangulator.computeTriangles(polygon.getVertices());
            polyRegion = new PolygonRegion(textureRegion, polygon.getVertices(), indices.toArray());
            polygonSprite = new PolygonSprite(polyRegion);

            polygonSprite.draw(polyBatch);
        }
    }


}
