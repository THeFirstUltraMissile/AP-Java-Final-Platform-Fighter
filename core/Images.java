package core;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {

    public static Image blankBackground;

    public static void loadImages() throws SlickException {
       blankBackground = new Image("media/blankBackground.png");
    }
}
