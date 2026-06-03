package stages;

import core.Images;
import core.Main;
import core.Sounds;
import core.StageSelect;
import core.player.Player;
import hitboxes.StageCollision;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class MusicStage extends Stage{
    StageCollision MainCollision;
    StageCollision LeftCollision;
    StageCollision RightCollision;
    StageCollision PlatFormCollision;

    int mainX;
    int mainY;

    public MusicStage() {
        name = "Music";
        song = Sounds.TestSong;
        background = Images.MusicStage;


        width = (int) (Main.getScreenWidth()*0.33f+25);
        height = (Main.getScreenHeight() / 3)/2;
        mainX = (int)(Main.getScreenWidth()*0.33f);
        mainY = Main.getScreenHeight() - height;

        MainCollision = new StageCollision();
        LeftCollision = new StageCollision();
        RightCollision = new StageCollision();
        PlatFormCollision = new StageCollision();


    }


    public void renderStage(Graphics g) {
        g.drawImage(background, 0, 0);
//       g.setColor(Color.red);
//       g.drawRect(mainX,mainY,width,height);
//       g.drawRect (190,mainY-100,width/3+10,height+(height/2));
//       g.drawRect(1920-415,mainY-100,width/3+10,height+(height/2));
//       g.drawRect(mainX+42,mainY-235,width-90,15);


    }

    public void updateStage(Player p1, Player p2) {
        MainCollision.collisionChecks(p1, mainX, mainY, width, height);
        MainCollision.collisionChecks(p2, mainX, mainY, width, height);

        LeftCollision.collisionChecks(p1,190,mainY-100,width/3+10,height+(height/2));
        LeftCollision.collisionChecks(p2,190,mainY-100,width/3+10,height+(height/2));

        RightCollision.collisionChecks(p1,1920-415,mainY-100,width/3+10,height+(height/2));
        RightCollision.collisionChecks(p2,1920-415,mainY-100,width/3+10,height+(height/2));

//        PlatFormCollision.collisionChecks(p1,mainX+42,mainY-235,width-90,15);
//        PlatFormCollision.collisionChecks(p2,mainX+42,mainY-235,width-90,15);
    }
    public void playSong() {

        //song.loop();
    }
}
