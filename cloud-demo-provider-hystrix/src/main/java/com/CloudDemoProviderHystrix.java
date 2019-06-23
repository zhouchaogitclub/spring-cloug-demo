package com;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zc.entities.Dept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouchao
 * @date 2019/5/26 10:46
 */
@RestController
@SpringBootApplication
@EnableCircuitBreaker
public class CloudDemoProviderHystrix {

  public static void main(String[] args) {
    SpringApplication.run(CloudDemoProviderHystrix.class, args);
  }

  @GetMapping("/get/{no}")
  @HystrixCommand(fallbackMethod = "processHystrix")
  public Dept index(@PathVariable long no) {
    Dept dept = new Dept();
    if (no > 100) {
      throw new RuntimeException();
    }
    dept.setDbSource("100");
    dept.setDeptNo(no);
    dept.setDName("provider-hystrix");
    return dept;
  }

  public Dept processHystrix(@PathVariable long no) {
    Dept dept = new Dept();
    dept.setDbSource("没有找到该编号");
    dept.setDeptNo(no);
    dept.setDName("provider-hystrix");
    return dept;
  }
}
