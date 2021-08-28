package com.emp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Camera {

  private String id;
  /**
   * 播放地址
   */
  private String url;

  /**
   * 备注
   */
  private String remark;

  /**
   * 启用flv
   */
  private boolean enabledFlv = false;

  /**
   * 启用hls
   */
  private boolean enabledHls = false;

  /**
   * javacv/ffmpeg
   */
  private String mode = "未开启";

  /**
   * md5 key，媒体标识，区分不同媒体
   */
  private String mediaKey;

  /**
   * 网络超时 ffmpeg默认5秒，这里设置15秒
   */
  private String netTimeout = "15000000";

  /**
   * 读写超时，默认5秒
   */
  private String readOrWriteTimeout = "15000000";

  /**
   * 0网络流，1本地视频
   */
  private int type = 0;

  /**
   * 无人拉流观看是否自动关闭流
   */
  private boolean autoClose=true;

  /**
   * 无人拉流观看持续多久自动关闭，默认1分钟
   */
  private long noClientsDuration = 60000;

}
