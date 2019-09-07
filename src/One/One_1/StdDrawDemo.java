package One.One_1;

import edu.princeton.cs.algs4.StdDraw;

public class StdDrawDemo {

    public static void changeString(int  i,String s,Obj obj) {
         s = "改变";
         i = 1;
         obj.i = 6666;
         obj.s = "改改";
    }

    public static void main(String[] args) {
        int N = 100;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N*N);
        StdDraw.setPenRadius(.01);
//        for (int i = 1;i<=N;i++){
//            StdDraw.point(i,i);
////            StdDraw.point(i,i*i);
//            StdDraw.point(i,i*Math.log(i));
//        }
        String s = "初值";
        int  i = 0;
        Obj obj = new Obj();
        obj.i = 0;
        obj.s ="kaishi";
        System.out.println(i);
        System.out.println(s);
        System.out.println(obj.i +obj.s);
        changeString(i,s,obj);
        System.out.println(i);
        System.out.println(s);
        System.out.println(obj.i +obj.s);
    }


    public static class  Obj{
       String s = "改变";
      int  i = 1;

      Obj(){

      }
    }

}
