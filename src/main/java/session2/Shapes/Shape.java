package session2.Shapes;

public abstract class Shape {
    protected int dimension1;
    protected int dimension2;

    public Shape(int a, int b) {
        this.dimension1 = a;
        this.dimension2 = b;
    }

    public abstract float area();
}
