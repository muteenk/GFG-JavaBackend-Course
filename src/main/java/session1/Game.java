package session1;

import session1.vehicles.Car;
import session1.vehicles.SportCar;

public class Game {
    static void main() {
        Car car1 = new Car("red", "rubber", 300, "Volvo"); // this consumes memory, this is real and tangible
        Car car2 = new Car("black", "rubber", 300, "Mazda");
        car1.accelerate();
        car2.accelerate();
        SportCar scar1 = new SportCar("yellow", "flat", 500, "Ferrari", "SuperFuel");
        scar1.accelerate();

        Env newEnv = new Env();
    }
}
