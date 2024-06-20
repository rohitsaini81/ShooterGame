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
            Player.PlayerDirectionLeft=true;
            Player.Player_prevX=Player.PlayerX;
            Player.PlayerX +=Variables.SPEED * Gdx.graphics.getDeltaTime();
                        Variables.camera.translate(Variables.angle*Variables.SPEED* Gdx.graphics.getDeltaTime(),0f);
            System.out.println(Variables.SPEED * Gdx.graphics.getDeltaTime());


        }


//        <----------A Key ------->
        if (
                MainGame.PLAYER_HEALTH>0 &&
                        Gdx.input.isKeyPressed(Input.Keys.A)&&
                        Player.PlayerDirectionLeft)
        {
            Player.PlayerDirectionRight=true;
            Player.Player_prevX=Player.PlayerX;
            Player.PlayerX -= Variables.SPEED*delta;
            Variables.camera.translate(Variables.angle*-Variables.SPEED * Gdx.graphics.getDeltaTime(),0f);

        }


//        <----------SpaceBar Key ------->
        if (MainGame.PLAYER_HEALTH>0 &&  Gdx.input.isKeyJustPressed(Input.Keys.SPACE ) && Landed){
            Landed=false;
            JUMP = true;
        }
//        <----------for Extra Operation : W Key ------->

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
//            Variables.camera.translate(0f,0f);
            MainGame.PLAYER_HEALTH=100;
            Player.PlayerY += Variables.SPEED*delta;
        }


    }
}
