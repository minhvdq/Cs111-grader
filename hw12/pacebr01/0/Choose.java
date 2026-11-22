
public class Choose {
	
	public static long[][] cache = new long[100][100]; // 100 rows, 100 cols
	/*
	 * Everything's filled with 0, and 0 means it's unaswered
	 * n = total number of items, k the number of chosen items
	 */

	// number of ways to choose k items from n
	public static long choose(int n, int k) {
		/*
		 * [n][k] where to look?
		 * if it isn't 0, then an answer's been stored, so return it (return cache[n][k])
		 */
		if (cache[n][k] != 0) { 
			return cache[n][k];
		}
		/*
		 * if k (chosen items) equals 0, then nothing is chosen, OR
		 * if k (chosen items) equals the amount of things that can be chosen,
		 * then there is only way way to do something
		 */
		if (k == 0 || k == n) {
			return 1;
		}
		// recursion:
		cache[n][k] = choose(n - 1, k - 1) + choose(n - 1, k);
		return cache[n][k];
	}
	
	// iterates through all the possible values
	/* k   n
	 * 0   0
	 * 1   0, 1
	 * 2   0, 1, 2
	 * 3   0, 1, 2, 3
	 * ...
	 */
	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
			System.out.println();
		}
	}

}
