package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {
//    Primitive Variables
    static boolean PlayerDirectionRight;
    static boolean PlayerDirectionLeft;
    static boolean PlayerIsIdle;
    static int index = 0;
    static float velocity=-200;
    static float Player_prevX;
    static float Player_prevY;



//     Non-Primitive Variables
    static float PlayerX,PlayerY,PlayerWidth,PlayerHeight;
    static Texture Texture;
    static TextureRegion[] playerTextureRegions;
    static TextureRegion[] playerRunningTextureRegions;
    static TextureRegion[] playerJumpTextureRegions;

    static Animation<TextureRegion> playerAnimation;
    static Animation<TextureRegion> playerRunningAnimation;
    static Animation<TextureRegion> playerJumpAnimation;
    static Rectangle PlayerRect;
//

    Player (){
        PlayerWidth=34;PlayerHeight=38;
        Texture= new Texture("shotgun.png");
        TextureRegion[][] temp = TextureRegion.split(Texture,34,38);
        playerRunningTextureRegions= new TextureRegion[12];
        for (int j = 0; j < 12; j++) {
            playerRunningTextureRegions[index++]=temp[1][j];
        }

        playerRunningAnimation = new Animation<>(.1f, playerRunningTextureRegions);
    }
    static void renderPlayer(){

        Variables.batch.draw(Player.playerRunningAnimation.getKeyFrame(Variables.stateTime,true), Player.PlayerX, Player.PlayerY,PlayerWidth,PlayerHeight);
    }
}
