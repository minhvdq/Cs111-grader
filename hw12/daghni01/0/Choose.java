public class Choose {

	public static long choose(int n, int k) {

		if (k == 0 || k == n) {
			return 1;
		}

		return choose(n - 1, k - 1) + choose(n - 1, k);
	}
	public static long chooseMemoized(int n, int k, long[][] memo) {
		if (k == 0 || k == n) return 1;
		if (memo[n][k] != 0) return memo[n][k];

		memo[n][k] = chooseMemoized(n - 1, k - 1, memo) + chooseMemoized(n - 1, k, memo);
		return memo[n][k];
	}

	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
			System.out.println();
		}

		int nVal = 30, kVal = 15;
		long[][] memo = new long[nVal + 1][kVal + 1];
		System.out.println("\nChoose(" + nVal + ", " + kVal + ") with memoization: " + chooseMemoized(nVal, kVal, memo));
	}
}