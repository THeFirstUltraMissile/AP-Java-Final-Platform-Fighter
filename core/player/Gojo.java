package core.player;

import org.newdawn.slick.*;

public class Gojo extends Player {

    private Animation idleAnim;
    private Animation lightAttackAnim;
    private Animation aerialAttackAnim;
    private Animation currentAnim;
    private Animation heavyAttackAnim;

    private boolean wasLightAttacking = false;
    private boolean wasHeavyAttacking = false;
    private boolean wasAerialAttacking = false;

    private float blueOrbX, blueOrbY;
    private boolean blueOrbActive = false;

    public Gojo(int x, int y,int direction) throws SlickException {
        super(x, y,direction);
        int jumpHeight = 22;
        double walkSpeed = 1.6;


        SpriteSheet idleSheet = new SpriteSheet(
                "media/sprites/kaisen/gojosatoru/assets_gojosatoru/satoru.png", 670, 670);

        SpriteSheet lightAttackSheet = new SpriteSheet(
                "media/sprites/unused/stone.png", 128, 128);

        SpriteSheet aerialAttackSheet = new SpriteSheet(
        "media/sprites/kaisen/ryomensukuna/basic/sukuna_jab2.png",128,128); //temp

        SpriteSheet heavySheet = new SpriteSheet("media/sprites/kaisen/gojosatoru/basic/gojo_smash.png", 256, 256);

        idleAnim = new Animation(idleSheet, 150);

        lightAttackAnim = new Animation(lightAttackSheet, 60);
        lightAttackAnim.setLooping(false);

        heavyAttackAnim = new Animation(heavySheet, 60);
        heavyAttackAnim.setLooping(false);

        aerialAttackAnim = new Animation(aerialAttackSheet,60);
        aerialAttackAnim.setLooping(false);

        currentAnim = idleAnim;
    }

    @Override
    public void specialAttack1(int duration) {
        super.specialAttack1(45);
        blueOrbActive = false;
    }

    // blue boy
    @Override
    public void specialAttack2(int duration) {
        super.specialAttack2(60);
        // Launch a tracking orb starting from in front of Gojo
        blueOrbX = isFacingRight() ? getRight() : getX() - 30;
        blueOrbY = getY() + getHeight() / 2f;
        blueOrbActive = true;
    }

    @Override
    public void step() {
        super.step();

        if (isHeavyAttacking()) {
            if (!wasHeavyAttacking) heavyAttackAnim.restart();
            currentAnim = heavyAttackAnim;
        } else if (isLightAttacking()) {
            if (!wasLightAttacking) lightAttackAnim.restart();
            currentAnim = lightAttackAnim;
        }
            else if (isAerialAttacking()) {
                if(!wasAerialAttacking) aerialAttackAnim.restart();
                currentAnim = aerialAttackAnim;
            }
         else {
            currentAnim = idleAnim;
        }

        wasHeavyAttacking = isHeavyAttacking();
        wasAerialAttacking = isAerialAttacking();
        wasLightAttacking = isLightAttacking();
        currentAnim.update(16);

        // so what did the merged beast in chapter 234 see?
        if (blueOrbActive) {
            blueOrbX += isFacingRight() ? 15 : -15;
        }
        if (!isSpecialAttacking2()) blueOrbActive = false;

    }

    @Override
    public void draw(Graphics g) {
        org.newdawn.slick.Image bodyFrame = idleAnim.getCurrentFrame();
        if (isFacingRight()) {
            bodyFrame.draw(getX(), getY(), getWidth(), getHeight());
        } else {
            bodyFrame.draw(getX() + getWidth(), getY(), -getWidth(), getHeight());
        }

    }
    public void drawAttack(Graphics g) {
        if (!isAttacking()) return;
        Animation attackAnim = isHeavyAttacking() ? heavyAttackAnim : isLightAttacking() ? lightAttackAnim : aerialAttackAnim ;
        org.newdawn.slick.Image frame = attackAnim.getCurrentFrame();
        if (isFacingRight()) {
            frame.draw(getX() + getWidth(), getY(), getWidth(), getHeight());
        } else {
            frame.draw(getX(), getY(), -getWidth(), getHeight());
        }

    }
    @Override
    public void drawSpecial(Graphics g) {
        // yeah this is NOT an image yet
        if (isSpecialAttacking1()) {
            float cx = getX() + getWidth() / 2f;
            float cy = getY() + getHeight() / 2f;
            float progress = 1f - (float) getSpecialTimer() / 45f; // 0 to 1

            for (int ring = 1; ring <= 9; ring++) {
                float r = ring * 55 * progress;
                float alpha = Math.max(0, 1f - progress) * 0.95f;
                g.setColor(new Color(0.55f, 0.85f, 1.0f, alpha));
                g.drawOval(cx - r, cy - r, r * 2, r * 2);
            }

            float coreAlpha = (1f - progress) * 0.5f;
            g.setColor(new Color(1f, 1f, 1f, coreAlpha));
            g.fillOval(cx - 20, cy - 20, 40, 40);
        }

        // kablueeey (outer to inner as move down)
        if (isSpecialAttacking2() && blueOrbActive) {
            float pct = (float) getSpecialTimer() / 60f;

            g.setColor(new Color(0.0f, 0.4f, 1.0f, 0.25f));
            g.fillOval(blueOrbX - 40, blueOrbY - 40, 80, 80);


            g.setColor(new Color(0.35f, 0.75f, 1.0f, 0.25f));
            g.fillOval(blueOrbX - 35, blueOrbY - 35, 70, 70);

            g.setColor(new Color(0.7f, 0.85f, 1.0f, 0.55f));
            g.fillOval(blueOrbX - 28, blueOrbY - 28, 56, 56);

            g.setColor(new Color(0.9f, 0.95f, 1.0f, 0.9f));
            g.fillOval(blueOrbX - 19, blueOrbY - 19, 38, 38);
        }

        g.setColor(Color.white);
    }


    public float[] getBlueOrbBounds() {
        if (!blueOrbActive || !isSpecialAttacking2()) return null;
        return new float[]{blueOrbX, blueOrbY, 22}; // x y radius
    }

    @Override
    public float getAttackRadius() {
        return 45;
    }

    @Override
    public float getAttackValue() {
        return 5;
    }

    @Override
    public float getKbValue() {
        return 4f;
    }
}