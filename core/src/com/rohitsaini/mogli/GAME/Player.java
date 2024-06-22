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
    static float Player_prevY;
    static int Player_State;
    static boolean isXCollision;
    static boolean isYCollision;

    static float PLAYER_HEALTH;
    static boolean canPLayerMoveLeft;
    static boolean canPLayerMoveRight;





//     Non-Primitive Variables
    static float PlayerX,PlayerY,PlayerWidth,PlayerHeight;
    static Texture TextureLeft,TextureRight;
    static Texture Texture2Left,Texture2Right;
    static Texture Texture3Left,Texture3Right;

    static TextureRegion[] playerTextureRegions;
    static TextureRegion[] playerRunningTextureRegions;
    static TextureRegion[] LeftplayerRunningTextureRegions;
    static TextureRegion[] playerJumpTextureRegions;

    static Animation<TextureRegion> playerAnimation;
    static Animation<TextureRegion> playerRunningAnimation;
    static Animation<TextureRegion> LeftplayerRunningAnimation;
        static Animation<TextureRegion> playerJumpAnimation;
    TextureRegion[][] temp;



//

    Player (){
        isYCollision=false;
        canPLayerMoveLeft=true;
        canPLayerMoveRight=true;
        PlayerWidth=34;PlayerHeight=38;
        Player_State = 2;
        TextureLeft = new Texture("idleshoot.png");
        TextureRight = new Texture("R_idleshoot.png");

        Texture2Left= new Texture("L_runningshooter.png");
        Texture2Right= new Texture("runningshooter.png");
//        Texture3Left= new Texture("jumpshooter.png");
        Texture3Right= new Texture("jumpshooter.png");


//      Idle
        temp = TextureRegion.split(TextureLeft,31,35);
        playerTextureRegions= new TextureRegion[5];
        for (int j = 0; j <5; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerAnimation = new Animation<>(.1f, playerTextureRegions);
        index=0;

//        Running Animation Right

        temp = TextureRegion.split(Texture2Right,34,36);
        playerRunningTextureRegions= new TextureRegion[12];
        for (int j = 0; j <12; j++) {
            playerRunningTextureRegions[index++]=temp[0][j];
        }

        playerRunningAnimation = new Animation<>(.1f,playerRunningTextureRegions);
        index=0;

//        Running Animation Left

        temp = TextureRegion.split(Texture2Left,34,36);
        LeftplayerRunningTextureRegions= new TextureRegion[11];
        for (int j = 0; j <11; j++) {
            LeftplayerRunningTextureRegions[index++]=temp[0][j];
        }

        LeftplayerRunningAnimation = new Animation<>(.1f, LeftplayerRunningTextureRegions);
        index=0;


//        JUMP_GUY
        temp = TextureRegion.split(Texture3Right,33,37);
        playerJumpTextureRegions= new TextureRegion[4];
        for (int j = 0; j <4; j++) {
            playerJumpTextureRegions[index++]=temp[0][j];
        }

        playerJumpAnimation = new Animation<>(.1f, playerJumpTextureRegions);

    }
    static void renderPlayer(){
        if (!isXCollision){
            Player.canPLayerMoveLeft=true;
            Player.canPLayerMoveRight=true;
        }
        if (!isYCollision){
            Variables.SurfaceY=60;
        }
        switch (Player_State){
            case 0:
                Variables.batch.draw(Player.playerJumpAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            case 1:
                Variables.batch.draw(Player.playerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            case 11:
                 Variables.batch.draw(Player.LeftplayerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                 break;
            default:
                Variables.batch.draw(Player.playerAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                 break;

        }
    }
}
