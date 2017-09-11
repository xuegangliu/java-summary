package com.lxg.patterns.factory.abstracts;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

class FirstXiaoDi extends MakeFood {
    @Override public Drink createMakeDrink() { return new MilkTea(); }
    @Override public Snack createMakeSnack() { return new HandGrab(); }
}
