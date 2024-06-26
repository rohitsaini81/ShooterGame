package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.ArrayList;

import static com.rohitsaini.mogli.GAME.Player.textureenemey;
import static com.rohitsaini.mogli.GAME.myKeyWords.*;


public class MainGame implements Screen {
    Game game;
    static float W0 = 0;
    static float W2 = Gdx.graphics.getWidth();
    static float H = Gdx.graphics.getHeight();
    static float S=W2/2;
    static float loopValue=0;
    static  int bg_times=5;
    static ArrayList<Bullet> bullets;
    static ShapeRenderer shapeRenderer;
    static Shapes shapes;



    Enemies enemy;
    Player player;
    SurfaceObjects surfaceObjects;

    public MainGame(Game game) {
        this.game = game;
        shapeRenderer= new ShapeRenderer();

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
        Variables.backgroundT = new Texture("Background.png");
        Variables.sprite=new Sprite(Variables.backgroundT);
        Variables.Font = new BitmapFont();




    }

    @Override
    public void show() {
        Player.PLAYER_HEALTH=500;
        Variables.SPEED=50;
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
        states.set(0,"X:" +(int)Player.PlayerX+"Y:"+(int)Player.PlayerY);
        states.set(1,"X:"+Gdx.input.getX()+" ,Health:"+Player.PLAYER_HEALTH);

        for (int i = 0; i < states.size(); i++) {
//            System.out.println(states.get(0));
            Variables.Font.draw(Variables.batch, states.get(i),Player.PlayerX,my_Y+(i*100));

        }

        Variables.Font.draw(Variables.batch,"|",Player.PlayerX ,80);


//         Render Surfaces Objects
        surfaceObjects.renderSfObjects(Variables.batch);
//        Snake Positon x460 y38
        enemy.RenderEnemy();
        Player.renderPlayer();


        Variables.batch.draw(textureenemey,Player.PlayerX,my_Y,40,60);








//        Bullet Functionality HERE

//        <Update Bullets>
        ArrayList<Bullet>bulletstoRemove = new ArrayList<>();
        for (Bullet bullet:bullets){
            bullet.update();
            if (bullet.remove){
                bulletstoRemove.add(bullet);}
        }
        bullets.removeAll(bulletstoRemove);
        for (Bullet bullet:bullets){
            bullet.Bullet_fire(Variables.batch);
        }



//        JUMP FUNCTIONALITY HERE

        if (Controlls.JUMP){
            Player.PlayerY+=Variables.SPEED*delta+0.5f;
        }
        if (Player.PlayerY>Variables.SurfaceY+80){
            Controlls.JUMP = false;
        }

        if (!Controlls.JUMP && Player.PlayerY >=Variables.SurfaceY){
            Player.PlayerY-= Variables.SPEED*delta;// -100+surface
        }
        if (Player.PlayerY>=Variables.SurfaceY-10&&Player.PlayerY<=Variables.SurfaceY){
            Controlls.Landed = true;
        }
        System.out.println();
        Controlls.render(delta);

        Variables.batch.end();
        shapes.shaperender();



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
        Variables.backgroundT.dispose();
        Variables.Font.dispose();
        Enemies.sound.dispose();
        Shapes.shapeRenderer.dispose();



    }
}
