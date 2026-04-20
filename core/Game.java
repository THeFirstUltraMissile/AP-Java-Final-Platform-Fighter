package core;

import core.player.Gojo;
import core.player.Player;
import core.player.Sukuna;
import hitboxes.AttackHitBox;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import stages.ShibuyaStage;
import stages.Stage;
import stages.TestStage;

import java.util.ArrayList;

public class Game extends BasicGameState {
    private int id;

    public Game(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public ArrayList<Stage> stages = new ArrayList<>();

    private int stageInt = 0;

    Player player1;
    Player player2;

    AttackHitBox attackHitBox = new AttackHitBox();

    private boolean gameOver = false;
    private String winner = "";

    private int p1CharIndex = 0;
    private int p2CharIndex = 1;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setShowFPS(true);
        Images.loadImages();
        Sounds.loadSounds();
        stages.add(new TestStage());
        stages.add(new ShibuyaStage());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gameOver) return;

        player1.step();
        player2.step();

        Input input = gc.getInput();

        // Player 1 movement
        if (input.isKeyDown(Input.KEY_A)) {
            player1.playerLeft();
        } else if (input.isKeyDown(Input.KEY_D)) {
            player1.playerRight();
        }
        if (input.isKeyDown(Input.KEY_S)) {
            player1.crouch();
        } else {
            player1.unCrouch();
        }

        // Player 2 movement
        if (input.isKeyDown(Input.KEY_J)) {
            player2.playerLeft();
        } else if (input.isKeyDown(Input.KEY_L)) {
            player2.playerRight();
        }
        if (input.isKeyDown(Input.KEY_K)) {
            player2.crouch();
        } else {
            player2.unCrouch();
        }

        attackHitBox.checkAttackHit(player1, player2,
                player1.getAttackRadius(), player1.getAttackValue(), player1.getKbValue(), 0, 10);
        attackHitBox.checkAttackHit(player2, player1,
                player2.getAttackRadius(), player2.getAttackValue(), player2.getKbValue(), 0, 10);

        float hOverlap = player1.getRight() - player2.getX();
        float vOverlap = player1.getBottom() - player2.getY();

        boolean horizontallyOverlapping = player1.getX() < player2.getRight() && player1.getRight() > player2.getX();
        boolean verticallyOverlapping   = player1.getY() < player2.getBottom() && player1.getBottom() > player2.getY();

        if (horizontallyOverlapping && verticallyOverlapping) {
            Player leftPlayer  = player1.getX() <= player2.getX() ? player1 : player2;
            Player rightPlayer = leftPlayer == player1 ? player2 : player1;

            float overlap = leftPlayer.getRight() - rightPlayer.getX();
            leftPlayer.setX(leftPlayer.getX()   - overlap / 2f);
            rightPlayer.setX(rightPlayer.getX() + overlap / 2f);
        }

        stages.get(stageInt).updateStage(player1, player2);

        int screenH = Main.getScreenHeight();
        int screenW = Main.getScreenWidth();
        if (player1.getY() > screenH + 100 || player1.getX() > screenW + 200 || player1.getRight() < -200) {
            gameOver = true;
            winner = "P2 WINS!";
        }
        if (player2.getY() > screenH + 100 || player2.getX() > screenW + 200 || player2.getRight() < -200) {
            gameOver = true;
            winner = "P1 WINS!";
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        stages.get(stageInt).renderStage(g);
        combatUI(g);

        player1.draw(g);
        player2.draw(g);

        player1.drawAttack(g);
        player2.drawAttack(g);

        drawIcons(g);

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
            g.setColor(Color.white);
            g.drawString(winner,
                    Main.getScreenWidth() / 2f - 60,
                    Main.getScreenHeight() / 2f - 20);
            g.drawString("Press R to restart",
                    Main.getScreenWidth() / 2f - 70,
                    Main.getScreenHeight() / 2f + 20);
        }
    }

    private void drawIcons(Graphics g) {
        // player 1 and 2 icon
        Image p1Icon = p1CharIndex == 1 ? Images.gojoIcon : Images.sukunaIcon;
        g.setColor(Color.white);
        g.drawString("P1", 60, 30);
        g.drawImage(p1Icon, 30, 50, 30 + 80, 50 + 80, 0, 0, 512, 512);
        g.setColor(Color.red);
        g.drawString(player1.getDamage() + "%", 120, 80);

        Image p2Icon = p2CharIndex == 1 ? Images.gojoIcon : Images.sukunaIcon;
        int p2X = Main.getScreenWidth() - 200;
        g.setColor(Color.white);
        g.drawString("P2", p2X + 60, 30);
        g.drawImage(p2Icon, p2X, 50, p2X + 80, 50 + 80, 0, 0, 512, 512);
        g.setColor(Color.red);
        g.drawString(player2.getDamage() + "%", p2X + 90, 80);
    }

    private Player buildPlayer(int charIndex, int x, int y) throws SlickException {
        switch (charIndex) {
            case 1:  return new Gojo(x, y);
            default: return new Sukuna(x, y);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gameOver = false;
        winner = "";
        p1CharIndex = CharacterSelect.p1Choice;
        p2CharIndex = CharacterSelect.p2Choice;
        player1 = buildPlayer(p1CharIndex, 1920 / 4,     1080 / 2);
        player2 = buildPlayer(p2CharIndex, 1920 * 3 / 4, 1080 / 2);
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
        switch (key) {


            // Controls : W and I are jump, A and J are move left, D and L are move right,
            // XC and NM are special buttons, QE and UO are attack buttons


            case Input.KEY_1:
                stageInt = 0;
                break;

            case Input.KEY_2:
                stageInt = 1;
                break;

            case Input.KEY_W:
                if (player1 != null) player1.jump();
                break;

            case Input.KEY_E:
                if (player1 != null) player1.lightAttack(49);
                break;

            case Input.KEY_I:
                if (player2 != null) player2.jump();
                break;

            case Input.KEY_U:
                if (player2 != null) player2.lightAttack(49);
                break;

            case Input.KEY_R: //R is for Respawn
                if (gameOver) {
                    gameOver = false;
                    winner = "";
                    try {
                        player1 = buildPlayer(p1CharIndex, 1920 / 4,     1080 / 2);
                        player2 = buildPlayer(p2CharIndex, 1920 * 3 / 4, 1080 / 2);
                    } catch (SlickException e) { e.printStackTrace(); }
                }
                break;

            default:
        }
    }

    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_A:
            case Input.KEY_D:
                if (player1 != null) player1.stopMoving();
                break;

            case Input.KEY_J:
            case Input.KEY_L:
                if (player2 != null) player2.stopMoving();
                break;
        }
    }

    public void combatUI(Graphics g)
	{
	playerMarkers(g);
	playerHealthIndicators(g);
	}
	public void playerMarkers(Graphics g)
	{
		 float middleX1 = player1.getX()+ (float) player1.getWidth() /2;
		g.setColor(Color.red);
		g.drawLine(middleX1,player1.getY()-25,middleX1-30,player1.getY()-55);
		g.drawLine(middleX1,player1.getY()-25,middleX1+30,player1.getY()-55);
		g.drawString("p1",middleX1-5,player1.getY()-60);

		float middleX2 = player2.getX()+ (float) player2.getWidth() /2;
		g.setColor(Color.blue);
		g.drawLine(middleX2,player2.getY()-25,middleX2-30,player2.getY()-55);
		g.drawLine(middleX2,player2.getY()-25,middleX2+30,player2.getY()-55);
		g.drawString("p2",middleX2-5,player2.getY()-60);
	}
	public void playerHealthIndicators(Graphics g)
	{
		float x1 = Main.getScreenWidth()*0.33f-64;
		float x2 = Main.getScreenWidth()*0.66f-64;
		g.setColor(Color.red);
		g.fillRect(x1,950,128,128);
		g.setColor(Color.white);
		g.drawString("p1",x1+64,975);
		g.drawString(String.valueOf(player1.getDamage()),x1+64,1040);

		g.setColor(Color.blue);
		g.fillRect(x2,950,128,128);
		g.setColor(Color.white);
		g.drawString("p2",x2+64,975);
		g.drawString(String.valueOf(player2.getDamage()),x2+64,1040);
	}

    
    public void mousePressed(int button, int x, int y) {
    }
}
