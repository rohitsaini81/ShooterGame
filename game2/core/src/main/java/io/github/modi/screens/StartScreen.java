package io.github.modi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.modi.Main;

public class StartScreen implements Screen {

    private final Main game;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Texture background;
    private Texture logoTexture;

    private BitmapFont titleFont;
    private BitmapFont infoFont;
    private GlyphLayout layout = new GlyphLayout();

    private Music bgMusic;

    private float pulseTime = 0f; // for pulsing "Tap to Start"

    public StartScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // World size similar to game
        float WORLD_HEIGHT = 800f;
        float aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float WORLD_WIDTH = WORLD_HEIGHT * aspect;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        camera.update();

        batch = new SpriteBatch();

        // Background & logo
        background = new Texture("background-day.png");
        logoTexture = new Texture("bird.png"); // reuse bird as small logo

        // Fonts
        titleFont = new BitmapFont();
        titleFont.getData().setScale(3.5f);
        titleFont.setColor(Color.YELLOW);

        infoFont = new BitmapFont();
        infoFont.getData().setScale(2f);
        infoFont.setColor(Color.WHITE);

        // Background music
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("modi_song.mp3"));
        bgMusic.setLooping(true);
        bgMusic.setVolume(0.5f);
        bgMusic.play();
    }

    @Override
    public void render(float delta) {
        pulseTime += delta;

        ScreenUtils.clear(0.3f, 0.7f, 1f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Draw background full screen
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Game title
        String title = "FLAPPY MODI BIRD";
        layout.setText(titleFont, title);
        titleFont.draw(batch, title,
            (viewport.getWorldWidth() - layout.width) / 2f,
            viewport.getWorldHeight() * 0.75f);

        // Center logo (bird image)
        float logoSize = viewport.getWorldHeight() * 0.12f;
        batch.draw(
            logoTexture,
            (viewport.getWorldWidth() - logoSize) / 2f,
            viewport.getWorldHeight() * 0.5f,
            logoSize,
            logoSize
        );

        // Pulsing "Tap to Start"
        float alpha = 0.5f + 0.5f * MathUtils.sin(pulseTime * 3f);
        infoFont.setColor(1, 1, 1, alpha);

        String tapText = "Tap anywhere to start";
        layout.setText(infoFont, tapText);
        infoFont.draw(batch, tapText,
            (viewport.getWorldWidth() - layout.width) / 2f,
            viewport.getWorldHeight() * 0.3f);

        batch.end();

        // Start game on touch or key press
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bgMusic.stop();
            game.setScreen(new FlappyBirdScreen());
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        logoTexture.dispose();
        titleFont.dispose();
        infoFont.dispose();
        bgMusic.dispose();
    }
}
