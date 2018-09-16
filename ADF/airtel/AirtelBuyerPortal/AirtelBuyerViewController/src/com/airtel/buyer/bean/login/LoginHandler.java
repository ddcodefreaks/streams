package com.airtel.buyer.bean.login;

import com.airtel.buyer.utility.CommonConstants;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.util.ComponentReference;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

import weblogic.servlet.security.ServletAuthentication;

/**
 * The Class LoginHandler.
 */
public class LoginHandler implements Serializable {

    /** The Constant serialVersionUID. */
    @SuppressWarnings("compatibility:-2865817129706313376")
    private static final long serialVersionUID = 1L;

    /** The Constant _logger. */
    private static final ADFLogger _logger = ADFLogger.createADFLogger(LoginHandler.class);

    /** The username. */
    private String _username;

    /** The password. */
    private String _password;

    /** The show email warning. */
    private Boolean showEmailWarning = CommonConstants.BOOLEAN_FALSE;

    /** The show password warning. */
    private Boolean showPasswordWarning = CommonConstants.BOOLEAN_FALSE;

    /** The disable login btn. */
    private Boolean disableLoginBtn = CommonConstants.BOOLEAN_TRUE;

    /** The login btn. */
    private ComponentReference loginBtn;

    /**
     * Sets the disable login btn.
     *
     * @param disableLoginBtn the new disable login btn
     */
    public void setDisableLoginBtn(Boolean disableLoginBtn) {
        this.disableLoginBtn = disableLoginBtn;
    }


    /**
     * Gets the disable login btn.
     *
     * @return the disable login btn
     */
    public Boolean getDisableLoginBtn() {
        return disableLoginBtn;
    }


    /**
     * Sets the username.
     *
     * @param _username the new username
     */
    public void setUsername(String _username) {
        this._username = _username;
    }


    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return _username;
    }


    /**
     * Sets the password.
     *
     * @param _password the new password
     */
    public void setPassword(String _password) {
        this._password = _password;
    }


    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return _password;
    }


    /**
     * Do login.
     *
     * @param event the event
     */
    public void doLogin(ActionEvent event) {
        _logger.config("Inside LoginHandler ::: doLogin ::: @param actionEvent");
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {

            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            String test = request.getPathInfo();
            _logger.config("test : " + test);
            CallbackHandler handler = new URLCallbackHandler(_username, _password);
            Subject mySubject = Authentication.login(handler);
            ServletAuthentication.runAs(mySubject, request);
            ServletAuthentication.generateNewSessionID(request);
            String loginUrl =
                "/adfAuthentication?success_url=/faces/pages/buyerHome/home.jspx"; /* + ctx.getViewRoot().getViewId();*/
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            sendForward(request, response, loginUrl);
        } catch (FailedLoginException e) {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Username or Password",
                                 "An incorrect Username or Password" + " was specified");
            ctx.addMessage(null, msg);
        } catch (LoginException e) {
            reportUnexpectedLoginError("LoginException", e);
        } catch (Exception e) {
            reportUnexpectedLoginError("Exception", e);
        }
        _logger.config("Exiting from LoginHandler ::: doLogin ::: @param actionEvent");

    }

    /**
     * Send forward.
     *
     * @param request the request
     * @param response the response
     * @param forwardUrl the forward url
     */
    private void sendForward(HttpServletRequest request, HttpServletResponse response, String forwardUrl) {
        _logger.config("Inside LoginHandler ::: sendForward ::: @param request, @param response, @param ForwardUrl");
        FacesContext ctx = FacesContext.getCurrentInstance();
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardUrl);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException se) {
            _logger.severe("Exception raised :::" + se.getMessage(), se);
            reportUnexpectedLoginError("ServletException", se);
        } catch (IOException ie) {
            _logger.severe("Exception raised :::" + ie.getMessage(), ie);
            reportUnexpectedLoginError("IOException", ie);
        } catch (Exception e) {
            _logger.severe("Exception raised :::" + e.getMessage(), e);
            reportUnexpectedLoginError("Exception", e);
        }
        ctx.responseComplete();
        _logger.config("Exiting from LoginHandler ::: sendForward ::: @param request, @param response, @param ForwardUrl");

    }

    /**
     * Report unexpected login error.
     *
     * @param errType the err type
     * @param e the e
     */
    private void reportUnexpectedLoginError(String errType, Exception e) {
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unexpected error during login",
                             "Unexpected error during login (" + errType + "), please consult logs for detail");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        e.printStackTrace();
    }


    /**
     * Do logout.
     *
     * @param event the event
     */
    public void doLogout(ActionEvent event) {
        _logger.config("Inside LoginHandler ::: doLogout ::: @param actionEvent");
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fctx.getExternalContext().getSession(true);
            session.invalidate();
            fctx.getExternalContext()
                .redirect(fctx.getExternalContext().getApplicationContextPath() +
                          fctx.getExternalContext().getRequestServletPath() + "/pages/login/buyerLogin.jspx");
        } catch (IOException e) {
            _logger.severe("Exception raised ::: "+ e.getMessage(),e);
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+ e.getMessage(),e);
        }
        _logger.config("Exiting from LoginHandler ::: doLogout :: @param actionEvent");
    }


    /**
     * On email value change.
     *
     * @param valueChangeEvent the value change event
     */
    public void onEmailValueChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Inside LoginHandler ::: onEmailValueChange ::: @pram valueChangeEvent");

        try {
            _logger.config("---------Inside onEmailValueChange ----------------");
            String email = valueChangeEvent.getNewValue().toString();
            _logger.config("-----email value --:: " + email);
            _username = email;
            if (email != null && !email.equalsIgnoreCase("")) {
                showEmailWarning = CommonConstants.BOOLEAN_FALSE;
            } else {
                showEmailWarning = CommonConstants.BOOLEAN_TRUE;
            }
            refreshLoginBtn();
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }
        _logger.config("Exiting from LoginHandler ::: onEmailValueChange ::: @pram valueChangeEvent");

    }


    /**
     * Sets the login btn.
     *
     * @param loginBtn the new login btn
     */
    public void setLoginBtn(RichButton loginBtn) {
        this.loginBtn = ComponentReference.newUIComponentReference(loginBtn);
    }


    /**
     * Gets the login btn.
     *
     * @return the login btn
     */
    public RichButton getLoginBtn() {
        if (loginBtn != null) {
            return (RichButton) loginBtn.getComponent();
        }
        return null;
    }


    /**
     * On password value change.
     *
     * @param valueChangeEvent the value change event
     */
    public void onPasswordValueChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Inside LoginHandler ::: onPasswordValueChange ::: @pram valueChangeEvent");
        try {
            String password = valueChangeEvent.getNewValue().toString();
//            _logger.config("-----password value --:: " + password);
            _password = password;
            if (password != null && !password.equalsIgnoreCase("")) {
                showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
            } else {
                showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
            }
            refreshLoginBtn();
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }
        _logger.config("Exiting from LoginHandler ::: onPasswordValueChange ::: @pram valueChangeEvent");

    }


    /**
     * Refresh component.
     *
     * @param uiComp the ui comp
     */
    private void refreshComponent(UIComponent uiComp) {
        if (uiComp != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(uiComp); //refreshing ui components
        }
    }


    /**
     * Refresh login btn.
     */
    public void refreshLoginBtn() {
        _logger.config("Inside LoginHandler ::: refreshLoginBtn ");
        try {
            _logger.config("----FLAGS------");
            _logger.config("" + showEmailWarning);
            _logger.config("" + showPasswordWarning);
            _logger.config("----FLAG VALUES------");
            _logger.config(_username);
//            _logger.config(_password);
            _logger.config("----------");
            if (showEmailWarning || showPasswordWarning) {
                //when any error is visible ie true disble button
                disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
                _logger.config("button disabled");
            } else {
                //when all errors are not visible enable login button
                if (_username != null && !_username.isEmpty() && _password != null && !_password.isEmpty()) {
                    disableLoginBtn = CommonConstants.BOOLEAN_FALSE;
                    _logger.config("button enabled" + disableLoginBtn);
                } else {
                    disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
                    _logger.config("button disabled" + disableLoginBtn);
                }
            }
            refreshComponent(getLoginBtn());
        } catch (Exception e) {
            _logger.severe("Exception raised ::: "+e.getMessage(),e);
            e.printStackTrace();
        }
        _logger.config("Exiting from LoginHandler ::: refreshLoginBtn ");

    }
}
