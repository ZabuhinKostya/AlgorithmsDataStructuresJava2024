package ConsoleGame.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

class ConsolePlayerInput implements PlayerInput {
    private Scanner scanner = new Scanner(System.in);

    public int getPlayerGuess() {
        while (true) {
            try {
                System.out.print("Введите ваше число: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода! Введите целое число.");
                scanner.next(); // Очистка ввода
            }
        }
    }
}
