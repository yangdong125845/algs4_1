package two.two_3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


//消除连个边界，lo边界本来就不需要，hi边界把最大元素发在a.length-1就好了
public class ExampleQuick3WayInserttionFastMiddle {

    private static int CUTOFF = 15;

    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 8;

    // cutoff to median-of-3 partitioning
    private static final int MEDIAN_OF_3_CUTOFF = 40;


    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);  //消除对输入的依赖
        int temp = 0;
        for (int i = 1; i < a.length; i++) {
            if (less(a[temp], a[i])) temp = i;
        }
        exch(a, temp, a.length - 1);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        //cutoff to insertion sort
        if (n <= INSERTION_SORT_CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        //use median-of-3 as partitioning element
        else if (n < MEDIAN_OF_3_CUTOFF) {
            int m = median3(a, lo, lo + n / 2, hi);
            exch(a, m, lo);
        }
        //use Tukey ninther as partitioning element
        else {
            int eps = n / 8;
            int mid = lo + n / 2;
            int m1 = median3(a, lo, lo + eps, lo + eps + eps);
            int m2 = median3(a, mid - eps, mid, mid + eps);
            int m3 = median3(a, hi - eps - eps, hi - eps, hi);
            int ninher = median3(a, m1, m2, m3);
            exch(a, ninher, lo);
        }

        //Bentley-McIlroy 3-way partitioning
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;

            if (i == j && eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (i >= j) break;
            exch(a, i, j);

            if (eq(a[i], v)) {
                exch(a, ++p, i);
            }
            if (eq(a[j], v)) {
                exch(a, --q, j);
            }
        }
        for (int k = lo; k <= p; k++) {
            exch(a, k, j--);
        }
        for (int k = hi; k >= q; k--) {
            exch(a, k, i++);
        }
        sort(a, lo, j);
        sort(a, i, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        //将数组切分为a[lo..i-1],a[i],a[i+1..hi]
        int i = lo, j = hi + 1;   //左右扫描指针
        Comparable v = a[lo];
        while (true) {
            //扫描左右，检查扫描是都结束并交换元素
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); //将 v= a[j] 放入正确的位置
        return j;   //a[lo..j-1] <=a[j] <=a[j+1..hi] 达成
    }

    //return the index of the median element among a[i],a[j],a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ? (less(a[j], a[k]) ? j :
                less(a[i], a[k]) ? k : i)
                : (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
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
