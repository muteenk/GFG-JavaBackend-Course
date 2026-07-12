package session2.Shapes;

public class Triangle extends Shape{
    public Triangle(int a, int b) {
        super(a, b);
    }

    @Override
    public float area() {
        return (float) 0.5*this.dimension1*this.dimension2;
    }
}
