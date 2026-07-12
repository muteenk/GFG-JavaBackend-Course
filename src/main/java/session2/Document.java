package session2;


interface Printable {
    void print();
}
interface Showable {
    void show();
}
// Supported: One class implementing multiple interfaces
class Document implements Printable, Showable {
    public void print() { System.out.println("Printing..."); }
    public void show() { System.out.println("Showing..."); }
}
