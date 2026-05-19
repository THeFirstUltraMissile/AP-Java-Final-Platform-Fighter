package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ShibuyaStage extends Stage {
    StageCollision collision1;
    StageCollision collision2;

    public ShibuyaStage() {
        name = "Conflicting Evils";
        song = Sounds.TestSong;
        background = Images.terraBackground;

        width = Main.getScreenWidth();
        height = (Main.getScreenHeight() / 4)+30;
        x = 0;
        y = Main.getScreenHeight() - height;

        collision1 = new StageCollision();
        collision2 = new StageCollision();
    }

    public void renderStage(Graphics g) {
       g.drawImage(background,0,0);

        g.drawRect(x,y,780,height);
       g.drawRect(1140,y,Main.getScreenWidth()-1140,height);

    }

    public void updateStage(Player p1, Player p2) {
        collision1.collisionChecks(p1, x, y, 780, height);
        collision1.collisionChecks(p2, x, y, 780, height);

        collision2.collisionChecks(p1,1140,y,Main.getScreenWidth()-1140,height);
        collision2.collisionChecks(p2,1140,y,Main.getScreenWidth()-1140,height);

    }

    public void playSong() {
        // song.loop();
    }
}
