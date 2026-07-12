package session2.world.attacks;

import session2.world.AttackInterface;

public class LongRangeAttack implements AttackInterface {
    @Override
    public int attack() {
        System.out.println("Shot with a sniper");
        return 30;
    }
}
