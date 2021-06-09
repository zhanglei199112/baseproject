package com.example.template.config;

import com.example.template.config.translatorconfig.CodeTranslateSerializer;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import org.springframework.beans.factory.InitializingBean;

public class CustomObjectMapper extends ObjectMapper implements InitializingBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    ModifySimpleModel module = new ModifySimpleModel();
    this.registerModule(module);
  }

  class ModifySimpleModel extends  SimpleModule{

    @Override
    public void setupModule(SetupContext context) {
      super.setupModule(context);
      context.addBeanSerializerModifier(new BeanSerializerModifier() {
        @Override
        public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc,
            JsonSerializer<?> serializer) {
          if (serializer instanceof BeanSerializerBase) {
            return new CodeTranslateSerializer((BeanSerializerBase) serializer);
          }
          return serializer;
        }

      });
    }
  }
}
