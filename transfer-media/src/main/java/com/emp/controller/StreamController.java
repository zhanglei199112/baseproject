package com.emp.controller;

import cn.hutool.crypto.digest.MD5;
import com.emp.entity.Camera;
import com.emp.entity.RestEntity;
import com.emp.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreamController {

  @Autowired
  private StreamService streamService;

  /**
   * 开始推流
   * @param
   * @return
   */
  @RequestMapping("start")
  public RestEntity start(String url) {
    String digestHex = MD5.create().digestHex(url);
    Camera camera = new Camera();
    camera.setUrl(url);
    camera.setMediaKey(digestHex);
    camera.setEnabledFlv(true);
    boolean playForApi = streamService.playForApi(camera);
    return playForApi ? RestEntity.ok("开启推流成功") : RestEntity.error("开启失败");
  }
}
