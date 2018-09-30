package com.airtel.supplierportal.bean.forgotpassword;

import com.airtel.supplierportal.utility.CommonConstants;
import com.airtel.supplierportal.utility.EmailUtil;
import com.airtel.supplierportal.utility.EncryptDecryptUtil;
import com.airtel.supplierportal.utility.SupplierUtils;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class ForgotPassword implements Serializable {
    @SuppressWarnings("compatibility:6564634557379418074")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(ForgotPassword.class);
    private String _emailAddress;
    private String inputOTP;
    private String generatedOtp;
    private String isDisable = "true";
    private String _newPassword;
    private String _confirmPassword;
    private ComponentReference emailValidationMessage;
    private String emailValidationMessageString = "";
    private String otpValidationMessageString = "";
    private ComponentReference otpValidationMessage;
    private String resetForgotPasswordValidationMessageString = "";
    private ComponentReference resetForgotPasswordValidationMessage;
    private String isDisableEmail = "true";
    private String isDisableSubmitOtp = "true";
    private ComponentReference notificationPGL;
    private String notificationHeader;
    private String notificationBody;
    private Boolean showNotification = CommonConstants.BOOLEAN_FALSE;

    /**
     *
     * @param resetForgotPasswordValidationMessageString
     */
    public void setResetForgotPasswordValidationMessageString(String resetForgotPasswordValidationMessageString) {
        this.resetForgotPasswordValidationMessageString = resetForgotPasswordValidationMessageString;
    }

    /**
     *
     * @return
     */
    public String getResetForgotPasswordValidationMessageString() {
        return resetForgotPasswordValidationMessageString;
    }

    /**
     *
     * @param resetForgotPasswordValidationMessage
     */
    public void setResetForgotPasswordValidationMessage(RichOutputText resetForgotPasswordValidationMessage) {
        this.resetForgotPasswordValidationMessage =
            ComponentReference.newUIComponentReference(resetForgotPasswordValidationMessage);
    }

    /**
     *
     * @return
     */
    public RichOutputText getResetForgotPasswordValidationMessage() {
        if (resetForgotPasswordValidationMessage != null) {
            return (RichOutputText) resetForgotPasswordValidationMessage.getComponent();
        }
        return null;
    }

    /**
     *
     * @param isDisableSubmitOtp
     */
    public void setIsDisableSubmitOtp(String isDisableSubmitOtp) {
        this.isDisableSubmitOtp = isDisableSubmitOtp;
    }
    
    /**
     *
     * @return
     */
    public String getIsDisableSubmitOtp() {
        return isDisableSubmitOtp;
    }

    /**
     *
     * @param isDisableEmail
     */
    public void setIsDisableEmail(String isDisableEmail) {
        this.isDisableEmail = isDisableEmail;
    }

    /**
     *
     * @return
     */
    public String getIsDisableEmail() {
        return isDisableEmail;
    }

    /**
     *
     * @param otpValidationMessage
     */
    public void setOtpValidationMessage(RichOutputText otpValidationMessage) {
        this.otpValidationMessage = ComponentReference.newUIComponentReference(otpValidationMessage);
    }

    /**
     *
     * @return
     */
    public RichOutputText getOtpValidationMessage() {
        if (otpValidationMessage != null) {
            return (RichOutputText) otpValidationMessage.getComponent();
        }
        return null;
    }

    /**
     *
     * @param otpValidationMessageString
     */
    public void setOtpValidationMessageString(String otpValidationMessageString) {
        this.otpValidationMessageString = otpValidationMessageString;
    }

    /**
     *
     * @return
     */
    public String getOtpValidationMessageString() {
        return otpValidationMessageString;
    }

    /**
     *
     * @param isDisable
     */
    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    /**
     *
     * @return
     */
    public String getIsDisable() {
        return isDisable;
    }

    /**
     *
     * @param _newPassword
     */
    public void setNewPassword(String _newPassword) {
        this._newPassword = _newPassword;
    }

    /**
     *
     * @return
     */
    public String getNewPassword() {
        return _newPassword;
    }

    /**
     *
     * @param _confirmPassword
     */
    public void setConfirmPassword(String _confirmPassword) {
        this._confirmPassword = _confirmPassword;
    }

    /**
     *
     * @return
     */
    public String getConfirmPassword() {
        return _confirmPassword;
    }

    /**
     *
     * @param inputOTP
     */
    public void setInputOTP(String inputOTP) {
        this.inputOTP = inputOTP;
    }

    /**
     *
     * @return
     */
    public String getInputOTP() {
        return inputOTP;
    }

    /**
     *
     * @param generatedOtp
     */
    public void setGeneratedOtp(String generatedOtp) {
        this.generatedOtp = generatedOtp;
    }

    /**
     *
     * @return
     */
    public String getGeneratedOtp() {
        return generatedOtp;
    }

    /**
     *
     * @param _emailAddress
     */
    public void setEmailAddress(String _emailAddress) {
        this._emailAddress = _emailAddress;
    }

    /**
     *
     * @return
     */
    public String getEmailAddress() {
        return _emailAddress;
    }

    /**
     *
     * @param emailValidationMessageString
     */
    public void setEmailValidationMessageString(String emailValidationMessageString) {
        this.emailValidationMessageString = emailValidationMessageString;
    }

    /**
     *
     * @return
     */
    public String getEmailValidationMessageString() {
        return emailValidationMessageString;
    }

    /**
     * 
     */
    public ForgotPassword() {
        super();
    }

    /**
     * called on clicking cancel link from page
     * @return
     */
    public String onCancel() {
        _logger.config("Entering Method onCancel");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            _logger.config("Redirecting to Login Page");
            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                          "/pages/login/partnerLogin.jspx");
        } catch (IOException e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        return null;
    }

    /**
     * method called on clicking Set Password button
     * @return
     */
    public String onSetPassword() {
        _logger.config("Entering Method onSetPassword");
        try {
            if (_confirmPassword.length() >= CommonConstants.PASSWORD_LENGTH &&
                _newPassword.length() >= CommonConstants.PASSWORD_LENGTH) {
                if (_confirmPassword.equals(_newPassword)) {
                    String encryptedPassword = EncryptDecryptUtil.encrypt(_newPassword);
                    ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        Boolean isSaved = savePasswordinDB(_emailAddress, encryptedPassword);
                        if (isSaved) {
                            _logger.config("Password Set Successfully");
                            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                          "/pages/login/partnerLogin.jspx?rp=success");
                        } else {
                            _logger.config("Password Set Failed");
                            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                                          "/pages/login/partnerLogin.jspx?rp=fail");
                        }
                    } catch (IOException e) {
                        _logger.severe("Exception:::" + e.getMessage());
                        //                    e.printStackTrace();
                    }
                } else {
                    AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
                    ctx.addPartialTarget(getEmailValidationMessage());
                    if (!_newPassword.equals(_confirmPassword)) {
                        setEmailValidationMessageString(CommonConstants.PASSWORD_NOT_MATCHED_ERROR_MESSAGE);
                        setIsDisableEmail("true");
                    } else {
                        setEmailValidationMessageString("");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method onSetPassword");
        return null;
    }

    /**
     * helper method called from onSetPassword()
     * 
     * @param email
     * @param password
     * @return
     */
    public Boolean savePasswordinDB(String email, String password) {
        _logger.config("Entering Method savePasswordinDB:::" + email + ":::" + password);
        Boolean isSaved = CommonConstants.BOOLEAN_FALSE;
        try {
            BindingContainer bindings = getBindings();
            OperationBinding resetPassword = bindings.getOperationBinding("resetPassword");
            resetPassword.getParamsMap().put("email", email);
            resetPassword.getParamsMap().put("password", password);
            Object result = resetPassword.execute();
            isSaved = CommonConstants.BOOLEAN_TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method savePasswordinDB:::"+isSaved);
        return isSaved;
    }

    /**
     * Method called on clicking Submit Otp button on page
     * @return
     */
    public String submitOTP() {
        _logger.config("Entering Method submitOTP() :::: Generated OTP :: " + generatedOtp + "input OTP :::: " + inputOTP);
        try {
            if (inputOTP.equals(generatedOtp)) {
                return "SETPWD";
            } else {
                // set message for otp did not match ;
                AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
                ctx.addPartialTarget(getOtpValidationMessage());
                setOtpValidationMessageString(CommonConstants.INCORRECT_OTP);
                setIsDisableSubmitOtp("true");
                return "no";
            }
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method submitOTP");
        return "no";
    }

    /**
     * helper method to get bindings
     * @return
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Method called on Email Value Change
     * @param valueChangeEvent
     */
    public void emailValidate(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering Method emailValidate");
        String emailValue = valueChangeEvent.getNewValue().toString();
        _logger.config(emailValue);
        setEmailAddress(emailValue);
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getEmailValidationMessage());
        try {
            if (emailValue != null && !emailValue.equals("")) {
                if (EmailUtil.validateEmail(emailValue)) {
                    setIsDisable("false");
                    setEmailValidationMessageString("");
                } else {
                    setEmailValidationMessageString(CommonConstants.EMAIL_NOT_VALID);
                    setIsDisable("true");
                }

            } else {
                setEmailValidationMessageString("");
                setIsDisable("true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method emailValidate");
    }

    /**
     * Method called on OTP Value Change
     * @param valueChangeEvent
     */
    public void otpValueChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering Method otpValueChangeListener");
        String opt_value = valueChangeEvent.getNewValue().toString();
        _logger.config(opt_value);
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getOtpValidationMessage());
        try {
            if (opt_value != null && !opt_value.equals("")) {
                _logger.info("EmailInputStream address ----------::: " + _emailAddress);
                _logger.info("Generated OTP :::---------" + generatedOtp);
                _logger.info("New OTP ::: ------" + opt_value);
                // getting binding of pages
                BindingContainer bindings = getBindings();
                DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                //getting iterator
                DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");
                //getting viewobject
                ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
                // getting viewCriteria by criteria name
                ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("UserSearch");
                supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndEmail", _emailAddress);
                //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
                supplierCrdentialDetailsVO.applyViewCriteria(null);
                supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
                supplierCrdentialDetailsVO.executeQuery();
                if (supplierCrdentialDetailsVO.first() != null) {
                    // do nothing
                    setOtpValidationMessageString("");
                    setIsDisableSubmitOtp("false");
                } else {
                    // set error msg
                    setOtpValidationMessageString(CommonConstants.EMAIL_NOT_REGISTERED);
                    setIsDisableSubmitOtp("true");
                }
            } else {
                setOtpValidationMessageString("");
                setIsDisableSubmitOtp("true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method otpValueChangeListener");
    }

    /**
     *
     * @param emailValidationMessage
     */
    public void setEmailValidationMessage(RichOutputText emailValidationMessage) {
        this.emailValidationMessage = ComponentReference.newUIComponentReference(emailValidationMessage);
    }

    /**
     *
     * @return
     */
    public RichOutputText getEmailValidationMessage() {
        if (emailValidationMessage != null) {
            return (RichOutputText) emailValidationMessage.getComponent();
        }
        return null;
    }

    /**
     * Methodcalled on clicking Send OTP from page
     * @return
     */
    public String sendOtpMail() {
        _logger.config("Entering Method sendOtpMail");
        // getting binding of pages
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        //getting iterator
        DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");
        //getting viewobject
        ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
        // getting viewCriteria by criteria name
        ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("UserSearch");
        supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndEmail", _emailAddress);
        //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
        supplierCrdentialDetailsVO.applyViewCriteria(null);
        supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
        supplierCrdentialDetailsVO.executeQuery();
        if (supplierCrdentialDetailsVO.first() != null) {
            // do nothing
            generatedOtp = new String(SupplierUtils.generateOTP(5));
            Boolean isMailSent =
                EmailUtil.sendEmail(_emailAddress, CommonConstants.OTP_SENDER_EMAIL, CommonConstants.OTP_EMAIL_SUBJECT,
                                    String.format(CommonConstants.OTP_EMAIL_BODY, generatedOtp));
            if (isMailSent) {
                setNotificationHeader("One time password sent.");
                setNotificationBody("Enter the one time password (OTP) sent to your Email Id " + _emailAddress);
                showNotification = CommonConstants.BOOLEAN_TRUE;
                
                return "OTP";
            } else {
                return null;
            }
        } else {
            // set error msg
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getEmailValidationMessage());
            setEmailValidationMessageString(CommonConstants.EMAIL_NOT_REGISTERED);
            return "OTP_Error";
        }
       
    }

    /**
     * Method called on Email Value Change
     * @param valueChangeEvent
     */
    public void validateNewEmail(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering Method validateNewEmail");
        String str = valueChangeEvent.getNewValue().toString();
        setNewPassword(valueChangeEvent.getNewValue().toString());
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getResetForgotPasswordValidationMessage());
        try {
            if (str != null && !str.equals("")) {
                if (_newPassword.length() < CommonConstants.PASSWORD_LENGTH) {
                    setResetForgotPasswordValidationMessageString(CommonConstants.PASSWORD_LENGTH_ERROR_MESSAGE);
                    setIsDisableEmail("true");
                } else {
                    setResetForgotPasswordValidationMessageString("");
                }
            } else {
                setResetForgotPasswordValidationMessageString("");
                setIsDisableEmail("true");
            }
            validateSignUpFormEmail();
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method validateNewEmail");
    }

    /**
     * Method called on Confirm Email Change
     * 
     * @param valueChangeEvent
     */
    public void validateConfirmEmail(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering Method validateConfirmEmail");
        setConfirmPassword(valueChangeEvent.getNewValue().toString());
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getEmailValidationMessage());
        try {
            String str = valueChangeEvent.getNewValue().toString();
            if (str != null && !str.equals("")) {
                if ((_newPassword != null ? _newPassword.length() : 0) < CommonConstants.PASSWORD_LENGTH ||
                    (_confirmPassword != null ? _confirmPassword.length() : 0) < CommonConstants.PASSWORD_LENGTH) {
                    setResetForgotPasswordValidationMessageString(CommonConstants.PASSWORD_LENGTH_ERROR_MESSAGE);
                    setIsDisableEmail("true");
                } else {
                    if (_confirmPassword.equals(_newPassword)) {
                        setResetForgotPasswordValidationMessageString("");
                    } else {
                        setResetForgotPasswordValidationMessageString(CommonConstants.PASSWORD_NOT_MATCHED_ERROR_MESSAGE);
                        setIsDisableEmail("true");
                    }
                }
            } else {
                setResetForgotPasswordValidationMessageString("");
                setIsDisableEmail("true");
            }
            validateSignUpFormEmail();
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        _logger.config("Exit Method validateConfirmEmail");
    }

    /**
     * helper method to validate signup form fields
     */
    public void validateSignUpFormEmail() {
        _logger.config("Entering Method validateSignUpFormEmail");
        try {
            if ((_newPassword != null && !_newPassword.equals("")) &&
                (_confirmPassword != null && !_confirmPassword.equals(""))) {
                setIsDisableEmail("false");
            } else {
                setIsDisableEmail("true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            _logger.severe("Exception raised:::"+e.getMessage());
        }
        
        _logger.config("Exit Method validateSignUpFormEmail");
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
     *
     * @param showNotification
     */
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
     *code for refreshing the ui component
     * @param uiComp
     */
    private void refreshComponent(UIComponent uiComp) {
        if (uiComp != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(uiComp); //refreshing ui components
        }
    }
}
