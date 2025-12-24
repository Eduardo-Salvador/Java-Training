package StudyChallenges.NavalBattleGame;

import java.util.Scanner;

public class NavalBattle {
    private int[] locationCells = new int[7];
    private int numOfHits = 0;

    public String checkYourself(Scanner input, int counter){
        while (numOfHits != 3){
            System.out.print("Enter any number: ");
            int guess = input.nextInt();
            counter++;
            if(locationCells[guess] == 1){
                System.out.println("Hit");
                locationCells[guess] = 0;
                numOfHits++;
                continue;
            }
            System.out.println("Miss");
        }
        return "Kill\nYou win! Took a " + counter + " guesses";
    }

    public void setLocationCells(int[] loc){
        for(int i: loc){
            locationCells[i] = 1;
        }
    }
}