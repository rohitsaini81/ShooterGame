package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SurfaceObjects {
    private Sprite Sand;
    private Sprite Ice;


//    objectss
    Collision SandCollision;

    public SurfaceObjects() {
        Texture sandTexture = new Texture("sand.png");
        Texture iceTexture = new Texture("sand.png"); // Assuming there's a separate ice texture

        Sand = new Sprite(sandTexture);
        Ice = new Sprite(iceTexture);

        Sand.setPosition(300, 60);
        Sand.setSize(15, 20);

        Ice.setPosition(400, 60);
        Ice.setSize(15, 20);
        SandCollision= new Collision(Sand);
    }


    public void renderSfObjects(SpriteBatch batch) {
        Sand.draw(batch);
        Ice.draw(batch);
        SandCollision.checkCollision();
    }


}
