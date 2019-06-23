package com;

import com.zc.entities.Dept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouchao
 * @date 2019/5/25 13:07
 */
@RestController
@SpringBootApplication
public class CloudDemoProvider {

  public static void main(String[] args) {
    SpringApplication.run(CloudDemoProvider.class, args);
  }

  @GetMapping("/get")
  public Dept index() {
    Dept dept = new Dept();
    dept.setDbSource("100");
    dept.setDeptNo(111111L);
    dept.setDName("provider1");
    return dept;
  }
}
