CREATE OR REPLACE PROCEDURE BTVL_CALL_EBS_VEND_CREATE_PROC(
    l_partner_vendor_id IN NUMBER,
    STATUS OUT VARCHAR2,
    MESSAGE OUT VARCHAR2 )
AS
  --+==========================================================================+
  -- |                        Bharti Airtel Limited                            |
  -- |                         New Delhi, India                                |
  -- +=========================================================================+
  -- |Owner        : BHARTI                                                    |
  -- |Application  : BTVL_SPORTAL                                              |
  -- |Program Type : PROCEDURE                                                 |
  -- |File Name    : BTVL_CALL_EBS_VEND_CREATE_PROC.SQL                        |
  -- |Date         : July 29, 2018                                             |
  -- |Author       : Sandeep G / Nishant S / Rupak K                           |
  -- |Description  : The package will be used to create supplier through API   |
  -- |                                                                         |
  -- |Modification History:                                                    |
  -- |===============                                                          |
  -- |Version   Date            Author            Remarks                      |
  -- |=======   ==========      =============     =============================|
  -- | 1.0     Jun 29, 2018     Kishor            Initial                      |
  -- +=========================================================================+
-- ==================================================================
  --
  supplier_header BTVL_SUPPLIER_LOAD_PKG.supplier_header_tab@BTVL_ERP_PORTAL;
  supplier_site BTVL_SUPPLIER_LOAD_PKG.supplier_site_tab@BTVL_ERP_PORTAL;
  supplier_contact BTVL_SUPPLIER_LOAD_PKG.supplier_contact_tab@BTVL_ERP_PORTAL;
  p_status  VARCHAR2(50);
  p_message VARCHAR2(1000);
  l_header  NUMBER;
  l_line    NUMBER;
BEGIN
  SELECT NVL ( a.vendor_id , 9999999 ) ,
    a.PARTNER_VENDOR_ID ,
    a.VENDOR_NAME ,
    a.VENDOR_TYPE_LOOKUP_CODE
  INTO supplier_header (1).vendor_id ,
    supplier_header (1).PARTNER_VENDOR_ID ,
    supplier_header (1).VENDOR_NAME ,
    supplier_header (1).VENDOR_TYPE_LOOKUP_CODE
  FROM BTVL_M_SUPPLIERS_TBL a
  WHERE PARTNER_VENDOR_ID = l_partner_vendor_id;
  --
  SELECT buyer_email ,
    passwd
  INTO supplier_header (1).buyer_email ,
    supplier_header (1).portal_password
  FROM BTVL_PARTNER_USER_TBL
  WHERE PARTNER_vendor_ID = l_partner_vendor_id;
  SELECT NVL (vendor_site_id , 9999999 ) ,
    PARTNER_VENDOR_ID ,
    CUSTOM_VENDOR_SITE_ID ,
    ADDRESS_LINE1 ,
    CITY ,
    STATE ,
    ZIP ,
    COUNTRY ,
    PHONE ,
    EMAIL_ADDRESS ,
    SITE_PAN
  INTO supplier_site (1).vendor_site_id ,
    supplier_site (1).PARTNER_VENDOR_ID ,
    supplier_site (1).CUSTOM_VENDOR_SITE_ID ,
    supplier_site (1).ADDRESS_LINE1 ,
    supplier_site (1).CITY ,
    supplier_site (1).STATE ,
    supplier_site (1).ZIP ,
    supplier_site (1).COUNTRY ,
    supplier_site (1).PHONE ,
    supplier_site (1).EMAIL_ADDRESS ,
    supplier_site (1).pan_num
  FROM BTVL_M_SUPPLIERS_SITES_TBL b
  WHERE b.PARTNER_VENDOR_ID = l_partner_vendor_id
  AND rownum                < 2;
  --
  BTVL_P_AP_SUPPLIER_API_PKG.BTVL_AP_SUPPLIER_API_PRC@BTVL_ERP_PORTAL ( supplier_header , supplier_site , supplier_contact , p_status , p_message );
  DBMS_OUTPUT.put_line ('<<p_status =' ||p_status);
  DBMS_OUTPUT.put_line ('<<p_message =' ||p_message);
  STATUS  :=p_status;
  MESSAGE := p_message;
END;