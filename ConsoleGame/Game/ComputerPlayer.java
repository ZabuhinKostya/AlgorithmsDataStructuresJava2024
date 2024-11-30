package ConsoleGame.Game;

// Клас ComputerPlayer, який реалізує PlayerInput
class ComputerPlayer implements PlayerInput {
    private int lowerBound = 1;
    private int upperBound = 100;

    @Override
    public int getPlayerGuess() {
        try {
            Thread.sleep(2000); // Затримка для "обдумування"
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int guess = (lowerBound + upperBound) / 2; // Бінарний пошук
        System.out.println("Комп'ютер думає, що число: " + guess);
        return guess;
    }

    public void updateBounds(int lastGuess, boolean isHigher) {
        if (isHigher) {
            lowerBound = lastGuess + 1;
        } else {
            upperBound = lastGuess - 1;
        }
    }
}

