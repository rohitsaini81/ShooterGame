package io.github.modi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FlappyBirdScreen implements Screen {

    // Camera + Viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    // Renderers
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    // Textures
    private Texture background;
    private Texture birdTexture;
    private Texture pipeTopTexture;
    private Texture pipeBottomTexture;
    private Texture gameOverTexture;

    // Sounds
    private Sound flapSound;
    private Music modi_song;

    // Font
    private BitmapFont font;
    private GlyphLayout glyphLayout = new GlyphLayout();

    // Bird physics
    private float birdX, birdY;
    private float birdVelocity;
    private float GRAVITY;
    private float FLAP_STRENGTH;
    private float birdWidth, birdHeight;
    private int score = 0;
    private boolean gameOver = false;

    // Pipes
    private static class Pipe {
        float x;
        float gapY;
        float GAP_HEIGHT;
        float width;
        float topHeight;
        float bottomHeight;
        float SPEED;
        boolean counted = false;
    }

    private final Array<Pipe> pipes = new Array<>();
    private float pipeSpawnTimer = 0f;
    private final float PIPE_SPAWN_INTERVAL = 1.7f;

    @Override
    public void show() {
        // Full-screen world based on device resolution
        float WORLD_HEIGHT = 800f;
        float aspect = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
        float WORLD_WIDTH = WORLD_HEIGHT * aspect;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        camera.update();

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Load assets
        background = new Texture("background-day.png");
        birdTexture = new Texture("bird.png");
        pipeTopTexture = new Texture("pipe-top.png");
        pipeBottomTexture = new Texture("pipe-top.png");
        gameOverTexture = new Texture("niga_baba_modi.png");
        flapSound = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));
        modi_song = Gdx.audio.newMusic(Gdx.files.internal("modi_song.mp3"));
        modi_song.setLooping(true);

        // Font
        font = new BitmapFont();
        font.getData().setScale(2.5f);
        font.setColor(Color.WHITE);

        // Dynamic scale based on world height
        birdWidth = WORLD_WIDTH * 0.1f;
        birdHeight = WORLD_HEIGHT * 0.12f;

        // Physics scale
        GRAVITY = -WORLD_HEIGHT * 1.8f;
        FLAP_STRENGTH = WORLD_HEIGHT * 0.52f;

        birdX = WORLD_WIDTH * 0.25f;

        resetGame();
    }

    private void resetGame() {
        birdY = viewport.getWorldHeight() / 2f;
        birdVelocity = 0f;
        pipes.clear();
        pipeSpawnTimer = 0f;
        score = 0;
        modi_song.stop();
        modi_song.play();
        gameOver = false;
    }

    @Override
    public void render(float delta) {
        update(delta);

        ScreenUtils.clear(0.3f, 0.7f, 1f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        // Draw pipes
        for (Pipe p : pipes) {
            float bottomY = p.gapY - p.GAP_HEIGHT - p.bottomHeight;
            float topY = p.gapY + p.GAP_HEIGHT;
            batch.draw(pipeBottomTexture, p.x, bottomY, p.width, p.bottomHeight);
            batch.draw(pipeTopTexture, p.x, topY, p.width, p.topHeight);
        }

        // Draw bird
        batch.draw(birdTexture, birdX, birdY, birdWidth, birdHeight);

        // Score
        String scoreText = String.valueOf(score);
        glyphLayout.setText(font, scoreText);
        font.draw(batch, scoreText,
            (viewport.getWorldWidth() - glyphLayout.width) / 2f,
            viewport.getWorldHeight() - 60);

        // Game over
        if (gameOver) {
            modi_song.stop();
            String msg = "GAME OVER - Tap to Restart";
            glyphLayout.setText(font, msg);
            font.draw(batch, msg,
                (viewport.getWorldWidth() - glyphLayout.width) / 2f,
                viewport.getWorldHeight() / 2f + 50);

            batch.draw(gameOverTexture,
                (viewport.getWorldWidth() - 150) / 2f,
                200, 300, 300);
        }

        batch.end();

        // Debug rectangles
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(birdX, birdY, birdWidth, birdHeight);
        for (Pipe p : pipes) {
            float bottomY = p.gapY - p.GAP_HEIGHT - p.bottomHeight;
            float topY = p.gapY + p.GAP_HEIGHT;
            shapeRenderer.rect(p.x, bottomY, p.width, p.bottomHeight);
            shapeRenderer.rect(p.x, topY, p.width, p.topHeight);
        }
        shapeRenderer.end();
    }

    private void update(float delta) {
        if (gameOver) {
            if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                resetGame();
            }
            return;
        }

        // Bird physics
        birdVelocity += GRAVITY * delta;
        birdY += birdVelocity * delta;

        // Flap
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            birdVelocity = FLAP_STRENGTH;
            flapSound.play(0.8f);
        }

        // Screen bounds
        if (birdY < 0 || birdY + birdHeight > viewport.getWorldHeight()) {
            gameOver = true;
        }

        // Spawn pipes
        pipeSpawnTimer += delta;
        if (pipeSpawnTimer >= PIPE_SPAWN_INTERVAL) {
            spawnPipe();
            pipeSpawnTimer = 0f;
        }

        // Update pipes
        Rectangle birdRect = new Rectangle(birdX, birdY, birdWidth, birdHeight);
        for (int i = pipes.size - 1; i >= 0; i--) {
            Pipe p = pipes.get(i);
            p.x -= p.SPEED * delta;

            if (!p.counted && p.x + p.width < birdX) {
                score++;
                p.counted = true;
            }

            float bottomY = p.gapY - p.GAP_HEIGHT - p.bottomHeight;
            float topY = p.gapY + p.GAP_HEIGHT;

            Rectangle bottomRect = new Rectangle(p.x, bottomY, p.width, p.bottomHeight);
            Rectangle topRect = new Rectangle(p.x, topY, p.width, p.topHeight);

            if (birdRect.overlaps(topRect) || birdRect.overlaps(bottomRect)) {
                gameOver = true;
            }

            if (p.x + p.width < 0) pipes.removeIndex(i);
        }
    }

    private void spawnPipe() {
        Pipe p = new Pipe();

        // Scale pipes relative to screen size
        float worldW = viewport.getWorldWidth();
        float worldH = viewport.getWorldHeight();

        p.width = worldW * 0.15f;
        p.topHeight = worldH * 0.4f;
        p.bottomHeight = worldH * 0.4f;
        p.GAP_HEIGHT = worldH * 0.12f;
        p.SPEED = worldW * 0.4f;

        p.x = worldW + 100;
        p.gapY = MathUtils.random(worldH * 0.35f, worldH * 0.75f);

        pipes.add(p);
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
        shapeRenderer.dispose();
        background.dispose();
        birdTexture.dispose();
        pipeTopTexture.dispose();
        pipeBottomTexture.dispose();
        font.dispose();
        flapSound.dispose();
        modi_song.dispose();
    }
}
