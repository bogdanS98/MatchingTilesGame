package com.bt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
 * Created by Bogdan on 5/9/2015.
 */
public class SettingsScreen extends Screen {

    public static Preferences prefs = Gdx.app.getPreferences("MatchingTiles-preferences");
    private SpriteBatch batch;
    public static BitmapFont font;
    private Label label = null;
    private Button backButton;
    private Button soundButton;
    private Button musicButton;
    private Var var = new Var();
    private OrthographicCamera camera = null;
    private int lineHeight = 0;
    private static boolean soundOn = prefs.getBoolean("soundOn");
    private static boolean musicOn =  prefs.getBoolean("musicOn");;

    public SettingsScreen() {

        batch  = new SpriteBatch();
        soundButton = new Button(soundOn ? "Sound ON" : "Sound OFF", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    soundButton.setClickColor();
                    if(soundOn == true){
                        soundOn = false;
                        soundButton.setCaption("Sound OFF");
                        AssetLoader.getClickSound().play();
                        prefs.putBoolean("soundOn", false);
                        prefs.flush();
                    }
                    else {
                        soundOn = true;
                        soundButton.setCaption("Sound ON");
                        prefs.putBoolean("soundOn", true);
                        prefs.flush();
                    }
                }
            }
        });
        musicButton = new Button(musicOn ? "Music ON" : "Music OFF", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    musicButton.setClickColor();
                    if(musicOn == true){
                        musicOn = false;
                        musicButton.setCaption("Music OFF");
                        prefs.putBoolean("musicOn", false);
                        prefs.flush();
                        if(soundOn)AssetLoader.getClickSound().play();
                        AssetLoader.getMusic().stop();
                    }
                    else {
                        musicOn = true;
                        musicButton.setCaption("Music ON");
                        prefs.putBoolean("musicOn", true);
                        prefs.flush();
                        if(soundOn)AssetLoader.getClickSound().play();
                        AssetLoader.getMusic().setVolume(0.1f);
                        AssetLoader.getMusic().setLooping(true);
                        AssetLoader.getMusic().play();
                    }
               }
            }
        });
        backButton = new Button("Back", font, new ScreenSwitchHandler(ScreenState.MAIN_MENU){
            @Override
            public void OnClick() {
                super.OnClick();
                if(Gdx.input.justTouched()){
                    backButton.setClickColor();
                }
            }
        });
        lineHeight = Math.round(2.5f * font.getCapHeight());
        label = new Label("Settings", font);
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(AssetLoader.getBackground(), 0, 0, Var.WIDTH, Var.HEIGHT);
        label.draw(batch);
        backButton.draw(batch, camera);
        soundButton.draw(batch, camera);
        musicButton.draw(batch, camera);
        batch.end();
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
        soundButton.setX(centerX - soundButton.getWidth() / 2);
        soundButton.setY(centerY);
        musicButton.setX(centerX - musicButton.getWidth() / 2);
        musicButton.setY(centerY - lineHeight);
        backButton.setX(centerX - backButton.getWidth() / 2);
        backButton.setY(centerY - 3 * lineHeight);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    public static boolean isSoundOn(){
        return soundOn;
    }

    @Override
    public void dispose() {
        batch.dispose();
        AssetLoader.dispose();
    }
}
