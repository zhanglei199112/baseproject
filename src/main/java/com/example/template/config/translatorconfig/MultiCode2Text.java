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
public @interface MultiCode2Text {

  /**
   * 翻译器
   * 
   * @return
   */
  @SuppressWarnings("rawtypes")
  Class<? extends MultiCodeTranslator> translateor();

  /**
   * 翻译器对应的Key，需要使用translateor.getName()
   * 
   * @return
   */
  String translateorKey() default "";

  /**
   * 输出时使用的字段名，默认是使用解析上的字段名称+I18n(e.g: nameI18n, ageI18n, sexI18n)
   * 
   * @return
   */
  String jsonFiledName() default "";
}
