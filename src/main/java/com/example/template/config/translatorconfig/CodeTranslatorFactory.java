package com.example.template.config.translatorconfig;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

public class CodeTranslatorFactory {

  private static final Map<String, CodeTranslator<?>> translateors = new HashMap<>(10);
  private static final Map<String, MultiCodeTranslator<?>> multiTranslateors = new HashMap<>(10);

  private CodeTranslatorFactory() {}

  /**
   * 注册翻译器
   * 
   * @param clazz
   * @param tran
   */
  public static void register(Class<? extends CodeTranslator<?>> clazz, CodeTranslator<?> tran) {
    translateors.put(clazz.getName(), tran);
  }

  public static void registerMulit(Class<? extends MultiCodeTranslator<?>> clazz,
      MultiCodeTranslator<?> tran) {
    multiTranslateors.put(clazz.getName(), tran);
  }

  /**
   * 进行翻译
   * 
   * @param object
   * @param consumer
   */
  public static void translate(Object object, BiConsumer<String, Object> consumer) {
    if (object == null) {
      return;
    }
    Class<? extends Object> clazz = object.getClass();
    CodeI18n ann = clazz.getAnnotation(CodeI18n.class);
    if (null != ann) {
      doClassTypeTrans(object, consumer, clazz);

      doFieldTrans(object, consumer, clazz);
    }
  }

  /**
   * 处理类上的翻译
   * 
   * @param object
   * @param consumer
   * @param clazz
   */
  private static void doClassTypeTrans(Object object, BiConsumer<String, Object> consumer,
      Class<? extends Object> clazz) {
    MultiCode2Texts muti2TextAnns = clazz.getAnnotation(MultiCode2Texts.class);
    if (muti2TextAnns != null) {
      for (int i = 0; i < muti2TextAnns.value().length; i++) {
        MultiCode2Text muti2TextAnn = muti2TextAnns.value()[i];
        String jsonFiledName = clazz.getSimpleName() + "I18n" + i;
        if (StringUtils.isNotEmpty(muti2TextAnn.jsonFiledName())) {
          jsonFiledName = muti2TextAnn.jsonFiledName();
        }
        String key = muti2TextAnn.translateorKey();
        if (StringUtils.isEmpty(key) && muti2TextAnn.translateor() != MultiCodeTranslator.class) {
          key = muti2TextAnn.translateor().getName();
        }
        MultiCodeTranslator<?> fieldTranslator = multiTranslateors.get(key);
        Object value = null;
        if (null != fieldTranslator) {
          value = fieldTranslator.translate(object, muti2TextAnn);
        }
        consumer.accept(jsonFiledName, value);
      }
    } else {
      // 处理类上的
      MultiCode2Text muti2TextAnn = clazz.getAnnotation(MultiCode2Text.class);
      if (muti2TextAnn != null) {
        String jsonFiledName = clazz.getSimpleName() + "I18n";
        if (StringUtils.isNotEmpty(muti2TextAnn.jsonFiledName())) {
          jsonFiledName = muti2TextAnn.jsonFiledName();
        }
        String key = muti2TextAnn.translateorKey();
        if (StringUtils.isEmpty(key) && muti2TextAnn.translateor() != MultiCodeTranslator.class) {
          key = muti2TextAnn.translateor().getName();
        }
        MultiCodeTranslator<?> fieldTranslator = multiTranslateors.get(key);
        Object value = null;
        if (null != fieldTranslator) {
          value = fieldTranslator.translate(object, muti2TextAnn);
        }
        consumer.accept(jsonFiledName, value);
      }
    }
  }

  /**
   * 处理字段上翻译
   * 
   * @param object
   * @param consumer
   * @param clazz
   */
  private static void doFieldTrans(Object object, BiConsumer<String, Object> consumer,
      Class<? extends Object> clazz) {
    Set<String> declaredFieldNames = ReflecUtil.getDeclaredFieldNames(clazz, Code2Text.class);
    Set<String> code2TextsFieldNames = ReflecUtil.getDeclaredFieldNames(clazz, Code2Texts.class);
    declaredFieldNames.addAll(code2TextsFieldNames);

    for (String fieldName : declaredFieldNames) {
      String methodName = ReflecUtil.getMethodName("get", fieldName);
      Method method = ReflectionUtils.findMethod(clazz, methodName);
      if (null != method && ReflecUtil.isGetter(method)) {
        Object getValue = ReflectionUtils.invokeMethod(method, object);
        // 查询对应字段
        Field field = ReflectionUtils.findField(clazz, fieldName);
        Object value = null;
        if (null != field) {
          Code2Texts code2TextsAnn = field.getDeclaredAnnotation(Code2Texts.class);
          if (null != code2TextsAnn) {
            // 处理多个
            Code2Text[] array = code2TextsAnn.value();
            for (int i = 0; i < array.length; i++) {
              Code2Text code2TextAnn = array[i];
              String jsonFiledName = fieldName + "I18n" + i;
              if (null != getValue && code2TextAnn != null) {
                if (StringUtils.isNotEmpty(code2TextAnn.jsonFiledName())) {
                  jsonFiledName = code2TextAnn.jsonFiledName();
                }
                String key = code2TextAnn.translateorKey();
                if (StringUtils.isEmpty(key)
                    && code2TextAnn.translateor() != CodeTranslator.class) {
                  key = code2TextAnn.translateor().getName();
                }
                CodeTranslator<?> fieldTranslator = translateors.get(key);
                if (null != fieldTranslator) {
                  value = fieldTranslator.translate(object, getValue, code2TextAnn);
                }
              }
              consumer.accept(jsonFiledName, null == value ? "" : value);
            }
          } else {
            // 处理单个注解
            Code2Text code2TextAnn = field.getDeclaredAnnotation(Code2Text.class);
            String jsonFiledName = fieldName + "I18n";
            if (null != getValue && code2TextAnn != null) {
              if (StringUtils.isNotEmpty(code2TextAnn.jsonFiledName())) {
                jsonFiledName = code2TextAnn.jsonFiledName();
              }
              String key = code2TextAnn.translateorKey();
              if (StringUtils.isEmpty(key) && code2TextAnn.translateor() != CodeTranslator.class) {
                key = code2TextAnn.translateor().getName();
              }
              CodeTranslator<?> fieldTranslator = translateors.get(key);
              if (null != fieldTranslator) {
                value = fieldTranslator.translate(object, getValue, code2TextAnn);
              }
            }
            consumer.accept(jsonFiledName, null == value ? "" : value);
          }
        }
      }
    }
  }

}
