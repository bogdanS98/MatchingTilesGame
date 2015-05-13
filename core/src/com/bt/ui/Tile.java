package com.bt.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bt.handlers.AssetLoader;

/**
 * Created by Mihai on 5/8/2015.
 */
public class Tile {

    private int x;
    private int y;
    private float width;
    private float height;
    private int colorNum;
    TextureRegion[] bright;
    TextureRegion[] dark;
    private boolean selected;
    private int clr;
    private float timer;
    private float maxTimer = 0.5f;
    private int maxWidth;
    private int maxHeight;
    private int solCol;
    private int maxCol;


    public Tile(int x, int y, int width, int height, int colorNum) {
        this.x = x;
        this.y = y;
        this.maxWidth = width - 10;
        this.maxHeight = height - 10;
        this.colorNum = colorNum;

        bright = new TextureRegion[colorNum];
        dark = new TextureRegion[colorNum];
        bright[0] = AssetLoader.getTiles().findRegion("brightgray");
        dark[0] = AssetLoader.getTiles().findRegion("darkgray");
        bright[1] = AssetLoader.getTiles().findRegion("brightorange");
        dark[1] = AssetLoader.getTiles().findRegion("darkorange");
        bright[2] = AssetLoader.getTiles().findRegion("brightblue");
        dark[2] = AssetLoader.getTiles().findRegion("darkblue");
        bright[3] = AssetLoader.getTiles().findRegion("brightred");
        dark[3] = AssetLoader.getTiles().findRegion("darkred");
        bright[4] = AssetLoader.getTiles().findRegion("brightgreen");
        dark[4] = AssetLoader.getTiles().findRegion("darkgreen");
        bright[5] = AssetLoader.getTiles().findRegion("brightyellow");
        dark[5] = AssetLoader.getTiles().findRegion("darkyellow");
        bright[6] = AssetLoader.getTiles().findRegion("brightpurple");
        dark[6] = AssetLoader.getTiles().findRegion("darkpurple");
        clr = 0;
        selected = false;
        solCol = 0;

    }

    public void setTimer(float t) {
        timer = t;
    }

    public void update(float dt) {
        if(width < maxWidth && height < maxHeight) {
            timer += dt;
            width = (timer / maxTimer) * maxWidth;
            height = (timer / maxTimer) * maxWidth;
            if(height > maxHeight) { height = maxHeight; }
            if(width > maxWidth) { width = maxWidth; }
            if(width < 0) width = 0;
            if(height < 0) height = 0;
        }
    }

    public boolean close(float dt) {
        width -= dt * maxWidth;
        height -= dt * maxHeight;
        if(height < 0 || width < 0) { width = height = 0; return true; }
        return false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(dark[clr], x - width / 2, y - height / 2, width, height);
    }

    public void updateCol() {
        clr++;
        if(clr > maxCol) { clr = 0; }
    }

    public void setCol(int clr) {
        this.clr = clr;
    }

    public int getCol() {
        return clr;
    }

    public void setSolCol(int clr) {
        solCol = clr;
    }

    public int getSolCol() {
        return solCol;
    }

    public void setMaxCol(int clr) {
        maxCol = clr;
    }

    public boolean animationPlaying() {
        return (timer < maxTimer);
    }


    public void isSelected(boolean s) {
        selected = s;
    }

}
