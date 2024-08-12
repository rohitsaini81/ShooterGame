package com.rohitsaini.mogli.GAME.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rohitsaini.mogli.GAME.Controler.Controlls;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.Variables;


public class Player {
//    Primitive Variables
    public static boolean PlayerDirectionRight;
    public static boolean PlayerIsIdle;
    static int index = 0;
    static float velocity=-200;
    public static float Player_prevX;
    public static float Player_prevY;
    public static int Player_State;
    public static int Player_Prev_State;
    public static boolean isXCollision;
    public static boolean isYCollision;
    public static float firingtime;

    public static float PLAYER_HEALTH;
    public static boolean canPLayerMoveLeft;
    public static boolean canPLayerMoveRight;
    public static boolean movecamera;

    public static float PlayerX;
    public static float PlayerY;
    public static float eyex1;
    public static float eyex2;
    public static float PlayerWidth;
    public static float PlayerHeight;
    public static float PlayerSpeed;





//     Non-Primitive Variables

    public static Array<Boolean> cameraOn;
    public static Array<Float> pl_twopoint;

    public static Sound playerDead;
    public static Music playerrunningfloor;
    public static Music playerrunninggrass;

    public static Rectangle eyerect;

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
    static TextureRegion[] playerFiringLeftTextureRegions;


    static Animation<TextureRegion> playerAnimation;
    static Animation<TextureRegion> playerILeftAnimation;
    static Animation<TextureRegion> playerRunningAnimation;
    static Animation<TextureRegion> LeftplayerRunningAnimation;
    static Animation<TextureRegion> playerJumpAnimation;
    static Animation<TextureRegion> playerFiringAnimation;
    static Animation<TextureRegion> playerFiringLeftAnimation;
    TextureRegion[][] temp;



//


    public static void setX(float playerX) {
        PlayerX=playerX;
    }
    public static void setY(float playerY) {
        PlayerY=playerY;
    }

    public static float getX() {
        return PlayerX;
    }
    public static float getY() {
        return Shapes.player.getY();
    }


    public Player(){
        isYCollision=false;
        canPLayerMoveLeft=true;
        canPLayerMoveRight=true;
        PlayerWidth=50;PlayerHeight=60;
        Player_State = 2;
        firingtime=0;
        movecamera=true;
        PlayerSpeed=70;

        PlayerDirectionRight=true;
        cameraOn = new Array<>();
        pl_twopoint=new Array<>(2);
        pl_twopoint.add(Player.PlayerX);
        pl_twopoint.add(Player.PlayerX);
        cameraOn.add(true);
        playerDead=Gdx.audio.newSound(Gdx.files.internal("playerSound/ouchmp3-14591.mp3"));
        playerrunningfloor = Gdx.audio.newMusic(Gdx.files.internal("playerSound/running-on-floor.mp3"));
        playerrunninggrass = Gdx.audio.newMusic(Gdx.files.internal("playerSound/running-on-grass.mp3"));
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
        Texture4Left= new Texture("characterSprites/Gangsters_1/ShotL.png");




//      Idle Left

        temp = TextureRegion.split(TextureLeft,128,128);
        playerTextureRegions= new TextureRegion[6];
        for (int j = 0; j <6; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerILeftAnimation = new Animation<>(.2f, playerTextureRegions);
        index=0;
//      Idle Right
        temp = TextureRegion.split(TextureRight,128,128);
        playerTextureRegions= new TextureRegion[6];
        for (int j = 0; j <6; j++) {
            playerTextureRegions[index++]=temp[0][j];
        }
        playerAnimation = new Animation<>(0.4f, playerTextureRegions);
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

        playerFiringAnimation = new Animation<>(.3f, playerFiringTextureRegions);
        index=0;

//left firing
        temp = TextureRegion.split(Texture4Left,128,128);

        playerFiringLeftTextureRegions= new TextureRegion[4];
        for (int j = 0; j <4; j++) {
            playerFiringLeftTextureRegions[index++]=temp[0][j];
        }

        playerFiringLeftAnimation = new Animation<>(.3f, playerFiringLeftTextureRegions);
        index=0;



    }

    public static void renderPlayer(){
        pl_twopoint.set(1, Player.PlayerX);
        eyex1 = Math.abs(Player.PlayerX-120);
        eyex2 = Math.abs(Player.PlayerX+120);
        if (firingtime>0){
            Player.setX(Player.Player_prevX);
            firingtime-= Variables.SPEED * Gdx.graphics.getDeltaTime();
            if (firingtime<0){firingtime=0;}

        }
//        sout(""+firingtime);
        if (!isXCollision){
            Player.canPLayerMoveLeft=true;
            Player.canPLayerMoveRight=true;
        }
        if (!isYCollision){
            Variables.SurfaceY=60;
        }

        switch (Player_State){
            case 0:
                PlayerIsIdle=false;
                Variables.batch.draw(Player.playerJumpAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            case 1:
                PlayerIsIdle=false;
                Variables.batch.draw(Player.playerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                break;
            case 11:
                PlayerIsIdle=false;
                Variables.batch.draw(Player.LeftplayerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
                 break;
            case 3:
                PlayerIsIdle=false;
                if (Player.PlayerDirectionRight) {
//                    PlayerIsIdle=false;
                    Variables.batch.draw(Player.playerFiringAnimation.getKeyFrame(Variables.stateTime, true), Player.PlayerX, Player.PlayerY, PlayerWidth, PlayerHeight);
                }else {
//                    PlayerIsIdle=false;
                    Variables.batch.draw(Player.playerFiringLeftAnimation.getKeyFrame(Variables.stateTime, true), Player.PlayerX, Player.PlayerY, PlayerWidth, PlayerHeight);}
                break;
            default:

                if (Player.PlayerDirectionRight && Player.firingtime==0){
                    PlayerIsIdle=true;
                Variables.batch.draw(Player.playerAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);}
                else if (Player.firingtime==0){
                    PlayerIsIdle=true;
                    Variables.batch.draw(Player.playerILeftAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);}
                 break;

        }
//        System.out.println(playerrunningfloor.getPosition());
        if (!PlayerIsIdle && !playerrunningfloor.isPlaying() && Controlls.Landed){
//            System.out.println("playing running sound");
//            playerrunninggrass.play();
            playerrunningfloor.play();
        }
        if (PlayerIsIdle){
//            System.out.println("player idel");
            playerrunninggrass.dispose();
            playerrunningfloor.dispose();
        }

    }
    public static Boolean checkcameraeye() {
        for (Boolean B : Player.cameraOn) {
            if (!B) {
//                System.out.println(B+"b is false");
                return false;
            }
        }
        return true;
    }

}
