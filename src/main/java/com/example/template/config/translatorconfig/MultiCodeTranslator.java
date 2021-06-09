package com.example.template.config.translatorconfig;

/**
 * 多个数据字典转换，对同一实体中有多个字段需要一起翻译
 * 
 * @author OYGD
 *
 */
public interface MultiCodeTranslator<R> {
  /**
   * 翻译接口
   * 
   * @param obj 对象
   * @param ann 字段对应的注解
   */
  public R translate(Object obj, MultiCode2Text ann);
}
