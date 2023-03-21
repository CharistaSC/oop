package Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Alien extends NonCollidable {
	private final String text;
	private final long animationDuration;
	private boolean animated = false;
	private long animationStart;
	private float x;
	private float y;
	private Texture imgAlien;
	private ShapeRenderer shape = new ShapeRenderer();
	private BitmapFont font = new BitmapFont();

	public Alien(int x, int y, int height, int width, int speed, String text, long animationDuration) {
		super(x, y, height, width, speed);
		this.text = text;
		this.animationDuration = animationDuration;
	}

	public void animate() {
		animated = true;
		animationStart = System.currentTimeMillis();
	}

	public boolean isAnimated() {
		return animated;
	}

	public void draw(Batch batch, float parentAlpha) {
		
		if (animated) {
			if (isDisposable()) {
				dispose();
				return;
			}
			float elapsed = System.currentTimeMillis() - animationStart;
			
			//Create text above alien
			//font.setColor(getColor().r, getColor().g, getColor().b, parentAlpha * (1 - elapsed / animationDuration));
			font.draw(batch, text, getX() + x * elapsed / 1000f, getY()
					+ y * elapsed / 1000f,150,1, true);

			//Create Alien image
			imgAlien = new Texture(Gdx.files.internal("ufo.png"));
			batch.draw(imgAlien, (getX() + x * elapsed / 1000f), (getY()
					+ y * elapsed / 1000f)-250, 150, 100);

		}
	}

	private boolean isDisposable() {
		return animationStart + animationDuration < System.currentTimeMillis();
	}

	private void dispose() {
		font.dispose();
	}	
}
