package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CharacterSelect extends BasicGameState {

    private int id;

    private int p1Cursor = 0;
    private int p2Cursor = 1;

    public static int p1Choice = -1;
    public static int p2Choice = -1;

    // -1 = no choice yet

    private static final int NUM_CHARS = 2;
    private static final String[] CHAR_NAMES = { "Sukuna", "Gojo" };

    // adding two later
    // to add a choice, increase the numchar, add a charname, and add a portrait

    private Image[] portraits;
    private boolean readyToAdvance = false;

    public CharacterSelect(int id) { this.id = id; }

    @Override public int getID() { return id; }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        portraits = new Image[NUM_CHARS];
        portraits[0] = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
        portraits[1] = new Image("media/sprites/kaisen/gojosatoru/assets_gojosatoru/icon_gojosatoru512.png");
     //   portraits[2] = new Image("media/sprites/kaisen/ryomensukuna/assets/icon_ryomensukuna512.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (readyToAdvance && p1Choice >= 0 && p2Choice >= 0) {
            readyToAdvance = false;
            sbg.enterState(Main.STAGE_SELECT_ID);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int W = gc.getWidth();
        int H = gc.getHeight();

        g.setColor(new Color(15, 15, 30));
        g.fillRect(0, 0, W, H);
        g.setColor(Color.white);
        g.drawString("CHARACTER SELECT", W / 2f - 90, 40);

        int portraitSize = 200;
        int portraitY = H / 2 - portraitSize / 2;
        drawColumn(g, "P1   [A][D] browse   [W] confirm", W / 4, portraitY, portraitSize, p1Cursor, p1Choice >= 0);
        drawColumn(g, "P2   [J][L] browse   [I] confirm", W * 3 / 4, portraitY, portraitSize, p2Cursor, p2Choice >= 0);

        if (p1Choice >= 0 && p2Choice >= 0) {
            g.setColor(Color.yellow);
            g.drawString("Both ready!  Press ENTER to continue", W / 2f - 170, H - 80);
        } else {
            g.setColor(new Color(160, 160, 160));
            g.drawString("Both players must confirm a character", W / 2f - 170, H - 80);
        }
    }

    private void drawColumn(Graphics g, String label, int cx, int portraitY, int size, int cursor, boolean confirmed) {
        g.setColor(Color.white);
        g.drawString(label, cx - 150, portraitY - 60);
        g.setColor(confirmed ? Color.yellow : Color.cyan);
        String name = CHAR_NAMES[cursor];
        g.drawString(name, cx - name.length() * 4, portraitY - 30);
        g.setColor(confirmed ? Color.yellow : Color.white);
        g.drawRect(cx - size / 2 - 3, portraitY - 3, size + 6, size + 6);
        g.drawImage(portraits[cursor], cx - size / 2, portraitY, cx - size / 2 + size, portraitY + size, 0, 0, 512, 512);
        if (confirmed) {
            g.setColor(Color.yellow);
            g.drawString("READY!", cx - 25, portraitY + size + 10);
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        p1Choice = -1;
        p2Choice = -1;
        p1Cursor = 0;
        p2Cursor = 1;
        readyToAdvance = false;
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_A: p1Cursor = (p1Cursor - 1 + NUM_CHARS) % NUM_CHARS; p1Choice = -1; break;
            case Input.KEY_D: p1Cursor = (p1Cursor + 1) % NUM_CHARS; p1Choice = -1; break;
            case Input.KEY_W: p1Choice = p1Cursor; break;
            case Input.KEY_J: p2Cursor = (p2Cursor - 1 + NUM_CHARS) % NUM_CHARS; p2Choice = -1; break;
            case Input.KEY_L: p2Cursor = (p2Cursor + 1) % NUM_CHARS; p2Choice = -1; break;
            case Input.KEY_I: p2Choice = p2Cursor; break;
            case Input.KEY_ENTER: if (p1Choice >= 0 && p2Choice >= 0) readyToAdvance = true; break;
        }
    }
}
