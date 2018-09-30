package com.airtel.rs.wrapper.email.pojo;

public class EmailMsgResp {
    public EmailMsgResp() {
        super();
    }
    
    private String status;
    private String error;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
