package com.rohitsaini.mogli.GAME.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.textra.TypingLabel;
import com.rohitsaini.mogli.GAME.player.Player;


public class statusbar {
    Stage stage;
    Table root;
    TypingLabel playerXlable;
    TypingLabel playerYlable;
    TypingLabel Health;
    TypingLabel Fps;
    public statusbar() {
        Health = new TypingLabel();
        playerXlable = new TypingLabel();
        playerYlable = new TypingLabel();
        Fps = new TypingLabel();
        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(viewport);

//	Gdx.input.setInputProcessor(stage);
        root = new Table();
        stage.addActor(root);
        root.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        root.setFillParent(true);
        root.add(Health);
        root.add(playerXlable);
        root.add(playerYlable);
        root.add(Fps);
//        root.row();

}
    public void render(){


        Health.setDefaultToken("PLAYER_HEALTH : "+Player.PLAYER_HEALTH);
        playerXlable.setDefaultToken("PLAYER X : "+Player.getX());
        playerYlable.setDefaultToken("PLAYER Y : "+Player.getY());
        Fps.setDefaultToken("FPS : "+Gdx.graphics.getFramesPerSecond());
        this.Health.setX(0);
        this.playerXlable.setX(0);
        this.playerYlable.setX(0);
        this.Fps.setX(0);

        this.Health.setY(Gdx.graphics.getHeight()-100);
        this.playerXlable.setY(Gdx.graphics.getHeight()-125);
        this.playerYlable.setY(Gdx.graphics.getHeight()-150);
        this.Fps.setY(Gdx.graphics.getHeight()-175);
        stage.act();
        stage.draw();




    }
}
