package games.TicTacToe.board;

public class EnhancedTicTacToeBoard {

    private TicTacToeBoard[][] ticTacToeBoards;

    public EnhancedTicTacToeBoard(int numRows,int numColumns) {

        ticTacToeBoards = new TicTacToeBoard[numRows][numColumns];
        for(int row = 0;row<numRows;row++){
            for(int col = 0;col<numColumns;col++) {
                ticTacToeBoards[row][col] = new TicTacToeBoard(3,3);
            }
        }
    }

    public TicTacToeBoard[][] getTicTacToeBoard() {
        return ticTacToeBoards;
    }

    public void setStateEnhancedTicTacToeBoard(int boardRow,int boardColumn,int cellRow,int cellColumn,String state) {
        TicTacToeBoard ticTacToeBoard = ticTacToeBoards[boardRow][boardColumn];
        ticTacToeBoard.setStateTicTacToeBoard(cellRow,cellColumn,state);
        ticTacToeBoard.printBoard();
    }

    public void printBoard() {
        int ROWS = ticTacToeBoards.length;
        int COLUMNS = ticTacToeBoards[0].length;
        System.out.println(ROWS);
        System.out.println(COLUMNS);
        int currBoardRow;
        int currBoardColumn;

        for(int row = 0;row<ROWS;row++){
            currBoardRow = row;
            for(int i=0;i<3;i++){
                for(int col=0;col<COLUMNS;col++){
                    currBoardColumn = col;
                    TicTacToeBoard ticTacToeBoard = ticTacToeBoards[row][col];
                    ticTacToeBoard.printRow(currBoardRow,currBoardColumn,i);
                    if(col!=COLUMNS-1) System.out.print("  ");
                }
                System.out.println();
            }
            System.out.println();
        }

        System.out.println();
    }

    public boolean isValidMove9By9(int boardRow,int boardColumn,int cellRow,int cellColumn) {
        boolean isValid = true;
        if(boardRow >= 0 && boardRow< ticTacToeBoards.length && boardColumn>=0 && ticTacToeBoards[0].length<boardColumn
            && cellRow>=0 && cellColumn>=0 && cellRow<3 && cellColumn<3){
            isValid = true;
        }
        return isValid;
    }

    public EnhancedTicTacToeBoard getPartialBoard(EnhancedTicTacToeBoard enhancedTicTacToeBoard,int dimensions,int startRow,int startCol) {
        EnhancedTicTacToeBoard newEnhancedTicTacToeBoard1 = new EnhancedTicTacToeBoard(dimensions,dimensions);
        for(int i=0;i<dimensions;i++){
            for(int j=0;j<dimensions;j++){
                newEnhancedTicTacToeBoard1.getTicTacToeBoard()[i][j].copyTicTacBoard(
                        enhancedTicTacToeBoard.getTicTacToeBoard()[startRow+dimensions][startCol+dimensions]
                );
            }
        }
        return newEnhancedTicTacToeBoard1;
    }
}
