package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import static com.rohitsaini.mogli.GAME.myKeyWords.my_X;

public class Controlls {
    public static boolean JUMP= false;
    public static boolean Landed= true;
    static int collx=0;

    public static void render(float delta){
        if (Player.PLAYER_HEALTH > 0) {
//            Collision
            if(Shapes.check_collision())
            {
                collx=1;
                if (Player.getX()<(Shapes.all_shapes.get(Shapes.collision_id).getX()+7.5f)){
                    Player.PlayerX=Player.Player_prevX-0.01f;
                }
                if (Player.getX()>(Shapes.all_shapes.get(Shapes.collision_id).getX()+10)){
                    Player.PlayerX=Player.Player_prevX+0.01f;
                }
            }
//        <----------D Key ------->
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                my_X++;
                Player.Player_Prev_State=Player.Player_State;
                Player.Player_State = 1;
                Player.PlayerDirectionLeft = true;
                Player.PlayerX += Variables.SPEED * delta;
                if(Shapes.check_collision()) {
                    if (Player.getX()<(Shapes.all_shapes.get(Shapes.collision_id).getX()+7.5f)){
                    Player.PlayerX=Player.Player_prevX-0.01f;
                }
                }
                else{Variables.camera.translate(Variables.angle * Variables.SPEED * delta, 0f);}
                Player.canPLayerMoveLeft=true;

            } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                Player.Player_prevX = Player.PlayerX;
                my_X--;
                Player.Player_Prev_State=Player.Player_State;
                Player.Player_State = 11;
                Player.PlayerDirectionRight = true;
                Player.PlayerX -= Variables.SPEED * delta;
                if(Shapes.check_collision()) {
                    if (Player.getX()>(Shapes.all_shapes.get(Shapes.collision_id).getX()+10)){
                        Player.PlayerX=Player.Player_prevX+0.01f;
                    }
                }
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
//                System.out.println(Variables.SurfaceY);
                Player.PLAYER_HEALTH = 100;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            	Gdx.app.exit();
              }

            Player.Player_prevX = Player.PlayerX;

//        Bullet Fired
            if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                MainGame.bullets.add(new Bullet(Player.PlayerX + Player.PlayerWidth));
//                System.out.println("Bullet added");
            }
        }



        if (Gdx.input.isTouched()){
            System.out.println("Screen touched");
            jj+=Gdx.input.getX()* delta;
            Variables.camera.position.set(jj, Variables.camera.viewportHeight / 2, 0);
//            Variables.camera.translate(Gdx.input.getX()* delta,0f);
        }

    }
    public static int jj;
}
