package com.airtel.buyer.bean.buyerhome;

import com.airtel.buyer.pojo.CreateSupplierRR;
import com.airtel.buyer.pojo.SupplierSitesTblRec;
import com.airtel.buyer.pojo.SupplierTblRec;
import com.airtel.buyer.pojo.VendorRequest;
import com.airtel.buyer.utility.CommonConstants;
import com.airtel.buyer.utility.EBSUtil;
import com.airtel.buyer.utility.EmailUtil;
import com.airtel.buyer.utility.EncryptDecryptUtil;

import java.io.IOException;
import java.io.Serializable;

import java.sql.SQLException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.share.security.identitymanagement.UserProfile;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.ComponentReference;
import org.apache.myfaces.trinidad.util.Service;


/**
 * The Class BuyerHomeBean.
 */
public class BuyerHomeBean implements Serializable {

    /** The Constant serialVersionUID. */
    @SuppressWarnings("compatibility:7199381648914818202")
    private static final long serialVersionUID = 1L;

    /** The Constant _logger. */
    private static final ADFLogger _logger = ADFLogger.createADFLogger(BuyerHomeBean.class);

    /** The show request expiring today link. */
    private Boolean showRequestExpiringTodayLink = Boolean.TRUE;

    /** The vendor request list obj. */
    private List<VendorRequest> vendorRequestListObj;

    /** The vendor request expiring today list obj. */
    private List<VendorRequest> vendorRequestExpiringTodayListObj;

    /** The vendor request rejected list obj. */
    private List<VendorRequest> vendorRequestRejectedListObj;

    /** The vendor request expiring today count. */
    private Integer vendorRequestExpiringTodayCount;

    /** The vendor request pending count. */
    private Integer vendorRequestPendingCount;

    /** The vendor request rejected count. */
    private Integer vendorRequestRejectedCount;

    /** The vendor type list. */
    private List<SelectItem> vendorTypeList;

    /** The curr reject wkf id. */
    private Long currRejectWkfId;

    /** The rejection reason. */
    private String rejectionReason;

    /** The reenable popup. */
    private ComponentReference reenablePopup;

    /** The pending request popup. */
    private ComponentReference pendingRequestPopup;

    /** The expiring today popup. */
    private ComponentReference expiringTodayPopup;

    /** The view attachment popup. */
    private ComponentReference viewAttachmentPopup;

    /** The expiring today warn popup. */
    private ComponentReference expiringTodayWarnPopup;

    /** The vendor mail id. */
    private String vendorMailId;

    /** The buyer mail. */
    private String buyerMail;


    /** The rejection reason is empty flag. */
    private Boolean rejectionReasonIsEmptyFlag = CommonConstants.BOOLEAN_FALSE;

    /** The disable submit btn. */
    private Boolean disableSubmitBtn = CommonConstants.BOOLEAN_TRUE;

    /** The submit btn. */
    private ComponentReference submitBtn;

    /** The current view attachment wkf id. */
    private String currentViewAttachmentWkfId;

    /** The viewed PVR attachement links. */
    private List<String> viewedPVRAttachementLinks = new ArrayList<>();

    /** The doc 1 link. */
    private String doc1Link;

    /** The doc 2 link. */
    private String doc2Link;

    /** The doc 3 link. */
    private String doc3Link;

    /** The view attachment warning popup. */
    private ComponentReference viewAttachmentWarningPopup;

    /** The is current doc 1 viewed. */
    private Boolean isCurrentDoc1Viewed = CommonConstants.BOOLEAN_FALSE;

    /** The is current doc 3 viewed. */
    //    private Boolean isCurrentDoc2Viewed = CommonConstants.BOOLEAN_FALSE;
    private Boolean isCurrentDoc3Viewed = CommonConstants.BOOLEAN_FALSE;

    /** The expiring today parent PGL. */
    private ComponentReference expiringTodayParentPGL;

    /** The rejected request parent PGL. */
    private ComponentReference rejectedRequestParentPGL;

    /** The pending request parent PGL. */
    private ComponentReference pendingRequestParentPGL;

    /** The render doc 1 PGL. */
    private Boolean renderDoc1PGL = CommonConstants.BOOLEAN_TRUE;

    /** The render doc 2 PGL. */
    private Boolean renderDoc2PGL = CommonConstants.BOOLEAN_TRUE;

    /** The render doc 3 PGL. */
    private Boolean renderDoc3PGL = CommonConstants.BOOLEAN_FALSE;


    /**
     * Sets the expiring today warn popup.
     *
     * @param expiringTodayWarnPopup the new expiring today warn popup
     */
    public void setExpiringTodayWarnPopup(RichPopup expiringTodayWarnPopup) {
        this.expiringTodayWarnPopup = ComponentReference.newUIComponentReference(expiringTodayWarnPopup);
    }


    /**
     * Gets the expiring today warn popup.
     *
     * @return the expiring today warn popup
     */
    public RichPopup getExpiringTodayWarnPopup() {
        if (expiringTodayWarnPopup != null) {
            return (RichPopup) expiringTodayWarnPopup.getComponent();
        }
        return null;
    }


    /**
     * Sets the current view attachment wkf id.
     *
     * @param currentViewAttachmentWkfId the new current view attachment wkf id
     */
    public void setCurrentViewAttachmentWkfId(String currentViewAttachmentWkfId) {
        this.currentViewAttachmentWkfId = currentViewAttachmentWkfId;
    }


    /**
     * Gets the current view attachment wkf id.
     *
     * @return the current view attachment wkf id
     */
    public String getCurrentViewAttachmentWkfId() {
        return currentViewAttachmentWkfId;
    }


    /**
     * Sets the rejection reason is empty flag.
     *
     * @param rejectionReasonIsEmptyFlag the new rejection reason is empty flag
     */
    public void setRejectionReasonIsEmptyFlag(Boolean rejectionReasonIsEmptyFlag) {
        this.rejectionReasonIsEmptyFlag = rejectionReasonIsEmptyFlag;
    }


    /**
     * Gets the rejection reason is empty flag.
     *
     * @return the rejection reason is empty flag
     */
    public Boolean getRejectionReasonIsEmptyFlag() {
        return rejectionReasonIsEmptyFlag;
    }


    /**
     * Sets the disable submit btn.
     *
     * @param disableSubmitBtn the new disable submit btn
     */
    public void setDisableSubmitBtn(Boolean disableSubmitBtn) {
        this.disableSubmitBtn = disableSubmitBtn;
    }


    /**
     * Gets the disable submit btn.
     *
     * @return the disable submit btn
     */
    public Boolean getDisableSubmitBtn() {
        return disableSubmitBtn;
    }


    /**
     * Sets the submit btn.
     *
     * @param submitBtn the new submit btn
     */
    public void setSubmitBtn(RichButton submitBtn) {
        this.submitBtn = ComponentReference.newUIComponentReference(submitBtn);
    }


    /**
     * Gets the submit btn.
     *
     * @return the submit btn
     */
    public RichButton getSubmitBtn() {
        if (submitBtn != null) {
            return (RichButton) submitBtn.getComponent();
        }
        return null;
    }


    /**
     * Sets the rejection reason.
     *
     * @param rejectionReason the new rejection reason
     */
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }


    /**
     * Gets the rejection reason.
     *
     * @return the rejection reason
     */
    public String getRejectionReason() {
        return rejectionReason;
    }


    /**
     * Sets the curr reject wkf id.
     *
     * @param currRejectWkfId the new curr reject wkf id
     */
    public void setCurrRejectWkfId(Long currRejectWkfId) {
        this.currRejectWkfId = currRejectWkfId;
    }


    /**
     * Gets the curr reject wkf id.
     *
     * @return the curr reject wkf id
     */
    public Long getCurrRejectWkfId() {
        return currRejectWkfId;
    }


    /**
     * Sets the vendor type list.
     *
     * @param vendorTypeList the new vendor type list
     */
    public void setVendorTypeList(List<SelectItem> vendorTypeList) {
        this.vendorTypeList = vendorTypeList;
    }


    /**
     * Gets the vendor type list.
     *
     * @return the vendor type list
     */
    public List<SelectItem> getVendorTypeList() {
        return vendorTypeList;
    }


    /**
     * Sets the vendor request list obj.
     *
     * @param vendorRequestListObj the new vendor request list obj
     */
    public void setVendorRequestListObj(List<VendorRequest> vendorRequestListObj) {
        this.vendorRequestListObj = vendorRequestListObj;
    }


    /**
     * Gets the vendor request list obj.
     *
     * @return the vendor request list obj
     */
    public List<VendorRequest> getVendorRequestListObj() {
        return vendorRequestListObj;
    }


    /**
     * Sets the vendor request expiring today list obj.
     *
     * @param vendorRequestExpiringTodayListObj the new vendor request expiring today list obj
     */
    public void setVendorRequestExpiringTodayListObj(List<VendorRequest> vendorRequestExpiringTodayListObj) {
        this.vendorRequestExpiringTodayListObj = vendorRequestExpiringTodayListObj;
    }


    /**
     * Gets the vendor request expiring today list obj.
     *
     * @return the vendor request expiring today list obj
     */
    public List<VendorRequest> getVendorRequestExpiringTodayListObj() {
        return vendorRequestExpiringTodayListObj;
    }


    /**
     * Sets the show request expiring today link.
     *
     * @param showRequestExpiringTodayLink the new show request expiring today link
     */
    public void setShowRequestExpiringTodayLink(Boolean showRequestExpiringTodayLink) {
        this.showRequestExpiringTodayLink = showRequestExpiringTodayLink;
    }


    /**
     * Gets the show request expiring today link.
     *
     * @return the show request expiring today link
     */
    public Boolean getShowRequestExpiringTodayLink() {
        return showRequestExpiringTodayLink;
    }

    /**
     * Instantiates a new buyer home bean.
     */
    public BuyerHomeBean() {
    }


    /**
     * Fetch vendor requests.
     * Getting all the submitted request from the db which have status 4\
     * Storing those request in List Object
     * Storing pending request in vendorRequestListObj List
     * String rejected request in vendorRequestRejectedListObj List
     * calculatig the expiration time of request and storing them in vendorRequestExpiringTodayListObj List
     * if there is count of expiry request greater than 1 then expiry today request page will visible else
     * it will get redirected to the pending request page
     * @return the string
     */
    public String fetchVendorRequests() {
        _logger.config("Inside BuyerHomeBean ::: fetchVendorRequests ");
        try {
            HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance()
                                                                           .getExternalContext()
                                                                           .getRequest());
            _logger.config("Logged In User:::" + request.getUserPrincipal().getName());
            UserProfile userProfileObj = ADFContext.getCurrent()
                                                   .getSecurityContext()
                                                   .getUserProfile();
            vendorRequestListObj = new ArrayList<VendorRequest>();
            vendorRequestExpiringTodayListObj = new ArrayList<VendorRequest>();
            vendorRequestRejectedListObj = new ArrayList<VendorRequest>();
            vendorTypeList = new ArrayList<SelectItem>();
            vendorRequestExpiringTodayCount = 0;
            vendorRequestPendingCount = 0;
            vendorRequestRejectedCount = 0;
            // getting binding of pages
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;

            //fetch all vendor types from BTVL_PO_LOOKUP_CODES_MV_RVO1Iterator
            DCIteratorBinding lookUpCodesIterator =
                dcBinding.findIteratorBinding("BTVL_PO_LOOKUP_CODES_MV_RVO1Iterator");
            ViewObjectImpl btvlPOLookupCodesMvRVO = (ViewObjectImpl) lookUpCodesIterator.getViewObject();
            RowSetIterator vendorTypeIter = btvlPOLookupCodesMvRVO.createRowSetIterator(null);
            vendorTypeIter.reset();
            while (vendorTypeIter.hasNext()) {
                Row r = vendorTypeIter.next();
                SelectItem si = new SelectItem();
                //            LookupCode
                //            DisplayedField

                if (r.getAttribute("DisplayedField") != null && r.getAttribute("LookupCode") != null) {
                    _logger.config("---row added in dropdown--label-" + r.getAttribute("DisplayedField").toString() +
                                   "--value-" + r.getAttribute("LookupCode").toString());
                    si.setLabel(r.getAttribute("DisplayedField").toString());
                    si.setValue(r.getAttribute("LookupCode").toString());
                    vendorTypeList.add(si);
                }

            }
            vendorTypeIter.closeRowSetIterator();

            // Fetch all vendor request for logged in buyer - goToExpiry
            // if there are no requests expiring today, route to pending requests jsff - goToPendingReq
            // BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator


            // getting iterator for vendor requests
            DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_WKF_PARTNER_STATUS_TBL_VO1Iterator");

            // getting viewobject
            ViewObjectImpl btvlWkfPartnerStatusTblVO = (ViewObjectImpl) dcIterator.getViewObject();

            ViewCriteria vendorRequestVC = btvlWkfPartnerStatusTblVO.getViewCriteria("VendorRequestVC");
            btvlWkfPartnerStatusTblVO.setNamedWhereClauseParam("bndBuyerEmailId", userProfileObj.getBusinessEmail());
            btvlWkfPartnerStatusTblVO.applyViewCriteria(null);
            btvlWkfPartnerStatusTblVO.applyViewCriteria(vendorRequestVC);
            btvlWkfPartnerStatusTblVO.executeQuery();
            // iterate view object and create Rows to display

            RowSetIterator iter = btvlWkfPartnerStatusTblVO.createRowSetIterator(null);
            iter.reset();
            while (iter.hasNext()) {
                _logger.config("--------------------------------------------------------------------------------------");
                Row r = iter.next();
                VendorRequest vrObj = new VendorRequest();
                vrObj.setWKF_ID(r.getAttribute("WkfId") != null ? r.getAttribute("WkfId").toString() : "");
                vrObj.setWKF_OWNER(r.getAttribute("WkfOwner") != null ? r.getAttribute("WkfOwner").toString() : "");
                vrObj.setWFK_COMMENT(r.getAttribute("WfkComment") != null ? r.getAttribute("WfkComment").toString() :
                                     "");
                vrObj.setPARTNER_USER_ID(r.getAttribute("PartnerUserId") != null ?
                                         r.getAttribute("PartnerUserId").toString() : "");
                vrObj.setPARTNER_VENDOR_ID(r.getAttribute("PartnerVendorId") != null ?
                                           r.getAttribute("PartnerVendorId").toString() : "");
                vrObj.setORG_NAME(r.getAttribute("OrgName") != null ? r.getAttribute("OrgName").toString() : "");
                vrObj.setPAN(r.getAttribute("Pan") != null ? r.getAttribute("Pan").toString() : "");
                vrObj.setCOUNTRY(r.getAttribute("Country") != null ? r.getAttribute("Country").toString() : "");
                vrObj.setSTATE(r.getAttribute("PState") != null ? r.getAttribute("PState").toString() : "");
                vrObj.setCITY(r.getAttribute("City") != null ? r.getAttribute("City").toString() : "");
                vrObj.setPINCODE(r.getAttribute("Pincode") != null ? r.getAttribute("Pincode").toString() : "");
                vrObj.setADD_LINE_1(r.getAttribute("AddLine1") != null ? r.getAttribute("AddLine1").toString() : "");
                vrObj.setSTATUS(r.getAttribute("Status") != null ? r.getAttribute("Status").toString() : "");
                vrObj.setDOC1(r.getAttribute("Attribute1") != null ? r.getAttribute("Attribute1").toString() : "");
                vrObj.setDOC2(r.getAttribute("Attribute2") != null ? r.getAttribute("Attribute2").toString() : "");
                vrObj.setP_USER_ID(r.getAttribute("PUserId") != null ? r.getAttribute("PUserId").toString() : "");
                vrObj.setCOMPANY_URL(r.getAttribute("CompanyUrl") != null ? r.getAttribute("CompanyUrl").toString() :
                                     "");
                vrObj.setPARTNER_EMAIL(r.getAttribute("PartnerEmail") != null ?
                                       r.getAttribute("PartnerEmail").toString() : "");
                vrObj.setPARTNER_MOBILE(r.getAttribute("PartnerMobile") != null ?
                                        r.getAttribute("PartnerMobile").toString() : "");
                vrObj.setCREATED_DATE_TIME(r.getAttribute("CreatedDateTime") != null ?
                                           r.getAttribute("CreatedDateTime").toString() : "");
                vrObj.setMODIFIED_DATE_TIME(r.getAttribute("ModifiedDateTime") != null ?
                                            r.getAttribute("ModifiedDateTime").toString() : "");
                vrObj.setEXPIRY_DATE(r.getAttribute("ExpiryDate") != null ? r.getAttribute("ExpiryDate").toString() :
                                     "");
                String panContentId =
                    r.getAttribute("Attribute1") != null ? r.getAttribute("Attribute1").toString() : "";
                String companyRegistrationContentId =
                    r.getAttribute("Attribute2") != null ? r.getAttribute("Attribute2").toString() : "";
                String proprietorshipContentId =
                    r.getAttribute("Attribute3") != null ? r.getAttribute("Attribute3").toString() : "";

                _logger.config("PAN Content ID :::" + panContentId);
                String panWebURL = "";
                String panNativeURL = "";
                //            if(!panContentId.isEmpty()){
                //                Map<String,String> panMetaData = DmsUtil.searchDocInUCM(panContentId);
                //                for (Map.Entry<String, String> entry : panMetaData.entrySet()) {
                //                    _logger.config(entry.getKey() + " = " + entry.getValue());
                //                    if (entry.getKey() != null && entry.getKey().equals("webURL")) {
                //                        panWebURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                    if (entry.getKey() != null && entry.getKey().equals("nativeURL")) {
                //                        panNativeURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                }
                //            }
                //            vrObj.setDOC1(panWebURL);
                vrObj.setDOC1(panContentId);
                //            _logger.config("PAN WebURL :::"+panWebURL);
                //            _logger.config("PAN NativeURL :::"+panNativeURL);

                //            _logger.config("Company Registration Content ID :::"+companyRegistrationContentId);
                //            String cRWebURL ="";
                //            String cRNativeURL ="";
                //            if(!companyRegistrationContentId.isEmpty()){
                //                Map<String,String> panMetaData = DmsUtil.searchDocInUCM(companyRegistrationContentId);
                //                for (Map.Entry<String, String> entry : panMetaData.entrySet()) {
                //                    _logger.config(entry.getKey() + " = " + entry.getValue());
                //                    if (entry.getKey() != null && entry.getKey().equals("webURL")) {
                //                        cRWebURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                    if (entry.getKey() != null && entry.getKey().equals("nativeURL")) {
                //                        cRNativeURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                }
                //            }
                //            vrObj.setDOC2(cRWebURL);
                //            _logger.config("Company Registration WebURL :::"+cRWebURL);
                //            _logger.config("Company Registration NativeURL :::"+cRNativeURL);

                _logger.config("Proprietorship Content ID :::" + proprietorshipContentId);
                //            String propWebURL ="";
                //            String propNativeURL ="";
                //            if(!proprietorshipContentId.isEmpty()){
                //                Map<String,String> panMetaData = DmsUtil.searchDocInUCM(proprietorshipContentId);
                //                for (Map.Entry<String, String> entry : panMetaData.entrySet()) {
                //                    _logger.config(entry.getKey() + " = " + entry.getValue());
                //                    if (entry.getKey() != null && entry.getKey().equals("webURL")) {
                //                        propWebURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                    if (entry.getKey() != null && entry.getKey().equals("nativeURL")) {
                //                        propNativeURL = entry.getValue();//WCCDEV_173040
                //                    }
                //                }
                //            }
                //            vrObj.setDOC3(propWebURL);
                vrObj.setDOC3(proprietorshipContentId);
                //            _logger.config("Proprietorship WebURL :::"+propWebURL);
                //            _logger.config("Proprietorship NativeURL :::"+propNativeURL);

                _logger.config(vrObj.toString());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate expiryDateObj = LocalDate.parse(vrObj.getEXPIRY_DATE(), formatter);

                LocalDate today = LocalDate.now();
                if (vrObj.getSTATUS().equalsIgnoreCase("4")) {
                    //status = 4 means Company Information Submitted
                    vendorRequestListObj.add(vrObj);
                    if (expiryDateObj.isEqual(today)) { //Check if expiry date is today, add to expiry list
                        vendorRequestExpiringTodayListObj.add(vrObj);
                    }
                } else if (vrObj.getSTATUS().equalsIgnoreCase("6")) {
                    //status = 6 means Company Information Rejected
                    vendorRequestRejectedListObj.add(vrObj);
                }
            }
            iter.closeRowSetIterator();

            //set counts for each view
            vendorRequestExpiringTodayCount = vendorRequestExpiringTodayListObj.size();
            vendorRequestPendingCount = vendorRequestListObj.size();
            vendorRequestRejectedCount = vendorRequestRejectedListObj.size();

            //refreshComponent(expiringTodayParentPGL);
            refreshComponent(getExpiringTodayParentPGL());
            // refreshComponent(pendingRequestParentPGL);
            refreshComponent(getPendingRequestParentPGL());
            // refreshComponent(rejectedRequestParentPGL);
            refreshComponent(getRejectedRequestParentPGL());

            if (vendorRequestExpiringTodayListObj.size() > 0) {
                _logger.config("ROUTE TO TODAY VIEW");
                _logger.config("Exiting from BuyerHomeBean ::: fetchVendorRequests");
                return "goToExpiry";
            } else {
                _logger.config("ROUTE TO PENDING VIEW");
                _logger.config("Exiting from BuyerHomeBean ::: fetchVendorRequests");
                return "goToPendingReq";
            }
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
            _logger.config("Exiting from BuyerHomeBean ::: fetchVendorRequests");
            return "invalid";
        }

    }


    /**
     * Sets the vendor request expiring today count.
     *
     * @param vendorRequestExpiringTodayCount the new vendor request expiring today count
     */
    public void setVendorRequestExpiringTodayCount(Integer vendorRequestExpiringTodayCount) {
        this.vendorRequestExpiringTodayCount = vendorRequestExpiringTodayCount;
    }


    /**
     * Gets the vendor request expiring today count.
     *
     * @return the vendor request expiring today count
     */
    public Integer getVendorRequestExpiringTodayCount() {
        return vendorRequestExpiringTodayCount;
    }


    /**
     * Sets the vendor request pending count.
     *
     * @param vendorRequestPendingCount the new vendor request pending count
     */
    public void setVendorRequestPendingCount(Integer vendorRequestPendingCount) {
        this.vendorRequestPendingCount = vendorRequestPendingCount;
    }


    /**
     * Gets the vendor request pending count.
     *
     * @return the vendor request pending count
     */
    public Integer getVendorRequestPendingCount() {
        return vendorRequestPendingCount;
    }


    /**
     * Sets the vendor request rejected count.
     *
     * @param vendorRequestRejectedCount the new vendor request rejected count
     */
    public void setVendorRequestRejectedCount(Integer vendorRequestRejectedCount) {
        this.vendorRequestRejectedCount = vendorRequestRejectedCount;
    }


    /**
     * Gets the vendor request rejected count.
     *
     * @return the vendor request rejected count
     */
    public Integer getVendorRequestRejectedCount() {
        return vendorRequestRejectedCount;
    }


    /**
     * Sets the vendor request rejected list obj.
     *
     * @param vendorRequestRejectedListObj the new vendor request rejected list obj
     */
    public void setVendorRequestRejectedListObj(List<VendorRequest> vendorRequestRejectedListObj) {
        this.vendorRequestRejectedListObj = vendorRequestRejectedListObj;
    }


    /**
     * Gets the vendor request rejected list obj.
     *
     * @return the vendor request rejected list obj
     */
    public List<VendorRequest> getVendorRequestRejectedListObj() {
        return vendorRequestRejectedListObj;
    }


    /**
     * Gets the bindings.
     *
     * @return the bindings
     */
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    /**
     * Accept vendor request btn click.
     * to accept vender request , first view required attachment of supplier type based on domestic and non-domestic.
     * then select vender type from lov.
     * based on conditions he accept or reject the request.
     * If supplier request is accepted then status change to 5
     * and send confirm mail to supplier
     * @param actionEvent the action event
     */
    public void acceptVendorRequestBtnClick(ActionEvent actionEvent) {
        _logger.config("Inside BuyerHomeBean ::: acceptVendorRequestBtnClick :::: actionEvent param");
        try {
            String pass = null;
            String rowWkfId = (String) (actionEvent.getComponent()
                                                   .getAttributes()
                                                   .get("rowWkfId"));
            _logger.config("rowWkfId:::" + rowWkfId);
            Boolean isViewAttachmentClicked = CommonConstants.BOOLEAN_FALSE;
            isViewAttachmentClicked = rowWkfId.equalsIgnoreCase(currentViewAttachmentWkfId);
            _logger.config("Are all docs viewed and view attachements clicked :::" +
                           (isViewAttachmentClicked && isCurrentDoc1Viewed && isCurrentDoc3Viewed));
            // Fix for Prod Bug Starts - Buyer unable to accept request - Nishant - 14-AUGUST-2018
            Boolean isDoc3CheckRequired = renderDoc3PGL;
            Boolean proceedAccept = CommonConstants.BOOLEAN_FALSE;
            _logger.config("isViewAttachmentClicked:::"+isViewAttachmentClicked);
            _logger.config("isDoc3CheckRequired:::"+isDoc3CheckRequired);
            _logger.config("isCurrentDoc1Viewed:::"+isCurrentDoc1Viewed);
            _logger.config("isCurrentDoc3Viewed:::"+isCurrentDoc3Viewed);
            if(isDoc3CheckRequired){
                proceedAccept = isViewAttachmentClicked && isCurrentDoc1Viewed && isCurrentDoc3Viewed;
            } else{
                proceedAccept = isViewAttachmentClicked && isCurrentDoc1Viewed;
            }
            _logger.config("proceedAccept:::"+proceedAccept);
            _logger.config("Are all docs viewed and view attachements clicked :::"+proceedAccept);
            if (proceedAccept) {
            // Fix for Prod Bug Ends - Buyer unable to accept request - Nishant - 14-AUGUST-2018
                String currVendorType = (String) (actionEvent.getComponent()
                                                             .getAttributes()
                                                             .get("vendorType"));
                _logger.config("currVendorType:::" + currVendorType);

                // expiringTodayReq
                String fragmentName = (String) (actionEvent.getComponent()
                                                           .getAttributes()
                                                           .get("fragment"));
                // pendingReq
                // getting binding of pages
                BindingContainer bindings = getBindings();


                Long wkfId = Long.parseLong(rowWkfId);


                // insert data into M_SUPPLIER TABLES
                _logger.config("Inserting Records in Supplier and Supplier Sites Tables");
                List<VendorRequest> vrObjList = vendorRequestListObj.stream()
                                                                    .filter(vr -> vr.getWKF_ID().equalsIgnoreCase(rowWkfId))
                                                                    .collect(Collectors.toList());
                VendorRequest vr = vrObjList.get(0);
                _logger.config("Printing Vendor Request Object");
                _logger.config(vr.getWKF_ID() + "---" + vr.getPARTNER_VENDOR_ID() + "----" + vr.getVENDOR_TYPE() +
                               "---");
                SupplierTblRec supplierTblRecObj =
                    new SupplierTblRec(Long.parseLong(vr.getPARTNER_VENDOR_ID()), vr.getORG_NAME(), currVendorType,
                                       currVendorType, vr.getCOMPANY_URL(),
                                       vr.getCOUNTRY().equalsIgnoreCase("IN") ? "domestic" : "international");
                _logger.config("Printing SupplierTbl Record------");
                _logger.config("Supplier----" + supplierTblRecObj.getPartnerVendorId() + "----" +
                               supplierTblRecObj.getVendorName() + "---" + supplierTblRecObj.getVendorType() + "----" +
                               supplierTblRecObj.getVendorTypeLookupCode());
                SupplierSitesTblRec supplierSitesTblRec =
                    new SupplierSitesTblRec(wkfId, Long.parseLong(vr.getPARTNER_VENDOR_ID()), vr.getADD_LINE_1(),
                                            vr.getCITY(), vr.getSTATE(), vr.getPINCODE(), vr.getCOUNTRY(), vr.getPAN(),
                                            vr.getPARTNER_MOBILE(), vr.getPARTNER_EMAIL());
                _logger.config("Printing supplier sites record-----");
                _logger.config("Supplier Site----" + supplierSitesTblRec.getCustomVendorSiteId() + "---" +
                               supplierSitesTblRec.getPartnerVendorId() + "----" + supplierSitesTblRec.getSitePan());
                OperationBinding createSupplierRecordsOB = bindings.getOperationBinding("createSupplierRecords");
                createSupplierRecordsOB.getParamsMap().put("supplierTblRec", supplierTblRecObj);
                createSupplierRecordsOB.getParamsMap().put("supplierSitesTblRec", supplierSitesTblRec);
                Boolean isSupplierCreated = (Boolean) createSupplierRecordsOB.execute();
                _logger.config("Supplier created Portal Tables :::" + isSupplierCreated);
                if (isSupplierCreated) {
                    // and then call procedure
                    // BTVL_PARTNER_USER_TBL_RVO1Iterator
                    DCBindingContainer dcBinding = (DCBindingContainer) bindings;
                    DCIteratorBinding dcIterator = dcBinding.findIteratorBinding("BTVL_PARTNER_USER_TBL_RVO1Iterator");

                    // getting viewobject
                    ViewObjectImpl btvlPartnerUserTblVO = (ViewObjectImpl) dcIterator.getViewObject();
                    //            FetchUserPassword
                    //            bndPartnerUserId
                    ViewCriteria btvlPartnerUserTblVC = btvlPartnerUserTblVO.getViewCriteria("FetchUserPassword");
                    btvlPartnerUserTblVO.setNamedWhereClauseParam("bndPartnerUserId", vr.getPARTNER_USER_ID());
                    btvlPartnerUserTblVO.applyViewCriteria(null);
                    btvlPartnerUserTblVO.applyViewCriteria(btvlPartnerUserTblVC);
                    btvlPartnerUserTblVO.executeQuery();

                    if (btvlPartnerUserTblVO.first() != null) {
                        Row r = btvlPartnerUserTblVO.first();
                        pass = r.getAttribute("Passwd").toString();
                    }
                    try {
                        String statusColumn = "";
                        String partnerCode = "";
                        CreateSupplierRR createSupplierRRObj =
                            EBSUtil.callProcCreateSupplierInEBS(new CreateSupplierRR(Long.parseLong(vr.getPARTNER_VENDOR_ID()),
                                                                                     EncryptDecryptUtil.decrypt(pass),
                                                                                     vr.getWKF_OWNER()));
                        // call supplier tbl to check status and message
                        // BTVL_M_SUPPLIERS_TBL_VO1Iterator
                        dcIterator = dcBinding.findIteratorBinding("BTVL_M_SUPPLIERS_TBL_VO1Iterator");
                        ViewObjectImpl supplierVO = (ViewObjectImpl) dcIterator.getViewObject();
                        // Check if record exists
                        _logger.config("Check if supplier record exists");
                        ViewCriteria getSupplierRecordbyPartnerVendorIdVC =
                            supplierVO.getViewCriteria("GetSupplierRecordbyPartnerVendorId");
                        supplierVO.setNamedWhereClauseParam("bndPartnerVendorId",
                                                            supplierTblRecObj.getPartnerVendorId());
                        supplierVO.applyViewCriteria(null);
                        supplierVO.applyViewCriteria(getSupplierRecordbyPartnerVendorIdVC);
                        supplierVO.executeQuery();
                        _logger.config("record check complete");
                        if (supplierVO.first() != null) {
                            // update supplier record
                            _logger.config("Supplier exists already");
                            Row supplierTblRecRow = supplierVO.first();
                            partnerCode =
                                supplierTblRecRow.getAttribute("Segment1") != null ?
                                supplierTblRecRow.getAttribute("Segment1").toString() : "";
                            _logger.config("----Partner Code Received from EBS -----" + partnerCode);
                            statusColumn =
                                supplierTblRecRow.getAttribute("Status") != null ?
                                supplierTblRecRow.getAttribute("Status").toString() : "";
                            _logger.config("----Status Received from EBS -----" + statusColumn);

                        }

                        if (statusColumn.equalsIgnoreCase("success") &&
                            !partnerCode.equalsIgnoreCase("")) {
                            // send email to buyer
                            String bodyBuyer =
                                                              String.format(CommonConstants.PARTNER_CREATION_BUYER_EMAIL_BODY,
                                                                            vr.getORG_NAME(), partnerCode,
                                                                            vr.getPARTNER_MOBILE(),
                                                                            vr.getPARTNER_EMAIL());
                            _logger.config("Email Body sent to Buyer:::");
                            _logger.config(bodyBuyer);
                            Boolean isMailSentToBuyer =
                                EmailUtil.sendEmail(vr.getWKF_OWNER(),
                                                    CommonConstants.PARTNER_CREATION_BUYER_EMAIL_SENDER,
                                                    CommonConstants.PARTNER_CREATION_BUYER_EMAIL_SUBJECT, bodyBuyer);
                            _logger.config("Mail Sent to Buyer:::" + isMailSentToBuyer);
                            // send email to partner
                            String bodyPartner =
                                String.format(CommonConstants.PARTNER_CREATION_PARTN_EMAIL_BODY, vr.getORG_NAME(),
                                              partnerCode, vr.getPARTNER_EMAIL(), vr.getPARTNER_MOBILE());
                            _logger.config("Email Body sent to Buyer:::");
                            _logger.config(bodyPartner);
                            Boolean isMailSentToPartner =
                                EmailUtil.sendEmail(vr.getPARTNER_EMAIL(),
                                                    CommonConstants.PARTNER_CREATION_BUYER_EMAIL_SENDER,
                                                    CommonConstants.PARTNER_CREATION_PART_EMAIL_SUBJECT, bodyPartner);
                            _logger.config("Mail Sent to Buyer:::" + isMailSentToPartner);
                            if (isMailSentToPartner && isMailSentToBuyer) {
                                // update workflow table with status 5 - accepted
                                OperationBinding updateWKFStatusOB = bindings.getOperationBinding("updateWKFStatus");
                                updateWKFStatusOB.getParamsMap().put("wkfId", wkfId);
                                updateWKFStatusOB.getParamsMap().put("status", 5);
                                updateWKFStatusOB.getParamsMap().put("comment", "");

                                Boolean result = (Boolean) updateWKFStatusOB.execute();
                                _logger.config("Status updated to Accpeted :::" + result);
                                if (fragmentName.equalsIgnoreCase("expiringTodayReq")) {
                                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                                    getExpiringTodayPopup().show(hints);
                                } else {
                                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                                    getPendingRequestPopup().show(hints);
                                }
                            }

                        } else {
                            RichPopup.PopupHints hints = new RichPopup.PopupHints();
                            getExpiringTodayWarnPopup().show(hints);
                        }
                    } catch (SQLException sqle) {
                        _logger.severe("Exception raised ::: " + sqle.getMessage(), sqle);
                        sqle.printStackTrace();
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        getExpiringTodayWarnPopup().show(hints);
                    } catch (Exception e) {
                        _logger.severe("Exception raised ::: " + e.getMessage(), e);
                        e.printStackTrace();
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        getExpiringTodayWarnPopup().show(hints);
                    }
                }
            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getViewAttachmentWarningPopup().show(hints);
            }
        } catch (NumberFormatException nfe) {
            _logger.severe("Exception raised ::: " + nfe.getMessage(), nfe);
            nfe.printStackTrace();
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }

        _logger.config("Exiting from BuyerHomeBean ::: acceptVendorRequestBtnClick ");
    }


    /**
     * Open popup.
     * Based on Accept or Reject operation, result will be shown on popup
     * @param popupId the popup id
     */
    public void openPopup(String popupId) {
        _logger.config("Inside BuyerHomeBean ::: openPopup ::: param popupId ");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            StringBuilder script = new StringBuilder();
            ExtendedRenderKitService extRenderKitSrvc =
                Service.getRenderKitService(context, ExtendedRenderKitService.class);
            script.append("var popup = AdfPage.PAGE.findComponent('" + popupId + "'); ").append("popup.show();");
            extRenderKitSrvc.addScript(context, script.toString());
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: openPopup ::: param popupId ");
    }


    /**
     * Reject vendor req btn click.
     * Used for reject supplier request and send a mail to supplier with rejection reason
     * @param actionEvent the action event
     */
    public void rejectVendorReqBtnClick(ActionEvent actionEvent) {
        _logger.config("Inside BuyerHomeBean ::: rejectVendorReqBtnClick...");
        try {
            _logger.config("rejected workflow item is :::" + currRejectWkfId);
            // getting binding of pages
            BindingContainer bindings = getBindings();
            DCBindingContainer dcBinding = (DCBindingContainer) bindings;
            OperationBinding updateWKFStatusOB = bindings.getOperationBinding("updateWKFStatus");

            updateWKFStatusOB.getParamsMap().put("wkfId", currRejectWkfId);
            updateWKFStatusOB.getParamsMap().put("status", 6);
            updateWKFStatusOB.getParamsMap().put("comment", rejectionReason);

            Boolean result = (Boolean) updateWKFStatusOB.execute();


            EmailUtil.sendEmail(vendorMailId, CommonConstants.PARTNER_REJECTION_EMAIL_SENDER,
                                CommonConstants.PARTNER_REJECTION_EMAIL_SUBJECT,
                                String.format(CommonConstants.PARTNER_REJECTION_EMAIL_BODY, rejectionReason,
                                              buyerMail));
            _logger.config("Supplier Rejected :::" + result);
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean :::  rejectVendorReqBtnClick ");
    }


    /**
     * On reject link click.
     * used for redirect to reject reason view page
     * @param actionEvent the action event
     */
    public void onRejectLinkClick(ActionEvent actionEvent) {
        _logger.config("Inside Reject Link ActionListner...");
        try {
            String rowWkfId = (String) (actionEvent.getComponent()
                                                   .getAttributes()
                                                   .get("rowWkfId"));
            _logger.config("rowWkfId:::" + rowWkfId);
            currRejectWkfId = Long.parseLong(rowWkfId);
            vendorMailId = actionEvent.getComponent()
                                      .getAttributes()
                                      .get("rowSupplierMail")
                                      .toString();

            buyerMail = actionEvent.getComponent()
                                   .getAttributes()
                                   .get("rowBuyerMail")
                                   .toString();
        } catch (NumberFormatException nfe) {
            _logger.severe("Exception raised ::: " + nfe.getMessage(), nfe);
            nfe.printStackTrace();
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: onRejectLinkClick");

    }


    /**
     * On vendor type change.
     *
     * @param valueChangeEvent the value change event
     */
    public void onVendorTypeChange(ValueChangeEvent valueChangeEvent) {
        _logger.config("Vendor Type Change Listner called");


    }


    /**
     * Enable reapplication.
     * Used for re-enable rejected request.
     * and delete correspnding records information from db.
     * and then supplier able to register.
     * @param actionEvent the action event
     */
    public void enableReapplication(ActionEvent actionEvent) {
        try {
            String rowPartnerVendorId = (String) (actionEvent.getComponent()
                                                             .getAttributes()
                                                             .get("rowPartnerVendorId"));
            _logger.config("rowPartnerVendorId:::" + rowPartnerVendorId);

            // getting binding of pages
            BindingContainer bindings = getBindings();
            OperationBinding enableReApplicationOB = bindings.getOperationBinding("enableReApplication");

            enableReApplicationOB.getParamsMap().put("_partnerVendorId", rowPartnerVendorId);

            Boolean result = (Boolean) enableReApplicationOB.execute();
            _logger.config("----After delete method execution ---- result :::::" + result);
            if (result) {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                getReenablePopup().show(hints);
            }
        } catch (Exception e) {
            _logger.severe("Exception raised :::" + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: enableReapplication ::");
    }


    /**
     * Sets the reenable popup.
     *
     * @param reenablePopup the new reenable popup
     */
    public void setReenablePopup(RichPopup reenablePopup) {
        this.reenablePopup = ComponentReference.newUIComponentReference(reenablePopup);
    }


    /**
     * Gets the reenable popup.
     *
     * @return the reenable popup
     */
    public RichPopup getReenablePopup() {
        if (reenablePopup != null) {
            return (RichPopup) reenablePopup.getComponent();
        }
        return null;
    }


    /**
     * Sets the pending request popup.
     *
     * @param pendingRequestPopup the new pending request popup
     */
    public void setPendingRequestPopup(RichPopup pendingRequestPopup) {
        this.pendingRequestPopup = ComponentReference.newUIComponentReference(pendingRequestPopup);
    }


    /**
     * Gets the pending request popup.
     *
     * @return the pending request popup
     */
    public RichPopup getPendingRequestPopup() {

        if (pendingRequestPopup != null) {
            return (RichPopup) pendingRequestPopup.getComponent();
        }
        return null;
    }


    /**
     * Sets the expiring today popup.
     *
     * @param expiringTodayPopup the new expiring today popup
     */
    public void setExpiringTodayPopup(RichPopup expiringTodayPopup) {
        this.expiringTodayPopup = ComponentReference.newUIComponentReference(expiringTodayPopup);
    }


    /**
     * Gets the expiring today popup.
     *
     * @return the expiring today popup
     */
    public RichPopup getExpiringTodayPopup() {
        if (expiringTodayPopup != null) {
            return (RichPopup) expiringTodayPopup.getComponent();
        }
        return null;
    }


    /**
     * On rejection reason VC.
     * validate rejected reason field on ValueChangeEvent of resaon field value
     * @param valueChangeEvent the value change event
     */
    public void onRejectionReasonVC(ValueChangeEvent valueChangeEvent) {
        try {
            _logger.config("--------Inside onRejectionReasonVC method --------");
            String _rejectionReason = valueChangeEvent.getNewValue().toString();
            _logger.config("rejectionReason");
            rejectionReason = _rejectionReason;
            if (_rejectionReason != null && !_rejectionReason.isEmpty()) {
                rejectionReasonIsEmptyFlag = CommonConstants.BOOLEAN_FALSE;
            } else {
                rejectionReasonIsEmptyFlag = CommonConstants.BOOLEAN_TRUE;
            }
            refreshSubmitBtn();
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: onRejectionReasonVc");
    }


    /**
     * code for refreshing the ui component.
     *
     * @param uiComp the ui comp
     */
    private void refreshComponent(UIComponent uiComp) {
        if (uiComp != null) {
            AdfFacesContext.getCurrentInstance().addPartialTarget(uiComp); //refreshing ui components
        }
    }


    /**
     * Refresh submit btn.
     * used for enable/disable submit button based on rejection field value.
     */
    public void refreshSubmitBtn() {
        _logger.config("Inside BuyerHomeBean :::  fefreshSubmitBtn :::");
        try {
            _logger.config("----FLAGS------");
            _logger.config("----FLAG VALUES------");
            _logger.config(rejectionReason);
            _logger.config("----------");
            if (rejectionReasonIsEmptyFlag) {
                //when any error is visible ie true disble button
                disableSubmitBtn = CommonConstants.BOOLEAN_TRUE;
                _logger.config("button disabled");
            } else {
                //when all errors are not visible enable login button
                if (rejectionReason != null && !rejectionReason.isEmpty()) {
                    disableSubmitBtn = CommonConstants.BOOLEAN_FALSE;
                    _logger.config("button enabled" + disableSubmitBtn);
                } else {
                    disableSubmitBtn = CommonConstants.BOOLEAN_TRUE;
                    _logger.config("button disabled " + disableSubmitBtn);
                }
            }
            refreshComponent(getSubmitBtn());
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: refreshSubmitBtn ");
    }


    /**
     * Do logout.
     * Used for log out and invalidate current session.
     * and route to login page
     * @param event the event
     */
    public void doLogout(ActionEvent event) {
        _logger.config("Inside BuyerHomeBean ::: doLogout ::: param actionEvent");
        try {
            FacesContext fctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fctx.getExternalContext().getSession(true);
            session.invalidate();
            ExternalContext ectx = fctx.getExternalContext();
            _logger.config(FacesContext.getCurrentInstance()
                                       .getExternalContext()
                                       .getApplicationContextPath());
            _logger.config(FacesContext.getCurrentInstance()
                                       .getExternalContext()
                                       .getRequestServletPath());
            ectx.redirect(ectx.getApplicationContextPath() + ectx.getRequestServletPath() +
                          "/pages/login/buyerLogin.jspx");
        } catch (IOException e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: doLogout ");
    }


    /**
     * Sets the view attachment popup.
     *
     * @param viewAttachmentPopup the new view attachment popup
     */
    public void setViewAttachmentPopup(RichPopup viewAttachmentPopup) {
        this.viewAttachmentPopup = ComponentReference.newUIComponentReference(viewAttachmentPopup);
    }


    /**
     * Gets the view attachment popup.
     *
     * @return the view attachment popup
     */
    public RichPopup getViewAttachmentPopup() {
        if (viewAttachmentPopup != null) {
            return (RichPopup) viewAttachmentPopup.getComponent();
        }
        return null;
    }


     /**
          * View attachment popup fetch listener.
          * this method is called after viewAttachmentClick request, that internally hit request for this method
          * 
          * this method show list of attachment to be view by buyer to take decision of accept or reject request.
          * Inside the list three type of document listed
          * 1. Pan Card
          * 2. Company Registration
          * 3. Proprietorship
          *  click on document to download
          *  
          *  Note:  if 4th character of pan is P, show propritory doc.
          * @param popupFetchEvent the popup fetch event
          */
    public void viewAttachmentPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        _logger.config("Inside BuyerHomeBean :::  viewAttachmentPopupFetchListener");
        try {
            _logger.config("Fetching Workflow item from Vendor Request List...");
            List<VendorRequest> vrObjList = vendorRequestListObj.stream()
                                                                .filter(vr -> vr.getWKF_ID().equalsIgnoreCase(currentViewAttachmentWkfId))
                                                                .collect(Collectors.toList());
            VendorRequest vr = vrObjList.get(0);
            _logger.config("VR OBJ :::");
            _logger.config(vr.toString());

            //get context path and form servlet url
            FacesContext ctx = FacesContext.getCurrentInstance();
            String contextPath = ((HttpServletRequest) ctx.getExternalContext().getRequest()).getContextPath();

            String url = contextPath + "/faces/getDocument?attributeId="; //?attributeId=WCCDEV_173211

            if (!vr.getDOC1().isEmpty()) {
                //            doc1Link=vr.getDOC1();
                doc1Link = url + vr.getDOC1();
            }
            //        if(!vr.getDOC2().isEmpty()){
            //            doc2Link=vr.getDOC2();
            //        }
            // if 4th character of pan is P, show propritory doc.
            if (vr.getPAN() != null && !vr.getPAN().isEmpty()) {
                Boolean isPanProprietory = Character.toString(vr.getPAN().charAt(3)).equalsIgnoreCase("P");
                renderDoc3PGL = isPanProprietory; //render panelGroupLayout if pan 4th character is P

                if (!vr.getDOC3().isEmpty()) {
                    doc3Link = url + vr.getDOC3();
                }
            } else {
                isCurrentDoc3Viewed = CommonConstants.BOOLEAN_TRUE;
            }
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: viewAttachmentPopupFetchListener");
    }


     /**
         * View attachment click.
         * this method is used to request to open a popup, that show documents
         * @param actionEvent the action event
         */
    public void viewAttachmentClick(ActionEvent actionEvent) {
        _logger.config("Inside BuyerHomeBean ::: viewAttachmentClick ::: param actionEvent");
        try {
            String rowWkfIdObj = (String) (actionEvent.getComponent()
                                                      .getAttributes()
                                                      .get("rowWkfId"));
            _logger.config("rowPartnerVendorId:::" + rowWkfIdObj);
            currentViewAttachmentWkfId = rowWkfIdObj;
            viewedPVRAttachementLinks.add(currentViewAttachmentWkfId);
            //reset doc viewed flag
            isCurrentDoc1Viewed = CommonConstants.BOOLEAN_FALSE;
            //        isCurrentDoc2Viewed = CommonConstants.BOOLEAN_FALSE;
            isCurrentDoc3Viewed = CommonConstants.BOOLEAN_FALSE;
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getViewAttachmentPopup().show(hints);
        } catch (Exception e) {
            _logger.severe("Exception raised :::" + e.getMessage(), e);
            e.printStackTrace();
        }
    }


    /**
     * Sets the doc 1 link.
     *
     * @param doc1Link the new doc 1 link
     */
    public void setDoc1Link(String doc1Link) {
        this.doc1Link = doc1Link;
    }


    /**
     * Gets the doc 1 link.
     *
     * @return the doc 1 link
     */
    public String getDoc1Link() {
        return doc1Link;
    }

    //    public void setDoc2Link(String doc2Link) {
    //        this.doc2Link = doc2Link;
    //    }
    //
    //    public String getDoc2Link() {
    //        return doc2Link;
    //    }


    /**
     * Sets the doc 3 link.
     *
     * @param doc3Link the new doc 3 link
     */
    public void setDoc3Link(String doc3Link) {
        this.doc3Link = doc3Link;
    }


    /**
     * Gets the doc 3 link.
     *
     * @return the doc 3 link
     */
    public String getDoc3Link() {
        return doc3Link;
    }


    /**
     * Sets the view attachment warning popup.
     *
     * @param viewAttachmentWarningPopup the new view attachment warning popup
     */
    public void setViewAttachmentWarningPopup(RichPopup viewAttachmentWarningPopup) {
        this.viewAttachmentWarningPopup = ComponentReference.newUIComponentReference(viewAttachmentWarningPopup);
    }


    /**
     * Gets the view attachment warning popup.
     *
     * @return the view attachment warning popup
     */
    public RichPopup getViewAttachmentWarningPopup() {
        if (viewAttachmentWarningPopup != null) {
            return (RichPopup) viewAttachmentWarningPopup.getComponent();
        }
        return null;
    }


    /**
     * View doc 1 click.
     *
     * @param actionEvent the action event
     */
    public void viewDoc1Click(ActionEvent actionEvent) {
        _logger.config("Inside BuyerHomeBean :::  viewDoc1Click ::: @param actionEvent");
        try {
            isCurrentDoc1Viewed = CommonConstants.BOOLEAN_TRUE;
            StringBuilder buffer = new StringBuilder();
            buffer.append("window.parent.open('" + doc1Link + "','_blank')");
            ExtendedRenderKitService service =
                Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
            service.addScript(FacesContext.getCurrentInstance(), buffer.toString());
        } catch (Exception e) {
            _logger.severe("Exception raised :::" + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: viewDoc1Click");
    }

    //    public void viewDoc2Click(ActionEvent actionEvent) {
    //        _logger.config("Inside viewDoc2Click");
    //        isCurrentDoc2Viewed = CommonConstants.BOOLEAN_TRUE;
    //
    //        StringBuilder buffer = new StringBuilder();
    //        buffer.append("window.parent.open('" + doc2Link + "','_blank')");
    //        ExtendedRenderKitService service =
    //            Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
    //        service.addScript(FacesContext.getCurrentInstance(), buffer.toString());
    //
    //    }


    /**
     * View doc 3 click.
     *
     * @param actionEvent the action event
     */
    public void viewDoc3Click(ActionEvent actionEvent) {
        _logger.config("Inside BuyerHomeBean ::: viewDoc3Click ::: @param actionEvent");
        try {
            isCurrentDoc3Viewed = CommonConstants.BOOLEAN_TRUE;
            StringBuilder buffer = new StringBuilder();
            buffer.append("window.parent.open('" + doc3Link + "','_blank')");
            ExtendedRenderKitService service =
                Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
            service.addScript(FacesContext.getCurrentInstance(), buffer.toString());
        } catch (Exception e) {
            _logger.severe("Exception raised ::: " + e.getMessage(), e);
            e.printStackTrace();
        }
        _logger.config("Exiting from BuyerHomeBean ::: viewDoc3Click");
    }


    /**
     * Sets the expiring today parent PGL.
     *
     * @param expiringTodayParentPGL the new expiring today parent PGL
     */
    public void setExpiringTodayParentPGL(RichPanelGroupLayout expiringTodayParentPGL) {
        this.expiringTodayParentPGL = ComponentReference.newUIComponentReference(expiringTodayParentPGL);
    }


    /**
     * Gets the expiring today parent PGL.
     *
     * @return the expiring today parent PGL
     */
    public RichPanelGroupLayout getExpiringTodayParentPGL() {
        if (expiringTodayParentPGL != null) {
            return (RichPanelGroupLayout) expiringTodayParentPGL.getComponent();
        }
        return null;
    }


    /**
     * Sets the rejected request parent PGL.
     *
     * @param rejectedRequestParentPGL the new rejected request parent PGL
     */
    public void setRejectedRequestParentPGL(RichPanelGroupLayout rejectedRequestParentPGL) {
        this.rejectedRequestParentPGL = ComponentReference.newUIComponentReference(rejectedRequestParentPGL);
    }


    /**
     * Gets the rejected request parent PGL.
     *
     * @return the rejected request parent PGL
     */
    public RichPanelGroupLayout getRejectedRequestParentPGL() {
        if (rejectedRequestParentPGL != null) {
            return (RichPanelGroupLayout) rejectedRequestParentPGL.getComponent();
        }
        return null;
    }


    /**
     * Sets the pending request parent PGL.
     *
     * @param pendingRequestParentPGL the new pending request parent PGL
     */
    public void setPendingRequestParentPGL(RichPanelGroupLayout pendingRequestParentPGL) {
        this.pendingRequestParentPGL = ComponentReference.newUIComponentReference(pendingRequestParentPGL);
    }


    /**
     * Gets the pending request parent PGL.
     *
     * @return the pending request parent PGL
     */
    public RichPanelGroupLayout getPendingRequestParentPGL() {
        if (pendingRequestParentPGL != null) {
            return (RichPanelGroupLayout) pendingRequestParentPGL.getComponent();
        }
        return null;
    }


    /**
     * Sets the render doc 1 PGL.
     *
     * @param renderDoc1PGL the new render doc 1 PGL
     */
    public void setRenderDoc1PGL(Boolean renderDoc1PGL) {
        this.renderDoc1PGL = renderDoc1PGL;
    }


    /**
     * Gets the render doc 1 PGL.
     *
     * @return the render doc 1 PGL
     */
    public Boolean getRenderDoc1PGL() {
        return renderDoc1PGL;
    }

    //    public void setRenderDoc2PGL(Boolean renderDoc2PGL) {
    //        this.renderDoc2PGL = renderDoc2PGL;
    //    }
    //
    //    public Boolean getRenderDoc2PGL() {
    //        return renderDoc2PGL;
    //    }


    /**
     * Sets the render doc 3 PGL.
     *
     * @param renderDoc3PGL the new render doc 3 PGL
     */
    public void setRenderDoc3PGL(Boolean renderDoc3PGL) {
        this.renderDoc3PGL = renderDoc3PGL;
    }


    /**
     * Gets the render doc 3 PGL.
     *
     * @return the render doc 3 PGL
     */
    public Boolean getRenderDoc3PGL() {
        return renderDoc3PGL;
    }
}
