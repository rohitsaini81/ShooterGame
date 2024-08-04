package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.Bullet;
import com.rohitsaini.mogli.GAME.Player;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.Variables;

import java.util.ArrayList;

public class Hitmans {
    public Rectangle enemyrecta;
    TextureRegion[][] temp;
    TextureRegion[] enemyTextureRegions;
    Animation<TextureRegion> enemyAnimation;
    private ArrayList<Bullet> bullets;

    int index;
    private float enmey_X;
    private float enmey_Y;
    private boolean rightface;
    public int health;
    Hitmans (){
        rightface=true;
        enemyrecta=new Rectangle();
        index=0;
        enmey_X=Gdx.graphics.getWidth();
        enmey_Y=60;
        health=20;
        bullets = new ArrayList<>();
        Texture T=new Texture("characterSprites/Robots.png");
        temp = TextureRegion.split(T,24,32);
        enemyTextureRegions = new TextureRegion[6];
        for (int j = 0; j <6; j++) {
            enemyTextureRegions[index++]=temp[0][j];
        }
        enemyAnimation = new Animation<>(.08f, enemyTextureRegions);
    }
    public void render (){
        if (health>0){
            Ai();
        }else {health=0;}
        enemyrecta.set(Math.abs(enmey_X),enmey_Y,40,25);
        Variables.batch.draw(enemyAnimation.getKeyFrame(Variables.stateTime,true),Math.abs(enmey_X),enmey_Y,40, 40);

    }
    private float timer;
    private void  Ai (){
        float distance=Math.abs(Shapes.player.getX()-(Math.abs(enmey_X)));
        System.out.println(rightface);
        timer--;
        if (timer<=-10){timer=Math.abs(timer);}
        if (enemyrecta.overlaps(Shapes.player)&& Player.PLAYER_HEALTH>0){
            bullets.add(new Bullet(Math.abs(enmey_X),enmey_Y+15,true));
        }



        enmey_X-=Variables.SPEED* Gdx.graphics.getDeltaTime();
        if (enmey_X<-300){
            rightface=false;
            enmey_X=Math.abs(enmey_X);
        }
        if (Math.abs(enmey_X)==0){
            rightface=true;
        }

        if (Math.abs(timer)/2==0 && distance<100 && Player.PLAYER_HEALTH>0){
            if (enmey_X<Shapes.player.getX()){
                bullets.add(new Bullet(Math.abs(enmey_X),enmey_Y+15,true));

            }else {
                bullets.add(new Bullet(Math.abs(enmey_X),enmey_Y+15,false));

            }
        }

        ArrayList<Bullet>bulletstoRemove = new ArrayList<>();
        for (Bullet bullet:bullets){
            bullet.update();
            if (Player.PLAYER_HEALTH>0&&bullet.bulletRect.overlaps(Shapes.player)){
                bullet.bolletWall = Shapes.player.getX();
                Player.PLAYER_HEALTH-=2;
            }
            if (bullet.remove){
                bulletstoRemove.add(bullet);}
        }
        bullets.removeAll(bulletstoRemove);
        for (Bullet bullet:bullets){
            bullet.Bullet_fire(Variables.batch);
        }
    }
}
