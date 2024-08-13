package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.Bullet;
import com.rohitsaini.mogli.GAME.player.Player;
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
    private float facePx;
    private boolean rightface;
    public int health;
    public float spawn_location;
    Hitmans (){

        spawn_location=500;
        rightface=true;
        facePx=-1;
        enemyrecta=new Rectangle();
        index=0;
        enmey_X=spawn_location-5;
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
//        System.out.println(rightface);
        timer--;
        if (timer<=-10){timer=Math.abs(timer);}
        if (enemyrecta.overlaps(Shapes.player)&& Player.PLAYER_HEALTH>0){
            bullets.add(new Bullet(Math.abs(enmey_X),enmey_Y+15,true));
        }


        enmey_X=(enmey_X-(Variables.SPEED* Gdx.graphics.getDeltaTime())*facePx);
        System.out.println(facePx);
        if (enmey_X>spawn_location){
            rightface=true;
            facePx=1;
            System.out.println("right >>>");
            enmey_X=Math.abs(enmey_X);
        }
        if (enmey_X<(spawn_location-100)){
            enmey_X=Math.abs(enmey_X);
            System.out.println("left <<<");
            rightface=false;
            facePx=-1;
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







    public static class Zombie{
        public static Rectangle zombieRect;
        public int health;
        public Animation<TextureRegion> zombieAnimation;
        public TextureRegion[] zombieTextureRegions;


        float zombieX=100;
        float zombieY=60;
        float spwan_location=500;



        public Zombie(float SpawnX){
            Texture Textture = new Texture("etc/others/zombie_spritesheet.png");
            zombieRect = new Rectangle(0,0,0,0);
            health = 20;
            spwan_location= SpawnX;

            TextureRegion[][] temp= TextureRegion.split(Textture,32,32);
            int Ti=8;
            int index=0;
            this.zombieTextureRegions = new TextureRegion[Ti];
            for (int j = 0; j < Ti; j++) {
                this.zombieTextureRegions[index++]=temp[1][j];
            }
            this.zombieAnimation=new Animation<>(0.2f, this.zombieTextureRegions);
        }

        public void render(){
                Variables.batch.draw(zombieAnimation.getKeyFrame(Variables.stateTime,true),getX(),60,32, 32);
                Aisystem();
        }




        public float getX(){
            return this.zombieX;
        }
        public void setX(float X){
            this.zombieX=X;
        }
        int facePx=-1;
        private void Aisystem(){
            if (health>0){
                setX(getX()-(3* Gdx.graphics.getDeltaTime())*facePx);
                zombieRect.set(this.getX(),60,20,32);

                if (getX()>spwan_location){
                    facePx=1;
                }
                if (getX()<spwan_location-100){
                    facePx=-1;
                }
            }
        }
    }
}

