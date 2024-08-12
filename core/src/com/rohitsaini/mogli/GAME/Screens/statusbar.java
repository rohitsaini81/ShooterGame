package com.rohitsaini.mogli.GAME.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.textra.TypingLabel;
import com.rohitsaini.mogli.GAME.Variables;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;


public class statusbar {
    Stage stage;
    Table root;
    String Helath;
//    public static String PlayerXPosition;
    TypingLabel Health;
    public statusbar() {
        Health = new TypingLabel();
        Health.setText("0,0,0");
//        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage();

//	Gdx.input.setInputProcessor(stage);
        root = new Table();
//        stage.addActor(root);

        root.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        root.setFillParent(true);
        root.add(Health);

}
    public void render(){
//        states.set(0,"X:" +(int) Player.PlayerX+"Y:"+(int)Player.PlayerY);
//        states.set(1,"X:"+ Gdx.input.getX()+" ,Health:"+Player.PLAYER_HEALTH);
        this.Health.setX(Gdx.input.getX());

    }
}
