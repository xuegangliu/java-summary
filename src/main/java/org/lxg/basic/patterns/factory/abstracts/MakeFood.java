package org.lxg.basic.patterns.factory.abstracts;

/**
 * Created by Administrator on 2017/1/18 0018.
 */

abstract class MakeFood {
    abstract Drink createMakeDrink();
    abstract Snack createMakeSnack();
}
