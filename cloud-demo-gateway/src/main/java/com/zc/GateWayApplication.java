package com.zc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author zhouchao
 * @date 2019/6/22 14:54
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class GateWayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GateWayApplication.class, args);
  }
}
