package com.airtel.buyer.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;

public class BuyerLoginFilter implements Filter {
    private FilterConfig _filterConfig = null;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(BuyerLoginFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                                     ServletException {
        _logger.info("Inside Filter Class");

        String requestUrl = ((HttpServletRequest) request).getRequestURL().toString();
        _logger.info("Request URL:::" + requestUrl);
        String contextRoot = ((HttpServletRequest) request).getContextPath();
        _logger.info("Context Root:::" + contextRoot);

        String requestUri = ((HttpServletRequest) request).getRequestURI();
        _logger.info("Request URI :::" + requestUri);

        if (((HttpServletRequest) request).getUserPrincipal() != null) {
            String loggedInUser = ((HttpServletRequest) request).getUserPrincipal().getName();
            _logger.info("LoggedInUser:::" + loggedInUser);
            if (requestUri.contains(".jspx") || requestUri.contains("/getDocument")) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(contextRoot + "/faces/pages/buyerHome/home.jspx");
            }
        } else {
            if (isRequestedUrlProtected(requestUrl)) {
                _logger.info("........URL is PROTECTED.....");
                ((HttpServletResponse) response).sendRedirect(contextRoot + "/faces/pages/login/buyerLogin.jspx");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    private boolean isRequestedUrlProtected(String requestUrl) {
        if (requestUrl != null && (requestUrl.contains("buyerLogin.jspx"))) {
            return false;
        } else {
            return true;
        }
    }
}