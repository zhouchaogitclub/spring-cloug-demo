package com.zc.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 周超
 * @since 2019-06-25 13:42
 */
@Component
public class TokenFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String token = request.getParameter("token");
		if (token == null) {
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(401);
			context.setResponseBody("unAuthrized");
			return null;
		}
		return null;
	}
}
