package com.bt.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


/**
 * Created by Mihai on 5/8/2015.
 */
public class AssetLoader {

    private static TextureAtlas tiles;
    private static Music music;
    private static Sound clickSound;
    private static Sound tileSound;
    private static Texture background;
    private static Sound goodSound;
    private static Sound badSound;

    public static void init() {
        tiles = new TextureAtlas(Gdx.files.internal("tilecolor.pack"));
        background = new Texture(Gdx.files.internal("menubackground.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music.wav"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        goodSound = Gdx.audio.newSound(Gdx.files.internal("goodsound.mp3"));
        badSound = Gdx.audio.newSound(Gdx.files.internal("badsound.mp3"));
        tileSound = Gdx.audio.newSound(Gdx.files.internal("tilesound.mp3"));
    }

    public static Texture getBackground(){
        return background;
    }
    public static TextureAtlas getTiles() {
        return tiles;
    }
    public static Sound getClickSound(){
        return clickSound;
    }
    public static Music getMusic(){
        return music;
    }
    public static Sound getTileSound() { return tileSound; }
    public static Sound getBadSound() { return badSound; }
    public static Sound getGoodSound() { return goodSound; }

    public static Sound getSound() {
        return clickSound;
    }

    public static void dispose(){
        background.dispose();
        tiles.dispose();
        clickSound.dispose();
        music.dispose();
    }
}
