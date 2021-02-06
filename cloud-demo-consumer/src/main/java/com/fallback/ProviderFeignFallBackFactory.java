package com.fallback;

import com.client.ProviderFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 周超
 * @since 2020/11/26 22:36
 */
@Component
public class ProviderFeignFallBackFactory implements FallbackFactory<ProviderFeign> {
    @Resource
    private ProviderFeignFallBack providerFeignFallBack;
    @Override
    public ProviderFeign create(Throwable throwable) {
        throwable.printStackTrace();
        return providerFeignFallBack;
    }
}
