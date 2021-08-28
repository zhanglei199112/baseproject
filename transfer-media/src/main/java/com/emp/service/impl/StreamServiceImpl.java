package com.emp.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.crypto.digest.MD5;
import com.emp.entity.Camera;
import com.emp.entity.ClientType;
import com.emp.service.StreamService;
import com.emp.transfer.MediaTransfer;
import com.emp.transfer.MediaTransferFlvByJavacv;
import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class StreamServiceImpl implements StreamService {

  public boolean playForApi(Camera camera) {
    // 区分不同媒体
    MediaTransfer mediaTransfer = cameras.get(camera.getMediaKey());
    if (null == mediaTransfer) {
      MediaTransferFlvByJavacv mediaConvert = new MediaTransferFlvByJavacv(camera);
      cameras.put(camera.getMediaKey(), mediaConvert);
      ThreadUtil.execute(mediaConvert);
    }
    mediaTransfer = cameras.get(camera.getMediaKey());
    if (mediaTransfer instanceof MediaTransferFlvByJavacv) {
      MediaTransferFlvByJavacv mediaft = (MediaTransferFlvByJavacv) mediaTransfer;
      // 30秒还没true认为启动不了
      for (int i = 0; i < 60; i++) {
        if (mediaft.isRunning() && mediaft.isGrabberStatus() && mediaft.isRecorderStatus()) {
          return true;
        }
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
        }
      }
    }
    return false;
  }

  /**
   * http-flv播放
   *
   * @param cameraDto
   * @param ctx
   */
  public void playForHttp(Camera cameraDto, ChannelHandlerContext ctx) {

    if (cameras.containsKey(cameraDto.getMediaKey())) {
      MediaTransfer mediaConvert = cameras.get(cameraDto.getMediaKey());
      if (mediaConvert instanceof MediaTransferFlvByJavacv) {
        MediaTransferFlvByJavacv mediaTransferFlvByJavacv = (MediaTransferFlvByJavacv) mediaConvert;
        //如果当前已经用ffmpeg，则重新拉流
        mediaTransferFlvByJavacv.addClient(ctx, ClientType.HTTP);
      }

    } else {
      MediaTransferFlvByJavacv mediaConvert = new MediaTransferFlvByJavacv(cameraDto);
      cameras.put(cameraDto.getMediaKey(), mediaConvert);
      ThreadUtil.execute(mediaConvert);
      mediaConvert.addClient(ctx, ClientType.HTTP);
    }
  }

  /**
   * ws-flv播放
   *
   * @param cameraDto
   * @param ctx
   */
  public void playForWs(Camera cameraDto, ChannelHandlerContext ctx) {

    if (cameras.containsKey(cameraDto.getMediaKey())) {
      MediaTransfer mediaConvert = cameras.get(cameraDto.getMediaKey());
      if (mediaConvert instanceof MediaTransferFlvByJavacv) {
        MediaTransferFlvByJavacv mediaTransferFlvByJavacv = (MediaTransferFlvByJavacv) mediaConvert;
        //如果当前已经用ffmpeg，则重新拉流
        mediaTransferFlvByJavacv.addClient(ctx, ClientType.WEBSOCKET);
      }
    } else {
      MediaTransferFlvByJavacv mediaConvert = new MediaTransferFlvByJavacv(cameraDto);
      cameras.put(cameraDto.getMediaKey(), mediaConvert);
      ThreadUtil.execute(mediaConvert);
      mediaConvert.addClient(ctx, ClientType.WEBSOCKET);
    }
  }

}
