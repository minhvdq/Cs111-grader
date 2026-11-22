import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
public class Maze {
	public static final int UNREACHED = 0;
	public static final int REACHED = 1;
	public static final int SOLUTION_PATH = 2;
	public static final int RIGHT = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static final int[] DIRECTIONS = {
			RIGHT, UP, LEFT, DOWN
	};
	public static final int[] ROW_CHANGE = {
			0, -1, 0, 1
	};
	public static final int[] COL_CHANGE = {
			1, 0, -1, 0
	};
	private int rows, cols;
	private int[][] grid;
	private boolean[][] openRight, openDown;
	public Maze(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new int[rows][cols];
		openRight = new boolean[rows][cols - 1];
		openDown = new boolean[rows - 1][cols];
		makeMaze();
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
	public int getGrid(int row, int col) {
		return grid[row][col];
	}
	public boolean getOpenRight(int row, int col) {
		return openRight[row][col];
	}
	public void setOpenRight(int row, int col, boolean isOpen) {
		openRight[row][col] = isOpen;
	}
	public boolean getOpenDown(int row, int col) {
		return openDown[row][col];
	}
	public void setOpenDown(int row, int col, boolean isOpen) {
		openDown[row][col] = isOpen;
	}
	private int[] getShuffledDirs() {
		int[] dirs = DIRECTIONS.clone();
		for (int i = dirs.length - 1; i > 0; i--) {
			int j = (int) (Math.random() * (i + 1));
			int tmp = dirs[i];
			dirs[i] = dirs[j];
			dirs[j] = tmp;
		}
		return dirs;
	}
	private boolean isUnreached(int row, int col, int dir) {
		int row2 = row + ROW_CHANGE[dir];
		int col2 = col + COL_CHANGE[dir];
		return (row2 >= 0 && row2 < rows &&
				col2 >= 0 && col2 < cols &&
				grid[row2][col2] == UNREACHED);
	}
	private void openWall(int row, int col, int dir) {
		int row2 = row + ROW_CHANGE[dir];
		int col2 = col + COL_CHANGE[dir];
		if (row == row2)
			openRight[row][col < col2 ? col : col2] = true;
		else
			openDown[row < row2 ? row : row2][col] = true;
	}
	// Recursively create a maze
	private void makeMaze() {
		makeMaze(0, 0);
	}
	private void makeMaze(int row, int col) {
		grid[row][col] = REACHED;
		for (int dir : getShuffledDirs()) {
			if (isUnreached(row, col, dir)) {
				openWall(row, col, dir);
				makeMaze(row + ROW_CHANGE[dir],
						col + COL_CHANGE[dir]);
			}
		}
	}
	public boolean canTravel(int row, int col, int dir) {
		int row2 = row + ROW_CHANGE[dir];
		int col2 = col + COL_CHANGE[dir];
		return (row2 >= 0 && row2 < rows &&
				col2 >= 0 && col2 < cols &&
				((row == row2 &&
				openRight[row][col < col2 ? col : col2])
						||
						(col == col2 &&
						openDown[row < row2 ? row : row2][col])));
	}
	// Recursively solve the maze
	public void solveMaze() {
		solveMaze(0, 0);
	}
	private boolean solveMaze(int row, int col) {
		grid[row][col] = SOLUTION_PATH;
		if (row == rows - 1 && col == cols - 1)
			return true;
		for (int dir : DIRECTIONS) {
			int row2 = row + ROW_CHANGE[dir];
			int col2 = col + COL_CHANGE[dir];
			if (canTravel(row, col, dir) &&
					grid[row2][col2] != SOLUTION_PATH) {
				if (solveMaze(row2, col2))
					return true;
			}
		}
		grid[row][col] = REACHED;
		return false;
	}
	public String toString() {
		char[] gridChars = {'#', ' ', '.'};
		int[][] outGrid = new int[2 * rows + 1][2 * cols + 1];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int r2 = 2 * r + 1;
				int c2 = 2 * c + 1;
				outGrid[r2][c2] = grid[r][c];
				if (canTravel(r, c, RIGHT))
					outGrid[r2][c2 + 1] =
					(grid[r][c] == grid[r][c + 1])
					? grid[r][c] : REACHED;
				if (canTravel(r, c, DOWN))
					outGrid[r2 + 1][c2] =
					(grid[r][c] == grid[r + 1][c])
					? grid[r][c] : REACHED;
			}
		}
		outGrid[0][1] = outGrid[1][1];
		outGrid[2 * rows][2 * cols - 1] =
				outGrid[2 * rows - 1][2 * cols - 1];
		StringBuffer sb = new StringBuffer();
		for (int r = 0; r < outGrid.length; r++) {
			for (int c = 0; c < outGrid[r].length; c++)
				sb.append(gridChars[outGrid[r][c]]);
			sb.append(' ');
		}
		return sb.toString();
	}
	public void exportImage(String filename) {
		int imageRows = 2 * rows + 1;
		int imageCols = 2 * cols + 1;
		BufferedImage bi = new BufferedImage(
				imageCols, imageRows, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.white);
		g.fill(new Rectangle(0, 0, imageCols, imageRows));
		Scanner mazeIn = new Scanner(toString());
		for (int r = 0; r < imageRows; r++) {
			String row = mazeIn.nextLine();
			for (int c = 0; c < imageCols; c++) {
				char ch = row.charAt(c);
				if (ch == '#')
					bi.setRGB(c, r, Color.BLACK.getRGB());
				else if (ch == '.')
					bi.setRGB(c, r, Color.GREEN.getRGB());
			}
		}
		mazeIn.close();
		g.dispose();
		File f = new File(filename + ".png");
		try {
			ImageIO.write(bi, "png", f);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Maze rows? ");
		int rows = in.nextInt();
		System.out.print("Maze columns? ");
		int cols = in.nextInt();
		in.close();
		Maze maze = new Maze(rows, cols);
		System.out.println(maze);
		System.out.println();
		maze.exportImage("maze");
		maze.solveMaze();
		System.out.println("Solution:");
		System.out.println(maze);
		maze.exportImage("maze-solution");
	}
}