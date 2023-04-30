import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class FizzBuzz {
    private int n;
    private BlockingQueue<String> queue;
    private volatile int currNumber;

    public FizzBuzz(int n) {
        this.n = n;
        queue = new LinkedBlockingQueue<>();
        currNumber = 1;
    }

    public void fizz() throws InterruptedException {
        while (currNumber <= n) {
            if (currNumber % 3 == 0 && currNumber % 5 != 0) {
                queue.put("fizz");
            }
            currNumber++;
        }
    }

    public void buzz() throws InterruptedException {
        while (currNumber <= n) {
            if (currNumber % 3 != 0 && currNumber % 5 == 0) {
                queue.put("buzz");
            }
            currNumber++;
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (currNumber <= n) {
            if (currNumber % 3 == 0 && currNumber % 5 == 0) {
                queue.put("fizzbuzz");
            }
            currNumber++;
        }
    }

    public void number() throws InterruptedException {
        while (currNumber <= n) {
            if (!queue.isEmpty()) {
                System.out.println(queue.take());
            } else {
                if (currNumber % 3 != 0 && currNumber % 5 != 0) {
                    System.out.println(currNumber);
                }
            }
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}