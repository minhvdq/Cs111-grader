
public class Choose {

	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			for (int k = 0; k <= n; k++) 
				System.out.printf("%d\t", choose(n, k));
			System.out.println();
		}
	}

//	public static Long[] nFib = new Long[93];
//	public static Long[] kFib = new Long[93];

	
	public static long choose(int n, int k) {
		if (k == 0)
			return 1L;
		else if (n == k)
			return 1L;
		else { // recursive case
			return choose(n - 1, k - 1) + choose(n - 1, k);
			
		}
		
	}

}
