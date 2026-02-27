package Threads_ConcurrencyThreads.ExecutorsThreadPool.NewCachedThreadPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ImageProcessor implements Callable<String>{
    private final String imageName;

    public ImageProcessor(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + ": Processing image...");
        long start = System.currentTimeMillis();
        //int sleepTime = ThreadLocalRandom.current().nextInt(3000, 3001);
        int sleepTime = ThreadLocalRandom.current().nextInt(500, 1501);
        Thread.sleep(sleepTime);
        long end = System.currentTimeMillis();
        return imageName + ".jpg processed in " + (end - start) + "ms";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService imageProcessorFuture = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            futureList.add(imageProcessorFuture.submit(new ImageProcessor("image-" + i)));
        }

        for (Future<String> future : futureList) {
            System.out.println(future.get(2, TimeUnit.SECONDS));
        }

        imageProcessorFuture.shutdown();
    }
}