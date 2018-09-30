package com.airtel.supplierportal.bean.partnerRegistrationPhase2;

import com.airtel.supplierportal.utility.CommonConstants;

import java.io.Serializable;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class DomesticSiteBean implements Serializable {
    @SuppressWarnings("compatibility:-8400143524448016394")
    private static final long serialVersionUID = 1L;
    
    private static final ADFLogger _logger = ADFLogger.createADFLogger(DomesticSiteBean.class);
    
    
    private Boolean gstnExempt = CommonConstants.BOOLEAN_TRUE;
    private Boolean msmed = CommonConstants.BOOLEAN_TRUE;
    private ComponentReference docUploadParentDomesticPGL;
    
    
    
    private String _gstnNo;
    private String _countryCode;
    private String _pinCode;
    private String _city;
    private String _state;
    private String _address;
    private String _firstName;
    private String _lastName;
    private String _mobileNumber;
    private String _contactEmail;
    private String _remittanceEmail;
    
    
    // bank details fields
    private String _ifsc;
    private String _bankName;
    private String _currencyCode;
    private String _accountNumber;
    private String _reAccountNumber;
    private String _accountType;
    
    
    
    
    
   
    // TODO implement auto switch between domestic to non domestic on change of country form India to foreign
    
    // TODO implement auto populate bank name by entring ifsc
    
    /**
     * default constructor of bean class PartnerRegistrationPhase2
     */
    public DomesticSiteBean() {
        _logger.info("Inside the default constructor of bean class PartnerRegistrationPhase2");
    }
    
    
    
    /**
     *
     * @param gstnExempt
     */
    public void setGstnExempt(Boolean gstnExempt) {
        this.gstnExempt = gstnExempt;
    }
    
    /**
     *
     * @return
     */
    public Boolean getGstnExempt() {
        return gstnExempt;
    }
    
    /**
     *
     * @param msmed
     */
    public void setMsmed(Boolean msmed) {
        this.msmed = msmed;
    }
    
    /**
     *
     * @return
     */
    public Boolean getMsmed() {
        return msmed;
    }

    /**
     *
     * @param docUploadParentDomesticPGL
     */
    public void setDocUploadParentDomesticPGL(RichPanelGroupLayout docUploadParentDomesticPGL) {
        this.docUploadParentDomesticPGL = ComponentReference.newUIComponentReference(docUploadParentDomesticPGL);;
    }
    
    /**
     *
     * @return
     */
    public RichPanelGroupLayout getDocUploadParentDomesticPGL() {
       
        if (docUploadParentDomesticPGL != null) {
            return (RichPanelGroupLayout) docUploadParentDomesticPGL.getComponent();
        }
        return null;
    }

    /**
     *
     * @param _gstnNo
     */
    public void setGstnNo(String _gstnNo) {
        this._gstnNo = _gstnNo;
    }
    /**
     *
     * @return
     */
    public String getGstnNo() {
        return _gstnNo;
    }
    /**
     *
     * @param _countryCode
     */
    public void setCountryCode(String _countryCode) {
        this._countryCode = _countryCode;
    }
    /**
     *
     * @return
     */
    public String getCountryCode() {
        return _countryCode;
    }
    /**
     *
     * @param _pinCode
     */
    public void setPinCode(String _pinCode) {
        this._pinCode = _pinCode;
    }
    /**
     *
     * @return
     */
    public String getPinCode() {
        return _pinCode;
    }
    /**
     *
     * @param _city
     */
    public void setCity(String _city) {
        this._city = _city;
    }
    /**
     *
     * @return
     */
    public String getCity() {
        return _city;
    }
    /**
     *
     * @param _state
     */
    public void setState(String _state) {
        this._state = _state;
    }
    /**
     *
     * @return
     */
    public String getState() {
        return _state;
    }
    /**
     *
     * @param _address
     */
    public void setAddress(String _address) {
        this._address = _address;
    }
    /**
     *
     * @return
     */
    public String getAddress() {
        return _address;
    }
    /**
     *
     * @param _firstName
     */
    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }
    /**
     *
     * @return
     */
    public String getFirstName() {
        return _firstName;
    }
    /**
     *
     * @param _lastName
     */
    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }
    /**
     *
     * @return
     */
    public String getLastName() {
        return _lastName;
    }
    /**
     *
     * @param _mobileNumber
     */
    public void setMobileNumber(String _mobileNumber) {
        this._mobileNumber = _mobileNumber;
    }
    /**
     *
     * @return
     */
    public String getMobileNumber() {
        return _mobileNumber;
    }
    /**
     *
     * @param _contactEmail
     */
    public void setContactEmail(String _contactEmail) {
        this._contactEmail = _contactEmail;
    }
    /**
     *
     * @return
     */
    public String getContactEmail() {
        return _contactEmail;
    }
    /**
     *
     * @param _remittanceEmail
     */
    public void setRemittanceEmail(String _remittanceEmail) {
        this._remittanceEmail = _remittanceEmail;
    }
    /**
     *
     * @return
     */
    public String getRemittanceEmail() {
        return _remittanceEmail;
    }
    /**
     *
     * @param _ifsc
     */
    public void setIfsc(String _ifsc) {
        this._ifsc = _ifsc;
    }
    /**
     *
     * @return
     */
    public String getIfsc() {
        return _ifsc;
    }
    /**
     *
     * @param _bankName
     */
    public void setBankName(String _bankName) {
        this._bankName = _bankName;
    }
    /**
     *
     * @return
     */
    public String getBankName() {
        return _bankName;
    }
    /**
     *
     * @param _currencyCode
     */
    public void setCurrencyCode(String _currencyCode) {
        this._currencyCode = _currencyCode;
    }
    /**
     *
     * @return
     */
    public String getCurrencyCode() {
        return _currencyCode;
    }
    /**
     *
     * @param _accountNumber
     */
    public void setAccountNumber(String _accountNumber) {
        this._accountNumber = _accountNumber;
    }
    /**
     *
     * @return
     */
    public String getAccountNumber() {
        return _accountNumber;
    }
    /**
     *
     * @param _reAccountNumber
     */
    public void setReAccountNumber(String _reAccountNumber) {
        this._reAccountNumber = _reAccountNumber;
    }
    /**
     *
     * @return
     */
    public String getReAccountNumber() {
        return _reAccountNumber;
    }
    /**
     *
     * @param _accountType
     */
    public void setAccountType(String _accountType) {
        this._accountType = _accountType;
    }
    /**
     *
     * @return
     */
    public String getAccountType() {
        return _accountType;
    }

    /**
     * value change listner mathod on GSTN EXEMPT radio group to handle the enable and disable the document upload component
     * for the GSTN EXEMPT certificate
     * @param valueChangeEvent
     */
    public void gstnExemptRadioVC(ValueChangeEvent valueChangeEvent) {
       _logger.info("Entering value change listner method gstnExemptRadioVC of GSTN EXEMPT radio group ");
       
       try {
            String newValue = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "";
            _logger.info("new changed value is :: " + newValue);

            if (!newValue.equalsIgnoreCase("") && newValue.equalsIgnoreCase("yes")) {
                gstnExempt = CommonConstants.BOOLEAN_TRUE;
                // refreshing parent pgl which contains the upload components
                refreshComponent(getDocUploadParentDomesticPGL());
            } else if (!newValue.equalsIgnoreCase("") && newValue.equalsIgnoreCase("no")) {
                gstnExempt = CommonConstants.BOOLEAN_FALSE;
                // refreshing parent pgl which contains the upload components
                refreshComponent(getDocUploadParentDomesticPGL());
            }
        } catch (Exception e) {
            _logger.info("Exception raised in gstnExemptionRadioVC method :: gstnExempt value :: "+gstnExempt+" "+e.getMessage());
            e.printStackTrace();
        }
       
       
       _logger.info("Exit the method gstnExemptRadioVC");
       
    }
    
    /**
     * value change listner mathod on MSMED radio group to handle the enable and disable the document upload component
     * or the MSMED certificate
     * @param valueChangeEvent
     */
    public void msmedRadioVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Entering the method msmedRadioVC on radio change of msmed");
        
        try {
            String newValue = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "";
            _logger.info("new changed value is :: " + newValue);

            if (!newValue.equalsIgnoreCase("") && newValue.equalsIgnoreCase("yes")) {
                msmed = CommonConstants.BOOLEAN_TRUE;
                // refreshing parent pgl which contains the upload components
                refreshComponent(getDocUploadParentDomesticPGL());
            } else if (!newValue.equalsIgnoreCase("") && newValue.equalsIgnoreCase("no")) {
                msmed = CommonConstants.BOOLEAN_FALSE;
                // refreshing parent pgl which contains the upload components
                refreshComponent(getDocUploadParentDomesticPGL());
            }
        } catch (Exception e) {
            _logger.info("Exception raised in msmedRadioVC method :: gstnExempt value :: "+msmed+" "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method msmedRadioVC");
    }
    
    
    
    /**
     * code for refreshing the ui component
     * @param uiComp
     */
    private void refreshComponent(UIComponent uiComp) {
        if (uiComp != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(uiComp); //refreshing ui components
        }
    }
    /**
     *
     * @param actionEvent
     */
    public void onSubmitDomesticSite(ActionEvent actionEvent) {
        _logger.info("Entering the method onSubmitDomesticSite");
        
        
        _logger.info("GSTN No ::"+getGstnNo());
        _logger.info("Account no :: "+getAccountNumber());
        
        // TODO implemet submit button logic here , i.e create new row of domestic site in the sites table
        
        _logger.info("Exit the method onSubmitDomesticSite");
    }
    /**
     * country value change listner method, used for swiching the page if country is other than india
     * @param valueChangeEvent
     */
    public void countryVC(ValueChangeEvent valueChangeEvent) {
        _logger.info("Entering the method countryVC");
        
        String newCountryCode = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "" ;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ControllerContext ccontext = ControllerContext.getInstance();
        
        _logger.info("New Country code after change ::: "+ newCountryCode);
        
        if(!newCountryCode.equalsIgnoreCase("") && !newCountryCode.equalsIgnoreCase("IN")){
        NavigationHandler nh = fctx.getApplication().getNavigationHandler();
        nh.handleNavigation(fctx, null, "goToNonDomestic");
        }
        _logger.info("Exit the method countryVC");
        
        
    }
}
