package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import Managers.SoundManager;
import Screens.MainMenuScreen;


public class MyGdxGame extends Game 
{	
	FileIO fileIO = new FileIO();
	@Override
	public void create () 
	{
		SoundManager.instance.playMusic(Gdx.audio.newMusic(Gdx.files.internal("background_music.mp3")));	
		SoundManager.instance.setVolume(1);
		fileIO.writeFile("score.txt","");
		setScreen(new MainMenuScreen(this));
	}

}
