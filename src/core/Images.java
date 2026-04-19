package core;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    public static Image blankBackground;
    public static Image shrineBackground;
    public static Image tetrisBackground;

    public static Image gojoIcon;
    public static Image sukunaIcon;
    public static Image mikuIcon;
    public static Image tSpunnedIcon;

    public static SpriteSheet tIconAnimation;



    public static void loadImages() throws SlickException {
       blankBackground = new Image("media/stages/blankBackground.png");
       shrineBackground = new Image("media/stages/stage_malfunctioningshrine.png");
       tetrisBackground = new Image("media/stages/stage_tetris.png");

       gojoIcon = new Image("media/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru256.png");
       sukunaIcon = new Image("media/kaisen/ryomensukuna/assets/icon_ryomensukuna256.png");
       mikuIcon = new Image("media/miku/icon_miku_256.png");
       tSpunnedIcon = new Image("media/tetris/icon_tetristspin_Pink_TEMP_256.png");

       tIconAnimation = new SpriteSheet("media/tetris/icon_tetristspin_256.png",256,256);
    }
}
