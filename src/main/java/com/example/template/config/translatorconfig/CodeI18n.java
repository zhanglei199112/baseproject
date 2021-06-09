package com.example.template.config.translatorconfig;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * JSON输出时加入I18n字段
 * 
 * @author OYGD
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface CodeI18n {

}
