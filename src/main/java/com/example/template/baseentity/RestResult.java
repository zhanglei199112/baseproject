package com.example.template.baseentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResult {

  private String msg;
  private Integer code;

  public static RestResult ok() {
    RestResult restResult = new RestResult();
    restResult.setCode(200);
    restResult.setMsg("");
    return restResult;
  }
}
