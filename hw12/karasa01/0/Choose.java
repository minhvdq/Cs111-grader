
public class Choose {

	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
			System.out.println();
		}
	}
	public static final int SIZE = 70;
	public static Long[][] memory = new Long[SIZE][SIZE];

	public static long choose(int n, int k) {
		if (k == 0 || k == n) {
			return 1L;
		}
		if (memory[n][k] != null) {
			return memory[n][k];
		}
		memory[n][k] = choose(n - 1, k - 1) + choose(n - 1, k);
		return memory[n][k];

	}
}