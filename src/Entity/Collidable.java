package Entity;

public abstract class Collidable extends Entity {
	
	public Collidable(int x, int y, int height, int width, int speed) {
		super(x, y, height, width, speed);
	}
	
	public boolean collidedWith(Collidable obj) {
		return false;
	}
	
}
