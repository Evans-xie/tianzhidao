package common;

import com.github.pagehelper.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/24 18:25
 */
@Component
public class CorsFilter implements Filter {

    public static final String OPTIONS = "OPTIONS";
    public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    /**
     * 通过CORS技术实现AJAX跨域访问, 只要将CORS响应头写入response对象中即可
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (StringUtil.isNotEmpty(allowOrigin)) {
            //允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制；
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            //允许访问的请求方式，多个用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS；
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            //是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true；
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type,X-Token,timestamp
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            //允许客户端访问的服务端响应头，多个响应头用逗号分割。
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        if (isPreFlightRequest(httpServletRequest)) {
            response.setStatus(204);
            return;
        }
        chain.doFilter(req, res);
    }

    public boolean isCorsRequest(HttpServletRequest request) {
        return (request.getHeader(HttpHeaders.ORIGIN) != null);
    }

    public boolean isPreFlightRequest(HttpServletRequest request) {
        return (isCorsRequest(request) && OPTIONS.equals(request.getMethod())
                && request.getHeader(ACCESS_CONTROL_REQUEST_METHOD) != null);
    }
    @Override
    public void destroy() {

    }

}
