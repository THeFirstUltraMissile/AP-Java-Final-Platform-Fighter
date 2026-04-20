package core;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StageSelect extends BasicGameState {

    private int id;
    private int cursor = 0;
    private boolean readyToAdvance = false;

    public static int stageChoice = 0;

    private static final int NUM_STAGES = 2;
    private static final String[] STAGE_NAMES  = { "Malfunctioning Shrine", "Shibuya" };
    private static final String[] STAGE_DESC   = {
            "Sukuna cast his Domain Expansion, Malevolent Shrine, but due to the brain damage he had acquired not long ago, his Shrine won't obey his commands..",
            "The Shibuya Incident in it's glory, at floor B5 right when the Cursed Spirit Mahito arrives"

    };

    private Image[] stagePreviews;

    public StageSelect(int id) { this.id = id; }

    @Override public int getID() { return id; }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        stagePreviews = new Image[NUM_STAGES];
        stagePreviews[0] = new Image("media/sprites/stages/stage_malfunctioningshrine.png");
        stagePreviews[1] = new Image("media/blankBackground.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (readyToAdvance) {
            readyToAdvance = false;
            stageChoice = cursor;
            sbg.enterState(Main.GAME_ID);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int W = gc.getWidth();
        int H = gc.getHeight();

        g.setColor(new Color(15, 15, 30));
        g.fillRect(0, 0, W, H);
        g.setColor(Color.white);
        g.drawString("STAGE SELECT", W / 2f - 60, 40);
        g.setColor(new Color(160, 160, 160));
        g.drawString("[A] [D] or [J] [L] to browse   ENTER to confirm", W / 2f - 220, 70);

        int previewW = 600;
        int previewH = 338;
        int previewX = W / 2 - previewW / 2;
        int previewY = H / 2 - previewH / 2;

        // Preview image
        g.drawImage(stagePreviews[cursor], previewX, previewY, previewX + previewW, previewY + previewH, 0, 0,
                (float)stagePreviews[cursor].getWidth(), (float)stagePreviews[cursor].getHeight());

        g.setColor(Color.yellow);
        g.drawRect(previewX - 2, previewY - 2, previewW + 4, previewH + 4);

        g.setColor(Color.white);
        g.drawString(STAGE_NAMES[cursor], W / 2f - STAGE_NAMES[cursor].length() * 4, previewY - 30);
        g.setColor(new Color(200, 200, 200));
        g.drawString(STAGE_DESC[cursor], W / 2f - STAGE_DESC[cursor].length() * 4, previewY + previewH + 20);
        g.drawString("Press Enter to Confirm",855, 880);

        g.setColor(cursor > 0 ? Color.white : new Color(60, 60, 60));
        g.drawString("<", previewX - 30, previewY + previewH / 2);
        g.setColor(cursor < NUM_STAGES - 1 ? Color.white : new Color(60, 60, 60));
        g.drawString(">", previewX + previewW + 15, previewY + previewH / 2);

        for (int i = 0; i < NUM_STAGES; i++) {
            g.setColor(i == cursor ? Color.yellow : new Color(80, 80, 80));
            g.fillOval(W / 2f - (NUM_STAGES * 20) / 2f + i * 20, previewY + previewH + 50, 10, 10);
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cursor = 0;
        readyToAdvance = false;
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case Input.KEY_A: case Input.KEY_J:
                if (cursor > 0) cursor--;
                break;
            case Input.KEY_D: case Input.KEY_L:
                if (cursor < NUM_STAGES - 1) cursor++;
                break;
            case Input.KEY_ENTER:
                readyToAdvance = true;
                break;
        }
    }
}
