package com.example.template.config.translatorconfig;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 数据字典转换为文字
 * 
 * @author OYGD
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Code2Texts {

  Code2Text[] value();

}
