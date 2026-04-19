package hitboxes;

import org.newdawn.slick.Graphics;

public class StageCollision extends Collision{

    public StageCollision()
    {
        //might use later
    }

    public void collisionChecks(float playerX,float playerY,int x,int y,int w,int h) //checks if it's inside the hitbox and acts accordingly
    {
        if(isInBox(playerX,playerY,x,y,w,h))
        {
            if(goingDown()) //send player up
            {

            }
            if(goingLeft()) //send player right
            {

            }
            if(goingRight()) //send player left
            {

            }
        }
    }



}
