package player;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player {
    private float x;
    private float y;
    private int wi;
    private int hi;

    private double hsp;
    private double vsp;
    private int jumpH;
    private double grv;
    private double walkSpeed;
    private double accel;
    private double accelFinal; //change to int as necessary
    private double accelMax;
    private double lastH;
    private String playerDirection;

    private Color pink;
    private Image currentSprite;

    private int currentHealth;
    private int maxHealth;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        wi = 64;
        hi = wi;

        hsp = 0;
        vsp = 0;
        jumpH = 24;
        grv = 0.75; //0.75
        walkSpeed = 1.25;
        accel = 0.8;
        accelFinal = 0.00;
        accelMax = 8;
        lastH = 0.00;

        playerDirection = "right"; //Will be affected by spawn location. It dictates which sprite will be drawn.
        pink = new Color(255, 0, 255);

        maxHealth = 100;
        currentHealth = maxHealth;
    }

    public void step() { //every frame events!
        applyGravity();

        //TODO Acceleration, do later!
//        if (isMoving()) {
//            if (lastH != 1) {
//                lastH = 1;
//                accelFinal = 0;
//            }
//            if (accelFinal <= accelMax) {
//                accelFinal += accel;
//            }
//        } else {
//            if (accelFinal > 0) {
//                accelFinal -= accel * 2;
//            }
//        }
//
//        if (accelFinal < accel) {
//            accelFinal = 0;
//            lastH = 0;
//        }

        hsp = accelFinal * lastH;

        x += hsp;
        //y += vsp;
        hsp = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.drawString("have you tried giving your heart to joc", Main.getScreenWidth() * .5f, Main.getScreenHeight() * .10f);
        g.setColor(pink);
        g.fillRect(x, y, wi, hi);
    }

    private void applyGravity() {
        vsp += grv;
    }

    public void jump() {
        vsp += -jumpH;
    }

    public void playerLeft() {
        hsp += -walkSpeed;
    }

    public void playerRight() {
        hsp += walkSpeed;
    }

    private boolean isMoving() {
        if (hsp == 0) {
            return true;
        }
        return false;
    }
}