package Streams.GenerateAndSummarizing.Application;
import Streams.GenerateAndSummarizing.Domain.Transaction;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, 1500.0, Transaction.Type.DEPOSIT),
                new Transaction(2, 300.0, Transaction.Type.WITHDRAW),
                new Transaction(3, 2200.0, Transaction.Type.DEPOSIT),
                new Transaction(4, 120.0, Transaction.Type.WITHDRAW),
                new Transaction(5, 5000.0, Transaction.Type.DEPOSIT),
                new Transaction(6, 50.0, Transaction.Type.WITHDRAW),
                new Transaction(7, 970.0, Transaction.Type.DEPOSIT),
                new Transaction(8, 200.0, Transaction.Type.WITHDRAW)
        );

        Stream<List<Transaction>> listStream = Stream.of(transactions);
        int[] array = IntStream.range(1,40).toArray();
        System.out.println(Arrays.toString(array));
        System.out.println("-------------------------------------------------------");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("-------------------------------------------------------");
        DoubleSummaryStatistics doubleSummaryStatistics = listStream.flatMap(List::stream)
                .filter(s -> s.getType().equals(Transaction.Type.DEPOSIT))
                .mapToDouble(Transaction::getValue)
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics.getSum());
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getMin());
        System.out.println(doubleSummaryStatistics.getCount());
        System.out.println("-------------------------------------------------------");
        System.out.println(doubleSummaryStatistics);
        System.out.println("-------------------------------------------------------");
        DoubleSummaryStatistics extraChallenge = Stream.generate(() -> ((int) (Math.random() * 10)) + 1)
                .limit(20)
                .mapToDouble(Integer::doubleValue)
                .summaryStatistics();

        System.out.println(extraChallenge);
    }
}