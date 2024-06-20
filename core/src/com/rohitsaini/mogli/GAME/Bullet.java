package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    float BulletX, BulletY;
    Texture bulletTexture;
    static Sprite bullet;


    Bullet(){
        bulletTexture = new Texture("02.png");
        bullet = new Sprite(bulletTexture);
        System.out.println("BulletX:"+BulletX);
    }
    static void Bullet_fire(){
        Variables.batch.draw(bullet,100,Player.PlayerY+Player.PlayerHeight/2,10,10);
    }

}
