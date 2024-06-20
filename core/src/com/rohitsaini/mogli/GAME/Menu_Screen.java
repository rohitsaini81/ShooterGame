package com.rohitsaini.mogli.GAME;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rohitsaini.mogli.Mogali;



public class Menu_Screen implements Screen {
    Mogali game;
    Texture TITLE_MYNAME;


    public Menu_Screen(Mogali game){
        this.game = game;
        if (TITLE_MYNAME==null){
            TITLE_MYNAME=new Texture("rohitsainipixel.png");
            game.font = new BitmapFont();
        }
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 5, 0, 1);
        Mogali.batch.begin();
        Player.PlayerX=Gdx.input.getX();
        Player.PlayerY=Gdx.input.getY();
        Mogali.font.draw(Mogali.batch,"X:" +Player.PlayerX+"Y:"+Player.PlayerY,180,500);
        Mogali.batch.draw(TITLE_MYNAME, 0, 10);
        Mogali.batch.end();
//        Screen change after click
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            this.dispose();
            game.setScreen(new MainGame(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
