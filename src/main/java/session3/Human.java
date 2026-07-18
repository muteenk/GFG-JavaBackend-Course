package session3;

public class Human
        implements Comparable<Human>
{
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public String getName() {return this.name;}
    public int getAge() {return this.age;}

    @Override
    public int compareTo(Human b) {
        return Integer.compare(this.age, b.getAge());
    }
}
