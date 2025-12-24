package StudyChallenges.NavalBattleGame;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NavalBattle game = new NavalBattle();
        int numRandom = (int) (Math.random() * 5);
        int[] loc = new int[]{numRandom, numRandom + 1, numRandom + 2};
        game.setLocationCells(loc);
        Scanner input = new Scanner(System.in);
        String result = game.checkYourself(input, 0);
        System.out.println(result);
    }
}
