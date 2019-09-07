package two.two_4;

import edu.princeton.cs.algs4.StdOut;

public class HeapSort<Key extends Comparable<Key>> {


    private static int N = 0; // 存储于pq[1..N]中，pq[0]没有使用


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void sort(Comparable[] a) {
        N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k);
        }
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1);
        }
    }

    private static void swim(Comparable[] a, int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(a, k / 2, k);
            k = k / 2;
        }
    }

    private static void sink(Comparable[] a, int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;

        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }
}
