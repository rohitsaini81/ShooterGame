package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Collision {
   static boolean isColliding = false;



    static boolean checkCollision(Sprite sprite) {
        boolean collisionX = sprite.getX() < Player.PlayerX + Player.PlayerWidth && sprite.getX() + sprite.getWidth() > Player.PlayerX;
        boolean collisionY = sprite.getY() < Player.PlayerY + Player.PlayerHeight && sprite.getY() + sprite.getHeight() > Player.PlayerY;

        if (collisionX && collisionY) {
            System.out.println("PlayerCollision");

            // Adjust player's X position
            if (Player.Player_prevX < Player.PlayerX) { // Moving right
                Player.PlayerX = sprite.getX() - Player.PlayerWidth;
            } else if (Player.Player_prevX > Player.PlayerX) { // Moving left
                Player.PlayerX = sprite.getX() + sprite.getWidth();
            }

            // Adjust player's Y position
            if (Player.PlayerY + Player.PlayerHeight > sprite.getY() && Player.PlayerY < sprite.getY() + sprite.getHeight()) { // Standing on top
                Player.PlayerY = sprite.getY() + sprite.getHeight();
                Player.isYCollision = true;
                Variables.SurfaceY = sprite.getY() + sprite.getHeight();
            } else if (Player.PlayerY > sprite.getY() + sprite.getHeight() && Player.PlayerY + Player.PlayerHeight > sprite.getY()) { // Hitting from below
                Player.PlayerY = sprite.getY() - Player.PlayerHeight;
            }
        } else {
            Player.isYCollision = false;
        }
        return false;
    }
}
