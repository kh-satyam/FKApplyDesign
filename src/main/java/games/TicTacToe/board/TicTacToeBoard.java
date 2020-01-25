package games.TicTacToe.board;

import games.TicTacToe.states.TicTacToeBoardCellStates;

import java.util.Optional;

public class TicTacToeBoard {

    TicTacToeBoardCell[][] board;

    public TicTacToeBoard(int rows,int cols) {

        board = new TicTacToeBoardCell[rows][cols];

        for(int row = 0;row<rows;row++){
            for(int column = 0;column<cols;column++) {
                board[row][column] = new TicTacToeBoardCell(row,column, TicTacToeBoardCellStates.EMPTY);
            }
        }
    }

    public void printBoard() {
        int ROWS = board.length;
        int COLUMNS = board[0].length;

        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                TicTacToeBoardCell ticTacToeBoardCell = board[row][column]; // print each of the cells
                ticTacToeBoardCell.printBoardCell();
                if (column != COLUMNS- 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != ROWS-1) {
                System.out.println("-----------"); // print horizontal partition
            }
        }
        System.out.println();
    }

    public boolean isValidMove(int row,int column) {
        if(row>=0 && row<board.length && column>=0 && column < board[0].length && board[row][column].getState().equals(TicTacToeBoardCellStates.EMPTY)){
            return true;
        }
        return false;
    }

    public void setStateTicTacToeBoard(int row,int column,String state) {
        board[row][column].setState(state);
    }

    public String getStateTicTacToeBoard(int row,int column) {
        return board[row][column].getState();
    }

    public boolean noEmptyCellPresent() {
        for(int row = 0;row < board.length;row++) {
            for(int col = 0; col<board[0].length ;col++){
                if(board[row][col].getState().equals(TicTacToeBoardCellStates.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Optional<TicTacToeBoardCell> getRandomBoardCell() {
        Optional<TicTacToeBoardCell> optionalTicTacToeBoardCell = Optional.empty();
        for(int row = 0;row < board.length;row++) {
            for(int column = 0;column < board[0].length;column++) {
                if(board[row][column].getState().equals(TicTacToeBoardCellStates.EMPTY)){
                    TicTacToeBoardCell ticTacToeBoardCell = board[row][column];
                    optionalTicTacToeBoardCell = Optional.of(ticTacToeBoardCell);
                }
            }
        }
        return optionalTicTacToeBoardCell;
    }
}
