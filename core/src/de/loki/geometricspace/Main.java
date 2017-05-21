package de.loki.geometricspace;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
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
	private ShapeRenderer shapeRenderer;
	private TextureRegion textureRegion;
	private EarClippingTriangulator triangulator;
	private PolygonSpriteBatch polyBatch;
	private Pixmap pixmap;
	public static Array<Particle> particles;
	public static Array<Particle> attractors;

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


		pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLUE);
		pixmap.fill();


		textureRegion = new TextureRegion(new Texture(pixmap));

		triangulator = new EarClippingTriangulator();

		particles = new Array<Particle>();
		attractors = new Array<Particle>();

        Physics.init();

		for(int i = 0; i<3; i++){
			particles.add(new Particle(new Vector2(MathUtils.random(0, VIEWPORT_WIDTH), MathUtils.random(0, getViewportHeight())), MathUtils.random(500, 1000), ParticleType.getRandomParticleType()));
		}

		for(int i = 0; i<2; i++){
			attractors.add(new Particle(new Vector2(MathUtils.random(0, VIEWPORT_WIDTH), MathUtils.random(0, getViewportHeight())), 3000, ParticleType.Circle));
		}

	}

	@Override
	public void render() {

		Player.move();
        Physics.render();

        for(Particle p : particles){
			p.render();
		}

		for(Particle p : attractors){
			p.render();
		}

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		Player.draw(shapeRenderer);
		polyBatch.setProjectionMatrix(camera.combined);
		polyBatch.begin();

		shapeRenderer.setColor(Color.BLUE);
		for(Particle p : particles){
			p.draw(shapeRenderer, polyBatch, triangulator, textureRegion);
		}

		shapeRenderer.setColor(Color.RED);
		for(Particle p : attractors){
			p.draw(shapeRenderer, polyBatch, triangulator, textureRegion);
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