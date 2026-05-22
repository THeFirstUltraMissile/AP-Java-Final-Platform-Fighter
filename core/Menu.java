package core;

import org.newdawn.slick.*;
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
//        g.drawImage(Images.menuBackground,0, 0);
        g.drawImage(Images.menuLogo, 300, 25);

        g.setColor(new Color(
                60,
                226,
                218
        ));
//        g.fillRect(0,0,1920,1080);

        g.drawString("Press both W and I to begin.", (Main.getScreenWidth()/2)-125, (Main.getScreenHeight()-100));

        if (!p1isReady) {
            g.drawString("Player 1 is not ready.", Main.getScreenWidth()/4-180, Main.getScreenHeight()/2);
        } else {
            g.drawString("Player 1 is ready!", Main.getScreenWidth()/4-180, Main.getScreenHeight()/2);
        }

        if (!p2isReady) {
            g.drawString("Player 2 is not ready.", Main.getScreenWidth()/4*3+180, Main.getScreenHeight()/2);
        } else {
            g.drawString("Player 2 is ready!", Main.getScreenWidth()/4*3+180, Main.getScreenHeight()/2);
        }


        g.drawString("Press the ; (semicolon) key to view the controls for the player", Main.getScreenWidth()-700, 100);

        if (showControls) {
            g.drawImage(Images.tutorialBackground, 0, 0);
            g.setColor(Color.black);
//            g.drawString("other controls not in the image because we didn't put them in for some reason lol,  \n player 1 ult -> q \n player 2 ult -> o \n player 1 specials -> x, c \n player 2 specials -> n, m \n p1 primary attacks -> e,f \n p2 primary attacks -> u,h",
//                    710,250);
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
