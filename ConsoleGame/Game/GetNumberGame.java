package ConsoleGame.Game;

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

    // Старий конструктор (для гравця-людини)
    public GameEngine(PlayerInput playerInput, HintProvider hintProvider) {
        this.playerInput = playerInput;
        this.hintProvider = hintProvider;
        this.secretNumber = new Random().nextInt(100) + 1; // Генерація випадкового числа
        this.attempts = 0;
    }

    // Новий конструктор (для гри з комп'ютером)
    public GameEngine(PlayerInput playerInput, HintProvider hintProvider, int secretNumber) {
        this.playerInput = playerInput;
        this.hintProvider = hintProvider;
        this.secretNumber = secretNumber;
        this.attempts = 0;
    }

    public void startGame() {
        System.out.println("Гра почалася! Вгадуйте число.");
        boolean guessed = false;

        while (!guessed) {
            int guess = playerInput.getPlayerGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Вітаємо! Ви відгадали число за " + attempts + " спроб.");
                guessed = true;
            } else {
                System.out.println(hintProvider.getHint(guess, secretNumber));

                if (playerInput instanceof ComputerPlayer) {
                    ComputerPlayer computerPlayer = (ComputerPlayer) playerInput;
                    computerPlayer.updateBounds(guess, guess < secretNumber);
                }
            }
        }
    }
     // Метод для запроса пользователя на продолжение игры
     public boolean askToPlayAgain() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Хочете зіграти ще раз? (так/ні): ");
            String answer = scanner.nextLine().toLowerCase();
            return answer.equals("так") || answer.equals("yes");
        }
    }
}


// Головний клас
public class GetNumberGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Виберіть режим гри: ");
            System.out.println("1. Ви вгадуєте число, загадане комп'ютером.");
            System.out.println("2. Комп'ютер вгадує ваше число.");
            int choice = scanner.nextInt();

            PlayerInput playerInput;
            HintProvider hintProvider = new HintProvider();
            GameEngine gameEngine;

            if (choice == 1) {
                // Людина вгадує число, яке загадав комп'ютер
                int secretNumber = new Random().nextInt(100) + 1; // Комп'ютер загадує випадкове число
                playerInput = new ConsolePlayerInput();
                gameEngine = new GameEngine(playerInput, hintProvider, secretNumber);
            } else if (choice == 2) {
                // Комп'ютер вгадує число, яке загадала людина
                playerInput = new ComputerPlayer();
                System.out.print("Загадайте число для комп'ютера (від 1 до 100): ");
                int secretNumber = scanner.nextInt();

                // Перевірка введення числа в правильному діапазоні
                while (secretNumber < 1 || secretNumber > 100) {
                    System.out.print("Невірне число! Введіть значення від 1 до 100: ");
                    secretNumber = scanner.nextInt();
                }

                gameEngine = new GameEngine(playerInput, hintProvider, secretNumber);
            } else {
                System.out.println("Невірний вибір. Спробуйте знову.");
                return;
            }

            gameEngine.startGame();
        }
    }
}