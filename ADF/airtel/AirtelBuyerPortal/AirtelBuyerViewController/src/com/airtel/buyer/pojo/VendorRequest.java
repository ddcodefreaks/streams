package com.airtel.buyer.pojo;

import java.io.Serializable;

public class VendorRequest implements Serializable {
    @SuppressWarnings("compatibility:-3902018417108087063")
    private static final long serialVersionUID = 1L;

    public VendorRequest() {
        super();
    }

    private String WKF_ID;
    private String WKF_OWNER;
    private String WFK_COMMENT;
    private String PARTNER_USER_ID;
    private String PARTNER_VENDOR_ID;
    private String ORG_NAME;
    private String PAN;
    private String COUNTRY;
    private String STATE;
    private String CITY;
    private String PINCODE;
    private String ADD_LINE_1;
    private String STATUS;
    private String DOC1;
    private String DOC2;
    private String DOC3;
    private String P_USER_ID;
    private String COMPANY_URL;
    private String PARTNER_EMAIL;
    private String PARTNER_MOBILE;
    private String CREATED_DATE_TIME;
    private String MODIFIED_DATE_TIME;
    private String EXPIRY_DATE;
    private String VENDOR_TYPE;

    public void setVENDOR_TYPE(String VENDOR_TYPE) {
        this.VENDOR_TYPE = VENDOR_TYPE;
    }

    public String getVENDOR_TYPE() {
        return VENDOR_TYPE;
    }

    public void setWKF_ID(String WKF_ID) {
        this.WKF_ID = WKF_ID;
    }

    public String getWKF_ID() {
        return WKF_ID;
    }

    public void setWKF_OWNER(String WKF_OWNER) {
        this.WKF_OWNER = WKF_OWNER;
    }

    public String getWKF_OWNER() {
        return WKF_OWNER;
    }

    public void setWFK_COMMENT(String WFK_COMMENT) {
        this.WFK_COMMENT = WFK_COMMENT;
    }

    public String getWFK_COMMENT() {
        return WFK_COMMENT;
    }

    public void setPARTNER_USER_ID(String PARTNER_USER_ID) {
        this.PARTNER_USER_ID = PARTNER_USER_ID;
    }

    public String getPARTNER_USER_ID() {
        return PARTNER_USER_ID;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME;
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getPAN() {
        return PAN;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getCITY() {
        return CITY;
    }

    public void setPINCODE(String PINCODE) {
        this.PINCODE = PINCODE;
    }

    public String getPINCODE() {
        return PINCODE;
    }

    public void setADD_LINE_1(String ADD_LINE_1) {
        this.ADD_LINE_1 = ADD_LINE_1;
    }

    public String getADD_LINE_1() {
        return ADD_LINE_1;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setDOC1(String DOC1) {
        this.DOC1 = DOC1;
    }

    public String getDOC1() {
        return DOC1;
    }

    public void setDOC2(String DOC2) {
        this.DOC2 = DOC2;
    }

    public String getDOC2() {
        return DOC2;
    }

    public void setP_USER_ID(String P_USER_ID) {
        this.P_USER_ID = P_USER_ID;
    }

    public String getP_USER_ID() {
        return P_USER_ID;
    }

    public void setCOMPANY_URL(String COMPANY_URL) {
        this.COMPANY_URL = COMPANY_URL;
    }

    public String getCOMPANY_URL() {
        return COMPANY_URL;
    }

    public void setCREATED_DATE_TIME(String CREATED_DATE_TIME) {
        this.CREATED_DATE_TIME = CREATED_DATE_TIME;
    }

    public String getCREATED_DATE_TIME() {
        return CREATED_DATE_TIME;
    }

    public void setMODIFIED_DATE_TIME(String MODIFIED_DATE_TIME) {
        this.MODIFIED_DATE_TIME = MODIFIED_DATE_TIME;
    }

    public String getMODIFIED_DATE_TIME() {
        return MODIFIED_DATE_TIME;
    }

    public void setEXPIRY_DATE(String EXPIRY_DATE) {
        this.EXPIRY_DATE = EXPIRY_DATE;
    }

    public String getEXPIRY_DATE() {
        return EXPIRY_DATE;
    }

    public void setPARTNER_EMAIL(String PARTNER_EMAIL) {
        this.PARTNER_EMAIL = PARTNER_EMAIL;
    }

    public String getPARTNER_EMAIL() {
        return PARTNER_EMAIL;
    }

    public void setPARTNER_MOBILE(String PARTNER_MOBILE) {
        this.PARTNER_MOBILE = PARTNER_MOBILE;
    }

    public String getPARTNER_MOBILE() {
        return PARTNER_MOBILE;
    }
    
    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getSTATE() {
        return STATE;
    }

    public void setPARTNER_VENDOR_ID(String PARTNER_VENDOR_ID) {
        this.PARTNER_VENDOR_ID = PARTNER_VENDOR_ID;
    }

    public String getPARTNER_VENDOR_ID() {
        return PARTNER_VENDOR_ID;
    }

    public void setDOC3(String DOC3) {
        this.DOC3 = DOC3;
    }

    public String getDOC3() {
        return DOC3;
    }
    
    @Override
    public String toString() {
        return "WKF_ID =" + WKF_ID + ",WKF_OWNER =" + WKF_OWNER + ",WFK_COMMENT =" + WFK_COMMENT +
               ",PARTNER_USER_ID =" + PARTNER_USER_ID + ",PARTNER_ORG_ID =" + PARTNER_VENDOR_ID + ",ORG_NAME =" +
               ORG_NAME + ",PAN =" + PAN + ",COUNTRY =" + COUNTRY + ",CITY =" + CITY + ",PINCODE =" + PINCODE +
               ",ADD_LINE_1 =" + ADD_LINE_1 + ",STATUS =" + STATUS + ",DOC1 =" + DOC1 + ",DOC2 =" + DOC2 + ",DOC3 =" + DOC3 +
               ",P_USER_ID =" + P_USER_ID + ",COMPANY_URL =" + COMPANY_URL + ",PARTNER_EMAIL =" + PARTNER_EMAIL +
               ", PARTNER_MOBILE =" + PARTNER_MOBILE + ",CREATED_DATE_TIME =" + CREATED_DATE_TIME +
               ",MODIFIED_DATE_TIME =" + MODIFIED_DATE_TIME + ",EXPIRY_DATE =" + EXPIRY_DATE;
    }
}
