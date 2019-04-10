package com.lxg.problem.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**************************
 * @description: SimpleTest
 * @author: xuegangliu
 * @date: 2019/3/19 16:07
 ***************************/
@Slf4j
public class SimpleTest {

    @Test
    public void testAutoBox(){
//        原始类型: boolean，char，byte，short，int，long，float，double
//        包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double
        log.info("Integer.Max:{},Integer.Min:{}",Integer.MAX_VALUE,Integer.MIN_VALUE);
        Integer a = new Integer(3);
        Integer b = 3;                  // 将3自动装箱成Integer类型
        int c = 3;
        log.info("a == b:{}",a == b);// false 两个引用没有引用同一对象
        log.info("a == c:{}",a == c);// true a自动拆箱成int类型再和c比较
        log.info("========================");
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        log.info("f1 == f2:{}",f1 == f2);
        log.info("f3 == f4:{}",f3 == f4);
    }

    @Test
    public void testBasicStruct(){
//        基本类型	大小(字节)	默认值	封装类
//        byte	1	(byte)0	Byte
//        short	2	(short)0	Short
//        int	4	0	Integer
//        long	8	0L	Long
//        float	4	0.0f	Float
//        double	8	0.0d	Double
//        boolean	-	false	Boolean
//        char	2	\u0000(null)	Character
//        void	-	-	Void
        int a1 = 1800000000;
        int a2 = new Integer(1800000000);
        int a3 = a1;
        int a4 = 1800000000;

        log.info("a1==a2:{}",(a1==a2));
        log.info("a1==a3:{}",(a1==a3));
        log.info("a1==a4:{}",(a1==a4));

        log.info("2 << 2:{}",(2 << 2));

    }

    @Test
    public void testAdd(){
        int i=0;
        int j=0;
        log.info("i++:{}",(i++));
        log.info("++j:{}",(++j));
    }

    @Test
    public void testFinal(){
        final int t = 5;
        testT(t);
    }

    public void testT(int t){
        log.info("{}",t);
        ++t;
        log.info("{}",t);
    }

    @Test
    public void test11(){
        Integer t = 101;
        Double t1 = 101.0000001;
        Long t2 = 101l;
        String t3 = "101.0000001";
        log.info("{}",new BigDecimal(t1));
        log.info("{},{},{}",t.getClass(),t,new BigDecimal(t).divide(new BigDecimal(100)));
        log.info("{},{}",t1,new BigDecimal(t1).divide(new BigDecimal(100)));
        log.info("{},{}",t2,new BigDecimal(t2).divide(new BigDecimal(100)));
        log.info("{},{}",t3,new BigDecimal(t3).divide(new BigDecimal(100)));

    }

    @Test
    public void testBigDecimalNull(){
        BigDecimal t = new BigDecimal("");
        log.info("{}",t);
    }

    /**
     * if 跳出
     */
    @Test
    public void testIfBreak(){
        log.info("start");
        ok:if(1<10){
            log.info("{}","1<10");
            if(5<10) {
                log.info("{}", "5<10");
                break ok;
            }
            if(6<10){
                log.info("{}","6<10");
            }
        }
        log.info("end");
    }

    @Test
    public void test111(){
        int i=5;
        if(i>1){
            System.out.println(1);
        }else if(i > 3){
            System.out.println(2);
        }
    }

    @Test
    public void testLt0Integer(){
        Integer i = -1;
        Integer a=500,b=500;
        log.info("{}",-1==i);
        log.info("{}",a==b);
    }
}
