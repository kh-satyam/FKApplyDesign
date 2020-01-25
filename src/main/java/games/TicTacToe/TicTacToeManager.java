package games.TicTacToe;

import games.GameManager;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.gameModes.TicTacToeGameModeVComputer;
import games.TicTacToe.gameModes.TicTacToeGameModeVHuman;
import games.TicTacToe.gameModes.TicTacToeGameModes;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToeManager implements GameManager {

    TicTacToeBoard ticTacToeBoard;
    TicTacToeGameModes ticTacToeGameMode;

    public TicTacToeManager() {
        ticTacToeBoard = new TicTacToeBoard(3,3);
    }

    public void playGame() {
        System.out.println("TicTacToe :Game Simulation");
        System.out.println("");
        System.out.println("1.Play vs Computer");
        System.out.println("2.Play vs Human");
        System.out.println("");
        System.out.println("Enter your choice:");

        try{

            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String choice = bufferedReader.readLine();
            if(choice.equals("2")) {
                System.out.println("Who would like to go First : X or O");
                System.out.println("1.X");
                System.out.println("2.O");
                System.out.println("");
                choice = bufferedReader.readLine();
                int startingPlayer = Integer.valueOf(choice);
                this.ticTacToeGameMode = new TicTacToeGameModeVHuman(ticTacToeBoard,startingPlayer);
                ticTacToeGameMode.playGame();
            }else if (choice.equals("1")){
                System.out.println("Would you like to play as O or X:");
                System.out.println("Enter your input:");
                String humanPlayer = bufferedReader.readLine();
                System.out.println("Who you Like to Go First");
                System.out.println("1.Yes");
                System.out.println("2.No");
                System.out.println("");
                choice = bufferedReader.readLine();
                boolean goFirst;
                if(choice.equals("1")){
                    goFirst=true;
                }else{
                    goFirst=false;
                }
                this.ticTacToeGameMode = new TicTacToeGameModeVComputer(ticTacToeBoard,goFirst,humanPlayer);
                ticTacToeGameMode.playGame();
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
