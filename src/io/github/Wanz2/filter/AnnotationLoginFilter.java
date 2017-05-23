package io.github.Wanz2.filter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 请求过滤器
 * Created by wuwenan on 10/05/2017.
 */
@WebFilter(
        //filter名字
        filterName = "AnnotationLoginFilter",
        //过滤器类型
        dispatcherTypes = DispatcherType.REQUEST,
        //需要过滤的url
        urlPatterns = "/*",
        //fiter初始化参数
        initParams = {
                //定义可以直接访问的资源
                @WebInitParam(name = "noLoginPaths",
                        value = "login.jsp;failure.jsp;AnnotationLoginServlet"),
                //配置请求编码字符集
                @WebInitParam(name = "charset",
                        value = "utf-8")
        }
)
public class AnnotationLoginFilter implements javax.servlet.Filter {
    //获取注解中设置的过滤器初始化参数
    private FilterConfig config;

    public void destroy() {
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        //观察参数类型，要先将ServletRequest和ServletResponse
        //转换成HttpServletRequest和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        //指定请求编码字符集，默认为utf-8
        String charset = config.getInitParameter("charset");
        if (charset == null) {
            charset = "utf-8";
        }
        request.setCharacterEncoding(charset);

        //获取初始化参数中可直接访问的资源名
        String[] noLoginPaths = config.getInitParameter("noLoginPaths").split(";");
        for (String p : noLoginPaths) {
            // 若所请求的资源是可以直接访问的，则直接放行
            if (request.getRequestURI().contains(p)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        //若已经正确输入用户名和密码，则放行
        if (session.getAttribute("username") != null) {
            chain.doFilter(req, resp);
        } else {
            //若所请求的资源不可直接访问，且未输入正确的用户名和密码，则重定向到登录页面
            response.sendRedirect("/login.jsp");
        }
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        //获取过滤器初始化配置
        this.config = config;
    }

}
