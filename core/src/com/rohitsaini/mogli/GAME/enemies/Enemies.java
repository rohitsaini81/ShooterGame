package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.Variables;

public class Enemies{

    int index=0;
    protected static float enmey_X;
    protected static float enmey_Y;

    public static Sound sound;
    Sprite Snake;
    public static AI ai;
    Texture snake;
    public  TextureRegion[] snakeRegion;
    TextureRegion[][] tempsnake;
    Animation<TextureRegion> EnemiesAnimation;
    public static Rectangle BoxJammer3;
    public static float getX(){
        return enmey_X;
    }
    public static float getY(){
        return enmey_Y;
    }
    public static void setX(float v){
        enmey_X=v;
    }


    public Enemies(){
        ai = new AI();
        BoxJammer3 = new Rectangle();

        enmey_Y=70;
        this.sound= Gdx.audio.newSound(Gdx.files.internal("Ibatman.mp3"));
//        this.snake = new Texture("batman.png");
        this.snake = new Texture("w4GckD.png");
        this.Snake = new Sprite(snake);
        this.Snake.setX(200);
        this.Snake.setY(30);
        this.Snake.setSize(25,30);
        this.tempsnake= TextureRegion.split(this.snake,24,32);
        int Ti=8;
        this.snakeRegion = new TextureRegion[Ti];
        for (int j = 0; j < Ti; j++) {
            this.snakeRegion[this.index++]=this.tempsnake[24][j];
        }
        this.EnemiesAnimation=new Animation<>(0.2f, this.snakeRegion);
        this.sound.play();

    }
    public void RenderEnemy(){
        ai.AI_render();
        BoxJammer3.set(enmey_X,enmey_Y,25,30);
        Variables.batch.draw((TextureRegion) this.EnemiesAnimation.getKeyFrame(Variables.stateTime,true),enmey_X,enmey_Y,25,30);

    }

}