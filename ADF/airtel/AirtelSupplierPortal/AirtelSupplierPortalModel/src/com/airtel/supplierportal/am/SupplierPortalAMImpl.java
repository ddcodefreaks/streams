package com.airtel.supplierportal.am;

import com.airtel.supplierportal.am.common.SupplierPortalAM;

import java.time.LocalDateTime;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 12 18:47:23 IST 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SupplierPortalAMImpl extends ApplicationModuleImpl implements SupplierPortalAM {
    /**
     * This is the default constructor (do not remove).
     */
    public SupplierPortalAMImpl() {
    }


    // reset password of supplier

    public void resetPassword(String email, String password) {

        System.out.println("Email :" + email + " passowrd : " + password);
        ViewObjectImpl supplierUserTableVO = getBTVL_PARTNER_USER_TBL_VO1();
        ViewCriteria vc = supplierUserTableVO.getViewCriteria("SearchSupplierByEmail");
        supplierUserTableVO.setNamedWhereClauseParam("bndEmail", email);
        supplierUserTableVO.applyViewCriteria(null);
        supplierUserTableVO.applyViewCriteria(vc);
        supplierUserTableVO.executeQuery();

        if (supplierUserTableVO.first() != null) {
            supplierUserTableVO.first().setAttribute("Passwd", password);
            getDBTransaction().commit();
        }
    }


    /**
     * Container's getter for BTVL_PARTNER_USER_TBL_VO1.
     * @return BTVL_PARTNER_USER_TBL_VO1
     */
    public ViewObjectImpl getBTVL_PARTNER_USER_TBL_VO1() {
        return (ViewObjectImpl) findViewObject("BTVL_PARTNER_USER_TBL_VO1");
    }

    /**
     * Container's getter for BTVL_PARTNER_USER_TBL_TXN_VO1.
     * @return BTVL_PARTNER_USER_TBL_TXN_VO1
     */
    public ViewObjectImpl getBTVL_PARTNER_USER_TBL_TXN_VO1() {
        return (ViewObjectImpl) findViewObject("BTVL_PARTNER_USER_TBL_TXN_VO1");
    }

    /**
     * Container's getter for SupplierCredentialsDetailsRVO1.
     * @return SupplierCredentialsDetailsRVO1
     */
    public ViewObjectImpl getSupplierCredentialsDetailsRVO1() {
        return (ViewObjectImpl) findViewObject("SupplierCredentialsDetailsRVO1");
    }


    public void onActivation(Integer partnerId) {
        ViewObjectImpl userTxnVO = getBTVL_PARTNER_USER_TBL_TXN_VO1();
        ViewObjectImpl userVO = getBTVL_PARTNER_USER_TBL_VO1();
        ViewCriteria vc = userTxnVO.getViewCriteria("SerachSupplierByPartnerID");
        userTxnVO.setNamedWhereClauseParam("bndPartnerId", partnerId);
        userTxnVO.applyViewCriteria(null);
        userTxnVO.applyViewCriteria(vc);
        userTxnVO.executeQuery();

        if (userTxnVO.first() != null) {
            Row newRow = userVO.createRow();
            newRow.setAttribute("PartnerUserId", userTxnVO.first()
                                                          .getAttribute("PartnerUserId")
                                                          .toString());
            newRow.setAttribute("BuyerEmail", userTxnVO.first()
                                                       .getAttribute("BuyerEmail")
                                                       .toString());
            newRow.setAttribute("Email", userTxnVO.first()
                                                  .getAttribute("Email")
                                                  .toString());
            newRow.setAttribute("MobNo", (Long) userTxnVO.first().getAttribute("MobNo"));
            newRow.setAttribute("Passwd", userTxnVO.first()
                                                   .getAttribute("Passwd")
                                                   .toString());
            newRow.setAttribute("PartnerVendorId", userTxnVO.first()
                                                            .getAttribute("PartnerVendorId")
                                                            .toString());
            newRow.setAttribute("PartnerStatusId", 2);

            userVO.insertRow(newRow);
            userVO.executeQuery();


            userTxnVO.first().setAttribute("Status", 2);

            getDBTransaction().commit();


        }

    }

    /**
     * Container's getter for CountryLookupRVO1.
     * @return CountryLookupRVO1
     */
    public ViewObjectImpl getCountryLookupRVO1() {
        return (ViewObjectImpl) findViewObject("CountryLookupRVO1");
    }

    /**
     * Container's getter for PinCityStateLookupRVO1.
     * @return PinCityStateLookupRVO1
     */
    public ViewObjectImpl getPinCityStateLookupRVO1() {
        return (ViewObjectImpl) findViewObject("PinCityStateLookupRVO1");
    }

    /**
     * Container's getter for BTVL_WKF_PARTNER_STATUS_TBL_VO1.
     * @return BTVL_WKF_PARTNER_STATUS_TBL_VO1
     */
    public ViewObjectImpl getBTVL_WKF_PARTNER_STATUS_TBL_VO1() {
        return (ViewObjectImpl) findViewObject("BTVL_WKF_PARTNER_STATUS_TBL_VO1");
    }

    /**
     * Container's getter for ProductAndCategoryLookupRVO1.
     * @return ProductAndCategoryLookupRVO1
     */
    public ViewObjectImpl getProductAndCategoryLookupRVO1() {
        return (ViewObjectImpl) findViewObject("ProductAndCategoryLookupRVO1");
    }

    /**
     * Container's getter for BTVL_PARTNER_OTP_VO1.
     * @return BTVL_PARTNER_OTP_VO1
     */
    public ViewObjectImpl getBTVL_PARTNER_OTP_VO1() {
        return (ViewObjectImpl) findViewObject("BTVL_PARTNER_OTP_VO1");
    }


    public void createOrgDetails(String _partnerUserID, String _partnerVendorId, String _partnerEmail,
                                 String _partnerMobile, String _wkfOwner, String _organizationName, String _pan,
                                 String _countryCode, String _city, String _state, String _pinCode,
                                 String _firstLineAddress, String _companyUrl, String _attribute1, String _attribute2,
                                 String _attribute3) {

        ViewObjectImpl wkfPartnerStatusVO = getBTVL_WKF_PARTNER_STATUS_TBL_VO1();

        Row newRow = wkfPartnerStatusVO.createRow();
        //no need to set as it will get value from sequence when trigger will be called before row insert
        // newRow.setAttribute("WkfId", wkfIdBaseIndex + 1);
        newRow.setAttribute("WkfOwner", _wkfOwner);
        newRow.setAttribute("PartnerUserId", _partnerUserID);
        newRow.setAttribute("OrgName", _organizationName);
        newRow.setAttribute("Pan", _pan);
        newRow.setAttribute("Country", _countryCode);
        newRow.setAttribute("City", _city);
        newRow.setAttribute("PState", _state);
        newRow.setAttribute("PartnerEmail", _partnerEmail);
        newRow.setAttribute("PartnerMobile", _partnerMobile);
        newRow.setAttribute("Pincode", _pinCode);
        newRow.setAttribute("AddLine1", _firstLineAddress);
        newRow.setAttribute("Status", 3);
        newRow.setAttribute("Attribute1", _attribute1);
        newRow.setAttribute("Attribute2", _attribute2);
        newRow.setAttribute("Attribute3", _attribute3);
        newRow.setAttribute("CompanyUrl", _companyUrl);
        newRow.setAttribute("PartnerVendorId", _partnerVendorId);

        wkfPartnerStatusVO.insertRow(newRow);
        wkfPartnerStatusVO.executeQuery();

        getDBTransaction().commit();


    }


    public void updateOrgDetails(String _partnerUserID, String _partnerVendorId, String _partnerEmail,
                                 String _partnerMobile, String _wkfOwner, String _organizationName, String _pan,
                                 String _countryCode, String _city, String _state, String _pinCode,
                                 String _firstLineAddress, String _companyUrl, String _attribute1, String _attribute2,
                                 String _attribute3) {

        ViewObjectImpl wkfPartnerStatusVO = getBTVL_WKF_PARTNER_STATUS_TBL_VO1();
        wkfPartnerStatusVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
        ViewCriteria vc = wkfPartnerStatusVO.getViewCriteria("PartnerOrgStatusVC");
        wkfPartnerStatusVO.applyViewCriteria(null);
        wkfPartnerStatusVO.applyViewCriteria(vc);
        wkfPartnerStatusVO.executeQuery();
        // long wkfIdBaseIndex = 100 + wkfPartnerStatusVO.getEstimatedRowCount();
        if (wkfPartnerStatusVO.first() != null) {
            Row newRow = wkfPartnerStatusVO.first();


            newRow.setAttribute("WkfOwner", _wkfOwner);
            newRow.setAttribute("PartnerUserId", _partnerUserID);
            newRow.setAttribute("OrgName", _organizationName);
            newRow.setAttribute("Pan", _pan);
            newRow.setAttribute("Country", _countryCode);
            newRow.setAttribute("City", _city);
            newRow.setAttribute("PState", _state);
            newRow.setAttribute("PartnerEmail", _partnerEmail);
            newRow.setAttribute("PartnerMobile", _partnerMobile);
            newRow.setAttribute("Pincode", _pinCode);
            newRow.setAttribute("AddLine1", _firstLineAddress);
            newRow.setAttribute("Status", 3);
            newRow.setAttribute("Attribute1", _attribute1);
            newRow.setAttribute("Attribute2", _attribute2);
            newRow.setAttribute("Attribute3", _attribute3);
            newRow.setAttribute("CompanyUrl", _companyUrl);

            wkfPartnerStatusVO.executeQuery();

            getDBTransaction().commit();
        }


    }


    public void updateOrgDetgailsStatus(String _partnerVendorId) {
        ViewObjectImpl wkfPartnerStatusVO = getBTVL_WKF_PARTNER_STATUS_TBL_VO1();
        ViewCriteria wkfPartnerOrgDetailsVC = wkfPartnerStatusVO.getViewCriteria("PartnerOrgStatusVC");
        wkfPartnerStatusVO.setNamedWhereClauseParam("bndPartnerOrdId", _partnerVendorId);
        wkfPartnerStatusVO.applyViewCriteria(null);
        wkfPartnerStatusVO.applyViewCriteria(wkfPartnerOrgDetailsVC);
        wkfPartnerStatusVO.executeQuery();

        if (wkfPartnerStatusVO.first() != null) {
            wkfPartnerStatusVO.first().setAttribute("Status", 4);
            wkfPartnerStatusVO.first().setAttribute("ModifiedDateTime", LocalDateTime.now());
            getDBTransaction().commit();
        }
    }


    /**
     * Container's getter for BTVL_M_SUPPLIERS_TBL_RVO1.
     * @return BTVL_M_SUPPLIERS_TBL_RVO1
     */
    public ViewObjectImpl getBTVL_M_SUPPLIERS_TBL_RVO1() {
        return (ViewObjectImpl) findViewObject("BTVL_M_SUPPLIERS_TBL_RVO1");
    }

    /**
     * Container's getter for SupplierProfileRVO1.
     * @return SupplierProfileRVO1
     */
    public ViewObjectImpl getSupplierProfileRVO1() {
        return (ViewObjectImpl) findViewObject("SupplierProfileRVO1");
    }

    /**
     * Container's getter for PanCardNumberLookupRVO1.
     * @return PanCardNumberLookupRVO1
     */
    public ViewObjectImpl getPanCardNumberLookupRVO1() {
        return (ViewObjectImpl) findViewObject("PanCardNumberLookupRVO1");
    }

    /**
     * Container's getter for BuyerLookupRVO1.
     * @return BuyerLookupRVO1
     */
    public ViewObjectImpl getBuyerLookupRVO1() {
        return (ViewObjectImpl) findViewObject("BuyerLookupRVO1");
    }

    /**
     * Container's getter for SupplierLookRVO1.
     * @return SupplierLookRVO1
     */
    public ViewObjectImpl getSupplierLookRVO1() {
        return (ViewObjectImpl) findViewObject("SupplierLookRVO1");
    }


    // Vendor Account Creation Method
    public void createVendorAccount(String _vendorEmail, String _buyerEmail, String _vendorMobile, String _password) {

        System.out.println("----Inside createVendorAccount in AMImpl class ---------");

        long mobileNumber = Long.valueOf(_vendorMobile);

        //getting viewobject
        ViewObjectImpl tblSupplierUserVO = this.getBTVL_PARTNER_USER_TBL_TXN_VO1();
        System.out.println("------getting VO object------------- : : " + tblSupplierUserVO.getDefName());
        // creating new blank row

        Row newRow = tblSupplierUserVO.createRow();
        System.out.println("-----------Creating new row -----------");
        newRow.setAttribute("Email", _vendorEmail);
        newRow.setAttribute("BuyerEmail", _buyerEmail);
        newRow.setAttribute("MobNo", mobileNumber);
        newRow.setAttribute("Passwd", _password);
        System.out.println("------ new row created ------ with following data :---");
        System.out.println("Vendor email ::: " + _vendorEmail);
        System.out.println("Buyer email ::: " + _buyerEmail);
        System.out.println("Vendor mbile ::: " + mobileNumber);
        System.out.println("password ::: " + _password);
        // insert this new row into the view
        tblSupplierUserVO.insertRow(newRow);

        System.out.println("----row inserted --------");

        this.getDBTransaction().commit();

        System.out.println("--transaction committed ----------");
    }
}

