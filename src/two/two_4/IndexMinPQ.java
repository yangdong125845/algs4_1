package two.two_4;

//https://www.cnblogs.com/nullzx/p/6624731.html 参考这，不过这里的含义于算法第四版有些不同，主要参考算法(第四版)

public class IndexMinPQ<Key extends Comparable<Key>> {

    private int N; //PQ中的元素数量
    private int[] pq;  //索引二叉堆，由1开始
    private int[] qp;  //逆序：qp[pq[i]] = pq[qp[i]] = i;
    private Key[] keys; //由优先级之分的元素

    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int k, Key key) {
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int delMin() {
        int indexOfMin = pq[1];
        swap(pq, 1, N--);
        swap(qp, pq[1], pq[N++]);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return indexOfMin;
    }

    public int minIndex() {
        return pq[1];
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        swap(pq, N--, index);
        swap(qp, pq[N++], pq[index]);
        swim(index);
        sink(index);
        qp[k] = -1;
        keys[k] = null;
    }

    @SuppressWarnings("unchecked")
    private void sink(int parent) {
        int child = parent * 2;
        while (child <= N) {
            if (child + 1 <= N) {
                child = less(keys[pq[child]], keys[pq[child + 1]]) ? child : child + 1;
            }

            if (less(keys[pq[child]], keys[pq[parent]])) {
                swap(pq, parent, child);
                swap(qp, pq[parent], pq[child]);
                parent = child;
                child = parent * 2;
            } else {
                break;
            }
        }
    }

    private void swim(int child) {
        int parent = child / 2;
        while (parent > 1) {
            if (less(keys[pq[child]], keys[pq[parent]])) {
                swap(pq, child, parent);
                swap(qp, pq[child], pq[parent]);
                child = parent;
                parent = child / 2;
            } else {
                break;
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp;
        tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }


}
