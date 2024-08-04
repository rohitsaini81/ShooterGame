package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SurfaceObjects {
    private Sprite Sand;
    private Sprite Ice;
    public static Rectangle BoxJammer1;
    public static Rectangle BoxJammer2;



//    objects
    Collision SandCollision;

    public SurfaceObjects() {
        Texture sandTexture = new Texture("sand.png");
        Texture iceTexture = new Texture("sand.png"); // Assuming there's a separate ice texture
        BoxJammer1 = new Rectangle();
        BoxJammer2 = new Rectangle();

        Sand = new Sprite(sandTexture);
        Ice = new Sprite(iceTexture);
        BoxJammer1.set(300,60,15,20);
        Sand.setPosition(300, 60);
        Sand.setSize(15, 20);



        Ice.setPosition(400, 60);
        BoxJammer2.set(400,60,15,20);
        Ice.setSize(15, 20);
//        SandCollision= new Collision(Sand);
    }


    public void renderSfObjects(SpriteBatch batch) {
        Sand.draw(batch);
        Ice.draw(batch);
    }


}
