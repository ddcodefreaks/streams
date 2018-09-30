package com.airtel.supplierportal.bean.partnerorgdetails;

import com.airtel.supplierportal.pojo.nsdl.VerifyCustomerPanResp;
import com.airtel.supplierportal.pojo.session.SupplierSession;
import com.airtel.supplierportal.utility.CommonConstants;
import com.airtel.supplierportal.utility.DmsUtil;
import com.airtel.supplierportal.utility.EmailUtil;
import com.airtel.supplierportal.utility.NsdlUtil;
import com.airtel.supplierportal.utility.SupplierUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.nio.charset.Charset;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.component.UIXEditableValue;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.util.ComponentReference;

public class PartnerOrgDetails implements Serializable {
    @SuppressWarnings("compatibility:-3694983429112384919")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(PartnerOrgDetails.class);
    // for select one choice list in product and category lov
    private List<SelectItem> countryList;
    // isStgate and City Disabled
    private boolean isDisabled = true;
    private boolean isPanDisabled = true;
    private String _pinCode;
    private String _state;
    private String _city;
    private String _mail;
    private String _contact;
    private String _organizationName;
    private String _pan;
    private String _countryCode;
    private String _firstLineAddress;
    private String _companyUrl;
    private String _partnerUserId;
    private String _partnerVendorId;
    private String _wkfOwner;
    // PAN validation error me
    private String _panInvalidError;
    // warnig message for the mendatory field voilation
    private String _organizationNameWarningMsg;
    private String _panWarningMsg;
    private String _countryWarningMsg;
    private String _pinCodeWarningMsg;
    private String _cityWarningMsg;
    private String _stateWarningMsg;
    private String _firstLineAddressWarningMsg;
    private String _companyUrlWarningMsg;
    private boolean isPanProprietory = CommonConstants.BOOLEAN_FALSE;
    private boolean panCardValidationFlag = CommonConstants.BOOLEAN_FALSE;
    private ComponentReference doc1Pan;
    private transient UploadedFile doc1PanUploadedfile = null;
    private ComponentReference doc2CompanyRegistration;
    //   private transient UploadedFile doc2CompanyRegistrationUploadedfile = null;
    private ComponentReference doc3Proprietorship;
    private transient UploadedFile doc3ProprietorshipUploadedfile = null;
    private String doc1FileName = "Upload PAN Card for Domestic/ Company Registration Certificate for Foreign Partner";
    //    private String doc2FileName = "Upload company registration certificate copy";
    private String doc3FileName = "Provide document establishing linkage between PAN no. and organization name";
    private String _panContentId = "";
    private String _companyRegistrationContentId = "";
    private String _proprietorshipContentId = "";
    private String _panDocWarningMsg;
    private String _companyRegistrationDocWarningMsg;
    private String _proprietorshipDocWarningMsg;
    private Boolean isUrlValid;
    
    
    /**
     * method which laods before partnerOrgDetailBTF
     * @return
     */

    public String onLoadBtf() {
        _logger.info("Inside onLoadBtf..");
        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("CountryLookupRVO1Iterator");
            //getting viewobject
            ViewObjectImpl countryListVO = (ViewObjectImpl) dcIterator.getViewObject();
            _logger.info("row count :---" + countryListVO.getEstimatedRowCount());
            countryList = new ArrayList<SelectItem>();
            Row row;
            int count = 0;
            if (countryList.size() == 0) {
                while (countryListVO.hasNext()) {
                    if (count == 0) {
                        row = countryListVO.first();
                        count = count + 1;
                    } else {
                        row = countryListVO.next();
                    }
                    countryList.add(new SelectItem(row.getAttribute("TerritoryCode").toString(),
                                                   row.getAttribute("TerritoryShortName").toString()));
                }
            }
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpSession session = (HttpSession) ectx.getSession(true);
            SupplierSession supplierObj = (SupplierSession) session.getAttribute("SupplierSessionObject");
            _mail = supplierObj.getEmail();
            _contact = String.valueOf(supplierObj.getMobileNumber());
            _partnerUserId = String.valueOf(supplierObj.getPartnerUserId());
            _partnerVendorId = String.valueOf(supplierObj.getPartnerVendorId());
            _wkfOwner = supplierObj.getBuyerMail();
            // populate data in fields if organization for the supplier is alreasdy created with status 3
            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            ViewObjectImpl wkfSupplierVO = (ViewObjectImpl) dcIterator1.getViewObject();
            ViewCriteria searchOrgVC = wkfSupplierVO.getViewCriteria("PartnerOrgStatusVC");
            wkfSupplierVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
            wkfSupplierVO.applyViewCriteria(null);
            wkfSupplierVO.applyViewCriteria(searchOrgVC);
            wkfSupplierVO.executeQuery();
            if (wkfSupplierVO.first() != null) {
                oracle.jbo.domain.Number statusNumber =
                    wkfSupplierVO.first().getAttribute("Status") != null ?
                    (oracle.jbo.domain.Number) wkfSupplierVO.first().getAttribute("Status") :
                    new oracle.jbo.domain.Number(0);
                if (statusNumber.equals(new oracle.jbo.domain.Number(3))) {
                    _organizationName =
                        wkfSupplierVO.first().getAttribute("OrgName") != null ?
                        wkfSupplierVO.first()
                                                                                                             .getAttribute("OrgName")
                                                                                                             .toString() :
                                                  null;
                    _pan = wkfSupplierVO.first().getAttribute("Pan") != null ? wkfSupplierVO.first()
                                                                                            .getAttribute("Pan")
                                                                                            .toString() : null;
                    _countryCode =
                        wkfSupplierVO.first().getAttribute("Country") != null ? wkfSupplierVO.first()
                                                                                                        .getAttribute("Country")
                                                                                                        .toString() :
                        null;
                    _pinCode = wkfSupplierVO.first().getAttribute("Pincode") != null ? wkfSupplierVO.first()
                                                                                                    .getAttribute("Pincode")
                                                                                                    .toString() : null;
                    _city = wkfSupplierVO.first().getAttribute("City") != null ? wkfSupplierVO.first()
                                                                                              .getAttribute("City")
                                                                                              .toString() : null;
                    _state = wkfSupplierVO.first().getAttribute("PState") != null ? wkfSupplierVO.first()
                                                                                                 .getAttribute("PState")
                                                                                                 .toString() : null;
                    _firstLineAddress =
                        wkfSupplierVO.first().getAttribute("AddLine1") != null ?
                        wkfSupplierVO.first()
                                                                                                              .getAttribute("AddLine1")
                                                                                                              .toString() :
                                                  null;
                    _companyUrl =
                        wkfSupplierVO.first().getAttribute("CompanyUrl") != null ? wkfSupplierVO.first()
                                                                                                          .getAttribute("CompanyUrl")
                                                                                                          .toString() :
                        null;
                    if (_countryCode != null && !_countryCode.equalsIgnoreCase("IN")) {
                        isDisabled = CommonConstants.BOOLEAN_FALSE;
                    }
                    if (_countryCode != null && _countryCode.equalsIgnoreCase("IN")) {
                        isPanDisabled = CommonConstants.BOOLEAN_FALSE;
                        this.checkProprietorship(_pan);
                    }
                    return "detailNotSubmited";
                } else if (statusNumber.equals(new oracle.jbo.domain.Number(4))) {
                    String countryCode =
                        wkfSupplierVO.first().getAttribute("Country") != null ?
                        wkfSupplierVO.first()
                                                                                                              .getAttribute("Country")
                                                                                                              .toString() :
                                                  null;
                    if (!countryCode.equals("IN")) {
                        return "detailsSubmittedNonDomestic";
                    } else {
                        return "detailSubmited";
                    }
                }
            } else {

                return "detailNotSubmited";
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method onLoadgBtf");
        return null;
    }
    
    
    /**
     *
     * @param countryList
     */

    public void setCountryList(List<SelectItem> countryList) {
        this.countryList = countryList;
    }
    
    
    /**
     *
     * @return
     */
    public List<SelectItem> getCountryList() {
        return countryList;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
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
     * @param _organizationName
     */
    public void setOrganizationName(String _organizationName) {
        this._organizationName = _organizationName;
    }
    
    /**
     *
     * @return
     */
    public String getOrganizationName() {
        return _organizationName;
    }
    
    /**
     *
     * @param _pan
     */
    public void setPan(String _pan) {
        this._pan = _pan;
    }
    
    /**
     *
     * @return
     */
    public String getPan() {
        return _pan;
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
     * @param _firstLineAddress
     */
    public void setFirstLineAddress(String _firstLineAddress) {
        this._firstLineAddress = _firstLineAddress;
    }
    
    /**
     *
     * @return
     */
    public String getFirstLineAddress() {
        return _firstLineAddress;
    }
    
    /**
     *
     * @param _companyUrl
     */
    public void setCompanyUrl(String _companyUrl) {
        this._companyUrl = _companyUrl;
    }
    
    /**
     *
     * @return
     */
    public String getCompanyUrl() {
        return _companyUrl;
    }
    
    /**
     *
     * @param _mail
     */
    public void setMail(String _mail) {
        this._mail = _mail;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return _mail;
    }
    
    /**
     *
     * @param _contact
     */
    public void setContact(String _contact) {
        this._contact = _contact;
    }
    
    
    /**
     *
     * @return
     */
    public String getContact() {
        return _contact;
    }

    /**
     *
     * @param _partnerUserId
     */
    public void setPartnerUserId(String _partnerUserId) {
        this._partnerUserId = _partnerUserId;
    }
    
    /**
     *
     * @return
     */
    public String getPartnerUserId() {
        return _partnerUserId;
    }
    
    
    /**
     *
     * @param _partnerVendorId
     */
    public void setPartnerVendorId(String _partnerVendorId) {
        this._partnerVendorId = _partnerVendorId;
    }
    
    /**
     *
     * @return
     */
    public String getPartnerVendorId() {
        return _partnerVendorId;
    }
    
    /**
     *
     * @param _wkfOwner
     */
    public void setWkfOwner(String _wkfOwner) {
        this._wkfOwner = _wkfOwner;
    }
    
    /**
     *
     * @return
     */
    public String getWkfOwner() {
        return _wkfOwner;
    }
    
    /**
     *
     * @param _panInvalidError
     */
    public void setPanInvalidError(String _panInvalidError) {
        this._panInvalidError = _panInvalidError;
    }
    
    /**
     *
     * @return
     */
    public String getPanInvalidError() {
        return _panInvalidError;
    }


    /**
     * value change listner method on pin code field which auto-populates 
     * the city and state based on entered pin code in case domestinc 
     * supplier i.e country = INDIA
     * @param valueChangeEvent
     */
    public void pinCodeChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method pinCodeChangeListener");
        try {
            /**
             * Added this if block to block autopopulating feature of city and state by entering pin code,
             *  in case of internation vendor
             * Date : 27-jul-2018.
             * Added By : Rupak
             */
            String newPinCode = valueChangeEvent.getNewValue().toString();
            if (isNumeric(newPinCode)) {

                if (valueChangeEvent.getNewValue().toString() != null && !valueChangeEvent.getNewValue()
                                                                                          .toString()
                                                                                          .equalsIgnoreCase("")) {
                    if ((_countryCode != null ? _countryCode : "").equalsIgnoreCase("IN")) {
                        BindingContainer bindings = getBindings();
                        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                        //getting iterator
                        DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("PinCityStateLookupRVO1Iterator");
                        //getting viewobject
                        ViewObjectImpl pinCityStateVO = (ViewObjectImpl) dcIterator.getViewObject();
                        ViewCriteria pinCityStateVC = pinCityStateVO.getViewCriteria("CityStateCriteria");
                        pinCityStateVO.setNamedWhereClauseParam("bndPinCode", newPinCode);
                        pinCityStateVO.applyViewCriteria(null);
                        pinCityStateVO.applyViewCriteria(pinCityStateVC);
                        pinCityStateVO.executeQuery();
                        if (pinCityStateVO.first() != null) {
                            _state = pinCityStateVO.first()
                                                   .getAttribute("State")
                                                   .toString();
                            setStateWarningMsg("");
                            _city = pinCityStateVO.first()
                                                  .getAttribute("City")
                                                  .toString();
                            setCityWarningMsg("");
                            setPinCodeWarningMsg("");
                        } else {
                            setPinCodeWarningMsg(CommonConstants.INVALID_PINCODE);
                            setCity("");
                            setState("");
                        }
                    } else {
                        setPinCodeWarningMsg("");
                    }
                } else {
                    setCity("");
                    setState("");
                    setPinCodeWarningMsg(CommonConstants.FIELD_IS_MANDATORY);
                }
            } else {
                setPinCodeWarningMsg(CommonConstants.INVALID_PINCODE);
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        refreshComponent(valueChangeEvent.getComponent().getParent());
        
        _logger.config("Exit method pinCodeChangeListener");
    }
    
    
    

    /**
     * on save button click method
     * saves the organization detials in the database
     * @param actionEvent
     */
    public void onSaveButtonClick(ActionEvent actionEvent) {
        
        _logger.config("Entering mehtod onSaveButtonClick");

        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            ViewObjectImpl wkfSupplierVO = (ViewObjectImpl) dcIterator1.getViewObject();
            wkfSupplierVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
            ViewCriteria vc = wkfSupplierVO.getViewCriteria("PartnerOrgStatusVC");
            wkfSupplierVO.applyViewCriteria(null);
            wkfSupplierVO.applyViewCriteria(vc);
            wkfSupplierVO.executeQuery();
            if (wkfSupplierVO.first() != null) {
                OperationBinding updateOrgDetails = bindings.getOperationBinding("updateOrgDetails");
                updateOrgDetails.getParamsMap().put("_partnerUserID", _partnerUserId);
                updateOrgDetails.getParamsMap().put("_partnerVendorId", _partnerVendorId);
                updateOrgDetails.getParamsMap().put("_partnerEmail", _mail);
                updateOrgDetails.getParamsMap().put("_partnerMobile", _contact);
                updateOrgDetails.getParamsMap().put("_wkfOwner", _wkfOwner);
                updateOrgDetails.getParamsMap().put("_organizationName", _organizationName);
                updateOrgDetails.getParamsMap().put("_pan", _pan);
                updateOrgDetails.getParamsMap().put("_countryCode", _countryCode);
                updateOrgDetails.getParamsMap().put("_city", _city);
                updateOrgDetails.getParamsMap().put("_state", _state);
                updateOrgDetails.getParamsMap().put("_pinCode", _pinCode);
                updateOrgDetails.getParamsMap().put("_firstLineAddress", _firstLineAddress);
                updateOrgDetails.getParamsMap().put("_companyUrl", _companyUrl);
                updateOrgDetails.getParamsMap().put("_attribute1", null);
                updateOrgDetails.getParamsMap().put("_attribute2", null);
                updateOrgDetails.getParamsMap().put("_attribute3", null);
                Object result = updateOrgDetails.execute();
            } else {
                wkfSupplierVO.applyViewCriteria(null);
                wkfSupplierVO.executeQuery();
                //            long indexBase = 100 + wkfSupplierVO.getEstimatedRowCount();
                OperationBinding createOrgDetails = bindings.getOperationBinding("createOrgDetails");
                //            createOrgDetails.getParamsMap().put("wkfIdBaseIndex", indexBase);
                createOrgDetails.getParamsMap().put("_partnerUserID", _partnerUserId);
                createOrgDetails.getParamsMap().put("_partnerVendorId", _partnerVendorId);
                createOrgDetails.getParamsMap().put("_partnerEmail", _mail);
                createOrgDetails.getParamsMap().put("_partnerMobile", _contact);
                createOrgDetails.getParamsMap().put("_wkfOwner", _wkfOwner);
                createOrgDetails.getParamsMap().put("_organizationName", _organizationName);
                createOrgDetails.getParamsMap().put("_pan", _pan);
                createOrgDetails.getParamsMap().put("_countryCode", _countryCode);
                createOrgDetails.getParamsMap().put("_city", _city);
                createOrgDetails.getParamsMap().put("_state", _state);
                createOrgDetails.getParamsMap().put("_pinCode", _pinCode);
                createOrgDetails.getParamsMap().put("_firstLineAddress", _firstLineAddress);
                createOrgDetails.getParamsMap().put("_companyUrl", _companyUrl);
                createOrgDetails.getParamsMap().put("_attribute1", null);
                createOrgDetails.getParamsMap().put("_attribute2", null);
                createOrgDetails.getParamsMap().put("_attribute3", null);
                Object result = createOrgDetails.execute();
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        _logger.config("Exit method onSaveButtonClick");
    }
    
    
    /**
     * on submit button click method
     * submitts the organization details in workflow table
     * @return
     */
    public String onSubmitButtonClick() {
        
        
        // 1. check if mandatory fields are present or not
        // 2. check if Supplier exist in EBS or not?
        //      if yes, give error supplier already exists.
        //      else if supplier does not exist,
        // 3. check if country is IN - is the pan valid? if Yes, Go ahead, else give error for invalid pan
        // 4. Is document upload success ??
        //      If yes, then submit Organization details
        //      Else give error message.
        
        _logger.config("Entering method onSubmitButtonClick");
        
        Boolean doSubmit = CommonConstants.BOOLEAN_FALSE;
        Boolean areFieldsValid = checkMandatoryFieldValidation() && isCompanyUrlValid(getCompanyUrl());
        if (!areFieldsValid) {
            return "notValid";
        }

        Boolean tmpSupplierExistsInWKFTbl = acceptedSupplierExistsInWKFTbl(_organizationName, _countryCode, _pinCode);
        //        Boolean tmpSupplierExists = checkIfSupplierExists(_organizationName);
        //        Boolean isSupplierValid =
        //            (tmpSupplierExistsInWKFTbl || tmpSupplierExists) == CommonConstants.BOOLEAN_FALSE ?
        //            CommonConstants.BOOLEAN_TRUE : CommonConstants.BOOLEAN_FALSE;
        Boolean isSupplierValid =
            tmpSupplierExistsInWKFTbl == CommonConstants.BOOLEAN_FALSE ? CommonConstants.BOOLEAN_TRUE :
            CommonConstants.BOOLEAN_FALSE;
        if (!isSupplierValid) {
            setOrganizationNameWarningMsg(CommonConstants.SUPPLIER_EXISTS_MSG);
            return "notValid";
        }

        Boolean isCountryIndia =
            _countryCode.equalsIgnoreCase("IN") ? CommonConstants.BOOLEAN_TRUE : CommonConstants.BOOLEAN_FALSE;
        Boolean isPANValid = isCountryIndia ? checkPANValid(_pan, _organizationName) : CommonConstants.BOOLEAN_FALSE;

        if (!isPANValid && _countryCode.equalsIgnoreCase("IN")) {
            return "notValid";
        }

        Boolean isDocUploaded = uploadDocuments();
        _logger.info("-----------Fields ARE VALID _________" + areFieldsValid);
        _logger.info("-----------Supplier is VALID ________" + isSupplierValid);
        _logger.info("-----------Country is INDIA _________" + isCountryIndia);
        _logger.info("-----------PAN is VALID _____________" + isPANValid);
        _logger.info("-----------Document Uploaded ________" + isDocUploaded);


        if (!isDocUploaded) {
            FacesContext ctx = FacesContext.getCurrentInstance();

            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
            session.invalidate();

            ExternalContext externalContext = ctx.getExternalContext();
            externalContext.setResponseStatus(HttpServletResponse.SC_FORBIDDEN);
            //            ctx.responseComplete();
            return null;
        } else {
            doSubmit = CommonConstants.BOOLEAN_TRUE;
        }

        if (doSubmit) {

            BindingContainer bindings = getBindings();
            OperationBinding updateOrgDetgailsStatus = bindings.getOperationBinding("updateOrgDetgailsStatus");

            DCBindingContainer dcBinding = (DCBindingContainer) bindings;

            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            ViewObjectImpl wkfSupplierVO = (ViewObjectImpl) dcIterator1.getViewObject();
            ViewCriteria vc = wkfSupplierVO.getViewCriteria("PartnerOrgStatusVC");
            wkfSupplierVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
            wkfSupplierVO.applyViewCriteria(null);
            wkfSupplierVO.applyViewCriteria(vc);
            wkfSupplierVO.executeQuery();

            if (wkfSupplierVO.first() != null) {
                // update all fields with current values
                OperationBinding updateOrgDetails = bindings.getOperationBinding("updateOrgDetails");
                updateOrgDetails.getParamsMap().put("_partnerUserID", _partnerUserId);
                updateOrgDetails.getParamsMap().put("_partnerVendorId", _partnerVendorId);
                updateOrgDetails.getParamsMap().put("_partnerEmail", _mail);
                updateOrgDetails.getParamsMap().put("_partnerMobile", _contact);
                updateOrgDetails.getParamsMap().put("_wkfOwner", _wkfOwner);
                updateOrgDetails.getParamsMap().put("_organizationName", _organizationName);
                updateOrgDetails.getParamsMap().put("_pan", _pan);
                updateOrgDetails.getParamsMap().put("_countryCode", _countryCode);
                updateOrgDetails.getParamsMap().put("_city", _city);
                updateOrgDetails.getParamsMap().put("_state", _state);
                updateOrgDetails.getParamsMap().put("_pinCode", _pinCode);
                updateOrgDetails.getParamsMap().put("_firstLineAddress", _firstLineAddress);
                updateOrgDetails.getParamsMap().put("_companyUrl", _companyUrl);
                updateOrgDetails.getParamsMap().put("_attribute1", _panContentId.isEmpty() ? null : _panContentId);
                updateOrgDetails.getParamsMap()
                    .put("_attribute2", _companyRegistrationContentId.isEmpty() ? null : _companyRegistrationContentId);
                updateOrgDetails.getParamsMap()
                    .put("_attribute3", _proprietorshipContentId.isEmpty() ? null : _proprietorshipContentId);
                Object updateOrgDetailsResult = updateOrgDetails.execute();
                // end update

                updateOrgDetgailsStatus.getParamsMap().put("_partnerVendorId", _partnerVendorId);
                Object result = updateOrgDetgailsStatus.execute();
                // Send mail to Buyer to notifying that Request for the supplier creation is pending
                EmailUtil.sendEmail(_wkfOwner, CommonConstants.NOTIFY_BUYER_SENDER_EMAIL,
                                    CommonConstants.NOTIFY_BUYER_EMAIL_SUBJECT,
                                    String.format(CommonConstants.NOTIFY_BUYER_EMAIL_BODY, _organizationName, _contact,
                                                  _mail, _companyUrl, CommonConstants.BUYER_LOGIN_URL));
                // Send mail to Supplier to notify that request has been submitted and pending for approval from buyer side
                EmailUtil.sendEmail(_mail, CommonConstants.NOTIFY_SUPPLIER_SENDER_EMAIL,
                                    CommonConstants.NOTIFY_SUPPLIER_EMAIL_SUBJECT,
                                    CommonConstants.NOTIFY_SUPPLIER_EMAIL_BODY);
            } else {
                createRowOnSubmit();
                updateOrgDetgailsStatus.getParamsMap().put("_partnerVendorId", _partnerVendorId);
                Object result = updateOrgDetgailsStatus.execute();

                // Send mail to Buyer to notifying that Request for the supplier creation is pending
                EmailUtil.sendEmail(_wkfOwner, CommonConstants.NOTIFY_BUYER_SENDER_EMAIL,
                                    CommonConstants.NOTIFY_BUYER_EMAIL_SUBJECT,
                                    String.format(CommonConstants.NOTIFY_BUYER_EMAIL_BODY, _organizationName, _contact,
                                                  _mail, _companyUrl, CommonConstants.BUYER_LOGIN_URL));


                // Send mail to Supplier to notify that request has been submitted and pending for approval from buyer side
                EmailUtil.sendEmail(_mail, CommonConstants.NOTIFY_SUPPLIER_SENDER_EMAIL,
                                    CommonConstants.NOTIFY_SUPPLIER_EMAIL_SUBJECT,
                                    CommonConstants.NOTIFY_SUPPLIER_EMAIL_BODY);
            }

            if (!_countryCode.equals("IN")) {
                return "nonDomesticSubmit";
            } else {
                return "submit";
            }
        } else {
            return "notValid";
        }

    }

    /**
     * helper method to create new row in workflow table on submit button click 
     * in case of orgation details is not saved in database and user directly clicks submit button 
     * 
     */
    private void createRowOnSubmit() {
        _logger.config("Entering method createRowOnSubmit");
        
        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            ViewObjectImpl wkfSupplierVO = (ViewObjectImpl) dcIterator1.getViewObject();
            wkfSupplierVO.applyViewCriteria(null);
            wkfSupplierVO.executeQuery();
            //        long indexBase = 100 + wkfSupplierVO.getEstimatedRowCount();
            OperationBinding createOrgDetails = bindings.getOperationBinding("createOrgDetails");
            //        createOrgDetails.getParamsMap().put("wkfIdBaseIndex", indexBase);
            createOrgDetails.getParamsMap().put("_partnerUserID", _partnerUserId);
            createOrgDetails.getParamsMap().put("_partnerVendorId", _partnerVendorId);
            createOrgDetails.getParamsMap().put("_partnerEmail", _mail);
            createOrgDetails.getParamsMap().put("_partnerMobile", _contact);
            createOrgDetails.getParamsMap().put("_wkfOwner", _wkfOwner);
            createOrgDetails.getParamsMap().put("_organizationName", _organizationName);
            createOrgDetails.getParamsMap().put("_pan", _pan);
            createOrgDetails.getParamsMap().put("_countryCode", _countryCode);
            createOrgDetails.getParamsMap().put("_city", _city);
            createOrgDetails.getParamsMap().put("_state", _state);
            createOrgDetails.getParamsMap().put("_pinCode", _pinCode);
            createOrgDetails.getParamsMap().put("_firstLineAddress", _firstLineAddress);
            createOrgDetails.getParamsMap().put("_companyUrl", _companyUrl);
            createOrgDetails.getParamsMap().put("_attribute1", _panContentId.isEmpty() ? null : _panContentId);
            createOrgDetails.getParamsMap()
                .put("_attribute2", _companyRegistrationContentId.isEmpty() ? null : _companyRegistrationContentId);
            createOrgDetails.getParamsMap()
                .put("_attribute3", _proprietorshipContentId.isEmpty() ? null : _proprietorshipContentId);
            Object result = createOrgDetails.execute();
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        _logger.config("Exit method createRowOnSubmit");
    }

    
    /**
     * value change listener method on country field
     * @param valueChangeEvent
     */
    public void onCountryChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method onCountryChangeListener");
        _logger.info("first line address ::: " + _firstLineAddress);
        try {
            String _countryCode =
                valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : null;
            if (_countryCode != null && !_countryCode.equals("")) {
                setCountryWarningMsg("");
                setOrganizationName(null);
                setPan(null);
                setPinCode(null);
                setCity(null);
                setState(null);
                setFirstLineAddress(null);
                _organizationNameWarningMsg = "";
                _panInvalidError = "";
                _countryWarningMsg = "";
                _pinCodeWarningMsg = "";
                _cityWarningMsg = "";
                _stateWarningMsg = "";
                _firstLineAddressWarningMsg = "";
                _companyUrlWarningMsg = "";
                isPanProprietory = CommonConstants.BOOLEAN_FALSE;
                if (!_countryCode.equals("IN")) {
                    isDisabled = false;
                    isPanDisabled = true;
                    isPanProprietory = CommonConstants.BOOLEAN_FALSE;
                } else {
                    isDisabled = true;
                    isPanDisabled = false;
                }
            } else {
                // give field required messge
            }
            refreshComponent(valueChangeEvent.getComponent()
                                             .getParent()
                                             .getParent()
                                             .getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        _logger.config("Exitg method onCountryChangeListner");
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
     * value change listner on PAN field 
     * validates the PAN is valid or not using NSDL api
     * @param valueChangeEvent
     */
    public void onChangePanValidate(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method onChangePanValidate");
        
        try {
            String _panNumber = valueChangeEvent.getNewValue().toString();
            String consumerTransactionId = new String(SupplierUtils.generateOTP(6));
            String statusCode = "";
            Boolean isNSDLPanValid = CommonConstants.BOOLEAN_FALSE;
            //change made by Nishant - check PAN against regex
            Boolean matchPanPattern = SupplierUtils.matchPANPattern(_panNumber);
            if (matchPanPattern) {
                try {
                    VerifyCustomerPanResp verifyCustomerPanRespObj =
                        NsdlUtil.fetchPANDetails(consumerTransactionId, _panNumber);
                    statusCode = verifyCustomerPanRespObj.getDataArea()
                                                         .getVerifyCustomerPanDetailsResponse()
                                                         .getPanDetails()
                                                         .get(0)
                                                         .getPanStatus();
                    if (!statusCode.equalsIgnoreCase("f") && !statusCode.equalsIgnoreCase("n")) {
                        String panFullName =
                            verifyCustomerPanRespObj.getDataArea()
                                                                     .getVerifyCustomerPanDetailsResponse()
                                                                     .getPanDetails()
                                                                     .get(0)
                                                                     .getFirstName() +
           verifyCustomerPanRespObj.getDataArea()
                                                                                                               .getVerifyCustomerPanDetailsResponse()
                                                                                                               .getPanDetails()
                                                                                                               .get(0)
                                                                                                               .getMiddleName() +
                        verifyCustomerPanRespObj.getDataArea()
                                                                                                                                                          .getVerifyCustomerPanDetailsResponse()
                                                                                                                                                          .getPanDetails()
                                                                                                                                                          .get(0)
                                                                                                                                                          .getLastName();
                        _logger.info("Printing Full Name received from NSDL API:::" + panFullName);
                        _logger.info("Organisation Name input by User from screen :::" + getOrganizationName());
                        String tmpPanFullName = panFullName.replaceAll("\\s+", "");
                        String tmpOrganisationName =
                            getOrganizationName() != null ? getOrganizationName().replaceAll("\\s+", "") : "";
                        _logger.info("----tmpPanFullName---" + tmpPanFullName + "---tmpOrganisationName---" +
                                     tmpOrganisationName);
                        if (tmpOrganisationName.equalsIgnoreCase(tmpPanFullName)) {
                            //pan is valid
                            isNSDLPanValid = CommonConstants.BOOLEAN_TRUE;
                            panCardValidationFlag = CommonConstants.BOOLEAN_TRUE;
                        } else {
                            //The organization name provided by you doesn’t match with the organization name listed on the PAN.
                            setPanInvalidError("The organization name provided by you doesn’t match with the organization name listed on the PAN.");
                            refreshComponent(valueChangeEvent.getComponent().getParent());
                        }

                    } else {
                        //The PAN # entered is incorrect. Please enter correct PAN No.
                        setPanInvalidError("The PAN # entered is incorrect. Please enter correct PAN No.");
                        refreshComponent(valueChangeEvent.getComponent().getParent());
                    }
                } catch (Exception e) {
                    setPanInvalidError("Dear User, we can’t validate for the PAN information provided by you at this point of time as the NSDL site is down. We are sorry for inconvenience caused. Please try again after sometime.");
                    refreshComponent(valueChangeEvent.getComponent().getParent());
                    e.printStackTrace();
                }
            } else {
                setPanInvalidError("The PAN # entered is incorrect. Please enter correct PAN No.");
                //            setPanInvalidError("Dear User, we can’t validate for the PAN information provided by you at this point of time as the NSDL site is down. We are sorry for inconvenience caused. Please try again after sometime.");
                refreshComponent(valueChangeEvent.getComponent().getParent());
            }
            _logger.info("Status recievd frompan api ::::" + statusCode);
            if (isNSDLPanValid) {

                BindingContainer bindings = getBindings();
                DCBindingContainer dcBinding = (DCBindingContainer) bindings;

                //getting iteratorPanCardNumberLookupRVO1Iterator
                DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("PanCardNumberLookupRVO1Iterator");
                //getting viewobject
                ViewObjectImpl panLookupVO = (ViewObjectImpl) dcIterator1.getViewObject();
                ViewCriteria panLookupVC = panLookupVO.getViewCriteria("SearchPanNoVC");
                panLookupVO.setNamedWhereClauseParam("bndPanNumber", _panNumber);
                panLookupVO.applyViewCriteria(null);
                panLookupVO.applyViewCriteria(panLookupVC);
                panLookupVO.executeQuery();

                if (panLookupVO.first() != null) {
                    _panInvalidError = CommonConstants.INVALID_PAN_ERROR;
                } else {
                    _panInvalidError = "";
                    panCardValidationFlag = CommonConstants.BOOLEAN_TRUE;
                }
            }
            this.checkProprietorship(_panNumber);
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method onChangePanValidate");
    }

    
    /**
     * helper method to check PAN has proprietorship or not
     * @param panNumber
     */
    public void checkProprietorship(String panNumber) {
        _logger.config("Entring method checkProprietorship");
        try {
            // proprietorship check
            if (panNumber != null && !panNumber.equalsIgnoreCase("") &&
                (Character.toString(panNumber.charAt(3)).equalsIgnoreCase("P") &&
                 (_countryCode != null ? _countryCode : "").equalsIgnoreCase("IN"))) {
                _logger.info("--- Inside pan proprietoryship check ");
                isPanProprietory = CommonConstants.BOOLEAN_TRUE;

            } else {
                _logger.info("--- Inside pan proprietoryship check else ::::: ");
                isPanProprietory = CommonConstants.BOOLEAN_FALSE;

            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method checkProprietorship");
    }

    
    /**
     *
     * @param isDisabled
     */
    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }
    
    /**
     *
     * @return
     */
    public boolean isIsDisabled() {
        return isDisabled;
    }
    
    /**
     *
     * @param isPanDisabled
     */
    public void setIsPanDisabled(boolean isPanDisabled) {
        this.isPanDisabled = isPanDisabled;
    }
    
    /**
     *
     * @return
     */
    public boolean isIsPanDisabled() {
        return isPanDisabled;
    }
    
    /**
     *
     * @param _organizationNameWarningMsg
     */
    public void setOrganizationNameWarningMsg(String _organizationNameWarningMsg) {
        this._organizationNameWarningMsg = _organizationNameWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getOrganizationNameWarningMsg() {
        return _organizationNameWarningMsg;
    }
    
    /**
     *
     * @param _panWarningMsg
     */
    public void setPanWarningMsg(String _panWarningMsg) {
        this._panWarningMsg = _panWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getPanWarningMsg() {
        return _panWarningMsg;
    }
    
    /**
     *
     * @param _countryWarningMsg
     */
    public void setCountryWarningMsg(String _countryWarningMsg) {
        this._countryWarningMsg = _countryWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getCountryWarningMsg() {
        return _countryWarningMsg;
    }
    
    
    /**
     *
     * @param _pinCodeWarningMsg
     */
    public void setPinCodeWarningMsg(String _pinCodeWarningMsg) {
        this._pinCodeWarningMsg = _pinCodeWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getPinCodeWarningMsg() {
        return _pinCodeWarningMsg;
    }
    
    /**
     *
     * @param _cityWarningMsg
     */
    public void setCityWarningMsg(String _cityWarningMsg) {
        this._cityWarningMsg = _cityWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getCityWarningMsg() {
        return _cityWarningMsg;
    }

    public void setStateWarningMsg(String _stateWarningMsg) {
        this._stateWarningMsg = _stateWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getStateWarningMsg() {
        return _stateWarningMsg;
    }
    
    /**
     *
     * @param _firstLineAddressWarningMsg
     */
    public void setFirstLineAddressWarningMsg(String _firstLineAddressWarningMsg) {
        this._firstLineAddressWarningMsg = _firstLineAddressWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getFirstLineAddressWarningMsg() {
        return _firstLineAddressWarningMsg;
    }
    
    /**
     *
     * @param _companyUrlWarningMsg
     */
    public void setCompanyUrlWarningMsg(String _companyUrlWarningMsg) {
        this._companyUrlWarningMsg = _companyUrlWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getCompanyUrlWarningMsg() {
        return _companyUrlWarningMsg;
    }
    
    /**
     * helper mehtod to set error messages
     */
    public void setErrorMsg() {
        
        _logger.config("Entering method setErrorMsg");

        try {
            if (_organizationName != null && !_organizationName.equals("")) {
                setOrganizationNameWarningMsg("");

            } else {
                _organizationNameWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }

            if (_pinCode != null && !_pinCode.equals("")) {
                setPinCodeWarningMsg("");
            } else {

                _pinCodeWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }


            if (_countryCode != null && !_countryCode.equals("")) {
                setCountryWarningMsg("");
            } else {

                _countryWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }

            if (_city != null && !_city.equals("")) {
                setCityWarningMsg("");
            } else {

                _cityWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }


            if (_state != null && !_state.equals("")) {
                setStateWarningMsg("");
            } else {
                _stateWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }


            if (_firstLineAddress != null && !_firstLineAddress.equals("")) {
                setFirstLineAddressWarningMsg("");
            } else {
                _firstLineAddressWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }

            if (_companyUrl != null && !_companyUrl.equals("")) {
                setCompanyUrlWarningMsg("");
            } else {
                _companyUrlWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }

            if (doc1PanUploadedfile != null) {
                setPanDocWarningMsg("");
            } else {
                _panDocWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }

            /**
         * commented on 06-08-2018
         * blocking the company registration document upload on OrgDetails page
         */
            //        if (doc2CompanyRegistrationUploadedfile != null ) {
            //            setCompanyRegistrationDocWarningMsg("");
            //        } else {
            //            _companyRegistrationDocWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            //        }

            if (doc3ProprietorshipUploadedfile != null) {
                setProprietorshipDocWarningMsg("");
            } else {
                _proprietorshipDocWarningMsg = CommonConstants.FIELD_IS_MANDATORY;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        _logger.config("Exit method setErrorMsg");

    }
    
    
    /**
     * helper mmethod to check mandatory validation for every fields on Organization details form
     * @return
     */

    // Check validation for the mendatory fields
    public Boolean checkMandatoryFieldValidation() {
        
        _logger.config("Entering method checkMandatoryFieldValidation");
        
        Boolean isValidationPassed = false;
        // Check supplier is domestic or international
        if (_countryCode != null && _countryCode.equals("IN")) {
            _countryWarningMsg = "";
            if (_organizationName != null && !_organizationName.equalsIgnoreCase("") && _pan != null &&
                !_pan.equalsIgnoreCase("") && _pinCode != null && !_pinCode.equalsIgnoreCase("") && _city != null &&
                !_city.equalsIgnoreCase("") && _state != null && !_state.equalsIgnoreCase("") &&
                _firstLineAddress != null && !_firstLineAddress.equalsIgnoreCase("") && _companyUrl != null &&
                !_companyUrl.equalsIgnoreCase("")) {
                isValidationPassed = true;
                _organizationNameWarningMsg = "";
                _panInvalidError = "";
                _countryWarningMsg = "";
                _pinCodeWarningMsg = "";
                _cityWarningMsg = "";
                _stateWarningMsg = "";
                _firstLineAddressWarningMsg = "";
                _companyUrlWarningMsg = "";
                _panDocWarningMsg = "";
                _companyRegistrationDocWarningMsg = "";
                _proprietorshipDocWarningMsg = "";
            } else {
                isValidationPassed = false;
                if (_pan != null && !_pan.equals("")) {
                    setPanInvalidError("");
                } else {
                    setPanInvalidError(CommonConstants.FIELD_IS_MANDATORY);
                }
                setErrorMsg();
            }
        } else if (_countryCode != null && !_countryCode.equals("IN")) {
            if (_organizationName != null && !_organizationName.equalsIgnoreCase("") && _pinCode != null &&
                !_pinCode.equalsIgnoreCase("") && _city != null && !_city.equalsIgnoreCase("") && _state != null &&
                !_state.equalsIgnoreCase("") && _firstLineAddress != null && !_firstLineAddress.equalsIgnoreCase("") &&
                _companyUrl != null && !_companyUrl.equalsIgnoreCase("")) {
                isValidationPassed = true;
                _organizationNameWarningMsg = "";
                _panInvalidError = "";
                _countryWarningMsg = "";
                _pinCodeWarningMsg = "";
                _cityWarningMsg = "";
                _stateWarningMsg = "";
                _firstLineAddressWarningMsg = "";
                _companyUrlWarningMsg = "";
            } else {
                _logger.info("-----------inside else block ------------");
                isValidationPassed = false;
                _panInvalidError = "";
                setErrorMsg();
            }
        } else {
            setErrorMsg();
        }
        //check for documents
        if (isValidationPassed && isPanProprietory == CommonConstants.BOOLEAN_TRUE && doc1PanUploadedfile != null &&
            doc3ProprietorshipUploadedfile != null) {
            //check for all three docs-- old
            // checking for 2 docs changed on 06-08-2018
            isValidationPassed = true;
            _panDocWarningMsg = "";
            _companyRegistrationDocWarningMsg = "";
            _proprietorshipDocWarningMsg = "";
        } else if (isValidationPassed && isPanProprietory == CommonConstants.BOOLEAN_FALSE &&
                   doc1PanUploadedfile != null) {
            //check for 2 docs -- old
            // checking only for the PanProprietory doc
            // chnaged on 06-08-2018
            isValidationPassed = true;
            _panDocWarningMsg = "";
            _companyRegistrationDocWarningMsg = "";
        } else {
            isValidationPassed = false;
            setErrorMsg();
        }
        _logger.info("Exit method checkMendatoryFieldValidation::::" + isValidationPassed);
        return isValidationPassed;
    }
    
    

    /**
     * value change method organisation name field
     * @param valueChangeEvent
     */
    public void onOrganizationChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method onOrganizationChange");
        try {
            String orgNameVal = valueChangeEvent.getNewValue() != null ? valueChangeEvent.getNewValue().toString() : "";
            if (orgNameVal != null && !orgNameVal.equals("")) {
                setOrganizationNameWarningMsg("");
                if (_pan != null && !_pan.equalsIgnoreCase("")) {
                    setPan("");
                    setPanInvalidError("");
                    refreshComponent(valueChangeEvent.getComponent()
                                                     .getParent()
                                                     .getParent());
                }
            } else {
                setOrganizationNameWarningMsg(CommonConstants.FIELD_IS_MANDATORY);

            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method onOrganizationChange");
    }
    
    
    /**
     * value change listner method of city field
     * @param valueChangeEvent
     */
    public void cityChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method cityChangeListner");
        try {
            if (valueChangeEvent.getNewValue().toString() != null && !valueChangeEvent.getNewValue()
                                                                                      .toString()
                                                                                      .equals("")) {
                setCityWarningMsg("");
            } else {
                setCityWarningMsg(CommonConstants.FIELD_IS_MANDATORY);
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit emthod cityChangeListener");
    }
    
    
    /**
     * calue change method of firstLine Address field
     * @param valueChangeEvent
     */
    public void firstLineChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method firstLineChangeListener");
        try {
            if (valueChangeEvent.getNewValue().toString() != null && !valueChangeEvent.getNewValue()
                                                                                      .toString()
                                                                                      .equals("")) {
                setFirstLineAddressWarningMsg("");
            } else {
                setFirstLineAddressWarningMsg(CommonConstants.FIELD_IS_MANDATORY);
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method firstLineChangeListener");
    }
    
    
    /**
     * value change listener method of company url field
     * @param valueChangeEvent
     */
    public void companyUrlChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method companyUrlChangeListener");
        try {
            String companyUrl = valueChangeEvent.getNewValue().toString();
            isUrlValid = SupplierUtils.matchUrlPattern(companyUrl);
            _logger.info("Is company url is valid ::::" + isUrlValid);
            if (companyUrl != null && !companyUrl.equals("")) {
                setCompanyUrlWarningMsg("");
            } else {
                setCompanyUrlWarningMsg(CommonConstants.FIELD_IS_MANDATORY);
            }
            Boolean validateUrl = isCompanyUrlValid(companyUrl);
            refreshComponent(valueChangeEvent.getComponent().getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method companyUrlChangeListener");
    }

    
    /**
     * helper method to chekc validaty of company url
     * @param url
     * @return
     */
    public Boolean isCompanyUrlValid(String url) {
        _logger.config("Entering isCOmapnyUrlValid ::: url :: "+url );
        Boolean isUrlValid = SupplierUtils.matchUrlPattern(url);
        if (isUrlValid) {
            setCompanyUrlWarningMsg("");
        } else {
            setCompanyUrlWarningMsg(CommonConstants.INVALID_URL);
        }
        _logger.config("Exit method isCompanyUrlValid");
        return isUrlValid;
    }

    /**
     *
     * @param isPanProprietory
     */
    public void setIsPanProprietory(boolean isPanProprietory) {
        this.isPanProprietory = isPanProprietory;
    }
    
    /**
     *
     * @return
     */
    public boolean isIsPanProprietory() {
        return isPanProprietory;
    }

    /**
     * value change listener method of state field
     * @param valueChangeEvent
     */
    public void onStateChangeListener(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method onStateChangeListener");
        try {
            if (valueChangeEvent.getNewValue().toString() != null && !valueChangeEvent.getNewValue()
                                                                                      .toString()
                                                                                      .equals("")) {
                setStateWarningMsg("");
            } else {
                setStateWarningMsg(CommonConstants.FIELD_IS_MANDATORY);
            }
            refreshComponent(valueChangeEvent.getComponent().getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method onStateChangeListener");
    }

    /**
     *
     * @param panCardValidationFlag
     */
    public void setPanCardValidationFlag(boolean panCardValidationFlag) {
        this.panCardValidationFlag = panCardValidationFlag;
    }
    
    /**
     *
     * @return
     */
    public boolean isPanCardValidationFlag() {
        return panCardValidationFlag;
    }

    
    /**
     * helper method to check entered string is numeric or not 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }

    /**
     *
     * @param doc1Pan
     */
    public void setDoc1Pan(RichInputFile doc1Pan) {
        this.doc1Pan = ComponentReference.newUIComponentReference(doc1Pan);
    }
    
    /**
     *
     * @return
     */
    public RichInputFile getDoc1Pan() {
        if (doc1Pan != null) {
            return (RichInputFile) doc1Pan.getComponent();
        }
        return null;
    }
    
    /**
     *
     * @param doc2CompanyRegistration
     */
    public void setDoc2CompanyRegistration(RichInputFile doc2CompanyRegistration) {
        this.doc2CompanyRegistration = ComponentReference.newUIComponentReference(doc2CompanyRegistration);
    }
    
    /**
     *
     * @return
     */
    public RichInputFile getDoc2CompanyRegistration() {
        if (doc2CompanyRegistration != null) {
            return (RichInputFile) doc2CompanyRegistration.getComponent();
        }
        return null;
    }
    
    /**
     *
     * @param doc3Proprietorship
     */
    public void setDoc3Proprietorship(RichInputFile doc3Proprietorship) {
        this.doc3Proprietorship = ComponentReference.newUIComponentReference(doc3Proprietorship);
    }
    
    /**
     *
     * @return
     */
    public RichInputFile getDoc3Proprietorship() {
        if (doc3Proprietorship != null) {
            return (RichInputFile) doc3Proprietorship.getComponent();
        }
        return null;
    }
    
    /**
     * setter method for Doc1PanUploadedFile 
     * @param doc1PanUploadedfile
     */
    public void setDoc1PanUploadedfile(UploadedFile doc1PanUploadedfile) {
        _logger.config("Entering method setDoc1PanUploadedfile ::: doc1PanUploadedfile ::: "+doc1PanUploadedfile);
        this.doc1PanUploadedfile = doc1PanUploadedfile;
        InputStream doc1PanUploadedfileInputStream = null;
        // String object to hold converted inputStream ibnto String 
        File file = null;
        try {
            if (getDoc1PanUploadedfile() != null) {
                doc1PanUploadedfileInputStream = getDoc1PanUploadedfile().getInputStream();
                file = DmsUtil.storeTmpFile(doc1PanUploadedfileInputStream,getDoc1PanUploadedfile().getFilename());
                
                
            }
            AdfFacesContext.getCurrentInstance()
                           .getPageFlowScope()
                           .put("panInputFile", file);
        } catch (IOException e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method setDoc1PanUploadedfile");
    }

    /**
     *
     * @return
     */
    public UploadedFile getDoc1PanUploadedfile() {
        return doc1PanUploadedfile;
    }

    /**
     * commented on 06-08-2018
     * blocking the company registration document upload on OrgDetails page
     */

    //        public void setDoc2CompanyRegistrationUploadedfile(UploadedFile doc2CompanyRegistrationUploadedfile) {
    //            this.doc2CompanyRegistrationUploadedfile = doc2CompanyRegistrationUploadedfile;
    //            InputStream doc2CompanyRegistrationUploadedfileInputStream = null;
    //            try {
    //                if (getDoc2CompanyRegistrationUploadedfile() != null) {
    //                    doc2CompanyRegistrationUploadedfileInputStream =getDoc2CompanyRegistrationUploadedfile().getInputStream();
    //                }
    //                AdfFacesContext.getCurrentInstance().getPageFlowScope().put("companyRegistrationInputStream",
    //                                                                                doc2CompanyRegistrationUploadedfileInputStream);
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //
    //        public UploadedFile getDoc2CompanyRegistrationUploadedfile() {
    //            return doc2CompanyRegistrationUploadedfile;
    //        }

    /**
     * setter method for odc3ProprietorshipUploadedfile
     * @param doc3ProprietorshipUploadedfile
     */
    public void setDoc3ProprietorshipUploadedfile(UploadedFile doc3ProprietorshipUploadedfile) {
        _logger.config("Entering method setDoc3ProprietorshiUploadedfile ::: doc3ProprietorshipUploadedfile ::: "+doc3ProprietorshipUploadedfile);
        this.doc3ProprietorshipUploadedfile = doc3ProprietorshipUploadedfile;
        InputStream doc3ProprietorshipUploadedfileInputStream = null;
        File file = null;
        try {
            if (getDoc3ProprietorshipUploadedfile() != null) {
                doc3ProprietorshipUploadedfileInputStream = getDoc3ProprietorshipUploadedfile().getInputStream();
                file = DmsUtil.storeTmpFile(doc3ProprietorshipUploadedfileInputStream, getDoc3ProprietorshipUploadedfile().getFilename());
            }
            AdfFacesContext.getCurrentInstance()
                           .getPageFlowScope()
                           .put("proprietorshipInputFile", file);
            //                String name = doc3ProprietorshipUploadedfile.getFilename();
            //                File tmpPanFile = DmsUtil.storeTmpFile(doc3ProprietorshipUploadedfileInputStream, name);

        } catch (IOException e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method setDoc3ProprietorshipUploadedFile");
    }

    /**
     *
     * @return
     */
    public UploadedFile getDoc3ProprietorshipUploadedfile() {
        return doc3ProprietorshipUploadedfile;
    }

    /**
     * This method is called on value change of doc1 Pan.
     * It checks for the valid file format and then sets UploadedFile variable - doc1PanUploadedfile
     * @param valueChangeEvent
     */
    public void panDocChange(ValueChangeEvent valueChangeEvent) {
        _logger.info("inside pan document change listener");
        try {
            String panFileName = null;
            if (documentTypeValidation(valueChangeEvent)) {
                setPanDocWarningMsg(CommonConstants.UPLOADED_DOC_ERR_MSG);
                refreshComponent(valueChangeEvent.getComponent().getParent());
                return;

            } else if (valueChangeEvent.getNewValue() != null) {
                setDoc1PanUploadedfile((UploadedFile) valueChangeEvent.getNewValue());
                setPanDocWarningMsg("");

            }

            if (null != getDoc1PanUploadedfile()) {
                panFileName = getDoc1PanUploadedfile().getFilename();
                AdfFacesContext.getCurrentInstance()
                               .getPageFlowScope()
                               .put("panFileName", panFileName);
                setDoc1FileName(panFileName);
            }
            refreshComponent(valueChangeEvent.getComponent()
                                             .getParent()
                                             .getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method panDocChange");
    }


    /**
     * commented on 06-08-2018
     * blocking the company registration document upload on OrgDetails page
     */


    /**
         * This method is called on value change of doc2 CompanyRegistration.
         * It checks for the valid file format and then sets UploadedFile variable - doc2CompanyRegistrationUploadedfile
         * @param valueChangeEvent
         */
    //        public void companyRegistrationDocChange(ValueChangeEvent valueChangeEvent) {
    //            try {
    //                String companyRegistrationFileName = null;
    //                if (documentTypeValidation(valueChangeEvent)) {
    //                    setCompanyRegistrationDocWarningMsg(CommonConstants.UPLOADED_DOC_ERR_MSG);
    //                    refreshComponent(valueChangeEvent.getComponent().getParent());
    //                    return;
    //
    //                } else if (valueChangeEvent.getNewValue() != null){
    //                    setDoc2CompanyRegistrationUploadedfile((UploadedFile)valueChangeEvent.getNewValue());
    //                    setCompanyRegistrationDocWarningMsg("");
    //
    //                }
    //                if (null != getDoc2CompanyRegistrationUploadedfile()) {
    //                    companyRegistrationFileName = getDoc2CompanyRegistrationUploadedfile().getFilename();
    //                    AdfFacesContext.getCurrentInstance().getPageFlowScope().put("companyRegistrationFileName",
    //                                                                                            companyRegistrationFileName);
    //                    setDoc2FileName(companyRegistrationFileName);
    //                }
    //                refreshComponent(valueChangeEvent.getComponent().getParent().getParent());
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //        }

    /**
     * This method is called on value change of doc3 Proprietorshi.
     * It checks for the valid file format and then sets UploadedFile variable - doc3ProprietorshipUploadedfile
     * @param valueChangeEvent
     */
    public void proprietorshipDocChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method proprietorshipDocChange");
        try {
            String proprietorshipFileName = null;
            if (documentTypeValidation(valueChangeEvent)) {
                setProprietorshipDocWarningMsg(CommonConstants.UPLOADED_DOC_ERR_MSG);
                refreshComponent(valueChangeEvent.getComponent().getParent());
                return;

            } else if (valueChangeEvent.getNewValue() != null) {
                setDoc3ProprietorshipUploadedfile((UploadedFile) valueChangeEvent.getNewValue());
                setProprietorshipDocWarningMsg("");

            }
            if (null != getDoc3ProprietorshipUploadedfile()) {
                proprietorshipFileName = getDoc3ProprietorshipUploadedfile().getFilename();
                AdfFacesContext.getCurrentInstance()
                               .getPageFlowScope()
                               .put("proprietorshipFileName", proprietorshipFileName);
                setDoc3FileName(proprietorshipFileName);
            }
            refreshComponent(valueChangeEvent.getComponent()
                                             .getParent()
                                             .getParent());
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.config("Exit method proprietorshipDocChange");
    }


    /**
     * This is helper method which validated file format of the uploaded file
     * @param valueChangeEvent
     * @return
     */
    private Boolean documentTypeValidation(ValueChangeEvent valueChangeEvent) {
        _logger.config("Entering method documentTypeValidation");
        
        UploadedFile tmpFile = (UploadedFile) valueChangeEvent.getNewValue();
        _logger.info("Uploaded File Name______" + tmpFile.getFilename());
        _logger.info("Uploaded File Content Type_____" + tmpFile.getContentType());
        if (!valueChangeEvent.getNewValue().equals(valueChangeEvent.getOldValue())) {
            if (this.isFileNameValid(tmpFile) && this.isFileTypeValid(tmpFile)) {
                return CommonConstants.BOOLEAN_FALSE;
            } else {
                ((UIXEditableValue) valueChangeEvent.getComponent()).resetValue();
                return CommonConstants.BOOLEAN_TRUE;
            }
        }
        
        _logger.config("Exit method documentTypeValidation");
        return CommonConstants.BOOLEAN_FALSE;
    }
    
    
    
    /**
     * method to check upload file Name is valid or not
     * @param tmp
     * @return
     */
    public Boolean isFileNameValid(UploadedFile tmp) {
        _logger.config("Entering method isFileNameValid");
        Boolean isFileNameValid = CommonConstants.BOOLEAN_FALSE;
        try {
            _logger.info("Uploaded File Name______" + tmp.getFilename());
            //check for valid file name
            String name = tmp.getFilename();
            String extension = null;
            if (name != null) {
                int index = name.lastIndexOf(".");
                if (index > 0) {
                    extension = name.substring(index + 1);
                    _logger.info("Extn___" + extension);
                }
                if (extension != null && !extension.equalsIgnoreCase("") && extension.equalsIgnoreCase("png")) {
                    isFileNameValid = CommonConstants.BOOLEAN_TRUE;
                } else if (extension != null && !extension.equalsIgnoreCase("") && extension.equalsIgnoreCase("jpg")) {
                    isFileNameValid = CommonConstants.BOOLEAN_TRUE;
                } else if (extension != null && !extension.equalsIgnoreCase("") && extension.equalsIgnoreCase("jpeg")) {
                    isFileNameValid = CommonConstants.BOOLEAN_TRUE;
                } else if (extension != null && !extension.equalsIgnoreCase("") && extension.equalsIgnoreCase("pdf")) {
                    isFileNameValid = CommonConstants.BOOLEAN_TRUE;
                }
                // else if (extension != null && !extension.equalsIgnoreCase("") && extension.equalsIgnoreCase("exe")) {
                //     isFileNameValid = CommonConstants.BOOLEAN_TRUE;
                // }
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        return isFileNameValid;
    }

    /**
     * method to check file type is valid or not
     * @param tmpFile
     * @return
     */
    public Boolean isFileTypeValid(UploadedFile tmpFile) {
        _logger.config("Entering method isFileTypeValid");
        Boolean isFileTypeValid = CommonConstants.BOOLEAN_FALSE;
        try {
            _logger.info("Uploaded File Content Type_____" + tmpFile.getContentType());
            if (tmpFile != null &&
                (tmpFile.getContentType().equalsIgnoreCase("image/jpeg") ||
                 tmpFile.getContentType().equalsIgnoreCase("image/png") ||
                 tmpFile.getContentType().equalsIgnoreCase("application/pdf") ||
                 tmpFile.getContentType().equalsIgnoreCase("application/x-msdownload"))) {

                isFileTypeValid = CommonConstants.BOOLEAN_TRUE;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        return isFileTypeValid;
    }

    /**
     * method to upload document
     * @return
     */
    public Boolean uploadDocuments() {
        _logger.info("Inside uploadDocuments....");
        Boolean documentsUploaded = CommonConstants.BOOLEAN_FALSE;
        File pancardFile = null;
        File companyRegistrationFile = null;
        File proprietorshipFile = null;
        //Upload the documents to UCM
        try {
            _logger.info("Uploading PAN...");
             pancardFile =  (File)AdfFacesContext.getCurrentInstance().getPageFlowScope()
                                                                         .get("panInputFile");
            
            String panFileName = (String) AdfFacesContext.getCurrentInstance()
                                                         .getPageFlowScope()
                                                         .get("panFileName");
            _logger.info("PAN File Name:::" + panFileName + "----" + pancardFile);
            if (panFileName != null && pancardFile != null) {
//                pancardFile = DmsUtil.storeTmpFile(panInputStreamObj, panFileName);// commented on 29-08-2018 by rupak
                //probe file type and delete unidentified file
                Boolean isPANFileValid = DmsUtil.probeAndClean(pancardFile);
                _logger.info("___Probe and Clean output for PAN CARD___" + isPANFileValid);
                if (isPANFileValid) {
                    Map<String, String> panDocMetaData = DmsUtil.uploadFiletoUCM(pancardFile, null, null, panFileName);
                    _logger.info("Uploaded document meta data :::" + panDocMetaData);
                    for (Map.Entry<String, String> entry : panDocMetaData.entrySet()) {
                        _logger.info(entry.getKey() + " = " + entry.getValue());
                        if (entry.getKey() != null && entry.getKey().equals("dDocName")) {
                            _panContentId = entry.getValue();
                        }
                    }
                    _logger.info("PAN Uploaded...");
                } else {
                    setPanDocWarningMsg(CommonConstants.UPLOADED_DOC_ERR_MSG);
                }
            }

        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
            setPanDocWarningMsg("Unable to upload document right now.");
        }

        /**
         * commented on 06-08-2018
         * blocking the company registration document upload on OrgDetails page
         */

        //        try {
        //            _logger.info("Uploading Company Registration...");
        //            InputStream companyRegistrationInputStreamObj =
        //                (InputStream) AdfFacesContext.getCurrentInstance()
        //                                                                                         .getPageFlowScope()
        //                                                                                         .get("companyRegistrationInputStream");
        //            String companyRegistrationFileName = (String) AdfFacesContext.getCurrentInstance()
        //                                                                         .getPageFlowScope()
        //                                                                         .get("companyRegistrationFileName");
        //            _logger.info("Company Registration File Name:::" + companyRegistrationFileName + "----" +
        //                               companyRegistrationInputStreamObj);
        //            if (companyRegistrationFileName != null && companyRegistrationInputStreamObj != null) {
        //                companyRegistrationFile =
        //                    DmsUtil.storeTmpFile(companyRegistrationInputStreamObj, companyRegistrationFileName);
        //
        //                Map<String, String> companyRegistrationDocMetaData =
        //                    DmsUtil.uploadFiletoUCM(companyRegistrationFile, null, null, companyRegistrationFileName);
        //                _logger.info("Uploaded document meta data :::" + companyRegistrationDocMetaData);
        //                for (Map.Entry<String, String> entry : companyRegistrationDocMetaData.entrySet()) {
        //                    _logger.info("(entry.getKey() + " = " + entry.getValue());
        //                    if (entry.getKey() != null && entry.getKey().equals("dDocName")) {
        //                        _companyRegistrationContentId = entry.getValue();
        //                    }
        //                }
        //            }
        //            _logger.info("Uploaded Company Registration...");
        //        } catch (Exception e) {
        //            // TODO: Add catch code
        //            e.printStackTrace();
        //            setCompanyRegistrationDocWarningMsg("Unable to upload document right now.");
        //        }

        try {
            _logger.info("Uploading Proprietorship...");
            proprietorshipFile = (File) AdfFacesContext.getCurrentInstance()
                                                                                    .getPageFlowScope()
                                                                                    .get("proprietorshipInputFile");
            String proprietorshipFileName = (String) AdfFacesContext.getCurrentInstance()
                                                                    .getPageFlowScope()
                                                                    .get("proprietorshipFileName");
            _logger.info("Proprietorship File Name:::" + proprietorshipFileName + "----" +
                         proprietorshipFile);

            if (proprietorshipFileName != null && proprietorshipFile != null) {
//                proprietorshipFile = DmsUtil.storeTmpFile(proprietorshipInputStreamObj, proprietorshipFileName);// commented on 29-08-2018 by rupak
                Boolean isProprietorshipFileValid = DmsUtil.probeAndClean(pancardFile);
                _logger.info("___Probe and Clean output for Proprietorship File___" + isProprietorshipFileValid);
                if (isProprietorshipFileValid) {
                    Map<String, String> proprietorshipDocMetaData =
                        DmsUtil.uploadFiletoUCM(proprietorshipFile, null, null, proprietorshipFileName);
                    _logger.info("Uploaded document meta data :::" + proprietorshipDocMetaData);
                    for (Map.Entry<String, String> entry : proprietorshipDocMetaData.entrySet()) {
                        _logger.info(entry.getKey() + " = " + entry.getValue());
                        if (entry.getKey() != null && entry.getKey().equals("dDocName")) {
                            _proprietorshipContentId = entry.getValue();
                        }
                    }
                    _logger.info("Uploaded Proprietorship...");
                }
            }

        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
            setProprietorshipDocWarningMsg("Unable to upload document right now.");
        }
        _logger.info("isPanProprietory::::::::::::::::" + isPanProprietory);
        _logger.info("_panContentId ::::::::::::::::::::" + _panContentId);
        _logger.info("_companyRegistrationContentId :::::::::::::::::::::" + _companyRegistrationContentId);
        if (isPanProprietory) {

            /**
             * commented on 06-08-2018
             * blocking the company registration document upload on OrgDetails page
             */
            //            documentsUploaded =
            //                (!_panContentId.isEmpty() && !_companyRegistrationContentId.isEmpty() &&
            //                 !_proprietorshipContentId.isEmpty()) ? CommonConstants.BOOLEAN_TRUE : CommonConstants.BOOLEAN_FALSE;

            documentsUploaded =
                (!_panContentId.isEmpty() && !_proprietorshipContentId.isEmpty()) ? CommonConstants.BOOLEAN_TRUE :
                CommonConstants.BOOLEAN_FALSE;
        } else {

            /**
             * commented on 06-08-2018
             * blocking the company registration document upload on OrgDetails page
             */
            //            documentsUploaded =
            //                (!_panContentId.isEmpty() && !_companyRegistrationContentId.isEmpty()) ? CommonConstants.BOOLEAN_TRUE :
            //                CommonConstants.BOOLEAN_FALSE;

            documentsUploaded =
                (!_panContentId.isEmpty()) ? CommonConstants.BOOLEAN_TRUE : CommonConstants.BOOLEAN_FALSE;

        }
        //save contentId in database
        _logger.info("---------------ALL DOCUMENTS UPLOADED_________" + documentsUploaded);
        _logger.config("Exit method uploadDocument");
        return documentsUploaded;
    }
    
    /**
     *
     * @param _panDocWarningMsg
     */
    public void setPanDocWarningMsg(String _panDocWarningMsg) {
        this._panDocWarningMsg = _panDocWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getPanDocWarningMsg() {
        return _panDocWarningMsg;
    }

    /**
     * commented on 06-08-2018
     * blocking the company registration document upload on OrgDetails page
     */

    //    public void setCompanyRegistrationDocWarningMsg(String _companyRegistrationDocWarningMsg) {
    //        this._companyRegistrationDocWarningMsg = _companyRegistrationDocWarningMsg;
    //    }
    //
    //    public String getCompanyRegistrationDocWarningMsg() {
    //        return _companyRegistrationDocWarningMsg;
    //    }
    
    /**
     *
     * @param _proprietorshipDocWarningMsg
     */
    public void setProprietorshipDocWarningMsg(String _proprietorshipDocWarningMsg) {
        this._proprietorshipDocWarningMsg = _proprietorshipDocWarningMsg;
    }
    
    /**
     *
     * @return
     */
    public String getProprietorshipDocWarningMsg() {
        return _proprietorshipDocWarningMsg;
    }
    
    /**
     *
     * @param doc1FileName
     */
    public void setDoc1FileName(String doc1FileName) {
        this.doc1FileName = doc1FileName;
    }
    
    /**
     *
     * @return
     */
    public String getDoc1FileName() {
        return doc1FileName;
    }

    /**
     * commented on 06-08-2018
     * blocking the company registration document upload on OrgDetails page
     */

    //    public void setDoc2FileName(String doc2FileName) {
    //        this.doc2FileName = doc2FileName;
    //    }
    //
    //    public String getDoc2FileName() {
    //        return doc2FileName;
    //    }
    
    /**
     *
     * @param doc3FileName
     */
    public void setDoc3FileName(String doc3FileName) {
        this.doc3FileName = doc3FileName;
    }
    
    /**
     *
     * @return
     */
    public String getDoc3FileName() {
        return doc3FileName;
    }
    
    /**
     * method to check supplier is already exist with same organization name or not
     * @param _organizationName
     * @return
     */
    private Boolean checkIfSupplierExists(String _organizationName) {
        _logger.config("Entering method checkIfSupplierExists :: _oraganizationName :: "+_organizationName);
        Boolean supplierExists = CommonConstants.BOOLEAN_TRUE;
        if (_organizationName == null) {
            _logger.info("Exiting checkIfSupplierExists :::" + supplierExists);
            return supplierExists;
        }

        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("SupplierLookRVO1Iterator");
            //getting viewobject
            ViewObjectImpl supplierLookRVO = (ViewObjectImpl) dcIterator1.getViewObject();
            ViewCriteria supplierExistVC = supplierLookRVO.getViewCriteria("SupplierExistVC");
            supplierLookRVO.setNamedWhereClauseParam("bndVendorName", _organizationName);
            supplierLookRVO.applyViewCriteria(null);
            supplierLookRVO.applyViewCriteria(supplierExistVC);
            supplierLookRVO.executeQuery();
            if (supplierLookRVO.first() != null) {
                supplierExists = CommonConstants.BOOLEAN_TRUE; //record already exits
            } else {
                supplierExists = CommonConstants.BOOLEAN_FALSE; // record not exits
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        _logger.info("Exiting checkIfSupplierExists :::" + supplierExists);
        return supplierExists;
    }

    /**
     * method to check is supplier is exists with same organization name, pin conde and country code
     * @param _organizationName
     * @param _countryCode
     * @param _pinCode
     * @return
     */
    private Boolean acceptedSupplierExistsInWKFTbl(String _organizationName, String _countryCode, String _pinCode) {
        _logger.config("Entering method acceptedSupplierExistsWKFTbl :: _organizationName ::"+_organizationName+" :: _country_Code :: "+_countryCode +" :: _pinCode :: "+_pinCode);
        ViewObjectImpl wkfSupplierVO = null;
        try {
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
            //getting viewobject
            wkfSupplierVO = (ViewObjectImpl) dcIterator1.getViewObject();
            ViewCriteria vc = wkfSupplierVO.getViewCriteria("ValidateNonDomesticPreExistedSupplier");
            wkfSupplierVO.setNamedWhereClauseParam("bndCountryName", _countryCode);
            wkfSupplierVO.setNamedWhereClauseParam("bndOrgNameSupplier", _organizationName);
            wkfSupplierVO.setNamedWhereClauseParam("bndPinCodeSupplier", _pinCode);
            wkfSupplierVO.applyViewCriteria(null);
            wkfSupplierVO.applyViewCriteria(vc);
            wkfSupplierVO.executeQuery();
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
            return CommonConstants.BOOLEAN_TRUE;
        }

        if (wkfSupplierVO.first() != null) {
            return CommonConstants.BOOLEAN_TRUE; //record already exits
        } else {
            return CommonConstants.BOOLEAN_FALSE; // record not exits
        }
    }

    /**
     * Method check validity of PAN and ORGANIZATION NAME.
     * @param _pan
     * @param name
     * @return
     */
    private Boolean checkPANValid(String _pan, String name) {
        _logger.config("Entering method checkPANValid :: _pan :: "+ _pan + " :: name :: " +name);
        Boolean isValid = CommonConstants.BOOLEAN_FALSE;
        String _panNumber = _pan;
        String consumerTransactionId = new String(SupplierUtils.generateOTP(6));
        String statusCode = "";

        //change made by Nishant - check PAN against regex
        Boolean matchPanPattern = SupplierUtils.matchPANPattern(_panNumber);

        if (!matchPanPattern) {
            setPanInvalidError("The PAN # entered is incorrect. Please enter correct PAN No.");
            return isValid;
        }

        VerifyCustomerPanResp verifyCustomerPanRespObj = null;
        String panFullName = null;
        try {
            verifyCustomerPanRespObj = NsdlUtil.fetchPANDetails(consumerTransactionId, _panNumber);
            statusCode = verifyCustomerPanRespObj.getDataArea()
                                                 .getVerifyCustomerPanDetailsResponse()
                                                 .getPanDetails()
                                                 .get(0)
                                                 .getPanStatus();
            panFullName =
                verifyCustomerPanRespObj.getDataArea()
                                                  .getVerifyCustomerPanDetailsResponse()
                                                  .getPanDetails()
                                                  .get(0)
                                                  .getFirstName() + verifyCustomerPanRespObj.getDataArea()
                                                                                            .getVerifyCustomerPanDetailsResponse()
                                                                                            .getPanDetails()
                                                                                            .get(0)
                                                                                            .getMiddleName() +
      verifyCustomerPanRespObj.getDataArea()
                                                                                                                                       .getVerifyCustomerPanDetailsResponse()
                                                                                                                                       .getPanDetails()
                                                                                                                                       .get(0)
                                                                                                                                       .getLastName();
        } catch (Exception e) {
            setPanInvalidError("Dear User, we can’t validate for the PAN information provided by you at this point of time as the NSDL site is down. We are sorry for inconvenience caused. Please try again after sometime.");
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        if (verifyCustomerPanRespObj == null) {
            return isValid;
        }
        if (!statusCode.equalsIgnoreCase("f") && !statusCode.equalsIgnoreCase("n")) {
            _logger.info("Printing Full Name received from NSDL API:::" + panFullName);
            _logger.info("Organisation Name input by User from screen :::" + name);
            String tmpPanFullName = panFullName != null ? panFullName.replaceAll("\\s+", "") : "";
            String tmpOrganisationName = name != null ? name.replaceAll("\\s+", "") : "";
            _logger.info("----tmpPanFullName---" + tmpPanFullName + "---tmpOrganisationName---" + tmpOrganisationName);
            if (tmpOrganisationName.equalsIgnoreCase(tmpPanFullName)) {
                //pan is valid
                isValid = CommonConstants.BOOLEAN_TRUE;
                panCardValidationFlag = CommonConstants.BOOLEAN_TRUE;
            } else {
                //The organization name provided by you doesn’t match with the organization name listed on the PAN.
                setPanInvalidError("The organization name provided by you doesn’t match with the organization name listed on the PAN.");
            }
        } else {
            //The PAN # entered is incorrect. Please enter correct PAN No.
            setPanInvalidError("The PAN # entered is incorrect. Please enter correct PAN No.");
        }
        _logger.info("Status recievd frompan api ::::" + statusCode);
        _logger.config("Exit method checkPANValid");
        return isValid;
    }
    
    
    
    public String convertInputStreamToString(InputStream inputStream, Charset charset) throws IOException {
            return IOUtils.toString(inputStream, charset);
    }
}
