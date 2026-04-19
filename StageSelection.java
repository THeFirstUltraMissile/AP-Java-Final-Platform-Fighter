package selection;

import core.Game;
import core.Images;
import core.Main;
import core.Sounds;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import stages.ShibuyaStage;
import stages.ShrineStage;
import stages.Stage;
import stages.TestStage;

import java.util.ArrayList;

public class StageSelection extends BasicGameState {
    private int id;
    StateBasedGame sbg;
    public StageSelection(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }

    public static ArrayList<Stage> stages = new ArrayList<>();
    public static int selected = 0;

    public SelectionSquare selectionSquare = new SelectionSquare();

    public static int returnStage = 0;

    Color backgroundColor = new Color(105, 199, 230);

    int iconD1 = 200;
    int iconD2 = 700;


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Images.loadImages();
        Sounds.loadSounds();

        stages.add(new ShibuyaStage());
        stages.add(new ShrineStage());
        stages.add(new TestStage());
        stages.add(new TestStage());

        this.sbg = sbg;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
g.setBackground(backgroundColor);


        g.drawImage(Images.gojoIcon,iconD1,iconD1);
        g.drawImage(Images.sukunaIcon,iconD2,iconD1);
        g.drawImage(Images.mikuIcon,iconD1,iconD2);
        g.drawImage(Images.tSpunnedIcon,iconD2,iconD2);

        g.setColor(Color.green);
        g.fillRect(Main.getScreenWidth()*0.66f,25,Main.getScreenWidth()/3,Main.getScreenHeight()-50);

        selectionSquare.render(g);

        g.drawString(String.valueOf(selected),1500,500); //test
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }


    public static ArrayList<Stage> getStages()
    {
        return stages;
    }
    public void keyPressed(int key, char c) {
    switch (c)
    {
        case 'd':
            if(selected==3)
            {
            selected = 0;
            selectionSquare.changeCords(selected);
            }
            else{
            if(selected<3){
            selected++;
            selectionSquare.changeCords(selected);
            }}

            break;

        case 'a':
            if(selected==0)
            {
                selected = 3;
                selectionSquare.changeCords(selected);
            }
            else{
            if(selected>0) {
                selected--;
                selectionSquare.changeCords(selected);
            }}

            break;

        case ' ':
            {
            sbg.enterState(Main.GAME_ID);
            }
        default: break;
    }

    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {
        returnStage = selected;

    }
    public static int getReturnStage()
    {
        return returnStage = selected;
    }
}
