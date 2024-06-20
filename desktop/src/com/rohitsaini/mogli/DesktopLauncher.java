package com.rohitsaini.mogli;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.rohitsaini.mogli.Mogali;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("rohitsaini");
		config.setWindowedMode(Mogali.GAME_WORLD_WIDTH,Mogali.GAME_WORLD_HEIGHT);
		new Lwjgl3Application(new Mogali(), config);
	}
}
