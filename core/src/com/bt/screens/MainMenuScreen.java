package com.bt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bt.handlers.AssetLoader;
import com.bt.handlers.ButtonHandler;
import com.bt.handlers.ScreenSwitchHandler;
import com.bt.ui.Button;
import com.bt.ui.Label;
import com.bt.vars.Var;

/**
 * Created by Bogdan on 5/8/2015.
 */
public class MainMenuScreen extends Screen{

    private SpriteBatch batch;
    private BitmapFont font;
    private Label label;
    private Button playButton;
    private Button exitButton;
    private Button settingsButton;
    private OrthographicCamera camera = null;
    private int lineHeight = 0;

    public MainMenuScreen(){
        batch  = new SpriteBatch();
        font = new BitmapFont();
        lineHeight = Math.round(2.5f * font.getCapHeight());
        label = new Label("Matching Tiles", font);
        playButton = new Button("Play", font, new ScreenSwitchHandler(ScreenState.GAME));
        settingsButton = new Button("Settings", font, new ScreenSwitchHandler(ScreenState.SETTINGS));
        exitButton = new Button("Exit", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    AssetLoader.getClickSound().play();
                    Gdx.app.exit();
                }
            }
        });
    }


    @Override
    public void render(float deltaTime) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(AssetLoader.getBackground(), 0, 0, Var.WIDTH, Var.HEIGHT);
        label.draw(batch);
        playButton.draw(batch, camera);
        settingsButton.draw(batch, camera);
        exitButton.draw(batch, camera);
        batch.end();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        batch.begin();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        batch.setProjectionMatrix(camera.combined);
        int centerX = width / 2;
        int centerY = height / 2;
        label.setX(centerX - label.getWidth() / 2);
        label.setY(centerY + 3 * lineHeight);
        playButton.setX(centerX - playButton.getWidth() / 2);
        playButton.setY(centerY + lineHeight);
        settingsButton.setX(centerX - settingsButton.getWidth() / 2);
        settingsButton.setY(centerY);
        exitButton.setX(centerX - exitButton.getWidth() / 2);
        exitButton.setY(centerY - lineHeight);
        batch.end();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        AssetLoader.dispose();
    }
}
