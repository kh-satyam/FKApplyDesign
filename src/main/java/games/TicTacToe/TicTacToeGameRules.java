package games.TicTacToe;

import games.TicTacToe.board.EnhancedTicTacToeBoard;
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

    public static boolean hasWon3By3(TicTacToeBoard ticTacToeBoard,String currentPlayer) {
        boolean hasWon = false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                hasWon = hasWon || hasWon(ticTacToeBoard,i,j,currentPlayer);
            }
        }
        return hasWon;
    }

    public static boolean hasWon9By9(EnhancedTicTacToeBoard enhancedTicTacToeBoard,int boardRow,int boardColumn
                ,String currentPlayer) {

        return (
                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[boardRow][0],currentPlayer) &&
                        hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[boardRow][1],currentPlayer) &&
                        hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[boardRow][2],currentPlayer)
                )
                ||
                (
                        hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[0][boardColumn],currentPlayer) &&
                        hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[1][boardColumn],currentPlayer) &&
                        hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[2][boardColumn],currentPlayer)
                )
                ||
                (
                        boardRow==boardColumn &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[0][0],currentPlayer) &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[1][1],currentPlayer) &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[2][2],currentPlayer)
                        )
                ||
                (
                        boardRow+boardColumn==2 &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[0][2],currentPlayer) &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[1][1],currentPlayer) &&
                                hasWon3By3(enhancedTicTacToeBoard.getTicTacToeBoard()[2][0],currentPlayer)
                        );
    }

    public static boolean isDraw9By9(EnhancedTicTacToeBoard enhancedTicTacToeBoard) {

        boolean isDraw = false;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++){
                isDraw = isDraw || TicTacToeGameRules.isDraw(enhancedTicTacToeBoard.getTicTacToeBoard()[i][j]);
            }
        }
        return isDraw;
    }
}
