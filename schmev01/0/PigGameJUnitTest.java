import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PigGameJUnitTest extends TestCase {

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

	volatile int minRoll = Integer.MAX_VALUE, maxRoll = Integer.MIN_VALUE, minTurnTotal = Integer.MAX_VALUE, maxTurnTotal = Integer.MIN_VALUE;

	@Test(timeout=59000)
	public void test() throws Throwable {
		int games = 0;
		int maxGames = 3;
		int minGames = 1;
		while (games++ < maxGames && (games <= minGames || minRoll > 1 || maxRoll < 6 || minTurnTotal > 0 || maxTurnTotal < 21)) {
			game();
			//			System.out.printf("%d %d %d %d\n", minRoll, maxRoll, minTurnTotal, maxTurnTotal);
		}
		if (minRoll > 1)
			fail(String.format("After %d games, the minimum roll was %d.", maxGames, minRoll));
		if (maxRoll < 6)
			fail(String.format("After %d games, the maximum roll was %d.", maxGames, maxRoll));	
		if (minTurnTotal > 0)
			fail(String.format("After %d games, the minimum turnTotal was %d.", maxGames, minTurnTotal));
		if (maxTurnTotal < 21)
			fail(String.format("After %d games, the maximum turnTotal was %d.", maxGames, maxTurnTotal));	
	}

	@SuppressWarnings("resource")
	public void game() throws Throwable {
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
//						System.err.print("Game");
						BufferedReader testReader = new BufferedReader(new InputStreamReader(testOutput));
						PrintStream testPrint = new PrintStream(testInput);
						int[] scores = new int[3];
						int player = 1;
						String line = testReader.readLine();
						if (!Pattern.matches("You will be player [12].", line))
							fail("The first line of output should have been \"You will be player <#>.\" where <#> is 1 or 2.  Line read: \"" + line + "\"");
						Scanner lineIn = new Scanner(line);
						lineIn.skip("You will be player ");
						int humanPlayer = lineIn.next().charAt(0) - '0';
						assertEquals("Enter nothing to roll; enter anything to hold.", testReader.readLine());
						while (scores[1] < 100 && scores[2] < 100) {
							assertEquals("Player 1 score: " + scores[1], testReader.readLine());
							assertEquals("Player 2 score: " + scores[2], testReader.readLine());
							assertEquals("It is player " + player + "'s turn.", testReader.readLine());
							int turnTotal = 0;
							String prefix = "";
							while (true) {
								line = testReader.readLine();
								if (!Pattern.matches(prefix + "Roll: \\d+", line))
									fail("Expected output line of the form: \"" + prefix + "Roll: <roll value>\"; Read: \"" + line + "\"\nwhere \"\\s+\" means whitespace, and [?] is a pattern match for a single '?' (omit the []).");
								lineIn = new Scanner(line);
								lineIn.skip(".*Roll: ");
								int roll = lineIn.nextInt();
								minRoll = Math.min(minRoll, roll);
								maxRoll = Math.max(maxRoll, roll);
								if (roll < 1 || roll > 6)
									fail("Invalid roll: " + line);
								else if (roll == 1) {
									turnTotal = 0;
									prefix = "";
									break;
								} else {
									turnTotal += roll;
									if (player != humanPlayer) {
										if (turnTotal >= 20 || scores[player] + turnTotal >= 100)
											break;
									}
									else {
										prefix = "Turn total: " + turnTotal + "\\s+Roll/Hold[?] ";
										int i = scores[humanPlayer];
										int j = scores[3 - humanPlayer];
										int k = turnTotal;
										boolean shouldRoll = (i + k < 100) && ((i >= 71 || j >= 71) || (k < 21 + (int) Math.round((j - i) / 8.0)));
										testPrint.println(shouldRoll ? "" : "testing_hold");
										testPrint.flush();
										try {
											Thread.sleep(100);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										if (!shouldRoll)
											break;
									}	
								}
							}
							line = testReader.readLine();
							if (turnTotal == 0)							
								assertEquals("Turn total: 0", line);
							else {
								if (!Pattern.matches(prefix + "Turn total: " + turnTotal, line))
									fail("Expected output line of the form: \"" + prefix + "Turn total: " + turnTotal + "\"; Read: \"" + line + "\"\nwhere \"\\s+\" means whitespace, and [?] is a pattern match for a single '?' (omit the []).");
							}
							scores[player] += turnTotal;
							assertEquals("New score: " + scores[player], testReader.readLine());
							minTurnTotal = Math.min(minTurnTotal, turnTotal);
							maxTurnTotal = Math.max(maxTurnTotal, turnTotal);
//							System.err.print(".");
							player = (player == 1) ? 2 : 1;
						}
					} catch (IOException e) {
						e.printStackTrace(consoleOutput);
					} catch (AssertionError e) {
						exc = e;
						System.err.println(e.getMessage());
					}
				}
			});
			thread.start();
			PigGame.main(new String[] {});
			thread.join(59000);
			if (exc != null)
				throw(exc);
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
