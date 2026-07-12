package session2.Shapes;

public class Rectangle extends Shape{

    public Rectangle(int a, int b) {
        super(a, b);
    }

    @Override
    public float area() {
        return this.dimension1*this.dimension2;
    }
}
