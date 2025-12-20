package LogicTrainingWithOOP.E15.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private void lifeCounter(int day, int month, int year, Scanner input){
        while(year > 2025 || year < 0 || day < 1 || day > 31 || month < 1 || month > 12) {
            System.out.println("Invalid Birthdate, try again");
            System.out.print("What day were you born?: ");
            day = input.nextInt();
            System.out.print("What month were you born?: ");
            month = input.nextInt();
            System.out.print("What year were you born?: ");
            year = input.nextInt();
        }
        if (year == 2025 && month > 10){
            System.out.println("Invalid Month, try again");
            System.out.print("What month were you born?: ");
            month = input.nextInt();
        } else if (day > 3){
            System.out.println("Invalid day, try again");
            System.out.print("What day were you born?: ");
            day = input.nextInt();
        }

        int yearAge = 2025 - year; //2025 is current year
        int monthAge = 10 - month; //October is current month
        int dayAge = 03 - day; //03 is current day

        if (dayAge < 0) {
            dayAge += 30;
            monthAge--;
        }
        if (monthAge < 0) {
            monthAge += 12;
            yearAge--;
        }

        System.out.println("You have: " + yearAge + " years, " + monthAge + " months and " + dayAge + " days of life.");
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber day = new CustomNumber();
        CustomNumber month = new CustomNumber();
        CustomNumber year = new CustomNumber();
        System.out.println("Algorithm 15:");
        System.out.print("What day were you born?: ");
        day.setValueInt(input.nextInt());
        System.out.print("What month were you born?: ");
        month.setValueInt(input.nextInt());
        System.out.print("What year were you born?: ");
        year.setValueInt(input.nextInt());
        lifeCounter(day.getValueInt(), month.getValueInt(), year.getValueInt(), input);
        input.close();
    }
}