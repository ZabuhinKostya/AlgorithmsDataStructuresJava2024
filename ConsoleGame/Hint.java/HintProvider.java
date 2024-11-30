

class HintProvider {
    private int lowerBound = 1;
    private int upperBound = 100;

    public String getHint(int lastGuess, int secretNumber) {
        if (lastGuess < secretNumber) {
            lowerBound = Math.max(lowerBound, lastGuess + 1);
            return "Загаданное число больше. Попробуйте диапазон: " + lowerBound + " - " + upperBound;
        } else {
            upperBound = Math.min(upperBound, lastGuess - 1);
            return "Загаданное число меньше. Попробуйте диапазон: " + lowerBound + " - " + upperBound;
        }
    }
}