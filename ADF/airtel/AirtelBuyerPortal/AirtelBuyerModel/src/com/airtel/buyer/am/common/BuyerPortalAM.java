package com.airtel.buyer.am.common;

import com.airtel.buyer.pojo.SupplierSitesTblRec;
import com.airtel.buyer.pojo.SupplierTblRec;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jul 17 20:27:20 IST 2018
// ---------------------------------------------------------------------
public interface BuyerPortalAM extends ApplicationModule {
    Boolean updateWKFStatus(Long wkfId, Integer status, String comment);

    Boolean enableReApplication(String _partnerVendorId);
}

