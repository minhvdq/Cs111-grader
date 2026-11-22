
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

	public Maze(int height, int width) {
		this.rows = height;
		this.cols = width;

		grid = new int[height][width];
		openRight = new boolean[height][width - 1];
		openDown = new boolean[height - 1][width];

		makeMaze();
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public int getGrid(int y, int x) {
		return grid[y][x];
	}

	public boolean getOpenRight(int y, int x) {
		return openRight[y][x];
	}

	public void setOpenRight(int y, int x, boolean status) {
		openRight[y][x] = status;
	}

	public boolean getOpenDown(int y, int x) {
		return openDown[y][x];
	}

	public void setOpenDown(int y, int x, boolean status) {
		openDown[y][x] = status;
	}

	private int[] getShuffledDirs() {
		int[] directions = DIRECTIONS.clone();

		for (int index = directions.length - 1; index > 0; index--) {
			int randomIndex = (int) (Math.random() * (index + 1));

			int temporary = directions[index];
			directions[index] = directions[randomIndex];
			directions[randomIndex] = temporary;
		}

		return directions;
	}

	private boolean isUnreached(int y, int x, int direction) {
		int nextRow = y + ROW_CHANGE[direction];
		int nextCol = x + COL_CHANGE[direction];

		return nextRow >= 0 && nextRow < rows
				&& nextCol >= 0 && nextCol < cols
				&& grid[nextRow][nextCol] == UNREACHED;
	}

	private void openWall(int y, int x, int direction) {
		int nextRow = y + ROW_CHANGE[direction];
		int nextCol = x + COL_CHANGE[direction];

		if (y == nextRow) {
			openRight[y][Math.min(x, nextCol)] = true;
		} else {
			openDown[Math.min(y, nextRow)][x] = true;
		}
	}

	private void makeMaze() {
		makeMaze(0, 0);
	}

	private void makeMaze(int y, int x) {
		grid[y][x] = REACHED;

		for (int direction : getShuffledDirs()) {
			if (isUnreached(y, x, direction)) {
				openWall(y, x, direction);

				makeMaze(
						y + ROW_CHANGE[direction],
						x + COL_CHANGE[direction]
						);
			}
		}
	}

	public boolean canTravel(int y, int x, int direction) {
		int nextRow = y + ROW_CHANGE[direction];
		int nextCol = x + COL_CHANGE[direction];

		return nextRow >= 0 && nextRow < rows
				&& nextCol >= 0 && nextCol < cols
				&& (
						(y == nextRow &&
						openRight[y][Math.min(x, nextCol)])
						||
						(x == nextCol &&
						openDown[Math.min(y, nextRow)][x])
						);
	}
	public void solveMaze() {
		solveMaze(0, 0);
	}

	private boolean solveMaze(int y, int x) {
		grid[y][x] = SOLUTION_PATH;

		if (y == rows - 1 && x == cols - 1) {
			return true;
		}

		for (int direction : DIRECTIONS) {
			int nextRow = y + ROW_CHANGE[direction];
			int nextCol = x + COL_CHANGE[direction];

			if (canTravel(y, x, direction)
					&& grid[nextRow][nextCol] != SOLUTION_PATH) {

				if (solveMaze(nextRow, nextCol)) {
					return true;
				}
			}
		}

		grid[y][x] = REACHED;
		return false;
	}

	public String toString() {
		char[] symbols = {'#', ' ', '.'};

		int[][] output = new int[2 * rows + 1][2 * cols + 1];

		for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
			for (int colIndex = 0; colIndex < cols; colIndex++) {

				int centerRow = 2 * rowIndex + 1;
				int centerCol = 2 * colIndex + 1;

				output[centerRow][centerCol] =
						grid[rowIndex][colIndex];

				if (canTravel(rowIndex, colIndex, RIGHT)) {
					output[centerRow][centerCol + 1] =
							(grid[rowIndex][colIndex] ==
							grid[rowIndex][colIndex + 1])
							? grid[rowIndex][colIndex] : REACHED;
				}

				if (canTravel(rowIndex, colIndex, DOWN)) {
					output[centerRow + 1][centerCol] =
							(grid[rowIndex][colIndex] ==
							grid[rowIndex + 1][colIndex])
							? grid[rowIndex][colIndex] : REACHED;
				}
			}
		}

		output[0][1] = output[1][1];

		output[2 * rows][2 * cols - 1] =
				output[2 * rows - 1][2 * cols - 1];

		StringBuffer result = new StringBuffer();

		for (int[] line : output) {
			for (int value : line) {
				result.append(symbols[value]);
			}
			result.append('\n');
		}

		return result.toString();
	}

	public void exportImage(String filename) {
		int height = 2 * rows + 1;
		int width = 2 * cols + 1;

		BufferedImage image = new BufferedImage(
				width, height, BufferedImage.TYPE_INT_RGB
				);

		Graphics2D graphics = image.createGraphics();

		graphics.setColor(Color.white);
		graphics.fill(new Rectangle(0, 0, width, height));

		Scanner reader = new Scanner(toString());

		for (int rowIndex = 0; rowIndex < height; rowIndex++) {
			String line = reader.nextLine();

			for (int colIndex = 0; colIndex < width; colIndex++) {
				char symbol = line.charAt(colIndex);

				if (symbol == '#') {
					image.setRGB(
							colIndex, rowIndex, Color.BLACK.getRGB()
							);
				} else if (symbol == '.') {
					image.setRGB(
							colIndex, rowIndex, Color.GREEN.getRGB()
							);
				}
			}
		}

		reader.close();
		graphics.dispose();

		File outputFile = new File(filename + ".png");

		try {
			ImageIO.write(image, "png", outputFile);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public static void main(String[] arguments) {
		Scanner keyboard = new Scanner(System.in);

		System.out.print("Maze rows? ");
		int height = keyboard.nextInt();

		System.out.print("Maze columns? ");
		int width = keyboard.nextInt();

		keyboard.close();

		Maze generatedMaze = new Maze(height, width);

		System.out.println(generatedMaze);
		System.out.println();

		generatedMaze.exportImage("maze");

		generatedMaze.solveMaze();

		System.out.println("Solution:");
		System.out.println(generatedMaze);

		generatedMaze.exportImage("maze-solution");
	}
}
