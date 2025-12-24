package ProgrammingLogicWithOOP.E15;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Report {

    LocalDate now = LocalDate.now();

    private void lifeCounter(int day, int month, int year, Scanner input){
        while(year > now.getYear() || year < 0 || day < 1 || day > 31 || month < 1 || month > 12) {
            System.out.println("Invalid Birthdate, try again");
            System.out.print("What day were you born?: ");
            day = input.nextInt();
            System.out.print("What month were you born?: ");
            month = input.nextInt();
            System.out.print("What year were you born?: ");
            year = input.nextInt();
        }
        if (year == now.getYear() && month > now.getMonthValue()){
            System.out.println("Invalid Month, try again");
            System.out.print("What month were you born?: ");
            month = input.nextInt();
        } else if (day > now.getDayOfMonth()){
            System.out.println("Invalid day, try again");
            System.out.print("What day were you born?: ");
            day = input.nextInt();
        }

        int yearAge = now.getYear() - year;
        int monthAge = now.getMonthValue() - month;
        int dayAge = now.getDayOfMonth() - day;

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