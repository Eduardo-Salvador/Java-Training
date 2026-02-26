package Threads_ConcurrentDataStructures.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ScoreBoard {
    private final ConcurrentHashMap<String, Integer> playerScore = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, Integer> getPlayerScore() {
        return playerScore;
    }

    public static class Player implements Runnable {
        private final String name;
        private final Integer initialScore;
        private final ScoreBoard scoreBoard;

        public Player(String name, Integer initialScore, ScoreBoard scoreBoard) {
            this.name = name;
            this.initialScore = initialScore;
            this.scoreBoard = scoreBoard;
        }

        @Override
        public void run() {
            scoreBoard.getPlayerScore().putIfAbsent(name, initialScore);

            for (int i = 0; i < 5; i++) {
                Integer newScore = scoreBoard.getPlayerScore().compute(name, (key, value) -> value + 1);
                System.out.println("player- " + this.name + " updated score to: " + newScore);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScoreBoard scoreBoard = new ScoreBoard();
        Runnable doubleScore = () -> {
            for (String name : scoreBoard.getPlayerScore().keySet()) {
                Integer newScore = scoreBoard.getPlayerScore().computeIfPresent(name, (key, value) -> value * 2);
                System.out.println(Thread.currentThread().getName() + " It says: player- " + name + " updated score to: " + newScore);
            }
        };

        Thread p1 = new Thread(new Player("Eduardo", 0, scoreBoard));
        Thread p2 = new Thread(new Player("Maria", 0, scoreBoard));
        Thread s1 = new Thread(doubleScore, "T-System");

        p1.start();
        p2.start();
        s1.start();

        p1.join();
        p2.join();
        s1.join();

        scoreBoard.getPlayerScore()
                .forEach((player, score) ->
                        System.out.println("Final score - " + player + ": " + score)
                );
    }
}