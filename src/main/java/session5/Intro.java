
package session5;

/*  Two ways to create tasks for the threads
*
*   1. Implementing a Functional Interface called Runnable
*
*   2. Extending the Thread Class
*
* */

// It represents the thread itself
class MyThread extends Thread {
    public int totalAmount = 0;

    @Override
    public void run() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

// It is just a interface implementation (so its just a task)
class MyTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
    }
}





public class Intro {
    static void main() {

        // Main thread and is performing the given task

        // This is the task
//        for (int i = 0; i < 100000; i++) {
//            System.out.println(i);
//        }

        Thread t1 = new MyThread();
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                System.out.println(i);
            }
        });
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("Hello World");
    }
}

