package com.rohitsaini.mogli.GAME.enemies;

import com.rohitsaini.mogli.GAME.Screens.MainGame;
import com.rohitsaini.mogli.GAME.Variables;

public class AI {
    private static float i=0;
        public void ai(){}
        public static void AI_render(){

            i+= Variables.SPEED * Variables.deltaTime;
            if(i>=50){i=-50;}
            MainGame.enemy.setX(Math.abs(i));
        }

}
