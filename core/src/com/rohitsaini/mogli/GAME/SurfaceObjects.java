package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SurfaceObjects {
    static Sprite r;
    Sprite Sand;
    Sprite Ice;

//    Objects Coordinats
    float Mudx;
    float Mudx2;
    float Mudy;
    float Mudy2;

    static float Sand_x;
    static float Sand_y;

    float Icex;
    float Icex2;
    float Icey;
    float Icey2;



    SurfaceObjects(){
        Texture texture = new Texture("sand.png");
        Sand = new Sprite(texture);
        Sand.setX(550);
        Sand.setY(50);

        Sand.setSize(20,20);
        Sand_x=550;
        Sand_y=50;



    }
//    static int tempI=3;
    public void RenderSfObjects(SpriteBatch batch) {
        Sand.draw(batch);
        PlayerCollision(Sand,true);

    }
     static void PlayerCollision (Sprite r,boolean isY) {
         if (r.getX() < Player.PlayerX + Player.PlayerWidth && r.getX() + r.getWidth() > Player.PlayerX
//        &&r.getY < Player.PlayerY + Player.PlayerHeight && r.getY + r.getHeight() > Player.PlayerY
         )
         {
             Variables.angle=0;
             if (Player.PlayerY<Sand_y){
                 Player.PlayerX=Player.Player_prevX;
             }
             if (Sand_y < Player.PlayerY + Player.PlayerHeight && Sand_y + r.getHeight() > Player.PlayerY){
                 System.out.println(Variables.SurfaceY);
                 Variables.SurfaceY=Sand_y + r.getHeight();
             }else{Player.PlayerX=Player.Player_prevX;}
//            Player.PlayerY=Player.Player_prevY;
         }else {
//            System.out.println(Variables.SurfaceY);
             Variables.angle=1;
             Variables.SurfaceY=40;}
//        return Sand_x < r.getX() + r.getWidth() && Sand_x + r.getWidth() > r.getX() && Sand_y < r.getY() + r.getHeight() && Sand_y + r.getHeight() > r.getY();
    }
}