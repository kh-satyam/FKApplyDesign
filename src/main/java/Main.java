import games.GameManager;
import games.TicTacToe.TicTacToeManager;
import games.TicTacToe.gameModes.TicTacToeGameModes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        GameManager ticTacToeManager = new TicTacToeManager();
        ticTacToeManager.playGame();
    }
}
