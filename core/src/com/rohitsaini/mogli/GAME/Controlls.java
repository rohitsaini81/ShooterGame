package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.rohitsaini.mogli.GAME.myKeyWords.my_X;
import static com.rohitsaini.mogli.GAME.myKeyWords.my_Y;

public class Controlls {
    public static boolean JUMP= false;
    public static boolean Landed= true;

    public static void render(float delta){
        if (Player.PLAYER_HEALTH > 0) {
//        <----------D Key ------->
            if (Gdx.input.isKeyPressed(Input.Keys.D) && Player.PlayerDirectionRight) {
                my_X++;
                Player.Player_State = 1;
                Player.PlayerDirectionLeft = true;
                Player.Player_prevX = Player.PlayerX;
                Player.PlayerX += Variables.SPEED * delta;

                if (Player.canPLayerMoveRight){Variables.camera.translate(Variables.angle * Variables.SPEED * delta, 0f);}
                Player.canPLayerMoveLeft=true;

            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && Player.PlayerDirectionLeft) {
                my_X--;
                Player.Player_State = 11;
                Player.PlayerDirectionRight = true;
                Player.Player_prevX = Player.PlayerX;
                Player.PlayerX -= Variables.SPEED * delta;
                if (Player.canPLayerMoveLeft){Variables.camera.translate(Variables.angle * -Variables.SPEED * delta, 0f);}
                Player.canPLayerMoveRight=true;

            } else if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && Player.PlayerY <= Variables.SurfaceY) {
                Player.Player_State = 2;
            }


//        <----------SpaceBar Key ------->
            if (Player.PLAYER_HEALTH > 0 && Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Landed) {
                Player.Player_State = 0;
                Landed = false;
                JUMP = true;
            }
//        <----------for Extra Operation : W Key ------->

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            Variables.camera.translate(0f,0f);
                Variables.SurfaceY++;
                System.out.println(Variables.SurfaceY);
                Player.PLAYER_HEALTH = 100;
            }

            Player.Player_prevX = Player.PlayerX;

//        Bullet Fired
            if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                MainGame.bullets.add(new Bullet(Player.PlayerX + Player.PlayerWidth));
                System.out.println("Bullet added");
            }
        }

    }
}
