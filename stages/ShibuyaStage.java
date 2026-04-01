package stages;

import core.Images;
import core.Main;
import core.Sounds;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ShibuyaStage extends Stage{
    public ShibuyaStage() {
        name = "Shibuya";
        song = Sounds.TestSong; //temp will make song later
        background = Images.blankBackground;


        width = Main.getScreenWidth();
        height = Main.getScreenHeight()/3;
        x = 0;
        y = Main.getScreenHeight()-height;



    }


    public void renderStage(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
        g.drawImage(background,1179,460);


    }

    public void updateStage() {

    }

    public void playSong() {
    song.loop();
    }

}
