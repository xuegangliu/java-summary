package org.lxg.basic.jdk5.annotation;

import java.lang.annotation.*;

/**
 * @author: Max
 * @date: 2018/4/6 15:24
 * @DES: com.lxg.jdk5.annotation
 */
@Documented
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface MethodInfo {
    String author() default "Max";
    String date();
    int version() default 1;
}