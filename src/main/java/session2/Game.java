package session2;

import session2.Shapes.Rectangle;
import session2.Shapes.Shape;
import session2.Shapes.Triangle;
import session2.Vehicles.Car;
import session2.Vehicles.Engine;
import session2.Vehicles.SuperCar;
import session2.world.AttackInterface;
import session2.world.Char;
import session2.world.attacks.LongRangeAttack;
import session2.world.attacks.MagicAttack;

public class Game {
    static void main() {
        Character mk = new Character("MK");

        System.out.println(mk.getName());

        mk.setHealth(50);
        System.out.println(mk.getHealth());

        System.out.println(1+2);
        System.out.println("Hello" + " World !");

        Shape rect = new Rectangle(1, 2);
        Shape square = new Rectangle(2, 2);
        Shape tri = new Triangle(3, 2);

        Shape[] shapes = {rect, square, tri};

        for (Shape shape: shapes){
            System.out.println(shape.area());
        }

        AttackInterface magic = new MagicAttack();
        AttackInterface sniperAttack = new LongRangeAttack();

        Char larry = new Char("larry", magic);
        Char wizard = new Char("harry", sniperAttack);

        wizard.attack();
        larry.attack();


        Engine v8 = new Engine("V8");
        Car myCar = new Car("Ferrari", v8);

        SuperCar mySuperCar = new SuperCar("Lambo", "v12");
        mySuperCar.startEngine();
        mySuperCar.changeEngine("v8");
        mySuperCar.startEngine();


        LazySingleton mySingleton = LazySingleton.getInstance();

        System.out.println(mySingleton.s);
        System.out.println(mySingleton.hashCode());

        LazySingleton secondInstance = LazySingleton.getInstance();

        System.out.println(secondInstance.s);
        System.out.println(secondInstance.hashCode());

    }
}
