package Managers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    public static final SoundManager instance = new SoundManager();
    private Music dropMusic;

    public void playMusic(Music sound){
        stopMusic();
        dropMusic  = sound;

		dropMusic.setLooping(true);
        dropMusic.setVolume(getAudioVolume());
		dropMusic.play();
    }

    public void stopMusic(){
        if (dropMusic != null){
            dropMusic.stop();
        }
    }

    public void playSound(Sound sound){
        sound.play(getAudioVolume());
    }

    public void mute(){
        dropMusic.setVolume(0);
    }

    //add settings slider
    public void changeVolume(float volume){
        dropMusic.setVolume(volume);
    }

    public float getAudioVolume(){
        return Gdx.app.getPreferences("VOLUME").getFloat("sound");
    }

    public void setVolume(float volume){
        dropMusic.setVolume(volume);
        //save to preferences
        Gdx.app.getPreferences("VOLUME").putFloat("sound", volume);
    }
}