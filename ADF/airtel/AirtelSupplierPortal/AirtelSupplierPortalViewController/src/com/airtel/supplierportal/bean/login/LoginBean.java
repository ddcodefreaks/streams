package com.airtel.supplierportal.bean.login;

import com.airtel.supplierportal.am.SupplierPortalAMImpl;
import com.airtel.supplierportal.pojo.session.SupplierSession;
import com.airtel.supplierportal.utility.AesUtil;
import com.airtel.supplierportal.utility.CommonConstants;
import com.airtel.supplierportal.utility.EncryptDecryptUtil;
import com.airtel.supplierportal.utility.JSFUtils;
import com.airtel.supplierportal.utility.SmsUtil;
import com.airtel.supplierportal.utility.SupplierUtils;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;

import oracle.adf.controller.ControllerContext;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class LoginBean implements Serializable {
    @SuppressWarnings("compatibility:5328463821103160446")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(LoginBean.class);
    private ComponentReference loginBtn;
    private Boolean showEmailWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showCaptchaWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showOTPWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
    private Boolean disableMobileLoginBtn = CommonConstants.BOOLEAN_TRUE;
    private Boolean disableOTP = CommonConstants.BOOLEAN_TRUE;
    private String emailWarningMsg = "";
    private String passwordWarningMsg = "";
    private String captchaWarningMsg = "";
    private String mobNoWarningMsg = "";
    private String otpWarningMsg = "";
    private String _email;
    private String _password;
    private String _captcha;
    private String _mobileNo;
    private String _otp;
    private String _generatedOTP;
    private Boolean isEmailLogin = CommonConstants.BOOLEAN_TRUE;
    private ComponentReference loginMobileBtn;
    private ComponentReference notificationPGL;
    private ComponentReference notificationPGLEmailLogIn;
    private String notificationHeader;
    private String notificationBody;
    private Boolean showNotification = CommonConstants.BOOLEAN_FALSE;
    private Integer captchaCount = 0;
    private ComponentReference captchImg;

    /**
     *
     * @param captchaCount
     */
    public void setCaptchaCount(Integer captchaCount) {
        this.captchaCount = captchaCount;
    }

    /**
     *
     * @return
     */
    public Integer getCaptchaCount() {
        return captchaCount;
    }

    /**
     *
     * @param notificationPGLEmailLogIn
     */
    public void setNotificationPGLEmailLogIn(RichPanelGroupLayout notificationPGLEmailLogIn) {
        this.notificationPGLEmailLogIn = ComponentReference.newUIComponentReference(notificationPGLEmailLogIn);
    }

    /**
     *
     * @return
     */
    public RichPanelGroupLayout getNotificationPGLEmailLogIn() {
        if (notificationPGLEmailLogIn != null) {
            return (RichPanelGroupLayout) notificationPGLEmailLogIn.getComponent();
        }
        return null;
    }

    /**
     *
     * @param notificationHeader
     */
    public void setNotificationHeader(String notificationHeader) {
        this.notificationHeader = notificationHeader;
    }

    /**
     *
     * @return
     */
    public String getNotificationHeader() {
        return notificationHeader;
    }

    /**
     *
     * @param notificationBody
     */
    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }

    /**
     *
     * @return
     */
    public String getNotificationBody() {
        return notificationBody;
    }

    /**
     * Method called on clicking Signup link from Email Login Jsff and Mobile Login Jsff
     * @return
     */
    public String onSignupClick() {
        _logger.config("Entering Method onSignupClick");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                          "/pages/partnerSignup/signup.jspx");
        } catch (IOException e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit Method onSignupClick");
        return null;
    }

    /**
     * Mthod called on clicking Forgot Password link
     * @return
     */
    public String onForgotpasswordClick() {
        _logger.config("Entering Method onForgotpasswordClick");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                          "/pages/supplierForgotPassword/supplierForgotPassword.jspx");
        } catch (IOException e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit Method onForgotpasswordClick");
        return null;
    }

    /**
     * Method called on radio button for email and mobile on login page
     * @param valueChangeEvent
     */
    public void onRadioValueChangeListner(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering Method onRadioValueChangeListner");
        try {
            String listValue = valueChangeEvent.getNewValue().toString();
            FacesContext fctx = FacesContext.getCurrentInstance();
            ControllerContext ccontext = ControllerContext.getInstance();
            String viewId = "partnerLoginMobile";

            if (listValue.equals("mobile")) {
                isEmailLogin = CommonConstants.BOOLEAN_FALSE;
                _logger.info(ccontext.getCurrentViewPort().getViewId());
                //            ccontext.getCurrentViewPort().setViewId(viewId);
                NavigationHandler nh = fctx.getApplication().getNavigationHandler();
                nh.handleNavigation(fctx, null, "mobile");

                showNotification = CommonConstants.BOOLEAN_FALSE;
                refreshComponent(getNotificationPGL());
                _email = null;
                _password = null;
                _captcha = null;
                _mobileNo = null;
                _otp = null;
                setCaptchaWarningMsg(null);
                setOtpWarningMsg(null);
                setPasswordWarningMsg(null);
                setOtpWarningMsg(null);
                setMobNoWarningMsg(null);
                setEmailWarningMsg(null);
                showCaptchaWarning = CommonConstants.BOOLEAN_FALSE;
                showEmailWarning = CommonConstants.BOOLEAN_FALSE;
                showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
                showOTPWarning = CommonConstants.BOOLEAN_FALSE;
                showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
            } else if (listValue.equals("email")) {
                isEmailLogin = CommonConstants.BOOLEAN_TRUE;
                NavigationHandler nh = fctx.getApplication().getNavigationHandler();
                nh.handleNavigation(fctx, null, "email");
                showNotification = CommonConstants.BOOLEAN_FALSE;
                refreshComponent(getNotificationPGL());
                _email = null;
                _password = null;
                _captcha = null;
                _mobileNo = null;
                _otp = null;
                setCaptchaWarningMsg(null);
                setOtpWarningMsg(null);
                setPasswordWarningMsg(null);
                setOtpWarningMsg(null);
                setMobNoWarningMsg(null);
                setEmailWarningMsg(null);
                showCaptchaWarning = CommonConstants.BOOLEAN_FALSE;
                showEmailWarning = CommonConstants.BOOLEAN_FALSE;
                showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
                showOTPWarning = CommonConstants.BOOLEAN_FALSE;
                showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit Method onRadioValueChangeListner");
    }

    /**
     * Method called on load of loginBtf Taskflow
     */
    public void onloadLogin() {
        _logger.config("Entering Method onloadLogin");
        try {
            this.checkAndActivateUser();
            this.checkAndResetPassword();
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit Method onloadLogin");
    }

    /**
     * Helper Method called from onloadLogin method.
     * This method sets success and failure message on page
     */
    public void checkAndResetPassword() {
        _logger.config("Entering method checkAndResetPassword");
        try {
            String resetPassStatusParamVal =
                JSFUtils.resolveExpression("#{param.rp}") != null ?
                JSFUtils.resolveExpression("#{param.rp}").toString() : null;
            _logger.info("rp Paramter value : : " + resetPassStatusParamVal);

            if (null != resetPassStatusParamVal && resetPassStatusParamVal.equalsIgnoreCase("success")) {
                setNotificationHeader("Reset Password Successful");
                setNotificationBody("Password Reset Successfully. Please login to continue.");
                showNotification = CommonConstants.BOOLEAN_TRUE;
            } else if (null != resetPassStatusParamVal && resetPassStatusParamVal.equalsIgnoreCase("fail")) {
                setNotificationHeader("Reset Password Failed");
                setNotificationBody("Password Reset Failed. Please try again.");
                showNotification = CommonConstants.BOOLEAN_TRUE;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method checkAndResetPassword");
    }

    /**
     * Helper Method called from onloadLogin method.
     * This method will activate the newly signed up user.
     * This method will execute when user clicks link sent in email during signup.
     */
    public void checkAndActivateUser() {
        _logger.config("Entering method checkAndActivateUser");
        String activationCode =
            JSFUtils.resolveExpression("#{param.actCode}") != null ?
            JSFUtils.resolveExpression("#{param.actCode}").toString() : null;
        _logger.info("activationCode Paramter value : : " + activationCode);
        if (null != activationCode) {

            //            activationCode = EncryptDecryptUtil.decrypt(activationCode);
            _logger.info("Activation Code :: " + activationCode);


            // NEED TO PARSE THJE ACTIVATION CODE TO GET PARTNER USER ID


            /*
             * 1011~2018~JULY~13~14~12~58
             * splitStrArr[0] = partnerUserID
         * splitStrArr[1] = year
         * splitStrArr[2] = month
         * splitStrArr[3] = date
         * splitStrArr[4] = hour
         * splitStrArr[5] = minute
         * splitStrArr[6] = second
         *
         */
            //            String[] splitStrArr = activationCode.split("~");
            //            if (splitStrArr.length == 7) {
            //                String partnerUserId = splitStrArr[0];
            String partnerUserId = activationCode;
            //                Integer year = Integer.parseInt(splitStrArr[1]);
            //                String month = splitStrArr[2];
            //                Integer date = Integer.parseInt(splitStrArr[3]);
            //                Integer hour = Integer.parseInt(splitStrArr[4]);
            //                Integer minute = Integer.parseInt(splitStrArr[5]);
            //                Integer second = Integer.parseInt(splitStrArr[6]);
            //                LocalDateTime activationExpiryObj =
            //                    LocalDateTime.of(year, Month.valueOf(month), date, hour, minute, second).plusHours(2);
            //                LocalDateTime currentDTObj = LocalDateTime.now();
            //                _logger.info(activationExpiryObj);
            //                _logger.info(currentDTObj);
            //                _logger.info(currentDTObj.isBefore(activationExpiryObj));
            //                if (currentDTObj.isBefore(activationExpiryObj)) {
            //TODO activation code is valid
            //check if partner user id exists
            //if it exists call procedure to transfer record from TXN to Main Partner User table
            try {
                _logger.info("Activating user...");
                FacesContext fc = FacesContext.getCurrentInstance();
                Application app = fc.getApplication();
                ExpressionFactory elFactory = app.getExpressionFactory();
                ELContext elContext = fc.getELContext();
                ValueExpression valueExp =
                    elFactory.createValueExpression(elContext, "#{data.SupplierPortalAMDataControl.dataProvider}",
                                                    Object.class);
                SupplierPortalAMImpl ami = (SupplierPortalAMImpl) valueExp.getValue(elContext);
                //                SupplierPortalAMImpl ami =
                //                    (SupplierPortalAMImpl) ADFUtils.getApplicationModuleForDataControl("SupplierPortalAMDataControl");
                _logger.info("----PARTNER_USER_ID---");
                _logger.info(Integer.parseInt(partnerUserId) + "");
                ami.onActivation(Integer.parseInt(partnerUserId));
                setNotificationHeader("Account Activation Successful");
                setNotificationBody("Email Verified Successfully. Please login to continue.");
                showNotification = CommonConstants.BOOLEAN_TRUE;
            } catch (Exception e) {
                _logger.severe("Exception raised:::" + e.getMessage());
                e.printStackTrace();

            }
            //                } else {
            //                    TODO show error message on login page
            //                    _logger.info("("Activation code is invalid.......");
            //                }
            //            }

        }
        _logger.config("Exit method checkAndActivateUser");
    }

    /**
     *
     * @param _generatedOTP
     */
    public void setGeneratedOTP(String _generatedOTP) {
        this._generatedOTP = _generatedOTP;
    }

    /**
     *
     * @return
     */
    public String getGeneratedOTP() {
        return _generatedOTP;
    }

    /**
     *
     * @param _captcha
     */
    public void setCaptcha(String _captcha) {
        this._captcha = _captcha;
    }

    /**
     *
     * @return
     */
    public String getCaptcha() {
        return _captcha;
    }

    /**
     *
     * @param showEmailWarning
     */
    public void setShowEmailWarning(Boolean showEmailWarning) {
        this.showEmailWarning = showEmailWarning;
    }

    /**
     *
     * @return
     */
    public Boolean getShowEmailWarning() {
        return showEmailWarning;
    }

    /**
     *
     * @param showPasswordWarning
     */
    public void setShowPasswordWarning(Boolean showPasswordWarning) {
        this.showPasswordWarning = showPasswordWarning;
    }

    /**
     *
     * @return
     */
    public Boolean getShowPasswordWarning() {
        return showPasswordWarning;
    }

    /**
     *
     * @param showCaptchaWarning
     */
    public void setShowCaptchaWarning(Boolean showCaptchaWarning) {
        this.showCaptchaWarning = showCaptchaWarning;
    }

    /**
     *
     * @return
     */
    public Boolean getShowCaptchaWarning() {
        return showCaptchaWarning;
    }

    /**
     *
     * @param showMobNoWarning
     */
    public void setShowMobNoWarning(Boolean showMobNoWarning) {
        this.showMobNoWarning = showMobNoWarning;
    }

    /**
     *
     * @return
     */
    public Boolean getShowMobNoWarning() {
        return showMobNoWarning;
    }

    /**
     *
     * @param showOTPWarning
     */
    public void setShowOTPWarning(Boolean showOTPWarning) {
        this.showOTPWarning = showOTPWarning;
    }

    /**
     *
     * @return
     */
    public Boolean getShowOTPWarning() {
        return showOTPWarning;
    }

    /**
     *
     * @param emailWarningMsg
     */
    public void setEmailWarningMsg(String emailWarningMsg) {
        this.emailWarningMsg = emailWarningMsg;
    }

    /**
     *
     * @return
     */
    public String getEmailWarningMsg() {
        return emailWarningMsg;
    }

    /**
     *
     * @param passwordWarningMsg
     */
    public void setPasswordWarningMsg(String passwordWarningMsg) {
        this.passwordWarningMsg = passwordWarningMsg;
    }

    /**
     *
     * @return
     */
    public String getPasswordWarningMsg() {
        return passwordWarningMsg;
    }

    /**
     *
     * @param captchaWarningMsg
     */
    public void setCaptchaWarningMsg(String captchaWarningMsg) {
        this.captchaWarningMsg = captchaWarningMsg;
    }

    /**
     *
     * @return
     */
    public String getCaptchaWarningMsg() {
        return captchaWarningMsg;
    }

    /**
     *
     * @param mobNoWarningMsg
     */
    public void setMobNoWarningMsg(String mobNoWarningMsg) {
        this.mobNoWarningMsg = mobNoWarningMsg;
    }

    /**
     *
     * @return
     */
    public String getMobNoWarningMsg() {
        return mobNoWarningMsg;
    }

    /**
     *
     * @param otpWarningMsg
     */
    public void setOtpWarningMsg(String otpWarningMsg) {
        this.otpWarningMsg = otpWarningMsg;
    }

    /**
     *
     * @return
     */
    public String getOtpWarningMsg() {
        return otpWarningMsg;
    }

    /**
     * Action Listener Method called on Login button click
     * @param actionEvent
     * @return
     */
    public String onLoginButonClickAL(ActionEvent actionEvent) {
        _logger.config("Entering method onLoginButonClickAL");
        _logger.info("----Email----" + _email);
        //        _logger.info("----password----" + _password);
        _logger.info("----captch----" + _captcha);
        try {
            if (_email == null) {
                showEmailWarning = CommonConstants.BOOLEAN_TRUE;
                setEmailWarningMsg("Invalid Email");
            } else if (_email.isEmpty()) {
                showEmailWarning = CommonConstants.BOOLEAN_TRUE;
                setEmailWarningMsg("Invalid Email");
            }

            if (_password == null) {
                showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
                setPasswordWarningMsg("Invalid Password");
            } else if (_password.isEmpty()) {
                showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
                setPasswordWarningMsg("Invalid Password");
            }

            if (_captcha == null) {
                showCaptchaWarning = CommonConstants.BOOLEAN_TRUE;
                setCaptchaWarningMsg("Invalid Captcha");
            } else if (_captcha.isEmpty()) {
                showCaptchaWarning = CommonConstants.BOOLEAN_TRUE;
                setCaptchaWarningMsg("Invalid Password");
            }

            if (!showEmailWarning && !showPasswordWarning && !showCaptchaWarning) {
                UIComponent loginformPGL = actionEvent.getComponent()
                                                      .getParent()
                                                      .getParent();
                this.doLogin(loginformPGL);
                refreshComponent(actionEvent.getComponent()
                                            .getParent()
                                            .getParent());
            } else {
                refreshComponent(actionEvent.getComponent()
                                            .getParent()
                                            .getParent());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onLoginButonClickAL");
        return null;
    }

    public String onMobileLoginButonClickAL(ActionEvent actionEvent) {
        _logger.config("Entering method onMobileLoginButonClickAL");
        _logger.info("----Email----" + _email);
        //        _logger.info("----password----" + _password);
        _logger.info("----captch----" + _captcha);
        try {
            if (_mobileNo == null) {
                showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                setMobNoWarningMsg("Invalid Mobile Number");
            } else if (_mobileNo.isEmpty()) {
                showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                setMobNoWarningMsg("Invalid Mobile Number");
            }

            if (_otp == null) {
                showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                setOtpWarningMsg("Invalid OTP");
            } else if (_otp.isEmpty()) {
                showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                setOtpWarningMsg("Invalid OTP");
            }

            if (_captcha == null) {
                showCaptchaWarning = CommonConstants.BOOLEAN_TRUE;
                setCaptchaWarningMsg("Invalid Captcha");
            } else if (_captcha.isEmpty()) {
                showCaptchaWarning = CommonConstants.BOOLEAN_TRUE;
                setCaptchaWarningMsg("Invalid Captcha");
            }


            if (!showMobNoWarning && !showOTPWarning && !showCaptchaWarning) {
                UIComponent loginformPGL = actionEvent.getComponent()
                                                      .getParent()
                                                      .getParent();
                this.doLoginMobile(loginformPGL);
                refreshComponent(actionEvent.getComponent()
                                            .getParent()
                                            .getParent());

            } else {
                refreshComponent(actionEvent.getComponent()
                                            .getParent()
                                            .getParent());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onMobileLoginButonClickAL");
        return null;
    }

    /**
     * Helper method to verify user entered Captcha String
     * Will return true if the user enterd string matches with Captcha image displayed
     * Will return false if the user enterd string dosen't matches with Captcha image displayed
     */
    public boolean verifyAnswer(String answer) {
        _logger.info(":::verifyAnswer:::: Method Entry");
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
        Captcha captcha = (Captcha) ectx.getSessionMap().get(Captcha.NAME);
        boolean validationFlag = CommonConstants.BOOLEAN_FALSE;
        try {
            request.setCharacterEncoding(CommonConstants.UTF8);
        } catch (UnsupportedEncodingException e) {
            _logger.info(":::verifyAnswer::::Exception ::===> UTF not supported !" + e.getMessage());
        }
        if (answer != null && captcha.isCorrect(answer)) {
            validationFlag = CommonConstants.BOOLEAN_TRUE;
        }
        _logger.config("Exit method verifyAnswer");
        return validationFlag;
    }

    /**
     *
     * @param _email
     */
    public void setEmail(String _email) {
        this._email = _email;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return _email;
    }

    /**
     *
     * @param _password
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return _password;
    }

    /**
     *value change listener method to check email format validation on login page
     * @param valueChangeEvent
     */

    public void onEmailVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Inside onEmailVC");
        try {
            String email = valueChangeEvent.getNewValue().toString();
            _email = email;
            _logger.info(email);
            if (email != null && !email.equalsIgnoreCase("")) {
                String emailExp = CommonConstants.EMAIL_FORMAT_REGEX;
                Pattern emailPattern = Pattern.compile(emailExp);
                Matcher emailMatcher = emailPattern.matcher(email);
                _logger.info("Email Regex::" + emailMatcher.matches());
                if (emailMatcher.matches()) {
                    showEmailWarning = CommonConstants.BOOLEAN_FALSE;
                } else {
                    showEmailWarning = CommonConstants.BOOLEAN_TRUE;
                    setEmailWarningMsg("Invalid Email");
                }
                refreshComponent(valueChangeEvent.getComponent().getParent());
                _logger.info("Before login button refresh method call :::::----" + _email);
                refreshLoginBtn();
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onEmailVC");
    }


    /**
     *value change listener method to check password validation on login page
     * @param valueChangeEvent
     */
    public void onPasswordVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Inside onPasswordVC");
        try {
            String pass = valueChangeEvent.getNewValue().toString();

            //            _logger.info(pass);
            _password = pass;
            if (pass != null && !pass.equalsIgnoreCase("")) {
                if (pass.length() >= 8) {
                    showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
                } else {
                    showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
                    setPasswordWarningMsg("Password must be minimum of 8 characters");
                }
            } else {
                showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
                setPasswordWarningMsg("Please enter password");
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
            refreshLoginBtn();
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onPasswordVC");
    }

    /**
     *value change listner to check captch validation and disable/enable login button accordingly
     * @param valueChangeEvent
     */

    public void onCaptchaVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Inside onCaptchaVC");
        try {
            String capt = valueChangeEvent.getNewValue().toString();
            _captcha = capt;
            _logger.info(capt);
            if (capt != null && !capt.equalsIgnoreCase("")) {
                Boolean isCaptchaValid = verifyAnswer(capt);
                _logger.info("Captcha is valid :::" + isCaptchaValid);
                if (isCaptchaValid) {
                    showCaptchaWarning = CommonConstants.BOOLEAN_FALSE;
                } else {
                    showCaptchaWarning = CommonConstants.BOOLEAN_TRUE;
                    setCaptchaWarningMsg("Invalid Captcha");
                }
                refreshComponent(valueChangeEvent.getComponent().getParent());
                refreshLoginBtn();
            } else {
                disableMobileLoginBtn = CommonConstants.BOOLEAN_TRUE;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onCaptchaVC");
    }

    /**
     * value change listner method to check Mobile number validation on login using mobile
     * and enbale/disable login button accordingly
     * @param valueChangeEvent
     */

    public void onMobileNoVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Inside onMobileNoVC");
        try {
            String mobNo = valueChangeEvent.getNewValue().toString();
            _logger.info(mobNo);
            if (mobNo != null && !mobNo.equalsIgnoreCase("")) {
                String mobileExp = CommonConstants.MOBILE_NUMBER_REGEX;
                Pattern mobilePattern = Pattern.compile(mobileExp);
                Matcher mobileMatcher = mobilePattern.matcher(mobNo);
                _logger.info("Mobile Regex::" + mobileMatcher.matches());
                if (mobileMatcher.matches()) {
                    showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
                } else {
                    showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                    setMobNoWarningMsg("Invalid Mobile Number");
                }
                refreshComponent(valueChangeEvent.getComponent()
                                                 .getParent()
                                                 .getParent()
                                                 .getParent());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onMobileNoVC");

    }

    /**
     * value change listner method to check OPT should not be empty
     *
     * @param valueChangeEvent
     */

    public void onOtpVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Inside onOtpVC");
        try {
            String otp = valueChangeEvent.getNewValue().toString();
            _otp = otp;
            if (otp != null && !otp.equalsIgnoreCase("")) {
                showOTPWarning = CommonConstants.BOOLEAN_FALSE;

            } else {
                showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                setOtpWarningMsg("Please enter OTP");
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
            refreshLoginBtn();
            //        if (pass != null && !pass.equalsIgnoreCase("")) {
            //            showPasswordWarning = CommonConstants.BOOLEAN_FALSE;
            //        } else {
            //            showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
            //            setPasswordWarningMsg("Please enter password");
            //        }
            //        refreshComponent(valueChangeEvent.getComponent().getParent());
            //        refreshLoginBtn();
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method onOtpVC");
    }

    /**
     * helper method to refresh login button so,
     * that it can be enable or disabled according to bussiness rule
     *
     */

    public void refreshLoginBtn() {
        _logger.config("Entering method refreshLoginBtn");

        _logger.info("Is User Login with EMAIL???" + isEmailLogin);
        _logger.info("----FLAGS------");
        _logger.info("Email Warning Flag::" + showEmailWarning);
        _logger.info("Passwd Warning Flag::" + showPasswordWarning);
        _logger.info("MobNo Warning Flag::" + showMobNoWarning);
        _logger.info("Otp Warning Flag::" + showOTPWarning);
        _logger.info("Captch Warning Flag::" + showCaptchaWarning);
        _logger.info("----FLAG VALUES------");
        _logger.info(_email);
        _logger.info(_password);
        _logger.info(_mobileNo);
        _logger.info(_otp);
        _logger.info(_captcha);
        _logger.info("----------");
        try {
            if (isEmailLogin) {
                if (showEmailWarning || showPasswordWarning || showCaptchaWarning) {
                    //when any error is visible ie true disble button
                    disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
                    _logger.info("button disabled");
                } else {
                    //when all errors are not visible enable login button
                    if (_email != null && !_email.isEmpty() && _password != null && !_password.isEmpty() &&
                        _captcha != null && !_captcha.isEmpty()) {
                        disableLoginBtn = CommonConstants.BOOLEAN_FALSE;
                        _logger.info("button enabled" + disableLoginBtn);
                    } else {
                        disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
                        _logger.info("button disabled" + disableLoginBtn);
                    }
                }
                refreshComponent(getLoginBtn());
            } else {
                if (showMobNoWarning || showOTPWarning || showCaptchaWarning) {
                    //when any error is visible ie true disble button
                    disableMobileLoginBtn = CommonConstants.BOOLEAN_TRUE;
                    _logger.info("mobile button disabled");
                } else {
                    if (_mobileNo != null && !_mobileNo.isEmpty() && _otp != null && !_otp.isEmpty() &&
                        _captcha != null && !_captcha.isEmpty()) {
                        disableMobileLoginBtn = CommonConstants.BOOLEAN_FALSE;
                        _logger.info("mobile button enabled");
                    } else {
                        disableMobileLoginBtn = CommonConstants.BOOLEAN_TRUE;
                        _logger.info("mobile button disabled");
                    }
                }
                refreshComponent(getLoginMobileBtn());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method refreshLoginBtn");
    }

    /**
     * code for refreshing the ui component
     * @param uiComp
     */
    private void refreshComponent(UIComponent uiComp) {
        _logger.config("Entering method refreshComponent");
        if (uiComp != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(uiComp); //refreshing ui components
        }
        _logger.config("Exit method refreshComponent");
    }


    /**
     *
     * @param _mobileNo
     */

    public void setMobileNo(String _mobileNo) {
        this._mobileNo = _mobileNo;
    }

    /**
     *
     * @return
     */
    public String getMobileNo() {
        return _mobileNo;
    }

    /**
     *
     * @param _otp
     */
    public void setOtp(String _otp) {
        this._otp = _otp;
    }

    /**
     *
     * @return
     */
    public String getOtp() {
        return _otp;
    }

    /**
     *
     * @param disableOTP
     */
    public void setDisableOTP(Boolean disableOTP) {
        this.disableOTP = disableOTP;
    }

    /**
     *
     * @return
     */
    public Boolean getDisableOTP() {
        return disableOTP;
    }


    /**
     *
     * @param loginformPGL
     */
    public void doLogin(UIComponent loginformPGL) {
        _logger.config("Inside doLogin method");
        //        _logger.config(_password);//encryoted password

        try {

            //decrypt password
            String decPass = decryptPassword(_password);
            //use decrypted password

            // Need to check from Workflow table if there is any record for the user's
            // partner org id which is submitted or greater
            String checkOrgDetail = "notApproved";
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();

            // getting binding of pages
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;

            //getting iterator
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");

            //getting viewobject
            ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
            // getting viewCriteria by criteria name
            ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("UserSearch");
            supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndEmail", _email);
            supplierCrdentialDetailsVO.applyViewCriteria(null);
            supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
            supplierCrdentialDetailsVO.executeQuery();

            if (supplierCrdentialDetailsVO.first() != null) {
                String storedPassword = supplierCrdentialDetailsVO.first()
                                                                  .getAttribute("Passwd")
                                                                  .toString();
                String descriptedPassword = EncryptDecryptUtil.decrypt(storedPassword);
                if (decPass.equals(descriptedPassword)) {

                    if (!checkRejectedSupplier(_email).equals("REJECTED_USER")) {
                        Long mobile = (Long) supplierCrdentialDetailsVO.first().getAttribute("MobNo");
                        String buyerMail = supplierCrdentialDetailsVO.first()
                                                                     .getAttribute("BuyerEmail")
                                                                     .toString();
                        Long partnerVendorId =
                            new Long((Integer) supplierCrdentialDetailsVO.first().getAttribute("PartnerVendorId"));
                        Long partnerUserId =
                            new Long((Integer) supplierCrdentialDetailsVO.first().getAttribute("PartnerUserId"));

                        SupplierSession supplierSession = new SupplierSession();
                        supplierSession.setEmail(_email);
                        supplierSession.setBuyerMail(buyerMail);
                        supplierSession.setMobileNumber(mobile);
                        supplierSession.setPartnerVendorId(partnerVendorId);
                        supplierSession.setPartnerUserId(partnerUserId);

                        // checking org status that is it is created or not
                        dcIterator = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
                        //getting viewobject
                        ViewObjectImpl partnerWkfVO = (ViewObjectImpl) dcIterator.getViewObject();
                        // getting viewCriteria by criteria name
                        ViewCriteria partnerWkfVC = partnerWkfVO.getViewCriteria("PartnerOrgStatusVC");
                        partnerWkfVO.setNamedWhereClauseParam("bndPartnerOrdId", partnerVendorId);
                        partnerWkfVO.applyViewCriteria(null);
                        partnerWkfVO.applyViewCriteria(partnerWkfVC);
                        partnerWkfVO.executeQuery();
                        if (partnerWkfVO.first() != null) {
                            oracle.jbo.domain.Number statusNumber =
                                (oracle.jbo.domain.Number) partnerWkfVO.first().getAttribute("Status");
                            if (statusNumber.equals(new oracle.jbo.domain.Number(5))) {
                                checkOrgDetail = "approved";
                            }
                        }
                        HttpSession userSession = (HttpSession) ectx.getSession(true);
                        if (checkOrgDetail.equals("notApproved")) {
                            supplierSession.setGoToPage("/faces/pages/partnerOrgDetail/partnerOrgDetail.jspx");
                            userSession.setAttribute("SupplierSessionObject", supplierSession);
                            try {
                                ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                              "/pages/partnerOrgDetail/partnerOrgDetail.jspx");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            supplierSession.setGoToPage("/faces/pages/partnerHome/partnerHome.jspx");
                            userSession.setAttribute("SupplierSessionObject", supplierSession);
                            try {
                                ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                              "/pages/partnerHome/partnerHome.jspx");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        setNotificationHeader("Rejected User");
                        setNotificationBody(CommonConstants.RJECTED_SUPPLIER_MSG);
                        showNotification = CommonConstants.BOOLEAN_TRUE;
                        refreshComponent(getNotificationPGLEmailLogIn());
                    }
                } else {
                    // TODO Decryption failed or password not matched
                    _logger.info("User entered incorrect password");
                    showPasswordWarning = CommonConstants.BOOLEAN_TRUE;
                    setPasswordWarningMsg("Incorrect Password");
                    refreshComponent(loginformPGL);
                }

            } else {
                // return message as "User does not exist. Please signup"
                _logger.info("User email id does not exist");
                showEmailWarning = CommonConstants.BOOLEAN_TRUE;
                setEmailWarningMsg("User doesn’t exist. Please signup");
                refreshComponent(loginformPGL);
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method doLogin");
    }


    /**
     *  method to check user is already rejected or not
     * @param _email
     * @return
     */
    public String checkRejectedSupplier(String _email) {
        _logger.config("Entering checkRejectedSupplier");
        try {
            // is rejected type supplier
            //getting binding of pages
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
            // getting viewCriteria by criteria name
            ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("SearchRejectionStatusVC");
            supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPartnerMailId", _email);
            supplierCrdentialDetailsVO.applyViewCriteria(null);
            supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
            supplierCrdentialDetailsVO.executeQuery();
            if (supplierCrdentialDetailsVO.first() != null) {
                Row row = supplierCrdentialDetailsVO.first();
                int status = Integer.parseInt(row.getAttribute("Status").toString());
                if (status == CommonConstants.PARTNER_STATUS_REJECTED) {
                    return "REJECTED_USER"; // it means supplier rejected
                } else {
                    return "ALREADY_REGISTER_USER"; // it meanse supplier not rejected but already registerd
                }
            } else {
                return "SUPPLIER_NOT_REGISTER"; //supplier record not fund , and it is not rejected supplier
            }
        } catch (NumberFormatException numberFormatException) {
            numberFormatException.printStackTrace();
            _logger.info("Exception occured in staus m :::  " + numberFormatException);
            return "false";
        }

    }

    /**
     * helper method to get boindings
     * @return
     */

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     *
     * @param loginBtn
     */

    public void setLoginBtn(RichButton loginBtn) {
        this.loginBtn = ComponentReference.newUIComponentReference(loginBtn);
    }

    /**
     *
     * @return
     */

    public RichButton getLoginBtn() {
        if (loginBtn != null) {
            return (RichButton) loginBtn.getComponent();
        }
        return null;
    }


    /**
     *
     * @param disableLoginBtn
     */

    public void setDisableLoginBtn(Boolean disableLoginBtn) {
        this.disableLoginBtn = disableLoginBtn;
    }

    /**
     *
     * @return
     */

    public Boolean getDisableLoginBtn() {
        return disableLoginBtn;
    }

    /**
     * method to refresh captcha
     * @param actionEvent
     */

    public void refrshCaptchaClick(ActionEvent actionEvent) {
        _logger.config("Entering method refreshCaptchClick");
        try {
            _captcha = "";
            disableLoginBtn = CommonConstants.BOOLEAN_TRUE;
            _logger.info("button disabled" + disableLoginBtn);
            if (this.getCaptchImg() != null) {
                _logger.info("Refresh captchacount :::" + captchaCount);
                captchaCount = captchaCount + 1;
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.getCaptchImg());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method refreshCaptchClick");
    }

    /**
     *
     * @param actionEvent
     */

    public void sendOtpClick(ActionEvent actionEvent) {
        _logger.info("Inside sendOtpClick");
        this.sendOTP();
    }

    /**
     * method to send OTP on registered mobile number
     */

    public void sendOTP() {
        _logger.info("Inside sendOTP");
        try {
            String patern = "^[0-9]{10}$";
            String mobile = String.valueOf(_mobileNo);
            if (Pattern.matches(patern, mobile)) {
                _generatedOTP = new String(SupplierUtils.generateOTP(5));
                SmsUtil.sendSms(mobile, String.format(CommonConstants.LOGIN_OTP_MSG, _generatedOTP));
                // getting binding of pages
                BindingContainer bindings = getBindings();
                DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                //getting iterator
                DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_PARTNER_OTP_VO1Iterator");
                //getting viewobject
                ViewObjectImpl partnerOtpVO = (ViewObjectImpl) dcIterator.getViewObject();
                ViewCriteria SearchOTPVC = partnerOtpVO.getViewCriteria("SearchOTP");
                partnerOtpVO.setNamedWhereClauseParam("bndMobile", mobile);
                partnerOtpVO.applyViewCriteria(null);
                partnerOtpVO.applyViewCriteria(SearchOTPVC);
                partnerOtpVO.executeQuery();
                if (partnerOtpVO.first() != null) {
                    partnerOtpVO.first().setAttribute("Otp", _generatedOTP);
                } else {
                    partnerOtpVO.applyViewCriteria(null);
                    partnerOtpVO.executeQuery();
                    Row newRow = partnerOtpVO.createRow();
                    int indexCount = partnerOtpVO.getEstimatedRangePageCount();
                    newRow.setAttribute("OtpId", indexCount + 1);
                    newRow.setAttribute("MobNo", mobile);
                    newRow.setAttribute("Otp", _generatedOTP);
                    partnerOtpVO.insertRow(newRow);
                    partnerOtpVO.executeQuery();
                }
                // performing commit action
                OperationBinding operationBinding = bindings.getOperationBinding("Commit");
                Object result = operationBinding.execute();
                _logger.info("Row insert/update:::" + result);
                setNotificationHeader("One time password sent.");
                setNotificationBody("Enter the one time password (OTP) sent to your mobile number " + mobile);
                showNotification = CommonConstants.BOOLEAN_TRUE;
                refreshComponent(getNotificationPGL());
            }
        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();

        }
        _logger.config("Exit method sendOTP");
    }

    /**
     * login method using mobile number
     * @param loginformPGL
     * @return
     */

    public String doLoginMobile(UIComponent loginformPGL) {

        _logger.config("Entering method doLoginMobile");

        try {
            // Need to check from Workflow table if there is any record for the user's partner org id which is submitted or greater
            String checkOrgDetail = "notApproved";
            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            if (_mobileNo != null && _otp != null) {
                if (!_mobileNo.isEmpty() && !_otp.isEmpty()) {
                    _logger.info("generated OTP :---" + _generatedOTP + "Input OTP : ----" + _otp);
                    // getting binding of pages
                    BindingContainer bindings = getBindings();
                    DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                    //getting iterator
                    DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_PARTNER_OTP_VO1Iterator");
                    //getting viewobject
                    ViewObjectImpl partnerOtpVO = (ViewObjectImpl) dcIterator.getViewObject();
                    ViewCriteria SearchOTPVC = partnerOtpVO.getViewCriteria("SearchOTP");
                    partnerOtpVO.setNamedWhereClauseParam("bndMobile", _mobileNo);
                    partnerOtpVO.applyViewCriteria(null);
                    partnerOtpVO.applyViewCriteria(SearchOTPVC);
                    partnerOtpVO.executeQuery();
                    String otpFromDb = "";
                    if (partnerOtpVO.first() != null) {
                        otpFromDb = partnerOtpVO.first()
                                                .getAttribute("Otp")
                                                .toString();
                        _logger.info("OTP From DB:::" + otpFromDb);
                    }
                    if (_otp.equals(otpFromDb)) {
                        dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");
                        //getting viewobject
                        ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
                        // getting viewCriteria by criteria name
                        ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("SearchMobile");
                        supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndMobile", _mobileNo);
                        supplierCrdentialDetailsVO.applyViewCriteria(null);
                        supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
                        supplierCrdentialDetailsVO.executeQuery();
                        if (supplierCrdentialDetailsVO.first() != null) {
                            String _supplierMail = supplierCrdentialDetailsVO.first()
                                                                             .getAttribute("Email")
                                                                             .toString();
                            if (!checkRejectedSupplier(_supplierMail).equalsIgnoreCase("REJECTED_USER")) {

                                String _buyerMail = supplierCrdentialDetailsVO.first()
                                                                              .getAttribute("BuyerEmail")
                                                                              .toString();
                                Long _partnerVendorId =
                                    new Long((Integer) supplierCrdentialDetailsVO.first()
                                             .getAttribute("PartnerVendorId"));
                                Long _partnerUserId =
                                    new Long((Integer) supplierCrdentialDetailsVO.first()
                                             .getAttribute("PartnerUserId"));
                                SupplierSession supplierSession = new SupplierSession();
                                supplierSession.setEmail(_supplierMail);
                                supplierSession.setBuyerMail(_buyerMail);
                                supplierSession.setMobileNumber(Long.valueOf(_mobileNo));
                                supplierSession.setPartnerVendorId(_partnerVendorId);
                                supplierSession.setPartnerUserId(_partnerUserId);
                                // checking org status that is it is created or not
                                dcIterator = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
                                //getting viewobject
                                ViewObjectImpl partnerWkfVO = (ViewObjectImpl) dcIterator.getViewObject();
                                // getting viewCriteria by criteria name
                                ViewCriteria partnerWkfVC = partnerWkfVO.getViewCriteria("PartnerOrgStatusVC");
                                partnerWkfVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
                                //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
                                partnerWkfVO.applyViewCriteria(null);
                                partnerWkfVO.applyViewCriteria(partnerWkfVC);
                                partnerWkfVO.executeQuery();
                                if (partnerWkfVO.first() != null) {
                                    oracle.jbo.domain.Number statusNumber =
                                        (oracle.jbo.domain.Number) partnerWkfVO.first().getAttribute("Status");
                                    if (statusNumber.equals(new oracle.jbo.domain.Number(5))) {
                                        checkOrgDetail = "approved";
                                    }
                                }
                                HttpSession userSession = (HttpSession) ectx.getSession(true);
                                if (checkOrgDetail.equalsIgnoreCase("notApproved")) {
                                    supplierSession.setGoToPage("/faces/pages/partnerOrgDetail/partnerOrgDetail.jspx");
                                    userSession.setAttribute("SupplierSessionObject", supplierSession);
                                    try {
                                        partnerOtpVO.first().setAttribute("Otp", null);
                                        // performing commit action
                                        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
                                        Object result = operationBinding.execute();
                                        ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                                      "/pages/partnerOrgDetail/partnerOrgDetail.jspx");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    supplierSession.setGoToPage("/faces/pages/partnerHome/partnerHome.jspx");
                                    userSession.setAttribute("SupplierSessionObject", supplierSession);
                                    try {
                                        partnerOtpVO.first().setAttribute("Otp", null);
                                        // performing commit action
                                        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
                                        Object result = operationBinding.execute();
                                        ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                                      "/pages/partnerHome/partnerHome.jspx");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                setNotificationHeader("Rejected User");
                                setNotificationBody(CommonConstants.REJECTED_SUPPLIER);
                                showNotification = CommonConstants.BOOLEAN_TRUE;
                                // getNotificationPGL().setInlineStyle("background-color: #e40000");
                                refreshComponent(getNotificationPGL());
                            }
                        } else {
                            // if mobile not found in table handle here
                            _logger.info("Mobile Number is null");
                            showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                            setMobNoWarningMsg("Mobile # is not registered. Please sign up");
                            refreshComponent(loginformPGL);
                        }
                    } else {
                        _logger.info("OTP is incorrect");
                        showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                        setOtpWarningMsg("Incorrect OTP");
                        refreshComponent(loginformPGL);
                    }
                } else {
                    // login failed logic
                    if (_mobileNo == null) {
                        _logger.info("Mobile Number is null");
                        showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                        setMobNoWarningMsg("Please enter registered Mobile Number");
                        refreshComponent(loginformPGL);
                    } else {
                        _logger.info("OTP is null");
                        showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                        setOtpWarningMsg("Please enter OTP");
                        refreshComponent(loginformPGL);
                    }
                }
            } else {
                //login failed due to email id or password is empty
                if (_mobileNo == null) {
                    _logger.info("Mobile Number is null");
                    showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                    setMobNoWarningMsg("Please enter registered Mobile Number");
                    refreshComponent(loginformPGL);
                } else {
                    _logger.info("OTP is null");
                    showOTPWarning = CommonConstants.BOOLEAN_TRUE;
                    setOtpWarningMsg("Please enter OTP");
                    refreshComponent(loginformPGL);
                }
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            _logger.severe("Exception raised:::" + nfe.getMessage());
        }
        return null;
    }

    /**
     *
     * @param disableMobileLoginBtn
     */

    public void setDisableMobileLoginBtn(Boolean disableMobileLoginBtn) {
        this.disableMobileLoginBtn = disableMobileLoginBtn;
    }

    /**
     *
     * @return
     */

    public Boolean getDisableMobileLoginBtn() {
        return disableMobileLoginBtn;
    }

    /**
     *
     * @param loginMobileBtn
     */
    public void setLoginMobileBtn(RichButton loginMobileBtn) {
        this.loginMobileBtn = ComponentReference.newUIComponentReference(loginMobileBtn);
    }

    /**
     *
     * @return
     */

    public RichButton getLoginMobileBtn() {
        if (loginMobileBtn != null) {
            return (RichButton) loginMobileBtn.getComponent();
        }
        return null;
    }

    /**
     *
     * @param notificationPGL
     */

    public void setNotificationPGL(RichPanelGroupLayout notificationPGL) {
        this.notificationPGL = ComponentReference.newUIComponentReference(notificationPGL);
    }

    /**
     *
     * @return
     */

    public RichPanelGroupLayout getNotificationPGL() {
        if (notificationPGL != null) {
            return (RichPanelGroupLayout) notificationPGL.getComponent();
        }
        return null;
    }

    public void setShowNotification(Boolean showNotification) {
        this.showNotification = showNotification;
    }

    /**
     *
     * @return
     */

    public Boolean getShowNotification() {
        return showNotification;
    }

    /**
     *
     * @param actionEvent
     */

    public void closeNotificationAL(ActionEvent actionEvent) {
        _logger.info("Closing notification...");
        showNotification = CommonConstants.BOOLEAN_FALSE;
        refreshComponent(getNotificationPGL());
    }

    /**
     *
     * @param captchImg
     */

    public void setCaptchImg(RichImage captchImg) {
        this.captchImg = ComponentReference.newUIComponentReference(captchImg);
    }


    /**
     *
     * @return
     */

    public RichImage getCaptchImg() {
        if (captchImg != null) {
            return (RichImage) captchImg.getComponent();
        }
        return null;
    }

    /**
     * Helper method used to decrypt , encrypted password coming from page, this password is encrypted at client side using JavaScript(AesUtil.js).
     * @param pass
     * @return
     */
    public String decryptPassword(String pass) {
        
        _logger.config("Entering method decryptPassword :: pass :::"+pass);
        String decryptedPassword = null;
        AesUtil aesUtil = new AesUtil(128, 1000);
        try {
            if (pass != null && pass.split(CommonConstants.DECRYPT_DELIMITER).length == 3) {

                decryptedPassword =
                    aesUtil.decrypt(pass.split(CommonConstants.DECRYPT_DELIMITER)[1],
                                    pass.split(CommonConstants.DECRYPT_DELIMITER)[0], CommonConstants.AES_PASSPHRASE,
                                    pass.split(CommonConstants.DECRYPT_DELIMITER)[2]);
            }
        } catch (Exception e) {
            _logger.severe("Exception rasied ::" + e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method decryptPassword");
        return decryptedPassword;
    }
}

