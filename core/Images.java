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
    public static Image MusicStage;
    public static SpriteSheet MusicStageSS;
    public static Animation MusicStageAnimation;

    public static Image sukunaIcon;
    public static Image gojoIcon;

    public static Image menuLogo;

    public static SpriteSheet hollowPurpleSpriteSheet;
    public static Animation hollowPurpleAnimation;

    public static SpriteSheet cleaveSpriteSheet;
    public static Animation cleaveAnimation;

    public static Animation walking;

    public static Animation sukunaIdle;
    public static Animation sukunaLight;
    public static Animation sukunaHeavy;
    public static Animation sukunaAerial;

    public static SpriteSheet sukunaIdleSS;
    public static SpriteSheet sukunaLightSS;
    public static SpriteSheet sukunaHeavySS;
    public static SpriteSheet sukunaAerialSS;

    public static void loadImages() throws SlickException {
        blankBackground = new Image("media/blankBackground.png");
        shrineBackground = new Image("media/sprites/stages/stage_malfunctioningshrine.png");
        tetrisBackground = new Image("media/sprites/stages/stage_tetris2x.png");
        terraBackground = new Image("media/sprites/stages/terrariaStage.png");
        menuBackground = new Image("media/sprites/menus/missingHD.png");
        tutorialBackground = new Image("media/sprites/menus/TutorialScreen.png");
        MusicStage = new Image("media/sprites/stages/MusicStage.png");
        MusicStageSS = new SpriteSheet("media/sprites/stages/MusicStageSS.png",1920,1080);
        MusicStageAnimation = new Animation(MusicStageSS, 360);

        sukunaIcon = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
        gojoIcon = new Image("media/sprites/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru512.png");
        menuLogo = new Image("media/sprites/menus/tempLogoFix.png");
        SpriteSheet walking = new SpriteSheet("media/sprites/effects/effects_sprinting1.png", 128, 128);

        hollowPurpleSpriteSheet = new SpriteSheet("media/sprites/kaisen/gojosatoru/limitless/purple/gojo_unlimitedpurple.png",512,512);
        hollowPurpleAnimation = new Animation(hollowPurpleSpriteSheet,180);
        hollowPurpleAnimation.setLooping(false);

        cleaveSpriteSheet = new SpriteSheet("media/sprites/kaisen/ryomensukuna/shrine/cleave/sukuna_cleave.png",256,256);
        cleaveAnimation = new Animation(cleaveSpriteSheet,50);
        cleaveAnimation.setLooping(true);

        sukunaIdleSS = new SpriteSheet("media/sprites/kaisen/ryomensukuna/assets/thukuna.png", 645, 645);
        sukunaLightSS = new SpriteSheet("media/sprites/kaisen/ryomensukuna/basic/sukuna_jab.png", 128, 128);
        sukunaHeavySS = new SpriteSheet("media/sprites/kaisen/ryomensukuna/shrine/dismantle/sukuna_dismantleimpact.png", 256, 256);
        sukunaAerialSS = new SpriteSheet("media/sprites/kaisen/ryomensukuna/basic/sukuna_jab2.png",128,128);

        sukunaIdle = new Animation(sukunaIdleSS,150);
        sukunaLight = new Animation(sukunaLightSS, 60);
        sukunaHeavy = new Animation(sukunaHeavySS, 60);
        sukunaAerial = new Animation(sukunaAerialSS, 60);
    }
}