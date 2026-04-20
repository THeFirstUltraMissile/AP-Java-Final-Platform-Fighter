package core.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
    protected float x;
    protected float y;
    protected int wi;
    protected int hi;
    private int baseHi;

    private double horizontalSpeed;
    private double verticalSpeed;
    protected int jumpHeight;
    private double gravityValue;
    protected double walkSpeed;
    protected double accel;
    private double maxAccel;
    protected double accelMax;
    private double direction;
    private int jumpsRemaining = 2;

    private Color pink;

    private boolean facingRight = true;
    private int damageTaken = 0;

    private boolean isCrouching;
    protected boolean isAttacking;
    private int attackTimer = 0;
    private float knockbackX = 0;
    private boolean heavyAttack = false;
    private float attackValue;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        wi = 128;
        hi = 128;
        baseHi = 128;

        horizontalSpeed = 0;
        verticalSpeed = 0;
        jumpHeight = 20;
        gravityValue = 0.75;
        walkSpeed = 1.25;
        accel = 0.8;
        maxAccel = 0;
        accelMax = 5;
        direction = 0;

        pink = new Color(255, 0, 255);
    }

    public void step() {
        applyGravity();

        if (isMoving()) {
            if (maxAccel <= accelMax) {
                maxAccel += accel;
            }
        } else {
            if (maxAccel > 0) {
                maxAccel -= accel * 2;
            }
        }

        if (maxAccel < accel) {
            maxAccel = 0;
            direction = 0;
        }

        horizontalSpeed = maxAccel * direction;
        x += horizontalSpeed;
        y += verticalSpeed;
        horizontalSpeed = 0;

        x += knockbackX;
        knockbackX *= 0.85f;
        if (Math.abs(knockbackX) < 0.1f) knockbackX = 0;

        if (attackTimer > 0) {
            attackTimer--;
        } else {
            isAttacking = false;
        }
    }

    private void applyGravity() {
        if (verticalSpeed < 15) {
            verticalSpeed += gravityValue;
        }
        if (verticalSpeed < -15) {
            verticalSpeed = -15;
        }
    }

    public void crouch() {
        if (!isCrouching) {
            isCrouching = true;
            hi = baseHi / 2;
        }
    }

    public void unCrouch() {
        if (isCrouching) {
            isCrouching = false;
            hi = baseHi;
        }
    }

    public void lightAttack(int duration) {
        if (!isAttacking) {
            isAttacking = true;
            attackTimer = duration;
            heavyAttack = false;
        }
    }

    public void heavyAttack(int duration) {
        if (!isAttacking()) {
            attackTimer = duration;
            heavyAttack = true;
            isAttacking = true;
        }
    }

    public void drawAttack(Graphics g) {
    }

    public void stopAttacking() {
        isAttacking = false;
        heavyAttack = false;
        attackTimer = 0;
    }

    public void draw(Graphics g) {
        g.setColor(pink);
        g.fillRect(x, y, wi, hi);
    }

    public void jump() {
        if (jumpsRemaining > 0) {
            setVerticalSpeed(-15f);
            jumpsRemaining--;
        }
    }

    public void setOnGround(boolean grounded) {
        if (grounded) jumpsRemaining = 2;
    }

    public void playerLeft() {
        direction = -1;
        facingRight = false;
    }

    public void playerRight() {
        direction = 1;
        facingRight = true;
    }

    public void stopMoving() {
        direction = 0;
    }

    protected boolean isMoving() {
        return direction != 0;
    }

    public void setX(float newX) {
        x = newX;
    }

    public void setY(float newY) {
        y = newY;
    }

    public void setVerticalSpeed(double v) {
        verticalSpeed = v;
    }

    public void setHorizontalSpeed(double h) {
        horizontalSpeed = h;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getYAccel() {
        return (float) verticalSpeed;
    }

    public int getWidth() {
        return wi;
    }

    public int getHeight() {
        return hi;
    }

    public float getBottom() {
        return y + hi;
    }

    public float getRight() {
        return x + wi;
    }

    public boolean isCrouching() {
        return isCrouching;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public boolean isHeavyAttacking() {
        return attackTimer > 0 && heavyAttack;
    }

    public boolean isLightAttacking() {
        return attackTimer > 0 && !heavyAttack;
    }

    public float getHeavyAttackValue() {
        return attackValue * 2.5f;
    }

    public float getHeavyKbValue() {
        return knockbackX * 2.5f;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public int getDamage() {
        return damageTaken;
    }

    public void takeDamage(int dmg) {
        damageTaken += dmg;
    }

    public void applyKnockback(float kbX, float kbY) {
        knockbackX = kbX;
        setVerticalSpeed(kbY);
    }

    public float getAttackRadius() {
        return 40;
    }

    public float getAttackValue() {
        return 5;
    }

    public float getKbValue() {
        return 3;
    }
}
