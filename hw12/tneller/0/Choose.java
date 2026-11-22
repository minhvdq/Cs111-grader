import java.util.HashMap;

public class Choose {
	
	static HashMap<Integer, HashMap<Integer, Long>> results = new HashMap<Integer, HashMap<Integer, Long>>();

	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
//				System.out.printf("%d\t", chooseDP(n, k));
			System.out.println();
		}
	}

	public static long choose(int n, int k) {
		if (k == 0)
			return 1;
		if (n == 0)
			return 0;
		return choose(n - 1, k - 1) + choose(n - 1, k);
	}
	
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
}
