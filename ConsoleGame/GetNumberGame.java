package ConsoleGame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// Інтерфейс для введення чисел гравцем
interface PlayerInput {
    int getPlayerGuess();
}

// Клас для роботи з консоллю
class ConsolePlayerInput implements PlayerInput {
    private Scanner scanner = new Scanner(System.in);

    public int getPlayerGuess() {
        while (true) {
            try {
                System.out.print("Введіть ваше число: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Невірний ввід! Будь ласка, введіть ціле число.");
                scanner.next(); // Очищення некоректного вводу
            }
        }
    }
}

// Клас для підказок
class HintProvider {
    private int lowerBound = 1;
    private int upperBound = 100;

    public String getHint(int lastGuess, int secretNumber) {
        if (lastGuess < secretNumber) {
            lowerBound = Math.max(lowerBound, lastGuess + 1);
            return "Загадане число більше. Спробуйте діапазон: " + lowerBound + " - " + upperBound;
        } else {
            upperBound = Math.min(upperBound, lastGuess - 1);
            return "Загадане число менше. Спробуйте діапазон: " + lowerBound + " - " + upperBound;
        }
    }
}

// Основний клас для логіки гри
class GameEngine {
    private int secretNumber;
    private PlayerInput playerInput;
    private HintProvider hintProvider;
    private int attempts;

    public GameEngine(PlayerInput playerInput) {
        this.playerInput = playerInput;
        this.hintProvider = new HintProvider();
        this.secretNumber = new Random().nextInt(100) + 1;
        this.attempts = 0;
    }

    public void startGame() {
        System.out.println("Вітаємо в грі 'Вгадай число'! Загадане число від 1 до 100.");
        boolean guessed = false;

        while (!guessed) {
            int guess = playerInput.getPlayerGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Вітаємо! Ви відгадали число за " + attempts + " спроб.");
                guessed = true;
            } else {
                System.out.println(hintProvider.getHint(guess, secretNumber));
            }
        }
    }
}

// Головний клас
public class GetNumberGame {
    public static void main(String[] args) {
        PlayerInput playerInput = new ConsolePlayerInput();
        GameEngine gameEngine = new GameEngine(playerInput);
        gameEngine.startGame();
    }
}

