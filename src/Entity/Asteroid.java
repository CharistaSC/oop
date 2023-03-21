package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Asteroid extends Collidable {
	private ShapeRenderer shape;
	private Texture texture;
	
	public Asteroid(int x, int y, int height, int width, int speed, ShapeRenderer shape, Texture texture) {
		super(x, y, height, width, speed);
		this.shape = shape;
		this.texture = texture;
	}
	
	public ShapeRenderer getShape() {return this.shape;}
	public void setShape(ShapeRenderer value) {this.shape = value;}
	public Texture getTexture() {return this.texture;}
	public void setTexture(Texture value) {this.texture = value;}
	
	public void draw(ShapeRenderer shape) {
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.setColor(Color.BROWN);
		shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		shape.end();
	}
	
	public void render(SpriteBatch batch) 
	{
		batch.draw(this.getTexture(), this.getX(), this.getY(), this.getHeight(), this.getWidth());
	}
	
	public void move(int rand, int height) {
		if(this.getX() <= (-1) * this.getWidth()) {
			int random = (int)(Math.random()*(Gdx.graphics.getHeight()-this.getHeight()-0+0)+0);
			while(random < rand + height && random > rand - this.getHeight()) {
				random = (int)(Math.random()*(Gdx.graphics.getHeight()-this.getHeight()-0+0)+0);
			}
			this.setY(random);
			this.setX(Gdx.graphics.getWidth());
		} else {
			this.setX(this.getX() - this.getSpeed());
		}
	}
	
	public void stop() {
		this.setSpeed(0);
	}
	
}
