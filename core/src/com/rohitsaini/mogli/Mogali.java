package com.rohitsaini.mogli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rohitsaini.mogli.GAME.MainGame;
import com.rohitsaini.mogli.GAME.Menu_Screen;


public class Mogali extends Game {
	public static SpriteBatch batch;
	public Texture img;
	public static int GAME_WORLD_WIDTH =480;
	public static int GAME_WORLD_HEIGHT=700;
	public static BitmapFont font;
	Menu_Screen Menu;
	MainGame Game;
	public static float stateTime;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
//		Menu= new Menu_Screen(this);
//		this.setScreen(Menu);
		Game= new MainGame(this);
		this.setScreen(Game);
	}

	@Override
	public void render () {
super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		font.dispose();

	}
}
