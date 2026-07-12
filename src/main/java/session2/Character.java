package session2;

public class Character {
    private final String name;
    private int health = 100;

    public Character(String name) {
        this.name = name;
    }

    // GETTER FOR NAME PROPERTY
    public String getName() {
        return this.name;
    }

    // GETTER FOR HEALTH
    public int getHealth() {
        return this.health;
    }

    // SETTER FOR HEALTH
    public void setHealth(int health){
        this.health = health;
    }

}
