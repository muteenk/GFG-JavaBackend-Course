package session5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MyThreadPool {
    static void main() {
        ExecutorService exe = Executors.newFixedThreadPool(5);

//        exe.execute(() -> {
//            for (int i = 0; i < 100000; i++) System.out.println(i);
//        });

        Future<Integer> response = exe.submit(() -> {
            int x = 0;
            for (int i = 0; i < 100000; i++) {
                x = x + 1;
                System.out.println(x);
            }
            return x;
        });

        try {
            int sum = response.get(); // MainThread sleeps here, until the value is settled down
            System.out.println("SUM " + sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            exe.shutdown();
        }

    }
}
