public class Choose {

	private static long[][] cache = new long[100][100];

	public static long choose(int n, int k) {
		if (k == 0 || k == n)
			return 1;
		if (cache[n][k] != 0)
			return cache[n][k];
		cache[n][k] = choose(n - 1, k - 1) + choose(n - 1, k);
		return cache[n][k];
	}

	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
			System.out.println();
		}

		
		long start = System.nanoTime();
		long result = choose(30, 15);
		long elapsed = System.nanoTime() - start;
		System.out.println("choose(30, 15) = " + result + " in " + elapsed / 1e6 + " ms");
	}
}