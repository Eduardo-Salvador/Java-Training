package Lambdas.BinaryOperator.Domain;
import java.util.List;
import java.util.function.BinaryOperator;

public class Warehouse {
    private String location;
    private Integer stock;

    public Warehouse(String location, Integer stock) {
        this.location = location;
        this.stock = stock;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public static Integer mergeStocks(List<Warehouse> a, List<Warehouse> b, BinaryOperator<Integer> doubleBinaryOperator){
        int lenght = 0;
        int sumValue = 0;
        lenght = Math.max(a.size(), b.size());
        for (int i = 0; i < lenght; i++) {
            sumValue += doubleBinaryOperator.apply(a.get(i).getStock(), b.get(i).getStock());
        }
        return sumValue;
    }

    public static void dashboardResult(List<Warehouse> a, List<Warehouse> b){
        BinaryOperator<Integer> max = (x, y) -> Math.max(x, y);
        BinaryOperator<Integer> min = (x, y) -> Math.min(x, y);
        BinaryOperator<Integer> average = (x, y) -> (x + y);

        int lenght = 0;
        int valueMax = 0;
        int valueMin = a.getFirst().getStock();
        int valueAverage = 0;
        lenght = Math.max(a.size(), b.size());
        for (int i = 0; i < lenght; i++) {
            valueMax = max.apply(a.get(i).getStock(), valueMax);
            valueMax = max.apply(b.get(i).getStock(), valueMax);
            valueMin = min.apply(a.get(i).getStock(), valueMin);
            valueMin = min.apply(b.get(i).getStock(), valueMin);
            valueAverage += average.apply(a.get(i).getStock(), b.get(i).getStock());
        }
        System.out.println("Max stock value in Lists: " + valueMax);
        System.out.println("Min stock value in Lists: " + valueMin);
        System.out.println("Average stock value in Lists: " + (valueAverage / (a.size() + b.size())));
    }
}