package core.player;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Player {
    protected float x;
    protected float y;
    protected int w;
    protected int h;
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
    private boolean lightAttack = false;
    private float attackValue;

    private boolean aerialAttack = false;
    public boolean isInAir = true;

    public int stocks = 2; //lives

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        w = 128;
        h = 128;
        baseHi = 128;

        horizontalSpeed = 0;
        verticalSpeed = 0;
        jumpHeight = 20;
        gravityValue = 0.75;
        walkSpeed = 1.25;
        accel = 0.8;
        maxAccel = 0;
        accelMax = 7.5; //15 for a good time ;) -bryce typed ts james says
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
            h = baseHi / 2;
        }
    }

    public void unCrouch() {
        if (isCrouching) {
            isCrouching = false;
            h = baseHi;
        }
    }

    public void lightAttack(int duration) {
        if (!isAttacking) {
            isAttacking = true;
            attackTimer = duration;
            heavyAttack = false;
            aerialAttack = false;
            lightAttack = true;
        }
    }

    public void heavyAttack(int duration) {
        if (!isAttacking()) {
            isAttacking = true;
            attackTimer = duration;
            heavyAttack = true;
            aerialAttack = false;
            lightAttack = false;
        }
    }

    public void aerialAttack(int duration) {
        if (!isAttacking()) {
            isAttacking = true;
            attackTimer = duration;
            heavyAttack = false;
            aerialAttack = true;
            lightAttack = false;
        }
    }

    public void drawAttack(Graphics g) {
    }

    public void stopAttacking() {
        isAttacking = false;
        heavyAttack = false;
        aerialAttack =false;
        lightAttack = false;
        attackTimer = 0;
    }

    public void draw(Graphics g) {

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

    public void setOnAir(boolean inAir)
    {
        isInAir = inAir;
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

    public boolean getIsInAir() {
        return isInAir;
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
        return w;
    }

    public int getHeight() {
        return h;
    }

    public float getBottom() {
        return y + h;
    }

    public float getRight() {
        return x + w;
    }

    public boolean isCrouching() {
        return isCrouching;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public boolean isHeavyAttacking() {
        return attackTimer > 0 && !lightAttack&&!aerialAttack;
    }

    public boolean isLightAttacking() {
        return attackTimer > 0 && !heavyAttack&&!aerialAttack;
    }

    public boolean isAerialAttacking(){
        return attackTimer > 0 && !heavyAttack&&lightAttack;
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

    public void resetDamage()
    {
        damageTaken = 0;
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