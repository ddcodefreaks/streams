package com.airtel.rs.wrapper.nsdl.pojo;


public class PanVerificationReq {
    
    String lob;
    String consumerTransactionId;
    String consumerName;
    String programmeName;
    String panNumber;
    
    public void setLob(String lob) {
        this.lob = lob;
    }

    public String getLob() {
        return lob;
    }

    public void setConsumerTransactionId(String consumerTransactionId) {
        this.consumerTransactionId = consumerTransactionId;
    }

    public String getConsumerTransactionId() {
        return consumerTransactionId;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public PanVerificationReq() {
        super();
    }
}
