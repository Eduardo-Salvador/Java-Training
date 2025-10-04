package LogicTrainingWithOOP.E19.Controller;

public class Report {
    private void multiplicationTable1to10(){
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println((i+1) + " Multiplication Table:");
            while (counter != 10){
                System.out.println((i+1) + " * " + (counter+1) + " = " + ((i+1) * (counter+1)));
                counter++;
            }
            counter = 0;
        }
    }

    public void runReport(){
        System.out.println("Algorithm 19:");
        System.out.println("Displaying multiplication tables from 1 to 10:");
        multiplicationTable1to10();
    }
}