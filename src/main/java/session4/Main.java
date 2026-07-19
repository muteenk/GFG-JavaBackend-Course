package session4;

import java.util.ArrayList;
import java.util.List;

class Box <T> {
    private int dimension1;
    private int dimension2;

    public Box(T arg, int dim1, int dim2){
        System.out.println("The box arg: " + arg);
        this.dimension1 = dim1;
        this.dimension2 = dim2;
    }

    public double calcArea(){
        return dimension2*dimension1;
    }
}

interface MyGenericInterface<T> {
    T add(T a, T b);
}


class myInterfaceClass implements MyGenericInterface<Integer> {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}

class myNewInterfaceClass<T> implements MyGenericInterface<T> {
    public T add(T a, T b) {
        return a;
    }
}


class Box2 {
    private int dimension1;
    private int dimension2;

    public <T> Box2(T arg, int dim1, int dim2){
        System.out.println("The box arg: " + arg);
        this.dimension1 = dim1;
        this.dimension2 = dim2;
    }

    public double calcArea(){
        return dimension2*dimension1;
    }
}


class Pair<K, V> {

}

public class Main {
    static void main() {
        Box<String> bx1 = new Box<>("Hello", 2, 5);
        Box<Integer> bx2 = new Box<>(1, 4, 6);
        Box<Double> bx3 = new Box<>(2.3, 6, 8);

        Box2 bxx = new Box2("Hello", 1, 2);
    }
}
