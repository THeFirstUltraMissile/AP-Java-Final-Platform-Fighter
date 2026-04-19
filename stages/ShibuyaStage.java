package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ShibuyaStage extends Stage{


    StageCollision testCollision;

    public ShibuyaStage() {
        name = "Shibuya";
        song = Sounds.TestSong; //temp will make song later
        background = Images.blankBackground;


        width = Main.getScreenWidth();
        height = Main.getScreenHeight()/3;
        x = 0;
        y = Main.getScreenHeight()-height;
        testCollision = new StageCollision();


    }


    public void renderStage(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
        g.drawImage(background,1179,460);


    }

    public void updateStage(Player p1, Player p2) {
        testCollision.collisionChecks(p1, x, y, width, height);
        testCollision.collisionChecks(p2, x, y, width, height);
    }

    public void playSong() {
    //song.loop();
    }

}
