package com.airtel.supplierportal.bean.signup;

import com.airtel.supplierportal.utility.CommonConstants;
import com.airtel.supplierportal.utility.EmailUtil;
import com.airtel.supplierportal.utility.EncryptDecryptUtil;
import com.airtel.supplierportal.utility.SupplierUtils;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.controller.ControllerContext;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class SignupBean implements Serializable {
    @SuppressWarnings("compatibility:8927012539603950188")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(SignupBean.class);
    // fields for the supplier signup
    private String _buyerMail;
    private String _supplierMail;
    private String _supplierMobile;
    private String _password;
    private String _rePassword;
    private Long _partnerUserId;
    private String errorSignUpEmailMessageString = "";
    private ComponentReference errorSignUpEmailFormMessage;
    private String errorSignUpMessageString = "";
    private ComponentReference errorSignUpFormMessage;
    private String isDisable = "true";
    private String isDisableSignupForm = "true";
    private String isDisableNotKnowBuyer = "true";
    // Fields for the signup flow(unknown buyer )
    private String _companyUrl;
    private oracle.jbo.domain.Number _productCategoryId;
    private String supplierExistanceMessageString = "";
    private ComponentReference supplierExistanceMessage;
    private String buyerEmailDoaminValidationString = "";
    private ComponentReference buyerEmailDoaminValidationMsg;
    // termes and condition flag
    private Boolean termsAndConditionFlag = CommonConstants.BOOLEAN_FALSE;
    private String _supplierUserIdResend;
    private Boolean showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
    private String showMobNoWarningMsg = "";

    /**
     *
     * @param showMobNoWarningMsg
     */
    public void setShowMobNoWarningMsg(String showMobNoWarningMsg) {
        this.showMobNoWarningMsg = showMobNoWarningMsg;
    }

    /**
     * @return
     */
    public String getShowMobNoWarningMsg() {
        return showMobNoWarningMsg;
    }


    /**
     * @param showMobNoWarning
     */
    public void setShowMobNoWarning(Boolean showMobNoWarning) {
        this.showMobNoWarning = showMobNoWarning;
    }

    /**
     * @return
     */
    public Boolean getShowMobNoWarning() {
        return showMobNoWarning;
    }

    /**
     * @param _supplierUserIdResend
     */
    public void setSupplierUserIdResend(String _supplierUserIdResend) {
        this._supplierUserIdResend = _supplierUserIdResend;
    }

    /**
     * @return
     */
    public String getSupplierUserIdResend() {
        return _supplierUserIdResend;
    }


    /**
     * @param errorSignUpEmailMessageString
     */
    public void setErrorSignUpEmailMessageString(String errorSignUpEmailMessageString) {
        this.errorSignUpEmailMessageString = errorSignUpEmailMessageString;
    }

    /**
     * @return
     */
    public String getErrorSignUpEmailMessageString() {
        return errorSignUpEmailMessageString;
    }

    /**
     * @param errorSignUpEmailFormMessage
     */
    public void setErrorSignUpEmailFormMessage(RichOutputText errorSignUpEmailFormMessage) {
        this.errorSignUpEmailFormMessage = ComponentReference.newUIComponentReference(errorSignUpEmailFormMessage);
    }

    /**
     * @return
     */
    public RichOutputText getErrorSignUpEmailFormMessage() {
        if (errorSignUpEmailFormMessage != null) {
            return (RichOutputText) errorSignUpEmailFormMessage.getComponent();
        }
        return null;
    }


    /**
     * @param errorSignUpMessageString
     */
    public void setErrorSignUpMessageString(String errorSignUpMessageString) {
        this.errorSignUpMessageString = errorSignUpMessageString;
    }

    /**
     * @return
     */
    public String getErrorSignUpMessageString() {
        return errorSignUpMessageString;
    }

    /**
     * @param errorSignUpFormMessage
     */
    public void setErrorSignUpFormMessage(RichOutputText errorSignUpFormMessage) {
        this.errorSignUpFormMessage = ComponentReference.newUIComponentReference(errorSignUpFormMessage);
    }

    /**
     * @return
     */
    public RichOutputText getErrorSignUpFormMessage() {
        if (errorSignUpFormMessage != null) {
            return (RichOutputText) errorSignUpFormMessage.getComponent();
        }
        return null;
    }

    /**
     * @param buyerEmailDoaminValidationString
     */
    public void setBuyerEmailDoaminValidationString(String buyerEmailDoaminValidationString) {
        this.buyerEmailDoaminValidationString = buyerEmailDoaminValidationString;
    }

    /**
     * @return
     */
    public String getBuyerEmailDoaminValidationString() {
        return buyerEmailDoaminValidationString;
    }

    /**
     * @param buyerEmailDoaminValidationMsg
     */
    public void setBuyerEmailDoaminValidationMsg(RichOutputText buyerEmailDoaminValidationMsg) {
        this.buyerEmailDoaminValidationMsg = ComponentReference.newUIComponentReference(buyerEmailDoaminValidationMsg);
    }

    /**
     * @return
     */
    public RichOutputText getBuyerEmailDoaminValidationMsg() {
        if (buyerEmailDoaminValidationMsg != null) {
            return (RichOutputText) buyerEmailDoaminValidationMsg.getComponent();
        }
        return null;
    }

    /**
     * @param _companyUrl
     */
    public void setCompanyUrl(String _companyUrl) {
        this._companyUrl = _companyUrl;
    }

    /**
     * @return
     */
    public String getCompanyUrl() {
        return _companyUrl;
    }


    /**
     * @param isDisable
     */
    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    /**
     * @return
     */
    public String getIsDisable() {
        return isDisable;
    }


    // for select one choice list in product and category lov
    private List<SelectItem> productAndCategory;
    private String selectValue;


    // default constructor
    public SignupBean() {
        super();
    }


    // to switch view if supplier know the buyer mail id or not

    /**
     * value change listner method of radio button group to switch btween mobile login or email login view
     * @param valueChangeEvent
     * @return
     */
    public String onValueChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method onValueChange");
        String listValue = valueChangeEvent.getNewValue().toString();
        FacesContext fctx = FacesContext.getCurrentInstance();
        ControllerContext ccontext = ControllerContext.getInstance();
        String viewId = "signupNoName";

        if (listValue.equals("N")) {
            _logger.info(ccontext.getCurrentViewPort().getViewId());
            NavigationHandler nh = fctx.getApplication().getNavigationHandler();
            nh.handleNavigation(fctx, null, "no");
            setIsDisable("false");
        } else if (listValue.equals("Y")) {
            setIsDisable("true");
            setBuyerMail("");
            NavigationHandler nh = fctx.getApplication().getNavigationHandler();
            nh.handleNavigation(fctx, null, "yes");
        } else {
            setIsDisable("true");
        }
        
        _logger.config("Exit method onValueChange");
        return null;
    }

    /**
     * @param _buyerMail
     */
    public void setBuyerMail(String _buyerMail) {
        this._buyerMail = _buyerMail;
    }

    /**
     * @return
     */
    public String getBuyerMail() {
        return _buyerMail;
    }

    /**
     * @param _supplierMail
     */
    public void setSupplierMail(String _supplierMail) {
        this._supplierMail = _supplierMail;
    }

    /**
     * @return
     */
    public String getSupplierMail() {
        return _supplierMail;
    }

    /**
     * @param _supplierMobile
     */
    public void setSupplierMobile(String _supplierMobile) {
        this._supplierMobile = _supplierMobile;
    }

    /**
     * @return
     */
    public String getSupplierMobile() {
        return _supplierMobile;
    }

    /**
     * @param _password
     */
    public void setPassword(String _password) {
        this._password = _password;
    }

    /**
     * @return
     */
    public String getPassword() {
        return _password;
    }

    /**
     * @param _rePassword
     */
    public void setRePassword(String _rePassword) {
        this._rePassword = _rePassword;
    }

    /**
     * @return
     */
    public String getRePassword() {
        return _rePassword;
    }

    /**
     * @param _partnerUserId
     */
    public void setPartnerUserId(Long _partnerUserId) {
        this._partnerUserId = _partnerUserId;
    }

    /**
     * @return
     */
    public Long getPartnerUserId() {
        return _partnerUserId;
    }

    /**
     * @return
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * @param supplierExistanceMessageString
     */
    public void setSupplierExistanceMessageString(String supplierExistanceMessageString) {
        this.supplierExistanceMessageString = supplierExistanceMessageString;
    }

    /**
     * @return
     */
    public String getSupplierExistanceMessageString() {
        return supplierExistanceMessageString;
    }

    /**
     * @param supplierExistanceMessage
     */
    public void setSupplierExistanceMessage(RichOutputText supplierExistanceMessage) {
        this.supplierExistanceMessage = ComponentReference.newUIComponentReference(supplierExistanceMessage);
    }

    /**
     * @return
     */
    public RichOutputText getSupplierExistanceMessage() {
        if (supplierExistanceMessage != null) {
            return (RichOutputText) supplierExistanceMessage.getComponent();
        }
        return null;
    }


    /**
     * @param isDisableSignupForm
     */
    public void setIsDisableSignupForm(String isDisableSignupForm) {
        this.isDisableSignupForm = isDisableSignupForm;
    }

    /**
     * @return
     */
    public String getIsDisableSignupForm() {
        return isDisableSignupForm;
    }


    /**
     * method to validate buyer mail id
     * @param valueChangeEvent
     */
    public void validateBuyerEmail(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method validateBuyerEmail");
        _logger.info("listening on validation");
        try {
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getBuyerEmailDoaminValidationMsg());
            // Getting email address
            String emailValue = valueChangeEvent.getNewValue().toString();
            setBuyerMail(emailValue);
            if (emailValue != null && !emailValue.equals("")) {
                if (validateEmailCompletion(emailValue)) {
                    _logger.info("after email formate validation");
                    buyerEmailDoaminValidationString = "";
                    setIsDisable("false");
                } else {
                    setIsDisable("true");
                    buyerEmailDoaminValidationString = CommonConstants.BUYER_EMAIL_ID_NOT_VALID_FORMAT;
                }
            } else {
                buyerEmailDoaminValidationString = "";
                setIsDisable("true");
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method validateBuyerEmail");
    }
    

    /*
     * validate email format for @airtel.com
     */

    /**
     * method to validate buyer's email format
     * @param buyerMail
     * @return
     */
    public boolean validateEmailCompletion(String buyerMail) {
        
        _logger.config("called validateEmailCompletion");
        String regex = "(\\W|^)[\\w.+\\-]*@airtel\\.com(\\W|$)";
        //        String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);

        if (buyerMail != null) {
            Matcher matcher = pattern.matcher(buyerMail);
            _logger.info(buyerMail + " : " + matcher.matches());
            return matcher.matches();
        } else {
            _logger.info("buyer email doesn't matched!");
            return false;
        }
    }

    /**
     * method to create new supplier account
     * @return
     */
    public String createAccount() {
        _logger.config("Entering method createAccount");
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getErrorSignUpFormMessage());
        ctx.addPartialTarget(getErrorSignUpEmailFormMessage());

        if (_password.equals(_rePassword) && _password.length() >= CommonConstants.PASSWORD_LENGTH &&
            _rePassword.length() >= CommonConstants.PASSWORD_LENGTH) {
            if (!validateSupplierEmailExistence(_supplierMail)) {
                // getting binding of pages
                // if supplier does not exists in the suppliercredentialvo then we need to check it may we rejected.
                if (!checkRejectedSupplier(getSupplierMail())) {
                    if (!isAlreadyRegisterMobileNo(_supplierMobile)) {
                        BindingContainer bindings = getBindings();
                        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                        _logger.info("-------Encrypting password --------");
                        String encriptedPassword = EncryptDecryptUtil.encrypt(_rePassword);
                        try {
                            _logger.info("--------Inside try to call createVendorAccount method -------");
                            OperationBinding createVendorAccount = bindings.getOperationBinding("createVendorAccount");
                            createVendorAccount.getParamsMap().put("_vendorEmail", _supplierMail);
                            createVendorAccount.getParamsMap().put("_buyerEmail", _buyerMail);
                            createVendorAccount.getParamsMap().put("_vendorMobile", _supplierMobile);
                            createVendorAccount.getParamsMap().put("_password", encriptedPassword);
                            _logger.info("------params set to method paramMap ----------");
                            createVendorAccount.execute();
                            _logger.info("------method executed ---------");
                        } catch (Exception e) {
                            _logger.info("---- Exception occured in account creation ----");
                            errorSignUpEmailMessageString = CommonConstants.ACCOUNT_CREATION_EXCEPTION;
                            _logger.severe("Exception raised "+e.getMessage());
                            e.printStackTrace();
                            
                        }
                        DCIteratorBinding dcIterator =
                            dcBinding.findIteratorBinding("BTVL_PARTNER_USER_TBL_TXN_VO1Iterator");

                        ViewObjectImpl tblSupplierUserVO = (ViewObjectImpl) dcIterator.getViewObject();
                        ViewCriteria searchSupplierByMailVC =
                            tblSupplierUserVO.getViewCriteria("SearchSupplierByEmail");
                        tblSupplierUserVO.setNamedWhereClauseParam("bndEmail", _supplierMail);
                        tblSupplierUserVO.applyViewCriteria(null);
                        tblSupplierUserVO.applyViewCriteria(searchSupplierByMailVC);
                        tblSupplierUserVO.executeQuery();
                        String supplierUserId = null;
                        if (tblSupplierUserVO.first() != null) {
                            supplierUserId = tblSupplierUserVO.first()
                                                              .getAttribute("PartnerUserId")
                                                              .toString();
                            _logger.info("--------- Partner id ------" + supplierUserId);
                            this._supplierUserIdResend = supplierUserId;
                        }
                        // Call sendActivationLink()
                        // _partnerUserId = indexCounter + 1;
                        // String supplierUserId = Long.toString(indexCounter + 1);
                        _logger.info("----Sending Activation Link to Vendor------------");
                        this.sendActivationLink(_supplierMail, supplierUserId);
                        return "createAccount";
                    } else {
                        setIsDisableSignupForm("true");
                        setSupplierMobile("");
                        setShowMobNoWarningMsg(CommonConstants.ALREADY_REGISTERED_SUPPLIER);
                        showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                        return null;
                    }
                } else {
                    setErrorSignUpEmailMessageString(CommonConstants.REJECTED_SUPPLIER);
                    setIsDisableSignupForm("true");
                    return null;
                }
            } else {
                //writen code for error msg on password not matched
                setErrorSignUpEmailMessageString(CommonConstants.ALREADY_REGISTERED_SUPPLIER);
                setIsDisableSignupForm("true");
                return null;
            }
        } else {
            //writen code for error msg on password not matched
            setErrorSignUpMessageString(CommonConstants.PASSWORD_NOT_MATCHED_ERROR_MESSAGE);
            setIsDisableSignupForm("true");
            return null;
        }
        
    }

    /**
     * method to check rejected supplier 
     * @param supplierMail
     * @return
     */
    public boolean checkRejectedSupplier(String supplierMail) {
        _logger.config("Entering method checkRejectedSupplier :: supplierMail :: "+supplierMail);
        try {
            setErrorSignUpMessageString("");
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
            supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPartnerMailId", _supplierMail);
            //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
            supplierCrdentialDetailsVO.applyViewCriteria(null);
            supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
            supplierCrdentialDetailsVO.executeQuery();
            if (supplierCrdentialDetailsVO.first() != null) {
                Row row = supplierCrdentialDetailsVO.first();
                int status = Integer.parseInt(row.getAttribute("Status").toString());
                if (status == CommonConstants.PARTNER_STATUS_REJECTED) {
                    return true; // it means supplier rejected
                } else {
                    return false; // it meanse supplier not rejected but already registerd
                }
            } else {
                return false; //supplier record not fund , and it is not rejected supplier
            }
        } catch (NumberFormatException numberFormatException) {
            _logger.info("Exception occured in staus m :::  " + numberFormatException);
            setSupplierMobile("");
            _logger.severe("Exception raised "+numberFormatException.getMessage());
            return false;
        }
    }


    /**
     *
     * helper method to generate and send activation link.
     * @param supplierEmailId
     * @param supplierUserId
     */
    public void sendActivationLink(String supplierEmailId, String supplierUserId) {
        _logger.config("Entering method sendActivationLink :: supplierEmailId :: "+supplierEmailId + " :: supplierUserId :: "+supplierUserId);
        try {
            String myActivationCode = SupplierUtils.generateActivationCode(supplierUserId);
            Boolean isMailSent =
                EmailUtil.sendEmail(supplierEmailId, CommonConstants.VERIFICATION_SENDER_EMAIL,
                                    CommonConstants.VERIFICAION_EMAIL_SUBJECT,
                                    String.format(CommonConstants.VERIFICATION_EMAIL_BODY,
                                                  String.format(CommonConstants.ACTIVATION_URL, myActivationCode)));
            _logger.info("Is Mail Sent :::" + isMailSent);
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method sendActivationLink");
    }


    /**
     * @param selectValue
     */
    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    /**
     * @return
     */
    public String getSelectValue() {
        return selectValue;
    }

    /**
     * @param productAndCategory
     */
    public void setProductAndCategory(List<SelectItem> productAndCategory) {
        this.productAndCategory = productAndCategory;
    }

    /**
     * @return
     */
    public List<SelectItem> getProductAndCategory() {
        return productAndCategory;
    }

    /**
     * @param isDisableNotKnowBuyer
     */
    public void setIsDisableNotKnowBuyer(String isDisableNotKnowBuyer) {
        this.isDisableNotKnowBuyer = isDisableNotKnowBuyer;
    }

    /**
     * @return
     */
    public String getIsDisableNotKnowBuyer() {
        return isDisableNotKnowBuyer;
    }


    /**
     * on submit button click method
     * @return
     */
    public String onSubmitButtonClick() {
        _logger.config("Entering method onSubmitBottinClick");
        try {
            if (!validateSupplierEmailExistence(_supplierMail)) {
                // supplier not exists so create him
                // getting binding of pages
                BindingContainer bindings = getBindings();
                DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                //getting iterator
                DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("ProductAndCategoryLookupRVO1Iterator");
                //getting viewobject
                ViewObjectImpl productCategoryVO = (ViewObjectImpl) dcIterator.getViewObject();
                ViewCriteria vc = productCategoryVO.getViewCriteria("SerachBuyerMailId");
                productCategoryVO.setNamedWhereClauseParam("bndProductCatId", _productCategoryId);
                productCategoryVO.applyViewCriteria(null);
                productCategoryVO.applyViewCriteria(vc);
                productCategoryVO.executeQuery();
                Row row = productCategoryVO.first();

                String buyerMailId = row.getAttribute("BuyerEmail").toString();
                String productCat = row.getAttribute("ProductCatName").toString();

                _logger.info("Buyer mail ID ----- :" + buyerMailId + "Product cat id ----- " + _productCategoryId);
                // send mail to buyer
                EmailUtil.sendEmail(buyerMailId, CommonConstants.SIGNUP_REQUEST_SENDER_EMAIL,
                                    CommonConstants.SIGNUP_REQUEST_EMAIL_SUBJECT,
                                    String.format(CommonConstants.SIGNUP_REQUEST_EMAIL_BODY, _supplierMail, _companyUrl,
                                                  productCat));
                _logger.info("Pduct id : " + _productCategoryId);
                return "submit";

            } else {
                // give validation message
                AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
                ctx.addPartialTarget(getSupplierExistanceMessage());
                supplierExistanceMessageString =
                    "You are already registered as a partner with Airtel. Please go to login using your existing account";
                return "";
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        return "";
    }

    
    /**
     * method to load list of values of product and services
     */
    public void loadProductsAndServices() {
        _logger.config("Entering method loadProductAndServices");
        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("ProductAndCategoryLookupRVO1Iterator");
            //getting viewobject
            ViewObjectImpl productCategoryVO = (ViewObjectImpl) dcIterator.getViewObject();
            _logger.info("row count :---" + productCategoryVO.getEstimatedRowCount());
            productAndCategory = new ArrayList<SelectItem>();
            productCategoryVO.applyViewCriteria(null);
            productCategoryVO.executeQuery();
            Row row;
            int count = 0;

            _logger.info("List length ::::: " + productAndCategory.size());
            productCategoryVO.applyViewCriteria(null);
            productCategoryVO.executeQuery();
            if (productAndCategory.size() == 0) {
                while (productCategoryVO.hasNext()) {
                    if (count == 0) {
                        row = productCategoryVO.first();
                        count = count + 1;
                    } else {
                        row = productCategoryVO.next();
                    }
                    _logger.info("product name ----" + row.getAttribute("ProductCatName").toString() +
                                 "pRODUCT id :::: " + (oracle.jbo.domain.Number) row.getAttribute("ProductCatId"));
                    productAndCategory.add(new SelectItem((oracle.jbo.domain.Number) row.getAttribute("ProductCatId"),
                                                          row.getAttribute("ProductCatName").toString()));
                }
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method loadProductAndServices");
    }

    /**
     * @param _productCategoryId
     */
    public void setProductCategoryId(oracle.jbo.domain.Number _productCategoryId) {
        this._productCategoryId = _productCategoryId;
    }

    /**
     * @return
     */
    public oracle.jbo.domain.Number getProductCategoryId() {
        return _productCategoryId;
    }

    /**
     * method to check supplier mail validity in unknown buyer flow(signup)
     * @param valueChangeEvent
     */
    public void supplierMailChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Method Invoked supplierMailChangeListener");
        try {
            String str = valueChangeEvent.getNewValue().toString();
            setSupplierMail(str);
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getSupplierExistanceMessage());
            if (str != null && !str.equals("")) {
                if (EmailUtil.validateEmail(str)) {
                    supplierExistanceMessageString = "";
                } else {
                    supplierExistanceMessageString = CommonConstants.EMAIL_NOT_VALID;
                    setIsDisableNotKnowBuyer("true");
                }
            } else {
                supplierExistanceMessageString = "";
                setIsDisableNotKnowBuyer("true");
            }
            validateField();
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method supplierMailChangeListener");
    }

    /**
     * value change listener of company Url field
     * @param valueChangeEvent
     */
    public void companyUrlChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.info("method companyUrlChangeListener has been called");
        setCompanyUrl(valueChangeEvent.getNewValue().toString());
        validateField();
        
        _logger.config("Exit method companyUrlChangeListener");
    }

    /**
     * value change listener method for product and Category services field
     * @param valueChangeEvent
     */
    public void productCategoryChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("method productCategoryChangeListener has been called");
        _logger.info("" + valueChangeEvent.getNewValue());
        setProductCategoryId((oracle.jbo.domain.Number) valueChangeEvent.getNewValue());
        validateField();
        _logger.config("Exit method productCateogryChageListener");
    }
    
    /**
     * method to validate all the fields
     */
    public void validateField() {
        _logger.config("Entering method validateField");
        _logger.info("PRODUCT ID :::: " + _productCategoryId);
        try {
            if ((_supplierMail != null && !_supplierMail.equals("")) &&
                (_companyUrl != null && !_companyUrl.equals("")) && (_productCategoryId != null)) {
                setIsDisableNotKnowBuyer("false");
                _logger.info("if called");
            } else {
                _logger.info("else has been called");
                setIsDisableNotKnowBuyer("true");
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * method to check supplier is already exist or not
     * @param supplierEmail
     * @return
     */
    public boolean validateSupplierEmailExistence(String supplierEmail) {
        _logger.config("Entering method validateSupplierEmailExistence");
        //getting binding of pages
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        //getting iterator
        DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");
        //getting viewobject
        ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
        // getting viewCriteria by criteria name
        ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("UserSearch");
        supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndEmail", _supplierMail);
        //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
        supplierCrdentialDetailsVO.applyViewCriteria(null);
        supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
        supplierCrdentialDetailsVO.executeQuery();
        if (supplierCrdentialDetailsVO.first() != null) {
            _logger.config("supplier email already exists");
            return true; //supplier email already exists
            
        } else {
            _logger.config("supplier email already exists false");
            return false; // create as supplier
        }
    }


    /**
     * on continue button click method
     * @return
     */
    public String onCountinueClick() {
        
        _logger.config("Entering method onContinueClick");
        //getting binding of pages
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        //getting iterator
        DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BuyerLookupRVO1Iterator");
        //getting viewobject
        ViewObjectImpl buyerDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
        // getting viewCriteria by criteria name
        ViewCriteria emailSearchVC = buyerDetailsVO.getViewCriteria("BuyerLookupRVOCriteria");
        buyerDetailsVO.setNamedWhereClauseParam("buyerEmail", _buyerMail);
        //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
        buyerDetailsVO.applyViewCriteria(null);
        buyerDetailsVO.applyViewCriteria(emailSearchVC);
        buyerDetailsVO.executeQuery();
        AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
        ctx.addPartialTarget(getBuyerEmailDoaminValidationMsg());
        if (buyerDetailsVO.first() != null) {
            _logger.config("buyer email verified");
            buyerEmailDoaminValidationString = "";
            return "continue";
        } else {
            _logger.config("buyer email not verfied");
            buyerEmailDoaminValidationString = "The email address entered is not a valid Airtel buyer's ID";
            return "continueFalse";
        }
    }


    /**
     * password validation method in signup form
     * @param valueChangeEvent
     */
    public void signUpFormPasswordValidation(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method signUpFormPasswordValidation");
        try {
            setPassword(valueChangeEvent.getNewValue().toString());
            validateSignUpForm();
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getErrorSignUpFormMessage());
            String str = valueChangeEvent.getNewValue().toString();
            if (str != null && !str.equals("")) {
                if (_password.length() < CommonConstants.PASSWORD_LENGTH) {
                    setErrorSignUpMessageString(CommonConstants.PASSWORD_LENGTH_ERROR_MESSAGE);
                    setIsDisableSignupForm("true");
                } else {
                    setErrorSignUpMessageString("");
                }
            } else {
                setErrorSignUpMessageString("");
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method signUpFormPasswordValidation");
    }

    /**
     * method for email validation validation on sign up form
     * @param valueChangeEvent
     */
    public void signUpFormEmailValidation(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method signUpFormEmailValidation");
        
        try {
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getErrorSignUpEmailFormMessage());
            setSupplierMail(valueChangeEvent.getNewValue().toString());
            String str = valueChangeEvent.getNewValue().toString();
            Boolean isRegexValid = CommonConstants.BOOLEAN_FALSE;
            //1. check regex
            if (str != null && !str.equals("")) {
                if (!EmailUtil.validateEmail(str)) {
                    setErrorSignUpEmailMessageString(CommonConstants.EMAIL_NOT_VALID);
                    setIsDisableSignupForm("true");

                } else {
                    setErrorSignUpEmailMessageString("");
                    isRegexValid = CommonConstants.BOOLEAN_TRUE;
                }
            } else {
                setErrorSignUpEmailMessageString("");

            }
            validateSignUpForm();

            //2. check if valid or not.
            if (str != null && !str.equals("")) {
                Boolean isExistingEmailId = validateSupplierEmailExistence(str);
                if (isRegexValid && isExistingEmailId) {
                    //show error message - supplier already exists
                    setErrorSignUpEmailMessageString(CommonConstants.ALREADY_REGISTERED_SUPPLIER);
                    setIsDisableSignupForm("true");
                } else if (isRegexValid && !isExistingEmailId) {
                    setErrorSignUpEmailMessageString("");

                }
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method signUpFormEmailValidation");
    }

    /**
     * metho to validate re entered password on signup form 
     * @param valueChangeEvent
     */
    public void signUpFormPasswordRenterValidation(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method signUpFormPasswordRenterValidation");
        try {
            AdfFacesContext ctx = AdfFacesContext.getCurrentInstance();
            ctx.addPartialTarget(getErrorSignUpFormMessage());
            setRePassword(valueChangeEvent.getNewValue().toString());
            String str = valueChangeEvent.getNewValue().toString();
            if (str != null && !str.equals("")) {
                if ((_password != null ? _password.length() : 0) < CommonConstants.PASSWORD_LENGTH ||
                    (_rePassword != null ? _rePassword.length() : 0) < CommonConstants.PASSWORD_LENGTH) {
                    setErrorSignUpMessageString(CommonConstants.PASSWORD_LENGTH_ERROR_MESSAGE);
                    setIsDisableSignupForm("true");
                } else {
                    if (null != _password && !_password.equals(_rePassword)) {
                        setErrorSignUpMessageString(CommonConstants.PASSWORD_NOT_MATCHED_ERROR_MESSAGE);
                        setIsDisableSignupForm("true");
                    } else {
                        setErrorSignUpMessageString("");

                    }
                    ;
                }

            } else {
                setErrorSignUpMessageString("");
            }
            validateSignUpForm();
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method signUpFormPasswordRenterValidation");
    }


    /**
     * method to check entered mobile number is already registered or not
     * @param mobNo
     * @return
     */
    public boolean isAlreadyRegisterMobileNo(String mobNo) {
        _logger.config("Entering method isAlreadyRegistredMobileNo :: mobNo :: "+ mobNo);
        //validate if provided contact details has been registered already
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierCredentialsDetailsRVO1Iterator");
        //getting viewobject
        ViewObjectImpl supplierCrdentialDetailsVO = (ViewObjectImpl) dcIterator.getViewObject();
        // getting viewCriteria by criteria name
        ViewCriteria supplierCredVC = supplierCrdentialDetailsVO.getViewCriteria("SearchMobile");
        supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndMobile", mobNo);
        //supplierCrdentialDetailsVO.setNamedWhereClauseParam("bndPassword", _password);
        supplierCrdentialDetailsVO.applyViewCriteria(null);
        supplierCrdentialDetailsVO.applyViewCriteria(supplierCredVC);
        supplierCrdentialDetailsVO.executeQuery();
        if (supplierCrdentialDetailsVO.first() == null) {
            _logger.config("Mobile number does not exist");
            return false;
        } else {
            _logger.config("Entered mobile number already resgistered");
            return true;
        }
    }

    /**
     * @param valueChangeEvent
     */
    public void signUpFormContactDetailsValidation(ValueChangeEvent valueChangeEvent) {
        _logger.config("Inside signUpFormCotactDetailsValidation");
        try {
            String mobNo = valueChangeEvent.getNewValue().toString();
            setSupplierMobile(mobNo);
            _logger.info(mobNo);
            if (mobNo != null && !mobNo.equalsIgnoreCase("")) {
                String mobileExp = CommonConstants.MOBILE_NUMBER_REGEX;
                Pattern mobilePattern = Pattern.compile(mobileExp);
                Matcher mobileMatcher = mobilePattern.matcher(mobNo);
                _logger.info("Mobile Regex::" + mobileMatcher.matches());
                if (mobileMatcher.matches()) {
                    if (!isAlreadyRegisterMobileNo(mobNo)) {
                        showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
                        setShowMobNoWarningMsg("");
                        setIsDisableSignupForm("false");
                    } else {
                        setIsDisableSignupForm("true");
                        setSupplierMobile("");
                        setShowMobNoWarningMsg(CommonConstants.ALREADY_REGISTERED_SUPPLIER);
                        showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                    }
                } else {
                    setIsDisableSignupForm("true");
                    setSupplierMobile("");
                    setShowMobNoWarningMsg(CommonConstants.MOBILE_NUMBER_NOT_VALID);
                    showMobNoWarning = CommonConstants.BOOLEAN_TRUE;
                }
            } else {
                setIsDisableSignupForm("true");
                setShowMobNoWarningMsg("");
                showMobNoWarning = CommonConstants.BOOLEAN_FALSE;
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
            validateSignUpForm();
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method signUpFormContactDetailsValidation");
    }

    /**
     * helper method to validate signup form
     */
    public void validateSignUpForm() {
        _logger.config("Entering method validateSignUpForm");
        try {
            if ((_supplierMail != null && !_supplierMail.equals("")) &&
                (_supplierMobile != null && !_supplierMobile.equals("")) &&
                (_password != null && !_password.equals("")) && (_rePassword != null && !_rePassword.equals("")) &&
                termsAndConditionFlag == CommonConstants.BOOLEAN_TRUE) {
                _logger.info(_supplierMail + "::: " + _supplierMobile + " :::  " + _password + " ::: " + _rePassword +
                             "termsAndConditionFlag ::: " + termsAndConditionFlag);
                setIsDisableSignupForm("false");
            } else {
                setIsDisableSignupForm("true");
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exit method validateSignUpForm");
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


    /**
     * @param termsAndConditionFlag
     */
    public void setTermsAndConditionFlag(Boolean termsAndConditionFlag) {
        this.termsAndConditionFlag = termsAndConditionFlag;

    }

    /**
     * @return
     */
    public Boolean getTermsAndConditionFlag() {
        return termsAndConditionFlag;
    }

    /**
     * method to handle t&c acceptence
     * @return
     */
    public String termsAndConditionAccepted() {
        _logger.config("Entering termsAndCoditionAccepted");
        termsAndConditionFlag = true;
        validateSignUpForm();
        _logger.config("Exit method termsAndCoditionAccepted");
        return "onSigupPage";
    }

    /**
     * method to handle declination of t&c
     * @return
     */
    public String termsAndConditionDecliened() {
        _logger.config("Entering method termsAndConditionDecliened");
        termsAndConditionFlag = false;
        validateSignUpForm();
        _logger.config("Exit method termsAndConditionDecliened");
        return "onSigupPage";
    }

    /**
     * on back button click
     * @return
     */
    public String onBackButtonClick() {
        _logger.config("Entering method onBackButtonClick");
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                          "/pages/login/partnerLogin.jspx");
        } catch (IOException e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * method for resend account activation link
     * @param actionEvent
     */
    public void resendActivationMail(ActionEvent actionEvent) {
        _logger.config("---Resend Activation Link---_supplierMail_" + _supplierMail + "---_supplierUserIdResend" +
                     _supplierUserIdResend);
        this.sendActivationLink(_supplierMail, _supplierUserIdResend);
        _logger.config("Exit method resendActivationMail");
    }

    /**
     * method to validate terms and condition page
     * @param valueChangeEvent
     */
    public void validateTermsAndConditionListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("check box value ::: " + valueChangeEvent.getNewValue().toString());
        setTermsAndConditionFlag(Boolean.parseBoolean(valueChangeEvent.getNewValue().toString()));
        validateSignUpForm();
        _logger.config("Exit method validateTermsAndConditionListener");
    }
}
