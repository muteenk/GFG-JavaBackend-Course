package session1.vehicles;

public class Car {
    // This is not real, this is just a template, an idea
    private final String color;
    private final String tyre;
    private final int maxSpeed;
    private int speed = 0;
    private int damageMeter = 100;
    private String name;

    public Car(String color, String tyre, int maxSpeed, String name) {
        this.color = color;
        this.tyre = tyre;
        this.maxSpeed = maxSpeed;
        this.name = name;
    }

    public void start() {
        System.out.println("Starting the engine !");
    }

    public void accelerate() {
        System.out.println(this.name);
        speed += 10;
    }
}




