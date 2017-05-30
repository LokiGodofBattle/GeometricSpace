package de.loki.geometricspace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Main extends ApplicationAdapter {
	private static OrthographicCamera camera;
	private Viewport viewport;
	public final static int VIEWPORT_WIDTH = 1440;
	private static float aspect_ratio;
	public static ShapeRenderer shapeRenderer;
	public static PolygonSpriteBatch polyBatch;
	private Array<Vector2> a;

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

		//Ersteugen der Zeichenobjekte
		shapeRenderer = new ShapeRenderer();
		polyBatch = new PolygonSpriteBatch();

		//Inizialisierung
		Player.init();
		Physics.init();
		ParticleManagement.init();

		a = new Array<Vector2>();

		for(int i = 0; i<ParticleManagement.movementComplexity; i++){
			a.add(new Vector2(MathUtils.random(0, VIEWPORT_WIDTH),(getViewportHeight()/(ParticleManagement.movementComplexity-1))*i));
		}

		float r = (float) Math.sqrt((double) 750);

		a.get(0).y -= r;
		a.get(a.size-1).y += r;

		ParticleManagement.spawners.add(new Spawner(750, a));
	}

	@Override
	public void render() {

		//Rendering
		Player.move();
        Physics.render();
		ParticleManagement.render();

		//Zeichnen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Zeichnen mit ShapeRenderer
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		Player.draw(shapeRenderer);

		//Zeichnen mit PolyBatch
		polyBatch.setProjectionMatrix(camera.combined);
		polyBatch.begin();

		ParticleManagement.draw();

		for(Vector2 v : a){
			shapeRenderer.rect(v.x-25, v.y-25, 50, 50);
		}

		polyBatch.end();
		shapeRenderer.end();
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