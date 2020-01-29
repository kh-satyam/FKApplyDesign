package games.TicTacToe;

import games.GameManager;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.gameModes.*;
import games.TicTacToe.statistics.TicTacToeGameStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToeManager implements GameManager {

    TicTacToeBoard ticTacToeBoard;
    TicTacToeGameModes ticTacToeGameMode;
    TicTacToeGameStats ticTacToeGameStats;

    public TicTacToeManager() {

        ticTacToeBoard = new TicTacToeBoard(3,3);
        ticTacToeGameStats = new TicTacToeGameStats();
    }

    public void playGame() {


        try{

                while(true) {
                    System.out.println("TicTacToe :Game Simulation");
                    System.out.println("");
                    System.out.println("1.Play TicTacToe on 3By3 Grid vs Computer");
                    System.out.println("2.Play vs Human on 9*9Grid");
                    System.out.println("3.Play vs Human on 27*27 Grid");
                    System.out.println("4.Play vs Human on Custom Grid");
                    System.out.println("5.Play vs Human on 4*4Grid");
                    System.out.println("6.Play HexagonalTicTacToe");
                    System.out.println("7.View LeaderBoard");
                    System.out.println("8.Exit");
                    System.out.println("");
                    System.out.println("Enter your choice:");
                    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String choice = bufferedReader.readLine();
                    if (choice.equals("1")) {
                        System.out.println("Enter player Name:");
                        String playerName = bufferedReader.readLine();
                        System.out.println("Who you Like to Go First");
                        System.out.println("1.Yes");
                        System.out.println("2.No");
                        System.out.println("");
                        boolean willgoFirst = Integer.valueOf(bufferedReader.readLine().split(" ")[0]) == 1 ? true : false;
                        System.out.println("Wil you play as X: or O:");
                        System.out.println("1.X");
                        System.out.println("2.O");
                        String startingPlayerSign = bufferedReader.readLine();
                        this.ticTacToeGameMode = new TicTacToeGameModeVComputer(3, 3, playerName,
                                startingPlayerSign, willgoFirst, ticTacToeGameStats);
                        ticTacToeGameMode.playGame();
                    } else if (choice.equals("2")) {
                        System.out.println("Enter player1 Name: ");
                        String player1 = bufferedReader.readLine();
                        System.out.println("Enter player2Name");
                        String player2 = bufferedReader.readLine();
                        System.out.println("Who wil go first :" + player1 + "  or " + player2);
                        System.out.println("Enter 1 or 2");
                        boolean willPlayerOneStart = Integer.valueOf(bufferedReader.readLine().split(" ")[0]) == 1 ? true : false;
                        if (willPlayerOneStart) {
                            System.out.println(player1 + ",Will you play as  : X or O");
                        } else {
                            System.out.println(player2 + ",Will you play as  : X or O");
                        }
                        System.out.println("X");
                        System.out.println("O");
                        System.out.println("");
                        String startingPlayerSign = bufferedReader.readLine();
                        ticTacToeGameMode = new TicTacToeGenericModeVHuman(9, 9,
                                startingPlayerSign, player1, player2, willPlayerOneStart, ticTacToeGameStats);
                        ticTacToeGameMode.playGame();
                    } else if (choice.equals("3")) {
                        System.out.println("Enter player1 Name: ");
                        String player1 = bufferedReader.readLine();
                        System.out.println("Enter player2Name");
                        String player2 = bufferedReader.readLine();
                        System.out.println("Who wil go first :" + player1 + "  or " + player2);
                        System.out.println("Enter 1 or 2");
                        boolean willPlayerOneStart = Integer.valueOf(bufferedReader.readLine().split(" ")[0]) == 1 ? true : false;
                        if (willPlayerOneStart) {
                            System.out.println(player1 + ",Will you play as  : X or O");
                        } else {
                            System.out.println(player2 + ",Will you play as  : X or O");
                        }
                        System.out.println("X");
                        System.out.println("O");
                        System.out.println("");
                        String startingPlayerSign = bufferedReader.readLine();
                        ticTacToeGameMode = new TicTacToeGenericModeVHuman(27, 27,
                                startingPlayerSign, player1, player2, willPlayerOneStart, ticTacToeGameStats);
                        ticTacToeGameMode.playGame();
                    } else if (choice.equals("4")) {
                        System.out.println("Enter number of rows and columns in TicTacToe Grid in multiples of 3 separated by space");
                        String[] gridDimensions = bufferedReader.readLine().split(" ");
                        int rows = Integer.valueOf(gridDimensions[0]);
                        int columns = Integer.valueOf(gridDimensions[1]);
                        System.out.println("Enter player1 Name: ");
                        String player1 = bufferedReader.readLine();
                        System.out.println("Enter player2Name");
                        String player2 = bufferedReader.readLine();
                        System.out.println("Who wil go first :" + player1 + "  or " + player2);
                        System.out.println("Enter 1 or 2");
                        boolean willPlayerOneStart = Integer.valueOf(bufferedReader.readLine().split(" ")[0]) == 1 ? true : false;
                        if (willPlayerOneStart) {
                            System.out.println(player1 + ",Will you play as  : X or O");
                        } else {
                            System.out.println(player2 + ",Will you play as  : X or O");
                        }
                        System.out.println("X");
                        System.out.println("O");
                        System.out.println("");
                        String startingPlayerSign = bufferedReader.readLine();
                        ticTacToeGameMode = new TicTacToeGenericModeVHuman(rows, columns,
                                startingPlayerSign, player1, player2, willPlayerOneStart, ticTacToeGameStats);
                        ticTacToeGameMode.playGame();
                    } else if (choice.equals("5")) {
                        System.out.println("Who would like to go First : X or O");
                        System.out.println("1.X");
                        System.out.println("2.O");
                        System.out.println("");
                        choice = bufferedReader.readLine();
                        int startingPlayer = Integer.valueOf(choice);
                        ticTacToeBoard = new TicTacToeBoard(4, 4);
                        this.ticTacToeGameMode = new EnhancedTicTacToeGameMode4By4VHuman(ticTacToeBoard, startingPlayer);
                        ticTacToeGameMode.playGame();
                    } else if (choice.equals("6")) {
                        System.out.println("HexagonalTicTacToeMode");
                    } else if (choice.equals("7")) {
                        System.out.println("TicTacToe Leaderboard");
                        System.out.println("Name:              Wins:           Losses");
                        ticTacToeGameStats.printStats();
                    }else if(choice.equals("8")){
                        break;
                    }
                }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
