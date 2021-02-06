package com;

import com.zc.entities.Dept;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouchao
 * @date 2019/5/25 13:07
 */
@RestController
@SpringBootApplication
@EnableEurekaClient
public class CloudDemoProvider {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoProvider.class, args);
    }

    @GetMapping("/get")
    public Dept index() {
        Dept dept = new Dept();
        dept.setDbSource(port);
        dept.setDeptNo(111111L);
        dept.setDName("hello world this is a demo");
        int a = 1 / 0;
        return dept;
    }

    @GetMapping("/get/{id}")
    public Dept findById(@PathVariable Integer id) {
        Dept dept = new Dept();
        dept.setDbSource(port);
        dept.setDeptNo(Long.valueOf(id));
        dept.setDName("from provider");
        return dept;
    }
}
