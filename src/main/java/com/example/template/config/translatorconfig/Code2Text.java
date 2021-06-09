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
public @interface Code2Text {
	/**
	 * 翻译器自行处理
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 翻译器
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends CodeTranslator> translateor() default CodeTranslator.class;
	
	/**
	 * 翻译器对应的Key，需要使用translateor.getName()
	 * @return
	 */
	String translateorKey() default "";
	
	/**
	 * 输出时使用的字段名，默认是使用解析上的字段名称+I18n(e.g: nameI18n, ageI18n, sexI18n)
	 * @return
	 */
	String jsonFiledName() default "";
}
