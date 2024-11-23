package ConsoleGame.Game;

public class GetNumberGame {
    public static void main(String[] args) {
        PlayerInput playerInput = new ConsolePlayerInput();
        GameEngine gameEngine = new GameEngine(playerInput);
        gameEngine.startGame();
    }
}
