package com.airtel.rs.wrapper.sms.pojo;

public class SMSMsgResp {
    public SMSMsgResp(String message, String to, String errorMsg, String status) {
        this.message = message;
        this.to = to;
        this.errorMsg = errorMsg;
        this.status = status;
    }

    public SMSMsgResp() {
        super();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    private String message;
    private String to;
    private String errorMsg;
    private String status;
}
