package UtilityClasses.Challenge2.E3.Domain;
import java.time.LocalDate;

public class Consumption {
    private double value;
    private LocalDate date;
    private Category category;

    public Consumption(double value, LocalDate date, Category category) {
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }
}
