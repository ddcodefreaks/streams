package com.airtel.supplierportal.bean.partnerRegistrationPhase2;

import com.airtel.supplierportal.pojo.session.SupplierSession;

import com.airtel.supplierportal.utility.CommonConstants;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class PartnerRegistrationPhase2 implements Serializable {
    @SuppressWarnings("compatibility:-3236392849269189004")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(PartnerRegistrationPhase2.class);
    
    
    
    private List<SelectItem> countryList;
    private List<SelectItem> bankNameList;
    private List<SelectItem> currencyCodeList;
    private List<SelectItem> accountTypeList;
    private List<SelectItem> countryPhoneCodeList;
    private List<SelectItem> legalEntityOfInterest;
    
  
    
    /**
     * default constructor of bean class PartnerRegistrationPhase2
     */
    public PartnerRegistrationPhase2() {
        _logger.info("Inside the default constructor of bean class PartnerRegistrationPhase2");
    }

    /**
     *
     * @param countryList
     */
    public void setCountryList(List countryList) {
        this.countryList = countryList;
    }
    /**
     *
     * @return
     */
    public List getCountryList() {
        return countryList;
    }

    /**
     *
     * @param bankNameList
     */
    public void setBankNameList(List<SelectItem> bankNameList) {
        this.bankNameList = bankNameList;
    }
    /**
     *
     * @return
     */
    public List<SelectItem> getBankNameList() {
        return bankNameList;
    }

    public void setCurrencyCodeList(List<SelectItem> currencyCodeList) {
        this.currencyCodeList = currencyCodeList;
    }
    /**
     *
     * @return
     */
    public List<SelectItem> getCurrencyCodeList() {
        return currencyCodeList;
    }
    /**
     *
     * @param accountTypeList
     */
    public void setAccountTypeList(List<SelectItem> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }
    /**
     *
     * @return
     */
    public List<SelectItem> getAccountTypeList() {
        return accountTypeList;
    }

    /**
     *
     * @param countryPhoneCodeList
     */
    public void setCountryPhoneCodeList(List<SelectItem> countryPhoneCodeList) {
        this.countryPhoneCodeList = countryPhoneCodeList;
    }
    /**
     *
     * @return
     */
    public List<SelectItem> getCountryPhoneCodeList() {
        return countryPhoneCodeList;
    }

    /**
     *
     * @param legalEntityOfInterest
     */
    public void setLegalEntityOfInterest(List<SelectItem> legalEntityOfInterest) {
        this.legalEntityOfInterest = legalEntityOfInterest;
    }
    
    /**
     *
     * @return
     */

    public List<SelectItem> getLegalEntityOfInterest() {
        return legalEntityOfInterest;
    }

    /**
     * method to determine partner country type i.e. domestic or non-demestic, retuns String "domestic"
     * for domestic partner and "nonDomestic" for internation partners
     * @return
     */
    public String checkCountry() {
        _logger.config("Entering method checkCountry");
        String countryType = null;
        String _countryCode = null;

        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpSession session = (HttpSession) ectx.getSession(true);
            SupplierSession supplierObj = (SupplierSession) session.getAttribute("SupplierSessionObject");
            Long partnerId = supplierObj.getPartnerVendorId();
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            //getting iterator
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("SupplierProfileRVO1Iterator");
            //getting viewobject
            ViewObjectImpl supplierProfileVO = (ViewObjectImpl) dcIterator.getViewObject();
            ViewCriteria supplierProfileVC = supplierProfileVO.getViewCriteria("SearchSupplier");
            supplierProfileVO.setNamedWhereClauseParam("bndVendorId", partnerId);
            supplierProfileVO.applyViewCriteria(null);
            supplierProfileVO.applyViewCriteria(supplierProfileVC);
            supplierProfileVO.executeQuery();
            if (supplierProfileVO.first() != null) {
                _logger.config("Getting country code of the partner");
                _countryCode =
                    supplierProfileVO.first().getAttribute("Country") != null ?
                    supplierProfileVO.first()
                                                                                                            .getAttribute("Country")
                                                                                                            .toString() :
                                          "";

                _logger.config("contry code of the partner is ::: " + _countryCode);

                if (_countryCode != null && !_countryCode.equalsIgnoreCase("") && _countryCode.equalsIgnoreCase("IN")) {
                    _logger.config("inside if statement, if partner is domenstic, setting countryType domestic");
                    countryType = "domestic";
                } else if (_countryCode != null && !_countryCode.equalsIgnoreCase("") &&
                           !_countryCode.equalsIgnoreCase("IN")) {
                    _logger.config("inside if statement, if partner is non-domenstic, setting countryType nonDomestic");
                    countryType = "nonDomestic";
                }
            }
        } catch (Exception e) {
            _logger.severe("Eception raised :: countryCode receieved :: "+_countryCode+" "+e.getMessage());
            e.printStackTrace();
        }
        // calling method to load the country list
        loadCountryList();
        // calling method to load the bank names list
        loadBankNameList();
        // calling method to load the currency code list
        loadCurrencyCodeList();
        // calling method to load account type list
        loadAccountTypeList();
        // calling method to load the country phone code list
        loadCountryPhoneCodeList();
        //calling method to lead the legal entity of Interest List
        loadLegalEntityOfInterest();
        
        
        _logger.config("Exit method checkCountry");
        return countryType;
    }
    
    
    /**
     *method to get bindings
     * @return
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    /**
     * load country list in country lov
     */
    public void loadCountryList(){
        _logger.info("Entering the method loadCountryList");
        try {
            countryList = new ArrayList<SelectItem>();
            if (countryList.size() == 0) {
                // TODO iterate with country list from db table and loop through it to populate the all the countries
                _logger.info("Loading the country list here ");
                countryList.add(new SelectItem("IN", "India"));
                countryList.add(new SelectItem("US", "United State"));
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the country list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadCountryList");
    }
    
    /**
     * helper method to load the banks name into the bank name LOV
     */
    public void loadBankNameList(){
        _logger.info("Entering the method loadBankNameList");

        try {
            bankNameList = new ArrayList<SelectItem>();

            if (bankNameList.size() == 0) {
                // TODO iterate with bank names list from db table and loop through it load into the ArrayList

                _logger.info("Loading the ban name list here");

                bankNameList.add(new SelectItem("State Bank Of India", "State Bank Of India"));
                bankNameList.add(new SelectItem("Punjab National Bank", "Punjab National Bank"));
                bankNameList.add(new SelectItem("Bank Of India", "Bank Of India"));
                bankNameList.add(new SelectItem("ICICC Bank", "ICICC Bank"));
                bankNameList.add(new SelectItem("HDFC Bank", "HDFC Bank"));
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the banks list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadBankNameList");
        
    }
    
    /**
     * helper method to load the currency code list for currency code lov
     */
    public void loadCurrencyCodeList(){
        _logger.info("Entering the method loadCurrencyCodeList");

        try {
            currencyCodeList = new ArrayList<SelectItem>();

            if (currencyCodeList.size() == 0) {
                // TODO iterate with currency code list from db table and loop through it load into the ArrayList

                _logger.info("Loading the ban name list here");

                currencyCodeList.add(new SelectItem("INR", "INR"));
                currencyCodeList.add(new SelectItem("USD", "USD"));
                currencyCodeList.add(new SelectItem("URO", "URO"));
                currencyCodeList.add(new SelectItem("YEN", "YEN"));
                
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the currency code list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadCurrencyCodeList");
    }
    
    /**
     * helper method to load the account type list in account type lov
     */
    public void loadAccountTypeList(){
        _logger.info("Entering the method loadAccountTypeList");

        try {
            accountTypeList = new ArrayList<SelectItem>();

            if (accountTypeList.size() == 0) {
                // TODO iterate with account type list from db table and loop through it load into the ArrayList

                _logger.info("Loading the ban name list here");

                accountTypeList.add(new SelectItem("Saving Account", "Saving Account"));
                accountTypeList.add(new SelectItem("Current Account", "Current Account"));
             
                
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the account type list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadAccountTypeList");
    }
    
    /**
     * helper method to load country phone code in country phone code LOV
     */
    public void loadCountryPhoneCodeList(){
        _logger.info("Entering the method loadCountryPhoneCodeList");

        try {
            countryPhoneCodeList = new ArrayList<SelectItem>();

            if (countryPhoneCodeList.size() == 0) {
                // TODO iterate with country phone code list from db table and loop through it load into the ArrayList

                _logger.info("Loading the ban name list here");

                countryPhoneCodeList.add(new SelectItem("+91", "+91"));
                countryPhoneCodeList.add(new SelectItem("+00", "+00"));
             
                
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the cuntry phone code list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadCountryPhoneCodeList");
    }
    
    /**
     * load legal entity of interest list in lov
     */

    public void loadLegalEntityOfInterest(){
        _logger.info("Entering the method loadLegalEntityOfInterest");
        try {
            legalEntityOfInterest = new ArrayList<SelectItem>();
            if (legalEntityOfInterest.size() == 0) {
                // TODO iterate with country list from db table and loop through it to populate the all the countries
                _logger.info("Loading the country list here ");
//                legalEntityOfInterest.add(new SelectItem("BA", "Bharti Airtel"));
//                legalEntityOfInterest.add(new SelectItem("ID", "Idea"));
                legalEntityOfInterest.add(new SelectItem("Bharti Airtel"));
                legalEntityOfInterest.add(new SelectItem("Idea"));
            }
        } catch (Exception e) {
            _logger.info("Exception raised during loading the country list :: "+e.getMessage());
            e.printStackTrace();
        }
        
        _logger.info("Exit the method loadLegalEntityOfInterest");
    }

}
