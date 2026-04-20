package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    public final static int FRAMES_PER_SECOND = 60;
    private static AppGameContainer appgc;

    public static final int CHAR_SELECT_ID = 0;
    public static final int STAGE_SELECT_ID = 1;
    public static final int GAME_ID = 2;

    public Main(String name) {
        super(name);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new CharacterSelect(CHAR_SELECT_ID));
        addState(new StageSelect(STAGE_SELECT_ID));
        addState(new Game(GAME_ID));
        enterState(CHAR_SELECT_ID);
    }

    public static void main(String[] args) {
        try {
            appgc = new AppGameContainer(new Main("Platform Fighter"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            appgc.setDisplayMode(1920, 1080, false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.setVSync(true);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
