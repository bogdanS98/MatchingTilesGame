package com.bt.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.bt.handlers.ButtonHandler;

/**
 * Created by Bogdan on 5/9/2015.
 */
public class Button {

    private static final Color NORMAL_COLOR = new Color(1f, 1f, 1f, 0.7f);
    private static final Color CLICK_COLOR = new Color(0f, 1f, 0f, 1f);
    private String caption;
    private BitmapFont font;
    private ButtonHandler handler;
    private Rectangle bounds;

    private int x;
    private int y;
    private int width;
    private int height;


    public Button(String caption, BitmapFont font, ButtonHandler handler, int x, int y){
        this.caption = caption;
        this.font = font;
        this.x = x;
        this.y = y;
        this.handler = handler;
        setDimensions();
    }

    public Button(String caption, BitmapFont font, ButtonHandler handler) {
        this(caption, font, handler, 0, 0);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
        setDimensions();
    }


    public void draw(SpriteBatch batch, Camera camera) {
        Color color = font.getColor();
        Vector3 cursor = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(cursor);
        boolean isIntersected = bounds.contains(cursor.x, cursor.y);
        font.setColor(/*isIntersected ? CLICK_COLOR : */NORMAL_COLOR);
        font.draw(batch, caption, x, y);
        font.setColor(color);
        if(isIntersected && Gdx.input.isTouched()){
            handler.OnClick();
            font.setColor(CLICK_COLOR);
        }
    }

    private void setDimensions(){
        com.badlogic.gdx.graphics.g2d.GlyphLayout layout = new com.badlogic.gdx.graphics.g2d.GlyphLayout();
        layout.setText(font, caption);
        width = Math.round(layout.width);
        height = Math.round(layout.height);
        bounds = new Rectangle(x, y - height, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        bounds.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        bounds.y = y - height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setClickColor(){
        font.setColor(CLICK_COLOR);
    }

}
