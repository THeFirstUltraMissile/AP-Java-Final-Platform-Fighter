package core.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Sukuna extends Player {

    private Animation idleAnim;
    private Animation lightAttackAnim;
    private Animation currentAnim;

    private boolean wasAttacking = false;

    public Sukuna(int x, int y) throws SlickException {
        super(x, y);
        jumpHeight = 20;
        walkSpeed = 1.5;

        // 1 frame 645
        SpriteSheet idleSheet = new SpriteSheet(
                "media/sprites/kaisen/ryomensukuna/assets/thukuna.png", 645, 645);

        // 13 frames
        SpriteSheet lightAttackSheet = new SpriteSheet(
                "media/sprites/kaisen/ryomensukuna/basic/sukuna_jab.png", 128, 128);

        idleAnim = new Animation(idleSheet, 150);
        lightAttackAnim = new Animation(lightAttackSheet, 60);
        lightAttackAnim.setLooping(false);

        currentAnim = idleAnim;
    }

    @Override
    public void step() {
        super.step();

        if (isAttacking()) {
            if (!wasAttacking) {
                lightAttackAnim.restart();
            }
            currentAnim = lightAttackAnim;
        } else {
            currentAnim = idleAnim;
        }
        wasAttacking = isAttacking();
        currentAnim.update(16);
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
        if (!isAttacking()) return;  // stops attacking
        org.newdawn.slick.Image attackFrame = lightAttackAnim.getCurrentFrame();
        if (isFacingRight()) {
            attackFrame.draw(getX() + getWidth(), getY(), getWidth(), getHeight());
        } else {

            attackFrame.draw(getX(), getY(), -getWidth(), getHeight());
        }
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
