/**
 * Choose
 */
public class Choose {

    public static long choose(int n, int k) {
        if (n < k) {
            return 0;
        } else if (n == k || n == 0) {
            return 1;
        }
        return choose(n - 1, k - 1) + choose(n - 1, k);
    }

    public static void main(String[] args) {
        for (int n = 0; n < 10; n++) {
            for (int k = 0; k <= n; k++) System.out.printf(
                "%d\t",
                choose(n, k)
            );
            System.out.println();
        }
    }
}
