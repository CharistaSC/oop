package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {

	public InputManager() {
	    for (int i = 0; i < 256; i++) {
	        key_states.add(new KeyState(i));
	    }
	    
	    touchStates.add(new TouchState(0, 0, 0, 0));
    }
	
	public class InputState {
	    public boolean pressed = false;
	    public boolean down = false;
	    public boolean released = false;
	}
	
	/*KEYBOARD INPUT*/
	public Array<KeyState> key_states = new Array<KeyState>();
	public class KeyState extends InputState{
	    public int key;
	    public KeyState(int key){
	        this.key = key;
	    }
	}
	
	@Override
	public boolean keyDown(int keycode) {
        key_states.get(keycode).pressed = true;
		key_states.get(keycode).down = true;
		key_states.get(keycode).released = false;
	    return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		key_states.get(keycode).pressed = false;
		key_states.get(keycode).down = false;
		key_states.get(keycode).released = true;
	    return false;
	}
	
	public boolean isKeyPressed(int key){
	    return key_states.get(key).pressed;
	}
	public boolean isKeyDown(int key){
	    return key_states.get(key).down;
	}
	public boolean isKeyReleased(int key){
	    return key_states.get(key).released;
	}
	
	public void update(){
	  for (int i = 0; i < 256; i++) {
	      KeyState k = key_states.get(i);
	      k.pressed = false;
	      k.down = false;
	      k.released = false;
	  }
      for (int i = 0; i < touchStates.size; i++) {
          TouchState t = touchStates.get(i);

          t.pressed = false;
          t.released = false;

          t.displacement.x = 0;
          t.displacement.y = 0;
      }
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/*Mouse INPUT*/
	public class TouchState extends InputState{
	    public int pointer;
	    public Vector2 coordinates;
	    public int button;
	    private Vector2 lastPosition;
	    public Vector2 displacement;
	  
	    public TouchState(int coord_x, int coord_y, int pointer, int button){
	        this.pointer = pointer;
	        coordinates = new Vector2(coord_x, coord_y);
	        this.button = button;

	        lastPosition = new Vector2(0, 0);
	        displacement = new Vector2(lastPosition.x, lastPosition.y);
	    }
	}
	public Array<TouchState> touchStates = new Array<TouchState>();

	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		boolean pointerFound = false;
        int coord_x = coordinateX(screenX);
        int coord_y = coordinateY(screenY);

        for (int i = 0; i < touchStates.size; i++) {
            TouchState t = touchStates.get(i);
            if (t.pointer == pointer) {
                t.down = true;
                t.pressed = true;
                t.released = false;
                t.coordinates.x = coord_x;
                t.coordinates.y = coord_y;
                t.button = button;
                t.lastPosition.x = coord_x;
                t.lastPosition.y = coord_y;
                pointerFound = true;
            }
        }

        if (!pointerFound) {
            touchStates.add(new TouchState(coord_x, coord_y, pointer, button));
            TouchState t = touchStates.get(pointer);

            t.down = true;
            t.pressed = true;
            t.released = false;

            t.lastPosition.x = coord_x;
            t.lastPosition.y = coord_y;
        }
        return false;
	}
	
	private int coordinateX (int screenX) {
        return screenX - Gdx.graphics.getWidth()/2;
    }
	private int coordinateY (int screenY) {
        return Gdx.graphics.getHeight()/2 - screenY;
    }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		TouchState t = touchStates.get(pointer);
        t.pressed = false;
		t.down = false;
        t.released = true;

        return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int coord_x = coordinateX(screenX);
        int coord_y = coordinateY(screenY);

        TouchState t = touchStates.get(pointer);
        t.coordinates.x = coord_x;
        t.coordinates.y = coord_y;
        t.displacement.x = coord_x - t.lastPosition.x;
        t.displacement.y = coord_y - t.lastPosition.y;
        t.lastPosition.x = coord_x;
        t.lastPosition.y = coord_y;

        return false;
	}
	
	//check states of supplied touch
    public boolean isTouchPressed(int pointer){
        return touchStates.get(pointer).pressed;
    }
    public boolean isTouchDown(int pointer){
        return touchStates.get(pointer).down;
    }
    public boolean isTouchReleased(int pointer){
        return touchStates.get(pointer).released;
    }

    public Vector2 touchCoordinates(int pointer){
        return touchStates.get(pointer).coordinates;
    }
    public Vector2 touchDisplacement(int pointer){
        return touchStates.get(pointer).displacement;
    }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}