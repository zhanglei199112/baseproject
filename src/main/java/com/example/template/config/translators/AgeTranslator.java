package com.example.template.config.translators;

import com.example.template.config.translatorconfig.Code2Text;
import com.example.template.config.translatorconfig.CodeTranslator;
import com.example.template.config.translatorconfig.CodeTranslatorFactory;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class AgeTranslator implements CodeTranslator<String> {

  @Override
  public String translate(Object obj, Object value, Code2Text ann) {
    return "测试age";
  }

  @PostConstruct
  public void init() {
    CodeTranslatorFactory.register(this.getClass(), this);
  }
}
