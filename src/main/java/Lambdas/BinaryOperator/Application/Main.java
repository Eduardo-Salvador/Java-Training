package Lambdas.BinaryOperator.Application;
import Lambdas.BinaryOperator.Domain.Warehouse;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        List<Warehouse> warehouseA = Arrays.asList(
                new Warehouse("São Paulo", 150),
                new Warehouse("Rio de Janeiro", 200),
                new Warehouse("Belo Horizonte", 100),
                new Warehouse("Curitiba", 180),
                new Warehouse("Porto Alegre", 220)
        );

        List<Warehouse> warehouseB = Arrays.asList(
                new Warehouse("São Paulo", 120),
                new Warehouse("Rio de Janeiro", 250),
                new Warehouse("Belo Horizonte", 90),
                new Warehouse("Curitiba", 200),
                new Warehouse("Porto Alegre", 180)
        );

        Integer result = Warehouse.mergeStocks(warehouseA, warehouseB, (x, y) -> x + y);
        System.out.println("Sum stocks: " + result + "Un");

        result = Warehouse.mergeStocks(warehouseA, warehouseB, (x, y) -> x - y);
        System.out.println("Sub stocks: " + result + "Un");

        result = Warehouse.mergeStocks(warehouseA, warehouseB, (x, y) -> x * y);
        System.out.println("Mult stocks: " + result + "Un");

        BinaryOperator<Integer> max = (x, y) -> Math.max(x, y);
        BinaryOperator<Integer> min = (x, y) -> Math.min(x, y);
        BinaryOperator<Integer> average = (x, y) -> (x + y) / 2;

        Integer maxResult = max.apply(warehouseA.get(4).getStock(), warehouseB.get(1).getStock());
        Integer minResult = min.apply(warehouseA.get(2).getStock(), warehouseB.get(2).getStock());
        Integer averageResult = average.apply(warehouseA.get(3).getStock(), warehouseB.get(0).getStock());

        System.out.println("Max in Warehouse A data 4 x Warehouse B data 1: " + maxResult);
        System.out.println("Min in Warehouse A data 2 x Warehouse B data 2: " + minResult);
        System.out.println("Average in Warehouse A data 3 x Warehouse B data 0: " + averageResult);

        Warehouse.dashboardResult(warehouseA, warehouseB);
    }
}