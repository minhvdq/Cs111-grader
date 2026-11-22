import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;

public class ChompGameJUnitTest extends TestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test(timeout=5000)
	public void test() throws Throwable {
		for (int i = 0; i < 3; i++)
			gameTest();
	}

	public void gameTest() {
		Random random = new Random();
		int rows = random.nextInt(3) + 8;
		int cols = random.nextInt(3) + 8;
		ChompGame game = new ChompGame(rows, cols);
		if (game.getRows() != rows)
			fail("Chomp getRows() returns " + game.getRows() + " rather than " + rows + " passed to the constructor.");
		if (game.getCols() != cols)
			fail("Chomp getCols() returns " + game.getCols() + " rather than " + cols + " passed to the constructor.");
		boolean[][] isChomped = new boolean[rows][cols]; 
		int numTurns = random.nextInt(2) + 10;
		int player = 1;
		for (int t = 0; t < numTurns; t++) {
//			System.err.println("turn " + t);
			int retPlayer = game.getCurrentPlayer();
			if (player != retPlayer)
				fail(String.format("Expected getCurrentPlayer() value %d, but instead had returned value %d", player, retPlayer));
			boolean isGameOver = game.isGameOver();
			if (isGameOver)
				fail("Chomp isGameOver() returns true when game is not over.");
			String gameStr = game.toString();
			StringBuilder sb = new StringBuilder(" 0123456789".substring(0, cols + 1) + "\n");
			for (int r = 0; r < rows; r++) {
				sb.append(r);
				for (int c = 0; c < cols; c++)
					sb.append(isChomped[r][c] ? " " : "*");
				sb.append("\n");
			}
			String correctGameStr = sb.toString();
//			System.err.println(correctGameStr);
			if (!gameStr.equals(correctGameStr))
				fail(String.format("Expected toString() value [%s], but instead had returned value [%s]", correctGameStr, gameStr));
			int chompRow = 0, chompCol = 0;
			while (t < numTurns - 1 && chompRow == 0 && chompCol == 0) {
				chompRow = random.nextInt(rows);
				chompCol = random.nextInt(cols);
			}
			if (t == 0)
				chompRow = -1;
			else if (t == 1)
				chompRow = rows;
			else if (t == 2)
				chompCol = -1;
			else if (t == 3)
				chompCol = cols;
			else if (t == 4 || t == 8) {
				chompRow = rows / 2 + random.nextInt(rows / 2);
				chompCol = cols / 2 + random.nextInt(cols / 2);
			}
			else if (t == 5) {
				chompRow = 1+ random.nextInt(rows / 2);
				chompCol = cols / 2 + random.nextInt(cols / 2);
			}
			else if (t == 6) {
				chompRow = rows / 2 + random.nextInt(rows / 2);
				chompCol = 1 + random.nextInt(cols / 2);
			}
			else if (t == 7) {
				chompRow = 1 + random.nextInt(rows / 2);
				chompCol = 1 + random.nextInt(cols / 2);
			}
			boolean isLegal = true;
			if (chompRow < 0 || chompCol < 0 || chompRow >= rows || chompCol >= cols) 
				isLegal = false;
			else if (isChomped[chompRow][chompCol])
				isLegal = false;
			else {
				for (int r = chompRow; r < rows; r++)
					for (int c = chompCol; c < cols; c++)
						isChomped[r][c] = true;
				player = (player == 1) ? 2 : 1;
			}
			boolean moveLegal = game.isLegalChomp(chompRow, chompCol);
			if (moveLegal != isLegal) 
				fail(String.format("Chomp isLegalChomp(%d, %d) returns %b but should return %b.", chompRow, chompCol, moveLegal, isLegal));
			moveLegal = game.chompAt(chompRow, chompCol);
			if (moveLegal != isLegal) 
				fail(String.format("Chomp chompAt(%d, %d) returns %b but should return %b.", chompRow, chompCol, moveLegal, isLegal));
			for (int r = 0; r < rows; r++)
				for (int c = 0; c < cols; c++)
					if (isChomped[r][c] != game.checkIfChomped(r, c))
						fail(String.format("Chomp checkIfChomped(%d, %d) returns %b but should return %b.", r, c, game.checkIfChomped(r, c), isChomped[r][c]));
		}
		boolean isGameOver = game.isGameOver();
		if (!isGameOver)
			fail("Chomp isGameOver() returns false when game is over.");
		int retPlayer = game.getCurrentPlayer();
		if (player != retPlayer)
			fail(String.format("After game, expected getCurrentPlayer() value %d, but instead had returned value %d", player, retPlayer));

	}
}
