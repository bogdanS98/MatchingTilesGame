package com.bt.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bt.ui.Button;
import com.bt.ui.Label;

/**
 * Created by Bogdan on 5/9/2015.
 */
public class ScoreScreen extends Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private Label label;
    private Button playButton;
    private Button exitButton;
    private Button settingsButton;
    private OrthographicCamera camera = null;
    private int lineHeight = 0;

    public ScoreScreen(){
        
    }
}
