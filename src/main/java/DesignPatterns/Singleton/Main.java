package DesignPatterns.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("========= EAGER CONFIG =========");
        System.out.println("================================");

        ExecutorService test = Executors.newFixedThreadPool(10);

        System.out.println("Thread tests:");
        for (int i = 0; i < 10; i++) {
            test.submit(() -> {
                EagerConfig eagerThread = EagerConfig.getInstance();
                System.out.println(Thread.currentThread().getName() + " | Instance Ref: " + eagerThread.hashCode());
            });
        }
        test.shutdown();

        try {
            test.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Eager Config: ");
        System.out.println("-------------------------");
        System.out.println("Instance 1:");
        EagerConfig config = EagerConfig.getInstance();
        config.setAppName("MyApp");
        config.setVersion("1.0.0");
        config.setEnvironment("production");
        config.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb");
        System.out.println(config);
        System.out.println("-------------------------");

        System.out.println("Instance 1 modified by Instance 2:");
        EagerConfig config2 = EagerConfig.getInstance();
        config2.setVersion("2.0.0");
        config2.setEnvironment("production");
        config2.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb/version2");
        System.out.println(config);

        System.out.println("-------------------------");
        System.out.println("Instance 1: " + config.memoryRef());
        System.out.println("Instance 2: " + config2.memoryRef());
        System.out.println("Equal instances? " + (config == config2));

        System.out.println("\n========= LAZY CONFIG =========");
        System.out.println("================================");

        ExecutorService test2 = Executors.newFixedThreadPool(10);

        System.out.println("Thread tests:");
        for (int i = 0; i < 10; i++) {
            test2.submit(() -> {
                LazyConfig lazyThread = LazyConfig.getInstance();
                System.out.println(Thread.currentThread().getName() + " | Instance Ref: " + lazyThread.hashCode());
            });
        }
        test2.shutdown();

        try {
            test2.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        test2.close();

        System.out.println("-------------------------");
        System.out.println("Lazy Config:");
        System.out.println("-------------------------");
        System.out.println("Instance 1:");
        LazyConfig lazyConfig = LazyConfig.getInstance();
        lazyConfig.setAppName("MyApp Lazy");
        lazyConfig.setVersion("1.0.0");
        lazyConfig.setEnvironment("production");
        lazyConfig.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb");

        System.out.println(lazyConfig);
        System.out.println("-------------------------");

        System.out.println("Instance 1 modified by Instance 2:");
        LazyConfig lazyConfig2 = LazyConfig.getInstance();
        lazyConfig2.setVersion("2.0.0");
        lazyConfig2.setEnvironment("production");
        lazyConfig2.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb/version2");
        System.out.println(lazyConfig);
        System.out.println("-------------------------");

        System.out.println("Instance 1: " + lazyConfig.memoryRef());
        System.out.println("Instance 2: " + lazyConfig2.memoryRef());
        System.out.println("Equal instances? " + (lazyConfig == lazyConfig2));
        System.out.println("-------------------------");

        System.out.println("\n========= LAZY CONFIG =========");
        System.out.println("========== NO THREAD SAFE ==========");

        ExecutorService test3 = Executors.newFixedThreadPool(10);

        System.out.println("Thread tests:");
        for (int i = 0; i < 10; i++) {
            test3.submit(() -> {
                LazyConfigNoThreadSafe lazyNoSafe = LazyConfigNoThreadSafe.getInstance();
                System.out.println(Thread.currentThread().getName() + " | Instance Ref: " + lazyNoSafe.hashCode());
            });
        }
        test3.shutdown();

        try {
            test3.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        test3.close();
        System.out.println("\n========= ENUM CONFIG =========");
        System.out.println("================================");

        ExecutorService test4 = Executors.newFixedThreadPool(10);

        System.out.println("Thread tests:");
        for (int i = 0; i < 10; i++) {
            test4.submit(() -> {
                EnumConfig enumThread = EnumConfig.INSTANCE;
                System.out.println(Thread.currentThread().getName() + " | Instance Ref: " + enumThread.hashCode());
            });
        }
        test4.shutdown();

        try {
            test4.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        test4.close();

        System.out.println("-------------------------");
        System.out.println("Enum Config:");
        System.out.println("-------------------------");
        System.out.println("Instance 1:");
        EnumConfig enumConfig = EnumConfig.INSTANCE;
        enumConfig.setAppName("MyApp Lazy");
        enumConfig.setVersion("1.0.0");
        enumConfig.setEnvironment("production");
        enumConfig.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb");

        System.out.println(enumConfig);
        System.out.println("-------------------------");

        System.out.println("Instance 1 modified by Instance 2:");
        EnumConfig enumConfig2 = EnumConfig.INSTANCE;
        enumConfig.setVersion("2.0.0");
        enumConfig.setEnvironment("production");
        enumConfig.setProperty("database_url", "jdbc:mysql://localhost:3306/mydb/version2");
        System.out.println(enumConfig);
        System.out.println("-------------------------");

        System.out.println("Instance 1: " + enumConfig.memoryRef());
        System.out.println("Instance 2: " + enumConfig.memoryRef());
        System.out.println("Equal instances? " + (enumConfig == enumConfig2));
        System.out.println("-------------------------");
    }
}