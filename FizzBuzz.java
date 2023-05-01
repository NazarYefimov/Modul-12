import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class FizzBuzz {
    private int n;
    private BlockingQueue<String> queueA;
    private BlockingQueue<String> queueB;
    private BlockingQueue<String> queueC;
    private BlockingQueue<Integer> queueD;

    public FizzBuzz(int n) {
        this.n = n;
        this.queueA = new LinkedBlockingQueue<>();
        this.queueB = new LinkedBlockingQueue<>();
        this.queueC = new LinkedBlockingQueue<>();
        this.queueD = new LinkedBlockingQueue<>();
    }

    public void fizz() {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                queueA.add(Integer.toString(i));
            }
        }
        queueA.add("done");
    }

    public void buzz() {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                queueB.add(Integer.toString(i));
            }
        }
        queueB.add("done");
    }

    public void fizzbuzz() {
        for (int i = 15; i <= n; i += 15) {
            queueC.add(Integer.toString(i));
        }
        queueC.add("done");
    }

    public void number() {
        for (int i = 1; i <= n; i++) {
            String output = "";
            if (i % 3 == 0) {
                output += "fizz";
            }
            if (i % 5 == 0) {
                output += "buzz";
            }
            if (output.isEmpty()) {
                output += Integer.toString(i);
            }
            try {
                if (queueA.peek().equals(Integer.toString(i))) {
                    output = "fizz";
                    queueA.take();
                }
                if (queueB.peek().equals(Integer.toString(i))) {
                    output = "buzz";
                    queueB.take();
                }
                if (queueC.peek().equals(Integer.toString(i))) {
                    output = "fizzbuzz";
                    queueC.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);

        Thread threadA = new Thread(() -> fizzBuzz.fizz());
        Thread threadB = new Thread(() -> fizzBuzz.buzz());
        Thread threadC = new Thread(() -> fizzBuzz.fizzbuzz());
        Thread threadD = new Thread(() -> fizzBuzz.number());

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
