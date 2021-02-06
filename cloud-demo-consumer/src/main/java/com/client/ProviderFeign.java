package com.client;

import com.fallback.ProviderFeignFallBackFactory;
import com.zc.entities.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 周超
 * @since 2020/11/26 21:58
 */
@FeignClient(value = "provider", fallbackFactory = ProviderFeignFallBackFactory.class
//        fallback = ProviderFeignFallBack.class
)
public interface ProviderFeign {
    @GetMapping("/get")
    Dept index();
    @GetMapping("/get/{id}")
    Dept findById(@PathVariable("id") Integer id);
}
