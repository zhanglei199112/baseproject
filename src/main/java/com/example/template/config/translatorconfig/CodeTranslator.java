package com.example.template.config.translatorconfig;

/**
 * 数据字典翻译器
 * 
 * @author OYGD
 *
 */
public interface CodeTranslator<R> {
	/**
	 * 翻译接口
	 * 
	 * @param obj   对象
	 * @param value 字段对应的值
	 * @param ann   字段对应的注解
	 */
	public R translate(Object obj, Object value, Code2Text ann);
}
