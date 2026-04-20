package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images {

    public static Image blankBackground;
    public static Image shrineBackground;
    public static Image tetrisBackground;

    public static Image sukunaIcon;
    public static Image gojoIcon;

    public static void loadImages() throws SlickException {
        blankBackground = new Image("media/blankBackground.png");
        shrineBackground = new Image("media/sprites/stages/stage_malfunctioningshrine.png");
        tetrisBackground = new Image("media/sprites/stages/stage_tetris2x.png");

        sukunaIcon = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
        gojoIcon = new Image("media/sprites/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru512.png");
    }
}
