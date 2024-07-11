package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.rohitsaini.mogli.GAME.myKeyWords.*;

public class Shapes {
    static ShapeRenderer shapeRenderer;
    static Rectangle shapes;
    static Rectangle player;
    Shapes (){
        shapeRenderer= new ShapeRenderer();
        shapes = new Rectangle(300,60,15,20);
        player = new Rectangle(0,0,0,0);

    }
    public void shaperender(){
    	
    
    	shapes.set(300,60,15,20);
    	player.set(Player.PlayerX, Player.PlayerY, 15, 40);
    	sout("--"+player.getX());
    	
        shapeRenderer.setProjectionMatrix(Variables.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(Player.PlayerX,Player.PlayerY,15,40);
        shapeRenderer.rect(300,60,15,20);// ( X,Y,WIDTH, HEIGHT ) POSITION 300 TO 300+15 LEFT TO RIGHT AND BOTTOM TO TOP

        shapeRenderer.line(Collision.objX,60f,Collision.objX2,80f);
        shapeRenderer.line(Collision.objX2,60f,Collision.objX,0f);
        shapeRenderer.end();
    }
    
    public static boolean check_collision() {
    	if(player.overlaps(shapes)) {			
    		if(Player.PlayerY<(shapes.getY()+15)){
    			return true;
    			}
//    		if(Player.Player_State==1 || Player.Player_State==11) {Player.PlayerX=Player.Player_prevX;}
    	}
    	return false;    	
    }
    public static boolean check_collision_surface() {
    	if(player.overlaps(shapes)) {		
    		if(Player.PlayerY>(shapes.getY()+15)){
    			sout("Y Surface Collision");
    			return true;
    			}
//    		if(Player.Player_State==1 || Player.Player_State==11) {Player.PlayerX=Player.Player_prevX;}
    	}
    	return false;    	
    }

}
