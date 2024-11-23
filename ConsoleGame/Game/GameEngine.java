package ConsoleGame.Game;

import java.util.Random;

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
        System.out.println("Добро пожаловать в игру 'Угадай число'! Загаданное число от 1 до 100.");
        boolean guessed = false;

        while (!guessed) {
            int guess = playerInput.getPlayerGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("Поздравляем! Вы угадали число за " + attempts + " попыток.");
                guessed = true;
            } else {
                System.out.println(hintProvider.getHint(guess, secretNumber));
            }
        }
    }
}
