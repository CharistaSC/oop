package Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import Entity.Asteroid;
import Entity.Collidable;
import Entity.Letter;

public class ObstacleFactory extends AbstractFactory {
	public ObstacleFactory() {}
	
	public Collidable createEntity(String type, int yPos, String filename) 
	{
		if(type == null) 
		{
			return null;
		} 
		else if(type == "ASTEROID") 
		{
			return new Asteroid(-40, yPos, 40, 40, 3, new ShapeRenderer(), new Texture(Gdx.files.internal(filename)));
		}
		else if(type == "LETTER") 
		{
			return new Letter(-40, yPos, 40, 40, 3, new ShapeRenderer(), new Texture(Gdx.files.internal(filename)));
		}
		
		return null;
	}
	
}
