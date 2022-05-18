import java.util.Scanner;

public class Eggs {
    static int[][] t;
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        t = new int[N+1][K+1];

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                    t[i][j] = -1;
            }
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                t[i][j] = eggs(i, j);
            }
        }

        System.out.print("    ");
        for (int i = 1; i <= K; i++) 
            System.out.printf("%4d", i);
        System.out.println();

        for (int i = 0; i <= N; i++) {
            System.out.printf("%4d", i);
            for (int j = 1; j <= K; j++) {
                System.out.printf("%4d", t[i][j]);
            }
            System.out.println();
        }
    }

    static int eggs(int n, int k) {
        if (t[n][k] != -1) {
            return t[n][k];
        }

        if (k == 1 || n == 0 || n == 1) {
            t[n][k] = n;
            return n;
        }

        int minEgg = Integer.MAX_VALUE;

        for (int x = 1; x <= n; x++) {
            int maxEgg = Integer.max(eggs(x - 1, k - 1), eggs(n - x, k));
            if (maxEgg < minEgg)
                minEgg = maxEgg;
        }

        t[n][k] = 1 + minEgg;

        return 1 + minEgg;
    }
}