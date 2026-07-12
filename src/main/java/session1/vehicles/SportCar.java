package session1.vehicles;

public class SportCar extends Car {
    private final String fuel;

    public SportCar(String color, String tyre, int maxSpeed, String name, String fuel) {
        this(fuel);
    }

    public SportCar(String fuel) {
        this.fuel = fuel;
    }

    public void details() {
        System.out.println(this.protected_name);
    }

    public void accelerate() {
        this.speed += 1000;
        System.out.println("THE NEW SPEED IS: " + this.speed);
    }
}