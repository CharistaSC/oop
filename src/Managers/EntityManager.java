package Managers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entity.Asteroid;
import Entity.Letter;
import Entity.Rocket;

public class EntityManager {
	
	private ArrayList<Asteroid> asteroid_list;
	private ArrayList<Letter>   letter_list;
	private ArrayList<ArrayList<Rocket>>   rocket_list;
	
	private static EntityManager entity_manager_instance;
	
	public EntityManager() {
		asteroid_list = new ArrayList<Asteroid>();
		letter_list   = new ArrayList<Letter>();
		rocket_list   = new ArrayList<ArrayList<Rocket>>();
		
		entity_manager_instance = null;
	}
	
	public void resetInstance() {
		entity_manager_instance = null;
	}
	
	public static EntityManager getEntityManagerInstance() {
		if(entity_manager_instance == null) {
			entity_manager_instance = new EntityManager();
		}
		
		return entity_manager_instance;
	}

	// Getters
	public ArrayList<Asteroid> getAsteroids(){return this.asteroid_list;}
	public ArrayList<Letter> getLetters()    {return this.letter_list;}
	public ArrayList<ArrayList<Rocket>> getRockets()    {return this.rocket_list;}
	
	// Setters (Adding new objects to lists)
	public void addNewAsteroid(Asteroid asteroid) {asteroid_list.add(asteroid);}
	public void addNewLetter(Letter letter)       {letter_list.add(letter);}
	public void addNewRocket(ArrayList<Rocket> rocket)       {rocket_list.add(rocket);}
	
	public void renderAsteroids(SpriteBatch batch) {
		for(int i = 0; i < asteroid_list.size(); i++) {
			asteroid_list.get(i).render(batch);
		}
	}
	public void renderLetters(SpriteBatch batch) {
		for(int i = 0; i < letter_list.size(); i++) {
			letter_list.get(i).render(batch);
		}
	}
	public void renderRockets(SpriteBatch batch, int curr) {
		for(int i = 0; i < rocket_list.size(); i++) {
			for(int j = 0; j < rocket_list.get(i).size(); j++) {
				if(j == curr) {
					rocket_list.get(i).get(j).render(batch);
				}
			}
		}
	}

}
