package ProgrammingLogicWithOOP.E18.Controller;

public class Report {
    private double franciscoHeight = 1.5;
    private double saraHeight = 1.1;

    public void runReport(){
        int necessaryYear = 0;
        System.out.println("Algorithm 18:");
        System.out.println("Francisco (1.5m) grows 2 centimeters per year, while Sara (1.1m) grows 3 centimeters per year");
        while (getFranciscoHeight() > getSaraHeight()){
            setFranciscoHeight(getFranciscoHeight() + 0.02);
            setSaraHeight(getSaraHeight() + 0.03);
            necessaryYear++;
        }
        System.out.printf("It takes %d years for Sara to surpass Francisco in height", ++necessaryYear);
    }

    public double getSaraHeight() {
        return saraHeight;
    }

    public void setSaraHeight(double saraHeight) {
        this.saraHeight = saraHeight;
    }

    public double getFranciscoHeight() {
        return franciscoHeight;
    }

    public void setFranciscoHeight(double franciscoHeight) {
        this.franciscoHeight = franciscoHeight;
    }
}