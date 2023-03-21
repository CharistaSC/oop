package Entity;
import com.badlogic.gdx.Input.Keys;

public class Player extends Collidable{
    private int playerKey;
    public Player(int x, int y, int height, int width, int speed) {
        super(x, y, height, width, speed);
        playerKey = Keys.SPACE;
    }
    public int getPlayerKey() {return this.playerKey;}
	public void setPlayerKey(int value) {this.playerKey = value;}
    
}
