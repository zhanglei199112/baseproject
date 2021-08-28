package com.emp.entity;

import java.util.HashMap;

public class RestEntity extends HashMap<String, Object>{

  private static String SUCCESS_STATUS = "200";

  private static String ERROR_STATUS = "500";

  private RestEntity(){}

  public static RestEntity ok()
  {
    RestEntity entity = build().msg("操作成功");
    return entity.success(true).code(SUCCESS_STATUS);
  }

  private RestEntity code(String successStatus) {
    this.put("status",successStatus);
    return this;
  }

  private RestEntity success(boolean b) {
    this.put("success",b);
    return this;
  }

  private RestEntity msg(String msg) {
    this.put("msg",msg);
    return this;
  }

  public static RestEntity ok(String msg)
  {
    RestEntity entity = build().msg(msg);
    return entity.success(true).code(SUCCESS_STATUS);
  }

  public static RestEntity ok(Object data)
  {
    return ok().data(data);
  }

  private RestEntity data(Object data) {
    this.put("data",data);
    return this;
  }

  public static RestEntity error(String msg)
  {
    RestEntity entity = build().msg(msg);
    return entity.success(false).code(ERROR_STATUS);
  }

  public static RestEntity error()
  {
    RestEntity entity = build();
    return entity.success(false).code(ERROR_STATUS);
  }

  private static RestEntity build()
  {
    return new RestEntity();
  }


}