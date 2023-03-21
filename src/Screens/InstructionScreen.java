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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Managers.ScreenManager;

public class InstructionScreen extends ScreenManager{

	SpriteBatch batch;
	private Stage stage;
	private Table table;
	private Texture backgroundImg;
	
	private Label titleLabel,instructionLabel;
	
	private TextButton btnStart;

	public InstructionScreen(Game game) {
		super(game);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
		table = new Table();
        Gdx.input.setInputProcessor(stage);
        
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
		
		backgroundImg = new Texture(Gdx.files.internal("background_image.jpg"));
		
		//Title
		titleLabel = new Label("INSTRUCTIONS", this.get_skin());
		titleLabel.setAlignment(Align.center); // Align

		//Instructions
		instructionLabel = new Label("Press the space bar to jump, avoid all obstacles and learn more about planets along the way!", this.get_skin());
		instructionLabel.setAlignment(Align.center); // Align
		instructionLabel.setWrap(true);
		
        btnStart = new TextButton("Start!", this.get_skin());
        btnStart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new LevelSelectScreen(game));
            }
        });
        
		table.add(titleLabel).fillX();
        table.row().pad(10, 0, 20, 0);
		table.add(instructionLabel).fillX();
        table.row().pad(10, 0, 5, 0);
        table.add(btnStart).fillX();
        table.row().pad(10, 0, 5, 0);
        
	}

	@Override
	public void render(float delta) {
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
