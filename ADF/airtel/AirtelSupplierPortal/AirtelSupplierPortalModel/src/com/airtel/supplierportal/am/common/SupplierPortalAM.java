package com.airtel.supplierportal.am.common;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Jul 12 19:08:23 IST 2018
// ---------------------------------------------------------------------
public interface SupplierPortalAM extends ApplicationModule {
    void resetPassword(String email, String password);

    //String activateSupplier(Integer partnerId);
    void onActivation(Integer partnerId);


    void updateOrgDetgailsStatus(String _partnerVendorId);


    void updateOrgDetails(String _partnerUserID, String _partnerVendorId, String _partnerEmail, String _partnerMobile,
                          String _wkfOwner, String _organizationName, String _pan, String _countryCode, String _city,
                          String _state, String _pinCode, String _firstLineAddress, String _companyUrl,
                          String _attribute1, String _attribute2, String _attribute3);

    void createVendorAccount(String _vendorEmail, String _buyerEmail, String _vendorMobile, String _password);

    void createOrgDetails(String _partnerUserID, String _partnerVendorId, String _partnerEmail, String _partnerMobile,
                          String _wkfOwner, String _organizationName, String _pan, String _countryCode, String _city,
                          String _state, String _pinCode, String _firstLineAddress, String _companyUrl,
                          String _attribute1, String _attribute2, String _attribute3);
}

