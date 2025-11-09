package com.rohitsaini.mogli.GAME.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rohitsaini.mogli.GAME.player.Player;
import com.rohitsaini.mogli.Mogali;



public class Menu_Screen implements Screen {
    Mogali game;
    Texture TITLE_MYNAME;
    Texture GAME_TITLE;
    Sound theme;


    public Menu_Screen(Mogali game){
        this.game = game;
        theme = Gdx.audio.newSound(Gdx.files.internal("playerSound/ezio_family.mp3"));
        if (TITLE_MYNAME==null){
            TITLE_MYNAME=new Texture("etc/rohitsainipixel.png");
            GAME_TITLE= new Texture("etc/assassins-creed-22704.png");
            game.font = new BitmapFont();
        }
    }



    @Override
    public void show() {
theme.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 5, 0, 1);
        Mogali.batch.begin();
        Player.PlayerX=Gdx.input.getX();
        Player.PlayerY=Gdx.input.getY();
        Mogali.font.draw(Mogali.batch,"X:" +Player.PlayerX+"Y:"+Player.PlayerY,180,500);
        Mogali.batch.draw(TITLE_MYNAME, 0, Gdx.graphics.getHeight()/1.5f);
        Mogali.batch.draw(GAME_TITLE,Gdx.graphics.getWidth()/2f-GAME_TITLE.getWidth()/2f, 100, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f);
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
        theme.dispose();
    }
}
