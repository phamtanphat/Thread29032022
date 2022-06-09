import java.util.concurrent.TimeUnit;

public class Main {
    static int a, b, c;
    public static void main(String[] args) {
        a = b = c = 0;
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50 ; i++) {
                    a = i;
                    System.out.println(String.format("A : %d", a));
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50 ; i++) {
                    b = i;
                    System.out.println(String.format("B : %d", b));
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50 ; i++) {
                    c = a + b;
                    System.out.println(String.format("C : %d", c));
                }
            }
        });

        threadB.start();
        threadA.start();
        threadC.start();
    }
}
