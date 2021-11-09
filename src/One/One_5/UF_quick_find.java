package One.One_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF_quick_find {

    private int[] id; //分量id(已触点作为索引)
    private int count;//分量数量

    public UF_quick_find(int N) {
        //初始化分量id数组
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int N = StdIn.readInt();
        UF_quick_find uf = new UF_quick_find(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        //    StdOut.println(p + " " + q);
        }
        long endTime = System.currentTimeMillis();
        StdOut.println(uf.count() + " components  spend   :" +(endTime-startTime));
    }

}
