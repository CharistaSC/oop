package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Managers.SoundManager;

public class PlanetGame {
    private Texture backgroundImage;
    private Music gameMusic;
    private String planetName;
	
	public PlanetGame(String planetName,Texture backgroundImage,Music gameMusic) {
        this.planetName = planetName;
		this.backgroundImage = backgroundImage;
        this.gameMusic = gameMusic;
        SoundManager.instance.playMusic(gameMusic);
    }

    public String getplanetName(){return this.planetName;}
    public void setplanetName(String value){
        this.planetName = value;
    }
	
	public Texture getbackgroundImage() {return this.backgroundImage;}
	public void setbackgroundImage(SpriteBatch batch,Texture value) {
        this.backgroundImage = value;		
        batch.draw(value, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
    }

	public Music getgameMusic() {return this.gameMusic;}
	public void setgameMusic(Music value) {
        this.gameMusic = value;
        SoundManager.instance.playMusic(value);
    }
}
