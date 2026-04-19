package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TestStage extends Stage {
    StageCollision testCollision;

    public TestStage() {
        name = "test";
        song = Sounds.TestSong;
        background = Images.background1;


        width = Main.getScreenWidth();
        height = Main.getScreenHeight() / 3;
        x = Main.getScreenHeight() / 3;
        y = Main.getScreenHeight() - height;

        testCollision = new StageCollision();


    }


    public void renderStage(Graphics g) {
        testCollision.makeBox(g, x, y, width, height);
        g.setColor(Color.green);
        g.fillRect(x, y, width, height);
        g.drawImage(background, 0, 0);


    }

    public void updateStage(Player p1, Player p2) {
        testCollision.collisionChecks(p1, x, y, width, height);
        testCollision.collisionChecks(p2, x, y, width, height);
    }

    public void playSong() {

        //song.loop();
    }
}
