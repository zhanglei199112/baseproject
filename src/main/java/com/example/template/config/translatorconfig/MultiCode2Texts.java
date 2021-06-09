package com.example.template.config.translatorconfig;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 多个数据字典转换，对同一实体中有多个字段需要一起翻译
 * 
 * @author OYGD
 *
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface MultiCode2Texts {

  MultiCode2Text[] value();
}
