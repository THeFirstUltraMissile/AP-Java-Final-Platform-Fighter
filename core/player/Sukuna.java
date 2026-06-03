package core.player;

import core.Images;
import org.newdawn.slick.*;

public class Sukuna extends Player {

    private Animation idleAnim;
    private Animation lightAttackAnim;
    private Animation currentAnim;
    private Animation aerialAttackAnim;
    private Animation heavyAttackAnim;
    private boolean wasHeavyAttacking = false;
    private boolean wasAerialAttacking = false;
    private boolean wasLightAttacking = false;

    private float dismantleX;
    private boolean dismantleActive = false;

    public Sukuna(int x, int y, int direction) throws SlickException {
        super(x, y, direction);
        jumpHeight = 20;
        walkSpeed = 1.5;


        idleAnim = Images.sukunaIdle;

        heavyAttackAnim = Images.sukunaHeavy;
        heavyAttackAnim.setLooping(false);

        lightAttackAnim = Images.sukunaLight;
        lightAttackAnim.setLooping(false);

//        aerialAttackAnim = Images.sukunaAerial;
//        aerialAttackAnim.setLooping(false);

        currentAnim = idleAnim;
    }

    // "this, this is too sweet"!! - jane juliet aka Ryu Ishigori, reincarnated sorcerer from the Edo Period
    @Override
    public void specialAttack1(int duration) {
        super.specialAttack1(40);
    }

    // "so I will show you what true jujutsu is..."  - Ryomen Sukuna before absolutely demolishing a fraud.
    @Override
    public void specialAttack2(int duration) {
        super.specialAttack2(55);
        dismantleX = isFacingRight() ? getRight() : getX();
        dismantleActive = true;
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
//        else if (isAerialAttacking()) {
//            if(!wasAerialAttacking) aerialAttackAnim.restart();
//            currentAnim = aerialAttackAnim;
//        }
        else {
            currentAnim = idleAnim;
        }

        wasHeavyAttacking = isHeavyAttacking();
        wasAerialAttacking = isAerialAttacking();
        wasLightAttacking = isLightAttacking();
        currentAnim.update(16);

        // move (nerfing from 18 to 12 (33%))
        if (dismantleActive) dismantleX += isFacingRight() ? 25 : -25;
        if (!isSpecialAttacking2()) dismantleActive = false;
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
        Animation attackAnim = isHeavyAttacking() ? heavyAttackAnim : lightAttackAnim;
        org.newdawn.slick.Image frame = attackAnim.getCurrentFrame();
        if (isFacingRight()) {
            frame.draw(getX() + getWidth(), getY(), getWidth(), getHeight());
        } else {
            frame.draw(getX(), getY(), -getWidth(), getHeight());
        }
    }

    @Override
    public void drawSpecial(Graphics g) {
        // slow cleave
        if (isSpecialAttacking1()) {
            float progress = 1f - (float) getSpecialTimer() / 40f; // zero to one
            float cx = isFacingRight() ? getRight() : getX();
            float cy = getY() + getHeight() / 2f;
            float startAngle = isFacingRight() ? -60 : 120;

            for (int i = 0; i < 3; i++) {
                float alpha = Math.max(0, (0.8f - progress) * 1.2f - i * 0.15f);
                g.setColor(new Color(0.9f + i * 0.05f, 0.15f - i * 0.05f, 0.15f - i * 0.05f, alpha));
                float offset = (isFacingRight() ? 1 : -1) * i * 8;
                g.drawArc(cx - 100 * progress + offset, cy - 80 * progress, 200 * progress, 160 * progress, startAngle, startAngle + 120);
            }
        }

        // most broken attack in ts game btw (nvm max blue better)
        if (isSpecialAttacking2() && dismantleActive) {
            float pct = (float) getSpecialTimer() / 55f; // 1→0
            g.setColor(new Color(0f, 0f, 0f, 1f));
            g.fillRect(dismantleX - 8, getY(), 16, getHeight());
            g.setColor(new Color(1f, 1f, 1.0f, pct * 0.95f));
            g.fillRect(isFacingRight() ? dismantleX + 6 : dismantleX - 8, getY(), 4, getHeight());
        }

        g.setColor(Color.white);
    }

    public float[] getDismantleBounds() {
        if (!dismantleActive || !isSpecialAttacking2()) return null;
        return new float[]{dismantleX - 8, getY(), 20, getHeight()};
    }


    @Override
    public float getAttackRadius() {
        return 50;
    }

    @Override
    public float getAttackValue() {
        return 6;
    }

    @Override
    public float getKbValue() {
        return 3.5f;
    }
}