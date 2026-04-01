package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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

private int stageInt = -1; //nothing

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		Images.loadImages();
		Sounds.loadSounds();
		stages.add(new TestStage());
		stages.add(new ShibuyaStage());

	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{




	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
        if (stage.equals("test")&&stage!=null) {
            stages.get(0).renderStage(g);
        }
		if(stage.equals("Shibuya")&&stage!=null){
			stages.get(1).renderStage(g);
		}

	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{

	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{

	}

	public void keyPressed(int key, char c)// This code happens every time the user presses a key
	{
		switch(c)
		{
			case 'l':
				System.exit(0);
				break;

			case 'q':
				stage = "test";						//sets stage
//				stages.get(stageInt).song.stop();	//stops song
				stageInt = 0;						//changes stage
				stages.get(stageInt).playSong();	//starts new song
				break;
			case 'w':
				stage = "Shibuya";
//				stages.get(stageInt).song.stop();
				stageInt = 1;
				stages.get(stageInt).playSong();
				break;
			default:
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
