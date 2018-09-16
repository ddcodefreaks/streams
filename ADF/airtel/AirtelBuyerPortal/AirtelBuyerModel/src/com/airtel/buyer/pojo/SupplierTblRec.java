package com.airtel.buyer.pojo;

import java.io.Serializable;

import java.sql.Timestamp;


public class SupplierTblRec implements Serializable {
    @SuppressWarnings("compatibility:-305271695052565981")
    private static final long serialVersionUID = 1L;

    public SupplierTblRec() {
        super();
    }

    private Long    _PartnerVendorId;
    private Long    _VendorId;
    private Timestamp       _LastUpdateDate;
    private Long    _LastUpdatedBy;
    private String  _VendorName;
    private String  _VendorNameAlt;
    private String  _Segment1;
    private String  _SummaryFlag;
    private String  _EnabledFlag;
    private String  _Segment2;
    private String  _Segment3;
    private String  _Segment4;
    private String  _Segment5;
    private Long    _LastUpdateLogin;
    private Timestamp       _CreationDate;
    private Long    _CreatedBy;
    private Long    _EmployeeId;
    private String  _VendorTypeLookupCode;
    private String  _CustomerNum;
    private String  _OneTimeFlag;
    private Long    _ParentVendorId;
    private Long    _MinOrderAmount;
    private Long    _ShipToLocationId;
    private Long    _BillToLocationId;
    private String  _ShipViaLookupCode;
    private String  _FreightTermsLookupCode;
    private String  _FobLookupCode;
    private Long    _TermsId;
    private Long    _SetOfBooksId;
    private String  _CreditStatusLookupCode;
    private Long    _CreditLimit;
    private String  _AlwaysTakeDiscFlag;
    private String  _PayDateBasisLookupCode;
    private String  _PayGroupLookupCode;
    private Long    _PaymentPriority;
    private String  _InvoiceCurrencyCode;
    private String  _PaymentCurrencyCode;
    private Long    _InvoiceAmountLimit;
    private String  _ExchangeDateLookupCode;
    private String  _HoldAllPaymentsFlag;
    private String  _HoldFuturePaymentsFlag;
    private String  _HoldReason;
    private Long    _DistributionSetId;
    private Long    _AcctsPayCodeCombinationId;
    private Long    _DiscLostCodeCombinationId;
    private Long    _DiscTakenCodeCombinationId;
    private Long    _ExpenseCodeCombinationId;
    private Long    _PrepayCodeCombinationId;
    private String  _Num1099;
    private String  _Type1099;
    private String  _WithholdingStatusLookupCode;
    private Timestamp       _WithholdingStartDate;
    private String  _OrganizationTypeLookupCode;
    private String  _VatCode;
    private Timestamp       _StartDateActive;
    private Timestamp       _EndDateActive;
    private String  _MinorityGroupLookupCode;
    private String  _PaymentMethodLookupCode;
    private String  _BankAccountName;
    private String  _BankAccountNum;
    private String  _BankNum;
    private String  _BankAccountType;
    private String  _WomenOwnedFlag;
    private String  _SmallBusinessFlag;
    private String  _StandardIndustryClass;
    private String  _HoldFlag;
    private String  _PurchasingHoldReason;
    private Integer _HoldBy;
    private Timestamp       _HoldDate;
    private String  _TermsDateBasis;
    private Long    _PriceTolerance;
    private String  _InspectionRequiredFlag;
    private String  _ReceiptRequiredFlag;
    private Long    _QtyRcvTolerance;
    private String  _QtyRcvExceptionCode;
    private String  _EnforceShipToLocationCode;
    private Long    _DaysEarlyReceiptAllowed;
    private Long    _DaysLateReceiptAllowed;
    private String  _ReceiptDaysExceptionCode;
    private Long    _ReceivingRoutingId;
    private String  _AllowSubstituteReceiptsFlag;
    private String  _AllowUnorderedReceiptsFlag;
    private String  _HoldUnmatchedInvoicesFlag;
    private String  _ExclusivePaymentFlag;
    private String  _ApTaxRoundingRule;
    private String  _AutoTaxCalcFlag;
    private String  _AutoTaxCalcOverride;
    private String  _AmountIncludesTaxFlag;
    private Timestamp       _TaxVerificationDate;
    private String  _NameControl;
    private String  _StateReportableFlag;
    private String  _FederalReportableFlag;
    private String  _AttributeCategory;
    private String  _Attribute1;
    private String  _Attribute2;
    private String  _Attribute3;
    private String  _Attribute4;
    private String  _Attribute5;
    private String  _Attribute6;
    private String  _Attribute7;
    private String  _Attribute8;
    private String  _Attribute9;
    private String  _Attribute10;
    private String  _Attribute11;
    private String  _Attribute12;
    private String  _Attribute13;
    private String  _Attribute14;
    private String  _Attribute15;
    private Long    _RequestId;
    private Long    _ProgramApplicationId;
    private Long    _ProgramId;
    private Timestamp       _ProgramUpdateDate;
    private String  _OffsetVatCode;
    private String  _VatRegistrationNum;
    private String  _AutoCalculateInterestFlag;
    private Long    _ValidationNumber;
    private String  _ExcludeFreightFromDiscount;
    private String  _TaxReportingName;
    private String  _CheckDigits;
    private String  _BankNumber;
    private String  _AllowAwtFlag;
    private Long    _AwtGroupId;
    private String  _GlobalAttribute1;
    private String  _GlobalAttribute2;
    private String  _GlobalAttribute3;
    private String  _GlobalAttribute4;
    private String  _GlobalAttribute5;
    private String  _GlobalAttribute6;
    private String  _GlobalAttribute7;
    private String  _GlobalAttribute8;
    private String  _GlobalAttribute9;
    private String  _GlobalAttribute10;
    private String  _GlobalAttribute11;
    private String  _GlobalAttribute12;
    private String  _GlobalAttribute13;
    private String  _GlobalAttribute14;
    private String  _GlobalAttribute15;
    private String  _GlobalAttribute16;
    private String  _GlobalAttribute17;
    private String  _GlobalAttribute18;
    private String  _GlobalAttribute19;
    private String  _GlobalAttribute20;
    private String  _GlobalAttributeCategory;
    private String  _EdiTransactionHandling;
    private String  _EdiPaymentMethod;
    private String  _EdiPaymentFormat;
    private String  _EdiRemittanceMethod;
    private String  _EdiRemittanceInstruction;
    private String  _BankChargeBearer;
    private String  _BankBranchType;
    private String  _MatchOption;
    private Long    _FutureDatedPaymentCcid;
    private String  _CreateDebitMemoFlag;
    private String  _OffsetTaxFlag;
    private Long    _PartyId;
    private Long    _ParentPartyId;
    private String  _NiNumber;
    private String  _TcaSyncNum1099;
    private String  _TcaSyncVendorName;
    private String  _TcaSyncVatRegNum;
    private Long    _UniqueTaxReferenceNum;
    private Long    _PartnershipUtr;
    private String  _PartnershipName;
    private String  _CisEnabledFlag;
    private String  _FirstName;
    private String  _SecondName;
    private String  _LastName;
    private String  _Salutation;
    private String  _TradingName;
    private String  _WorkReference;
    private String  _CompanyRegistrationNumber;
    private String  _NationalInsuranceNumber;
    private String  _VerificationNumber;
    private Long    _VerificationRequestId;
    private String  _MatchStatusFlag;
    private Timestamp       _CisVerificationDate;
    private String  _Individual1099;
    private Long    _PayAwtGroupId;
    private Long    _CisParentVendorId;
    private Timestamp       _BusClassLastCertifiedDate;
    private Long    _BusClassLastCertifiedBy;
    private String  _VendorType;
    private String  _CompanyUrl;
    private String  _OrganizationType;
    private String  _Status;
    private String  _Message;

    public SupplierTblRec(Long _PartnerVendorId, String _VendorName, String _VendorType,String _VendorTypeLookupCode, String _CompanyUrl,
                          String _OrganizationType) {
        this._PartnerVendorId = _PartnerVendorId;
        this._VendorName = _VendorName;
        this._VendorType = _VendorType;
        this._VendorTypeLookupCode=_VendorType;
        this._CompanyUrl = _CompanyUrl;
        this._OrganizationType = _OrganizationType;
    }

    public void setPartnerVendorId(Long _PartnerVendorId) {
        this._PartnerVendorId = _PartnerVendorId;
    }

    public Long getPartnerVendorId() {
        return _PartnerVendorId;
    }

    public void setVendorId(Long _VendorId) {
        this._VendorId = _VendorId;
    }

    public Long getVendorId() {
        return _VendorId;
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

    public void setVendorName(String _VendorName) {
        this._VendorName = _VendorName;
    }

    public String getVendorName() {
        return _VendorName;
    }

    public void setVendorNameAlt(String _VendorNameAlt) {
        this._VendorNameAlt = _VendorNameAlt;
    }

    public String getVendorNameAlt() {
        return _VendorNameAlt;
    }

    public void setSegment1(String _Segment1) {
        this._Segment1 = _Segment1;
    }

    public String getSegment1() {
        return _Segment1;
    }

    public void setSummaryFlag(String _SummaryFlag) {
        this._SummaryFlag = _SummaryFlag;
    }

    public String getSummaryFlag() {
        return _SummaryFlag;
    }

    public void setEnabledFlag(String _EnabledFlag) {
        this._EnabledFlag = _EnabledFlag;
    }

    public String getEnabledFlag() {
        return _EnabledFlag;
    }

    public void setSegment2(String _Segment2) {
        this._Segment2 = _Segment2;
    }

    public String getSegment2() {
        return _Segment2;
    }

    public void setSegment3(String _Segment3) {
        this._Segment3 = _Segment3;
    }

    public String getSegment3() {
        return _Segment3;
    }

    public void setSegment4(String _Segment4) {
        this._Segment4 = _Segment4;
    }

    public String getSegment4() {
        return _Segment4;
    }

    public void setSegment5(String _Segment5) {
        this._Segment5 = _Segment5;
    }

    public String getSegment5() {
        return _Segment5;
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

    public void setEmployeeId(Long _EmployeeId) {
        this._EmployeeId = _EmployeeId;
    }

    public Long getEmployeeId() {
        return _EmployeeId;
    }

    public void setVendorTypeLookupCode(String _VendorTypeLookupCode) {
        this._VendorTypeLookupCode = _VendorTypeLookupCode;
    }

    public String getVendorTypeLookupCode() {
        return _VendorTypeLookupCode;
    }

    public void setCustomerNum(String _CustomerNum) {
        this._CustomerNum = _CustomerNum;
    }

    public String getCustomerNum() {
        return _CustomerNum;
    }

    public void setOneTimeFlag(String _OneTimeFlag) {
        this._OneTimeFlag = _OneTimeFlag;
    }

    public String getOneTimeFlag() {
        return _OneTimeFlag;
    }

    public void setParentVendorId(Long _ParentVendorId) {
        this._ParentVendorId = _ParentVendorId;
    }

    public Long getParentVendorId() {
        return _ParentVendorId;
    }

    public void setMinOrderAmount(Long _MinOrderAmount) {
        this._MinOrderAmount = _MinOrderAmount;
    }

    public Long getMinOrderAmount() {
        return _MinOrderAmount;
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

    public void setTermsId(Long _TermsId) {
        this._TermsId = _TermsId;
    }

    public Long getTermsId() {
        return _TermsId;
    }

    public void setSetOfBooksId(Long _SetOfBooksId) {
        this._SetOfBooksId = _SetOfBooksId;
    }

    public Long getSetOfBooksId() {
        return _SetOfBooksId;
    }

    public void setCreditStatusLookupCode(String _CreditStatusLookupCode) {
        this._CreditStatusLookupCode = _CreditStatusLookupCode;
    }

    public String getCreditStatusLookupCode() {
        return _CreditStatusLookupCode;
    }

    public void setCreditLimit(Long _CreditLimit) {
        this._CreditLimit = _CreditLimit;
    }

    public Long getCreditLimit() {
        return _CreditLimit;
    }

    public void setAlwaysTakeDiscFlag(String _AlwaysTakeDiscFlag) {
        this._AlwaysTakeDiscFlag = _AlwaysTakeDiscFlag;
    }

    public String getAlwaysTakeDiscFlag() {
        return _AlwaysTakeDiscFlag;
    }

    public void setPayDateBasisLookupCode(String _PayDateBasisLookupCode) {
        this._PayDateBasisLookupCode = _PayDateBasisLookupCode;
    }

    public String getPayDateBasisLookupCode() {
        return _PayDateBasisLookupCode;
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

    public void setInvoiceAmountLimit(Long _InvoiceAmountLimit) {
        this._InvoiceAmountLimit = _InvoiceAmountLimit;
    }

    public Long getInvoiceAmountLimit() {
        return _InvoiceAmountLimit;
    }

    public void setExchangeDateLookupCode(String _ExchangeDateLookupCode) {
        this._ExchangeDateLookupCode = _ExchangeDateLookupCode;
    }

    public String getExchangeDateLookupCode() {
        return _ExchangeDateLookupCode;
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

    public void setDiscLostCodeCombinationId(Long _DiscLostCodeCombinationId) {
        this._DiscLostCodeCombinationId = _DiscLostCodeCombinationId;
    }

    public Long getDiscLostCodeCombinationId() {
        return _DiscLostCodeCombinationId;
    }

    public void setDiscTakenCodeCombinationId(Long _DiscTakenCodeCombinationId) {
        this._DiscTakenCodeCombinationId = _DiscTakenCodeCombinationId;
    }

    public Long getDiscTakenCodeCombinationId() {
        return _DiscTakenCodeCombinationId;
    }

    public void setExpenseCodeCombinationId(Long _ExpenseCodeCombinationId) {
        this._ExpenseCodeCombinationId = _ExpenseCodeCombinationId;
    }

    public Long getExpenseCodeCombinationId() {
        return _ExpenseCodeCombinationId;
    }

    public void setPrepayCodeCombinationId(Long _PrepayCodeCombinationId) {
        this._PrepayCodeCombinationId = _PrepayCodeCombinationId;
    }

    public Long getPrepayCodeCombinationId() {
        return _PrepayCodeCombinationId;
    }

    public void setNum1099(String _Num1099) {
        this._Num1099 = _Num1099;
    }

    public String getNum1099() {
        return _Num1099;
    }

    public void setType1099(String _Type1099) {
        this._Type1099 = _Type1099;
    }

    public String getType1099() {
        return _Type1099;
    }

    public void setWithholdingStatusLookupCode(String _WithholdingStatusLookupCode) {
        this._WithholdingStatusLookupCode = _WithholdingStatusLookupCode;
    }

    public String getWithholdingStatusLookupCode() {
        return _WithholdingStatusLookupCode;
    }

    public void setWithholdingStartDate(Timestamp _WithholdingStartDate) {
        this._WithholdingStartDate = _WithholdingStartDate;
    }

    public Timestamp getWithholdingStartDate() {
        return _WithholdingStartDate;
    }

    public void setOrganizationTypeLookupCode(String _OrganizationTypeLookupCode) {
        this._OrganizationTypeLookupCode = _OrganizationTypeLookupCode;
    }

    public String getOrganizationTypeLookupCode() {
        return _OrganizationTypeLookupCode;
    }

    public void setVatCode(String _VatCode) {
        this._VatCode = _VatCode;
    }

    public String getVatCode() {
        return _VatCode;
    }

    public void setStartDateActive(Timestamp _StartDateActive) {
        this._StartDateActive = _StartDateActive;
    }

    public Timestamp getStartDateActive() {
        return _StartDateActive;
    }

    public void setEndDateActive(Timestamp _EndDateActive) {
        this._EndDateActive = _EndDateActive;
    }

    public Timestamp getEndDateActive() {
        return _EndDateActive;
    }

    public void setMinorityGroupLookupCode(String _MinorityGroupLookupCode) {
        this._MinorityGroupLookupCode = _MinorityGroupLookupCode;
    }

    public String getMinorityGroupLookupCode() {
        return _MinorityGroupLookupCode;
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

    public void setWomenOwnedFlag(String _WomenOwnedFlag) {
        this._WomenOwnedFlag = _WomenOwnedFlag;
    }

    public String getWomenOwnedFlag() {
        return _WomenOwnedFlag;
    }

    public void setSmallBusinessFlag(String _SmallBusinessFlag) {
        this._SmallBusinessFlag = _SmallBusinessFlag;
    }

    public String getSmallBusinessFlag() {
        return _SmallBusinessFlag;
    }

    public void setStandardIndustryClass(String _StandardIndustryClass) {
        this._StandardIndustryClass = _StandardIndustryClass;
    }

    public String getStandardIndustryClass() {
        return _StandardIndustryClass;
    }

    public void setHoldFlag(String _HoldFlag) {
        this._HoldFlag = _HoldFlag;
    }

    public String getHoldFlag() {
        return _HoldFlag;
    }

    public void setPurchasingHoldReason(String _PurchasingHoldReason) {
        this._PurchasingHoldReason = _PurchasingHoldReason;
    }

    public String getPurchasingHoldReason() {
        return _PurchasingHoldReason;
    }

    public void setHoldBy(Integer _HoldBy) {
        this._HoldBy = _HoldBy;
    }

    public Integer getHoldBy() {
        return _HoldBy;
    }

    public void setHoldDate(Timestamp _HoldDate) {
        this._HoldDate = _HoldDate;
    }

    public Timestamp getHoldDate() {
        return _HoldDate;
    }

    public void setTermsDateBasis(String _TermsDateBasis) {
        this._TermsDateBasis = _TermsDateBasis;
    }

    public String getTermsDateBasis() {
        return _TermsDateBasis;
    }

    public void setPriceTolerance(Long _PriceTolerance) {
        this._PriceTolerance = _PriceTolerance;
    }

    public Long getPriceTolerance() {
        return _PriceTolerance;
    }

    public void setInspectionRequiredFlag(String _InspectionRequiredFlag) {
        this._InspectionRequiredFlag = _InspectionRequiredFlag;
    }

    public String getInspectionRequiredFlag() {
        return _InspectionRequiredFlag;
    }

    public void setReceiptRequiredFlag(String _ReceiptRequiredFlag) {
        this._ReceiptRequiredFlag = _ReceiptRequiredFlag;
    }

    public String getReceiptRequiredFlag() {
        return _ReceiptRequiredFlag;
    }

    public void setQtyRcvTolerance(Long _QtyRcvTolerance) {
        this._QtyRcvTolerance = _QtyRcvTolerance;
    }

    public Long getQtyRcvTolerance() {
        return _QtyRcvTolerance;
    }

    public void setQtyRcvExceptionCode(String _QtyRcvExceptionCode) {
        this._QtyRcvExceptionCode = _QtyRcvExceptionCode;
    }

    public String getQtyRcvExceptionCode() {
        return _QtyRcvExceptionCode;
    }

    public void setEnforceShipToLocationCode(String _EnforceShipToLocationCode) {
        this._EnforceShipToLocationCode = _EnforceShipToLocationCode;
    }

    public String getEnforceShipToLocationCode() {
        return _EnforceShipToLocationCode;
    }

    public void setDaysEarlyReceiptAllowed(Long _DaysEarlyReceiptAllowed) {
        this._DaysEarlyReceiptAllowed = _DaysEarlyReceiptAllowed;
    }

    public Long getDaysEarlyReceiptAllowed() {
        return _DaysEarlyReceiptAllowed;
    }

    public void setDaysLateReceiptAllowed(Long _DaysLateReceiptAllowed) {
        this._DaysLateReceiptAllowed = _DaysLateReceiptAllowed;
    }

    public Long getDaysLateReceiptAllowed() {
        return _DaysLateReceiptAllowed;
    }

    public void setReceiptDaysExceptionCode(String _ReceiptDaysExceptionCode) {
        this._ReceiptDaysExceptionCode = _ReceiptDaysExceptionCode;
    }

    public String getReceiptDaysExceptionCode() {
        return _ReceiptDaysExceptionCode;
    }

    public void setReceivingRoutingId(Long _ReceivingRoutingId) {
        this._ReceivingRoutingId = _ReceivingRoutingId;
    }

    public Long getReceivingRoutingId() {
        return _ReceivingRoutingId;
    }

    public void setAllowSubstituteReceiptsFlag(String _AllowSubstituteReceiptsFlag) {
        this._AllowSubstituteReceiptsFlag = _AllowSubstituteReceiptsFlag;
    }

    public String getAllowSubstituteReceiptsFlag() {
        return _AllowSubstituteReceiptsFlag;
    }

    public void setAllowUnorderedReceiptsFlag(String _AllowUnorderedReceiptsFlag) {
        this._AllowUnorderedReceiptsFlag = _AllowUnorderedReceiptsFlag;
    }

    public String getAllowUnorderedReceiptsFlag() {
        return _AllowUnorderedReceiptsFlag;
    }

    public void setHoldUnmatchedInvoicesFlag(String _HoldUnmatchedInvoicesFlag) {
        this._HoldUnmatchedInvoicesFlag = _HoldUnmatchedInvoicesFlag;
    }

    public String getHoldUnmatchedInvoicesFlag() {
        return _HoldUnmatchedInvoicesFlag;
    }

    public void setExclusivePaymentFlag(String _ExclusivePaymentFlag) {
        this._ExclusivePaymentFlag = _ExclusivePaymentFlag;
    }

    public String getExclusivePaymentFlag() {
        return _ExclusivePaymentFlag;
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

    public void setTaxVerificationDate(Timestamp _TaxVerificationDate) {
        this._TaxVerificationDate = _TaxVerificationDate;
    }

    public Timestamp getTaxVerificationDate() {
        return _TaxVerificationDate;
    }

    public void setNameControl(String _NameControl) {
        this._NameControl = _NameControl;
    }

    public String getNameControl() {
        return _NameControl;
    }

    public void setStateReportableFlag(String _StateReportableFlag) {
        this._StateReportableFlag = _StateReportableFlag;
    }

    public String getStateReportableFlag() {
        return _StateReportableFlag;
    }

    public void setFederalReportableFlag(String _FederalReportableFlag) {
        this._FederalReportableFlag = _FederalReportableFlag;
    }

    public String getFederalReportableFlag() {
        return _FederalReportableFlag;
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

    public void setOffsetVatCode(String _OffsetVatCode) {
        this._OffsetVatCode = _OffsetVatCode;
    }

    public String getOffsetVatCode() {
        return _OffsetVatCode;
    }

    public void setVatRegistrationNum(String _VatRegistrationNum) {
        this._VatRegistrationNum = _VatRegistrationNum;
    }

    public String getVatRegistrationNum() {
        return _VatRegistrationNum;
    }

    public void setAutoCalculateInterestFlag(String _AutoCalculateInterestFlag) {
        this._AutoCalculateInterestFlag = _AutoCalculateInterestFlag;
    }

    public String getAutoCalculateInterestFlag() {
        return _AutoCalculateInterestFlag;
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

    public void setTaxReportingName(String _TaxReportingName) {
        this._TaxReportingName = _TaxReportingName;
    }

    public String getTaxReportingName() {
        return _TaxReportingName;
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

    public void setEdiRemittanceInstruction(String _EdiRemittanceInstruction) {
        this._EdiRemittanceInstruction = _EdiRemittanceInstruction;
    }

    public String getEdiRemittanceInstruction() {
        return _EdiRemittanceInstruction;
    }

    public void setBankChargeBearer(String _BankChargeBearer) {
        this._BankChargeBearer = _BankChargeBearer;
    }

    public String getBankChargeBearer() {
        return _BankChargeBearer;
    }

    public void setBankBranchType(String _BankBranchType) {
        this._BankBranchType = _BankBranchType;
    }

    public String getBankBranchType() {
        return _BankBranchType;
    }

    public void setMatchOption(String _MatchOption) {
        this._MatchOption = _MatchOption;
    }

    public String getMatchOption() {
        return _MatchOption;
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

    public void setPartyId(Long _PartyId) {
        this._PartyId = _PartyId;
    }

    public Long getPartyId() {
        return _PartyId;
    }

    public void setParentPartyId(Long _ParentPartyId) {
        this._ParentPartyId = _ParentPartyId;
    }

    public Long getParentPartyId() {
        return _ParentPartyId;
    }

    public void setNiNumber(String _NiNumber) {
        this._NiNumber = _NiNumber;
    }

    public String getNiNumber() {
        return _NiNumber;
    }

    public void setTcaSyncNum1099(String _TcaSyncNum1099) {
        this._TcaSyncNum1099 = _TcaSyncNum1099;
    }

    public String getTcaSyncNum1099() {
        return _TcaSyncNum1099;
    }

    public void setTcaSyncVendorName(String _TcaSyncVendorName) {
        this._TcaSyncVendorName = _TcaSyncVendorName;
    }

    public String getTcaSyncVendorName() {
        return _TcaSyncVendorName;
    }

    public void setTcaSyncVatRegNum(String _TcaSyncVatRegNum) {
        this._TcaSyncVatRegNum = _TcaSyncVatRegNum;
    }

    public String getTcaSyncVatRegNum() {
        return _TcaSyncVatRegNum;
    }

    public void setUniqueTaxReferenceNum(Long _UniqueTaxReferenceNum) {
        this._UniqueTaxReferenceNum = _UniqueTaxReferenceNum;
    }

    public Long getUniqueTaxReferenceNum() {
        return _UniqueTaxReferenceNum;
    }

    public void setPartnershipUtr(Long _PartnershipUtr) {
        this._PartnershipUtr = _PartnershipUtr;
    }

    public Long getPartnershipUtr() {
        return _PartnershipUtr;
    }

    public void setPartnershipName(String _PartnershipName) {
        this._PartnershipName = _PartnershipName;
    }

    public String getPartnershipName() {
        return _PartnershipName;
    }

    public void setCisEnabledFlag(String _CisEnabledFlag) {
        this._CisEnabledFlag = _CisEnabledFlag;
    }

    public String getCisEnabledFlag() {
        return _CisEnabledFlag;
    }

    public void setFirstName(String _FirstName) {
        this._FirstName = _FirstName;
    }

    public String getFirstName() {
        return _FirstName;
    }

    public void setSecondName(String _SecondName) {
        this._SecondName = _SecondName;
    }

    public String getSecondName() {
        return _SecondName;
    }

    public void setLastName(String _LastName) {
        this._LastName = _LastName;
    }

    public String getLastName() {
        return _LastName;
    }

    public void setSalutation(String _Salutation) {
        this._Salutation = _Salutation;
    }

    public String getSalutation() {
        return _Salutation;
    }

    public void setTradingName(String _TradingName) {
        this._TradingName = _TradingName;
    }

    public String getTradingName() {
        return _TradingName;
    }

    public void setWorkReference(String _WorkReference) {
        this._WorkReference = _WorkReference;
    }

    public String getWorkReference() {
        return _WorkReference;
    }

    public void setCompanyRegistrationNumber(String _CompanyRegistrationNumber) {
        this._CompanyRegistrationNumber = _CompanyRegistrationNumber;
    }

    public String getCompanyRegistrationNumber() {
        return _CompanyRegistrationNumber;
    }

    public void setNationalInsuranceNumber(String _NationalInsuranceNumber) {
        this._NationalInsuranceNumber = _NationalInsuranceNumber;
    }

    public String getNationalInsuranceNumber() {
        return _NationalInsuranceNumber;
    }

    public void setVerificationNumber(String _VerificationNumber) {
        this._VerificationNumber = _VerificationNumber;
    }

    public String getVerificationNumber() {
        return _VerificationNumber;
    }

    public void setVerificationRequestId(Long _VerificationRequestId) {
        this._VerificationRequestId = _VerificationRequestId;
    }

    public Long getVerificationRequestId() {
        return _VerificationRequestId;
    }

    public void setMatchStatusFlag(String _MatchStatusFlag) {
        this._MatchStatusFlag = _MatchStatusFlag;
    }

    public String getMatchStatusFlag() {
        return _MatchStatusFlag;
    }

    public void setCisVerificationDate(Timestamp _CisVerificationDate) {
        this._CisVerificationDate = _CisVerificationDate;
    }

    public Timestamp getCisVerificationDate() {
        return _CisVerificationDate;
    }

    public void setIndividual1099(String _Individual1099) {
        this._Individual1099 = _Individual1099;
    }

    public String getIndividual1099() {
        return _Individual1099;
    }

    public void setPayAwtGroupId(Long _PayAwtGroupId) {
        this._PayAwtGroupId = _PayAwtGroupId;
    }

    public Long getPayAwtGroupId() {
        return _PayAwtGroupId;
    }

    public void setCisParentVendorId(Long _CisParentVendorId) {
        this._CisParentVendorId = _CisParentVendorId;
    }

    public Long getCisParentVendorId() {
        return _CisParentVendorId;
    }

    public void setBusClassLastCertifiedDate(Timestamp _BusClassLastCertifiedDate) {
        this._BusClassLastCertifiedDate = _BusClassLastCertifiedDate;
    }

    public Timestamp getBusClassLastCertifiedDate() {
        return _BusClassLastCertifiedDate;
    }

    public void setBusClassLastCertifiedBy(Long _BusClassLastCertifiedBy) {
        this._BusClassLastCertifiedBy = _BusClassLastCertifiedBy;
    }

    public Long getBusClassLastCertifiedBy() {
        return _BusClassLastCertifiedBy;
    }

    public void setVendorType(String _VendorType) {
        this._VendorType = _VendorType;
    }

    public String getVendorType() {
        return _VendorType;
    }

    public void setCompanyUrl(String _CompanyUrl) {
        this._CompanyUrl = _CompanyUrl;
    }

    public String getCompanyUrl() {
        return _CompanyUrl;
    }

    public void setOrganizationType(String _OrganizationType) {
        this._OrganizationType = _OrganizationType;
    }

    public String getOrganizationType() {
        return _OrganizationType;
    }

    public void setStatus(String _Status) {
        this._Status = _Status;
    }

    public String getStatus() {
        return _Status;
    }

    public void setMessage(String _Message) {
        this._Message = _Message;
    }

    public String getMessage() {
        return _Message;
    }
}
