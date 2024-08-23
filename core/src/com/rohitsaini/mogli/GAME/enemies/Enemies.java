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

    public Array<Hitmans.Zombie> zombiesarray;


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
        zombiesarray = new Array<>();
        Henenmy= new Hitmans();
        zombiesarray.add(new Hitmans.Zombie(100));
        zombiesarray.add(new Hitmans.Zombie(240));
        zombiesarray.add(new Hitmans.Zombie(400));
//        zombie= new Hitmans.Zombie(500);
//        for (int j = 1; j < 5; j++) {
//            Hitmans.Zombie Z= new Hitmans.Zombie(120*i);
//            zombiesarray.add(Z);
//        }
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
        for (Hitmans.Zombie z:zombiesarray){
            if (z.health>0) {
                z.render();
                if (z.zombieRect.overlaps(Shapes.player) && z.health > 0) {
//                play blood animatioin
                    if (Player.PLAYER_HEALTH > 0) {
                        Player.PLAYER_HEALTH -= .5f;
                    }
                }
            }
            else {
                if (z.health<=0 && !z.dead){
                    all_shapes.remove(z.id);
                }
                z.dead=true;
            }
        }
        if (zombiesarray.size<10){
            Hitmans.Zombie Z =new Hitmans.Zombie((float) (RandomNumber(5)+(1000*RandomNumber(1))));
            all_shapes.add(Z.zombieRect);
            zombiesarray.add(Z);
            Z.id=zombiesarray.size-1;
        }


    }

    private void AI_render(){
        AIj+= Variables.SPEED * Variables.deltaTime;
        if(AIj>=50){AIj=-50;}
        enmey_X=Math.abs(AIj);
//        setX(Math.abs(AIj));
    }

}