package com.airtel.supplierportal.pojo.nsdl;


public class VerifyCustomerPanResp {
    EbmHeader ebmHeader;
    DataArea dataArea;

    public void setEbmHeader(EbmHeader ebmHeader) {
        this.ebmHeader = ebmHeader;
    }

    public EbmHeader getEbmHeader() {
        return ebmHeader;
    }

    public void setDataArea(DataArea dataArea) {
        this.dataArea = dataArea;
    }

    public DataArea getDataArea() {
        return dataArea;
    }
}
