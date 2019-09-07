package three.three_5;

import java.util.HashMap;

public class SparseVector {

    private HashMap<Integer, Double> st;

    public SparseVector() {
        st = new HashMap<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.containsKey(i)) return 0.0;
        else return st.get(i);
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keySet()) {
            sum += that[i] * this.get(i);
        }

        return sum;
    }

//    //使用
//    private int N = 4;
//    SparseVector[] a;
//    a=new SparseVector[N];
//    double[] x = new double[N];
//    double[] b = new double[N];
//    ...
//
//    //初始化 a[] 和 x[]
//
//    for(int i = 0;i<N; i++) {
//        b[i] = a[i].dot(x);
//    }

}
