import java.util.*;

class DFT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = 3, p, minRoot = Integer.MAX_VALUE;
        boolean found = false;

        n = sc.nextLong();

        p = n;

        while (!found) {
            p++;
            if (!isPrime(p))
                continue;
            
            for (long i = 1; i < p-1; i++) {
                if (isPrimitiveRoot(i, n, p)) {
                    if (!found) {
                        minRoot = i;
                        System.out.print(p + ":");
                    } else if (i < minRoot) {
                            minRoot = i;
                    }

                    System.out.print(" " + i);

                    found = true;
                }
            }
        }

        System.out.println();

        for (long k = 0; k < n; k++) {
            for (long j = 0; j < n; j++) {
                if (j != 0)
                    System.out.print(" ");
                
                if (k * j > 10) {
                    long np = (long)Math.pow((double)minRoot, Math.floor((k * j) * 0.5)) % p;
                    long mp = (long)Math.pow((double)minRoot, Math.ceil((k * j) * 0.5)) % p;
                    if ((np*mp) % p < 10)
                        System.out.print(" "); 
                    System.out.print((np*mp) % p);
                } else {
                    if ((long)Math.pow((double)minRoot, (double)(k * j)) % p < 10)
                        System.out.print(" ");
                    System.out.print((long)Math.pow((double)minRoot, (double)(k * j)) % p);
                }
            }
            System.out.println();
        }
    }


    public static boolean isPrime(long n) {
        if (n <= 1)
            return false;
        
        for (long i = 2; i < n; i++)
            if (n % i == 0)
                return false;
  
        return true;
    }

    public static boolean isPrimitiveRoot(long a, long n, long p) {
        if (((long)Math.pow(a, n)) % p != 1)
            return false;

        for (long i = 1; i < n; i++) {
            if ((long)(Math.pow((double)a, (double)i) % p) == 1) {
                return false;
            }
        }

        return true;
    }
}