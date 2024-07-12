package com.rohitsaini.mogli.GAME.enemies;

public class AI {
    private static float i=0;
        public void ai(){}
        public static void AI_render(){

            i+=0.5;
            if(i>=50){i=-50;}
            Enemies.setX(Math.abs(i));
        }

}
