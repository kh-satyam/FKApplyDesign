package games.TicTacToe.gameModes;

import games.TicTacToe.TicTacToeGameRules;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.states.TicTacToeBoardCellStates;
import games.TicTacToe.states.TicTacToeGameStates;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToeGameModeVHuman implements TicTacToeGameModes {

    private TicTacToeBoard ticTacToeBoard;
    private int currentState;
    private String currentPlayer;

    private int currentRow;
    private int currentColumn;

    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;

    private int lastRow;
    private int lastCol;

    public TicTacToeGameModeVHuman(TicTacToeBoard ticTacToeBoard,int startingPlayer) {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        this.ticTacToeBoard = ticTacToeBoard;
        this.currentState = TicTacToeGameStates.PLAYING;
        if(startingPlayer==1) {
            currentPlayer = TicTacToeBoardCellStates.CROSS;
        }else{
            currentPlayer = TicTacToeBoardCellStates.CIRCLE;
        }

    }

    public void playGame(){
        try {
            ticTacToeBoard.printBoard();
            System.out.println();

            while (currentState == TicTacToeGameStates.PLAYING) {
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
                }
                ticTacToeBoard.printBoard();
                if (currentState == TicTacToeGameStates.CROSSWON) {
                    System.out.println("X Won,Ending the Game.");
                } else if (currentState == TicTacToeGameStates.OWON) {
                    System.out.println("O Won,Ending The Game");
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
        this.currentRow = this.lastRow;
        this.currentColumn = this.lastCol;
        ticTacToeBoard.setStateTicTacToeBoard(this.currentRow,this.currentColumn,currentPlayer);
    }
    private void playerMove() {

        boolean validInput = false;

        do{

            if(currentPlayer == TicTacToeBoardCellStates.CROSS) {
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
                        this.lastCol = this.currentColumn;
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
        return TicTacToeGameRules.hasWon(ticTacToeBoard,currentRow,currentColumn,currentPlayer);
    }

    public boolean isDraw() {
        return TicTacToeGameRules.isDraw(ticTacToeBoard);
    }
}
