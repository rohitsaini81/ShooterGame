package com.rohitsaini.mogli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rohitsaini.mogli.GAME.Controler.InputManger;
import com.rohitsaini.mogli.GAME.Screens.MainGame;
import com.rohitsaini.mogli.GAME.Screens.Menu_Screen;
import com.rohitsaini.mogli.GAME.Screens.headScreen;


public class Mogali extends Game {
	public static SpriteBatch batch;
	public Texture img;
	public static int GAME_WORLD_WIDTH =480;
	public static int GAME_WORLD_HEIGHT=700;
	public static BitmapFont font;
	public static Menu_Screen Menu;
	public static InputManger inputManger;
	headScreen screen2;
	MainGame Game;
	public static float stateTime;
	public static boolean GameisRunning;


	
	@Override
	public void create () {
		inputManger= new InputManger();
		img= new Texture("etc/sand.png");
		batch = new SpriteBatch();
		Menu= new Menu_Screen(this);
//		this.setScreen(Menu);
//		Game = new MainGame(this);
//		screen2 = new headScreen(this);
		this.setScreen(Menu);
		GameisRunning=true;
	}

	@Override
	public void render () {
//		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && super.getScreen()==Game){
//			this.setScreen(Menu);
//			this.getScreen().pause();
//			this is not working properly cause of menu screen
//			to resolve to check how menu exchanges screen
//		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			if (GameisRunning){
			GameisRunning=false;}
			else {
				GameisRunning=true;
			}
		}
//		System.out.println(GameisRunning);
//		else {
		super.render();
//		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();

	}
}
