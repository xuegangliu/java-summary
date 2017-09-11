package com.lxg.patterns.single;

//懒汉模式(线程安全，但效率低，不推荐使用)
public class Single_3 {
    private static Single_3 instance = null;
    private Single_3() { }
    public static synchronized Single_3 getInstance() {
        if(instance == null) {
            instance = new Single_3();
        }
        return instance;
    }
}