package de.loki.geometricspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ShortArray;

/**
 * Created by Loki on 30.05.2017.
 */

public class TriangleParticle extends Particle {

    private PolygonRegion polyRegion;
    private PolygonSprite polygonSprite;
    private Polygon polygon;

    public TriangleParticle(Vector2 position, float mass){
        super(position);

        edge = (float) Math.sqrt((mass/Math.sqrt(3))*4);

        float h = (float) (Math.sqrt(Math.pow(edge, 2)- Math.pow(edge/2, 2)));

        polygon = new Polygon(new float[]{position.x-edge/2, position.y-h/2, position.x+edge/2, position.y-h/2, position.x, position.y+h/2});

        ShortArray indices = ParticleManagement.triangulator.computeTriangles(polygon.getTransformedVertices());
        polyRegion = new PolygonRegion(ParticleManagement.textureRegion, polygon.getTransformedVertices(), indices.toArray());
        polygonSprite = new PolygonSprite(polyRegion);

    }

    @Override
    public void draw(){
        //Zeichnen des Polygons
        polygonSprite.draw(Main.polyBatch);
    }

    @Override
    public void render(){
        super.render();

        //Nachziehen des Polygons
        Vector2 v = velocity.cpy().scl(Gdx.graphics.getDeltaTime());
        polygonSprite.translate(v.x, v.y);
    }

}
