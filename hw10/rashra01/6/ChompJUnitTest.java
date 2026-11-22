import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ChompJUnitTest extends TestCase {
	private volatile AssertionError exc; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		exc = null;
	}

	@After
	public void tearDown() throws Exception {
		exc = null;
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	volatile int linesRead = 0;
	ArrayList<String> outputs = new ArrayList<String>();

	@SuppressWarnings("resource")
	@Test(timeout=25000)
	public void test() throws Throwable {
		final InputStream consoleInput = System.in;
		final PrintStream consoleOutput = System.out;
		try {
			final PipedOutputStream testInput = new PipedOutputStream();
			final PipedOutputStream out = new PipedOutputStream();
			final PipedInputStream testOutput = new PipedInputStream(out);
			System.setIn(new PipedInputStream(testInput));
			System.setOut(new PrintStream(out));
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						//						ArrayList<String> inputs = new ArrayList<String>();
						Random random = new Random();
						int rows = random.nextInt(3) + 8;
						int cols = random.nextInt(3) + 8;
						boolean[][] isChomped = new boolean[rows][cols]; 
						PrintStream testPrint = new PrintStream(testInput);
						BufferedReader testReader = new BufferedReader(new InputStreamReader(testOutput));
						assertEquals("CHOMP", testReader.readLine());
						assertEquals("In the game of Chomp, players bite a rectangular cookie in turn.", testReader.readLine());
						assertEquals("The player chomping the last bite (0,0) loses.", testReader.readLine());
						assertEquals("Each chomp, a row and column, bites off all below and to the right.", testReader.readLine());
						testPrint.println(rows);
						testPrint.flush();
						testPrint.println(cols);
						testPrint.flush();
						try { Thread.sleep(100L); } catch (InterruptedException e) { e.printStackTrace(); }
						String prompt = "Rows? Columns? ";
						int numTurns = random.nextInt(1) + 10;
						int player = 1;
						for (int t = 0; t < numTurns; t++) {
//							System.err.println("turn " + t + " prompt[" + prompt + "]");
							assertEquals(prompt, testReader.readLine());
							if (prompt.endsWith(".")) assertEquals("", testReader.readLine());
							assertEquals(" 0123456789".substring(0, cols + 1), testReader.readLine());
							for (int r = 0; r < rows; r++) {
								StringBuilder sb = new StringBuilder();
								sb.append(r);
								for (int c = 0; c < cols; c++)
									sb.append(isChomped[r][c] ? " " : "*");
								assertEquals(sb.toString(), testReader.readLine());
//								System.err.println(sb.toString());
							}
							assertEquals("", testReader.readLine());
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
							prompt = String.format("Player %d chomp? ", player);
							testPrint.printf("%d %d\n", chompRow, chompCol);
//							System.err.printf("%d %d\n", chompRow, chompCol);
							testPrint.flush();
							try { Thread.sleep(100L); } catch (InterruptedException e) { e.printStackTrace(); }
							if (chompRow < 0 || chompCol < 0 || chompRow >= rows || chompCol >= cols) 
								prompt += "That is not a legal chomp position.";
							else if (isChomped[chompRow][chompCol])
								prompt += "That is not a legal chomp position.";
							else {
								for (int r = chompRow; r < rows; r++)
									for (int c = chompCol; c < cols; c++)
										isChomped[r][c] = true;
								player = (player == 1) ? 2 : 1;
							}
						}
						assertEquals(prompt, testReader.readLine());
						assertEquals(String.format("Player %d wins!", player), testReader.readLine());
					} catch (IOException e) {
						e.printStackTrace(consoleOutput);
					} catch (AssertionError e) {
						exc = e;
						System.err.println(e.getMessage());
					}
				}
			});
			thread.start();
			Chomp.main(new String[] {});
			thread.join(20000);
			if (exc != null)
				throw(exc);
			if (linesRead < outputs.size()) 
				fail("Only " + linesRead + " of the " + outputs.size() + " required lines are printed.");
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (InterruptedException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			throw(exc != null ? exc : e);
		}
		System.setIn(consoleInput);
		System.setOut(consoleOutput);
	}
}
