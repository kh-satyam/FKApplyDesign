package games.TicTacToe.gameModes;

import games.TicTacToe.TicTacToeGameRules;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.states.TicTacToeBoardCellStates;
import games.TicTacToe.states.TicTacToeGameStates;
import games.TicTacToe.statistics.TicTacToeGameStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToeGenericModeVHuman implements TicTacToeGameModes {

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
    private String player1Name;
    private String player2Name;
    private String player1Sign;
    private String player2Sign;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private TicTacToeGameStats ticTacToeGameStats;

    public TicTacToeGenericModeVHuman(int numRows,int numColumns,String startingPlayer,
                                String player1Name,
                                String player2Name,
                                boolean playerOneIsStarting, TicTacToeGameStats ticTacToeGameStats) {

        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
        ticTacToeBoard = new TicTacToeBoard(numRows,numColumns);
        this.gridRows = numRows;
        this.gridColumns = numColumns;
        this.currentPlayer = startingPlayer;
        this.dimensions = numColumns;
        this.currentState = TicTacToeGameStates.PLAYING;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        if(playerOneIsStarting){
            this.player1Sign = startingPlayer;
            this.player2Sign = TicTacToeBoardCellStates.CIRCLE.equals(player1Sign)
                                ? TicTacToeBoardCellStates.CROSS:TicTacToeBoardCellStates.CIRCLE;
        }
        this.ticTacToeGameStats = ticTacToeGameStats;
    }

    @Override
    public void playGame() {
        System.out.println("Welcome To TicTacToe");
        System.out.println("Enjoy the Game");
        System.out.println();
        ticTacToeBoard.printBoard();
        try{
            while(currentState == TicTacToeGameStates.PLAYING) {
                playerMove();
                updateGame();
                ticTacToeBoard.printBoard();

                System.out.println("Do you want to undo the last move");
                System.out.println("1.Yes");
                System.out.println("2.No");
                String answer = bufferedReader.readLine();
                int response = Integer.valueOf(answer);
                if(response==1) {
                    undoMove();
                    ticTacToeBoard.printBoard();
                    continue;
                }
                if(currentState==TicTacToeGameStates.CROSSWON) {
                    System.out.println("X Won,Ending the Game.");
                    String winner = player1Sign.equals(TicTacToeBoardCellStates.CROSS) ? player1Name:player2Name;
                    ticTacToeGameStats.addStats(winner);
                }else if(currentState==TicTacToeGameStates.OWON){
                    System.out.println("O Won,Ending The Game");
                }else if(currentState==TicTacToeGameStates.DRAW){
                    System.out.println("Game Ended in a Draw.");
                }

                currentPlayer = (currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE)) ? TicTacToeBoardCellStates.CROSS:
                        TicTacToeBoardCellStates.CIRCLE;
            }
        }catch (IOException e) {
            System.out.println(e);
        }

    }

    private void playerMove() {


        boolean validInput = false;

        do{
            if(currentPlayer.equals(TicTacToeBoardCellStates.CROSS) ){
                System.out.println("Player X: Enter your move: row and column");
            }else{
                System.out.println("Player O : Enter your move : row and column");
            }
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
                    System.out.println("Invalid move ,Please try again");
                }

            }catch (Exception e){

            }
        }while(!validInput);
    }

    private void undoMove() {
        ticTacToeBoard.setStateTicTacToeBoard(this.currentRow,this.currentColumn,TicTacToeBoardCellStates.EMPTY);
    }

    private void updateGame() {
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

    private boolean isDraw() {
        return TicTacToeGameRules.isDrawGeneric(ticTacToeBoard);
    }
}
