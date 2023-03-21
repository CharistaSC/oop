package Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Rocket extends Player {
	private int jumpHeight ;
	private int fallHeight ;
	private boolean stop = false;
	private Texture frame;
	
	public Rocket(int x, int y, int height, int width, int speed, Texture frame) {
		super(x, y, height, width, speed);
		jumpHeight = speed;
		fallHeight = speed / 5;
		this.frame = frame;
	}
	
	public void jump() {
		this.setY(this.getY() - this.getFallHeight());
		if(Gdx.input.isKeyPressed(getPlayerKey()))
		{
			this.setY(this.getY() + this.getJumpHeight());
		}
	}
	
	public int getJumpHeight() {return this.jumpHeight;}
	public void setJumpHeight(int value) {this.jumpHeight = value;}
	
	public int getFallHeight() {return this.fallHeight;}
	public void setFallHeight(int value) {this.fallHeight = value;}
	
	public boolean getStop() {return this.stop;}
	public void setStop(boolean value) {this.stop = value;}
	
	public Texture getFrame() {return this.frame;}
	public void setFrame(Texture value) {this.frame = value;}
	
	public void render(SpriteBatch batch) 
	{
		batch.draw(this.getFrame(), this.getX(), this.getY(), this.getHeight(), this.getWidth());
	}
	
	public void stop() {
		this.setJumpHeight(0);
		this.setFallHeight(0);
	}
	
	public boolean collidedWith(Collidable rect){
		float left_bounds = rect.getX()-this.getWidth();
		float right_bounds = rect.getX()+rect.getWidth();
		float top_bounds = rect.getY()+ rect.getHeight();
		float bottom_bounds = rect.getY() - this.getHeight();

		return (this.getX() >= left_bounds +30 && this.getX() <=rect.getX() || this.getX() <=right_bounds + 30 && this.getX() >= rect.getX())
		&& (this.getY() <= top_bounds && this.getY()>=rect.getY() || this.getY()>=bottom_bounds && this.getY()<=rect.getY());

		}
	
}
