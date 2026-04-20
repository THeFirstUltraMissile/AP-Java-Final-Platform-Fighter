package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ShibuyaStage extends Stage {
    StageCollision collision;

    public ShibuyaStage() {
        name = "Shibuya";
        song = Sounds.TestSong;
        background = Images.blankBackground;

        width = Main.getScreenWidth();
        height = Main.getScreenHeight() / 8;
        x = 0;
        y = Main.getScreenHeight() - height;

        collision = new StageCollision();
    }

    public void renderStage(Graphics g) {
        g.setColor(new Color(30, 30, 60));
        g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
        g.setColor(new Color(100, 100, 160));
        g.fillRect(x, y, width, height);
    }

    public void updateStage(Player p1, Player p2) {
        collision.collisionChecks(p1, x, y, width, height);
        collision.collisionChecks(p2, x, y, width, height);
    }

    public void playSong() {
        // song.loop();
    }
}
