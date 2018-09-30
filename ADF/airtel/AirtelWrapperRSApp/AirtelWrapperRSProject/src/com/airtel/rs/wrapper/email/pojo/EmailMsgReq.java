package com.airtel.rs.wrapper.email.pojo;

public class EmailMsgReq {
    public EmailMsgReq() {
        super();
    }
    
    private String recipient;
    private String sender;
    private String subject;
    private String content;


    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
