package com.bt.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.bt.handlers.AssetLoader;
import com.bt.screens.GameScreen;
import com.bt.screens.MainMenuScreen;
import com.bt.screens.ScreenManager;
import com.bt.screens.ScreenState;
import com.bt.screens.SettingsScreen;
import com.bt.vars.Var;

public class MatchingTiles extends Game {

	private ExtendViewport viewport;
	private OrthographicCamera camera;
	private BitmapFont font;

	public void create () {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(Var.WIDTH, Var.HEIGHT, Var.WIDTH, 0, camera);

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
		font = createFont(generator, 20);
		MainMenuScreen.font = this.font;
		SettingsScreen.font = this.font;
		GameScreen.font = this.font;
		generator.dispose();

		AssetLoader.init();
		if(SettingsScreen.prefs.getBoolean("musicOn")){
			AssetLoader.getMusic().setVolume(0.1f);
			AssetLoader.getMusic().setLooping(true);
			AssetLoader.getMusic().play();
		}
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(ScreenState.MAIN_MENU);

	}

	private BitmapFont createFont(FreeTypeFontGenerator generator, float dp)
	{
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

		int fontSize = (int)(dp * Gdx.graphics.getDensity());
		parameter.size = fontSize;
		return generator.generateFont(parameter);
	}

	public void dispose () {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

}
