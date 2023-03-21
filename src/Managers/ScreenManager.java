package Managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ScreenManager implements Screen{
	protected Game game;
    private AssetManager assetManager = new AssetManager();
    private final String skin_string = "skin/star-soldier/star-soldier-ui";
    
    public ScreenManager(Game game) {
        this.game = game;
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter(skin_string+".atlas");
        assetManager.load(skin_string+".json", Skin.class, params);
        assetManager.finishLoading();
    }
    
    public Skin get_skin() {
		return assetManager.get(skin_string+".json");
    }


	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}