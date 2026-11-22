import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiceHistogramJUnitTest extends TestCase {
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
	
	volatile int linesRead = 0;
	ArrayList<String> outputs = new ArrayList<String>();
	
	@SuppressWarnings("resource")
	@Test(timeout=10000)
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
						PrintStream testPrint = new PrintStream(testInput);
						BufferedReader testReader = new BufferedReader(new InputStreamReader(testOutput));
						testPrint.println(10000);
						testPrint.flush();
						String line = testReader.readLine();
						String prefix = "How many rolls? Max count: ";
						if (line.length() <= prefix.length())
							fail(String.format("First output line too short.  Expecting prefix: \"%s\"", prefix));
						if (!line.substring(0, prefix.length()).equals(prefix))
							fail(String.format("First line prefix incorrect.  Expecting prefix: \"%s\"  Read: \"%s\"", prefix, line.substring(0, prefix.length())));
						try {
							int maxCount = Integer.parseInt(line.substring(prefix.length()));
							if (maxCount < 1489 || maxCount > 1863)
								fail("Max count is outside reasonable bounds.");
							if (maxCount == 1667)
								System.err.println("WARNING: Max count is improbably equal to the average expected value.  CHECK CODE.");
						}
						catch (NumberFormatException nfe) {
							fail("Expected max count integer, read: \"" + line.substring(prefix.length()) + "\"");
						}
						int[] stars = new int[13];
						for (int i = 2; i <= 12; i++) {
							prefix = String.format("%2d ", i);
							line = testReader.readLine();
							if (line.length() <= prefix.length())
								fail(String.format("Output line too short.  Expecting prefix: \"%s\"", prefix));
							if (!line.substring(0, prefix.length()).equals(prefix))
								fail(String.format("Line prefix incorrect.  Use format string \"%%2d \".  Expecting prefix: \"%s\"  Read: \"%s\"", prefix, line.substring(0, prefix.length())));
							String restOfLine = line.substring(prefix.length());
							for (int j = 0; j < restOfLine.length(); j++)
								if (restOfLine.charAt(j) != '*')
									fail(String.format("Non-asterisk character '%s' after prefix: \"%s\"", restOfLine.substring(j, j + 1), prefix));
							stars[i] = restOfLine.length();
						}
						boolean symmetric = true;
						for (int i = 2; i <= 7; i++)
							if (stars[i] != stars[14 - i])
								symmetric = false;
						if (symmetric) 
							fail("Asterisk histogram data is highly unlikely given the input number of rolls.");
						int[] minStars = {0, 0, 6, 15, 24, 33, 42, 60, 42, 33, 24, 15, 6}, maxStars = {0, 0, 12, 25, 36, 47, 58, 60, 58, 47, 36, 25, 12};
						for (int i = 2; i <= 12; i++) 
							if (stars[i] < minStars[i] || stars[i] > maxStars[i])
								fail("Number of asterisks for roll sum " + i + " is outside probable bounds.");
//						System.err.println(Arrays.toString(stars));
					} catch (IOException e) {
						e.printStackTrace(consoleOutput);
					} catch (AssertionError e) {
						exc = e;
						System.err.println(e.getMessage());
					}
				}
			});
			thread.start();
			DiceHistogram.main(new String[] {});
			thread.join(8000);
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
