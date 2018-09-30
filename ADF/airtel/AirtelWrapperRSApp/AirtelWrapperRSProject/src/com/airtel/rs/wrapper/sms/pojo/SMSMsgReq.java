package com.airtel.rs.wrapper.sms.pojo;

public class SMSMsgReq {
    public SMSMsgReq() {
        super();
    }
    private String message;
    private String to;

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

}
