import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        Thread timerThread = new Thread(new Timer());
        Thread messageThread = new Thread(new Message());
        timerThread.start();
        messageThread.start();
    }

    private static class Timer implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            while (true) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Time elapsed: " + TimeUnit.MILLISECONDS.toSeconds(elapsedTime) + " seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Message implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Минуло 5 секунд");
            }
        }
    }
}