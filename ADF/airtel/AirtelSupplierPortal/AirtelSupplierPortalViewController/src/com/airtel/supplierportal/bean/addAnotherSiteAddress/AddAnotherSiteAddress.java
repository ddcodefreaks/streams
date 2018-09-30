package com.airtel.supplierportal.bean.addAnotherSiteAddress;

import com.airtel.supplierportal.bean.partnerRegistrationPhase2.PartnerRegistrationPhase2;

import com.airtel.supplierportal.pojo.session.SupplierSession;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

public class AddAnotherSiteAddress implements Serializable {
    @SuppressWarnings("compatibility:7664484977447152353")
    private static final long serialVersionUID = 1L;
    
    private static final ADFLogger _logger = ADFLogger.createADFLogger(AddAnotherSiteAddress.class);

    public AddAnotherSiteAddress() {
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
}
