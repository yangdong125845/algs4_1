package two.two_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExampleInsertionII {

    public static void sort(Comparable[] a) {
        //将a[] 按升序排列
        int N = a.length;
        for (int i = 1; i < N; i++) {
            //将a[i] 插入到a[i-1],a[i-2],a[i-3]...之中
            int j = i;
            for (; j > 0 && less(a[j], a[j - 1]); j--) {
                a[j] = a[j - 1];
            }
            a[j] = a[i];
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
