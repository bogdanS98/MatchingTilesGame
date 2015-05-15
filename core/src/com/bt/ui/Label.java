package com.bt.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bogdan on 5/9/2015.
 */
public class Label {

    private static final Color COLOR = new Color(1f, 1f, 1f, 1f);
    private String caption = null;
    private static BitmapFont font = null;
    private int x;
    private int y;
    private int width;
    private int height;

    public Label(String caption, BitmapFont font , int x, int y){
        this.caption = caption;
        this.font = font;
        this.x = x;
        this.y = y;
        setDimensions();
    }

    public Label(String caption, BitmapFont font){
        this(caption, font, 0, 0);
    }

    private void setDimensions() {
        com.badlogic.gdx.graphics.g2d.GlyphLayout layout = new com.badlogic.gdx.graphics.g2d.GlyphLayout();
        layout.setText(font, caption);
        width = Math.round(layout.width);
        height = Math.round(layout.height);
    }

    public void draw(SpriteBatch batch){
        Color color = font.getColor();
        font.setColor(COLOR);
        font.draw(batch, caption, x, y);
        font.setColor(color);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
        setDimensions();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
