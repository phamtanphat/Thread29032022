import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        handleData().thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {

            }
        }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println(throwable.getMessage().toLowerCase(Locale.ROOT));
                return null;
            }
        });
    }

    private static CompletableFuture<Integer> handleData () {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletableFuture<Integer> future = new CompletableFuture<>();
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                int a = 10;
                int b = 5;
                future.completeExceptionally(new Exception("Lỗi"));
            }
        });
        return future;
    }
}
