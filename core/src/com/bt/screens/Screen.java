package com.bt.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bt.vars.Var;

/**
 * Created by Mihai on 5/8/2015.
 */
public class Screen implements com.badlogic.gdx.Screen {

    protected OrthographicCamera camera;

    public Screen() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Var.WIDTH, Var.HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    public void dispose(){}


}
