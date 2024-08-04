package com.rohitsaini.mogli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rohitsaini.mogli.GAME.Screens.MainGame;
import com.rohitsaini.mogli.GAME.Screens.Menu_Screen;
import com.rohitsaini.mogli.GAME.Screens.headScreen;


public class Mogali extends Game {
	public static SpriteBatch batch;
	public Texture img;
	public static int GAME_WORLD_WIDTH =480;
	public static int GAME_WORLD_HEIGHT=700;
	public static BitmapFont font;
	Menu_Screen Menu;
	headScreen screen2;
	MainGame Game;
	public static float stateTime;


	
	@Override
	public void create () {
		img= new Texture("sand.png");
		batch = new SpriteBatch();
//		Menu= new Menu_Screen(this);
//		this.setScreen(Menu);
		Game= new MainGame(this);
//		screen2 = new headScreen(this);
		this.setScreen(Game);
	}

	@Override
	public void render () {
super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();

	}
}
