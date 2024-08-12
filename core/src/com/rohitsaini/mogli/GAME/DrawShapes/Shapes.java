package com.rohitsaini.mogli.GAME.DrawShapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rohitsaini.mogli.GAME.player.Player;
import com.rohitsaini.mogli.GAME.Screens.MainGame;
import com.rohitsaini.mogli.GAME.SurfaceObjects;
import com.rohitsaini.mogli.GAME.Variables;

import java.util.ArrayList;

public class Shapes {
    public static ShapeRenderer shapeRenderer;
     public static Rectangle player;
     public static Rectangle airStands;
    public static ArrayList<Rectangle>all_shapes;
    public static boolean collision_x_on;
    public static int collision_id;
    public  static Rectangle surfaceRect;
    public Shapes(){
        collision_x_on=false;
        shapeRenderer= new ShapeRenderer();
        all_shapes = new ArrayList<>();
        surfaceRect= new Rectangle(0,60,2000,1);
        player = new Rectangle(0,0,0,0);
        airStands = new Rectangle(0,120,20,5);
//        all_shapes.add(airStands);

        
//        shapes init

        shapeRenderer.setColor(Color.RED);

    }
    public void shaperender(){
    	

//    	sout("--"+player.getX());
    	
        shapeRenderer.setProjectionMatrix(Variables.camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        player.set((Player.PlayerX+15), Player.PlayerY, 18, 40);
        shapeRenderer.rect((Player.PlayerX+15),Player.PlayerY,18,40); // Player is HERE;
        shapeRenderer.rect(SurfaceObjects.BoxJammer1.getX(), SurfaceObjects.BoxJammer1.getY(), SurfaceObjects.BoxJammer1.getWidth(), SurfaceObjects.BoxJammer1.getHeight()); // BoxJammer1
        shapeRenderer.rect(SurfaceObjects.BoxJammer2.getX(), SurfaceObjects.BoxJammer2.getY(), SurfaceObjects.BoxJammer2.getWidth(), SurfaceObjects.BoxJammer2.getHeight()); // BoxJammer2
        shapeRenderer.line(surfaceRect.getX(),surfaceRect.getY(),surfaceRect.getWidth(),surfaceRect.getY());
        // ( X,Y,WIDTH, HEIGHT ) POSITION 300 TO 300+15 LEFT TO RIGHT AND BOTTOM TO TOP
        shapeRenderer.end();

//        airStands
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(airStands.getX(), airStands.getY(),airStands.width,airStands.height);
//        shapeRenderer.line(SurfaceObjects.BoxJammer1.getX(),60f,100,80f);
        shapeRenderer.line(player.getX(),player.getY(),player.getX(),Math.abs(MainGame.jumptime));
        shapeRenderer.end();
        this.myshapes();
        this.level1draw();
        
        
//        here we update jammers stability
//        static jammers could be on constructor because they are not going to destroy like enemy

//        System.out.println(all_shapes.size());
        
    }

   private void myshapes () {
	   shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//       float x1 = Math.abs(Player.PlayerX-120);
//       float x2 = Math.abs(Player.PlayerX+120);
       shapeRenderer.line(Player.eyex1,Player.PlayerY+(Shapes.player.getHeight()/2),Player.eyex2,Player.PlayerY+(Shapes.player.getHeight()/2));
       shapeRenderer.end();
   }
   private void level1draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(MainGame.level1.airStands1.getX(),MainGame.level1.airStands1.getY(),MainGame.level1.airStands1.getWidth(),MainGame.level1.airStands1.getHeight());
        shapeRenderer.rect(MainGame.level1.airStands2.getX(),MainGame.level1.airStands2.getY(),MainGame.level1.airStands2.getWidth(),MainGame.level1.airStands2.getHeight());
       shapeRenderer.rect(MainGame.level1.airStands3.getX(),MainGame.level1.airStands3.getY(),MainGame.level1.airStands3.getWidth(),MainGame.level1.airStands3.getHeight());
       shapeRenderer.rect(MainGame.level1.airStands4.getX(),MainGame.level1.airStands4.getY(),MainGame.level1.airStands4.getWidth(),MainGame.level1.airStands4.getHeight());

       shapeRenderer.setColor(Color.BLACK);
       shapeRenderer.rect(MainGame.enemy.Henenmy.enemyrecta.getX(),MainGame.enemy.Henenmy.enemyrecta.getY(),MainGame.enemy.Henenmy.enemyrecta.getWidth(),MainGame.enemy.Henenmy.enemyrecta.getHeight());
        shapeRenderer.end();
   }






//    left and right collision
    public static boolean check_collision() {
    	for (int i=0;i<all_shapes.size();i++) {
    		if(player.overlaps(all_shapes.get(i))) {		
        		if(Player.PlayerY<(all_shapes.get(i).getY()+all_shapes.get(i).getHeight()-2)){
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
    	if(player.overlaps(all_shapes.get(i)) || player.overlaps(surfaceRect)) {
    		if(Player.PlayerY<(all_shapes.get(i).getY()+all_shapes.get(i).getHeight()+10)){
    			return true;
    			}
    	}
    	}
    	return false;    	
    }
    public static boolean check_collision_surface(Rectangle R) {

            if(player.overlaps(R)) {
                if(Player.PlayerY>(R.getY()+ player.getHeight()+1)){
                    return true;
                }
            }

        return false;
    }
    public static boolean check_collision_surface(Rectangle R,Rectangle X) {

        if (R.overlaps(X)) {
            return true;
        }
        return false;
    }
    }
//            