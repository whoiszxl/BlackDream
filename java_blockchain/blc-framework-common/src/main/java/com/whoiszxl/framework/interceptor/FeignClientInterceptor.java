package com.whoiszxl.framework.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign拦截器
 * @author Administrator
 *
 */
public class FeignClientInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if(requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            //取出request中的header，找到jwt令牌向下传递
            Enumeration<String> headerNames = request.getHeaderNames();
            if(headerNames != null) {
                while(headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
                    requestTemplate.header(headerName, headerValue);
                }
            }
        }
    }

}
