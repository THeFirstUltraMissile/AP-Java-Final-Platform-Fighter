package core.projectile;

import core.player.Player;
import org.newdawn.slick.Graphics;

public class TestProjectile extends Projectile{
    float x;
    float y;
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
        g.drawOval(x,y,25,25);
    }
    public void step()
    {

        x += pxSpeed * direction;
        y += pySpeed * direction;

        attackHitBox.checkAttackHit(owner,target,r,dmg,kb,0);
        checkHit(target);
    }
}
