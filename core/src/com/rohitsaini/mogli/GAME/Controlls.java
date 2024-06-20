package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controlls {
    public static boolean JUMP= false;
    public static boolean Landed= true;

    public static void render(float delta){
//        <----------D Key ------->
        if (
                MainGame.PLAYER_HEALTH>0 &&
                        Gdx.input.isKeyPressed(Input.Keys.D)&&
                        Player.PlayerDirectionRight)
        {
            Player.Player_State=1;
            Player.PlayerDirectionLeft=true;
            Player.Player_prevX=Player.PlayerX;
            Player.PlayerX +=Variables.SPEED * Gdx.graphics.getDeltaTime();
                        Variables.camera.translate(Variables.angle*Variables.SPEED* Gdx.graphics.getDeltaTime(),0f);
            System.out.println(Variables.SPEED * Gdx.graphics.getDeltaTime());


        }

        else if (!Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A) && Player.PlayerY<=Variables.SurfaceY){Player.Player_State=2;}


//        <----------A Key ------->
        if (
                MainGame.PLAYER_HEALTH>0 &&
                        Gdx.input.isKeyPressed(Input.Keys.A)&&
                        Player.PlayerDirectionLeft)
        {
            Player.Player_State=1;
            Player.PlayerDirectionRight=true;
            Player.Player_prevX=Player.PlayerX;
            Player.PlayerX -= Variables.SPEED*delta;
            Variables.camera.translate(Variables.angle*-Variables.SPEED * Gdx.graphics.getDeltaTime(),0f);

        }
        else if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && Player.PlayerY<=Variables.SurfaceY){Player.Player_State=2;}



//        <----------SpaceBar Key ------->
        if (MainGame.PLAYER_HEALTH>0 &&  Gdx.input.isKeyJustPressed(Input.Keys.SPACE ) && Landed){
            Player.Player_State=0;
            Landed=false;
            JUMP = true;
        }
//        <----------for Extra Operation : W Key ------->

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
//            Variables.camera.translate(0f,0f);
            MainGame.PLAYER_HEALTH=100;
        }


//        Bullet Fired
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)){
            MainGame.bullets.add(new Bullet(Player.PlayerX+Player.PlayerWidth));
            System.out.println("Bullet added");
        }


    }
}
