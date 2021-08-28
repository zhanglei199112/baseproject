package com.emp.server;

import cn.hutool.core.util.StrUtil;
import com.emp.service.StreamService;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitServer implements ApplicationContextAware {

  @SneakyThrows
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    String ip = InetAddress.getLocalHost().getHostAddress();
    String httpPort = env.getProperty("server.port");
    String path = env.getProperty("server.servlet.context-path");
    if (StrUtil.isEmpty(path)) {
      path = "";
    }
    log.info("\n--------------------------------------------------------- \n" +
            "\t EasyMedia is running! Access address: \n" +
            "\t media port at : \t {} \n" +
            "\t http port at : \t {} \n" +
            "\t web Local: \t http://localhost:{} \n" +
            "\t web External: \t http://{}:{}{} \n" +
            "\t httpflv: \t http://{}:{}/live?url={您的源地址} \n" +
            "\t wsflv: \t ws://{}:{}/live?url={您的源地址} \n" +
            "\t hls(m3u8): \t http://{}:{}/hls?url={您的源地址} \n" +
            "\t h2-database: \t http://{}:{}/h2-console \n" +
            "--------------------------------------------------------- \n",
        port,
        httpPort,
        httpPort,
        ip, httpPort, path,
        ip, port,
        ip, port,
        ip, httpPort,
        ip, httpPort);
    mediaServer.start(new InetSocketAddress("0.0.0.0", port));
  }


  @Value("${mediaserver.port:9001}")
  private int port;

  @Autowired
  private MediaServer mediaServer;

  @Autowired
  private StreamService mediaService;

  @Autowired
  private Environment env;

}
