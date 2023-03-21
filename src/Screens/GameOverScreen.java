package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Managers.ScreenManager;

public class GameOverScreen extends ScreenManager{

	SpriteBatch batch;
	private Texture backgroundImg;
	private Stage stage;
	private Table table;
	private Label gameoverLabel,scoreboardLabel;
	private TextButton btnReturn,btnExit;
	private Integer score;
	
	
	public GameOverScreen(Game game, Integer score) {
		super(game);
		this.score = score;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void show() {
		stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        Gdx.input.setInputProcessor(stage);
        
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
        
		batch         = new SpriteBatch();
		backgroundImg = new Texture(Gdx.files.internal("background_image.jpg"));
		
		//GAME title
		gameoverLabel = new Label("GAME OVER", this.get_skin());
		gameoverLabel.setAlignment(Align.center); // Align

		scoreboardLabel = new Label("YOUR SCORE: " + Integer.toString(this.score), this.get_skin());
		scoreboardLabel.setAlignment(Align.center); // Align

		btnReturn = new TextButton("Return", this.get_skin());
		btnExit = new TextButton("Exit", this.get_skin());
		
		btnReturn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
            }
        });
		btnExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
		
		table.add(scoreboardLabel).fillX().uniformX();
        table.row().pad(20, 0, 5, 0);
		table.add(gameoverLabel).fillX().uniformX();
        table.row().pad(5, 0, 0, 0);
		table.add(btnReturn).fillX().uniformX();
        table.row().pad(5, 0, 0, 0);
        table.add(btnExit).fillX().uniformX();
        table.row().pad(5, 0, 0, 0);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		batch.begin();
		batch.draw(backgroundImg, 0, 0, 480, 800);
        batch.end();
        
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
