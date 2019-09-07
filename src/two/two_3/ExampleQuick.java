package two.two_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ExampleQuick {

    private static Comparable[] aux;  //归并锁需要的辅助数组


    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);  //消除对输入的依赖
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int j = partition(a, lo, hi); //切分
        sort(a, lo, j - 1);  //将左半部分a[lo..j-1] 排序
        sort(a, j + 1, hi);   //将右半部分a[j+1..hi]排序
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //将数组切分为a[lo..i-1],a[i],a[i+1..hi]
        int i = lo, j = hi + 1;   //左右扫描指针
        Comparable v = a[lo];
        while (true) {
            //扫描左右，检查扫描是都结束并交换元素
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); //将 v= a[j] 放入正确的位置
        return j;   //a[lo..j-1] <=a[j] <=a[j+1..hi] 达成
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
