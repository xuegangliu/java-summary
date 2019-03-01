package com.lxg.core.util;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**************************
 * @description: ClassUtils
 * @author: xuegangliu
 * @date: 2019/3/1 10:29
 ***************************/
@Log4j2
public class ClassUtils {

    /**
     * 执行对象中的所有方法
     * @param obj 实例化的对象
     * @param clazz 对象的class
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void excuteObjMethod(Object obj,Class clazz) throws InvocationTargetException, IllegalAccessException {
        log.info("----------------- {} ------------------------",clazz.getName());
        Method[] methods = clazz.getDeclaredMethods();
        for(Method t:methods){
            if(t.getParameterCount()==0)
                log.info("{}():{}",t.getName(),t.invoke(obj,(Object[]) null));
        }
        log.info("-----------------------------------------------");
    }
}
