package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import player.Player;
import selection.CharacterSelection;
import selection.StageSelection;
import stages.Stage;

import java.util.ArrayList;

public class Game extends BasicGameState
{	
	private int id;
	public Game(int id)
	{
		this.id = id;
	}
	public int getID() 
	{
		return id;		
	}

		public static ArrayList<Stage> stages = StageSelection.getStages();
		public static ArrayList<Player> players = CharacterSelection.getPlayers();

		Player player1;
		Player player2;



	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);



//		System.out.println(CharacterSelection.returnPlayer1);
//		System.out.println(CharacterSelection.returnPlayer2);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		// At least this part is easier.

		player1.step();
		player2.step();

		Input input = gc.getInput();




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


		if (stageInt != -1) {
			stages.get(stageInt).updateStage(player1, player2);
		}


	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
        getStage().renderStage(g);
		combatUI(g);
		player1.draw(g);
		player2.draw(g);

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		player1 = players.get(0);
		player2 = players.get(1);
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{

	}

	public void keyPressed(int key, char c)
	{


		switch(c)
		{
			case 'q':
				System.exit(0);
				break;

			default:
		}
		switch(key)
		{
			case Input.KEY_1:
				stage = "test";
				stageInt = 0;
				stages.get(stageInt).playSong();
				break;

			case Input.KEY_2:
				stage = "Shibuya";
				stageInt = -1;
				break;

			case Input.KEY_W:
				if (player1 != null) player1.jump();
				break;
			case Input.KEY_A:
				if (player1 != null) player1.playerLeft();
				break;
			case Input.KEY_D:
				if (player1 != null) player1.playerRight();
				break;

			case Input.KEY_C:
				if (player1 != null) player1.lightAttack(10);
				break;

			case Input.KEY_I:
				if (player2 != null) player2.jump();
				break;
			case Input.KEY_J:
				if (player2 != null) player2.playerLeft();
				break;
			case Input.KEY_L:
				if (player2 != null) player2.playerRight();
				break;


			case Input.KEY_N:
				if (player2 != null) player2.lightAttack(10);
				break;

			default:
				if (player1 != null) {
					if (key == Input.KEY_SPACE) {
						player1.jump();
					}
				}
	}}

	public void keyReleased(int key, char c)
	{


		switch(key)
		{
			case Input.KEY_A:
			case Input.KEY_D:
				player1.stopMoving();
				break;

			case Input.KEY_J:
			case Input.KEY_L:
				player2.stopMoving();
				break;
		}
	}
	
	public void mousePressed(int button, int x, int y)
	{

	}
	


	public void combatUI(Graphics g)
	{
	playerMarkers(g);
	playerHealthIndicators(g);
	}
	public void playerMarkers(Graphics g)
	{
		 float middleX1 = player1.getX()+ (float) player1.getWidth() /2;
		g.drawLine(middleX1,player1.getY()-25,middleX1-30,player1.getY()-55);
		g.drawLine(middleX1,player1.getY()-25,middleX1+30,player1.getY()-55);
//		g.drawLine(middleX1+30,player1.getY()-55,middleX1-30,player1.getY()-55);
		g.setColor(Color.red);
		g.drawString("p1",middleX1-5,player1.getY()-60);

		float middleX2 = player2.getX()+ (float) player2.getWidth() /2;
		g.drawLine(middleX2,player2.getY()-25,middleX2-30,player2.getY()-55);
		g.drawLine(middleX2,player2.getY()-25,middleX2+30,player2.getY()-55);
//		g.drawLine(middleX2+30,player2.getY()-55,middleX2-30,player2.getY()-55);
		g.setColor(Color.blue);
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
//accessors
	public Stage getStage()
	{
		return stages.get(StageSelection.returnStage);
	}


}
