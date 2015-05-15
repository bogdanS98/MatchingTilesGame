package com.bt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.bt.handlers.AssetLoader;
import com.bt.handlers.ButtonHandler;
import com.bt.handlers.ScreenSwitchHandler;
import com.bt.ui.Button;
import com.bt.ui.Label;
import com.bt.ui.Tile;
import com.bt.vars.Var;

/**
 * Created by Mihai on 5/8/2015.
 */
public class GameScreen extends Screen {

    private Tile[][] tiles;
    private int tileSize;
    private int colorNum = Var.max_color;
    private int margin;
    private Button backButton;
    public static BitmapFont font;
    private SpriteBatch batch;
    private int boardHeight;
    boolean release;
    private int maxLevel;
    private int crtLevel;
    private int difDim;
    private Array<Tile> finishedTiles;
    private boolean displaySol;
    private float timer;
    private boolean animationPlaying;
    private boolean selected;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button insaneButton;
    private int deltaButton;
    private Label label;
    private float endTimer;
    private float score;
    private float upScore;
    private Label scoreLabel;
    private int lineHeight = 0;


    public GameScreen() {

        lineHeight = Math.round(2.5f * font.getCapHeight());
        deltaButton = 70;
        scoreLabel = new Label("Score : " + 0, font);
        label = new Label("Choose your difficulty", font);
        easyButton = new Button("EASY", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    easyButton.setClickColor();
                    if(SettingsScreen.isSoundOn())AssetLoader.getClickSound().play();
                    selected = true;
                    difDim = Var.easy_dim;
                    maxLevel = Var.easy_maxLevel;
                    createLevel();
                    createFinished(3 + crtLevel / difDim, 3 + crtLevel / (difDim - 1));
                }
            }
        });
        easyButton.setX(Var.WIDTH / 2 - easyButton.getWidth() / 2);
        easyButton.setY(Var.HEIGHT / 2 + deltaButton);
        mediumButton = new Button("MEDIUM", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    mediumButton.setClickColor();
                    if(SettingsScreen.isSoundOn())AssetLoader.getClickSound().play();
                    selected = true;
                    difDim = Var.medium_dim;
                    maxLevel = Var.medium_maxLevel;
                    createLevel();
                    createFinished(3 + crtLevel / difDim, 3 + crtLevel / (difDim - 1));
                }
            }
        });
        mediumButton.setX(Var.WIDTH / 2 - mediumButton.getWidth() / 2);
        mediumButton.setY(Var.HEIGHT / 2 + deltaButton / 2);
        hardButton = new Button("HARD", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    hardButton.setClickColor();
                    if(SettingsScreen.isSoundOn())AssetLoader.getClickSound().play();
                    selected = true;
                    difDim = Var.hard_dim;
                    maxLevel = Var.hard_maxLevel;
                    createLevel();
                    createFinished(3 + crtLevel / difDim, 3 + crtLevel / (difDim - 1));
                }
            }
        });
        hardButton.setX(Var.WIDTH / 2 - hardButton.getWidth() / 2);
        hardButton.setY(Var.HEIGHT / 2);
        insaneButton = new Button("INSANE", font, new ButtonHandler() {
            @Override
            public void OnClick() {
                if(Gdx.input.justTouched()){
                    insaneButton.setClickColor();
                    if(SettingsScreen.isSoundOn())AssetLoader.getClickSound().play();
                    selected = true;
                    difDim = Var.insane_dim;
                    maxLevel = Var.insane_maxLevel;
                    createLevel();
                    createFinished(3 + crtLevel / difDim, 3 + crtLevel / (difDim - 1));
                }
            }
        });
        insaneButton.setX(Var.WIDTH / 2 - insaneButton.getWidth() / 2);
        insaneButton.setY(Var.HEIGHT / 2 - deltaButton / 2);

        backButton = new Button("Back", font, new ScreenSwitchHandler(ScreenState.MAIN_MENU){
            @Override
            public void OnClick() {
                super.OnClick();
                if(Gdx.input.justTouched()){
                    backButton.setClickColor();
                }
            }
        });
        crtLevel = 1;
        finishedTiles = new Array<Tile>();
        selected = false;
        score = 0;
    }

    public void createLevel() {

        endTimer = difDim * 4;
        tiles = new Tile[difDim][difDim];
        tileSize = Var.WIDTH / tiles.length;
        boardHeight = tileSize * tiles.length;
        margin = (Var.HEIGHT - boardHeight) / 2;
        release = true;
        animationPlaying = false;
        displaySol = true;
        timer = 0;

        for(int row = 0; row < tiles.length; row++) {
            for(int col = 0; col < tiles.length; col++) {
                tiles[row][col] =
                        new Tile(col * tileSize + tileSize / 2,
                                row * tileSize + tileSize / 2 + margin,
                                tileSize, tileSize, colorNum);
                tiles[row][col].setMaxCol(3 + crtLevel / difDim);
            }
        }

        for(int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length - row; col++) {
                tiles[row][col].setTimer(-(tiles.length - col + row ) * 0.1f);
                tiles[tiles.length - row - 1][tiles.length - col - 1].setTimer(-(tiles.length - col + row ) * 0.1f);
            }
        }
    }

    public void createFinished(int clrNum, int levelDim) {
        finishedTiles.clear();
        for(int i = 1; i <= levelDim; ++i) {
            int row = 0;
            int col = 0;
            do{
                row = MathUtils.random(tiles.length - 1);
                col = MathUtils.random(tiles.length - 1);
            }while(finishedTiles.contains(tiles[row][col], true));
            int clr = MathUtils.random(1, clrNum);
            tiles[row][col].setCol(clr);
            tiles[row][col].setSolCol(clr);
            finishedTiles.add(tiles[row][col]);
        }
    }

    public boolean isFinished() {

        if(displaySol) { return false; }
        for(int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                if(!(tiles[row][col].getSolCol() == tiles[row][col].getCol())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void solPlayed(float deltaTime) {

        if(!animationPlaying && displaySol && timer < 3) {
            timer += deltaTime;
            if(timer > 3) {
                displaySol = false;
                for(int i = 0; i < finishedTiles.size; i++) {
                    finishedTiles.get(i).setCol(0);
                }
            }
        }

    }

    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(AssetLoader.getBackground(), 0, 0, Var.WIDTH, Var.HEIGHT);
        backButton.draw(batch, camera);
        if(!selected){
            batch.draw(AssetLoader.getBackground(), 0, 0, Var.WIDTH, Var.HEIGHT);
            easyButton.draw(batch, camera);
            mediumButton.draw(batch, camera);
            hardButton.draw(batch, camera);
            insaneButton.draw(batch, camera);
            label.draw(batch);
        }
        else {
            updateScore(deltaTime);
            scoreLabel.setCaption("Score: " + "  " + (int)score);
            scoreLabel.draw(batch);
            update(deltaTime);
            animationPlaying = false;
            for (int row = 0; row < tiles.length; row++) {
                for (int col = 0; col < tiles.length; col++) {
                    tiles[row][col].render(batch);
                    if (tiles[row][col].animationPlaying()) {
                        animationPlaying = true;
                    }
                }
            }
        }
        batch.end();
    }

    public void update(float deltaTime) {
        endTimer -= deltaTime;
        solPlayed(deltaTime);
        if(!Gdx.input.isTouched()) { release = true; }
        if(!displaySol && Gdx.input.isTouched() && release && !isFinished()) {
            if(Var.WIDTH - Gdx.input.getX() > 0 && Var.WIDTH - Gdx.input.getX() < Var.WIDTH && Var.HEIGHT - Gdx.input.getY() > margin && Var.HEIGHT - Gdx.input.getY() < margin + boardHeight) {
                int row = (int) ((Var.HEIGHT - Gdx.input.getY() - margin) / tileSize);
                int col = (int) (Gdx.input.getX() / tileSize);
                if(SettingsScreen.isSoundOn()) { AssetLoader.getTileSound().play(); }
                tiles[row][col].updateCol();
                tiles[row][col].render(batch);
                if(isFinished() && crtLevel < maxLevel) {
                    upScore = endTimer * 10;
                    if(SettingsScreen.isSoundOn()) { AssetLoader.getGoodSound().play(); }
                    crtLevel++;
                    createLevel();
                    createFinished(3 + crtLevel / 6, 3 + crtLevel / 5);
                }
                else if(isFinished()){
                    //game over
                }
            }
            release = false;
        }
        for(int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles.length; col++) {
                tiles[row][col].update(deltaTime);
            }
        }
        if(endTimer <= 0){
            if(SettingsScreen.isSoundOn()) { AssetLoader.getBadSound().play(); }
            crtLevel++;
            createLevel();
            createFinished(3 + crtLevel / difDim, 3 + crtLevel / (difDim - 1) + difDim / 5);
        }

    }

    public void updateScore(float deltaTime) {
        if(upScore <= 0) {
            upScore = 0;
            return;
        }
        score += deltaTime * 100;
        upScore -= deltaTime * 100;
    }


    @Override
    public void resize(int width, int height) {
        int centerX = width / 2;
        int centerY = height / 2;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Var.WIDTH, Var.HEIGHT);
        label.setX(centerX - label.getWidth() / 2);
        label.setY(centerY + 3 * lineHeight);
        scoreLabel.setX(centerX - (scoreLabel.getWidth()) / 2);
        scoreLabel.setY(Var.HEIGHT - lineHeight);
        backButton.setX(centerX - (backButton.getWidth() * 4));
        backButton.setY(Var.HEIGHT - lineHeight);
        easyButton.setX(centerX - easyButton.getWidth() / 2);
        easyButton.setY(centerY + lineHeight);
        mediumButton.setX(centerX - mediumButton.getWidth() / 2);
        mediumButton.setY(centerY);
        hardButton.setX(centerX - hardButton.getWidth() / 2);
        hardButton.setY(centerY - lineHeight);
        insaneButton.setX(centerX - insaneButton.getWidth() / 2);
        insaneButton.setY(centerY - 2 * lineHeight);
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
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
