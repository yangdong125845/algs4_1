package One.One_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF_compress_path {

    private int count;
    private int[] parent;
    private int[] size;

    public UF_compress_path(int n){
       this.count = n;
       this.parent = new int[n];
       this.size = new int[n];
       for(int i = 0;i<n;i++){
           parent[i] = i;
           size[i]= 1;
       }
    }

    public void union(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);

        if(size[rootP]>size[rootQ]){
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        count--;

    }

    public boolean connected(int p,int q){
        int rootP = find(p);
        int rootQ  =find(q);
        return rootP == rootQ;
    }

    private int find(int x){
        while(parent[x]!=x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int N = StdIn.readInt();
        UF_compress_path uf = new UF_compress_path(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            //  StdOut.println(p + " " + q);
        }
        long endTime = System.currentTimeMillis();
        StdOut.println(uf.count() + " components  spend   :" +(endTime-startTime));
    }
}
