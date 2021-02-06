package com;

import com.client.ProviderFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.zc.entities.Dept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouchao
 * @since 2019/5/25 13:05
 */
@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class CloudDemoConsumer {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule iRule() {
        return new RoundRobinRule();
    }

    @Resource
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(CloudDemoConsumer.class, args);
    }

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ProviderFeign providerFeign;

    @GetMapping("/consumer")
    public Dept index() {
        return providerFeign.index();
    }


    @GetMapping("/consumers")
    @HystrixCommand(fallbackMethod = "indexFallBack",commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")})
    public Dept getAll() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1100);
        return new Dept();
    }

    public Dept indexFallBack() {
        System.out.println(Thread.currentThread().getName());
        Dept dept = new Dept();
        dept.setDName("hystrix降级默认返回");
        dept.setDbSource("82034820");
        dept.setDeptNo(0L);
        return dept;
    }

    @GetMapping("/get/{id}")
    public Dept findById(@PathVariable Integer id) {
        System.out.println(Thread.currentThread().getName());
        return providerFeign.findById(id);
    }

    @GetMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstanceList(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
