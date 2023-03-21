package Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.mygdx.game.PlanetGame;

import Managers.ScreenManager;

public class LevelSelectScreen extends ScreenManager{
    SpriteBatch batch;
	private Stage stage;
	private Table table;
	private Table levelTable;
	private Texture backgroundImg;
	private Label titleLabel;
	private TextButton btnReturn;
    private ArrayList<PlanetGame> planetList = new ArrayList<PlanetGame>();

    public LevelSelectScreen(Game game) {
        super(game);
        //TODO Auto-generated constructor stub
        planetList.add(new PlanetGame("Sun", new Texture(Gdx.files.internal("Planets/Sun-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Moon Earth", new Texture(Gdx.files.internal("asteroid.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Earth", new Texture(Gdx.files.internal("Planets/Earth-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Mercury", new Texture(Gdx.files.internal("Planets/Mercury-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Venus", new Texture(Gdx.files.internal("Planets/Venus-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Mars", new Texture(Gdx.files.internal("Planets/Mars-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Jupiter", new Texture(Gdx.files.internal("Planets/Jupiter-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Saturn", new Texture(Gdx.files.internal("Planets/Saturn-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Uranus", new Texture(Gdx.files.internal("Planets/Uranus-removebg-preview.png")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
        planetList.add(new PlanetGame("Pluto", new Texture(Gdx.files.internal("drop.jpg")), Gdx.audio.newMusic(Gdx.files.internal("sample.mp3"))));
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
        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        
        levelTable = new Table();
        levelTable.setDebug(false);
        
        stage.addActor(table);
        
		batch         = new SpriteBatch();
		backgroundImg = new Texture(Gdx.files.internal("background_image.jpg"));
		
		//SETTINGS title
		titleLabel = new Label("Level Select", this.get_skin());
		titleLabel.setAlignment(Align.center); // Align

		btnReturn = new TextButton("Return", this.get_skin());
		btnReturn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
            }
        });

		table.add(titleLabel).fillX().uniformX();
        table.row().pad(10, 0, 0,0);

        for (int i = 0; i < planetList.size(); i++) {
            System.out.println(i);
            if (i % 2 == 0) {
                levelTable.row().pad(5, 0, 0, 0);
            }
            addLevel(levelTable, planetList.get(i));
            
        }
        table.add(levelTable);

        table.row().pad(10, 0, 5, 0);
        table.add(btnReturn).fillX();
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

    private void addLevel(Table table,final PlanetGame planet){
        TextButton tb = new TextButton(planet.getplanetName(), this.get_skin());
        tb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new GameScreen(game,planet));
            }
        });
        table.add(tb);
    }
}
