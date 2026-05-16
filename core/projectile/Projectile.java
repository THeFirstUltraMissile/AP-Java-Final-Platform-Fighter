package core.projectile;
import core.player.Player;
import hitboxes.AttackHitBox;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

public abstract class Projectile {

    protected float x;
    protected float y;
    protected int w;
    protected int h;
    protected float r;

    private float speedX;
    private float speedY;
    private float damage;
    private float knockback;

    AttackHitBox attackHitBox = new AttackHitBox();

    Player owner;
    Player target;


    private boolean active;

    public Projectile(float x, float y, int w, int h, float r, float xSpeed, float ySpeed, float damage, float knockback, Player owner, Player target) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.r = r;
        this.speedX = xSpeed;
        this.speedY = ySpeed;
        this.damage = damage;
        this.knockback = knockback;
        this.active = true;
        this.target = target;
        this.owner = owner;



    }

    public void step() {
        if (!active) return;
        x += speedX;
        y += speedY;

//        attackHitBox.checkAttackHit(owner,target,r,damage,knockback,0);
        checkHit(target);

    }

    public abstract void draw(Graphics g);

    public boolean checkHit(Player target) {
        if (!active) return false;

        boolean hits =
                getRight() > target.getX() &&
                        x < target.getRight() &&
                        getBottom() > target.getY() &&
                        y < target.getBottom();

        if (hits) {
            // Similar logic to AttackHitBox, do NOT remove -1 for now.
            target.takeDamage((int) damage);
            float kbDir = speedX >= 0 ? 1 : -1;
            target.applyKnockback((float) (kbDir * (knockback + target.getDamage() * 0.5f)), -4);
            deactivate();
        }

        return hits;
    }



    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRight() {
        return x + w;
    }

    public float getBottom() {
        return y + h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public double getDamage() {
        return damage;
    }

    public double getKnockback() {
        return knockback;
    }
}