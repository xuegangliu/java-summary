package org.lxg.basic.patterns.observer.push;

import org.junit.Test;

/**
 * @author xuegangliu
 *  8/30/2018 11:45 AM
 *
 * @version: v1.0
 */
public class PushTest {

    @Test
    public void test(){
        //创建被观察者
        Plant flower = new Flower();
        //创建三个观察者
        Insect bee1 = new Bee(1);
        Insect bee2 = new Bee(2);
        Insect bee3 = new Bee(3);
        //注册观察者
        flower.registerInsect(bee1);
        flower.registerInsect(bee2);
        flower.registerInsect(bee3);
        //改变被观察者状态，先开后合
        flower.notifyInsect(true);
        System.out.println("=== 太阳从东边到西边... ===");
        flower.notifyInsect(false);
        //最后解除注册
        flower.unregisterInsect(bee1);
        flower.unregisterInsect(bee2);
        flower.unregisterInsect(bee3);
    }
}
