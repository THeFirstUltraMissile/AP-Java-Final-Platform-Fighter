package core.ultimates;

import core.Images;
import core.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Cleave extends Ultimates{
    Player p;
    Player target;

    float x;
    float y;
    float r;

    float xSpeed;
    float ySpeed;

    float dmg;
    float kb;

    float duration;
    public Cleave( Player p, Player target, float x, float y, float r , float xSpeed, float ySpeed, float dmg, float kb) throws SlickException
    {
        super(p,target,x,y,r,xSpeed,ySpeed,dmg,kb);

        this.x = x;
        this.y = y-300;
        this.r = r;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.dmg = dmg;
        this.kb = kb;

        this.p = p;
        this.target = target;

        duration = 180;

        Images.cleaveAnimation.restart();
    }
    public void render(Graphics g)
    {
        if(duration()) {
            org.newdawn.slick.Image bodyFrame = Images.cleaveAnimation.getCurrentFrame();

            bodyFrame.draw(x, y, r, r);

//            g.setColor(Color.red);
//            g.drawRect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
//            g.drawRect(x, y, r, r);
        }
    }



    public void update() {
        if (duration()) {
            x += xSpeed;
            y += ySpeed;

            if (isOver()&&target.getCanBeHit()) {
                target.takeDamage((int) (dmg));
                target.applyKnockback(kbX(), -(kbX()/5));

                target.setIFrames(target.getIJamesMax());
                target.ChangeCanBeHit(false);

                System.out.println("ULT Ckeave HIT!");
                System.out.println(kbX());
            }

            Images.cleaveAnimation.update(16);
            duration--;
        }
    }
    public boolean isOver()
    {
        if(      x<target.getX()&&
                x+r>target.getX()&&
                target.getY() > y&&
                target.getY() < y+r
            &&
                target.getCanBeHit())
        {
            System.out.println("IsOver Cleave TRUE");
            return true;
        }
        return false;
    }

    public float kbX() {
        System.out.println("direction"+kbDir);
        System.out.println("dmg" + dmg);
        System.out.println("kb"+kb);
        System.out.println("hp"+(target.getDamage()+1));
        return (( dmg * kb*(target.getDamage()+1))/2500);
    }
    public boolean duration()
    {
        return duration >= 0;
    }
}
