package session3;
import java.util.*;
import java.util.function.*;

@FunctionalInterface
interface Add {
    int calc(int a, int b);
}

@FunctionalInterface
interface Greeting {
    void greet();
}

public class Main{
    static void main() {
        Add add = (a,b) -> a+b;
        Greeting myGreet = () -> System.out.println("Hello");
        Greeting mySuperGreet = () -> {
            int a = 9;
            int b = 19;
            int res = a+b;
            System.out.println("Hello: " + res);
        };

        Predicate<Integer> isEven = (x) -> x % 2 == 0;  // takes in one Integer and gives out boolean
        Consumer<String> sayMyName = System.out::println;
        Supplier<ArrayList<Integer>> giveMeArray = () -> {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(1);
            arr.add(2);
            arr.add(3);
            return arr;
        };
        Function<Integer, String> myFunc = (a) -> "My Integer: " + a;

        int result = add.calc(1, 2);
        myGreet.greet();
        mySuperGreet.greet();
        if (isEven.test(26)) {
            System.out.println("IS EVEN");
        }
        sayMyName.accept("Hi");
        for (int i: giveMeArray.get()){
            System.out.println(i);
        }
        System.out.println(myFunc.apply(5));
        System.out.println(result);

        // ------- Comparator

        Comparator<Integer> compareMyInts = Integer::compare;
        Comparator<String> compareStr = (a, b) -> a.compareTo(b);
        Comparator<Human> compareMyHuman = (a, b) -> a.getAge() - b.getAge();

        System.out.println(compareMyInts.compare(2, 5));
        System.out.println(compareMyInts.compare(3, 3));
        System.out.println(compareMyInts.compare(5, 2));

        System.out.println(compareStr.compare("Hello", "world"));

        Human myHuman1 = new Human("Jake", 20);
        Human myHuman2 = new Human("Jacob", 15);
        System.out.println(compareMyHuman.compare(myHuman1, myHuman2));

        ArrayList<Human> humans = new ArrayList<>();
        humans.add(myHuman1);
        humans.add(myHuman2);
        humans.add(new Human("a", 18));
        humans.add(new Human("b", 5));
        humans.add(new Human("c", 21));

        Collections.sort(humans, (a, b) -> a.getName().compareTo(b.getName()));

        for (Human h: humans){
            System.out.println("Human name: " + h.getName() + " Human age: " + h.getAge());
        }

    }
}
