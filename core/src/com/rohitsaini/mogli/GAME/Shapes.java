package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Shapes {
    static ShapeRenderer shapeRenderer;
    Shapes (){
        shapeRenderer= new ShapeRenderer();

    }
    public void shaperender(){
        shapeRenderer.setProjectionMatrix(Variables.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(Player.PlayerX,Player.PlayerY,30,40);
        shapeRenderer.rect(300,60,15,20);

        shapeRenderer.line(Collision.objX,60f,Collision.objX2,80f);
        shapeRenderer.line(Collision.objX2,60f,Collision.objX,80f);
        shapeRenderer.end();
    }

}
