package games.TicTacToe;

import games.TicTacToe.board.TicTacToeBoard;

public class TicTacToeGameRules {

    public static boolean hasWon(TicTacToeBoard ticTacToeBoard,int currentRow,
                                 int currentColumn,String currentPlayer) {

        return (ticTacToeBoard.getStateTicTacToeBoard(currentRow,0).equals(currentPlayer)         // 3-in-the-row
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,2).equals(currentPlayer)
                || ticTacToeBoard.getStateTicTacToeBoard(0,currentColumn).equals(currentPlayer)      // 3-in-the-column
                && ticTacToeBoard.getStateTicTacToeBoard(1,currentColumn).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,currentColumn).equals(currentPlayer)
                || currentRow == currentColumn            // 3-in-the-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(0,0).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(1,1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,2).equals(currentPlayer)
                || currentRow + currentColumn == 2  // 3-in-the-opposite-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(0,2).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(1,1).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,0).equals(currentPlayer));

    }

    public static boolean isDraw(TicTacToeBoard ticTacToeBoard) {
        return ticTacToeBoard.noEmptyCellPresent();
    }
}
