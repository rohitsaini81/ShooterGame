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
    private Boolean playerState;


    Bullet(float bulletX){
        bulletTexture = new Texture("02.png");
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("Raw-Shoot-Variation01.mp3"));
        bulletSound.play();
        bullet = new Sprite(bulletTexture);
        
        if(Player.Player_State==1) {
        	this.X=bulletX;
        	this.X2=Gdx.graphics.getWidth();
        	this.playerState=true;}
        else {
        	this.X=(bulletX-35);
        	this.X2=Gdx.graphics.getWidth();
        	this.playerState=false;}
        
        this.BulletY=Player.PlayerY+Player.PlayerHeight-15;
        System.out.println("BulletX:"+X);
    }
    public void update(){
    	if(this.playerState) {
    		this.X+=Variables.SPEED*Gdx.graphics.getDeltaTime()*10;	
    	}else {this.X-=Variables.SPEED*Gdx.graphics.getDeltaTime()*10;}
        
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
