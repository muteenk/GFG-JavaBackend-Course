
package session5;

class Node {
    public int value;
    public Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

public class Main {
    static void main() {

        Node head;
        Node n1 = new Node(1, null);
        head = n1;
        Node n2 = new Node(2, null);
        n1.next = n2;

    }
}

