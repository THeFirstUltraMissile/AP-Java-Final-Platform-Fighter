package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TetrisStage extends Stage{
    StageCollision testCollision;

    public TetrisStage() {
        name = "Tetris";
        song = Sounds.TestSong;
        background = Images.tetrisBackground;


        width = (Main.getScreenWidth()/2)+75;
        height = Main.getScreenHeight() / 3;
        x = (Main.getScreenHeight() / 3)+100;
        y = Main.getScreenHeight() - height;

        testCollision = new StageCollision();


    }


    public void renderStage(Graphics g) {
        testCollision.makeBox(g, x, y, width, height);
        g.drawImage(Images.tetrisLand, 0, 0);
        //g.setColor(Color.blue);
        //g.drawRect(x, y, width, height);
        g.drawImage(background, 0, 225);


    }

    public void updateStage(Player p1, Player p2) {
        testCollision.collisionChecks(p1, x, y, width, height);
        testCollision.collisionChecks(p2, x, y, width, height);
    }

    public void playSong() {

        //song.loop();
    }
}
