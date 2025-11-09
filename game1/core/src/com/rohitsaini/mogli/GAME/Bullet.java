package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.player.Player;


public class Bullet {
    public  boolean remove = false;
    private float X;
    private float X2;
    private final float BulletY;
    Texture bulletTexture;
    Sprite bullet;
    Sound bulletSound;
    public static Rectangle bulletRect;

    private Boolean playerState;
    public float bolletWall;



    public Bullet(float bulletX){
        this.bolletWall=Player.getX()+Gdx.graphics.getWidth();
        bulletTexture = new Texture("etc/02.png");
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("playerSound/Raw-Shoot-Variation01.mp3"));
        bulletSound.play();
        bullet = new Sprite(bulletTexture);

        if(Player.PlayerDirectionRight) {
            this.X=bulletX;
            this.X2=Gdx.graphics.getWidth();
            this.playerState=true;}
        else {
            this.X=(bulletX-35);
            this.X2=Gdx.graphics.getWidth();
            this.playerState=false;
            bolletWall=(-bolletWall);
        }

        this.BulletY=(Player.PlayerY+Player.PlayerHeight/4)-1.5f;
        bulletRect = new Rectangle(bulletX,this.BulletY,10,10);

//        System.out.println("BulletX:"+X);
    }
    public Bullet(float bulletX,float bulletY,boolean right){
        this.bolletWall=Player.getX()+Gdx.graphics.getWidth();
        bulletTexture = new Texture("etc/02.png");
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("playerSound/Raw-Shoot-Variation01.mp3"));
        bulletSound.play();
        this.BulletY=bulletY;
        bullet = new Sprite(bulletTexture);
        
        if(right) {
        	this.X=bulletX;
        	this.X2=Gdx.graphics.getWidth();
        	this.playerState=true;}
        else {
        	this.X=(bulletX-35);
        	this.X2=Gdx.graphics.getWidth();
        	this.playerState=false;
            bolletWall=(-bolletWall);
        }
        
        bulletRect = new Rectangle(bulletX,this.BulletY,10,10);

//        System.out.println("BulletX:"+X);
    }
    public void update(){
        bulletRect.setX(this.X);
        bullet.setY(this.BulletY);
    	if(this.playerState) {
            this.X+=Variables.SPEED*Gdx.graphics.getDeltaTime()*10;
            if (this.X>=bolletWall){
                remove=true;
                bulletSound.dispose();
            }
    	}else {this.X-=Variables.SPEED*Gdx.graphics.getDeltaTime()*10;}
        
        if (!this.playerState && this.X<=bolletWall){
            remove=true;
            bulletSound.dispose();
        }

    }
    public void Bullet_fire (SpriteBatch batch){
//        System.out.println("Bullet fired AND X: "+this.X+" Y: "+this.BulletY);
        batch.draw(this.bullet,this.X,this.BulletY,10,10);
        }
}
