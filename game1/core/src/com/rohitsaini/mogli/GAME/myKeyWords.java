package com.rohitsaini.mogli.GAME;

import java.util.ArrayList;

public class myKeyWords {
    public static ArrayList<String> states;
    public static float my_Y=200;
    public static float my_X=200;
    public static boolean Yes(){
        return true;
    }
    public static boolean No(){
        return false;
    }
    public static double RandomNumber(int range){
        return Math.round(range * Math.random()) + 1;
    }
    public static int RandomNumber(int from, int range) {
        return from + (int) (Math.random() * (range + 1 - from));
    }


    public static void reset (){
//        Player.PLAYER_HEALTH=100;
        Variables.SPEED=50;

        Variables.SurfaceY=60;
//        Variables.angle=1;
    }
    public static void sout(String obj_String) {
    	System.out.println(obj_String);
    }
}
