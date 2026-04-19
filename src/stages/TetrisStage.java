package stages;

import core.Images;
import core.Sounds;
import org.newdawn.slick.Graphics;

public class TetrisStage extends Stage{
    public TetrisStage()
    {
        name = "Tetris";
        song = Sounds.TestSong;
        background = Images.tetrisBackground;
    }

    public void renderStage(Graphics g) {
        g.drawImage(background,0,0);
    }


    public void updateStage() {

    }


    public void playSong() {
    song.loop();
    }
}
