package com.airtel.supplierportal.filters;

import com.airtel.supplierportal.pojo.session.SupplierSession;

import com.airtel.supplierportal.utility.ADFUtils;

import java.io.IOException;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.share.logging.ADFLogger;

public class SupplierLoginFilter implements Filter {
    private FilterConfig _filterConfig = null;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(SupplierLoginFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
                                                                                                     ServletException {
        _logger.info("Inside Filter Class");
        String requestUrl = ((HttpServletRequest)request).getRequestURL().toString();
        _logger.info(requestUrl);
        String contextRoot = ((HttpServletRequest)request).getContextPath();
        _logger.info("Context Root:::"+contextRoot);
        String requestUri = ((HttpServletRequest)request).getRequestURI();
        _logger.info("Request URI :::"+requestUri );
        
        Map<String,String[]> params =request.getParameterMap();
        for(Map.Entry<String, String[]> entry : params.entrySet()){
            _logger.info(entry.getKey() + " = " + Arrays.toString(entry.getValue()));
        }
        HttpSession session = ((HttpServletRequest)request).getSession();
        SupplierSession supplierSessionObject=(SupplierSession) session.getAttribute("SupplierSessionObject");
                
        if(null !=supplierSessionObject){
            _logger.info("........session object found.....");
            //session exists
            if (requestUri.contains(".jspx") || requestUri.contains("/getDocument")) {
                chain.doFilter(request, response);
            } else {
                _logger.info(supplierSessionObject.getGoToPage());
                if (!supplierSessionObject.getGoToPage().equalsIgnoreCase("")) {
                    ((HttpServletResponse) response).sendRedirect(contextRoot + supplierSessionObject.getGoToPage());
                } else{
                    ((HttpServletResponse) response).sendRedirect(contextRoot + "/faces/pages/login/partnerLogin.jspx");
                }
            }
        }else{
            //session doesnot exist
            //route to login page
            _logger.info("........session object NOT found.....");
            if (isRequestedUrlProtected(requestUrl)) {
                _logger.info("........URL is PROTECTED.....");
                ((HttpServletResponse) response).sendRedirect(contextRoot + "/faces/pages/login/partnerLogin.jspx");
            } else{
                chain.doFilter(request, response);
            }
        }
    }
    
    private boolean isRequestedUrlProtected(String requestUrl){
            if(requestUrl != null && ( requestUrl.contains("partnerLogin.jspx") || requestUrl.contains("signup.jspx") || requestUrl.contains("supplierForgotPassword.jspx") || requestUrl.contains("/getDocument") ) )  {
                return false;
            }
            else{
                return true;
            }
        }
}
