package Factories;

import Entity.Entity;

public abstract class AbstractFactory {
	public abstract Entity createEntity(String type, int yPos, String filename);
}
