package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.MainGame;
import com.rohitsaini.mogli.GAME.Player;
import com.rohitsaini.mogli.GAME.Shapes;
import com.rohitsaini.mogli.GAME.Variables;

public class Enemies{

    int index=0;
    private float i=0;
    private float AIj=0;
    protected float enmey_X;
    protected float enmey_Y;

    public Sound sound;
    public Sound collision_Damage;
    public Sound health_down;
    Sprite Snake;
    public AI ai;
    Texture snake;
    public  TextureRegion[] snakeRegion;
    TextureRegion[][] tempsnake;
    Animation<TextureRegion> EnemiesAnimation;
    public Rectangle EnemyJammer1;
    public boolean isDead;
    public int enemyHealth;
    public float getX(){
        return enmey_X;
    }
    public float getY(){
        return enmey_Y;
    }
    public void setX(float v){
        enmey_X=v;
    }


    public Enemies(){
        enemyHealth=20;
        ai = new AI();
        EnemyJammer1 = new Rectangle();
        isDead=false;

        enmey_Y=70;
        this.sound= Gdx.audio.newSound(Gdx.files.internal("playerSound/Ibatman.mp3"));
        this.collision_Damage=Gdx.audio.newSound(Gdx.files.internal("playerSound/jump-climb-or-damage-sound-f-95942.mp3"));
//        this.snake = new Texture("batman.png");
        this.snake = new Texture("characterSprites/w4GckD.png");
        this.Snake = new Sprite(snake);
        this.Snake.setX(200);
        this.Snake.setY(30);
        this.Snake.setSize(25,30);
        this.tempsnake= TextureRegion.split(this.snake,24,32);
        int Ti=2;
        this.snakeRegion = new TextureRegion[Ti];
        for (int j = 0; j < Ti; j++) {
            this.snakeRegion[this.index++]=this.tempsnake[24][j];
        }
        this.EnemiesAnimation=new Animation<>(0.2f, this.snakeRegion);
        this.sound.play();

    }

    public void RenderEnemy(){
        if (enemyHealth>0){
            this.AI_render();
    }else {enemyHealth=0;}
        if(enemyHealth>0 && Shapes.player.overlaps(EnemyJammer1)) {
            if(Player.PlayerY<(EnemyJammer1.getY()+15)){
                Player.PLAYER_HEALTH--;
                this.collision_Damage.play();
                Player.setX(Player.getX()+i);
                Shapes.player.set(Player.getX(), Player.PlayerY, 15, 40);
                i+=Variables.SPEED*Variables.deltaTime;
//                this.collision_Damage.dispose();

            }
        }
        Shapes.check_collision_surface(EnemyJammer1);
        EnemyJammer1.set(enmey_X,enmey_Y,25,30);
//        System.out.println("enemyX"+enmey_X);
        Variables.batch.draw(this.EnemiesAnimation.getKeyFrame(Variables.stateTime,true),enmey_X,enmey_Y,25,30);

    }

    private void AI_render(){
        AIj+= Variables.SPEED * Variables.deltaTime;
        if(AIj>=50){AIj=-50;}
        enmey_X=Math.abs(AIj);
//        setX(Math.abs(AIj));
    }

}