package core.player;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
    private float x;
    private float y;
    private int wi;
    private int hi;

    private double horizontalSpeed;
    private double verticalSpeed;
    private int jumpHeight;
    private double gravityValue;
    private double walkSpeed;
    private double accel;
    private double maxAccel;
    private double accelMax;
    private double direction;
    private String playerDirection;

    private Color pink;
    private Image currentSprite;

    private boolean facingRight = true;
    private int damageTaken = 0;

    private boolean isCrouching;
    private boolean isAttacking;

    private int attackTimer = 0;
    private static final int ATTACK_DURATION = 10;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        wi = 64;
        hi = 128;

        horizontalSpeed = 0;
        verticalSpeed = 0;
        jumpHeight = 24;
        gravityValue = 0.75;
        walkSpeed = 1.25;
        accel = 0.8;
        maxAccel = 0;
        accelMax = 5;
        direction = 0.00;

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
            hi = hi / 2;
        }
    }

    public void unCrouch() {
        if (isCrouching) {
            isCrouching = false;
            hi = hi * 2;
        }
    }

    public void lightAttack(int duration) {
        if (!isAttacking) {
            isAttacking = true;
            attackTimer = duration;
        }
    }

    public void stopAttacking() {
        isAttacking = false;
        attackTimer = 0;
    }


    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setColor(pink);
        g.fillRect(x, y, wi, hi);
    }


    public void jump() {
        verticalSpeed += -jumpHeight;
    }

    public void playerLeft() {
        direction = -1;
        facingRight = false;
    }

    public void playerRight() {
        direction = 1;
        facingRight = true;
    }

    public void setX(float newX) {
        x = newX;
    }

    public void setY(float newY) {
        y = newY;
    }

    public void setVerticalSpeed(double newverticalSpeed) {
        verticalSpeed = newverticalSpeed;
    }

    public void setHorizontalSpeed(double newhorizontalSpeed) {
        horizontalSpeed = newhorizontalSpeed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXAccel() {
        return (float) horizontalSpeed;
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

    private boolean isMoving() {
        return direction != 0;
    }

    public void stopMoving() {
        direction = 0;
    }

    public boolean isCrouching() {
        return isCrouching;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void takeDamage(int dmg) {
        damageTaken += dmg;
    }

    public void applyKnockback(float kbX, float kbY) {
        setHorizontalSpeed(kbX);
        setVerticalSpeed(kbY);
    }

    public int getDamage() {
        return damageTaken;
    }

    public float getAttackRadius() {
        return 1;
    }

    public float getAttackValue() {
        return 1;
    }

    public float getKbValue() {
        return 1;
    }

    public boolean isFacingRight() {
        return facingRight;
    }


    // I THOUGHT I WAS DOING ART SO WHY AM I CODING????????? - James
}
