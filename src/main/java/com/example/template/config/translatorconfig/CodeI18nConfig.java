package com.example.template.config.translatorconfig;

import com.example.template.config.CustomObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class CodeI18nConfig {

  @Bean
  public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter()
      throws Exception {
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    CustomObjectMapper customObjectMapper = new CustomObjectMapper();
    customObjectMapper.afterPropertiesSet();
    mappingJackson2HttpMessageConverter.setObjectMapper(customObjectMapper);
    return  mappingJackson2HttpMessageConverter;
  }

}
