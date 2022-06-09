import java.util.concurrent.TimeUnit;

public class Main {
    static int a, b, c;
    static MyFlag myFlag;

    public static void main(String[] args) {
        a = b = c = 0;
        myFlag = new MyFlag(1);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 50; ) {
                        if (myFlag.index == 1) {
                            a = i;
                            System.out.println(String.format("A : %d", a));
                            myFlag.index = 2;
                            myFlag.notifyAll();
                            i++;
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 50; ) {
                       if (myFlag.index == 2) {
                           b = i;
                           System.out.println(String.format("B : %d", b));
                           myFlag.index = 3;
                           myFlag.notifyAll();
                           i++;
                       } else {
                           try {
                               myFlag.wait();
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myFlag) {
                    for (int i = 1; i <= 50;) {
                        if (myFlag.index == 3) {
                            c = a + b;
                            System.out.println(String.format("C : %d", c));
                            myFlag.index = 1;
                            myFlag.notifyAll();
                            i++;
                        } else {
                            try {
                                myFlag.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadB.start();
        threadA.start();
        threadC.start();
    }
}
