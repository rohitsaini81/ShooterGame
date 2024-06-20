package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Enemies{
    private int x;
    private int y;
    int index=0;


    Sprite Snake;
    Texture snake;
    public  TextureRegion[] snakeRegion;
    TextureRegion[][] tempsnake;
    Animation<TextureRegion> EnemiesAnimation;

    Enemies(){
        this.snake = new Texture("chainsawon38x2.png");
        this.Snake = new Sprite(snake);
        this.Snake.setX(460);
        this.Snake.setY(38);
        this.Snake.setSize(10,20);
        this.tempsnake= TextureRegion.split(this.snake,38,38);
        this.snakeRegion = new TextureRegion[8];
        for (int j = 0; j < 8; j++) {
            this.snakeRegion[this.index++]=this.tempsnake[0][j];
        }
        this.EnemiesAnimation=new Animation<>(0.2f, this.snakeRegion);

    }
    public void RenderEnemy(){

        Variables.batch.draw((TextureRegion) this.EnemiesAnimation.getKeyFrame(Variables.stateTime,true),460,38,10,20);

        SurfaceObjects.PlayerCollision(Snake,false);
    }

}