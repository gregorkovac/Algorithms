import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BiggestSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int[] t;

        n = sc.nextInt();
        t = new int[n];
        for (int i = 0; i < n; i++)
            t[i] = sc.nextInt();

        maxSubsequenceSum(t);
    }

    public static int maxSubsequenceSum(int[] t) {
        if (t.length <= 1) {
            System.out.println("[" + t[0] + "]: " + t[0]);
            return t[0];
        }

        int[] a, b;
        int n = (int)Math.ceil((double)t.length/2);

        a = new int[n];
        b = new int[t.length - n];

        for (int i = 0; i < t.length; i++) {
            if (i < n)
                a[i] = t[i];
            else
                b[i - n] = t[i];
        }

        int ai = a.length - 1, bi = 0;
        int maxMidSum = a[ai] + b[bi];
        int currMidSum = maxMidSum;

        while (ai > 0) {
            ai--;
            currMidSum +=  a[ai];
            if (currMidSum > maxMidSum)
                maxMidSum = currMidSum;
        }

        currMidSum = maxMidSum;

        while (bi < b.length - 1) {
            bi++;
            currMidSum += b[bi];
            if (currMidSum > maxMidSum)
                maxMidSum = currMidSum;
        }
        
        int maxLeftSum = maxSubsequenceSum(a);

        int ret = maxLeftSum;

        int maxRightSum = maxSubsequenceSum(b);

        if (maxMidSum > ret)
            ret = maxMidSum;
        if (maxRightSum > ret)
            ret = maxRightSum;
        
        System.out.print("[");
        for (int i = 0; i < t.length; i++) {
            if (i != 0)
                System.out.print(", ");
            System.out.print(t[i]);
        }
        System.out.println("]: " + ret);

        return ret;
    }
}