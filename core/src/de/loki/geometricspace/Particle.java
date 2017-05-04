package de.loki.geometricspace;

import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;

/**
 * Created by Loki on 28.04.2017.
 */
public class Particle {

    private Vector2 position;
    private float mass;
    private ParticleType type;
    private Polygon polygon;
    private float edge;
    private PolygonRegion polyRegion;
    private PolygonSprite polygonSprite;

    public Particle(Vector2 position, float mass, ParticleType type){
        this.position = position;
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

            polygon = new Polygon(new float[]{position.x-edge/2, position.y-h/2, position.x+edge/2, position.y-h/2, position.x, position.y+h/2});
        }

    }

    public void draw(ShapeRenderer shapeRenderer, PolygonSpriteBatch polyBatch, EarClippingTriangulator triangulator, TextureRegion textureRegion){
        if(type == ParticleType.Rectangle) shapeRenderer.rect(position.x - edge / 2, position.y - edge / 2, edge, edge);
        else if(type == ParticleType.Circle) shapeRenderer.circle(position.x, position.y, edge);
        else if(type == ParticleType.Triangle){
            ShortArray indices = triangulator.computeTriangles(polygon.getVertices());
            polyRegion = new PolygonRegion(textureRegion, polygon.getVertices(), indices.toArray());
            polygonSprite = new PolygonSprite(polyRegion);

            polygonSprite.draw(polyBatch);
        }
    }


}
