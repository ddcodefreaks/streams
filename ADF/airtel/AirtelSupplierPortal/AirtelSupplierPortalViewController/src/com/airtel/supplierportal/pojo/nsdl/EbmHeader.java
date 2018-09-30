package com.airtel.supplierportal.pojo.nsdl;


public class EbmHeader {
    
    String lob;
    String consumerTransactionId;
    String consumerName;
    String programmeName;

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
}
