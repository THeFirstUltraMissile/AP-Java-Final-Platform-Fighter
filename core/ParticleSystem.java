package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

// spend all weekend on this bullshit, way easier than dumb ap test
public class ParticleSystem {

    private static class Particle {
        float x, y, vx, vy, life, maxLife, size;
        Color color;

        Particle(float x, float y, float vx, float vy, float life, float size, Color color) {
            this.x = x; this.y = y;
            this.vx = vx; this.vy = vy;
            this.life = life; this.maxLife = life;
            this.size = size; this.color = color;
        }

        boolean isDead() { return life <= 0; }

        void update() {
            x += vx; y += vy;
            vy += 0.18f;  // gravity
            vx *= 0.88f;  // friction
            life--;
        }

        void draw(Graphics g) {
            float alpha = Math.max(0, life / maxLife);
            float s = size * (0.4f + 0.6f * alpha);
            g.setColor(new Color(color.r, color.g, color.b, alpha * 0.85f));
            g.fillOval(x - s / 2, y - s / 2, s, s);
        }
    }

    private final ArrayList<Particle> particles = new ArrayList<>();
    private final Random rng = new Random();
    private int walkCooldown = 0;

    public void emitWalkDust(float footX, float footY, boolean facingRight) {
        if (walkCooldown > 0) { walkCooldown--; return; }
        walkCooldown = 1; // puff puff puff

        for (int i = 0; i < rng.nextInt(2) + 2; i++) { // 2-3 particles per puff puff puff
            float vx = (facingRight ? -1 : 1) * (rng.nextFloat() * 1.4f + 0.3f);
            float vy = -(rng.nextFloat() * 0.9f + 0.1f);
            particles.add(new Particle(footX, footY, vx, vy,
                    rng.nextFloat() * 8 + 10, rng.nextFloat() * 10 + 6,
                    new Color(0.78f, 0.70f, 0.55f, 1f))); // dusty and ashy like your face
        }
    }

    public void emitJumpBurst(float footX, float footY) {
        for (int i = 0; i < rng.nextInt(4) + 6; i++) { // 69696996969
            float angle = (float)(Math.PI + rng.nextFloat() * Math.PI);
            float speed = rng.nextFloat() * 2.5f + 0.8f;
            particles.add(new Particle(footX, footY,
                    (float)(Math.cos(angle) * speed), (float)(Math.sin(angle) * speed) * 0.5f,
                    rng.nextFloat() * 10 + 12, rng.nextFloat() * 12 + 5,
                    new Color(0.85f, 0.85f, 0.95f, 1f)));
        }
    }

    public void emitDoubleJump(float centerX, float centerY) {
        int count = rng.nextInt(5) + 8; // 8-12 puff puff puffs
        for (int i = 0; i < count; i++) {
            float angle = (float)(2 * Math.PI * i / count + rng.nextFloat() * 0.3f);
            float speed = rng.nextFloat() * 1.8f + 1.0f;
            particles.add(new Particle(centerX, centerY,
                    (float)(Math.cos(angle) * speed), (float)(Math.sin(angle) * speed) - 0.5f, //a little to the north
                    rng.nextFloat() * 12 + 14, rng.nextFloat() * 9 + 7,
                    new Color(0.45f, 0.85f, 1.0f, 1f))); //cyan
        }
    }

    public void update() {
        particles.removeIf(Particle::isDead);
        particles.forEach(Particle::update);
    }

    public void draw(Graphics g) {
        particles.forEach(p -> p.draw(g));
        g.setColor(Color.white); // make it bright shiny white like terraria
    }
}