package com.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebServerLauncher {
  private static final Logger log = LoggerFactory.getLogger(WebServerLauncher.class);

  public static void main(String[] args) throws LifecycleException {
    String webappDirLocation = "webapp/";
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();

    tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
    log.info("configuring app with basedir : {}",new File("./"+webappDirLocation).getAbsolutePath());

    tomcat.start();
    tomcat.getServer().await();
  }
}
