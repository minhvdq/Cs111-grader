
public class ChompGame {
	
	private int rows;
	private int cols;
	private int current;
    private boolean[][] list;
    
    public ChompGame (int rows, int cols) {
    	this.rows =rows;
    	this.cols = cols;
    	this.current =1;
    	this.list = new boolean[rows][cols];
    }
    
    public boolean Chomped(int row, int col) {
        return list[row][col];
    }
    

    public boolean True(int row, int col) {
        return row >= 0 && row < rows
                && col >= 0 && col < cols
                && !list[row][col];
    }

    public boolean chompAt(int row, int col) {

        if (!True(row, col)) {
            return false;
        }
        for (int i = row; i < rows; i++) {
            for (int j = col; j < cols; j++) {
                list[i][j] = true;
            }
        }
        if (current == 1) {
            current = 2;
        } else {
            current = 1;
        }

        return true;
    }

    public boolean Over() {
        return list[0][0];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getCurrent() {
        return current;
    }

    public String toString() {
        String result = " ";

        for (int j = 0; j < cols; j++) {
            result += j;
        }
        result += "\n";

        for (int i = 0; i < rows; i++) {
            result += i;
            for (int j = 0; j < cols; j++) {
                if (list[i][j]) {
                    result += " ";
                } else {
                    result += "*";
                }
            }
            result += "\n";
        }
        return result;
    }

	

}
