package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.Screens.MainGame;

import static com.rohitsaini.mogli.GAME.myKeyWords.my_X;

public class Controlls {
    public static boolean JUMP= false;
    public static boolean Landed= true;
    static int collx=0;
    static float XValue;
    static float X2Value;
    static float bottomValue;
    public static void render(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//                System.out.println("surface y: "+Variables.SurfaceY);
            MainGame.jumptime=0;



            Player.PLAYER_HEALTH = 20;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }


        if (Player.PLAYER_HEALTH > 0) {
//            Collision
            if(Shapes.check_collision())
            {
                collx=1;
                XValue=Shapes.all_shapes.get(Shapes.collision_id).getX()-30f;
//                sout("Logs:"+(dummyValue1));
                if (Player.getX()<(XValue)){
                    Player.PlayerX=Player.Player_prevX-0.01f;

                }


                X2Value=Shapes.all_shapes.get(Shapes.collision_id).getX()-2.5f;
//                sout("Logs:"+(dummyValue2));
                if (Player.getX()>(X2Value)){
//                    sout("Logs: testing");
                    Player.PlayerX=Player.Player_prevX+0.1f;
                }


                bottomValue =Shapes.all_shapes.get(Shapes.collision_id).getY();
                if (Player.getY()<bottomValue){
                    Landed = false;
                    MainGame.jumptime=0;
                    Player.setY(--Player.PlayerY);
                }
            }
            if (Player.getX()>121&&Player.getX()<2020) {
                Variables.camera.position.set(Player.getX(), Variables.camera.viewportHeight / 2, 0);
            }





//        <----------D Key ------->
            if (Gdx.input.isKeyPressed(Input.Keys.D) && MainGame.level1.wallCollision()) {
                my_X++;
                Player.Player_State = 1;
                Player.Player_Prev_State=Player.Player_State;
                Player.PlayerDirectionRight = true;
                Player.PlayerX += Variables.SPEED * delta;
                if(Shapes.check_collision()) {


                    float dummyValue=Shapes.all_shapes.get(Shapes.collision_id).getX()-30f;
//                    sout("Logs:"+(dummyValue));
                    if (Player.getX()<(dummyValue)){
                    Player.PlayerX=Player.Player_prevX-0.01f;
                }
                }

            } else if (Gdx.input.isKeyPressed(Input.Keys.A)  && Player.getX()>-10) {

                Player.Player_prevX = Player.PlayerX;
                my_X--;
                Player.Player_Prev_State=Player.Player_State;
                Player.Player_State = 11;
                Player.PlayerDirectionRight = false;
                Player.PlayerX -= Variables.SPEED * delta;
                if(Shapes.check_collision()) {



                    float dummyValue=Shapes.all_shapes.get(Shapes.collision_id).getX()-2.5f;
//                    sout("Logs:"+(dummyValue));
                    if (Player.getX()>(dummyValue)){
//                        sout("Logs: testing");
                        Player.PlayerX=Player.Player_prevX+0.1f;
                    }
                }



            } else if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D) ) {
            	Player.Player_Prev_State = Player.Player_State;
                if (Player.Player_Prev_State ==1 || Player.Player_Prev_State==3 || Player.firingtime==0) {
                    Player.Player_State = 2;
                }else if (Player.Player_Prev_State ==11){
                    Player.Player_State = -2;
                }
            }


//        <----------SpaceBar Key ------->
            if (Player.PLAYER_HEALTH > 0 && Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && Landed) {
                Player.Player_State = 0;
                Player.Player_Prev_State = Player.Player_State;
                Player.Player_prevY = Player.PlayerY;
                Landed = false;
                JUMP = true;
                MainGame.jumptime=40;
            }
//        <----------for Extra Operation : W Key ------->



            Player.Player_prevX = Player.PlayerX;

//        Bullet Fired
            if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
                Player.firingtime=3;
                Player.Player_State=3;
                MainGame.bullets.add(new Bullet(Player.PlayerX + Player.PlayerWidth));
//                System.out.println("Bullet added");
            }
        }

        if (Gdx.input.isTouched()){
            Variables.camera.position.set(Gdx.input.getX(), Variables.camera.viewportHeight / 2, 0);
        }



    }
}
