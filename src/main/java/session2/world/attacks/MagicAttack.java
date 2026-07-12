package session2.world.attacks;

import session2.world.AttackInterface;

public class MagicAttack implements AttackInterface {
    @Override
    public int attack() {
        System.out.println("Attacked with magic wand");
        return 5;
    }
}
