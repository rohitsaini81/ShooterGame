package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Enemies{

    int index=0;

    static Sound sound;
    Sprite Snake;
    Texture snake;
    public  TextureRegion[] snakeRegion;
    TextureRegion[][] tempsnake;
    Animation<TextureRegion> EnemiesAnimation;


    Enemies(){
        this.sound= Gdx.audio.newSound(Gdx.files.internal("Ibatman.mp3"));
//        this.snake = new Texture("chainsawon38x2.png");
        this.snake = new Texture("batman.png");


        this.Snake = new Sprite(snake);
        this.Snake.setX(200);
        this.Snake.setY(30);
        this.Snake.setSize(10,20);
        this.tempsnake= TextureRegion.split(this.snake,100,105);
        int Ti=2;
        this.snakeRegion = new TextureRegion[Ti];
        for (int j = 0; j < Ti; j++) {
            this.snakeRegion[this.index++]=this.tempsnake[0][j];
        }
        this.EnemiesAnimation=new Animation<>(0.2f, this.snakeRegion);
        this.sound.play();

    }
    public void RenderEnemy(){

        Variables.batch.draw((TextureRegion) this.EnemiesAnimation.getKeyFrame(Variables.stateTime,true),Player.PlayerX+100,70,38,150);

    }
}