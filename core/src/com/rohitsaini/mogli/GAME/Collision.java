package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.rohitsaini.mogli.GAME.myKeyWords.No;
import static com.rohitsaini.mogli.GAME.myKeyWords.Yes;

public class Collision {
     static int i=0;
    double distX;
    double distY;
    float objectCenterX;
    float objectCenterY;
    Sprite sprite;




    Collision(Sprite spriteObject){
    this.sprite = spriteObject;
    objectCenterX = sprite.getX() + (sprite.getWidth() / 2f);
    objectCenterY = sprite.getY() + (sprite.getHeight() / 2f);
}
    public void checkCollision() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            i++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            i--;
        }

        distX= Math.sqrt(Math.pow(((int)objectCenterX - Player.PlayerX), 2));
        distY =Math.sqrt (Math.pow(Math.abs(objectCenterY-Player.PlayerY),2));
        System.out.println("Distance between Player and Object is X:"+distX+",Y:"+distY);
        System.out.println("Center of between Object by XY:"+objectCenterX+","+objectCenterY);
        System.out.println("<--------------I-------------> is :"+i);

        if(distX<=40 || distY>=15){
            Player.isYCollision=No();
        }
        if (distY>50 && distX <= 40){
            Variables.SurfaceY=100;
            Player.isYCollision=Yes();
        }
        if (distY<20 && distX <= 39) {
            if (distX<=38 && distX>=35){
                Player.canPLayerMoveRight=false;
                Player.isXCollision=Yes();
            }
            if (distX<=12 && distX>=10){
                Player.canPLayerMoveLeft=false;
                Player.isXCollision=Yes();
            }
        }
    }
}