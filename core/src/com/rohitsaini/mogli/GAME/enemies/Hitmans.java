package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.tommyettinger.textra.TypingLabel;
import com.rohitsaini.mogli.GAME.Bullet;
import com.rohitsaini.mogli.GAME.player.Player;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.Variables;

import java.util.ArrayList;

import static com.rohitsaini.mogli.GAME.myKeyWords.RandomNumber;
import static com.rohitsaini.mogli.GAME.myKeyWords.sout;

public class Hitmans {
    public Rectangle enemyrecta;
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
        TextureRegion[][] temp;
        TextureRegion[] enemyTextureRegions;
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
//        System.out.println(facePx);
        if (enmey_X>spawn_location){
            rightface=true;
            facePx=1;
//            System.out.println("right >>>");
            enmey_X=Math.abs(enmey_X);
        }
        if (enmey_X<(spawn_location-100)){
            enmey_X=Math.abs(enmey_X);
//            System.out.println("left <<<");
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
        public Rectangle zombieRect;
        public int health;
        public int id;
        public int rect_id;
        public boolean dead;
        public Animation<TextureRegion> zombieAnimation;
        public Animation<TextureRegion> rzombieAnimation;
        public TextureRegion[] zombieTextureRegions;


        float zombieX=100;
        float zombieY=60;
        float spwan_location;
        int facePx;
        TypingLabel zombiesXY;
        BitmapFont text;

        Music zombieEating;
        Music zombieSound;
        StringBuilder healthString;




        public Zombie(float SpawnX){
            dead=false;
            final String path="etc/others/";
          zombieSound=Gdx.audio.newMusic(Gdx.files.internal(path+"zombieSounds/zombie-moans-29924.mp3"));
//          zombieSound;
          text= new BitmapFont();
          text.getData().setScale(0.5f);
            zombiesXY= new TypingLabel();
            if(RandomNumber(1)==1){
                facePx=1;
            }else {facePx=-1;}
            Texture Textture = new Texture("etc/others/zombie_spritesheet.png");
            Texture Textture_r = new Texture("etc/others/r-zombie_spritesheet.png");
            spwan_location= SpawnX;
            zombieRect = new Rectangle(spwan_location,60,20,32);
            health = 6;
            healthString= new StringBuilder();
//            for (int i=0;i<=health;i++){
//                healthString.append("-");
//            }


            TextureRegion[][] temp= TextureRegion.split(Textture,32,32);
            int Ti=8;
            int index=0;
            this.zombieTextureRegions = new TextureRegion[Ti];
            for (int j = 0; j < Ti; j++) {
                this.zombieTextureRegions[index++]=temp[1][j];
            }
            this.zombieAnimation=new Animation<>(0.2f, this.zombieTextureRegions);

            temp= TextureRegion.split(Textture_r,32,32);
            Ti=8;
            index=0;
            TextureRegion[] rzombieTextureRegions;
            rzombieTextureRegions = new TextureRegion[Ti];
            for (int j = 0; j < Ti; j++) {
                rzombieTextureRegions[index++]=temp[1][j];
            }
            this.rzombieAnimation=new Animation<>(0.2f, rzombieTextureRegions);


//            System.out.println("spl:"+spwan_location);

        }

        public void render(){
            Aisystem();
//            zombiesXY.setDefaultToken("zombie XY: "+zombieRect.getX()+" "+60);
            healthString.delete(0,healthString.length());
            for (int i=0;i<=health;i++){
                healthString.append("-");
            }
//            System.out.println();
            text.draw(Variables.batch,"|"+healthString+"|",zombieRect.getX(),zombieRect.getY()+60,5,5,false);
            if (facePx==-1){
                Variables.batch.draw(zombieAnimation.getKeyFrame(Variables.stateTime,true),zombieRect.getX(),60,32, 32);
            }else {
                Variables.batch.draw(rzombieAnimation.getKeyFrame(Variables.stateTime, true), zombieRect.getX(), 60, 32, 32);
            }
        }


    void defalutbehave(){
    if (zombieRect.getX()>spwan_location){
//      Going to 'left' side
        facePx=1;
    }
    else if (zombieRect.getX()<0){
//      Going to right side
        facePx=-1;
    }
}

        public void setX(float X){
            zombieRect.setX(X);
        }

        private void Aisystem(){
            if (health>0){
                float a=zombieRect.getX();
                int random=(int) RandomNumber(10);
//                System.out.println(RandomNumber(4,8));
                float b=(3* Gdx.graphics.getDeltaTime()*random)*facePx;
                zombieRect.set(a-b,60,20,32);


                if (Shapes.player.overlaps(zombieRect)){
                    facePx=RandomNumber(-5,5);
//                    System.out.println(facePx);

                                                        if (!zombieSound.isPlaying()){zombieSound.play();}
                }else {
                    defalutbehave();
                }
            }
        }
    }
}

