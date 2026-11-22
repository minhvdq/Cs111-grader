import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class ChooseJUnitTest {

	static HashMap<Integer, HashMap<Integer, Long>> results = new HashMap<Integer, HashMap<Integer, Long>>();

	public static long chooseDP(int n, int k) {
		if (k == 0)
			return 1;
		if (n == 0)
			return 0;
		if (!results.containsKey(n))
			results.put(n, new HashMap<Integer, Long>());
		HashMap<Integer, Long> nMap = results.get(n);
		if (!nMap.containsKey(k))
			nMap.put(k, chooseDP(n - 1, k - 1) + chooseDP(n - 1, k));
		return nMap.get(k);
	}
	
	@Test
	public void test() {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) {
//				System.err.println(n + " " + k);
				long actual = chooseDP(n, k);
				long observed = Choose.choose(n, k);
				if (observed != actual)
					fail(String.format("choose(%d, %d) == %d, but the result %d was returned.", n, k, actual, observed));
			}
		}
	}
}
