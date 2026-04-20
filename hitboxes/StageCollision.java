package hitboxes;

import core.player.Player;
import org.newdawn.slick.Graphics;

public class StageCollision extends Collision{

    public StageCollision()
    {
        //might use later
    }

    public void collisionChecks(Player player, int stageX, int stageY, int stageW, int stageH) {

        if (isInBox(player.getX() + (float) player.getWidth() /2, player.getBottom(), stageX, stageY, stageW, stageH)) {
            if (player.getYAccel() >= 0) {
                player.setY(stageY - player.getHeight());
                player.setVerticalSpeed(0);
            }
        }


        if (isInBox(player.getRight(), player.getY() + (float) player.getHeight() /2, stageX, stageY, stageW, stageH)) {
            player.setX(stageX - player.getWidth());
            player.setHorizontalSpeed(0);
        }


        if (isInBox(player.getX(), player.getY() + player.getHeight()/2, stageX, stageY, stageW, stageH)) {
            player.setX(stageX + stageW);
            player.setHorizontalSpeed(0);
        }
    }
    }




