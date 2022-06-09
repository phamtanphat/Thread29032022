import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                printMessage("A");
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                printMessage("B");
            }
        });

        threadB.start();
        threadA.start();
    }
    private synchronized static void printMessage(String message) {
        for (int i = 0; i < 50 ; i++) {
            System.out.println(String.format("%s : %d", message, i));
        }
    }
}
