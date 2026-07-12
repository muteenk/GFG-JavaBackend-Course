package session2.Vehicles;

public class SuperCar {
    private final String name;
    private final Engine engine;

    public SuperCar(String name, String engine_model) {
        this.name = name;
        this.engine = new Engine(engine_model);
    }

    public void startEngine() {
        System.out.println(this.name + " Car:");
        this.engine.runEngine();
    }

    public void changeEngine(String newModel) {
        this.engine.setModel(newModel);
    }
}
