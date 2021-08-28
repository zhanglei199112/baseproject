package com.emp.service;

import com.emp.entity.Camera;
import com.emp.transfer.MediaTransfer;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentHashMap;

public interface StreamService {

  /**
   * 缓存流转换线程
   */
  public static ConcurrentHashMap<String, MediaTransfer> cameras = new ConcurrentHashMap<>();

  boolean playForApi(Camera camera);

  void playForHttp(Camera cameraDto, ChannelHandlerContext ctx);

  void playForWs(Camera cameraDto, ChannelHandlerContext ctx);
}
