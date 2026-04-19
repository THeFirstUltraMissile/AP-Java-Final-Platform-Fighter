package core.player;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Gojo extends Player {

    private Animation idleAnim;
    private Animation walkAnim;
    private Animation attackAnim;
    private Animation currentAnim;

    public Gojo(int x, int y) throws SlickException {
        super(x, y);
        int jumpHeight = 12;
        float walkSpeed = 1.5f;

        SpriteSheet idleSheet = new SpriteSheet("sprites/kaisen/idle.png", 64, 64);
        SpriteSheet walkSheet = new SpriteSheet("sprites/kaisen/walk.png", 64, 64);
        SpriteSheet attackSheet = new SpriteSheet("sprites/kaisen/attack.png", 64, 64);

        idleAnim = new Animation(idleSheet, 150);   // 150ms per frame
        walkAnim = new Animation(walkSheet, 100);
        attackAnim = new Animation(attackSheet, 80);
        attackAnim.setLooping(false);               // attack plays once

        currentAnim = idleAnim;
    }
}