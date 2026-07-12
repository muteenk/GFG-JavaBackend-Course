package session2.world;

public class Char {
    private String name;
    private int health = 100;
    private AttackInterface attack; // Association

    public Char(String name, AttackInterface attack){
        this.name = name;
        this.attack = attack;
    }

    public int attack() {
        return attack.attack();
    }
}
