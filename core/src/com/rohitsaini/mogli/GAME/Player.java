package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Player {
//    Primitive Variables
    static boolean PlayerDirectionRight;
    static boolean PlayerDirectionLeft;
    static boolean PlayerIsIdle;
    static int index = 0;
    static float velocity=-200;
    static float Player_prevX;
    static int Player_State;



//     Non-Primitive Variables
    static float PlayerX,PlayerY,PlayerWidth,PlayerHeight;
    static Texture Texture;
    static Texture Texture2;
    static Texture Texture3;

    static TextureRegion[] playerTextureRegions;
    static TextureRegion[] playerRunningTextureRegions;
    static TextureRegion[] playerJumpTextureRegions;

    static Animation<TextureRegion> playerAnimation;
    static Animation<TextureRegion> playerRunningAnimation;
    static Animation<TextureRegion> playerJumpAnimation;
    TextureRegion[][] temp;



//

    Player (){
        PlayerWidth=34;PlayerHeight=38;
        Player_State = 2;
        Texture = new Texture("idleshoot.png");
        Texture2= new Texture("runningshooter.png");
        Texture3= new Texture("jumpshooter.png");



        temp = TextureRegion.split(Texture,36,35);
        playerTextureRegions= new TextureRegion[5];
        for (int j = 0; j <5; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerAnimation = new Animation<>(.1f, playerTextureRegions);
        index=0;

//        Running Animation

        temp = TextureRegion.split(Texture2,34,36);
        playerRunningTextureRegions= new TextureRegion[12];
        for (int j = 0; j <12; j++) {
            playerRunningTextureRegions[index++]=temp[0][j];
        }

        playerRunningAnimation = new Animation<>(.1f, playerRunningTextureRegions);
        index=0;


//        JUMP_GUY
        temp = TextureRegion.split(Texture3,33,37);
        playerJumpTextureRegions= new TextureRegion[4];
        for (int j = 0; j <4; j++) {
            playerJumpTextureRegions[index++]=temp[0][j];
        }

        playerJumpAnimation = new Animation<>(.1f, playerJumpTextureRegions);

    }
    static void renderPlayer(){
        switch (Player_State){
            case 0:
                Variables.batch.draw(Player.playerJumpAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            case 1:
                 Variables.batch.draw(Player.playerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                 break;
            default:
                Variables.batch.draw(Player.playerAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                 break;

        }
    }
}
