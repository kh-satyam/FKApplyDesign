package games.TicTacToe.gameModes;

import games.TicTacToe.TicTacToeGameRules;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.board.TicTacToeBoardCell;
import games.TicTacToe.gameModes.TicTacToeGameModes;
import games.TicTacToe.states.TicTacToeBoardCellStates;
import games.TicTacToe.states.TicTacToeGameStates;
import games.TicTacToe.statistics.TicTacToeGameStats;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class TicTacToeGameModeVComputer implements TicTacToeGameModes {

    TicTacToeBoard ticTacToeBoard;
    private int gridRows;
    private int gridColumns;
    private int currentRow;
    private int currentColumn;
    private int lastRow;
    private int lastColumn;
    private String currentPlayer;
    private int dimensions;
    private int currentState;
    private String playerName;
    private String playerSign;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private TicTacToeGameStats ticTacToeGameStats;

    public TicTacToeGameModeVComputer(int numRows,int numColumns,
                                      String playerName,
                                      String startingPlayer,
                                      boolean playerIsStarting,
                                      TicTacToeGameStats ticTacToeGameStats) {

        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
        ticTacToeBoard = new TicTacToeBoard(numRows,numColumns);
        this.gridRows = numRows;
        this.gridColumns = numColumns;
        this.currentPlayer = startingPlayer;
        this.dimensions = numColumns;
        this.currentState = TicTacToeGameStates.PLAYING;
        this.playerName = playerName;
        if(playerIsStarting){
            this.playerSign = startingPlayer;
        }else{
            this.playerSign = startingPlayer.equals(TicTacToeBoardCellStates.CIRCLE) ? TicTacToeBoardCellStates.CROSS:
                    TicTacToeBoardCellStates.CIRCLE;
        }
        this.ticTacToeGameStats = ticTacToeGameStats;
    }

    public void playGame(){
        ticTacToeBoard.printBoard();
        System.out.println();
        try {
            while (currentState == TicTacToeGameStates.PLAYING) {
                playerMove();
                updateGame();
                System.out.println("Updated Board");
                ticTacToeBoard.printBoard();
                if(currentPlayer.equals(playerSign)) {
                    System.out.println("Do you want to undo the last move");
                    System.out.println("1.Yes");
                    System.out.println("2.No");
                    String answer = bufferedReader.readLine();
                    int response = Integer.valueOf(answer);
                    if (response == 1){
                        undoMove();
                        ticTacToeBoard.printBoard();
                        continue;
                    }
                    ticTacToeBoard.printBoard();
                }
                if (currentState == TicTacToeGameStates.CROSSWON) {
                    if (currentPlayer.equals(playerSign))
                      {
                          System.out.println("You Won,Ending the Game.");
                          ticTacToeGameStats.addStats(playerName);
                      }

                    else
                        System.out.println("Computer won the game");
                } else if (currentState == TicTacToeGameStates.OWON) {
                    if (currentPlayer.equals(playerSign))
                        System.out.println("You Won,Ending the Game.");
                    else
                        System.out.println("Computer won the game");
                } else if (currentState == TicTacToeGameStates.DRAW) {
                    System.out.println("Game Ended in a Draw.");
                }

                currentPlayer = (currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE)) ? TicTacToeBoardCellStates.CROSS :
                        TicTacToeBoardCellStates.CIRCLE;
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private void undoMove() {
        ticTacToeBoard.setStateTicTacToeBoard(this.currentRow,this.currentColumn,TicTacToeBoardCellStates.EMPTY);
    }

    private void playerMove() {

        boolean validInput = false;

        if(currentPlayer.equals(playerSign)) {
            System.out.println("Player:" + playerSign + ",Enter your move:row and column");
            do{
                try{

                    String[] move = bufferedReader.readLine().split(" ");
                    if(move.length==2) {
                        int row = Integer.valueOf(move[0]);
                        int column = Integer.valueOf(move[1]);
                        if(ticTacToeBoard.isValidMove(row,column)) {
                            validInput = true;
                            this.lastRow = this.currentRow;
                            this.lastColumn = this.currentColumn;
                            this.currentRow = row;
                            this.currentColumn = column;
                            ticTacToeBoard.setStateTicTacToeBoard(row,column,currentPlayer);
                        }
                    }else{
                        System.out.println("Invlaid move ,Please try again");
                    }

                }catch (Exception e){

                }
            }while(!validInput);


        }else{
            System.out.println("Its Computer Player Turn");
            Optional<TicTacToeBoardCell> optionalTicTacToeBoardCell = ticTacToeBoard.getRandomBoardCell();
            if(optionalTicTacToeBoardCell.isPresent()) {
                TicTacToeBoardCell ticTacToeBoardCell = optionalTicTacToeBoardCell.get();
                this.currentRow = ticTacToeBoardCell.getRow();
                this.currentColumn = ticTacToeBoardCell.getColumn();
                ticTacToeBoard.setStateTicTacToeBoard(currentRow,currentColumn,currentPlayer);
            }else{
                System.out.println("Game Error Occurred");
            }
        }

    }

    public void updateGame() {
        if(hasWon()) {
            currentState = currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE) ? TicTacToeGameStates.OWON:TicTacToeGameStates.CROSSWON;
        }
        else if(isDraw()) {
            currentState = TicTacToeGameStates.DRAW;
        }
    }

    private boolean hasWon() {
        return TicTacToeGameRules.hasWonGeneric(ticTacToeBoard,gridRows,gridColumns,0,
                0,dimensions,currentPlayer);
    }

    public boolean isDraw() {
        return TicTacToeGameRules.isDrawGeneric(ticTacToeBoard);
    }
}
