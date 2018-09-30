package com.airtel.supplierportal.bean.partnerhome;

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

public class PartnerHomeBean implements Serializable {
    @SuppressWarnings("compatibility:-8748184247415620665")
    private static final long serialVersionUID = 1L;
    private static final ADFLogger _logger = ADFLogger.createADFLogger(PartnerHomeBean.class);
    private String _companyName;
    private String _companyUrl;
    private String _address;
    private String _pan;
    private String _vendorCode;
    private String _email;
    private String _mobile;
    
    
    /**
     * default constructor PartnerHomeBean class
     */
    public PartnerHomeBean() {
        super();
    }


    /**
     * method to load supplier info before supplierHome page load
     */
    public void onPageLoad() {
        _logger.config("Entering method onPageLoad");
        
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = fctx.getExternalContext();
            HttpSession session = (HttpSession) ectx.getSession(true);
            SupplierSession supplierObj = (SupplierSession) session.getAttribute("SupplierSessionObject");
            Long partnerId = supplierObj.getPartnerVendorId();
            
            _logger.config("vendor Id ::: "+partnerId);
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
                _companyName =
                    supplierProfileVO.first().getAttribute("VendorName") != null ?
                    supplierProfileVO.first()
                                                                                                               .getAttribute("VendorName")
                                                                                                               .toString() :
                                          "";
                _companyUrl =
                    supplierProfileVO.first().getAttribute("CompanyUrl") != null ?
                    supplierProfileVO.first()
                                                                                                              .getAttribute("CompanyUrl")
                                                                                                              .toString() :
                                          "";
                _address =
                    supplierProfileVO.first().getAttribute("Address") != null ? supplierProfileVO.first()
                                                                                                        .getAttribute("Address")
                                                                                                        .toString() :
                    "";
                _vendorCode =
                    supplierProfileVO.first().getAttribute("Segment1") != null ?
                    supplierProfileVO.first()
                                                                                                            .getAttribute("Segment1")
                                                                                                            .toString() :
                                          "";
                _pan = supplierProfileVO.first().getAttribute("SitePan") != null ? supplierProfileVO.first()
                                                                                                    .getAttribute("SitePan")
                                                                                                    .toString() : "";
                _email =
                    supplierProfileVO.first().getAttribute("EmailAddress") != null ? supplierProfileVO.first()
                                                                                                           .getAttribute("EmailAddress")
                                                                                                           .toString() :
                    "";
                _mobile = supplierProfileVO.first().getAttribute("Phone") != null ? supplierProfileVO.first()
                                                                                                     .getAttribute("Phone")
                                                                                                     .toString() : "";
            }
        } catch (Exception e) {
            _logger.severe("Exception raised "+e.getMessage());
            e.printStackTrace();
           
        }
        
        _logger.config("Exit method onPageLoad");
    }
    
    
    /**
     *method to get bindings
     * @return
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    /**
     *
     * @param _companyName
     */
    public void setCompanyName(String _companyName) {
        this._companyName = _companyName;
    }
    
    /**
     *
     * @return
     */
    public String getCompanyName() {
        return _companyName;
    }
    
    /**
     *
     * @param _companyUrl
     */
    public void setCompanyUrl(String _companyUrl) {
        this._companyUrl = _companyUrl;
    }

    /**
     *
     * @return
     */
    public String getCompanyUrl() {
        return _companyUrl;
    }
    
    /**
     *
     * @param _address
     */
    public void setAddress(String _address) {
        this._address = _address;
    }
    
    /**
     *
     * @return
     */
    public String getAddress() {
        return _address;
    }
    
    /**
     *
     * @param _pan
     */
    public void setPan(String _pan) {
        this._pan = _pan;
    }
    
    /**
     *
     * @return
     */
    public String getPan() {
        return _pan;
    }
    
    /**
     *
     * @param _vendorCode
     */
    public void setVendorCode(String _vendorCode) {
        this._vendorCode = _vendorCode;
    }
    
    /**
     *
     * @return
     */
    public String getVendorCode() {
        return _vendorCode;
    }

    /**
     *
     * @param _email
     */
    public void setEmail(String _email) {
        this._email = _email;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return _email;
    }
    
    /**
     *
     * @param _mobile
     */
    public void setMobile(String _mobile) {
        this._mobile = _mobile;
    }

    /**
     *
     * @return
     */
    public String getMobile() {
        return _mobile;
    }
}
