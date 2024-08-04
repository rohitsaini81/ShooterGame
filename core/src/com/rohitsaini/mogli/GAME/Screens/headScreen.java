package com.rohitsaini.mogli.GAME.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.textra.TextraLabel;
import com.github.tommyettinger.textra.TypingLabel;
import com.rohitsaini.mogli.Mogali;

public class headScreen implements Screen {

    TypingLabel lablel;
    TypingLabel lablel2;
    Mogali game;
    Stage stage;
    Table root;
    public headScreen(Mogali game){
        Viewport viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        this.game = game;
        stage = new Stage(viewport);
        root = new Table();
        root.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        root.setFillParent(true);
        stage.addActor(root);
        lablel= new TypingLabel();
        lablel.setText("Hii This Is Rohit Saini, \n Here is a Message for you \n please read it carefully \n Belowd");
        lablel.setSize(100,100);
        lablel.setColor(Color.BLUE);
        lablel.setX(100);

        lablel2= new TypingLabel();
        lablel2.setText("RAAM RAAM LADDR KE KYA THA GADDI ROKEGA");
        lablel2.setSize(100,100);
        lablel2.setColor(Color.PINK);
        lablel2.setX(0);
        lablel2.setY(0);

        root.add(lablel).setActorX(Gdx.graphics.getWidth()/2);


    }
    @Override
    public void show() {

    }
float counter=0;
    float triger=0;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
//        game.batch.draw(game.img,100,100,100,100);
        System.out.println("vallue of counter is : "+counter);
        if (counter<=-100){
            counter=Math.abs(counter);

        }else {counter-=Gdx.graphics.getDeltaTime()*50;triger=counter;}
        lablel.setX(Math.abs(counter));
        lablel.setSize(100,100);
        lablel2.setY(50);
        lablel2.setX(20);

        if (triger<-50){
            root.add(lablel2);
        }
        stage.act();
        stage.draw();
        game.batch.end();

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
