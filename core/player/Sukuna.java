package core.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Sukuna extends Player {

    private Animation idleAnim;
    private Animation lightAttackAnim;
    private Animation specialOneAnim;
    private Animation currentAnim;

    public Sukuna(int x, int y) throws SlickException {
        super(x, y);
        int jumpHeight = 12;
        double walkSpeed = 1.5;

        SpriteSheet idleSheet = new SpriteSheet("sprites/kaisen/ryomensukuna/assets/thukuna.png", 30, 64);
        SpriteSheet lightAttackSheet = new SpriteSheet("sprites/kaisen/ryomensukuna/basic/sukuna_jab.png", 64, 64);
        SpriteSheet specialOneSheet = new SpriteSheet("sprites/kaisen/ryomensukuna/shrine/cleave/sukuna_cleave.png", 64, 64);

        idleAnim = new Animation(idleSheet, 150);
        lightAttackAnim = new Animation(lightAttackSheet, 80);
        specialOneAnim = new Animation(specialOneSheet, 80);

        lightAttackAnim.setLooping(false);
        specialOneAnim.setLooping(false);

        currentAnim = idleAnim;
    }

    @Override
    public void step() {
        super.step();

        if (isAttacking()) {
            currentAnim = lightAttackAnim;
        } else {
            currentAnim = idleAnim;
        }

        currentAnim.update(16);
    }

    @Override
    public void draw(Graphics g) {
        if (isFacingRight()) {
            currentAnim.draw(getX(), getY(), getWidth(), getHeight());
        } else {
            currentAnim.draw(getX() + getWidth(), getY(), -getWidth(), getHeight());
        }
    }

    @Override
    public float getAttackRadius() { return 40; }

    @Override
    public float getAttackValue() { return 5; }

    @Override
    public float getKbValue() { return 3; }
}