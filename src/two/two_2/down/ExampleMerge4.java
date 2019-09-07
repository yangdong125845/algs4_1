package two.two_2.down;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExampleMerge4 {

    private static int CUTOFF = 15;

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dest, int lo, int hi) {
        if (hi - lo < CUTOFF) {
            insertionSort(dest, lo, hi);
            return;
        }
        //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //dest,src颠倒，是为了让src有序，最终在dest中合并，dest中就有序了
        sort(dest, src, lo, mid); //将左半边排序
        sort(dest, src, mid + 1, hi); //将右半边排序
        if (less(src[mid + 1], src[mid])) {
            merge(src, dest, lo, mid, hi); //归并结果
        }
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void merge(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
        //将a[lo..mid] 和a[mid+1..hi]归并
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dest[k] = src[j++];
            } else if (j > hi) {
                dest[k] = src[i++];
            } else if (less(src[j], src[i])) {
                dest[k] = src[j++];
            } else {
                dest[k] = src[i++];
            }
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

    public static void main(String[] args) {
        //从标准输入读取字符串，将它们排序并输出
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
