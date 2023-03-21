package Entity;

public abstract class Entity{
    private int x;
	private int y;
	private int height;
	private int width;
	private int speed;
	
	public Entity(int x, int y, int height, int width, int speed) {
		this.x      = x;
		this.y      = y;
		this.height = height;
		this.width  = width;
        this.speed = speed;
	}
	
	public int getX() {return this.x;}
	public void setX(int value) {this.x = value;}
	
	public int getY() {return this.y;}
	public void setY(int value) {this.y = value;}
	
	public int getHeight() {return this.height;}
	public void setHeight(int value) {this.height = value;}
	
	public int getWidth() {return this.width;}
	public void setWidth(int value) {this.width = value;}

	public int getSpeed() {return this.speed;}
	public void setSpeed(int value) {this.speed = value;}

}
