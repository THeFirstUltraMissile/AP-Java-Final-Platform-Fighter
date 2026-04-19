package stages;

import core.Images;
import core.Main;
import core.Sounds;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ShrineStage  extends Stage{
    Image scaledBackground;
    public ShrineStage() {
        name = "Shrine";
        song = Sounds.TestSong; //temp will make song later
        background = Images.shrineBackground;


        width = Main.getScreenWidth();
        height = Main.getScreenHeight()/3;
        x = 0;
        y = Main.getScreenHeight()-height;


    }


    public void renderStage(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x,y,width,height);
        g.drawImage(background,0,0);


    }

    public void updateStage() {

    }

    public void playSong() {
        song.loop();
    }

}
