package com.lxg.open.library.guice.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @author xuegangliu
 */
@Target({ METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface InvokeMe {
}
