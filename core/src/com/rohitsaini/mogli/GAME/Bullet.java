package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Bullet {
    public  boolean remove = false;
    private float X;
    private float X2;
    private final float BulletY;
    Texture bulletTexture;
    Sprite bullet;
    Sound bulletSound;


    Bullet(float bulletX){
        bulletTexture = new Texture("02.png");
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("Raw-Shoot-Variation01.mp3"));
        bulletSound.play();
        bullet = new Sprite(bulletTexture);
        this.X=bulletX;
        this.X2=Gdx.graphics.getWidth();
        this.BulletY=Player.PlayerY+Player.PlayerHeight-15;
        System.out.println("BulletX:"+X);
    }
    public void update(){
        this.X+=Variables.SPEED*Gdx.graphics.getDeltaTime()*10;
        if (this.X>Variables.SurfaceX2+10){
            remove=true;
            bulletSound.dispose();
        }

    }
    public void Bullet_fire (SpriteBatch batch){
        System.out.println("Bullet fired AND X: "+this.X+" Y: "+this.BulletY);
        batch.draw(this.bullet,this.X,this.BulletY,10,10);
        }
}
