package com.zc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周超
 * @since 2019-06-25 13:42
 */
//@Component
public class TokenFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 数字越小约先执行
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        //是否应该被过滤,如果被过滤了但是后面还有过滤器，后面的过滤器还会执行,
        // sendZuulResponse没有设置时默认为true，如果前面有过滤器设置为了false，那么后面的过滤器将不会执行
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if (token == null) {
            // token校验失败，直接响应数据，但是后续的过滤器还会继续执行
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.setResponseBody(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return null;
        }
        // filter中的值在底层并没有被赋值，所以这里返回null就可以了
        return null;
    }
}
