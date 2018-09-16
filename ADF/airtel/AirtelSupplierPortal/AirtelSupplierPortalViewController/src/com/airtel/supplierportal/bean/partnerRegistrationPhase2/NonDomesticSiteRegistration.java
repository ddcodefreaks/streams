package com.airtel.supplierportal.bean.partnerRegistrationPhase2;

import com.airtel.supplierportal.bean.login.LoginBean;

import com.airtel.supplierportal.utility.CommonConstants;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.faces.model.SelectItem;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

public class NonDomesticSiteRegistration implements Serializable {
    @SuppressWarnings("compatibility:23556810274713143")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(LoginBean.class);
    private Boolean showCountryWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showPinCodeWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showCityWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showStateWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showAddressWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showFirstNameWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showLastNameWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showCountryCodeWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showPhoneNumberWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showEmailWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showRemittanceWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showBankNameWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showCurrencyWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showAccountNumberWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showReEnterAccountNumberWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showAccountTypeWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showSwiftCodeWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showKYCWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showCancelledChequeWarning = CommonConstants.BOOLEAN_FALSE;
    private Boolean showBankLetterWarning = CommonConstants.BOOLEAN_FALSE;
    private String countryWarningMsg = "";
    private String pinCodeWarningMsg = "";
    private String cityWarningMsg = "";
    private String stateWarningMsg = "";
    private String addressWarningMsg = "";
    private String firstNameWarningMsg = "";
    private String lastNameWarningMsg = "";
    private String countryCodeWarningMsg = "";
    private String phoneNumberWarningMsg = "";
    private String emailWarningMsg = "";
    private String remittanceEmailWarningMsg = "";
    private String bankNameWarningMsg = "";
    private String currencyWarningMsg = "";
    private String accountNumberWarningMsg = "";
    private String reEnterAccountNumberWarningMsg = "";
    private String accountTypeWarningMsg = "";
    private String swiftCodeWarningMsg = "";
    private String kycWarningMsg = "";
    private String cancelledChequeWarningMsg = "";
    private String bankLetterWarningMsg = "";
    private String country;
    private String pinCode;
    private String city;
    private String state;
    private String address;
    private String firstName;
    private String lastName;
    private String countryCode;
    private String phoneNumber;
    private String email;
    private String remittanceEmail;
    private String bankName;
    private String currency;
    private String accountNumber;
    private String reEnterAccountNumber;
    private String accountType;
    private String swiftCode;
    private RichInputFile kycFormCertificate;
    private RichInputFile cancelledCheque;
    private RichInputFile bankLetter;
    private RichOutputText fnBinding;

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */

    public String getCountry() {
        return country;
    }

    /**
     *
     * @param pinCode
     */

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     *
     * @return
     */

    public String getPinCode() {
        return pinCode;
    }

    /**
     *
     * @param city
     */

    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */

    public String getCity() {
        return city;
    }

    /**
     *
     * @param state
     */

    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */

    public String getState() {
        return state;
    }

    /**
     *
     * @param address
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */

    public String getAddress() {
        return address;
    }

    /**
     *
     * @param firstName
     */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param lastName
     */

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */

    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param countryCode
     */

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     *
     * @return
     */

    public String getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @param phoneNumber
     */

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */

    public String getEmail() {
        return email;
    }

    /**
     *
     * @param remittanceEmail
     */

    public void setRemittanceEmail(String remittanceEmail) {
        this.remittanceEmail = remittanceEmail;
    }

    /**
     *
     * @return
     */

    public String getRemittanceEmail() {
        return remittanceEmail;
    }

    /**
     *
     * @param enterFirstNameot
     */

    public NonDomesticSiteRegistration() {
        super();
    }

    /**
     *
     * @param bankName
     */

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     *
     * @return
     */

    public String getBankName() {
        return bankName;
    }

    /**
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     */

    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param accountNumber
     */

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     *
     * @return
     */

    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     *
     * @param reEnterAccountNumber
     */

    public void setReEnterAccountNumber(String reEnterAccountNumber) {
        this.reEnterAccountNumber = reEnterAccountNumber;
    }

    /**
     *
     * @return
     */

    public String getReEnterAccountNumber() {
        return reEnterAccountNumber;
    }

    /**
     *
     * @param accountType
     */

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     *
     * @return
     */

    public String getAccountType() {
        return accountType;
    }

    /**
     *
     * @param swiftCode
     */

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    /**
     *
     * @return
     */

    public String getSwiftCode() {
        return swiftCode;
    }

    /**
     *
     * @param showCountryWarning
     */
    public void setShowCountryWarning(Boolean showCountryWarning) {
        this.showCountryWarning = showCountryWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowCountryWarning() {
        return showCountryWarning;
    }

    /**
     *
     * @param showPinCodeWarning
     */

    public void setShowPinCodeWarning(Boolean showPinCodeWarning) {
        this.showPinCodeWarning = showPinCodeWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowPinCodeWarning() {
        return showPinCodeWarning;
    }

    /**
     *
     * @param showCityWarning
     */

    public void setShowCityWarning(Boolean showCityWarning) {
        this.showCityWarning = showCityWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowCityWarning() {
        return showCityWarning;
    }

    /**
     *
     * @param showStateWarning
     */

    public void setShowStateWarning(Boolean showStateWarning) {
        this.showStateWarning = showStateWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowStateWarning() {
        return showStateWarning;
    }

    /**
     *
     * @param showAddressWarning
     */

    public void setShowAddressWarning(Boolean showAddressWarning) {
        this.showAddressWarning = showAddressWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowAddressWarning() {
        return showAddressWarning;
    }

    /**
     *
     * @param showFirstNameWarning
     */

    public void setShowFirstNameWarning(Boolean showFirstNameWarning) {
        this.showFirstNameWarning = showFirstNameWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowFirstNameWarning() {
        return showFirstNameWarning;
    }

    /**
     *
     * @param showLastNameWarning
     */

    public void setShowLastNameWarning(Boolean showLastNameWarning) {
        this.showLastNameWarning = showLastNameWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowLastNameWarning() {
        return showLastNameWarning;
    }

    /**
     *
     * @param showCountryCodeWarning
     */

    public void setShowCountryCodeWarning(Boolean showCountryCodeWarning) {
        this.showCountryCodeWarning = showCountryCodeWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowCountryCodeWarning() {
        return showCountryCodeWarning;
    }

    /**
     *
     * @param showPhoneNumberWarning
     */

    public void setShowPhoneNumberWarning(Boolean showPhoneNumberWarning) {
        this.showPhoneNumberWarning = showPhoneNumberWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowPhoneNumberWarning() {
        return showPhoneNumberWarning;
    }

    /**
     *
     * @param showEmailWarning
     */

    public void setShowEmailWarning(Boolean showEmailWarning) {
        this.showEmailWarning = showEmailWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowEmailWarning() {
        return showEmailWarning;
    }

    /**
     *
     * @param showRemittanceWarning
     */

    public void setShowRemittanceWarning(Boolean showRemittanceWarning) {
        this.showRemittanceWarning = showRemittanceWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowRemittanceWarning() {
        return showRemittanceWarning;
    }

    /**
     *
     * @param showBankNameWarning
     */

    public void setShowBankNameWarning(Boolean showBankNameWarning) {
        this.showBankNameWarning = showBankNameWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowBankNameWarning() {
        return showBankNameWarning;
    }

    /**
     *
     * @param showCurrencyWarning
     */

    public void setShowCurrencyWarning(Boolean showCurrencyWarning) {
        this.showCurrencyWarning = showCurrencyWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowCurrencyWarning() {
        return showCurrencyWarning;
    }

    /**
     *
     * @param showAccountNumberWarning
     */

    public void setShowAccountNumberWarning(Boolean showAccountNumberWarning) {
        this.showAccountNumberWarning = showAccountNumberWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowAccountNumberWarning() {
        return showAccountNumberWarning;
    }

    /**
     *
     * @param showReEnterAccountNumberWarning
     */

    public void setShowReEnterAccountNumberWarning(Boolean showReEnterAccountNumberWarning) {
        this.showReEnterAccountNumberWarning = showReEnterAccountNumberWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowReEnterAccountNumberWarning() {
        return showReEnterAccountNumberWarning;
    }

    /**
     *
     * @param showAccountTypeWarning
     */

    public void setShowAccountTypeWarning(Boolean showAccountTypeWarning) {
        this.showAccountTypeWarning = showAccountTypeWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowAccountTypeWarning() {
        return showAccountTypeWarning;
    }

    /**
     *
     * @param showSwiftCodeWarning
     */

    public void setShowSwiftCodeWarning(Boolean showSwiftCodeWarning) {
        this.showSwiftCodeWarning = showSwiftCodeWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowSwiftCodeWarning() {
        return showSwiftCodeWarning;
    }

    /**
     *
     * @param countryWarningMsg
     */
    public void setCountryWarningMsg(String countryWarningMsg) {
        this.countryWarningMsg = countryWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getCountryWarningMsg() {
        return countryWarningMsg;
    }

    /**
     *
     * @param pinCodeWarningMsg
     */

    public void setPinCodeWarningMsg(String pinCodeWarningMsg) {
        this.pinCodeWarningMsg = pinCodeWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getPinCodeWarningMsg() {
        return pinCodeWarningMsg;
    }

    /**
     *
     * @param cityWarningMsg
     */

    public void setCityWarningMsg(String cityWarningMsg) {
        this.cityWarningMsg = cityWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getCityWarningMsg() {
        return cityWarningMsg;
    }

    /**
     *
     * @param stateWarningMsg
     */

    public void setStateWarningMsg(String stateWarningMsg) {
        this.stateWarningMsg = stateWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getStateWarningMsg() {
        return stateWarningMsg;
    }

    /**
     *
     * @param addressWarningMsg
     */

    public void setAddressWarningMsg(String addressWarningMsg) {
        this.addressWarningMsg = addressWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getAddressWarningMsg() {
        return addressWarningMsg;
    }

    /**
     *
     * @param firstNameWarningMsg
     */

    public void setFirstNameWarningMsg(String firstNameWarningMsg) {
        this.firstNameWarningMsg = firstNameWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getFirstNameWarningMsg() {
        return firstNameWarningMsg;
    }

    /**
     *
     * @param lastNameWarningMsg
     */

    public void setLastNameWarningMsg(String lastNameWarningMsg) {
        this.lastNameWarningMsg = lastNameWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getLastNameWarningMsg() {
        return lastNameWarningMsg;
    }

    /**
     *
     * @param countryCodeWarningMsg
     */

    public void setCountryCodeWarningMsg(String countryCodeWarningMsg) {
        this.countryCodeWarningMsg = countryCodeWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getCountryCodeWarningMsg() {
        return countryCodeWarningMsg;
    }

    /**
     *
     * @param phoneNumberWarningMsg
     */

    public void setPhoneNumberWarningMsg(String phoneNumberWarningMsg) {
        this.phoneNumberWarningMsg = phoneNumberWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getPhoneNumberWarningMsg() {
        return phoneNumberWarningMsg;
    }

    /**
     *
     * @param emailWarningMsg
     */

    public void setEmailWarningMsg(String emailWarningMsg) {
        this.emailWarningMsg = emailWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getEmailWarningMsg() {
        return emailWarningMsg;
    }

    /**
     *
     * @param remittanceEmailWarningMsg
     */

    public void setRemittanceEmailWarningMsg(String remittanceEmailWarningMsg) {
        this.remittanceEmailWarningMsg = remittanceEmailWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getRemittanceEmailWarningMsg() {
        return remittanceEmailWarningMsg;
    }

    /**
     *
     * @param bankNameWarningMsg
     */

    public void setBankNameWarningMsg(String bankNameWarningMsg) {
        this.bankNameWarningMsg = bankNameWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getBankNameWarningMsg() {
        return bankNameWarningMsg;
    }

    /**
     *
     * @param currencyWarningMsg
     */

    public void setCurrencyWarningMsg(String currencyWarningMsg) {
        this.currencyWarningMsg = currencyWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getCurrencyWarningMsg() {
        return currencyWarningMsg;
    }

    /**
     *
     * @param accountNumberWarningMsg
     */

    public void setAccountNumberWarningMsg(String accountNumberWarningMsg) {
        this.accountNumberWarningMsg = accountNumberWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getAccountNumberWarningMsg() {
        return accountNumberWarningMsg;
    }

    /**
     *
     * @param reEnterAccountNumberWarningMsg
     */

    public void setReEnterAccountNumberWarningMsg(String reEnterAccountNumberWarningMsg) {
        this.reEnterAccountNumberWarningMsg = reEnterAccountNumberWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getReEnterAccountNumberWarningMsg() {
        return reEnterAccountNumberWarningMsg;
    }

    /**
     *
     * @param accountTypeWarningMsg
     */

    public void setAccountTypeWarningMsg(String accountTypeWarningMsg) {
        this.accountTypeWarningMsg = accountTypeWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getAccountTypeWarningMsg() {
        return accountTypeWarningMsg;
    }

    /**
     *
     * @param swiftCodeWarningMsg
     */

    public void setSwiftCodeWarningMsg(String swiftCodeWarningMsg) {
        this.swiftCodeWarningMsg = swiftCodeWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getSwiftCodeWarningMsg() {
        return swiftCodeWarningMsg;
    }

    /**
     *
     * @param kycFormCertificate
     */

    public void setKycFormCertificate(RichInputFile kycFormCertificate) {
        this.kycFormCertificate = kycFormCertificate;
    }

    /**
     *
     * @return
     */

    public RichInputFile getKycFormCertificate() {
        return kycFormCertificate;
    }

    /**
     *
     * @param cancelledCheque
     */

    public void setCancelledCheque(RichInputFile cancelledCheque) {
        this.cancelledCheque = cancelledCheque;
    }

    /**
     *
     * @return
     */

    public RichInputFile getCancelledCheque() {
        return cancelledCheque;
    }

    /**
     *
     * @param bankLetter
     */

    public void setBankLetter(RichInputFile bankLetter) {
        this.bankLetter = bankLetter;
    }

    /**
     *
     * @return
     */

    public RichInputFile getBankLetter() {
        return bankLetter;
    }

    /**
     *
     * @param showKYCWarning
     */


    public void setShowKYCWarning(Boolean showKYCWarning) {
        this.showKYCWarning = showKYCWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowKYCWarning() {
        return showKYCWarning;
    }

    /**
     *
     * @param showCancelledChequeWarning
     */

    public void setShowCancelledChequeWarning(Boolean showCancelledChequeWarning) {
        this.showCancelledChequeWarning = showCancelledChequeWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowCancelledChequeWarning() {
        return showCancelledChequeWarning;
    }

    /**
     *
     * @param showBankLetterWarning
     */

    public void setShowBankLetterWarning(Boolean showBankLetterWarning) {
        this.showBankLetterWarning = showBankLetterWarning;
    }

    /**
     *
     * @return
     */

    public Boolean getShowBankLetterWarning() {
        return showBankLetterWarning;
    }

    /**
     *
     * @param kycWarningMsg
     */

    public void setKycWarningMsg(String kycWarningMsg) {
        this.kycWarningMsg = kycWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getKycWarningMsg() {
        return kycWarningMsg;
    }

    /**
     *
     * @param cancelledChequeWarningMsg
     */

    public void setCancelledChequeWarningMsg(String cancelledChequeWarningMsg) {
        this.cancelledChequeWarningMsg = cancelledChequeWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getCancelledChequeWarningMsg() {
        return cancelledChequeWarningMsg;
    }

    /**
     *
     * @param bankLetterWarningMsg
     */

    public void setBankLetterWarningMsg(String bankLetterWarningMsg) {
        this.bankLetterWarningMsg = bankLetterWarningMsg;
    }

    /**
     *
     * @return
     */

    public String getBankLetterWarningMsg() {
        return bankLetterWarningMsg;
    }

    /**
     *
     * @param fnBinding
     */

    public void setFnBinding(RichOutputText fnBinding) {
        this.fnBinding = fnBinding;
    }

    /**
     *
     * @return
     */

    public RichOutputText getFnBinding() {
        return fnBinding;
    }

    /**
     * method for Non Domestic Site Registration
     * validation: no field should be null of Registration form
     * @param actionEvent
     */
    public void submitAction(ActionEvent actionEvent) {
        _logger.info("Entering Method submitAction of NonDomesticSiteRegistration.java");
        try {

        } catch (Exception e) {
            _logger.severe("Exception raised:::" + e.getMessage());
            e.printStackTrace();
        }
    }


}
