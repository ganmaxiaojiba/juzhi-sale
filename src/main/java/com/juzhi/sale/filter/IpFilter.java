package com.juzhi.sale.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gschen on 5/3/14.
 */
public class IpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

//        filterConfig.getInitParameter("");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("----------->" + request.getRemoteAddr());

        String ip = request.getRemoteAddr();

        //TODO: forbidden some ip address
//        if(ip.equals("192.168.1.4")){
//            ((HttpServletResponse)response).sendRedirect("/404");
//            return ;
//        }



        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
