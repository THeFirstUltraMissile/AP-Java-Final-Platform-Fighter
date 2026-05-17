package core;

import core.player.Gojo;
import core.player.Player;
import core.player.Sukuna;
import core.projectile.Projectile;
import core.projectile.TestProjectile;
import core.ultimates.Ultimates;
import hitboxes.AttackHitBox;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import stages.ShibuyaStage;
import stages.Stage;
import stages.TestStage;
import stages.TetrisStage;

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

    public Player player1;
    public Player player2;


    AttackHitBox attackHitBox = new AttackHitBox();

    ArrayList<Projectile> projectiles = new ArrayList<>();

    Ultimates p1Ult;

    private boolean gameOver = false;
    private String winner = "";

    private int p1CharIndex = 0;
    private int p2CharIndex = 1;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gc.setShowFPS(true);
        Images.loadImages();
        Sounds.loadSounds();
        stages.add(new TestStage());
        stages.add(new TetrisStage());
        stages.add(new ShibuyaStage());

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gameOver) return;

        player1.step();
        player2.step();

        for(int i = 0; i < projectiles.size();i++)
        {
            projectiles.get(i).step();
        }

        Input input = gc.getInput();

        if (input.isKeyDown(Input.KEY_A)) {
            player1.playerLeft();
            player1.setFacingRight(false);
        } else if (input.isKeyDown(Input.KEY_D)) {
            player1.playerRight();
            player1.setFacingRight(true);
        }
        if (input.isKeyDown(Input.KEY_S)) {
            player1.crouch();
        } else {
            player1.unCrouch();
        }

        if (input.isKeyDown(Input.KEY_J)) {
            player2.playerLeft();
            player2.setFacingRight(false);
        } else if (input.isKeyDown(Input.KEY_L)) {
            player2.playerRight();
            player2.setFacingRight(true);
        }
        if (input.isKeyDown(Input.KEY_K)) {
            player2.crouch();
        } else {
            player2.unCrouch();
        }

        attackHitBox.checkAttackHit(player1, player2,
                player1.getAttackRadius(), player1.getAttackValue(), player1.getKbValue(), 10);

        attackHitBox.checkAttackHit(player2, player1,
                player2.getAttackRadius(), player2.getAttackValue(), player2.getKbValue(), 10); //light attack

        attackHitBox.checkAttackHit(player1, player2,
                30, 3, 2, 5);
        attackHitBox.checkAttackHit(player2, player1,
                30, 3, 2, 5); //aerial attack, radius 30 atk = 3, kb = 2


        attackHitBox.checkHeavyAttackHit(player1, player2, player1.getAttackRadius() * 1.5f, player1.getHeavyAttackValue(), player1.getHeavyKbValue(),20);
        attackHitBox.checkHeavyAttackHit(player2, player1, player2.getAttackRadius() * 1.5f, player2.getHeavyAttackValue(), player2.getHeavyKbValue(),20);
        //you can read (heavy attack)




        float hOverlap = player1.getRight() - player2.getX();
        float vOverlap = player1.getBottom() - player2.getY();

        boolean horizontallyOverlapping = player1.getX() < player2.getRight() && player1.getRight() > player2.getX();
        boolean verticallyOverlapping = player1.getY() < player2.getBottom() && player1.getBottom() > player2.getY();

        if (horizontallyOverlapping && verticallyOverlapping) {
            Player leftPlayer = player1.getX() <= player2.getX() ? player1 : player2;
            Player rightPlayer = leftPlayer == player1 ? player2 : player1;

            float overlap = leftPlayer.getRight() - rightPlayer.getX();
            leftPlayer.setX(leftPlayer.getX() - overlap / 2f);
            rightPlayer.setX(rightPlayer.getX() + overlap / 2f);
        }


        stages.get(StageSelect.stageChoice).updateStage(player1, player2);

        int screenH = Main.getScreenHeight();
        int screenW = Main.getScreenWidth();
        if (player1.getY() > screenH + 100 || player1.getX() > screenW + 200 || player1.getRight() < -200) {
            player1.stocks--;
            if(player1.stocks<=0)
            {
                gameOver = true;
                winner = "P2 WINS!";
            }
            else {
                resetPlayer(player1);
            }


        }
        if (player2.getY() > screenH + 100 || player2.getX() > screenW + 200 || player2.getRight() < -200) {
            player2.stocks--;
            if(player2.stocks<=0) {
                gameOver = true;
                winner = "P1 WINS!";
            }
            else {
                resetPlayer(player2);
            }
        }
        if(p1Ult!=null) {
            p1Ult.update();
        }

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        stages.get(StageSelect.stageChoice).renderStage(g);


        player1.draw(g);
        player2.draw(g);

        player1.drawAttack(g);
        player2.drawAttack(g);

        for(int i = 0; i < projectiles.size();i++)
        {
            projectiles.get(i).draw(g);
        }

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

        combatUI(g);

        if(p1Ult!=null)
        {
            p1Ult.render(g);
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

    private Player buildPlayer(int charIndex, int x, int y,int direction) throws SlickException {
        switch (charIndex) {
            case 1:  return new Gojo(x, y,direction);
            default: return new Sukuna(x, y,direction);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gameOver = false;
        winner = "";
        p1CharIndex = CharacterSelect.p1Choice;
        p2CharIndex = CharacterSelect.p2Choice;
        player1 = buildPlayer(p1CharIndex, (1920 / 3),     1080 / 2,1);
        player2 = buildPlayer(p2CharIndex, (1920 * 2 / 3), 1080 / 2,-1);
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
        switch (key) {


            // Controls : W and I are jump, A and J are move left, D and L are move right,
            // XC and NM are special buttons, QE and UO are attack buttons
            // e and u are also aerials

            //q and o will be for ults

            //player 1

            case Input.KEY_W:
                if (player1 != null) player1.jump();
                break;


            case Input.KEY_E:


                if(player1!=null&&!player1.getIsInAir()) player1.aerialAttack(35); //if in air do aerial else normie attack
                else {  if (player1 != null) player1.lightAttack(49); }
                break;

            case Input.KEY_F:
                if (player1 != null) player1.heavyAttack(70);
                break;

            case Input.KEY_Q:
                    if(player1!=null) //&&player1.getUlt()
                    {
                        player1.setHasUlt(false); // resets the ult charge
                        p1Ult = new Ultimates(player1,player2,player1.getX(),player1.getY()+25,35,20*player1.getFacing(),0,40,15);
                        System.out.println("p1 ult");
                    }
                    break;

                //player 2

            case Input.KEY_I:
                if (player2 != null) player2.jump();
                break;

            case Input.KEY_U:

                if(player2!=null&&player2.getIsInAir()) player2.aerialAttack(35);
                else{   if (player2 != null) { player2.lightAttack(49); } }

                break;

            case Input.KEY_H:
                if (player2 != null) player2.heavyAttack(70);
                break;


            case Input.KEY_O:
                if(player2!=null&&player2.getUlt())
                {
                    player2.setHasUlt(false);
                    player1.setX(10000);
                }
                break;





            case Input.KEY_R: //R is for Respawn
                if (gameOver) {
                    gameOver = false;
                    winner = "";
                    try {
                        player1 = buildPlayer(p1CharIndex, 1920 / 4, 1080 / 2,1);
                        player2 = buildPlayer(p2CharIndex, (1920 * 3 / 4)-25, 1080 / 2,-1);
                    } catch (SlickException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case Input.KEY_T:
                projectiles.add(new TestProjectile(player1.getX(),player1.getY(),32,32,32,15,0,15,15,player1.getFacing(),player1,player2));


            default:
        }
    }

    public void resetPlayer(Player p)
    {
        p.resetDamage();
        if(p == player1)
        {
            p.setX(1920 / 4);
        }
        if(p == player2)
        {
            p.setX((1920 * 3 / 4)-25);
        }
        p.setY(1080 / 2);
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
    ultLabels(g);
	}

    public void ultLabels(Graphics g) //this would be so much quicker in godot
    {
        g.setColor(Color.white);
        g.drawString(String.valueOf(player1.getUltCharge()),100,100);
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

    public Player getPlayer1()
    {
        return player1;
    }
    public Player getPlayer2()
    {
        return player2;
    }

}
