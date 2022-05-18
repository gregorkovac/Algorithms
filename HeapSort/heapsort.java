import java.util.Scanner;

public class HeapSort {
    public static void main(String[] args) {
        int n;
        int[] a;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = sc.nextInt();

        sort(a, n);

        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void sort(int a[], int n) {

        heap(a, n);

        while (n > 1) {
            int tmp = a[0];
            a[0] = a[n - 1];
            a[n - 1] = tmp; 

            n--;

            heapify(a, 0, n);
        }
    }

    public static void heap(int a[], int n) {
        for (int i = n / 2; i >= 0; i--) {
            heapify(a, i, n);
        }
    }


    public static void heapify(int a[], int i, int heapLen) {
        int left = 2 * i + 1, right = 2 * i + 2;
        boolean leftOk, rightOk;

        leftOk = left < heapLen ? true : false;
        rightOk = right < heapLen ? true : false;

        if ((!leftOk && !rightOk) || (leftOk && rightOk && a[i] >= a[left] && a[i] >= a[right]))
            return;
        
        if (leftOk && a[left] > a[i] && (!rightOk || a[left] >= a[right])) {
            int tmp = a[i];
            a[i] = a[left];
            a[left] = tmp;

            heapify(a, left, heapLen);
        }

        if (rightOk && a[right] > a[i] && (!leftOk || a[right] >= a[left])) {
            int tmp = a[i];
            a[i] = a[right];
            a[right] = tmp;

            heapify(a, right, heapLen);
        }
    }
}