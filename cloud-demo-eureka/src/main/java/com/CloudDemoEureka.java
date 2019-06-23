package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zhouchao
 * @date 2019/5/25 13:40
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudDemoEureka {

  public static void main(String[] args) {
    SpringApplication.run(CloudDemoEureka.class, args);
  }
}
