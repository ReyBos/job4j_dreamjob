package ru.job4j.dream.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sreq;
        ((HttpServletResponse) sresp).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) sresp).addHeader(
                "Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST"
        );
        HttpServletResponse resp = (HttpServletResponse) sresp;
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        chain.doFilter(request, sresp);
    }
}