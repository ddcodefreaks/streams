package com.airtel.supplierportal.pojo.nsdl;

import java.util.List;


public class VerifyCustomerPanDetailsResponse {
    Status status;
    List<PanDetails> panDetails;

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPanDetails(List<PanDetails> panDetails) {
        this.panDetails = panDetails;
    }

    public List<PanDetails> getPanDetails() {
        return panDetails;
    }

    public Status getStatus() {
        return status;
    }

   
}
