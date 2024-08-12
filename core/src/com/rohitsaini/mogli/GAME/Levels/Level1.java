package com.rohitsaini.mogli.GAME.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.Controler.Controlls;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;
import com.rohitsaini.mogli.GAME.player.Player;

public class Level1 {
    public Rectangle airStands1;
    public Rectangle airStands2;
    public Rectangle airStands3;
    public Rectangle airStands4;
    public Rectangle LevelWall;
    public float LevelWallHeight;
    public static byte eyeId;
    public static boolean triggered;


    public Level1() {
        airStands1 = new Rectangle(0, 180, 20, 5);
        airStands2 = new Rectangle(50, 100, 20, 5);
        LevelWall = new Rectangle(325,0,1,400);

        airStands3 = new Rectangle(300, 200f,50,15);
        airStands4 = new Rectangle(55, 200f,Gdx.graphics.getWidth(),15);

        LevelWallHeight = 200;
        triggered=false;

    }
    public boolean wallCollision(){
        if (Shapes.player.overlaps(LevelWall)){
        if (Shapes.player.overlaps(LevelWall) && Shapes.player.getY()<LevelWallHeight){return false;}

    }
        return true;
    }



    public void render(){
        if (Player.PlayerX>LevelWall.getX()&& Player.PlayerX<(LevelWall.getX()+100)
        ){
            System.out.println(Controlls.cameratime+" ,camera stop "+LevelWall.getX());
            Player.movecamera=false;

        }else{

            Player.movecamera=true;


        }
    }
}