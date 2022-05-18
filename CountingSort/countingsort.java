import java.util.*;

public class countingsort {
    public static void main(String[] args) {
        int n;
        int[] a, numOnes, c, sorted;
        Scanner sc = new Scanner (System.in);
        int numOnesMax = 0;

        n = sc.nextInt();
        a = new int[n];
        numOnes = new int[n];
        sorted = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            numOnes[i] = Integer.bitCount(a[i]);
            if (numOnes[i] > numOnesMax)
                numOnesMax = numOnes[i];
        }
        
        numOnesMax++;
        c = new int[numOnesMax];

        for (int i = 0; i < n; i++)
            c[numOnes[i]]++;

        for (int i = 0; i < numOnesMax; i++) {
            if (i != 0)
                c[i] += c[i-1];
        }

        for (int i = 0; i < numOnesMax; i++)
            c[i]--;

        for (int i = n - 1; i >= 0; i--) {
            sorted[c[numOnes[i]]] = a[i];
            c[numOnes[i]]--;
        }
        
        
        for (int i = 0; i < n; i++) {
            if (i != 0)
                System.out.print(" ");
            System.out.print(sorted[i]);
        }
        System.out.println();
    }
}