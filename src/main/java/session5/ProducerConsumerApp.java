package session5;

class SharedBuffer {
    private int content;    // shared resource
    private boolean isEmpty = true;


    // Consumer calls this
    public synchronized int consume() throws InterruptedException {
        // 1. Loop checking condition to prevent spurious wakeups
        while (isEmpty) {
            wait(); // Releases lock, waits for producer
        }


        isEmpty = true;
        System.out.println("Consumed: " + content);


        // 2. Notify the producer that space is available
        notifyAll();
        return content;
    }


    // Producer calls this
    public synchronized void produce(int value) throws InterruptedException {
        while (!isEmpty) {
            wait(); // Releases lock, waits for consumer
        }


        content = value;
        isEmpty = false;
        System.out.println("Produced: " + value);


        // 3. Notify the consumer that data is ready
        notifyAll();
    }
}

public class ProducerConsumerApp {

    static void main() {

        SharedBuffer buf = new SharedBuffer();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    buf.produce(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++){
                try {
                    buf.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
