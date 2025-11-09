package io.github.modi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.modi.screens.FlappyBirdScreen;
import io.github.modi.screens.StartScreen;

public class Main extends Game {
    FlappyBirdScreen flappyBirdScreen;


    @Override
    public void create() {
        flappyBirdScreen = new FlappyBirdScreen();

        setScreen(new StartScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {

    }
}
