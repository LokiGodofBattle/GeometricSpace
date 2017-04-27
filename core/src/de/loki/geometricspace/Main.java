package de.loki.geometricspace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {
	private static OrthographicCamera camera;
	private Viewport viewport;
	public final static int VIEWPORT_WIDTH = 1440;
	private static float aspect_ratio;
	private ShapeRenderer shapeRenderer;
	private TextureRegion textureRegion;
	private EarClippingTriangulator triangulator;
	private ShortArray indices;
	private Polygon polygon;
	private PolygonRegion polyRegion;
	private PolygonSprite polygonSprite;
	private PolygonSpriteBatch polyBatch;
	private Pixmap pixmap;

	@Override
	public void create() {

		//Ausrechnen des Größenverhältnisses des Geräts
		aspect_ratio = Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

		camera = new OrthographicCamera();

		//FitViewport damit es auf allen Geräten gleich aussieht
		viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_WIDTH * aspect_ratio, camera);
		viewport.apply();

		//Kamera zentrieren
		camera.position.set(VIEWPORT_WIDTH / 2f, VIEWPORT_WIDTH * aspect_ratio / 2f, 0);

		shapeRenderer = new ShapeRenderer();
		polyBatch = new PolygonSpriteBatch();

		Player.init();

		polygon = new Polygon(new float[]{0, 0, VIEWPORT_WIDTH/2, getViewportHeight(), VIEWPORT_WIDTH, 0});

		pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLUE);
		pixmap.fill();


		textureRegion = new TextureRegion(new Texture(pixmap));

		triangulator = new EarClippingTriangulator();
		indices = triangulator.computeTriangles(polygon.getVertices());

		polyRegion = new PolygonRegion(textureRegion, polygon.getVertices(), indices.toArray());
		polygonSprite = new PolygonSprite(polyRegion);

	}

	@Override
	public void render() {

		Player.move();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		Player.draw(shapeRenderer);
		shapeRenderer.end();
		polyBatch.setProjectionMatrix(camera.combined);
		polyBatch.begin();
		polygonSprite.draw(polyBatch);
		polyBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		camera.position.set(VIEWPORT_WIDTH / 2f, VIEWPORT_WIDTH * aspect_ratio / 2f, 0);
	}

	public static float getViewportHeight() {
		return VIEWPORT_WIDTH * aspect_ratio;
	}
}