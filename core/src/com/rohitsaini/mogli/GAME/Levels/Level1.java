package com.rohitsaini.mogli.GAME.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.rohitsaini.mogli.GAME.DrawShapes.Shapes;

public class Level1 {
    public Rectangle airStands1;
    public Rectangle airStands2;
    public Rectangle airStands3;
    public Rectangle airStands4;
    public Rectangle LevelWall;
    public float LevelWallHeight;


    public Level1() {
        airStands1 = new Rectangle(0, 180, 20, 5);
        airStands2 = new Rectangle(50, 100, 20, 5);
        LevelWall = new Rectangle(325,0,1,300);

        airStands3 = new Rectangle(300, 200f,50,15);

        LevelWallHeight = 500f;

    }
    public boolean wallCollision(){
        if (Shapes.player.overlaps(LevelWall) && Shapes.player.getY()<LevelWallHeight){return false;}
        return true;
    }
    public void render(){

    }
}