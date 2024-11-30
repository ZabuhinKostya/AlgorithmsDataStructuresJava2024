package ConsoleGame.Game;

// Реалізація інтерфейсу HintProvider
class RangeHintProvider implements HintProvider {
    private int lowerBound = 1;
    private int upperBound = 100;

    @Override
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
