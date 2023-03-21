package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Letter extends Collidable {
	private ShapeRenderer shape;
	private boolean isCollided;
	private Texture texture;
	
	public Letter(int x, int y, int height, int width, int speed, ShapeRenderer shape, Texture texture) {
		super(x, y, height, width, speed);
		this.shape = shape;
		this.isCollided = false;
		this.texture = texture;
	}
	
	public ShapeRenderer getShape() {return this.shape;}
	public void setShape(ShapeRenderer value) {this.shape = value;}
	public boolean getCollided() {return this.isCollided;}
	public void setCollided(boolean value) {this.isCollided = value;}
	public Texture getTexture() {return this.texture;}
	public void setTexture(Texture value) {this.texture = value;}
	
	public void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.YELLOW);
		shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		shape.end();
	}
	
	public void render(SpriteBatch batch) 
	{
		batch.draw(this.getTexture(), this.getX(), this.getY(), this.getHeight(), this.getWidth());
	}
	
	public void move(int pos, String letter) {
		if(this.getX() <= (-1) * this.getWidth()) {
			this.setY(pos);
			this.setX(Gdx.graphics.getWidth());
			if(this.getCollided() == true) {
				this.setTexture(new Texture(Gdx.files.internal("letters/"+letter+".png")));
			}
			this.setCollided(false);
		} else {
			this.setX(this.getX() - this.getSpeed());
		}
	}
	
	public void stop() {
		this.setSpeed(0);
	}
	
}
