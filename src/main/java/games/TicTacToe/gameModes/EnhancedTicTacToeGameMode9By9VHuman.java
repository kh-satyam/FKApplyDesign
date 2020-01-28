package games.TicTacToe.gameModes;

import games.TicTacToe.TicTacToeGameRules;
import games.TicTacToe.board.EnhancedTicTacToeBoard;
import games.TicTacToe.states.TicTacToeBoardCellStates;
import games.TicTacToe.states.TicTacToeGameStates;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EnhancedTicTacToeGameMode9By9VHuman implements TicTacToeGameModes {

    private EnhancedTicTacToeBoard enhancedTicTacToeBoard;
    private int currentState;
    private int currentBoardRow;
    private int currentBoardColumn;
    private int currentCellRow;
    private int currentCellColumn;
    private String currentPlayer;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public EnhancedTicTacToeGameMode9By9VHuman(int numRows,int numColumns,int startingPlayer) {

        this.enhancedTicTacToeBoard = new EnhancedTicTacToeBoard(numRows,numColumns);
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);

        this.currentState = TicTacToeGameStates.PLAYING;

        if(startingPlayer==1) {
            currentPlayer = TicTacToeBoardCellStates.CROSS;
        }else{
            currentPlayer = TicTacToeBoardCellStates.CIRCLE;
        }
    }

    @Override
    public void playGame() {
        System.out.println("Welcome to EnhancedTicTacToe GameMode3");
        System.out.println();
        this.enhancedTicTacToeBoard.printBoard();
        System.out.println();

        while (currentState == TicTacToeGameStates.PLAYING) {

            playerMove();
            updateGame();
            enhancedTicTacToeBoard.printBoard();
            if(currentState==TicTacToeGameStates.CROSSWON) {
                System.out.println("X Won,Ending the Game.");
            }else if(currentState==TicTacToeGameStates.OWON){
                System.out.println("O Won,Ending The Game");
            }else if(currentState==TicTacToeGameStates.DRAW){
                System.out.println("Game Ended in a Draw.");
            }

            currentPlayer = (currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE)) ? TicTacToeBoardCellStates.CROSS:
                    TicTacToeBoardCellStates.CIRCLE;

        }
    }

    public void playerMove() {

        boolean validInput = false;
        do{

            if(currentPlayer == TicTacToeBoardCellStates.CROSS) {
                System.out.println("Player X: Enter your move: boardRow,boardColumn,cellRow and cellColumn");
            }else{
                System.out.println("Player O: Enter your move: boardRow,boardColumn,cellRow and cellColumn");
            }
            try{

                String[] move = bufferedReader.readLine().split(" ");
                if(move.length==4) {
                    int boardRow = Integer.valueOf(move[0]);
                    int boardColumn = Integer.valueOf(move[1]);
                    int cellRow = Integer.valueOf(move[2]);
                    int cellColumn = Integer.valueOf(move[3]);
                    if(enhancedTicTacToeBoard.isValidMove9By9(boardRow,boardColumn,cellRow,cellColumn)) {
                        validInput = true;
                        this.currentBoardRow = boardRow;
                        this.currentBoardColumn = boardColumn;
                        this.currentCellRow = cellRow;
                        this.currentCellColumn = cellColumn;
                        enhancedTicTacToeBoard.setStateEnhancedTicTacToeBoard(currentBoardRow,currentBoardColumn,currentCellRow,
                                currentCellColumn,currentPlayer);
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
            currentState = currentPlayer.equals(TicTacToeBoardCellStates.CIRCLE) ? TicTacToeGameStates.OWON : TicTacToeGameStates.CROSSWON;
        }
        else if(isDraw()) {
            currentState = TicTacToeGameStates.DRAW;
        }
    }

    public boolean hasWon(){
        return TicTacToeGameRules.hasWon9By9(enhancedTicTacToeBoard,currentBoardRow,currentBoardColumn,currentPlayer);
    }

    public boolean isDraw() {
        return TicTacToeGameRules.isDraw9By9(enhancedTicTacToeBoard);
    }
}
