package selection;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SelectionSquare {
    public int x;
    public int y;

    public final int Dimension = 300;

    public Color color;

    public SelectionSquare()
    {
        x = 180;
        y = 180;

        color = Color.white;

    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.drawRect(x,y,Dimension,Dimension);
    }
    public void changeColor(int c)
    {
        if(c==1)
        {
            color = Color.white;
        }
        if(c==2)
        {
            color = Color.blue;
        }
        if(c==3)
        {
            color = Color.red;
        }
    }
    public void changeCords(int s)
    {
        switch(s){

            case 0:
            x = 180;
            y = 180;
            break;

            case 1:
                x = 680;
                y = 180;
                break;

            case 2:
                x = 180;
                y = 680;
                break;

            case 3:
                x = 680;
                y = 680;
                break;



        }
    }
}
