package core.ultimates;

import core.Images;
import core.player.Player;
import org.newdawn.slick.*;

public class HollowPurple extends Ultimates{
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



    public HollowPurple(Player p, Player target, float x, float y, float r , float xSpeed, float ySpeed, float dmg, float kb) throws SlickException {
        super(p,target,x,y,r,xSpeed,ySpeed,dmg,kb);

        this.x = x;
        this.y = y-100 ;
        this.r = r;

        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        this.dmg = dmg;
        this.kb = kb;

        this.p = p;
        this.target = target;

        System.out.println("made hp ult");

        duration = 180;

        Images.hollowPurpleAnimation.restart();
    }

    public void render(Graphics g)
    {
        if(duration()) {
            org.newdawn.slick.Image bodyFrame = Images.hollowPurpleAnimation.getCurrentFrame();

            bodyFrame.draw(x, y, r, r);

            g.setColor(Color.red);
            g.drawRect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
            g.drawRect(x, y, r, r);
        }
    }



    public void update(){
        if(duration()){
        x+=xSpeed * p.getFacing();
        y+=ySpeed;

        xSpeed += 0.6f;
        if(isOver()&&target.getCanBeHit())
        {
            target.takeDamage((int)(dmg));
            target.applyKnockback(kbX(),-(kbX()/5));

            target.setIFrames(target.getIJamesMax());
            target.ChangeCanBeHit(false);

            System.out.println("ULT HIT!");
            System.out.println(kbX());
        }

        Images.hollowPurpleAnimation.update(16);
            duration--;
    }}

    public boolean isOver()
    {
        if(
                x >= target.getX()
                &&x <= target.getX()+target.getWidth()
            &&
                y+r>=target.getY()
                &&y-r<=target.getY()-target.getHeight()
        )
        {
            System.out.println("Purple Ult ISOVER TRUE");
            return true;
        }
        return false;
    }

    public float kbX() {
        System.out.println("direction"+kbDir);
        System.out.println("dmg" + dmg);
        System.out.println("kb"+kb);
        System.out.println("hp"+(target.getDamage()+1));
        return (xSpeed *( dmg * kb*(target.getDamage()+1))/2500)-46.8f;
    }
    public boolean duration()
    {
        return duration >= 0;
    }
}
