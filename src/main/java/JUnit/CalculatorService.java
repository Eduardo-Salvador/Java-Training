package JUnit;

public class CalculatorService {
    private final CalculatorRepository repository;

    public CalculatorService(CalculatorRepository repository) {
        this.repository = repository;
    }

    public int sum(int a, int b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException("Negative values not accepted");
        int result = a + b;
        repository.save(result);
        return result;
    }

    public int subtract(int a, int b) {
        int result = a - b;
        repository.save(result);
        return result;
    }

    public int multiply(int a, int b) {
        int result = a * b;
        repository.save(result);
        return result;
    }

    public int divide(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Cannot divide by zero");
        int result = a / b;
        repository.save(result);
        return result;
    }
}