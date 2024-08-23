package com.rohitsaini.mogli.GAME.Screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rohitsaini.mogli.GAME.Bullet;
import com.rohitsaini.mogli.GAME.Controler.Controlls;
import com.rohitsaini.mogli.GAME.Levels.Level1;
import com.rohitsaini.mogli.GAME.enemies.Hitmans;
import com.rohitsaini.mogli.GAME.player.Player;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.SurfaceObjects;
import com.rohitsaini.mogli.GAME.Variables;
import com.rohitsaini.mogli.GAME.enemies.Enemies;


import java.awt.*;
import java.util.ArrayList;

//import static com.rohitsaini.mogli.GAME.player.Player.textureenemey;
import static com.rohitsaini.mogli.GAME.DrawShapes.Shapes.airStands;
import static com.rohitsaini.mogli.GAME.DrawShapes.Shapes.all_shapes;
import static com.rohitsaini.mogli.GAME.myKeyWords.*;


public class MainGame implements Screen {
    Game game;
    public static Level1 level1;
    static float W0 = 0;
    static float W2 = Gdx.graphics.getWidth();
    static float H = Gdx.graphics.getHeight();
    static float S=W2/2;
    static float loopValue=0;
    static  int bg_times=5;
    public static ArrayList<Bullet> bullets;
    static ShapeRenderer shapeRenderer;
    static Shapes shapes;


    public static Sound themesound;
    static Audio audio;
    static Music music;

    public static Enemies enemy;
    Player player;
    SurfaceObjects surfaceObjects;
    statusbar statusbar;

    public MainGame(Game game) {
        statusbar = new statusbar();
        this.game = game;
        level1= new Level1();
        shapeRenderer= new ShapeRenderer();
        themesound = Gdx.audio.newSound(Gdx.files.internal("playerSound/warning-mystrious.mp3"));
        Variables.camera = new OrthographicCamera(W2/2,H/2);
        Variables.camera.setToOrtho(false, W2/2 ,H/2);

//        Class Objects = new;
        enemy = new Enemies();
        bullets= new ArrayList<>();
        states = new ArrayList<>();
        shapes = new Shapes();



        surfaceObjects = new SurfaceObjects();
        Variables.batch = new SpriteBatch();
        player= new Player();
        Variables.backgroundT = new Texture("etc/Background.png");
        Variables.sprite=new Sprite(Variables.backgroundT);
        Variables.Font = new BitmapFont();




        all_shapes.add(SurfaceObjects.BoxJammer1);
        all_shapes.add(SurfaceObjects.BoxJammer2);
        all_shapes.add(airStands);
        all_shapes.add(Shapes.surfaceRect);
        all_shapes.add(level1.airStands1);
        all_shapes.add(level1.airStands2);
        all_shapes.add(level1.airStands3);
        all_shapes.add(level1.airStands4);
//        all_shapes.add(Hitmans.Zombie.zombieRect);
        for (Hitmans.Zombie z:enemy.zombiesarray){
            all_shapes.add(z.zombieRect);
            z.id=all_shapes.size()-1;

        }
//        System.out.println("Random number : "+RandomNumber(1));



    }

    @Override
    public void show() {
        Player.PLAYER_HEALTH=20;
        Variables.SPEED=50;
        Variables.deltaTime=Gdx.graphics.getDeltaTime();
        Player.PlayerIsIdle=true;
        Variables.isCollision=false;
        Player.PlayerDirectionRight=true;
        Player.PlayerY=60;
        Player.PlayerX=70;
        Variables.SurfaceX=60;
        Variables.SurfaceX2=1866;
        Variables.SurfaceY=60;
        Variables.angle=1;
        Variables.Font.setColor(Color.PINK);
        states.add("X:" +(int)Player.PlayerX+"Y:"+(int)Player.PlayerY);
        states.add("X:"+Gdx.input.getX()+" ,Health:"+Player.PLAYER_HEALTH);
//        themesound.play();
        themesound.loop();

    }
//  <----------- Render Method -------------->

    public static float jumptime=0;
    @Override
    public void render(float delta) {
        if (jumptime>=0){
            jumptime-=Variables.SPEED*delta;
        }
//        System.out.println(Math.abs(jumptime));
        Variables.deltaTime=delta;
        Player.Player_prevX = Player.PlayerX;
        Controlls.render(delta);

        loopValue+=1;
        if(loopValue>=230){loopValue=0;}
        ScreenUtils.clear(0, 0, 0, 1);
        Variables.stateTime+=delta;
//        Variables.camera.translate(0.1f,0.1f);

        Variables.camera.update();
        Variables.batch.setProjectionMatrix(Variables.camera.combined);
        Variables.batch.begin();

        for (int i = 0; i < bg_times; i++) {
            Variables.batch.draw(Variables.sprite,W0+i*415,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        }


//        statusbar.stage.act();
//        statusbar.stage.draw();
//         Render --------->   :

        surfaceObjects.renderSfObjects(Variables.batch);
        enemy.RenderEnemy();
        Player.renderPlayer();


//        Variables.batch.draw(textureenemey,Player.PlayerX,my_Y,40,60);








//        Bullet Functionality HERE
//        <Update Bullets>
        ArrayList<Bullet>bulletstoRemove = new ArrayList<>();
        for (Bullet bullet:bullets){
            bullet.update();
            for (int i = 0; i <enemy.zombiesarray.size-1;  i++) {
                if (enemy.zombiesarray.get(i).zombieRect.overlaps(bullet.bulletRect) && enemy.zombiesarray.get(i).health>0){
                enemy.zombiesarray.get(i).health--;
                bullet.remove=true;
                }
            }
            if (enemy.enemyHealth>0&&bullet.bulletRect.overlaps(enemy.EnemyJammer1)){
                bullet.bolletWall = enemy.getX();
//                System.out.println("Enemy got injured");
                enemy.enemyHealth-=2;
            }
            if (enemy.Henenmy.health>0&&bullet.bulletRect.overlaps(enemy.Henenmy.enemyrecta)){
                bullet.bolletWall=enemy.Henenmy.enemyrecta.getX();
                enemy.Henenmy.health--;
            }
            if (bullet.remove){
                bulletstoRemove.add(bullet);}
        }
        bullets.removeAll(bulletstoRemove);
        for (Bullet bullet:bullets){
            bullet.Bullet_fire(Variables.batch);
        }
//        for (int i = 0; i < all_shapes.size(); i++) {
//
//        }



//        JUMP FUNCTIONALITY HERE

        if (Controlls.JUMP){
            Player.Player_prevY = Player.PlayerY;
            Player.PlayerY+=Variables.SPEED*delta+0.5f;
        }
        if (jumptime<=0){
            Player.Player_prevY = Player.PlayerY;
            Controlls.JUMP = false;
        }

        if (!Controlls.JUMP){
            Player.Player_prevY = Player.PlayerY;
            if(Shapes.check_collision_surface()) {
                Player.PlayerY=Player.Player_prevY;
            	if(Player.PlayerDirectionRight) {
            		Player.Player_State=2;
            	}else {
            		Player.Player_State=-2;
            	}
                Variables.SurfaceY=(Player.PlayerY+50);
            	Controlls.Landed = true;
            }else if (Player.PlayerY>=60){

            Player.PlayerY-= Variables.SPEED*delta;// -100+surface
            }
        }
        if (Player.PlayerY>=Variables.SurfaceY-5&&Player.PlayerY<=Variables.SurfaceY){
            Controlls.Landed = true;
        }
        
       
        Variables.batch.end();
        Variables.batch.begin();
        statusbar.render();
        Variables.batch.end();
        shapes.shaperender();
        level1.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Variables.batch.dispose();
//        Variables.backgroundT.dispose();
//        Variables.Font.dispose();
        enemy.sound.dispose();
        Shapes.shapeRenderer.dispose();
        themesound.dispose();
        Player.playerrunninggrass.dispose();
        Player.playerrunningfloor.dispose();



    }
}
