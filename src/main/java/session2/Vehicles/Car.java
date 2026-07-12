package session2.Vehicles;

public class Car {
    private final String name;

    public Car(String name, Engine engine) {
        this.name = name;
        this.startEngine(engine);
    }

    public void startEngine(Engine engine) {
        engine.runEngine();
    }
}
