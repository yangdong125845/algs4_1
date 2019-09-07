package two.two_4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Multiway {
    public static void main(String[] args) {
        args = new String[] { ".\\src\\two\\m1.txt", ".\\src\\two\\m2.txt",  ".\\src\\two\\m3.txt"};
        int N = args.length;
        In[] streams = new In[N];
        for(int i =0 ;i<N;i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }

    public static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<String>(N);
        for (int i = 0;i<N;i++) {
            if(!streams[i].isEmpty()) {
                pq.insert(i,streams[i].readString());
            }
        }

        while (!pq.isEmpty()){
            StdOut.println(pq.minKey());
            int i = pq.delMin();
            if(!streams[i].isEmpty()) {
                pq.insert(i,streams[i].readString());
            }
        }
    }
}
