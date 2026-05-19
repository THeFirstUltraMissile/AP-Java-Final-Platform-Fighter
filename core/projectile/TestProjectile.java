package core.projectile;

import core.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TestProjectile extends Projectile{
    float x;
    float y;
    float r;
    float pxSpeed; //projectile x speed
    float pySpeed;
    float dmg;
    float kb;
    int direction;
    Player owner;
    Player target;
    public TestProjectile(float x, float y, int w, int h, float r,float xSpeed, float ySpeed, float damage, float knockback, int direction, Player owner, Player target)
    {
        super( x,  y, 32,32,32, xSpeed,ySpeed, damage, knockback,owner,target);
        this. x = x;
        this.y = y+75;
        this.w = w;
        this.h = h;
        this.r = r;
        pxSpeed = (float) xSpeed;
        pySpeed = ySpeed;

        dmg =  damage;
        kb  =  knockback;
        this.direction = direction;

        this.target = target;
        this.owner = owner;

    }
    public void draw(Graphics g) {
        g.drawOval(x,y,r,r);
        g.setColor(Color.red);
        g.drawRect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
        g.drawRect(x,y,r,r);

    }
    public void step()
    {

        x += pxSpeed * direction;
        y += pySpeed * direction;

        checkHit(target);
    }
}
