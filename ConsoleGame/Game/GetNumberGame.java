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
                System.out.print("Введiть ваше число: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Невiрний ввiд! Будь ласка, введiть цiле число.");
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
            return "Загадане число бiльше. Спробуйте дiапазон: " + lowerBound + " - " + upperBound;
        } else {
            upperBound = Math.min(upperBound, lastGuess - 1);
            return "Загадане число менше. Спробуйте дiапазон: " + lowerBound + " - " + upperBound;
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
                System.out.println("Вiтаємо! Ви вiдгадали число за " + attempts + " спроб.");
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



// Головний клас
public class GetNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВиберiть режим гри:");
            System.out.println("1. Ви вгадуєте число, загадане комп'ютером.");
            System.out.println("2. Комп'ютер вгадує ваше число.");
            System.out.println("3. Вихiд.");
            System.out.print("Ваш вибiр: ");

            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("Дякуємо за гру! До побачення!");
                break;
            }

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
                System.out.print("Загадайте число для комп'ютера (вiд 1 до 100): ");
                int secretNumber = scanner.nextInt();

                // Перевірка введення числа в правильному діапазоні
                while (secretNumber < 1 || secretNumber > 100) {
                    System.out.print("Невiрне число! Введiть значення вiд 1 до 100: ");
                    secretNumber = scanner.nextInt();
                }

                gameEngine = new GameEngine(playerInput, hintProvider, secretNumber);
            } else {
                System.out.println("Невiрний вибiр. Спробуйте знову.");
                continue;
            }

            gameEngine.startGame();
        }

        scanner.close();
        }
    }
}