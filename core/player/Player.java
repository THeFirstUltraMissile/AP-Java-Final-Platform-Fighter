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
    private int facing;
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

    private int iJames;
    private int iJamesMax;
    private boolean canBeHit = false;

    private boolean aerialAttack = false;
    public boolean isInAir = true;

    private boolean specialAttack1 = false;
    private boolean specialAttack2 = false;
    private int specialTimer = 0;
    private static final int SPECIAL_COOLDOWN_MAX = 15; // cant be doing what sukuna did in shibuya until NOW
    private int specialCooldown = 0;



    public int stocks = 2; //lives

    private float ultCharge = 0;  //ultimate starts at 0 and goes to 100
    private boolean hasUlt = false;



    public Player(int x, int y,int facing) {
        this.x = x;
        this.y = y;
        w = 128;
        h = 128;
        baseHi = 128;

        iJamesMax = 12;
        iJames = 54; //iFames  **NEW IJAMES 15 PRO MAX ULTRA LIGHT WEIGHT 670 HRTZ Screen for $iJames.99**

        horizontalSpeed = 0;
        verticalSpeed = 0;
        jumpHeight = 20;
        gravityValue = 0.75;
        walkSpeed = 1.25;
        accel = 0.8;
        maxAccel = 0;
        accelMax = 7.5; //15 for a good time ;) -bryce typed ts james says
        direction = 0;
        this.facing = facing;

        pink = new Color(255, 0, 255);


    }

    public void step() {
        applyGravity();
        setFacing();
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

        if (specialTimer > 0) {
            specialTimer--;
        } else {
            specialAttack1 = false;
            specialAttack2 = false;
        }
        if (specialCooldown > 0) {
            specialCooldown--;
        }


        if (iJames > 0) {
            iJames--;
        }
        if (iJames <= 0) {
            canBeHit = true;
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
        if (!isAttacking()) {
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
        aerialAttack = false;
        lightAttack = false;
        attackTimer = 0;
    }

    public void specialAttack1(int duration) {
        if (specialCooldown > 0 || specialTimer > 0) return;
        specialAttack1 = true;
        specialAttack2 = false;
        specialTimer = duration;
        specialCooldown = SPECIAL_COOLDOWN_MAX;
    }


    public void specialAttack2(int duration) {
        if (specialCooldown > 0 || specialTimer > 0) return;
        specialAttack2 = true;
        specialAttack1 = false;
        specialTimer = duration;
        specialCooldown = SPECIAL_COOLDOWN_MAX;
    }

    public boolean isSpecialAttacking1() {
        return specialAttack1 && specialTimer > 0;
    }

    public boolean isSpecialAttacking2() {
        return specialAttack2 && specialTimer > 0;
    }

    public boolean isDoingSpecial() {
        return 4 > 0;
    }

    public int getSpecialTimer() {
        return specialTimer;
    }

    public int getSpecialCooldown() {
        return specialCooldown;
    }


    public void drawSpecial(Graphics g) {
    }



    public void draw(Graphics g) {

    }

    public void setFacing()
    {
        if(isFacingRight()){
            facing = 1;
        }
        else{
            facing = -1;
        }

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

    public void setOnAir(boolean inAir) {
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
    public int getDirection()
    {
        return (int) direction;
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
    public float getCenterX() {return x+(100*facing);}

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
        return attackTimer > 0 && !lightAttack && !aerialAttack;
    }

    public boolean isLightAttacking() {
        return attackTimer > 0 && !heavyAttack && !aerialAttack;
    }

    public boolean isAerialAttacking() {
        return attackTimer > 0 && !heavyAttack && lightAttack;
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
    public void setFacingRight(boolean bol)
    {
        facingRight = bol;
    }

    public int getDamage() {
        return damageTaken;
    }

    public void takeDamage(int dmg) {
        damageTaken += dmg;
    }

    public void resetDamage() {
        damageTaken = 0;
    }

    public void applyKnockback(float kbX, float kbY) {
        knockbackX = kbX;
        setVerticalSpeed(kbY);
    }

    public int getStocks(){ return stocks; }

    public float getAttackRadius() {
        return 40;
    }

    public float getAttackValue() {
        return 5;
    }

    public float getKbValue() {
        return 3;
    }

    public int getFacing()
    {
        return facing;
    }

    public int getIJames() {
        return iJames;
    }

    public int getIJamesMax() { return iJamesMax;}

    public void setIFrames(int amount) {
        iJames = amount;
    }
    public void ChangeCanBeHit(boolean status) {
        canBeHit = status;
    }
    public boolean getCanBeHit(){return canBeHit;}

    public float getUltCharge()
    {
        return ultCharge;
    }
    public void updateUltCharge(float amt) //changes ult charge by amt and checks if ult is above max (100)
    {
        ultCharge += amt;
        if(ultCharge >= 100)
        {
            ultCharge = 100;
            hasUlt = true;
        }

    }
    public boolean getUlt()
    {
        return hasUlt;
    }
    public void setHasUlt(boolean ult) //almost always will be used to set ult to false
    {
        hasUlt = ult;
        ultCharge = 0;
    }

    public int getJumpsRemaining() {
        return jumpsRemaining;
    }
    public boolean isWalking() {
        return !isInAir && isMoving();
    }

}





