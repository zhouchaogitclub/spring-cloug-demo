package com.fallback;

import com.client.ProviderFeign;
import com.zc.entities.Dept;
import org.springframework.stereotype.Component;

/**
 * @author 周超
 * @since 2020/11/26 22:28
 */
@Component
public class ProviderFeignFallBack implements ProviderFeign {
    @Override
    public Dept index() {
        Dept dept = new Dept();
        dept.setDbSource("1");
        dept.setDeptNo(1L);
        dept.setDName("默认");
        return dept;
    }

    @Override
    public Dept findById(Integer id) {
        Dept dept = new Dept();
        dept.setDbSource("1");
        dept.setDeptNo(1L);
        dept.setDName("feign默认");
        return dept;
    }
}
