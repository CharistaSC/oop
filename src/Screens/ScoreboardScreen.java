package Screens;


import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.FileIO;
import com.mygdx.game.Score;

import Managers.ScreenManager;

public class ScoreboardScreen extends ScreenManager{

	SpriteBatch batch;
	private Stage stage;
	private Table table,scoreTable;
	private Texture backgroundImg;

	private FileIO fileIO = new FileIO();
	private Label titleLabel,scoreLabel,planetLabel;
	private TextButton btnReturn;
	
	public ScoreboardScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
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
        
		//read file put into scoreboard
		ArrayList<Score> score_list = getFileScore();

		// Create a table that fills the screen. Everything else will go inside this table.
        table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
		
		scoreTable = new Table();
        scoreTable.setDebug(false);
        stage.addActor(table);
        
		batch         = new SpriteBatch();
		backgroundImg = new Texture(Gdx.files.internal("background_image.jpg"));
		
		//SCOREBOARD title
		titleLabel = new Label("SCOREBOARD", this.get_skin());
		titleLabel.setAlignment(Align.center); // Align

		
		btnReturn = new TextButton("Return", this.get_skin());
		btnReturn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MainMenuScreen(game));
				dispose();
            }
        });
       

		table.add(titleLabel).fillX().uniformX();
        table.row().pad(10, 0, 5, 0);

        //INSERT SCOREBOARD
		if (score_list.isEmpty()){
			planetLabel = new Label("Play to get score!", this.get_skin());
			scoreTable.add(planetLabel).fillX().uniformX();
		}else{
			for (Score score: score_list){
				planetLabel = new Label(score.getPlanetName(), this.get_skin());
				scoreLabel = new Label(Integer.toString(score.getScore()), this.get_skin());
				scoreTable.add(planetLabel).fillX().uniformX();
				scoreTable.add(scoreLabel).fillX().uniformX();
				scoreTable.row().pad(10, 0, 5, 0);
			}
		}
		table.add(scoreTable).fillX().uniformX();
        table.row().pad(10, 0, 5, 0);
        
		table.add(btnReturn).fillX().uniformX();
        table.row().pad(10, 0, 5, 0);
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

	public ArrayList<Score> getFileScore(){
		String file = fileIO.readFile("score.txt");
		System.out.print("file"+file);
		String[] line_list = null;
		ArrayList<Score> score_list = new ArrayList<Score>();
		if (file != ""){
			if (file.contains("\n")){
				line_list = file.split("\n");
			}else{
				line_list = new String [] {file};
			}
			for (String i: line_list) {
				if (!i.isEmpty()){
					String[] score = i.split("=");
					Score score_obj = new Score(score[0], Integer.parseInt(score[1]));
					score_list.add(score_obj);
				}
			}
		}
		return score_list;
	}
}
