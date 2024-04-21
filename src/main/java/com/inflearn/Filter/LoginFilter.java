package com.inflearn.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFilter implements Filter {
    public LoginFilter() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpSession session = httpRequest.getSession();
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        if (session.getAttribute("customer") == null) {
            PrintWriter out = servletResponse.getWriter();
            out.println("<script>alert('로그인을 하세요');history.back();</script>");
            out.close();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}