package LogicTrainingWithOOP.E24.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private static final int CAR_SPENT_PER_LITER = 12;

    private double validValue(double num, Scanner input){
        while (num <= 0){
            System.out.print("Invalid input, please input again: ");
            num = input.nextDouble();
        }
        return num;
    }

    private double averageSpeedCalculator(double timeTravelling, double distance){
        return (distance / timeTravelling);
    }

    public void travelReport(double timeTravelling, double averageSpeed, double distance, double litersUsed){
        System.out.println("Travel Report:");
        System.out.printf("Travel Time = %.2fH%n", timeTravelling);
        System.out.printf("Distance = %.2fKm%n", distance);
        System.out.printf("Average Speed = %.2fKm/h%n", averageSpeed);
        System.out.printf("Liters Used = %.2fL%n", litersUsed);
        System.out.println("Car consumption = " + CAR_SPENT_PER_LITER + "Km per Liter");
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber timeTravelling = new CustomNumber();
        CustomNumber averageSpeed = new CustomNumber();
        CustomNumber distance = new CustomNumber();
        CustomNumber litersUsed = new CustomNumber();
        System.out.println("Algorithm 24:");
        System.out.print("How long did the trip last in hours (Ex: 2.4): ");
        timeTravelling.setValueDouble(validValue(input.nextDouble(), input));
        System.out.print("What was the distance traveled in km: ");
        distance.setValueDouble(validValue(input.nextDouble(), input));
        averageSpeed.setValueDouble(averageSpeedCalculator(timeTravelling.getValueDouble(), distance.getValueDouble()));
        litersUsed.setValueDouble(distance.getValueDouble() / CAR_SPENT_PER_LITER);
        travelReport(timeTravelling.getValueDouble(), averageSpeed.getValueDouble(), distance.getValueDouble(), litersUsed.getValueDouble());
        input.close();
    }
}