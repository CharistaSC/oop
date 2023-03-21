package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import Managers.ScreenManager;

public class MainMenuScreen extends ScreenManager {
	
	SpriteBatch batch;
	private Texture backgroundImg;
	private TextButton btnPlay,btnScore,btnExit,btnSettings;
	private Stage stage;
    private Table table;
    private Label gameTitle;
    
	
	public MainMenuScreen(Game game) {
		super(game);
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void show() {
		//create stage
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
		gameTitle = new Label("SPACY SHIP", this.get_skin());
		gameTitle.setAlignment(Align.center); // Align

        btnPlay = new TextButton("Play", this.get_skin());
		btnScore = new TextButton("Scoreboard", this.get_skin());
		btnExit = new TextButton("Exit", this.get_skin());
		btnSettings = new TextButton("Settings", this.get_skin());
		
		btnPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new InstructionScreen(game));
            }
        });
		btnScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ScoreboardScreen(game));
            }
        });
		btnSettings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingsScreen(game));
            }
        });
		btnExit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

		table.add(gameTitle).fillX().uniformX();
        table.row().pad(10, 0, 5, 0);
        table.add(btnPlay).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
		table.add(btnScore).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
		table.add(btnSettings).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
        table.add(btnExit).fillX().uniformX();
        table.row().pad(5, 0, 5, 0);
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
}
