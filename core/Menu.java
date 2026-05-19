package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState
{

    private int id;

    private boolean p1isReady;
    private boolean p2isReady;
    private boolean showControls;

    public Menu(int id)
    {
        this.id = id;
    }

    public int getID()
    {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        if (p1isReady && p2isReady) {
            sbg.enterState(Main.CHAR_SELECT_ID);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.drawImage(Images.menuBackground,0, 0);
        g.drawImage(Images.menuLogo, 544, 136);

        g.drawString("Press both W and I to begin.", (Main.getScreenWidth()/2)-125, (Main.getScreenHeight()-100));

        if (!p1isReady) {
            g.drawString("Player 1 is not ready.", Main.getScreenWidth()/4, Main.getScreenHeight()/2);
        } else {
            g.drawString("Player 1 is ready!", Main.getScreenWidth()/4, Main.getScreenHeight()/2);
        }

        if (!p2isReady) {
            g.drawString("Player 2 is not ready.", Main.getScreenWidth()/4*3, Main.getScreenHeight()/2);
        } else {
            g.drawString("Player 2 is ready!", Main.getScreenWidth()/4*3, Main.getScreenHeight()/2);
        }

        g.drawString("Press the ; (semicolon) key to view the controls for the player", Main.getScreenWidth()-500, 100);

        if (showControls) {
            g.drawImage(Images.tutorialBackground, 0, 0);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        // This code happens when you enter a gameState.
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        // This code happens when you leave a gameState.
    }

    public void keyPressed(int key, char c)
    {
        switch (key) {
            case Input.KEY_W:
                p1isReady = true;
                break;
            case Input.KEY_I:
                p2isReady = true;
                break;
            case Input.KEY_SEMICOLON:
                if (!showControls) {
                    showControls = true;
                } else {
                    showControls = false;
                }
                break;
        }
    }

    public void mousePressed(int button, int x, int y)
    {
        // This code happens every time the user presses the mouse
    }
}
