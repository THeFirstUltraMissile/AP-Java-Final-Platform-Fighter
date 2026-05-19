package core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    public static Image blankBackground;
    public static Image shrineBackground;
    public static Image tetrisBackground;
    public static Image menuBackground;
    public static Image tutorialBackground;
    public static Image tetrisBackdrop; //actual background and not the foreground

    public static Image menuLogo;

    public static Image sukunaIcon;
    public static Image gojoIcon;

    public static Animation walking;

    public static void loadImages() throws SlickException {
        blankBackground = new Image("media/blankBackground.png");
        shrineBackground = new Image("media/sprites/stages/stage_malfunctioningshrine.png");
        tetrisBackground = new Image("media/sprites/stages/stage_tetris2x.png");
        menuBackground = new Image("media/sprites/menus/missingHD.png");
        tutorialBackground = new Image("media/sprites/menus/TutorialScreen.png");

        menuLogo = new Image("media/sprites/menus/missingLogo.png");
        sukunaIcon = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
        gojoIcon = new Image("media/sprites/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru512.png");

        SpriteSheet walking = new SpriteSheet("media/sprites/effects/effects_sprinting1.png", 128, 128);
    }
}