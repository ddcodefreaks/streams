package com.airtel.buyer.pojo;

import java.io.Serializable;


public class VendorSiteRequest  implements Serializable{

    @SuppressWarnings("compatibility:7866471761587580200")
    private static final long serialVersionUID = 1L;
    
    public VendorSiteRequest(){
        super();
    }
    
    private String _vendorCode;
    private String _vendorOrganizationName;
    private String _vendorCompanyURL;
    private String _vendorContactName;
    private String _vendorEmail;
    private String _vendorPhoneNo;
    private String _siteAddress;
    private String _siteGSTNNo;
    private String _vendorPanNumber;
    private String _siteBankName;
    private String _siteBankAccountNo;
    private String _siteIFSC_SWIFT_Code;
    private String _siteBankAccountType;
    private String _siteRemittanceEmailId;
    private String _siteIsBankDetailsValid;
    private String _siteIsGSTNExempt;
    private String _siteIsMSMED;
    private String _siteStatus;
    private String _bankAccRowId;
    private String _siteIsDomestic;
    private String _siteIsPennyTestingSuccess;


    public void setSiteIsPennyTestingSuccess(String _siteIsPennyTestingSuccess) {
        this._siteIsPennyTestingSuccess = _siteIsPennyTestingSuccess;
    }

    public String getSiteIsPennyTestingSuccess() {
        return _siteIsPennyTestingSuccess;
    }

    public void setSiteIsDomestic(String _siteIsDomestic) {
        this._siteIsDomestic = _siteIsDomestic;
    }

    public String getSiteIsDomestic() {
        return _siteIsDomestic;
    }


    public void setBankAccRowId(String _bankAccRowId) {
        this._bankAccRowId = _bankAccRowId;
    }

    public String getBankAccRowId() {
        return _bankAccRowId;
    }

    public void setSiteStatus(String _siteStatus) {
        this._siteStatus = _siteStatus;
    }

    public String getSiteStatus() {
        return _siteStatus;
    }

    public void setVendorCode(String _vendorCode) {
        this._vendorCode = _vendorCode;
    }

    public String getVendorCode() {
        return _vendorCode;
    }

    public void setVendorOrganizationName(String _vendorOrganizationName) {
        this._vendorOrganizationName = _vendorOrganizationName;
    }

    public String getVendorOrganizationName() {
        return _vendorOrganizationName;
    }

    public void setVendorCompanyURL(String _vendorCompanyURL) {
        this._vendorCompanyURL = _vendorCompanyURL;
    }

    public String getVendorCompanyURL() {
        return _vendorCompanyURL;
    }

    public void setVendorContactName(String _vendorContactName) {
        this._vendorContactName = _vendorContactName;
    }

    public String getVendorContactName() {
        return _vendorContactName;
    }

    public void setVendorEmail(String _vendorEmail) {
        this._vendorEmail = _vendorEmail;
    }

    public String getVendorEmail() {
        return _vendorEmail;
    }

    public void setVendorPhoneNo(String _vendorPhoneNo) {
        this._vendorPhoneNo = _vendorPhoneNo;
    }

    public String getVendorPhoneNo() {
        return _vendorPhoneNo;
    }

    public void setSiteAddress(String _siteAddress) {
        this._siteAddress = _siteAddress;
    }

    public String getSiteAddress() {
        return _siteAddress;
    }

    public void setSiteGSTNNo(String _siteGSTNNo) {
        this._siteGSTNNo = _siteGSTNNo;
    }

    public String getSiteGSTNNo() {
        return _siteGSTNNo;
    }

    public void setVendorPanNumber(String _vendorPanNumber) {
        this._vendorPanNumber = _vendorPanNumber;
    }

    public String getVendorPanNumber() {
        return _vendorPanNumber;
    }

    public void setSiteBankName(String _siteBankName) {
        this._siteBankName = _siteBankName;
    }

    public String getSiteBankName() {
        return _siteBankName;
    }

    public void setSiteBankAccountNo(String _siteBankAccountNo) {
        this._siteBankAccountNo = _siteBankAccountNo;
    }

    public String getSiteBankAccountNo() {
        return _siteBankAccountNo;
    }

    public void setSiteIFSC_SWIFT_Code(String _siteIFSC_SWIFT_Code) {
        this._siteIFSC_SWIFT_Code = _siteIFSC_SWIFT_Code;
    }

    public String getSiteIFSC_SWIFT_Code() {
        return _siteIFSC_SWIFT_Code;
    }

    public void setSiteBankAccountType(String _siteBankAccountType) {
        this._siteBankAccountType = _siteBankAccountType;
    }

    public String getSiteBankAccountType() {
        return _siteBankAccountType;
    }

    public void setSiteRemittanceEmailId(String _siteRemittanceEmailId) {
        this._siteRemittanceEmailId = _siteRemittanceEmailId;
    }

    public String getSiteRemittanceEmailId() {
        return _siteRemittanceEmailId;
    }

    public void setSiteIsBankDetailsValid(String _siteIsBankDetailsValid) {
        this._siteIsBankDetailsValid = _siteIsBankDetailsValid;
    }

    public String getSiteIsBankDetailsValid() {
        return _siteIsBankDetailsValid;
    }

    public void setSiteIsGSTNExempt(String _siteIsGSTNExempt) {
        this._siteIsGSTNExempt = _siteIsGSTNExempt;
    }

    public String getSiteIsGSTNExempt() {
        return _siteIsGSTNExempt;
    }

    public void setSiteIsMSMED(String _siteIsMSMED) {
        this._siteIsMSMED = _siteIsMSMED;
    }

    public String getSiteIsMSMED() {
        return _siteIsMSMED;
    }

    @Override
    public String toString() {
        return "_vendorCode =" + _vendorCode + ",_vendorOrganizationName =" + _vendorOrganizationName +
               ",_vendorCompanyURL =" + _vendorCompanyURL + ",_vendorContactName =" + _vendorContactName +
               ",_vendorEmail =" + _vendorEmail + ",_vendorPhoneNo =" + _vendorPhoneNo + ",_siteAddress =" +
               _siteAddress + ",_siteGSTNNo =" + _siteGSTNNo + ",_vendorPanNumber =" + _vendorPanNumber +
               ",_siteBankName =" + _siteBankName + ",_siteBankAccountNo =" + _siteBankAccountNo +
               ",_siteIFSC_SWIFT_Code =" + _siteIFSC_SWIFT_Code + ",_siteBankAccountType =" + _siteBankAccountType +
               ",_siteRemittanceEmailId =" + _siteRemittanceEmailId + ",_siteIsBankDetailsValid =" +
               _siteIsBankDetailsValid + ",_siteIsGSTNExempt =" + _siteIsGSTNExempt + ",_siteIsMSMED =" + _siteIsMSMED +
               ",_siteStatus =" + _siteStatus;

    }

}
