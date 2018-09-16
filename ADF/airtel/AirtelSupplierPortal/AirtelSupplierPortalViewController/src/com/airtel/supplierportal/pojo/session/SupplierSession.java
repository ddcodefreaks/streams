package com.airtel.supplierportal.pojo.session;

import java.io.Serializable;


public class SupplierSession implements Serializable {
    @SuppressWarnings("compatibility:-4592375983293516725")
    private static final long serialVersionUID = 1L;

    private String email;
    private Long mobileNumber;
    private Long partnerVendorId;
    private String companyName;
    private Long partnerUserId;
    private String buyerMail;
    private String goToPage;
    
    public SupplierSession() {
        super();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setPartnerVendorId(Long partnerVendorId) {
        this.partnerVendorId = partnerVendorId;
    }

    public Long getPartnerVendorId() {
        return partnerVendorId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setPartnerUserId(Long partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public Long getPartnerUserId() {
        return partnerUserId;
    }

    public void setBuyerMail(String buyerMail) {
        this.buyerMail = buyerMail;
    }

    public String getBuyerMail() {
        return buyerMail;
    }

    public void setGoToPage(String goToPage) {
        this.goToPage = goToPage;
    }

    public String getGoToPage() {
        return goToPage;
    }
}
