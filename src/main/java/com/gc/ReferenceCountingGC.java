package com.gc;

public class ReferenceCountingGC {

    public Object instance = null;
    //占用内存
    private byte[] bigSizeArr = new byte[2 * 1024 * 1024];

    public static void main(String[] args) {
        ReferenceCountingGC A = new ReferenceCountingGC();
        ReferenceCountingGC B = new ReferenceCountingGC();
        A.instance = B;
        B.instance = A;
        A = null;
        B = null;
        System.gc();
    }

}
