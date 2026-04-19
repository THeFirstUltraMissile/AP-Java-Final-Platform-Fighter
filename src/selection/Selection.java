package selection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Selection extends BasicGameState {
    private int id;
    public Selection(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }



    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }
}
