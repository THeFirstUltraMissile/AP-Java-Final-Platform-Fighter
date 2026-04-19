package selection;

import core.Images;
import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import player.*;

import java.util.ArrayList;

public class CharacterSelection extends BasicGameState {
    private int id;
    StateBasedGame sbg;
    public CharacterSelection(int id)
    {

        this.id = id;
    }
    public int getID()
    {
        return id;
    }

    public static ArrayList<Player> characters = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    public static int selected1 = 0;
    public static int selected2 = 0;

    boolean player1Picking = true;

    public static int returnPlayer1 =0;
    public static int returnPlayer2 =0;

    public SelectionSquare selectionSquare1 = new SelectionSquare();
    public SelectionSquare selectionSquare2 = new SelectionSquare();

    Color backgroundColor = new Color(105, 199, 230);
    SpriteSheet t;
    Animation tAnimation;

    int iconD1 = 200;
    int iconD2 = 700;


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Images.loadImages();
        t = Images.tIconAnimation;
        tAnimation = new Animation(t,450);
        characters.add(new Gojo(100,100));
        characters.add(new Sukuna(200,200));
        characters.add(new Miku(300,300));
        characters.add(new T_Spuned(400,400));
        this.sbg = sbg;

    }
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(backgroundColor);
        g.drawString("CHARACTER SELECTION.DEBUG.STRING",Main.getScreenWidth()/2,50);


        g.drawImage(Images.gojoIcon,iconD1,iconD1);
        g.drawImage(Images.sukunaIcon,iconD2,iconD1);
        g.drawImage(Images.mikuIcon,iconD1,iconD2);
        tAnimation.draw(iconD2,iconD2);

        g.setColor(Color.green);
        g.fillRect(Main.getScreenWidth()*0.66f,25,Main.getScreenWidth()/3,Main.getScreenHeight()-50);

        if(!player1Picking) {
            selectionSquare2.changeColor(3);
            selectionSquare2.render(g);
        }
        selectionSquare1.changeColor(2);
        selectionSquare1.render(g);


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public static ArrayList<Player> getCharacters()
    {
        return characters;
    }

    public void keyPressed(int key, char c) {
        if(player1Picking) {
            playerOnePicking(c);
        }
        else {
            playerTwoPicking(c);
        }
    }
    public void playerTwoPicking(char c)
    {
        switch (c) {
            case 'l':
                if(selected2==3)
                {
                    selected2 = 0;
                    selectionSquare2.changeCords(selected2);
                }
                else{
                    if(selected2<3){
                        selected2++;
                        selectionSquare2.changeCords(selected2);
                    }}

                break;

            case 'j':
                if(selected2==0)
                {
                    selected2 = 3;
                    selectionSquare2.changeCords(selected2);
                }
                else{
                    if(selected2>0) {
                        selected2--;
                        selectionSquare2.changeCords(selected2);
                    }}

                break;

            case ' ':
            {
                players.add(characters.get(selected2));
                sbg.enterState(Main.STAGESELCETION_ID);
            }
            default: break;
        }
    }
    public void playerOnePicking(char c)
    {
        switch (c) {
            case 'd':
                if(selected1==3)
                {
                    selected1 = 0;
                    selectionSquare1.changeCords(selected1);
                }
                else{
                    if(selected1<3){
                        selected1++;
                        selectionSquare1.changeCords(selected1);
                    }}

                break;

            case 'a':
                if(selected1==0)
                {
                    selected1 = 3;
                    selectionSquare1.changeCords(selected1);
                }
                else{
                    if(selected1>0) {
                        selected1--;
                        selectionSquare1.changeCords(selected1);
                    }}

                break;

            case ' ':
            {
                player1Picking = false;
                players.add(characters.get(selected1));

            }
            default: break;
        }
    }

    public void leave(GameContainer gc, StateBasedGame sbg)
    {



    }
    public static ArrayList<Player> getPlayers()
    {
        return players;
    }
    public static int getSelected1()
    {
        return selected1;
    }
    public static int getSelected2()
    {
        return selected2;
    }
}
