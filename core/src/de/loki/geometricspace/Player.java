package de.loki.geometricspace;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loki on 26.04.2017.
 */
public class Player {

    private static Vector2 position;
    private static int speed;
    private static int sizeX;
    private static int sizeY;

    public static void init() {
        position = new Vector2(Main.VIEWPORT_WIDTH/2, Main.getViewportHeight()/2);
        speed = 1000;
        sizeX = 50;
        sizeY = 100;
    }

    public static void move() {
        if(Gdx.input.isKeyPressed(Input.Keys.W)) position.y += speed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.A)) position.x -= speed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S)) position.y -= speed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D)) position.x += speed * Gdx.graphics.getDeltaTime();
    }

    public static void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(position.x - sizeX/2, position.y - sizeY/2, sizeX, sizeY);
    }

    public static Vector2 getPosition(){
        return position;
    }

}
