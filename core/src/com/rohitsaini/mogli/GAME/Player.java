package com.rohitsaini.mogli.GAME;

import static com.rohitsaini.mogli.GAME.myKeyWords.sout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Player {
//    Primitive Variables
    static boolean PlayerDirectionRight;
    static boolean PlayerIsIdle;
    static int index = 0;
    static float velocity=-200;
    static float Player_prevX;
    static float Player_prevY;
    static int Player_State;
    static int Player_Prev_State;
    static boolean isXCollision;
    static boolean isYCollision;
    static float firingtime;

    public static float PLAYER_HEALTH;
    static Sound playerDead;
    static boolean canPLayerMoveLeft;
    static boolean canPLayerMoveRight;





//     Non-Primitive Variables
    static float PlayerX;
    public static float PlayerY;
    static float PlayerWidth;
    static float PlayerHeight;
//    static Texture textureenemey;
    static Texture TextureLeft,TextureRight;
    static Texture Texture2Left,Texture2Right;
    static Texture Texture3Left,Texture3Right;
    static Texture Texture4Left,Texture4Right;

    static TextureRegion[] playerTextureRegions;
    static TextureRegion[] playerRunningTextureRegions;
    static TextureRegion[] LeftplayerRunningTextureRegions;
    static TextureRegion[] playerJumpTextureRegions;
    static TextureRegion[] playerFiringTextureRegions;

    static Animation<TextureRegion> playerAnimation;
    static Animation<TextureRegion> playerILeftAnimation;
    static Animation<TextureRegion> playerRunningAnimation;
    static Animation<TextureRegion> LeftplayerRunningAnimation;
    static Animation<TextureRegion> playerJumpAnimation;
    static Animation<TextureRegion> playerFiringAnimation;
    TextureRegion[][] temp;



//


    public static void setX(float playerX) {
        PlayerX = playerX;
    }
    public static float getX() {
        return PlayerX;
    }
    public static void setY(float playerY) {
        PlayerX = playerY;
    }


    Player (){
        isYCollision=false;
        canPLayerMoveLeft=true;
        canPLayerMoveRight=true;
        PlayerWidth=50;PlayerHeight=60;
        Player_State = 2;
        firingtime=0;
        PlayerDirectionRight=true;
        playerDead=Gdx.audio.newSound(Gdx.files.internal("playerSound/ouchmp3-14591.mp3"));
//        ouchmp3-14591.mp3
//        characterouch2-163912.mp3
//        male_hurt7-48124.mp3
//        punch sound ::>> punch-2-123106.mp3
        TextureLeft = new Texture("characterSprites/Gangsters_1/IdleL.png");
        TextureRight = new Texture("characterSprites/Gangsters_1/IdleR.png");

        Texture2Left= new Texture("characterSprites/Gangsters_1/RunL.png");
        Texture2Right= new Texture("characterSprites/Gangsters_1/Run.png");
//        Texture3Left= new Texture("jumpshooter.png");
        Texture3Right= new Texture("characterSprites/Gangsters_1/Jump.png");

        Texture4Right= new Texture("characterSprites/Gangsters_1/Shot.png");




//      Idle Left

        temp = TextureRegion.split(TextureLeft,128,128);
        playerTextureRegions= new TextureRegion[6];
        for (int j = 0; j <6; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerILeftAnimation = new Animation<>(.08f, playerTextureRegions);
        index=0;
//      Idle Right
        temp = TextureRegion.split(TextureRight,128,128);
        playerTextureRegions= new TextureRegion[6];
        for (int j = 0; j <6; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerAnimation = new Animation<>(.08f, playerTextureRegions);
        index=0;

//        Running Animation Right

        temp = TextureRegion.split(Texture2Right,128,128);
        playerRunningTextureRegions= new TextureRegion[10];
        for (int j = 0; j <10; j++) {
            playerRunningTextureRegions[index++]=temp[0][j];
        }

        playerRunningAnimation = new Animation<>(.1f,playerRunningTextureRegions);
        index=0;

//        Running Animation Left

        temp = TextureRegion.split(Texture2Left,128,128);

        LeftplayerRunningTextureRegions= new TextureRegion[10];
        for (int j = 0; j <10; j++) {
            LeftplayerRunningTextureRegions[index++]=temp[0][j];
        }

        LeftplayerRunningAnimation = new Animation<>(.1f, LeftplayerRunningTextureRegions);
        LeftplayerRunningAnimation.setPlayMode(Animation.PlayMode.LOOP_REVERSED);
        index=0;


//        JUMP_GUY
        temp = TextureRegion.split(Texture3Right,128,128);
        playerJumpTextureRegions= new TextureRegion[10];
        for (int j = 0; j <10; j++) {
            playerJumpTextureRegions[index++]=temp[0][j];
        }
        index = 0;

        playerJumpAnimation = new Animation<>(.3f, playerJumpTextureRegions);

//        Shot animation
        temp = TextureRegion.split(Texture4Right,128,128);

        playerFiringTextureRegions= new TextureRegion[4];
        for (int j = 0; j <4; j++) {
            playerFiringTextureRegions[index++]=temp[0][j];
        }

        playerFiringAnimation = new Animation<>(.1f, playerFiringTextureRegions);
        index=0;



    }

    static void renderPlayer(){
        if (firingtime>0){
            firingtime-= Variables.SPEED * Gdx.graphics.getDeltaTime();
            if (firingtime<0){firingtime=0;}

        }
        sout(""+firingtime);
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
            case 3:
                Variables.batch.draw(Player.playerFiringAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            default:

                if (Player.Player_Prev_State==2 && Player.firingtime==0){
                Variables.batch.draw(Player.playerAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);}
                else if (Player.firingtime==0){Variables.batch.draw(Player.playerILeftAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);}
                 break;

        }
    }
}
