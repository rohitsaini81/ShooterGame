package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rohitsaini.mogli.Mogali;
import com.badlogic.gdx.math.Rectangle;


import java.util.Arrays;

public class MainGame implements Screen {
    static float PLAYER_HEALTH;
    Game game;
    static float W0 = 0;
    static float W2 = Gdx.graphics.getWidth();
    static float H = Gdx.graphics.getHeight();
    static float S=W2/2;
    static float loopValue=0;
    static  int bg_times=5;


    Enemies enemy;
    Player player;
    SurfaceObjects surfaceObjects;


    public MainGame(Game game) {
        this.game = game;
        Variables.camera = new OrthographicCamera(W2/2,H/2);
        Variables.camera.setToOrtho(false, W2/2 ,H/2);

//        Class Objects = new;
        enemy = new Enemies();
        Bullet bullet = new Bullet();
        surfaceObjects = new SurfaceObjects();

        Variables.batch = new SpriteBatch();
        player= new Player();
        Variables.backgroundT = new Texture("Background.png");
        Variables.sprite=new Sprite(Variables.backgroundT);
        Variables.Font = new BitmapFont();




    }

    @Override
    public void show() {
        PLAYER_HEALTH=500;
        Variables.SPEED=50;
        Player.PlayerIsIdle=true;
        Variables.isCollision=false;
        Player.PlayerDirectionRight=true;
        Player.PlayerY=40;
        Player.PlayerX=70;
        Variables.SurfaceX=50;
        Variables.SurfaceX2=1866;
        Variables.SurfaceY=40;
        Variables.angle=1;
        Variables.Font.setColor(Color.PINK);

    }
//  <----------- Render Method -------------->

    @Override
    public void render(float delta) {
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


        System.out.println(Variables.camera.view.getScaleX());
        Variables.Font.draw(Variables.batch,"X:" +(int)Player.PlayerX+"Y:"+(int)Player.PlayerY,Player.PlayerX,100);
        Variables.Font.draw(Variables.batch,"Health:"+MainGame.PLAYER_HEALTH,Player.PlayerX ,90);
        Variables.Font.draw(Variables.batch,"|",Player.PlayerX ,80);


//         Render Surfaces Objects
        surfaceObjects.RenderSfObjects(Variables.batch);
//        Snake Positon x460 y38
        enemy.RenderEnemy();
        Bullet.Bullet_fire();
        Player.renderPlayer();

//        JUMP FUNCTIONALITY HERE

        if (Controlls.JUMP){
            Player.PlayerY+=Variables.SPEED*delta+0.5f;
        }
        if (Player.PlayerY>=Variables.SurfaceY+40){
            Controlls.JUMP = false;
            Player.PlayerY+=Variables.SPEED*delta+0.5f;
        }

        if (!Controlls.JUMP && Player.PlayerY >=Variables.SurfaceY){
            Player.PlayerY+= Player.velocity*delta;
        }
        if (Player.PlayerY>=0&&Player.PlayerY<=Variables.SurfaceY){
            System.out.println("Player Y Position"+Player.PlayerY);
            Controlls.Landed = true;
        }
        Controlls.render(delta);
        Variables.batch.end();
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
        Player.Texture.dispose();
        Variables.backgroundT.dispose();
        Variables.Font.dispose();


    }
}
