package org.lxg.basic.patterns.strategy;

/**
 * Created by Administrator on 2017/3/6 0006.
 */
public class Mul implements Compute{
    @Override public String compute(int first, int second) {
        return "输出结果：" + first + " * " + second + " = " + (first * second);
    }
}
