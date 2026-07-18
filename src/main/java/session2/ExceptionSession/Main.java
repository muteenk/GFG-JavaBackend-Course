package session2.ExceptionSession;

import java.util.Scanner;

public class Main {
    // Error: Systematic Failure that occurs in java
    // Exception: Logical Failure that might exists in the logical flow

    void drive(Human h){
        if (h.getAge() >= 18) {
            System.out.println("You can drive");
        } else {
            throw new MyException("Below 18");
        }
    }

    int division(int myInt) throws ArithmeticException {
        int x = 45;
        return x/myInt;
    }

    static void main() {
//        Scanner scan = new Scanner(System.in);
//        try {
//            System.out.println("Give me a number: ");
//            int y = scan.nextInt();
//            int x = 45;
//            System.out.println("Your division is: " + x/y);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            scan.close();
//        }

        Human myHuman1 = new Human("Jake", 20);
        Human myHuman2 = new Human("Jacob", 15);

        Main m = new Main();
        try {
            m.drive(myHuman2);
        } catch (MyException e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(m.division(0));
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }

        System.out.println("HELO");
//        for (int i=0; i < 100; i++) System.out.println(i);
    }
}