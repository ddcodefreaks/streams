package com.airtel.buyer.pojo;


public class CreateSupplierRR {
    public CreateSupplierRR() {
        super();
    }


    public CreateSupplierRR(Long partnerVendorId,String partnerPassword,String buyerEmail) {
        this.partnerVendorId = partnerVendorId;
        this.buyerEmail=buyerEmail;
        this.partnerPassword=partnerPassword;
    }

    private Long partnerVendorId;
    private String partnerPassword;
    private String buyerEmail;

    public void setPartnerPassword(String partnerPassword) {
        this.partnerPassword = partnerPassword;
    }

    public String getPartnerPassword() {
        return partnerPassword;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }
    private String status;
    private String message;

    public void setPartnerVendorId(Long partnerVendorId) {
        this.partnerVendorId = partnerVendorId;
    }

    public Long getPartnerVendorId() {
        return partnerVendorId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CreateSupplierRR[ partnerVendorId=" + partnerVendorId + ", status=" + status + ", message=" + message;
    }
}
