package games.TicTacToe.gameModes;

import games.TicTacToe.TicTacToeGameRules;
import games.TicTacToe.board.TicTacToeBoard;
import games.TicTacToe.board.TicTacToeBoardCell;
import games.TicTacToe.gameModes.TicTacToeGameModes;
import games.TicTacToe.states.TicTacToeBoardCellStates;
import games.TicTacToe.states.TicTacToeGameStates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class TicTacToeGameModeVComputer implements TicTacToeGameModes {

    private TicTacToeBoard ticTacToeBoard;
    private int currentState;
    private String currentPlayer;
    private String humanPlayer;
    private int currentRow;
    private int currentColumn;

    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;


    public TicTacToeGameModeVComputer(TicTacToeBoard ticTacToeBoard,boolean goFirst,String humanPlayer) {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        this.ticTacToeBoard = ticTacToeBoard;
        this.currentState = TicTacToeGameStates.PLAYING;
        if(goFirst) {
            currentPlayer = humanPlayer;
        }else{
            currentPlayer = humanPlayer.equals(TicTacToeBoardCellStates.CIRCLE)?TicTacToeBoardCellStates.CROSS:
                        TicTacToeBoardCellStates.CIRCLE;
        }
        this.humanPlayer = humanPlayer;
    }

    public void playGame(){
        ticTacToeBoard.printBoard();
        System.out.println();

        while(currentState == TicTacToeGameStates.PLAYING) {
            playerMove();
            updateGame();
            System.out.println("Updated Board");
            ticTacToeBoard.printBoard();
            if(currentState==TicTacToeGameStates.CROSSWON) {
                if(currentPlayer.equals(humanPlayer))
                    System.out.println("You Won,Ending the Game.");
                else
                    System.out.println("Computer won the game");
            }else if(currentState==TicTacToeGameStates.OWON){
                if(currentPlayer.equals(humanPlayer))
                    System.out.println("You Won,Ending the Game.");
                else
                    System.out.println("Computer won the game");
            }else if(currentState==TicTacToeGameStates.DRAW){
                System.out.println("Game Ended in a Draw.");
            }

            currentPlayer = (currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE)) ? TicTacToeBoardCellStates.CROSS:
                    TicTacToeBoardCellStates.CIRCLE;
        }
    }

    private void playerMove() {

        boolean validInput = false;

        if(currentPlayer.equals(humanPlayer)) {
            System.out.println("Player:" + humanPlayer + ",Enter your move:row and column");
            do{
                try{

                    String[] move = bufferedReader.readLine().split(" ");
                    if(move.length==2) {
                        int row = Integer.valueOf(move[0]);
                        int column = Integer.valueOf(move[1]);
                        if(ticTacToeBoard.isValidMove(row,column)) {
                            validInput = true;
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
        return TicTacToeGameRules.hasWon(ticTacToeBoard,currentRow,currentColumn,currentPlayer);
    }

    public boolean isDraw() {
        return TicTacToeGameRules.isDraw(ticTacToeBoard);
    }
}
