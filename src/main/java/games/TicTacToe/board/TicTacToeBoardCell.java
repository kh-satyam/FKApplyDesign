package games.TicTacToe.board;


public class TicTacToeBoardCell {

    private String state;
    private int row;
    private int column;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TicTacToeBoardCell(int row, int column, String state) {
        this.state = state;
        this.row=row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void printBoardCell () {
        System.out.print(row + " "+ column + " "+ state);
    }
}
