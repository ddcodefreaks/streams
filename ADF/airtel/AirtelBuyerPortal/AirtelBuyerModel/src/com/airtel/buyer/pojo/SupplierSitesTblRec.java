package com.airtel.buyer.pojo;

import java.io.Serializable;

import java.sql.Timestamp;

public class SupplierSitesTblRec implements Serializable {
    @SuppressWarnings("compatibility:-1213033660021402594")
    private static final long serialVersionUID = 1L;


    public SupplierSitesTblRec(Long _CustomVendorSiteId, Long _PartnerVendorId, String _AddressLine1, String _City,
                               String _State, String _Zip, String _Country, String _SitePan, String _Phone,
                               String _EmailAddress) {
        this._CustomVendorSiteId = _CustomVendorSiteId;
        this._PartnerVendorId = _PartnerVendorId;
        this._AddressLine1 = _AddressLine1;
        this._City = _City;
        this._State = _State;
        this._Zip = _Zip;
        this._Country = _Country;
        this._SitePan = _SitePan;
        this._EmailAddress = _EmailAddress;
        this._Phone = _Phone;
    }

    public SupplierSitesTblRec() {
        super();
    }

    private Long _CustomVendorSiteId;
    private Long _PartnerVendorId;
    private Long _VendorSiteId;
    private Timestamp _LastUpdateDate;
    private Long _LastUpdatedBy;
    private Long _VendorId;
    private String _VendorSiteCode;
    private String _VendorSiteCodeAlt;
    private Long _LastUpdateLogin;
    private Timestamp _CreationDate;
    private Long _CreatedBy;
    private String _PurchasingSiteFlag;
    private String _RfqOnlySiteFlag;
    private String _PaySiteFlag;
    private String _AttentionArFlag;
    private String _AddressLine1;
    private String _AddressLinesAlt;
    private String _AddressLine2;
    private String _AddressLine3;
    private String _City;
    private String _State;
    private String _Zip;
    private String _Province;
    private String _Country;
    private String _AreaCode;
    private String _Phone;
    private String _CustomerNum;
    private Long _ShipToLocationId;
    private Long _BillToLocationId;
    private String _ShipViaLookupCode;
    private String _FreightTermsLookupCode;
    private String _FobLookupCode;
    private Timestamp _InactiveDate;
    private String _Fax;
    private String _FaxAreaCode;
    private String _Telex;
    private String _PaymentMethodLookupCode;
    private String _BankAccountName;
    private String _BankAccountNum;
    private String _BankNum;
    private String _BankAccountType;
    private String _TermsDateBasis;
    private String _CurrentCatalogNum;
    private String _VatCode;
    private Long _DistributionSetId;
    private Long _AcctsPayCodeCombinationId;
    private Long _PrepayCodeCombinationId;
    private String _PayGroupLookupCode;
    private Long _PaymentPriority;
    private Long _TermsId;
    private Long _InvoiceAmountLimit;
    private String _PayDateBasisLookupCode;
    private String _AlwaysTakeDiscFlag;
    private String _InvoiceCurrencyCode;
    private String _PaymentCurrencyCode;
    private String _HoldAllPaymentsFlag;
    private String _HoldFuturePaymentsFlag;
    private String _HoldReason;
    private String _HoldUnmatchedInvoicesFlag;
    private String _ApTaxRoundingRule;
    private String _AutoTaxCalcFlag;
    private String _AutoTaxCalcOverride;
    private String _AmountIncludesTaxFlag;
    private String _ExclusivePaymentFlag;
    private String _TaxReportingSiteFlag;
    private String _AttributeCategory;
    private String _Attribute1;
    private String _Attribute2;
    private String _Attribute3;
    private String _Attribute4;
    private String _Attribute5;
    private String _Attribute6;
    private String _Attribute7;
    private String _Attribute8;
    private String _Attribute9;
    private String _Attribute10;
    private String _Attribute11;
    private String _Attribute12;
    private String _Attribute13;
    private String _Attribute14;
    private String _Attribute15;
    private Long _RequestId;
    private Long _ProgramApplicationId;
    private Long _ProgramId;
    private Timestamp _ProgramUpdateDate;
    private Long _ValidationNumber;
    private String _ExcludeFreightFromDiscount;
    private String _VatRegistrationNum;
    private String _OffsetVatCode;
    private Long _OrgId;
    private String _CheckDigits;
    private String _BankNumber;
    private String _AddressLine4;
    private String _County;
    private String _AddressStyle;
    private String _Language;
    private String _AllowAwtFlag;
    private Long _AwtGroupId;
    private String _GlobalAttribute1;
    private String _GlobalAttribute2;
    private String _GlobalAttribute3;
    private String _GlobalAttribute4;
    private String _GlobalAttribute5;
    private String _GlobalAttribute6;
    private String _GlobalAttribute7;
    private String _GlobalAttribute8;
    private String _GlobalAttribute9;
    private String _GlobalAttribute10;
    private String _GlobalAttribute11;
    private String _GlobalAttribute12;
    private String _GlobalAttribute13;
    private String _GlobalAttribute14;
    private String _GlobalAttribute15;
    private String _GlobalAttribute16;
    private String _GlobalAttribute17;
    private String _GlobalAttribute18;
    private String _GlobalAttribute19;
    private String _GlobalAttribute20;
    private String _GlobalAttributeCategory;
    private String _EdiTransactionHandling;
    private String _EdiIdNumber;
    private String _EdiPaymentMethod;
    private String _EdiPaymentFormat;
    private String _EdiRemittanceMethod;
    private String _BankChargeBearer;
    private String _EdiRemittanceInstruction;
    private String _BankBranchType;
    private String _PayOnCode;
    private Long _DefaultPaySiteId;
    private String _PayOnReceiptSummaryCode;
    private Long _TpHeaderId;
    private String _EceTpLocationCode;
    private String _PcardSiteFlag;
    private String _MatchOption;
    private String _CountryOfOriginCode;
    private Long _FutureDatedPaymentCcid;
    private String _CreateDebitMemoFlag;
    private String _OffsetTaxFlag;
    private String _SupplierNotifMethod;
    private String _EmailAddress;
    private String _RemittanceEmail;
    private String _PrimaryPaySiteFlag;
    private String _ShippingControl;
    private String _SellingCompanyIdentifier;
    private String _GaplessInvNumFlag;
    private String _DunsNumber;
    private Long _ToleranceId;
    private Long _LocationId;
    private Long _PartySiteId;
    private Long _ServicesToleranceId;
    private Long _RetainageRate;
    private String _TcaSyncState;
    private String _TcaSyncProvince;
    private String _TcaSyncCounty;
    private String _TcaSyncCity;
    private String _TcaSyncZip;
    private String _TcaSyncCountry;
    private Long _PayAwtGroupId;
    private String _CageCode;
    private String _LegalBusinessName;
    private String _DoingBusAsName;
    private String _DivisionName;
    private String _SmallBusinessCode;
    private String _CcrComments;
    private Timestamp _DebarmentStartDate;
    private Timestamp _DebarmentEndDate;
    private String _SitePan;

    public void setCustomVendorSiteId(Long _CustomVendorSiteId) {
        this._CustomVendorSiteId = _CustomVendorSiteId;
    }

    public Long getCustomVendorSiteId() {
        return _CustomVendorSiteId;
    }

    public void setPartnerVendorId(Long _PartnerVendorId) {
        this._PartnerVendorId = _PartnerVendorId;
    }

    public Long getPartnerVendorId() {
        return _PartnerVendorId;
    }

    public void setVendorSiteId(Long _VendorSiteId) {
        this._VendorSiteId = _VendorSiteId;
    }

    public Long getVendorSiteId() {
        return _VendorSiteId;
    }

    public void setLastUpdateDate(Timestamp _LastUpdateDate) {
        this._LastUpdateDate = _LastUpdateDate;
    }

    public Timestamp getLastUpdateDate() {
        return _LastUpdateDate;
    }

    public void setLastUpdatedBy(Long _LastUpdatedBy) {
        this._LastUpdatedBy = _LastUpdatedBy;
    }

    public Long getLastUpdatedBy() {
        return _LastUpdatedBy;
    }

    public void setVendorId(Long _VendorId) {
        this._VendorId = _VendorId;
    }

    public Long getVendorId() {
        return _VendorId;
    }

    public void setVendorSiteCode(String _VendorSiteCode) {
        this._VendorSiteCode = _VendorSiteCode;
    }

    public String getVendorSiteCode() {
        return _VendorSiteCode;
    }

    public void setVendorSiteCodeAlt(String _VendorSiteCodeAlt) {
        this._VendorSiteCodeAlt = _VendorSiteCodeAlt;
    }

    public String getVendorSiteCodeAlt() {
        return _VendorSiteCodeAlt;
    }

    public void setLastUpdateLogin(Long _LastUpdateLogin) {
        this._LastUpdateLogin = _LastUpdateLogin;
    }

    public Long getLastUpdateLogin() {
        return _LastUpdateLogin;
    }

    public void setCreationDate(Timestamp _CreationDate) {
        this._CreationDate = _CreationDate;
    }

    public Timestamp getCreationDate() {
        return _CreationDate;
    }

    public void setCreatedBy(Long _CreatedBy) {
        this._CreatedBy = _CreatedBy;
    }

    public Long getCreatedBy() {
        return _CreatedBy;
    }

    public void setPurchasingSiteFlag(String _PurchasingSiteFlag) {
        this._PurchasingSiteFlag = _PurchasingSiteFlag;
    }

    public String getPurchasingSiteFlag() {
        return _PurchasingSiteFlag;
    }

    public void setRfqOnlySiteFlag(String _RfqOnlySiteFlag) {
        this._RfqOnlySiteFlag = _RfqOnlySiteFlag;
    }

    public String getRfqOnlySiteFlag() {
        return _RfqOnlySiteFlag;
    }

    public void setPaySiteFlag(String _PaySiteFlag) {
        this._PaySiteFlag = _PaySiteFlag;
    }

    public String getPaySiteFlag() {
        return _PaySiteFlag;
    }

    public void setAttentionArFlag(String _AttentionArFlag) {
        this._AttentionArFlag = _AttentionArFlag;
    }

    public String getAttentionArFlag() {
        return _AttentionArFlag;
    }

    public void setAddressLine1(String _AddressLine1) {
        this._AddressLine1 = _AddressLine1;
    }

    public String getAddressLine1() {
        return _AddressLine1;
    }

    public void setAddressLinesAlt(String _AddressLinesAlt) {
        this._AddressLinesAlt = _AddressLinesAlt;
    }

    public String getAddressLinesAlt() {
        return _AddressLinesAlt;
    }

    public void setAddressLine2(String _AddressLine2) {
        this._AddressLine2 = _AddressLine2;
    }

    public String getAddressLine2() {
        return _AddressLine2;
    }

    public void setAddressLine3(String _AddressLine3) {
        this._AddressLine3 = _AddressLine3;
    }

    public String getAddressLine3() {
        return _AddressLine3;
    }

    public void setCity(String _City) {
        this._City = _City;
    }

    public String getCity() {
        return _City;
    }

    public void setState(String _State) {
        this._State = _State;
    }

    public String getState() {
        return _State;
    }

    public void setZip(String _Zip) {
        this._Zip = _Zip;
    }

    public String getZip() {
        return _Zip;
    }

    public void setProvince(String _Province) {
        this._Province = _Province;
    }

    public String getProvince() {
        return _Province;
    }

    public void setCountry(String _Country) {
        this._Country = _Country;
    }

    public String getCountry() {
        return _Country;
    }

    public void setAreaCode(String _AreaCode) {
        this._AreaCode = _AreaCode;
    }

    public String getAreaCode() {
        return _AreaCode;
    }

    public void setPhone(String _Phone) {
        this._Phone = _Phone;
    }

    public String getPhone() {
        return _Phone;
    }

    public void setCustomerNum(String _CustomerNum) {
        this._CustomerNum = _CustomerNum;
    }

    public String getCustomerNum() {
        return _CustomerNum;
    }

    public void setShipToLocationId(Long _ShipToLocationId) {
        this._ShipToLocationId = _ShipToLocationId;
    }

    public Long getShipToLocationId() {
        return _ShipToLocationId;
    }

    public void setBillToLocationId(Long _BillToLocationId) {
        this._BillToLocationId = _BillToLocationId;
    }

    public Long getBillToLocationId() {
        return _BillToLocationId;
    }

    public void setShipViaLookupCode(String _ShipViaLookupCode) {
        this._ShipViaLookupCode = _ShipViaLookupCode;
    }

    public String getShipViaLookupCode() {
        return _ShipViaLookupCode;
    }

    public void setFreightTermsLookupCode(String _FreightTermsLookupCode) {
        this._FreightTermsLookupCode = _FreightTermsLookupCode;
    }

    public String getFreightTermsLookupCode() {
        return _FreightTermsLookupCode;
    }

    public void setFobLookupCode(String _FobLookupCode) {
        this._FobLookupCode = _FobLookupCode;
    }

    public String getFobLookupCode() {
        return _FobLookupCode;
    }

    public void setInactiveDate(Timestamp _InactiveDate) {
        this._InactiveDate = _InactiveDate;
    }

    public Timestamp getInactiveDate() {
        return _InactiveDate;
    }

    public void setFax(String _Fax) {
        this._Fax = _Fax;
    }

    public String getFax() {
        return _Fax;
    }

    public void setFaxAreaCode(String _FaxAreaCode) {
        this._FaxAreaCode = _FaxAreaCode;
    }

    public String getFaxAreaCode() {
        return _FaxAreaCode;
    }

    public void setTelex(String _Telex) {
        this._Telex = _Telex;
    }

    public String getTelex() {
        return _Telex;
    }

    public void setPaymentMethodLookupCode(String _PaymentMethodLookupCode) {
        this._PaymentMethodLookupCode = _PaymentMethodLookupCode;
    }

    public String getPaymentMethodLookupCode() {
        return _PaymentMethodLookupCode;
    }

    public void setBankAccountName(String _BankAccountName) {
        this._BankAccountName = _BankAccountName;
    }

    public String getBankAccountName() {
        return _BankAccountName;
    }

    public void setBankAccountNum(String _BankAccountNum) {
        this._BankAccountNum = _BankAccountNum;
    }

    public String getBankAccountNum() {
        return _BankAccountNum;
    }

    public void setBankNum(String _BankNum) {
        this._BankNum = _BankNum;
    }

    public String getBankNum() {
        return _BankNum;
    }

    public void setBankAccountType(String _BankAccountType) {
        this._BankAccountType = _BankAccountType;
    }

    public String getBankAccountType() {
        return _BankAccountType;
    }

    public void setTermsDateBasis(String _TermsDateBasis) {
        this._TermsDateBasis = _TermsDateBasis;
    }

    public String getTermsDateBasis() {
        return _TermsDateBasis;
    }

    public void setCurrentCatalogNum(String _CurrentCatalogNum) {
        this._CurrentCatalogNum = _CurrentCatalogNum;
    }

    public String getCurrentCatalogNum() {
        return _CurrentCatalogNum;
    }

    public void setVatCode(String _VatCode) {
        this._VatCode = _VatCode;
    }

    public String getVatCode() {
        return _VatCode;
    }

    public void setDistributionSetId(Long _DistributionSetId) {
        this._DistributionSetId = _DistributionSetId;
    }

    public Long getDistributionSetId() {
        return _DistributionSetId;
    }

    public void setAcctsPayCodeCombinationId(Long _AcctsPayCodeCombinationId) {
        this._AcctsPayCodeCombinationId = _AcctsPayCodeCombinationId;
    }

    public Long getAcctsPayCodeCombinationId() {
        return _AcctsPayCodeCombinationId;
    }

    public void setPrepayCodeCombinationId(Long _PrepayCodeCombinationId) {
        this._PrepayCodeCombinationId = _PrepayCodeCombinationId;
    }

    public Long getPrepayCodeCombinationId() {
        return _PrepayCodeCombinationId;
    }

    public void setPayGroupLookupCode(String _PayGroupLookupCode) {
        this._PayGroupLookupCode = _PayGroupLookupCode;
    }

    public String getPayGroupLookupCode() {
        return _PayGroupLookupCode;
    }

    public void setPaymentPriority(Long _PaymentPriority) {
        this._PaymentPriority = _PaymentPriority;
    }

    public Long getPaymentPriority() {
        return _PaymentPriority;
    }

    public void setTermsId(Long _TermsId) {
        this._TermsId = _TermsId;
    }

    public Long getTermsId() {
        return _TermsId;
    }

    public void setInvoiceAmountLimit(Long _InvoiceAmountLimit) {
        this._InvoiceAmountLimit = _InvoiceAmountLimit;
    }

    public Long getInvoiceAmountLimit() {
        return _InvoiceAmountLimit;
    }

    public void setPayDateBasisLookupCode(String _PayDateBasisLookupCode) {
        this._PayDateBasisLookupCode = _PayDateBasisLookupCode;
    }

    public String getPayDateBasisLookupCode() {
        return _PayDateBasisLookupCode;
    }

    public void setAlwaysTakeDiscFlag(String _AlwaysTakeDiscFlag) {
        this._AlwaysTakeDiscFlag = _AlwaysTakeDiscFlag;
    }

    public String getAlwaysTakeDiscFlag() {
        return _AlwaysTakeDiscFlag;
    }

    public void setInvoiceCurrencyCode(String _InvoiceCurrencyCode) {
        this._InvoiceCurrencyCode = _InvoiceCurrencyCode;
    }

    public String getInvoiceCurrencyCode() {
        return _InvoiceCurrencyCode;
    }

    public void setPaymentCurrencyCode(String _PaymentCurrencyCode) {
        this._PaymentCurrencyCode = _PaymentCurrencyCode;
    }

    public String getPaymentCurrencyCode() {
        return _PaymentCurrencyCode;
    }

    public void setHoldAllPaymentsFlag(String _HoldAllPaymentsFlag) {
        this._HoldAllPaymentsFlag = _HoldAllPaymentsFlag;
    }

    public String getHoldAllPaymentsFlag() {
        return _HoldAllPaymentsFlag;
    }

    public void setHoldFuturePaymentsFlag(String _HoldFuturePaymentsFlag) {
        this._HoldFuturePaymentsFlag = _HoldFuturePaymentsFlag;
    }

    public String getHoldFuturePaymentsFlag() {
        return _HoldFuturePaymentsFlag;
    }

    public void setHoldReason(String _HoldReason) {
        this._HoldReason = _HoldReason;
    }

    public String getHoldReason() {
        return _HoldReason;
    }

    public void setHoldUnmatchedInvoicesFlag(String _HoldUnmatchedInvoicesFlag) {
        this._HoldUnmatchedInvoicesFlag = _HoldUnmatchedInvoicesFlag;
    }

    public String getHoldUnmatchedInvoicesFlag() {
        return _HoldUnmatchedInvoicesFlag;
    }

    public void setApTaxRoundingRule(String _ApTaxRoundingRule) {
        this._ApTaxRoundingRule = _ApTaxRoundingRule;
    }

    public String getApTaxRoundingRule() {
        return _ApTaxRoundingRule;
    }

    public void setAutoTaxCalcFlag(String _AutoTaxCalcFlag) {
        this._AutoTaxCalcFlag = _AutoTaxCalcFlag;
    }

    public String getAutoTaxCalcFlag() {
        return _AutoTaxCalcFlag;
    }

    public void setAutoTaxCalcOverride(String _AutoTaxCalcOverride) {
        this._AutoTaxCalcOverride = _AutoTaxCalcOverride;
    }

    public String getAutoTaxCalcOverride() {
        return _AutoTaxCalcOverride;
    }

    public void setAmountIncludesTaxFlag(String _AmountIncludesTaxFlag) {
        this._AmountIncludesTaxFlag = _AmountIncludesTaxFlag;
    }

    public String getAmountIncludesTaxFlag() {
        return _AmountIncludesTaxFlag;
    }

    public void setExclusivePaymentFlag(String _ExclusivePaymentFlag) {
        this._ExclusivePaymentFlag = _ExclusivePaymentFlag;
    }

    public String getExclusivePaymentFlag() {
        return _ExclusivePaymentFlag;
    }

    public void setTaxReportingSiteFlag(String _TaxReportingSiteFlag) {
        this._TaxReportingSiteFlag = _TaxReportingSiteFlag;
    }

    public String getTaxReportingSiteFlag() {
        return _TaxReportingSiteFlag;
    }

    public void setAttributeCategory(String _AttributeCategory) {
        this._AttributeCategory = _AttributeCategory;
    }

    public String getAttributeCategory() {
        return _AttributeCategory;
    }

    public void setAttribute1(String _Attribute1) {
        this._Attribute1 = _Attribute1;
    }

    public String getAttribute1() {
        return _Attribute1;
    }

    public void setAttribute2(String _Attribute2) {
        this._Attribute2 = _Attribute2;
    }

    public String getAttribute2() {
        return _Attribute2;
    }

    public void setAttribute3(String _Attribute3) {
        this._Attribute3 = _Attribute3;
    }

    public String getAttribute3() {
        return _Attribute3;
    }

    public void setAttribute4(String _Attribute4) {
        this._Attribute4 = _Attribute4;
    }

    public String getAttribute4() {
        return _Attribute4;
    }

    public void setAttribute5(String _Attribute5) {
        this._Attribute5 = _Attribute5;
    }

    public String getAttribute5() {
        return _Attribute5;
    }

    public void setAttribute6(String _Attribute6) {
        this._Attribute6 = _Attribute6;
    }

    public String getAttribute6() {
        return _Attribute6;
    }

    public void setAttribute7(String _Attribute7) {
        this._Attribute7 = _Attribute7;
    }

    public String getAttribute7() {
        return _Attribute7;
    }

    public void setAttribute8(String _Attribute8) {
        this._Attribute8 = _Attribute8;
    }

    public String getAttribute8() {
        return _Attribute8;
    }

    public void setAttribute9(String _Attribute9) {
        this._Attribute9 = _Attribute9;
    }

    public String getAttribute9() {
        return _Attribute9;
    }

    public void setAttribute10(String _Attribute10) {
        this._Attribute10 = _Attribute10;
    }

    public String getAttribute10() {
        return _Attribute10;
    }

    public void setAttribute11(String _Attribute11) {
        this._Attribute11 = _Attribute11;
    }

    public String getAttribute11() {
        return _Attribute11;
    }

    public void setAttribute12(String _Attribute12) {
        this._Attribute12 = _Attribute12;
    }

    public String getAttribute12() {
        return _Attribute12;
    }

    public void setAttribute13(String _Attribute13) {
        this._Attribute13 = _Attribute13;
    }

    public String getAttribute13() {
        return _Attribute13;
    }

    public void setAttribute14(String _Attribute14) {
        this._Attribute14 = _Attribute14;
    }

    public String getAttribute14() {
        return _Attribute14;
    }

    public void setAttribute15(String _Attribute15) {
        this._Attribute15 = _Attribute15;
    }

    public String getAttribute15() {
        return _Attribute15;
    }

    public void setRequestId(Long _RequestId) {
        this._RequestId = _RequestId;
    }

    public Long getRequestId() {
        return _RequestId;
    }

    public void setProgramApplicationId(Long _ProgramApplicationId) {
        this._ProgramApplicationId = _ProgramApplicationId;
    }

    public Long getProgramApplicationId() {
        return _ProgramApplicationId;
    }

    public void setProgramId(Long _ProgramId) {
        this._ProgramId = _ProgramId;
    }

    public Long getProgramId() {
        return _ProgramId;
    }

    public void setProgramUpdateDate(Timestamp _ProgramUpdateDate) {
        this._ProgramUpdateDate = _ProgramUpdateDate;
    }

    public Timestamp getProgramUpdateDate() {
        return _ProgramUpdateDate;
    }

    public void setValidationNumber(Long _ValidationNumber) {
        this._ValidationNumber = _ValidationNumber;
    }

    public Long getValidationNumber() {
        return _ValidationNumber;
    }

    public void setExcludeFreightFromDiscount(String _ExcludeFreightFromDiscount) {
        this._ExcludeFreightFromDiscount = _ExcludeFreightFromDiscount;
    }

    public String getExcludeFreightFromDiscount() {
        return _ExcludeFreightFromDiscount;
    }

    public void setVatRegistrationNum(String _VatRegistrationNum) {
        this._VatRegistrationNum = _VatRegistrationNum;
    }

    public String getVatRegistrationNum() {
        return _VatRegistrationNum;
    }

    public void setOffsetVatCode(String _OffsetVatCode) {
        this._OffsetVatCode = _OffsetVatCode;
    }

    public String getOffsetVatCode() {
        return _OffsetVatCode;
    }

    public void setOrgId(Long _OrgId) {
        this._OrgId = _OrgId;
    }

    public Long getOrgId() {
        return _OrgId;
    }

    public void setCheckDigits(String _CheckDigits) {
        this._CheckDigits = _CheckDigits;
    }

    public String getCheckDigits() {
        return _CheckDigits;
    }

    public void setBankNumber(String _BankNumber) {
        this._BankNumber = _BankNumber;
    }

    public String getBankNumber() {
        return _BankNumber;
    }

    public void setAddressLine4(String _AddressLine4) {
        this._AddressLine4 = _AddressLine4;
    }

    public String getAddressLine4() {
        return _AddressLine4;
    }

    public void setCounty(String _County) {
        this._County = _County;
    }

    public String getCounty() {
        return _County;
    }

    public void setAddressStyle(String _AddressStyle) {
        this._AddressStyle = _AddressStyle;
    }

    public String getAddressStyle() {
        return _AddressStyle;
    }

    public void setLanguage(String _Language) {
        this._Language = _Language;
    }

    public String getLanguage() {
        return _Language;
    }

    public void setAllowAwtFlag(String _AllowAwtFlag) {
        this._AllowAwtFlag = _AllowAwtFlag;
    }

    public String getAllowAwtFlag() {
        return _AllowAwtFlag;
    }

    public void setAwtGroupId(Long _AwtGroupId) {
        this._AwtGroupId = _AwtGroupId;
    }

    public Long getAwtGroupId() {
        return _AwtGroupId;
    }

    public void setGlobalAttribute1(String _GlobalAttribute1) {
        this._GlobalAttribute1 = _GlobalAttribute1;
    }

    public String getGlobalAttribute1() {
        return _GlobalAttribute1;
    }

    public void setGlobalAttribute2(String _GlobalAttribute2) {
        this._GlobalAttribute2 = _GlobalAttribute2;
    }

    public String getGlobalAttribute2() {
        return _GlobalAttribute2;
    }

    public void setGlobalAttribute3(String _GlobalAttribute3) {
        this._GlobalAttribute3 = _GlobalAttribute3;
    }

    public String getGlobalAttribute3() {
        return _GlobalAttribute3;
    }

    public void setGlobalAttribute4(String _GlobalAttribute4) {
        this._GlobalAttribute4 = _GlobalAttribute4;
    }

    public String getGlobalAttribute4() {
        return _GlobalAttribute4;
    }

    public void setGlobalAttribute5(String _GlobalAttribute5) {
        this._GlobalAttribute5 = _GlobalAttribute5;
    }

    public String getGlobalAttribute5() {
        return _GlobalAttribute5;
    }

    public void setGlobalAttribute6(String _GlobalAttribute6) {
        this._GlobalAttribute6 = _GlobalAttribute6;
    }

    public String getGlobalAttribute6() {
        return _GlobalAttribute6;
    }

    public void setGlobalAttribute7(String _GlobalAttribute7) {
        this._GlobalAttribute7 = _GlobalAttribute7;
    }

    public String getGlobalAttribute7() {
        return _GlobalAttribute7;
    }

    public void setGlobalAttribute8(String _GlobalAttribute8) {
        this._GlobalAttribute8 = _GlobalAttribute8;
    }

    public String getGlobalAttribute8() {
        return _GlobalAttribute8;
    }

    public void setGlobalAttribute9(String _GlobalAttribute9) {
        this._GlobalAttribute9 = _GlobalAttribute9;
    }

    public String getGlobalAttribute9() {
        return _GlobalAttribute9;
    }

    public void setGlobalAttribute10(String _GlobalAttribute10) {
        this._GlobalAttribute10 = _GlobalAttribute10;
    }

    public String getGlobalAttribute10() {
        return _GlobalAttribute10;
    }

    public void setGlobalAttribute11(String _GlobalAttribute11) {
        this._GlobalAttribute11 = _GlobalAttribute11;
    }

    public String getGlobalAttribute11() {
        return _GlobalAttribute11;
    }

    public void setGlobalAttribute12(String _GlobalAttribute12) {
        this._GlobalAttribute12 = _GlobalAttribute12;
    }

    public String getGlobalAttribute12() {
        return _GlobalAttribute12;
    }

    public void setGlobalAttribute13(String _GlobalAttribute13) {
        this._GlobalAttribute13 = _GlobalAttribute13;
    }

    public String getGlobalAttribute13() {
        return _GlobalAttribute13;
    }

    public void setGlobalAttribute14(String _GlobalAttribute14) {
        this._GlobalAttribute14 = _GlobalAttribute14;
    }

    public String getGlobalAttribute14() {
        return _GlobalAttribute14;
    }

    public void setGlobalAttribute15(String _GlobalAttribute15) {
        this._GlobalAttribute15 = _GlobalAttribute15;
    }

    public String getGlobalAttribute15() {
        return _GlobalAttribute15;
    }

    public void setGlobalAttribute16(String _GlobalAttribute16) {
        this._GlobalAttribute16 = _GlobalAttribute16;
    }

    public String getGlobalAttribute16() {
        return _GlobalAttribute16;
    }

    public void setGlobalAttribute17(String _GlobalAttribute17) {
        this._GlobalAttribute17 = _GlobalAttribute17;
    }

    public String getGlobalAttribute17() {
        return _GlobalAttribute17;
    }

    public void setGlobalAttribute18(String _GlobalAttribute18) {
        this._GlobalAttribute18 = _GlobalAttribute18;
    }

    public String getGlobalAttribute18() {
        return _GlobalAttribute18;
    }

    public void setGlobalAttribute19(String _GlobalAttribute19) {
        this._GlobalAttribute19 = _GlobalAttribute19;
    }

    public String getGlobalAttribute19() {
        return _GlobalAttribute19;
    }

    public void setGlobalAttribute20(String _GlobalAttribute20) {
        this._GlobalAttribute20 = _GlobalAttribute20;
    }

    public String getGlobalAttribute20() {
        return _GlobalAttribute20;
    }

    public void setGlobalAttributeCategory(String _GlobalAttributeCategory) {
        this._GlobalAttributeCategory = _GlobalAttributeCategory;
    }

    public String getGlobalAttributeCategory() {
        return _GlobalAttributeCategory;
    }

    public void setEdiTransactionHandling(String _EdiTransactionHandling) {
        this._EdiTransactionHandling = _EdiTransactionHandling;
    }

    public String getEdiTransactionHandling() {
        return _EdiTransactionHandling;
    }

    public void setEdiIdNumber(String _EdiIdNumber) {
        this._EdiIdNumber = _EdiIdNumber;
    }

    public String getEdiIdNumber() {
        return _EdiIdNumber;
    }

    public void setEdiPaymentMethod(String _EdiPaymentMethod) {
        this._EdiPaymentMethod = _EdiPaymentMethod;
    }

    public String getEdiPaymentMethod() {
        return _EdiPaymentMethod;
    }

    public void setEdiPaymentFormat(String _EdiPaymentFormat) {
        this._EdiPaymentFormat = _EdiPaymentFormat;
    }

    public String getEdiPaymentFormat() {
        return _EdiPaymentFormat;
    }

    public void setEdiRemittanceMethod(String _EdiRemittanceMethod) {
        this._EdiRemittanceMethod = _EdiRemittanceMethod;
    }

    public String getEdiRemittanceMethod() {
        return _EdiRemittanceMethod;
    }

    public void setBankChargeBearer(String _BankChargeBearer) {
        this._BankChargeBearer = _BankChargeBearer;
    }

    public String getBankChargeBearer() {
        return _BankChargeBearer;
    }

    public void setEdiRemittanceInstruction(String _EdiRemittanceInstruction) {
        this._EdiRemittanceInstruction = _EdiRemittanceInstruction;
    }

    public String getEdiRemittanceInstruction() {
        return _EdiRemittanceInstruction;
    }

    public void setBankBranchType(String _BankBranchType) {
        this._BankBranchType = _BankBranchType;
    }

    public String getBankBranchType() {
        return _BankBranchType;
    }

    public void setPayOnCode(String _PayOnCode) {
        this._PayOnCode = _PayOnCode;
    }

    public String getPayOnCode() {
        return _PayOnCode;
    }

    public void setDefaultPaySiteId(Long _DefaultPaySiteId) {
        this._DefaultPaySiteId = _DefaultPaySiteId;
    }

    public Long getDefaultPaySiteId() {
        return _DefaultPaySiteId;
    }

    public void setPayOnReceiptSummaryCode(String _PayOnReceiptSummaryCode) {
        this._PayOnReceiptSummaryCode = _PayOnReceiptSummaryCode;
    }

    public String getPayOnReceiptSummaryCode() {
        return _PayOnReceiptSummaryCode;
    }

    public void setTpHeaderId(Long _TpHeaderId) {
        this._TpHeaderId = _TpHeaderId;
    }

    public Long getTpHeaderId() {
        return _TpHeaderId;
    }

    public void setEceTpLocationCode(String _EceTpLocationCode) {
        this._EceTpLocationCode = _EceTpLocationCode;
    }

    public String getEceTpLocationCode() {
        return _EceTpLocationCode;
    }

    public void setPcardSiteFlag(String _PcardSiteFlag) {
        this._PcardSiteFlag = _PcardSiteFlag;
    }

    public String getPcardSiteFlag() {
        return _PcardSiteFlag;
    }

    public void setMatchOption(String _MatchOption) {
        this._MatchOption = _MatchOption;
    }

    public String getMatchOption() {
        return _MatchOption;
    }

    public void setCountryOfOriginCode(String _CountryOfOriginCode) {
        this._CountryOfOriginCode = _CountryOfOriginCode;
    }

    public String getCountryOfOriginCode() {
        return _CountryOfOriginCode;
    }

    public void setFutureDatedPaymentCcid(Long _FutureDatedPaymentCcid) {
        this._FutureDatedPaymentCcid = _FutureDatedPaymentCcid;
    }

    public Long getFutureDatedPaymentCcid() {
        return _FutureDatedPaymentCcid;
    }

    public void setCreateDebitMemoFlag(String _CreateDebitMemoFlag) {
        this._CreateDebitMemoFlag = _CreateDebitMemoFlag;
    }

    public String getCreateDebitMemoFlag() {
        return _CreateDebitMemoFlag;
    }

    public void setOffsetTaxFlag(String _OffsetTaxFlag) {
        this._OffsetTaxFlag = _OffsetTaxFlag;
    }

    public String getOffsetTaxFlag() {
        return _OffsetTaxFlag;
    }

    public void setSupplierNotifMethod(String _SupplierNotifMethod) {
        this._SupplierNotifMethod = _SupplierNotifMethod;
    }

    public String getSupplierNotifMethod() {
        return _SupplierNotifMethod;
    }

    public void setEmailAddress(String _EmailAddress) {
        this._EmailAddress = _EmailAddress;
    }

    public String getEmailAddress() {
        return _EmailAddress;
    }

    public void setRemittanceEmail(String _RemittanceEmail) {
        this._RemittanceEmail = _RemittanceEmail;
    }

    public String getRemittanceEmail() {
        return _RemittanceEmail;
    }

    public void setPrimaryPaySiteFlag(String _PrimaryPaySiteFlag) {
        this._PrimaryPaySiteFlag = _PrimaryPaySiteFlag;
    }

    public String getPrimaryPaySiteFlag() {
        return _PrimaryPaySiteFlag;
    }

    public void setShippingControl(String _ShippingControl) {
        this._ShippingControl = _ShippingControl;
    }

    public String getShippingControl() {
        return _ShippingControl;
    }

    public void setSellingCompanyIdentifier(String _SellingCompanyIdentifier) {
        this._SellingCompanyIdentifier = _SellingCompanyIdentifier;
    }

    public String getSellingCompanyIdentifier() {
        return _SellingCompanyIdentifier;
    }

    public void setGaplessInvNumFlag(String _GaplessInvNumFlag) {
        this._GaplessInvNumFlag = _GaplessInvNumFlag;
    }

    public String getGaplessInvNumFlag() {
        return _GaplessInvNumFlag;
    }

    public void setDunsNumber(String _DunsNumber) {
        this._DunsNumber = _DunsNumber;
    }

    public String getDunsNumber() {
        return _DunsNumber;
    }

    public void setToleranceId(Long _ToleranceId) {
        this._ToleranceId = _ToleranceId;
    }

    public Long getToleranceId() {
        return _ToleranceId;
    }

    public void setLocationId(Long _LocationId) {
        this._LocationId = _LocationId;
    }

    public Long getLocationId() {
        return _LocationId;
    }

    public void setPartySiteId(Long _PartySiteId) {
        this._PartySiteId = _PartySiteId;
    }

    public Long getPartySiteId() {
        return _PartySiteId;
    }

    public void setServicesToleranceId(Long _ServicesToleranceId) {
        this._ServicesToleranceId = _ServicesToleranceId;
    }

    public Long getServicesToleranceId() {
        return _ServicesToleranceId;
    }

    public void setRetainageRate(Long _RetainageRate) {
        this._RetainageRate = _RetainageRate;
    }

    public Long getRetainageRate() {
        return _RetainageRate;
    }

    public void setTcaSyncState(String _TcaSyncState) {
        this._TcaSyncState = _TcaSyncState;
    }

    public String getTcaSyncState() {
        return _TcaSyncState;
    }

    public void setTcaSyncProvince(String _TcaSyncProvince) {
        this._TcaSyncProvince = _TcaSyncProvince;
    }

    public String getTcaSyncProvince() {
        return _TcaSyncProvince;
    }

    public void setTcaSyncCounty(String _TcaSyncCounty) {
        this._TcaSyncCounty = _TcaSyncCounty;
    }

    public String getTcaSyncCounty() {
        return _TcaSyncCounty;
    }

    public void setTcaSyncCity(String _TcaSyncCity) {
        this._TcaSyncCity = _TcaSyncCity;
    }

    public String getTcaSyncCity() {
        return _TcaSyncCity;
    }

    public void setTcaSyncZip(String _TcaSyncZip) {
        this._TcaSyncZip = _TcaSyncZip;
    }

    public String getTcaSyncZip() {
        return _TcaSyncZip;
    }

    public void setTcaSyncCountry(String _TcaSyncCountry) {
        this._TcaSyncCountry = _TcaSyncCountry;
    }

    public String getTcaSyncCountry() {
        return _TcaSyncCountry;
    }

    public void setPayAwtGroupId(Long _PayAwtGroupId) {
        this._PayAwtGroupId = _PayAwtGroupId;
    }

    public Long getPayAwtGroupId() {
        return _PayAwtGroupId;
    }

    public void setCageCode(String _CageCode) {
        this._CageCode = _CageCode;
    }

    public String getCageCode() {
        return _CageCode;
    }

    public void setLegalBusinessName(String _LegalBusinessName) {
        this._LegalBusinessName = _LegalBusinessName;
    }

    public String getLegalBusinessName() {
        return _LegalBusinessName;
    }

    public void setDoingBusAsName(String _DoingBusAsName) {
        this._DoingBusAsName = _DoingBusAsName;
    }

    public String getDoingBusAsName() {
        return _DoingBusAsName;
    }

    public void setDivisionName(String _DivisionName) {
        this._DivisionName = _DivisionName;
    }

    public String getDivisionName() {
        return _DivisionName;
    }

    public void setSmallBusinessCode(String _SmallBusinessCode) {
        this._SmallBusinessCode = _SmallBusinessCode;
    }

    public String getSmallBusinessCode() {
        return _SmallBusinessCode;
    }

    public void setCcrComments(String _CcrComments) {
        this._CcrComments = _CcrComments;
    }

    public String getCcrComments() {
        return _CcrComments;
    }

    public void setDebarmentStartDate(Timestamp _DebarmentStartDate) {
        this._DebarmentStartDate = _DebarmentStartDate;
    }

    public Timestamp getDebarmentStartDate() {
        return _DebarmentStartDate;
    }

    public void setDebarmentEndDate(Timestamp _DebarmentEndDate) {
        this._DebarmentEndDate = _DebarmentEndDate;
    }

    public Timestamp getDebarmentEndDate() {
        return _DebarmentEndDate;
    }

    public void setSitePan(String _SitePan) {
        this._SitePan = _SitePan;
    }

    public String getSitePan() {
        return _SitePan;
    }
}
