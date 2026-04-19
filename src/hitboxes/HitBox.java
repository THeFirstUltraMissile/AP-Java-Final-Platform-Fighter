package hitboxes;

import core.Images;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;


public abstract class HitBox {

/* make a box
* check if something is in the box*/

 public void makeBox(Graphics g,int x, int y, int w, int h)
 {
     g.setColor(Color.blue);
     g.drawRect(x,y,w,h);
 }

 protected boolean isInBox(float inputX, float inPutY, int stageX,int stageY,int stageW,int stageH)
 {

         if(
                 inputX > stageX && 		    	// > Left
                 inputX < stageX + stageW &&	   	// > Right
                 inPutY > stageY &&			// > Top
                 inPutY < stageY + stageH)			// < Bottom
         {
             return true;
         }
             return false;
     }

}
