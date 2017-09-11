package com.lxg.patterns.interpreter;

/**
 * Created by Administrator on 2017/3/16 0016.
 * 解释器模式(申明器模式\翻译器解析器模式)
 */

public class Client {
    public static void main(String[] args) {
        Context context = new Context();

        VariableExpression a = new VariableExpression("a");
        VariableExpression b = new VariableExpression("b");
        ConstantExpression c = new ConstantExpression(6);

        context.addExpression(a, 2);
        context.addExpression(b, 3);

        Expression expression = new PlusExpression(new PlusExpression(a,b),new MinusExpression(a,c));
        System.out.println(expression.toString() + " = " + expression.interpret(context));

    }
}
