package core.ultimates;

import core.player.Player;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Ultimates {

    protected float xSpeed;
    protected float ySpeed;

    protected float x;
    protected float y;

    protected float r;

    Player p;
    Player target;

    protected float kbDir;
    protected float dmg;
    protected float kb;



    public Ultimates(Player p,Player target,float x, float y,float r ,float xSpeed, float ySpeed, float damage, float kb)
    {
        //from parameter
        this.p = p;
        this.target = target;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.r = r;

        this.x = x;
        this.y = y;

        dmg = damage;
        this.kb = kb;

        //other var

        kbDir = target.getFacing() * -1; //flips direction


    }

    public void update()
    {
       x+=xSpeed;
       y+=ySpeed;

       if(isOver())
       {
           target.takeDamage((int)(dmg));
           target.applyKnockback(kbX(),-5);
           System.out.println("ULT HIT!");
           System.out.println(kbX());
       }


    }

    public void render(Graphics g)
    {
        g.drawOval(x,y,r,r);
        g.setColor(Color.red);
        g.drawRect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
        g.drawRect(x,y,r,r);
    }

    public boolean isOver()
    {
        if(       x >= target.getX()
                &&x+r <= target.getX()+target.getWidth()
                &&y>=target.getY()
                &&y+r<=target.getY()+target.getHeight())
        {
            return true;
        }
        return false;
    }

    public float kbX(){
        System.out.println("direction"+kbDir);
        System.out.println("dmg" + dmg);
        System.out.println("kb"+kb);
        System.out.println("hp"+target.getDamage());
   return (xSpeed *( dmg * kb*(target.getDamage()+1))/2500)-46.8f;}
}
