package com.bt.screens;


/**
 * Created by Bogdan on 5/8/2015.
 */
public enum ScreenState {

    MAIN_MENU {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new MainMenuScreen();
        }
    },

    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new GameScreen();
        }
    },

    SETTINGS {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new SettingsScreen();
        }
    },

    SCORE {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new ScoreScreen();
        }
    };

    protected abstract com.badlogic.gdx.Screen getScreenInstance();
}
