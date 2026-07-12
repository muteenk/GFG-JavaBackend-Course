package session1.vehicles;

public class SportCar extends Car {
    private final String fuel;

    public SportCar(String color, String tyre, int maxSpeed, String name, String fuel) {
        super(color, tyre, maxSpeed, name);
        this.fuel = fuel;
    }
}