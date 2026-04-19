package core;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
public class Sounds {

    public static Music TestSong;

    public static void loadSounds() throws SlickException
    {

            TestSong =  new Music("media/music/testSong.ogg");


    }

}
