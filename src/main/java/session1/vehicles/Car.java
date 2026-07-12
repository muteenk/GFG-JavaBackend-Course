package session1.vehicles;

public class Car {
    // This is not real, this is just a template, an idea
    // PROPERTIES (Variables in a class)
    private final String color;
    private final String tyre;
    private final int maxSpeed;
    protected int speed = 0;
    private int damageMeter = 100;
    private String name;

    protected String protected_name = "Prot Name";

    public String public_name = "A CAR";

    // CONSTRUCTOR (Method like structures used for initialization)
    public Car(String color, String tyre, int maxSpeed, String name) {
        this.color = color;
        this.tyre = tyre;
        this.maxSpeed = maxSpeed;
        this.name = name;
    }

    // Our simple constructor
    public Car() {
        this.color = "pink";
        this.tyre = "metallic";
        this.maxSpeed = 250;
        this.name = "THE CAR";
    }

    public Car(String name) {
        this.color = "pink";
        this.tyre = "metallic";
        this.maxSpeed = 250;
        this.name = name;
    }

    public Car(int maxSpeed) {
        this.color = "pink";
        this.tyre = "metallic";
        this.maxSpeed = maxSpeed;
        this.name = "THE CAR";
    }

    // METHODS (Functions in a class)
    public void start() {
        System.out.println("Starting the engine !");
    }

    public void accelerate() {
        System.out.println(this.name);
        speed += 10;
    }

    // OVERLOADED accelerate function
    public int accelerate(int speed) {
        this.speed += speed;
        return this.speed;
    }
}




