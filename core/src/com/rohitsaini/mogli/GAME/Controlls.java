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
                Player.Player_Prev_State=Player.Player_State;
                Player.Player_State = 1;
                Player.PlayerDirectionLeft = true;
                Player.PlayerX += Variables.SPEED * delta;
                if(Shapes.check_collision()) {Player.PlayerX=Player.Player_prevX-0.05f;}
                else{Variables.camera.translate(Variables.angle * Variables.SPEED * delta, 0f);}
                Player.canPLayerMoveLeft=true;

            } else if (Gdx.input.isKeyPressed(Input.Keys.A) && Player.PlayerDirectionLeft) {

                Player.Player_prevX = Player.PlayerX;
                my_X--;
                Player.Player_Prev_State=Player.Player_State;
                Player.Player_State = 11;
                Player.PlayerDirectionRight = true;
                Player.PlayerX -= Variables.SPEED * delta;
                if(Shapes.check_collision()) {Player.PlayerX=Player.Player_prevX+0.05f;}
                else{Variables.camera.translate(Variables.angle * -Variables.SPEED * delta, 0f);}
                Player.canPLayerMoveRight=true;

            } else if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) && Player.PlayerY <= Variables.SurfaceY) {
            	Player.Player_Prev_State = Player.Player_State;
            	Player.Player_State = 2;
            }


//        <----------SpaceBar Key ------->
            if (Player.PLAYER_HEALTH > 0 && Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Landed) {

                Player.Player_prevY = Player.PlayerY;
                Player.Player_Prev_State = Player.Player_State;
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
            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            	Gdx.app.exit();
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
