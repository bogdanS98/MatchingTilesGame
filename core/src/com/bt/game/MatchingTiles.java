package com.bt.game;

import com.badlogic.gdx.Game;
import com.bt.handlers.AssetLoader;
import com.bt.screens.ScreenManager;
import com.bt.screens.ScreenState;

public class MatchingTiles extends Game {


	public void create () {
		AssetLoader.init();
		AssetLoader.getMusic().setVolume(0.1f);
		AssetLoader.getMusic().setLooping(true);
		AssetLoader.getMusic().play();
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenState.MAIN_MENU);
	}

	public void dispose () {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

}
