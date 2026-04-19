package core;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {

    public static Image blankBackground;
    public static Image background1;

    public static void loadImages() throws SlickException {
       blankBackground = new Image("media/blankBackground.png");
       background1 = new Image ("sprites/stages/stage_malfunctioningshrine.png");
    }
}
