package core;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Images {

    public static Image blankBackground;
    public static Image shrineBackground;
    public static Image tetrisBackground;
    public static Image terraBackground;
    public static Image menuBackground;
    public static Image tutorialBackground;
    public static Image tetrisLand; //actual bg and not foreground

    public static Image sukunaIcon;
    public static Image gojoIcon;

    public static Image menuLogo;

    public static SpriteSheet hollowPurpleSpriteSheet;
    public static Animation hollowPurpleAnimation;

    public static SpriteSheet cleaveSpriteSheet;
    public static Animation cleaveAnimation;

    public static Animation walking;

    public static void loadImages() throws SlickException {
        blankBackground = new Image("media/blankBackground.png");
        shrineBackground = new Image("media/sprites/stages/stage_malfunctioningshrine.png");
        tetrisBackground = new Image("media/sprites/stages/stage_tetris2x.png");
        menuBackground = new Image("media/sprites/menus/missingHD.png");
        terraBackground = new Image("media/sprites/stages/terrariaStage.png");
        tutorialBackground = new Image("media/sprites/menus/TutorialScreen.png");
        tetrisBackground = new Image("media/sprites/stages/tetrisbackground.png");

        sukunaIcon = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
        gojoIcon = new Image("media/sprites/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru512.png");

        menuLogo = new Image("media/sprites/menus/missingLogo.png");
        SpriteSheet walking = new SpriteSheet("media/sprites/effects/effects_sprinting1.png", 128, 128);

        hollowPurpleSpriteSheet = new SpriteSheet("media/sprites/kaisen/gojosatoru/limitless/purple/gojo_unlimitedpurple.png",512,512);
        hollowPurpleAnimation = new Animation(hollowPurpleSpriteSheet,180);
        hollowPurpleAnimation.setLooping(false);

        cleaveSpriteSheet = new SpriteSheet("media/sprites/kaisen/ryomensukuna/shrine/cleave/sukuna_cleave.png",256,256);
        cleaveAnimation = new Animation(cleaveSpriteSheet,180);
        cleaveAnimation.setLooping(false);
    }
}