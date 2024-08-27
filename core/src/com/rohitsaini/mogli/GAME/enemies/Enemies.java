package com.rohitsaini.mogli.GAME.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rohitsaini.mogli.GAME.player.Player;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.Variables;

import java.util.ArrayList;

import static com.rohitsaini.mogli.GAME.DrawShapes.Shapes.all_shapes;
import static com.rohitsaini.mogli.GAME.DrawShapes.Shapes.shapeRenderer;
import static com.rohitsaini.mogli.GAME.myKeyWords.RandomNumber;
import static com.rohitsaini.mogli.GAME.myKeyWords.sout;

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
    Texture airStands;
    public  TextureRegion[] snakeRegion;
    TextureRegion[][] tempsnake;
    Animation<TextureRegion> EnemiesAnimation;
    public Rectangle EnemyJammer1;
    public boolean isDead;
    public int enemyHealth;
    public Hitmans Henenmy;
//    public Hitmans.Zombie zombie;
    public float getX(){
        return enmey_X;
    }
    public float getY(){
        return enmey_Y;
    }
    public void setX(float v){
        enmey_X=v;
    }

    public ArrayList<Hitmans.Zombie> zombiesarray;


    public Enemies(){
        enemyHealth=20;
        ai = new AI();
        EnemyJammer1 = new Rectangle();
        isDead=false;

        enmey_Y=70;
//        airStands= new Texture("");
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
        Henenmy= new Hitmans();
        zombiesarray = new ArrayList<>();
//        zombiesarray.add(new Hitmans.Zombie(100));
//        zombiesarray.add(new Hitmans.Zombie(240));

    }


    public void thisenemy(){
        if (enemyHealth>0){
            this.AI_render();
        }else {enemyHealth=0;}
        if(enemyHealth>0 && Shapes.player.overlaps(EnemyJammer1)) {
            if(Player.PlayerY<(EnemyJammer1.getY()+15)){
                Player.PLAYER_HEALTH--;

                this.collision_Damage.play();
                Player.playerDead.play();


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

    public void RenderEnemy(){

        thisenemy();
        Henenmy.render();
        if (zombiesarray.size()<10){
            int zombiespawnrange= (int) RandomNumber(1000,2105);
//            int zombiespawnrange=0;
//            zombiespawnrange=zombiespawnrange>2105?zombiespawnrange=2100:zombiespawnrange;
            Hitmans.Zombie Z =new Hitmans.Zombie((float) zombiespawnrange );
//            System.out.println("all shapes size before: "+all_shapes.size());
//            all_shapes.add(Z.zombieRect);

//            System.out.println("all shapes size after adding: "+all_shapes.size()+" rect id"+Z.rect_id);

//            System.out.println("zombies array size before :"+zombiesarray.size());
            zombiesarray.add(Z);
            Z.rect_id=zombiesarray.size()-1;
            Z.id=zombiesarray.size()-1;
//            System.out.println("zombies array size after adding : "+zombiesarray.size);
//            sout("new spawn zombies id,rect id"+Z.id+","+Z.rect_id);

        }
        for (Hitmans.Zombie z:zombiesarray){
                z.render();
                if (z.zombieRect.overlaps(Shapes.player) && z.health > 0) {
//                play blood animatioin
//                    .........
//                    __________
                    if (Player.PLAYER_HEALTH > 0) {
                        Player.PLAYER_HEALTH -= .5f;
                    }
                }


        }



    }

    private void AI_render(){
        AIj+= Variables.SPEED * Variables.deltaTime;
        if(AIj>=50){AIj=-50;}
        enmey_X=Math.abs(AIj);
//        setX(Math.abs(AIj));
    }

}