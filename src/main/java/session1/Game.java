package session1;

import session1.vehicles.Car;
import session1.vehicles.SportCar;

public class Game {
    static void main() {
        Car car1 = new Car("red", "rubber", 300, "Volvo"); // this consumes memory, this is real and tangible
        Car car2 = new Car("black", "rubber", 300, "Mazda");
        car1.accelerate();
        System.out.println(car1.public_name);

        Car car3 = new Car();
        Car car4 = new Car("best_car");
        Car car5 = new Car(500);

        String[] names = {"Mercedes", "BMW", "Maruti Suzuki", "Audi"};
        Car[] cars = new Car[4];

        for (int i = 0; i < 4; i++) {
            Car newCar = new Car(names[i]);
            cars[i] = newCar;
        }

        for (Car car : cars){
            car.accelerate();
        }

        car2.accelerate();
        SportCar scar1 = new SportCar("yellow", "flat", 500, "Ferrari", "SuperFuel");
        scar1.accelerate();
        scar1.details();

        Env newEnv = new Env();
    }
}
