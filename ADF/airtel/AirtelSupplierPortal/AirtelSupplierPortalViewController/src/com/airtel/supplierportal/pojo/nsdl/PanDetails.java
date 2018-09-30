package com.airtel.supplierportal.pojo.nsdl;


public class PanDetails {
    
    String panNumber;
    String panStatus;
    String lastName;
    String firstName;
    String middleName;
    String panTitle;
    String lastUpdatedDate;

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setPanTitle(String panTitle) {
        this.panTitle = panTitle;
    }

    public String getPanTitle() {
        return panTitle;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }
}
