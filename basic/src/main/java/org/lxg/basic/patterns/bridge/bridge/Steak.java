package org.lxg.basic.patterns.bridge.bridge;

/**
 * 描述：牛排大类
 * @author coder-pig： 2017/02/05 下午1:59
 */
abstract class Steak {
    Rations rations;

    Steak(Rations rations) { this.rations = rations; }

    abstract String sale();
}