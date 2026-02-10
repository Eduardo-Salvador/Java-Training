package Streams.GenerateStreams;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream.generate(() -> ThreadLocalRandom.current().nextInt(1, 51))
                .limit(15)
                .forEach(System.out::println);

        System.out.println("------------------");

        Stream.iterate(0, n -> n + 3)
                .filter(n -> n > 30)
                .limit(20)
                .forEach(System.out::println);

        System.out.println("------------------");

        int sum = IntStream.rangeClosed(1, 50)
                .filter(n -> n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0))
                        .sum();

        System.out.println(sum);
    }
}