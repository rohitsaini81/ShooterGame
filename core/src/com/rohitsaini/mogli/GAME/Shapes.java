package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.rohitsaini.mogli.GAME.myKeyWords.*;

import java.util.ArrayList;

public class Shapes {
    static ShapeRenderer shapeRenderer;
    static Rectangle BoxJammer1;
     public static Rectangle player;
    static ArrayList<Rectangle>all_shapes;
    public static boolean collision_x_on;
    public static int collision_id;
    Shapes (){
        collision_x_on=false;
        shapeRenderer= new ShapeRenderer();
        all_shapes = new ArrayList<>();
        BoxJammer1 = new Rectangle(300,60,15,20);
        player = new Rectangle(0,0,0,0);
        
        
//        shapes init
    	BoxJammer1.set(300,60,15,20);
    	
        shapeRenderer.setColor(Color.RED);

    }
    public void shaperender(){
    	

//    	sout("--"+player.getX());
    	
        shapeRenderer.setProjectionMatrix(Variables.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                player.set((Player.PlayerX+15), Player.PlayerY, 18, 40);
        shapeRenderer.rect((Player.PlayerX+15),Player.PlayerY,18,40); // Player is HERE;
        shapeRenderer.rect(300,60,15,20); // BoxJammer1
        // ( X,Y,WIDTH, HEIGHT ) POSITION 300 TO 300+15 LEFT TO RIGHT AND BOTTOM TO TOP

        shapeRenderer.line(Collision.objX,60f,Collision.objX2,80f);
        shapeRenderer.line(Collision.objX2,60f,Collision.objX,200f);
        shapeRenderer.end();
        this.myshapes();
        
        
//        here we update jammers stability
//        static jammers could be on constructor because they are not going to destroy like enemy
        all_shapes.add(BoxJammer1);
        all_shapes.add(SurfaceObjects.BoxJammer2);
//        all_shapes.add(BoxJammer3);
        
    }

   private void myshapes () {
	   shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
       shapeRenderer.line(0f,100f,Player.PlayerX,200f);
       shapeRenderer.end();
	   
	   
   }
   
    
    public static boolean check_collision() {
    	for (int i=0;i<all_shapes.size();i++) {
    		if(player.overlaps(all_shapes.get(i))) {		
        		if(Player.PlayerY<(all_shapes.get(i).getY()+15)){
                    collision_id=i;
                    collision_x_on=true;
        			return true;
        			}
        	}
    	}
        collision_x_on=false;
    	return false;    	
    }
    public static boolean check_collision(Rectangle R) {
            if(player.overlaps(R)) {
                if(Player.PlayerY<(R.getY()+15)){
                    return true;
                }
            }
        return false;
    }
    public static boolean check_collision_surface() {
    	for (int i=0;i<all_shapes.size();i++) {
    	if(player.overlaps(all_shapes.get(i))) {		
    		if(Player.PlayerY>(all_shapes.get(i).getY()+15)){
//    			sout("Y Surface Collision");
    			return true;
    			}
    	}
    	}
    	return false;    	
    }
    public static boolean check_collision_surface(Rectangle R) {

            if(player.overlaps(R)) {
                if(Player.PlayerY>(R.getY()+15)){
//                    sout("Y Surface Collision");
                    return true;
                }
            }

        return false;
    }
    public static boolean check_collision_surface(Rectangle R,Rectangle X) {

        if(R.overlaps(X)) {
//            if(Player.PlayerY>(R.getY()+15)){
//                    sout("Y Surface Collision");
                return true;
//            }
        }

        return false;
    }

}
