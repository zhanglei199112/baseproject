package com.example.template.config.translatorconfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ReflecUtil {
  /**
   * 返回类cls及其超类（不包括Object类）中声明的字段列表。 如果子类中的字段重载了超类中的字段，则超类中的字段将不会添加到结果列表中。
   * 
   * @param cls
   * @return
   * @see [类、类#方法、类#成员]
   */
  public static List<Field> getDeclaredFields(Class<?> cls) {
    if (cls == null || Object.class.equals(cls)) {
      return Collections.emptyList();
    }
    final List<Field> fields = new ArrayList<Field>();
    final Set<String> fieldNames = new HashSet<String>();
    for (Field field : cls.getDeclaredFields()) {
      fields.add(field);
      fieldNames.add(field.getName());
    }
    for (Field field : getDeclaredFields(cls.getSuperclass())) {
      if (!fieldNames.contains(field.getName())) {
        fields.add(field);
      }
    }
    return fields;
  }

  public static <T extends Annotation> Set<String> getDeclaredFieldNames(Class<?> cls,
      Class<T> annotationClass) {
    Set<String> list = new LinkedHashSet<>();

    List<Field> declaredFields = getDeclaredFields(cls);
    if (null != declaredFields && !declaredFields.isEmpty()) {
      for (Field field : declaredFields) {
        T ann = field.getAnnotation(annotationClass);
        if (ann != null) {
          list.add(field.getName());
        }
      }
    }
    return list;
  }

  /**
   * 返回类cls及其超类（不包括Object类）中声明的方法列表。 如果子类中的字段重载了超类中的方法，则超类中的方法将不会添加到结果列表中。
   * 
   * @param cls
   * @return <字段名, (get方法, set方法)>
   * @see [类、类#方法、类#成员]
   */
  public static Map<String, MethodMap> getAllMethods(Class<?> cls) {
    Map<String, MethodMap> methodMap = new HashMap<>();
    if (cls == null || Object.class.equals(cls)) {
      return methodMap;
    }
    final Set<String> methodNames = new HashSet<String>();
    for (Method method : cls.getMethods()) {
      if (!isObjectMethod(method) && (isGetter(method) || isIser(method) || isSetter(method))) {
        MethodMap map;
        String fieldName = getFieldNameByMethod(method);
        if (methodMap.containsKey(fieldName)) {
          map = methodMap.get(fieldName);
        } else {
          map = new MethodMap();
        }

        if (isGetter(method) || isIser(method)) {
          map.getter = method;
        } else if (isSetter(method)) {
          map.setter = method;
        }
        methodNames.add(method.getName());
        methodMap.put(fieldName, map);
      }
    }
    for (Entry<String, MethodMap> entry : getAllMethods(cls.getSuperclass()).entrySet()) {
      String fieldName = entry.getKey();
      MethodMap value = entry.getValue();
      if (null != value.getter && null != value.setter
          && !methodNames.contains(value.getter.getName())
          && !methodNames.contains(value.setter.getName())) {
        methodMap.put(fieldName, value);
      }
    }
    return methodMap;
  }

  /**
   * 反射方法名称
   *
   * @param prefix
   * @param fieldName
   * @return
   */
  public static String getMethodName(String prefix, String fieldName) {
    return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public static boolean isSetter(Method method) {
    return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
  }

  public static boolean isGetter(Method method) {
    return method.getName().startsWith("get") && method.getParameterTypes().length == 0;
  }

  public static boolean isIser(Method method) {
    return method.getName().startsWith("is") && method.getParameterTypes().length == 0;
  }

  /**
   * Determine whether the given method is originally declared by {@link Object}.
   */
  public static boolean isObjectMethod(Method method) {
    if (method == null) {
      return false;
    }
    try {
      Object.class.getDeclaredMethod(method.getName(), method.getParameterTypes());
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * 根据方法名获取字段名
   * 
   * @param method
   * @return
   */
  private static String getFieldNameByMethod(Method method) {
    String str = method.getName().substring(isIser(method) ? 2 : 3);
    int strLen;
    if (str == null || (strLen = str.length()) == 0) {
      return str;
    }
    return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0)))
        .append(str.substring(1)).toString();
  }

  public static class MethodMap {
    /** getter方法 */
    Method getter;

    /** setter方法 */
    Method setter;
  }
}
