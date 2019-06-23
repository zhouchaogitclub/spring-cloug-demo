package com;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.zc.entities.Dept;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhouchao
 * @date 2019/5/25 13:05
 */
@RestController
@SpringBootApplication
public class CloudDemoConsumer {

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public IRule iRule() {
    return new RandomRule();
  }

  @Autowired
  private DiscoveryClient discoveryClient;

  public static void main(String[] args) {
    SpringApplication.run(CloudDemoConsumer.class, args);
  }

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/index/{no}")
  public Dept index(@PathVariable long no) {
    return restTemplate.getForObject("http://EUREKA-PROVIDER/get/" + no, Dept.class);
  }

  @GetMapping("/service-instances/{applicationName}")
  public List<ServiceInstance> serviceInstanceList(@PathVariable String applicationName) {
    return this.discoveryClient.getInstances(applicationName);
  }
}
