package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import Entity.Rocket;

public class PlayerFactory extends AbstractFactory {
	public PlayerFactory() {}
	
	public Rocket createEntity(String type, int yPos, String filename) {
		if(type == null) {
			return null;
		} else if(type == "ROCKET") {
			return new Rocket(10, yPos, 100, 75, 15, new Texture(Gdx.files.internal(filename)));
		}
		
		return null;
	}

}
