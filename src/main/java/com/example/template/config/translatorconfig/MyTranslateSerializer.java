package com.example.template.config.translatorconfig;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MyTranslateSerializer <T> extends JsonSerializer<T> {

  @Override
  public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
      gen.writeNumber(1);
  }

  @Override
  public Class<T> handledType() {
    Type type = getClass().getGenericSuperclass();
    Type[] parameter = ((ParameterizedType) type).getActualTypeArguments();
    return (Class<T>)parameter[0];
  }
}
