package com.example.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.template.mapper")
public class TemplateApplication {

  public static void main(String[] args) {
    SpringApplication.run(TemplateApplication.class, args);
  }

}
