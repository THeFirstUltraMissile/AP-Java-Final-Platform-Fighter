package core;

import core.player.Player;
import core.player.Sukuna;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import stages.ShibuyaStage;
import stages.Stage;
import stages.TestStage;

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

	public ArrayList<Stage> stages = new ArrayList<>();

	private String stage = "nada"; //none selected

	Player player1;
	Player player2;

	private int stageInt = -1; //nothing


	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		Images.loadImages();
		Sounds.loadSounds();
		stages.add(new TestStage());
		stages.add(new ShibuyaStage());
		player1 = new Player(1920/4, 1080/2);
		player2 = new Player(1920 * 3/4, 1080/2);

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
        if (stage.equals("test")&&stage!=null) {
            stages.get(0).renderStage(g);

		}
		if(stage.equals("Shibuya")&&stage!=null){
			stages.get(1).renderStage(g);


		}
		player1.draw(g);
		player2.draw(g);

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{

	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{

	}

	public void keyPressed(int key, char c)// This code happens every time the user presses a key
	{

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
		}

		//If it crashes due to the null expression, just try to comment the bottom out until we can fix it
		if (player1 != null) {
			if (key == Input.KEY_SPACE) {
				player1.jump();
			}
		}
	}

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
	
	
//accessors
	public String getStage()

	{
		return stage;
	}






}
