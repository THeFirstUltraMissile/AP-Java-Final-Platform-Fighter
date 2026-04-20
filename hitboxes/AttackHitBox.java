package hitboxes;

import core.player.Player;

public class AttackHitBox extends HitBox {

    public void checkAttackHit(Player attacker, Player target, float attackRadius, float attackValue, float kbValue, float specialValue) {
        if (!attacker.isAttacking()) return;

        int attackW = (int) attackRadius;
        int attackH = attacker.getHeight();
        float attackX;

        if (attacker.isFacingRight()) {
            attackX = attacker.getRight();
        } else {
            attackX = attacker.getX() - attackW;
        }

        float attackY = attacker.getY();

        if (isInBox(target.getX(), target.getY(), (int)attackX, (int)attackY, attackW, attackH) ||
                isInBox(target.getRight(), target.getY(), (int)attackX, (int)attackY, attackW, attackH) ||
                isInBox(target.getX(), target.getBottom(), (int)attackX, (int)attackY, attackW, attackH) ||
                isInBox(target.getRight(), target.getBottom(), (int)attackX, (int)attackY, attackW, attackH)) {

            target.takeDamage((int) attackValue);
            float kbDir = attacker.isFacingRight() ? 1 : -1;
            target.applyKnockback(kbDir * (kbValue + target.getDamage() * 0.5f), -4);
            // attacker.stopAttacking();
        }
    }

    public void checkHeavyAttackHit(Player attacker, Player target,
                                    float attackRadius, float attackValue, float kb) {
        if (!attacker.isHeavyAttacking()) return;

        float attackX = attacker.isFacingRight() ? attacker.getRight() : attacker.getX() - attackRadius;
        boolean hitsTarget = attackX < target.getRight() && attackX + attackRadius > target.getX()
                && attacker.getY() < target.getBottom() && attacker.getBottom() > target.getY();
        if (hitsTarget) {
            target.takeDamage((int) attackValue);
            int dir = attacker.isFacingRight() ? 1 : -1;
            float scaling = kb + target.getDamage() * 0.8f;
            target.applyKnockback(dir * scaling, -4f - target.getDamage() * 0.04f);
        }
    }
}
