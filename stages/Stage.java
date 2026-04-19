package stages;

import core.Images;
import core.player.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public abstract class Stage {
    Image background = Images.blankBackground;
    public  int x;
    public  int y;
    public  int width;
    public int height;

    public String name;
    public Music song;

    public abstract void renderStage(Graphics g);

    public abstract void updateStage(Player p1, Player p2);

    public abstract void playSong();


}

