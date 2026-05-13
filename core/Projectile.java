package core;
import org.newdawn.slick.Graphics;

public abstract class Projectile {

    protected float x;
    protected float y;
    protected int w;
    protected int h;

    private double speedX;
    private double speedY;
    private double damage;
    private double knockback;

    private boolean active;

    public Projectile(float x, float y, double projectileSpeed, double damage, double knockback) {
        this.x = x;
        this.y = y;
        this.w = 32;
        this.h = 32;
        this.speedX = projectileSpeed;
        this.speedY = 0;
        this.damage = damage;
        this.knockback = knockback;
        this.active = true;
    }

    public void step() {
        if (!active) return;
        x += speedX;
        y += speedY;
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

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setKnockback(double knockback) {
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