package com.rohitsaini.mogli.GAME;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.rohitsaini.mogli.GAME.player.Player;


import static com.rohitsaini.mogli.GAME.myKeyWords.*;

public class Collision {
     static int i=0;
    double distX;
    double distX2;
    double distY;
    float objectCenterX;
    float objectCenterY;
    Sprite sprite;
    public static float objX,objX2,objY,objY2;




    Collision(Sprite spriteObject){
    this.sprite = spriteObject;
    objectCenterX = sprite.getX() + (sprite.getWidth() / 2f);
    objectCenterY = sprite.getY() + (sprite.getHeight() / 2f);
    objX = sprite.getX();
    objX2 = sprite.getX()+sprite.getWidth();
    objY = sprite.getY();
    objY2 = sprite.getY() + (sprite.getHeight() / 2f);
}
    public void checkCollision() {

        distX= Math.sqrt(Math.pow(((int)objX - Player.PlayerX), 2));
        distX2=Math.sqrt(Math.pow(((int)objX2 - Player.PlayerX), 2));
        distY =Math.sqrt (Math.pow(Math.abs(objectCenterY-Player.PlayerY),2));
//        System.out.println("Distance between Player and Object is X2:"+distX2+",X:::"+distX);
//        System.out.println("Center of between Object by XY:"+objectCenterX+","+objectCenterY);


//        if((distX<=34 && distX>30) && distY>60){
//            Player.isYCollision=No();
//        }
//        System.out.println("\nOBJECT X:"+objX+"||"+ this.sprite.getX());
//        sout("hii");
//        System.out.println("Surface Collision,objx"+objX+"objx2"+objX2);
// Y SURFACE !
        if (Player.PlayerY>=objY2 && ( (Player.PlayerX>=objX-sprite.getWidth() && Player.PlayerX<=316) 
//        		||(Player.PlayerX+15>=objX-sprite.getWidth() && Player.PlayerX<=316)
        		)){
            Variables.SurfaceY=80;
            Player.isYCollision=Yes();
        }else reset();
       
        if (Player.PlayerY<objY2) {
        	
            if (distX<=16 && distX>=15 && Player.PlayerX<objX2+1 && (Player.Player_State==1 || Player.Player_State==11 )){
//                System.out.println("Right Collision");
                Player.canPLayerMoveRight=false;
                Variables.angle=0;
                Player.PlayerX-=Variables.SPEED * Gdx.graphics.getDeltaTime();
                Player.isXCollision=Yes();
            }
            else {Player.canPLayerMoveRight=Yes();Variables.angle=1;}

            if (Player.PlayerX<=objX2+1 && Player.PlayerX>objX-1&& Player.Player_State==11){
//                System.out.println("Left Collision");
                Player.canPLayerMoveLeft=false;
                Variables.angle=0;
                Player.PlayerX+=Variables.SPEED * Gdx.graphics.getDeltaTime();
//                Player.isXCollision=Yes();
            }
            else {Player.canPLayerMoveLeft=Yes();Variables.angle=1;}
        }
//        if()
    }
}