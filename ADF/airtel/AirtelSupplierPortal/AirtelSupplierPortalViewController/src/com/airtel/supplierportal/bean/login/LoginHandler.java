package com.airtel.supplierportal.bean.login;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.share.logging.ADFLogger;

public class LoginHandler {
    private static final ADFLogger _logger = ADFLogger.createADFLogger(LoginHandler.class);
    
   /**
     *
     * @param actionEvent
     */

    public void doLogout(ActionEvent actionEvent) {
        
        _logger.config("Entering into logout method ");
        FacesContext fctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fctx.getExternalContext().getSession(true);
        session.invalidate();
        try {
            fctx.getExternalContext()
                .redirect(fctx.getExternalContext().getApplicationContextPath() +
                          fctx.getExternalContext().getRequestServletPath() + "/pages/login/partnerLogin.jspx");
        } catch (IOException e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        
        _logger.config("Exit into logout method ");
    }

}
