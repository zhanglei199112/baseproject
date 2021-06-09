package com.example.template.config.translatorconfig;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;


/**
 * Jackson Bean序列化
 */
@Slf4j
public class CodeTranslateSerializer extends BeanSerializerBase {

  private static final long serialVersionUID = 1L;

  Logger logger = Logger.getLogger("BeanSerializerBase");

  public CodeTranslateSerializer(BeanSerializerBase source) {
    super(source);
  }

  protected CodeTranslateSerializer(CodeTranslateSerializer source, ObjectIdWriter objectIdWriter) {
    super(source, objectIdWriter);
  }

  protected CodeTranslateSerializer(CodeTranslateSerializer source, Set<String> toIgnore) {
    super(source, toIgnore);
  }

  protected CodeTranslateSerializer(BeanSerializerBase src, ObjectIdWriter objectIdWriter, Object filterId) {
    super(src, objectIdWriter, filterId);
  }

  @Override
  public BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter) {
    return new CodeTranslateSerializer(this, objectIdWriter);
  }

  @Override
  public void serialize(Object bean, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonGenerationException {
    jgen.writeStartObject();
    CodeTranslatorFactory.translate(bean, (key, value) -> {
      try {
        if (value instanceof String) {
          jgen.writeStringField(key, (String) value);
        } else if (value instanceof Integer) {
          jgen.writeNumberField(key, ((Integer) value).intValue());
        } else if (value instanceof Long) {
          jgen.writeNumberField(key, ((Long) value).longValue());
        } else if (value instanceof Double) {
          jgen.writeNumberField(key, ((Double) value).doubleValue());
        } else if (value instanceof Float) {
          jgen.writeNumberField(key, ((Float) value).floatValue());
        } else {
          jgen.writeObjectField(key, value);
        }
      } catch (IOException e) {
        log.warn(e.getMessage(), e);
      }
    });
    serializeFields(bean, jgen, provider);
    jgen.writeEndObject();
  }

  @Override
  protected BeanSerializerBase withIgnorals(Set<String> toIgnore) {
    return new CodeTranslateSerializer(this, toIgnore);
  }

  @Override
  protected BeanSerializerBase asArraySerializer() {
    if ((_objectIdWriter == null) && (_anyGetterWriter == null) && (_propertyFilterId == null)) {
      return new BeanAsArraySerializer(this);
    }
    // already is one, so:
    return this;
  }

  @Override
  public BeanSerializerBase withFilterId(Object filterId) {
    return new CodeTranslateSerializer(this, _objectIdWriter, filterId);
  }
}
