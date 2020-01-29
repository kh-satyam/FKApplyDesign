package games.TicTacToe;

import games.TicTacToe.board.TicTacToeBoard;

public class TicTacToeGameRules {

    public static boolean hasWon4By4(TicTacToeBoard ticTacToeBoard,int currentRow,
                                 int currentColumn,String currentPlayer) {

        return (ticTacToeBoard.getStateTicTacToeBoard(currentRow,0).equals(currentPlayer)         // 3-in-the-row
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,2).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,3).equals(currentPlayer)
                || ticTacToeBoard.getStateTicTacToeBoard(0,currentColumn).equals(currentPlayer)      // 3-in-the-column
                && ticTacToeBoard.getStateTicTacToeBoard(1,currentColumn).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,currentColumn).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(3,currentColumn).equals(currentPlayer)
                || currentRow == currentColumn            // 3-in-the-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(0,0).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(1,1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,2).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(3,3).equals(currentPlayer)
                || currentRow + currentColumn == 2  // 3-in-the-opposite-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(0,3).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(1,1).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(2,1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(3,0).equals(currentPlayer));

    }

    public static boolean hasWon3By3Generic(TicTacToeBoard ticTacToeBoard,int currentRow,
                                            int currentColumn,String currentPlayer) {
        System.out.println("hasWon3By3Generic:"+currentRow + " " + currentColumn);
        int startRow = currentRow/3 * 3;
        int startColumn = currentColumn/3 *3;
        return (ticTacToeBoard.getStateTicTacToeBoard(currentRow,startColumn).equals(currentPlayer)         // 3-in-the-row
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,startColumn+1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(currentRow,startColumn+2).equals(currentPlayer)
                || ticTacToeBoard.getStateTicTacToeBoard(startRow,currentColumn).equals(currentPlayer)      // 3-in-the-column
                && ticTacToeBoard.getStateTicTacToeBoard(startRow+1,currentColumn).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(startRow+2,currentColumn).equals(currentPlayer)
                || currentRow == currentColumn            // 3-in-the-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(startRow,startColumn).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(startRow+1,startColumn+1).equals(currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(startRow+2,startColumn+2).equals(currentPlayer)
                || currentRow + currentColumn == 2  // 3-in-the-opposite-diagonal
                && ticTacToeBoard.getStateTicTacToeBoard(startRow,startColumn+2).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(startRow,startColumn+1).equals( currentPlayer)
                && ticTacToeBoard.getStateTicTacToeBoard(startRow,startColumn).equals(currentPlayer));
    }

    public static  boolean hasWonNByNGeneric(TicTacToeBoard ticTacToeBoard,int currentRow,
                                             int currentColumn,String currentPlayer,
                                             int dimensions) {

        System.out.println("hasWonNByNGeneric called for cell: " + currentRow + " " + currentColumn + " with dimensions: "+ dimensions);
        return
                (hasWonGeneric(ticTacToeBoard,34,-1,currentRow,0,dimensions,
                        currentPlayer) &&
                        hasWonGeneric(ticTacToeBoard,34,-1,currentRow,dimensions,dimensions,
                                currentPlayer)  &&
                        hasWonGeneric(ticTacToeBoard,34,-1,currentRow,dimensions,dimensions,
                                currentPlayer) )||

                        (hasWonGeneric(ticTacToeBoard,-1,-1,0,currentColumn,dimensions,
                                currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,dimensions,currentColumn,dimensions,
                                        currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,dimensions*2,currentColumn,dimensions,
                                        currentPlayer)) ||
                        (currentRow == currentColumn &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,0,0,dimensions,
                                        currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,dimensions,dimensions,dimensions,
                                        currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,2*dimensions,2*dimensions,dimensions,
                                        currentPlayer)) ||
                        (currentRow + currentColumn == 2*dimensions &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,0,2*dimensions,dimensions,
                                        currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,dimensions,dimensions,dimensions,
                                        currentPlayer) &&
                                hasWonGeneric(ticTacToeBoard,-1,-1,2*dimensions,0,dimensions,
                                        currentPlayer))
                 ;

    }

    public static boolean hasWonGeneric(TicTacToeBoard ticTacToeBoard,int gridRows,int gridColumns,
                                        int leftStartGrid,int topStartGrid,int dimensions,
                                        String currentPlayer) {
        boolean result = false;
        if(dimensions==3) {
            for(int row=leftStartGrid;row<leftStartGrid+dimensions;row++) {
                for(int column = topStartGrid;column<topStartGrid+dimensions;column++) {
                    result = result || hasWon3By3Generic(ticTacToeBoard,row,column,currentPlayer);
                }
            }
        }else{
            int newDimensions =dimensions/3;
            for(int row=leftStartGrid*dimensions;row<(leftStartGrid+1)*dimensions;row+=newDimensions){
                for(int column = topStartGrid*dimensions;column<(topStartGrid+1)*dimensions;column+=newDimensions){
                    result = result || hasWonNByNGeneric(ticTacToeBoard,row,column,currentPlayer,newDimensions);
                }
            }
        }
        return result;
    }

    public static boolean isDrawGeneric(TicTacToeBoard ticTacToeBoard) {
        return ticTacToeBoard.noEmptyCellPresent();
    }
}
