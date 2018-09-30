package com.airtel.buyer.bean.buyerhome;

import com.airtel.buyer.pojo.VendorSiteRequest;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.share.security.identitymanagement.UserProfile;
import oracle.adf.view.rich.component.rich.data.RichIterator;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

/**
 * The Class MasterHomeBean
 */
public class MasterHomeBean implements Serializable {

    @SuppressWarnings("compatibility:2531540749601416148")
    private static final long serialVersionUID = 1L;

    private static final ADFLogger _logger = ADFLogger.createADFLogger(MasterHomeBean.class);

    /**
     * Gets the bindings.
     *
     * @return the bindings
     */
    private List<VendorSiteRequest> pendingRequestList;
    private List<VendorSiteRequest> masterPendingRequestList;
    private List<VendorSiteRequest> vendorResponsesList;
    private List<VendorSiteRequest> foreignVendorPendingCAApprovalList;
    private List<VendorSiteRequest> vendorResponsesToCARejectionList;
    private Integer vendorPendingRequestCount;
    private Integer vendorResponsesCount;
    private Integer foreignVendorPendingCAApprovalCount;
    private Integer vendorResponsesToCARejectionCount;
    private String vendorType;
    VendorSiteRequest vsrObj = null;
    private RichIterator itrBinding;

    public void setItrBinding(RichIterator itrBinding) {
        this.itrBinding = itrBinding;
    }

    public RichIterator getItrBinding() {
        return itrBinding;
    }

    public void setMasterPendingRequestList(List<VendorSiteRequest> masterPendingRequestList) {
        this.masterPendingRequestList = masterPendingRequestList;
    }

    public List<VendorSiteRequest> getMasterPendingRequestList() {
        return masterPendingRequestList;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorResponsesCount(Integer vendorResponsesCount) {
        this.vendorResponsesCount = vendorResponsesCount;
    }

    public Integer getVendorResponsesCount() {
        return vendorResponsesCount;
    }

    public void setForeignVendorPendingCAApprovalCount(Integer foreignVendorPendingCAApprovalCount) {
        this.foreignVendorPendingCAApprovalCount = foreignVendorPendingCAApprovalCount;
    }

    public Integer getForeignVendorPendingCAApprovalCount() {
        return foreignVendorPendingCAApprovalCount;
    }

    public void setVendorResponsesToCARejectionCount(Integer vendorResponsesToCARejectionCount) {
        this.vendorResponsesToCARejectionCount = vendorResponsesToCARejectionCount;
    }

    public Integer getVendorResponsesToCARejectionCount() {
        return vendorResponsesToCARejectionCount;
    }


    public void setVendorPendingRequestCount(Integer vendorPendingRequestCount) {
        this.vendorPendingRequestCount = vendorPendingRequestCount;
    }

    public Integer getVendorPendingRequestCount() {
        return vendorPendingRequestCount;
    }

    public void setPendingRequestList(List<VendorSiteRequest> pendingRequestList) {
        this.pendingRequestList = pendingRequestList;
    }

    public List<VendorSiteRequest> getPendingRequestList() {
        return pendingRequestList;
    }

    public void setVendorResponsesList(List<VendorSiteRequest> vendorResponsesList) {
        this.vendorResponsesList = vendorResponsesList;
    }

    public List<VendorSiteRequest> getVendorResponsesList() {
        return vendorResponsesList;
    }

    public void setForeignVendorPendingCAApprovalList(List<VendorSiteRequest> foreignVendorPendingCAApprovalList) {
        this.foreignVendorPendingCAApprovalList = foreignVendorPendingCAApprovalList;
    }

    public List<VendorSiteRequest> getForeignVendorPendingCAApprovalList() {
        return foreignVendorPendingCAApprovalList;
    }

    public void setVendorResponsesToCARejectionList(List<VendorSiteRequest> vendorResponsesToCARejectionList) {
        this.vendorResponsesToCARejectionList = vendorResponsesToCARejectionList;
    }

    public List<VendorSiteRequest> getVendorResponsesToCARejectionList() {
        return vendorResponsesToCARejectionList;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    /**
     * This method is called on Taskflow load.
     * @return
     */
    public String fetchWKFVendorSiteRequest() {
        _logger.config("Inside MasterHomeBean ::: fetchWKFVendorSiteRequest ");

        try {
            HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance()
                                                                           .getExternalContext()
                                                                           .getRequest());
            _logger.config("Logged In User:::" + request.getUserPrincipal().getName());
            UserProfile userProfileObj = ADFContext.getCurrent()
                                                   .getSecurityContext()
                                                   .getUserProfile();

            /** creating array list for all the lists */
            pendingRequestList = new ArrayList<>();
            masterPendingRequestList=new ArrayList<>();
            vendorResponsesList = new ArrayList<>();
            foreignVendorPendingCAApprovalList = new ArrayList<>();
            vendorResponsesToCARejectionList = new ArrayList<>();
            /** Initializing counts */
            vendorPendingRequestCount = 0;
            vendorResponsesCount = 0;
            foreignVendorPendingCAApprovalCount = 0;
            vendorResponsesToCARejectionCount = 0;
            /** Getting Binding container*/
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            /**fetch vendor pending requests from the BTVL_WKF_PARTNER_SITE_TBL
            *BTVL_WKF_PARTNER_SITE_TBL_VO1Iterator
            *Iterator for pending requests*/
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_SITE_TBL_VO1Iterator");
            /**viewObject*/
            ViewObjectImpl btvlWkfPartnerSiteTblVO = (ViewObjectImpl) dcIterator.getViewObject();
            /**iterate view object and create Rows to display*/
            RowSetIterator iter = btvlWkfPartnerSiteTblVO.createRowSetIterator(null);
            iter.reset();
            while (iter.hasNext()) {
                _logger.config("----------------------Iterator Started---------- ");
                Row r = iter.next();
                vsrObj = new VendorSiteRequest();
                vsrObj.setVendorCode(r.getAttribute("SPartnerVendorId") != null ?
                                     r.getAttribute("SPartnerVendorId").toString() : "");
                System.out.println("getVendorCode" + vsrObj.getVendorCode());
                /**fetching BTVL_PARTNER_STATUS_TBL details */
                if (vsrObj.getVendorCode() != null) {
                    fetchPartnerStatusTblRecord(vsrObj.getVendorCode());
                }
                /** concatening first name and last name */
                String firstName = r.getAttribute("SFirstName").toString();
                String lastName = r.getAttribute("SLastName").toString();
                if (firstName != null && lastName != null) {
                    String contactName = firstName + " " + lastName;
                    vsrObj.setVendorContactName(contactName);
                }

                /**fetching BTVL_PARTNER_BANK_ACC_TBL details */
                vsrObj.setBankAccRowId(r.getAttribute("SBankAccRowId") != null ?
                                     r.getAttribute("SBankAccRowId").toString() : "");
                if (vsrObj.getBankAccRowId() != null) {
                    System.out.println("testing bank row id"+vsrObj.getBankAccRowId());
                    fetchPartnerStatusBankAccTblRecord(vsrObj.getBankAccRowId());
                }
                vsrObj.setVendorEmail(r.getAttribute("SEmail") != null ? r.getAttribute("SEmail").toString() : "");
                vsrObj.setVendorPhoneNo(r.getAttribute("SContactNo") != null ? r.getAttribute("SContactNo").toString() :
                                        "");
                vsrObj.setSiteAddress(r.getAttribute("SAddress") != null ? r.getAttribute("SAddress").toString() : "");
                vsrObj.setSiteGSTNNo(r.getAttribute("SGstNumber") != null ? r.getAttribute("SGstNumber").toString() :
                                     "");

                vsrObj.setSiteIsDomestic(r.getAttribute("SIsDomestic") != null ?
                                                r.getAttribute("SIsDomestic").toString() : "");
                vsrObj.setSiteRemittanceEmailId(r.getAttribute("SRemittanceEmail") != null ?
                                                r.getAttribute("SRemittanceEmail").toString() : "");

                vsrObj.setSiteIsGSTNExempt(r.getAttribute("SGstExempted") != null ?
                                           r.getAttribute("SGstExempted").toString() : "");
                vsrObj.setSiteIsMSMED(r.getAttribute("SMsmed") != null ? r.getAttribute("SMsmed").toString() : "");
                vsrObj.setSiteStatus(r.getAttribute("SStatus") != null ? r.getAttribute("SStatus").toString() : "");
                /** Adding objects to the list as per the site status with setting count for each view*/
                if (vsrObj.getSiteStatus() != null && vsrObj.getSiteStatus().equalsIgnoreCase("1")) {
                    System.out.println("getGstExempted" + vsrObj.getSiteIsGSTNExempt());
                    pendingRequestList.add(vsrObj);
                    masterPendingRequestList.addAll(pendingRequestList);
                    vendorPendingRequestCount = pendingRequestList.size();
                } else if (vsrObj.getSiteStatus() != null && vsrObj.getSiteStatus().equalsIgnoreCase("2")) {
                    vendorResponsesList.add(vsrObj);
                    vendorResponsesCount = vendorResponsesList.size();
                } else if (vsrObj.getSiteStatus() != null && vsrObj.getSiteStatus().equalsIgnoreCase("3")) {
                    foreignVendorPendingCAApprovalList.add(vsrObj);
                    foreignVendorPendingCAApprovalCount = foreignVendorPendingCAApprovalList.size();
                } else if (vsrObj.getSiteStatus() != null && vsrObj.getSiteStatus().equalsIgnoreCase("4")) {
                    vendorResponsesToCARejectionList.add(vsrObj);
                    vendorResponsesToCARejectionCount = vendorResponsesToCARejectionList.size();
                }

            }
            iter.closeRowSetIterator();


            /** Router Handler for viewing pages */
            if (pendingRequestList.size() > 0) {
                _logger.config("Routed To Pending Requets");
                return "goToPendingRequests";
            } else if (vendorResponsesList.size() > 0) {
                _logger.config("Routed To Vendor Responses");
                return "vendorResponses";
            } else if (foreignVendorPendingCAApprovalList.size() > 0) {
                _logger.config("Routed To CA Approval List");
                return "pendingForCaApproval";
            } else if (vendorResponsesToCARejectionList.size() > 0) {
                _logger.config("Routed To CA Rejection List");
                return "vendorResponseToCaRjection";
            } else {
                return "goToPendingRequests";
            }
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
            return "invalid";
        }

    }

    /** fetching BTVL_WKF_PARTNER_STATUS_TBL details for Views */
    public void fetchPartnerStatusTblRecord(String vendorId) {
        /**fetch vendor status from the BTVL_WKF_PARTNER_STATUS_TBL
        *BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator
        *for pending requests*/
        /** Getting Binding container*/
        Long vendorId1 = Long.parseLong(vendorId);
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");
        ViewObjectImpl btvlWkfPartnerStatusTblVO = (ViewObjectImpl) dcIterator1.getViewObject();
        ViewCriteria vendorRequestVC = btvlWkfPartnerStatusTblVO.getViewCriteria("GetVendorIdVC");
        btvlWkfPartnerStatusTblVO.setNamedWhereClauseParam("bndVendorId", vendorId1);
        //btvlWkfPartnerStatusTblVO.applyViewCriteria(null);
        btvlWkfPartnerStatusTblVO.applyViewCriteria(vendorRequestVC);
        btvlWkfPartnerStatusTblVO.executeQuery();
        RowSetIterator firstRecord = btvlWkfPartnerStatusTblVO.createRowSetIterator(null);
        firstRecord.reset();
        /** setting data on basis of vendorID*/
        while (firstRecord.hasNext()) {
            Row r = firstRecord.next();
            vsrObj.setVendorOrganizationName(r.getAttribute("OrgName") != null ? r.getAttribute("OrgName").toString() :
                                             "");
            vsrObj.setVendorCompanyURL(r.getAttribute("CompanyUrl") != null ? r.getAttribute("CompanyUrl").toString() :
                                       "");
            vsrObj.setVendorPanNumber(r.getAttribute("Pan") != null ? r.getAttribute("Pan").toString() : "");
        }

    }

    /** fetching BTVL_WKF_BANK_ACC_TBL details for Views */
    public void fetchPartnerStatusBankAccTblRecord(String bankRowId) {
        /**fetch vendor bank account details  from the BTVL_WKF_BANK_ACC_TBL
        *BTVL_WKF_BANK_ACC_TBL_VO1Iterator
        *for pending requests*/
        /** Converting string in to long */
        Long bankRowId1 = Long.parseLong(bankRowId);
        System.out.println("longId"+bankRowId1);
        /** Getting Binding container*/
        BindingContainer bindings = getBindings();
        DCBindingContainer dcBinding = (DCBindingContainer) bindings;
        DCIteratorBinding dcIterator1 = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_BANK_ACC_TBL_VO1Iterator");
        ViewObjectImpl btvlWkfPartnerStatusTblVO = (ViewObjectImpl) dcIterator1.getViewObject();
        ViewCriteria vendorRequestVC = btvlWkfPartnerStatusTblVO.getViewCriteria("fetchBankRowIdVc");
        btvlWkfPartnerStatusTblVO.setNamedWhereClauseParam("bndBankRowId", bankRowId1);
        //btvlWkfPartnerStatusTblVO.applyViewCriteria(null);
        btvlWkfPartnerStatusTblVO.applyViewCriteria(vendorRequestVC);
        btvlWkfPartnerStatusTblVO.executeQuery();
        RowSetIterator firstRecord = btvlWkfPartnerStatusTblVO.createRowSetIterator(null);
        firstRecord.reset();
        /** setting data on basis of vendorID*/
        while (firstRecord.hasNext()) {
            Row r = firstRecord.next();
            vsrObj.setSiteBankName(r.getAttribute("SBankName") != null ? r.getAttribute("SBankName").toString() : "");
            vsrObj.setSiteBankAccountNo(r.getAttribute("SAccountNumber") != null ?
                                        r.getAttribute("SAccountNumber").toString() : "");
            vsrObj.setSiteIFSC_SWIFT_Code(r.getAttribute("SIfsSwiftCode") != null ?
                                          r.getAttribute("SIfsSwiftCode").toString() : "");
            /**set account type saving and current */
            if(r.getAttribute("SAccountType") != null && r.getAttribute("SAccountType").toString().equalsIgnoreCase("1")){
            vsrObj.setSiteBankAccountType("SAVING");
            }else{
                vsrObj.setSiteBankAccountType("CURRENT"); 
            }
            /**if pennytesting status -- y -- account details has been validated */
            vsrObj.setSiteIsPennyTestingSuccess(r.getAttribute("SPennyTestSuccessful") != null ?
                                                r.getAttribute("SPennyTestSuccessful").toString() : "");
            if (vsrObj.getSiteIsPennyTestingSuccess() != null &&
                vsrObj.getSiteIsPennyTestingSuccess().equalsIgnoreCase("y"))
                vsrObj.setSiteIsBankDetailsValid("y");
            }

    }


    /**
     * Accept site pending request on btn click
     * mark status 5 on accepting
     * and send confirm mail to supplier
     * @param actionEvent the action event
     */
    //TODO
    /**
     *GET VALUE CHANGE LISTENER FOR THE VENDOR TYPE 
     * @param valueChangeEvent
     * refresh site lists using vendor type 
     */
    public void ValueChangeForVendorType(ValueChangeEvent valueChangeEvent) {
        vendorType= (String)valueChangeEvent.getNewValue();
       System.out.println("check vendor Type"+ vendorType );
       /** filter objects from the list if the site is Domestic*/
       if(vendorType.equalsIgnoreCase("Domestic")){
           pendingRequestList.clear();
           pendingRequestList.addAll(masterPendingRequestList);
           pendingRequestList.removeIf(producer -> producer.getSiteIsDomestic().trim().equalsIgnoreCase("n"));
           System.out.println("size of the list"+ pendingRequestList.size());
           AdfFacesContext.getCurrentInstance().addPartialTarget(itrBinding);
       }else if(vendorType.equalsIgnoreCase("Foreign")){
           pendingRequestList.clear();
           pendingRequestList.addAll(masterPendingRequestList);
           pendingRequestList.removeIf(producer -> producer.getSiteIsDomestic().trim().equalsIgnoreCase("y"));
           System.out.println("size of the list"+ pendingRequestList.size());
           AdfFacesContext.getCurrentInstance().addPartialTarget(itrBinding);
       }else if(vendorType.trim().equalsIgnoreCase("Both")){
           pendingRequestList.clear();
           pendingRequestList.addAll(masterPendingRequestList);
           AdfFacesContext.getCurrentInstance().addPartialTarget(itrBinding);
       }else{
           pendingRequestList.clear();
           pendingRequestList.addAll(masterPendingRequestList); 
           AdfFacesContext.getCurrentInstance().addPartialTarget(itrBinding);
       }
       
    }

   
}
