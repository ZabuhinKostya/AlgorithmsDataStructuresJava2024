package ConsoleGame.Game;

import java.util.Random;
import java.util.Scanner;

class GameEngine {
    private int secretNumber;
    private PlayerInput playerInput;
    private HintProvider hintProvider;
    private int attempts;

    // Конструктор для випадкового числа (людина вгадує)
    public GameEngine(PlayerInput playerInput, HintProvider hintProvider) {
        this.playerInput = playerInput;
        this.hintProvider = hintProvider;
        this.secretNumber = new Random().nextInt(100) + 1;
        this.attempts = 0;
    }

    // Конструктор для числа, заданого користувачем (комп'ютер вгадує)
    public GameEngine(PlayerInput playerInput, HintProvider hintProvider, int secretNumber) {
        this.playerInput = playerInput;
        this.hintProvider = hintProvider;
        this.secretNumber = secretNumber;
        this.attempts = 0;
    }

    public void startGame() {
        System.out.println("Гра почалася!");
        boolean guessed = false;

        while (!guessed) {
            int guess = playerInput.getPlayerGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Вітаємо! Ви відгадали число " + secretNumber + " за " + attempts + " спроб.");
                guessed = true;
            } else {
                System.out.println(hintProvider.getHint(guess, secretNumber));

                // Оновлення меж для ComputerPlayer
                if (playerInput instanceof ComputerPlayer) {
                    ComputerPlayer computerPlayer = (ComputerPlayer) playerInput;
                    computerPlayer.updateBounds(guess, guess < secretNumber);
                }
            }
        }
    }
    public boolean askToPlayAgain() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Хочете зіграти ще раз? (так/ні): ");
            String answer = scanner.nextLine().toLowerCase();
            return answer.equals("так") || answer.equals("yes");
        }
    }
}