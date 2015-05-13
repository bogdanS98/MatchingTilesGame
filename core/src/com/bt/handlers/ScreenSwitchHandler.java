package com.bt.handlers;

import com.badlogic.gdx.Gdx;
import com.bt.screens.ScreenManager;
import com.bt.screens.ScreenState;
import com.bt.screens.SettingsScreen;

/**
 * Created by Bogdan on 5/9/2015.
 */
public class ScreenSwitchHandler implements ButtonHandler {

        private ScreenState screen = null;

        public ScreenSwitchHandler(ScreenState screen) {
            this.screen = screen;
        }

        @Override
        public void OnClick() {
            if(Gdx.input.justTouched()){
                if(SettingsScreen.isSoundOn())
                    AssetLoader.getClickSound().play();
            }
            ScreenManager.getInstance().show(screen);
        }
}
