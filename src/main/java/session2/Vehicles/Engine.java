package session2.Vehicles;

public class Engine {
    private String model;

    public Engine(String model){
        this.model = model;
    }

    public String getModel(){
        return this.model;
    }

    public void runEngine(){
        System.out.println(this.model + " ENGINE IS RUNNING !");
    }

    public void setModel(String newModel) {
        this.model = newModel;
    }
}
