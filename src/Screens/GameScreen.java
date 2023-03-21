package Screens;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.FileIO;
import com.mygdx.game.PlanetGame;

import Entity.Alien;
import Entity.Asteroid;
import Entity.Letter;
import Entity.Rocket;
import Factories.AbstractFactory;
import Factories.FactoryProducer;
import Managers.EntityManager;
import Managers.InputManager;
import Managers.ScreenManager;
import Managers.SoundManager;

public class GameScreen extends ScreenManager {
	
	// Score label
	private Label score;
	private Label letter_score;
	private Label gameText;
    private Table table;
    
    private String letter_progress = "";
	
    // Entities
	private EntityManager     entityManager;
	private AbstractFactory   obstacleFactory;
	private AbstractFactory   playerFactory;
	private ArrayList<Rocket> rocket_frames = new ArrayList<Rocket>();

	private String[] planetname;
	
	private int num_asteroids = 2;
	private int current_rocket;
	private int rand_pos;
	private int curr_score = 0;
	private int letters_collected = 0;
	private int score_speed = 5;
	private int score_multiplier = 1;
	
	private Sound        bellEffect;
	SpriteBatch          batch;
	private InputManager inputManager;
	
	private Texture      backgroundImg;
	private PlanetGame   planetGame;
	
	private Stage stage;
	private Alien alien;
	
	public GameScreen(Game game, PlanetGame planetGame) {
		super(game);
		this.planetGame = planetGame;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		super.dispose();
	}

	@Override
	public void show() {
		backgroundImg = planetGame.getbackgroundImage();
		planetname    = planetGame.getplanetName().split("");
		for(int i = 0; i < planetname.length; i++) {
			planetname[i] = planetname[i].toUpperCase();
		}
		
		// Tables and Labels
		score        = new Label("Score: " + curr_score, this.get_skin());
		gameText     = new Label("Spell the word " + planetGame.getplanetName() + " \nto get an additional\n 2x score multiplier!\nCurrent Multiplier: " + score_multiplier + "x", this.get_skin());
		letter_score = new Label("Letters Collected: ", this.get_skin());
		
		score.setAlignment(Align.center);
		letter_score.setAlignment(Align.center);
		gameText.setAlignment(Align.center);
		
        table = new Table();
        table.top();
        table.setFillParent(true);
        table.setDebug(false);
		
		stage = new Stage();
        stage.addActor(table);
        
		batch = new SpriteBatch();
		
		// Get instance of Entity Manager
		entityManager = EntityManager.getEntityManagerInstance();
		obstacleFactory = FactoryProducer.createFactory(false);
		playerFactory = FactoryProducer.createFactory(true);
			
		// Add asteroids
		for(int i = 1; i <= num_asteroids; i++) 
		{
			entityManager.addNewAsteroid((Asteroid)obstacleFactory.createEntity("ASTEROID", (i+1)*80, "Asteroid " + Integer.toString(i%3) + ".png"));
		}
		
		// Add letter
		entityManager.addNewLetter((Letter)obstacleFactory.createEntity("LETTER", 0, "letters/"+planetname[0]+".png"));

		// Add rockets
		for(int i = 0; i < 5; i ++) 
		{
			rocket_frames.add((Rocket)playerFactory.createEntity("ROCKET", 350, "Rocket " + Integer.toString((i+1)) + ".png"));
		}
		entityManager.addNewRocket(rocket_frames);
		for(int k = 0; k < entityManager.getRockets().size(); k++) 
		{
			for(int r = 0; r < entityManager.getRockets().get(k).size(); r++) 
			{
				entityManager.getRockets().get(k).get(r).setPlayerKey(Keys.SPACE);
			}
		}
		
		bellEffect    = Gdx.audio.newSound(Gdx.files.internal("ding.mp3"));

		inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		
		table.add(score).fillX();
        table.row().pad(10, 0, 5, 0);
		table.add(gameText).fillX();
        table.row().pad(10, 0, 5, 0);
		table.add(letter_score).fillX();
        table.row().pad(10, 0, 5, 0);
	}

	@Override
	public void render(float delta) {
		
		for(int k = 0; k < entityManager.getRockets().size(); k++) {
			for(int r = 0; r < entityManager.getRockets().get(k).size(); r++) {
				if(entityManager.getRockets().get(k).get(r).getStop()) {
					//add score in file
					FileIO fileIO = new FileIO();
					fileIO.appendFile("score.txt", planetGame.getplanetName()+"="+Integer.toString(curr_score)+"\n");
					game.setScreen(new GameOverScreen(game,curr_score));
				}
			}
		}
		
		rand_pos = (int)(Math.random()*(Gdx.graphics.getHeight()-entityManager.getRockets().get(0).get(0).getHeight()-0+0)+0); 
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 
		batch.begin();
		batch.draw(backgroundImg, 0, Gdx.graphics.getHeight()/4, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
		
		if(current_rocket == 4) {
			current_rocket = 0;
		} else {
			current_rocket += 1;
		}

		entityManager.renderRockets(batch, current_rocket);
		entityManager.renderLetters(batch); 
		entityManager.renderAsteroids(batch);
		
		for(int k = 0; k < entityManager.getRockets().size(); k++) {
			for(int r = 0; r < entityManager.getRockets().get(k).size(); r++) {
				entityManager.getRockets().get(k).get(r).jump();
			}
		}
		
		for(int i = 0; i < entityManager.getAsteroids().size(); i++) {
			entityManager.getAsteroids().get(i).move(rand_pos, entityManager.getRockets().get(0).get(0).getHeight());
			for(int k = 0; k < entityManager.getRockets().size(); k++) {
				for(int r = 0; r < entityManager.getRockets().get(k).size(); r++) {
					if(entityManager.getRockets().get(k).get(r).collidedWith(entityManager.getAsteroids().get(i))) {
						entityManager.getAsteroids().get(i).stop();
						entityManager.getRockets().get(k).get(r).setStop(true);
						entityManager.getRockets().get(k).get(r).stop();
					}
				}
			}
		}
		batch.end();
		
		for(int j = 0; j < entityManager.getLetters().size(); j++) {
			entityManager.getLetters().get(j).move(rand_pos, planetname[letters_collected]);
			for(int k = 0; k < entityManager.getRockets().size(); k++) {
				for(int r = 0; r < entityManager.getRockets().get(k).size(); r++) {
					if(entityManager.getRockets().get(k).get(r).collidedWith(entityManager.getLetters().get(j))) {
						if(entityManager.getLetters().get(j).getCollided() == false) {
							SoundManager.instance.playSound(bellEffect);
							letter_progress += planetname[letters_collected] + " ";
							entityManager.getLetters().get(j).setCollided(true);
							entityManager.getLetters().get(j).setTexture(new Texture(Gdx.files.internal("transparent.jpg")));
							if(letters_collected == planetname.length-1) {
								score_multiplier *= 2;
								letter_progress = "";
								letters_collected = 0;
							} else {
								letters_collected += 1;
							}
						}
					}
				}
			}
			
		}
		curr_score += score_speed * score_multiplier;
		score.setText("Score: " + curr_score);
		letter_score.setText("Letters Collected: " + letter_progress);
		gameText.setText("Spell the word " + planetGame.getplanetName() + " \nto get an additional 2x score multiplier!\nCurrent Multiplier: " + score_multiplier + "x");
		stage.act();
		stage.draw();
		
		//inputManager.update();
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
	
	//show alien
	/*private void showPopup(String text) {
		alien = new Alien(text, TimeUnit.SECONDS.toMillis(2));
		alien.setPosition(100, 10);
		alien.setY(200);
		stage.addActor(alien);
        if (!alien.isAnimated()) {
			alien.animate();
		}
	}*/
}
