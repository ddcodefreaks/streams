create or replace PROCEDURE BTVL_CALL_EBS_VENDER_PRC(
    l_partner_vendor_id IN NUMBER,
    l_partner_password IN VARCHAR2,
    l_buyer_email IN VARCHAR2,
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
  -- | 1.0     Jun 29, 2018     Nishant            Initial                      |
  -- +=========================================================================+
-- ==================================================================
  --
  supplier_header BTVL_SUPPLIER_LOAD_PKG.supplier_header_tab;
  supplier_site BTVL_SUPPLIER_LOAD_PKG.supplier_site_tab;
  supplier_contact BTVL_SUPPLIER_LOAD_PKG.supplier_contact_tab;
  p_status  VARCHAR2(50);
  p_message VARCHAR2(1000);
  l_header  NUMBER;
  l_line    NUMBER;
  v_no_data1  NUMBER :=1;
  v_no_data2  NUMBER :=1;
  v_no_data3  NUMBER :=1;
 
BEGIN
 BEGIN
  SELECT NVL ( a.vendor_id , 9999999 ) ,
    a.PARTNER_VENDOR_ID ,
    a.VENDOR_NAME ,
    a.VENDOR_TYPE_LOOKUP_CODE
  INTO supplier_header (1).vendor_id ,
    supplier_header (1).PARTNER_VENDOR_ID ,
    supplier_header (1).VENDOR_NAME ,
    supplier_header (1).VENDOR_TYPE_LOOKUP_CODE
  FROM BTVL_M_SUPPLIERS_TBL@BTVL_PORTAL_ERP a
  WHERE PARTNER_VENDOR_ID = l_partner_vendor_id;
  EXCEPTION WHEN NO_DATA_FOUND THEN 
  v_no_data1 :=0;
  MESSAGE:='No record found in BTVL_M_SUPPLIER_TBL'||'-' || SQLCODE ;
  WHEN OTHERS
  THEN
  v_no_data1 :=0;
  MESSAGE:='An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM;
  END;
  --
supplier_header (1).buyer_email := l_buyer_email;
supplier_header (1).portal_password := l_partner_password;
--
BEGIN  
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
  FROM BTVL_M_SUPPLIERS_SITES_TBL@BTVL_PORTAL_ERP b
  WHERE b.PARTNER_VENDOR_ID = l_partner_vendor_id;
EXCEPTION 
  WHEN NO_DATA_FOUND THEN
   v_no_data3:=0;
   MESSAGE:='No record found in BTVL_M_SUPPLIER_SITES_TBL'||'-' || SQLCODE ;
  WHEN OTHERS
  THEN
   v_no_data3 :=0;
   MESSAGE:='An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM;
END;

BEGIN
  if v_no_data1 != 0 and v_no_data3 != 0
  then
  --
  BTVL_P_AP_SUPPLIER_API_PKG.BTVL_AP_SUPPLIER_API_PRC ( supplier_header , supplier_site , supplier_contact , p_status , p_message );
  
  DBMS_OUTPUT.put_line ('<<p_status =' ||p_status);
  DBMS_OUTPUT.put_line ('<<p_message =' ||p_message);
  STATUS  :=p_status;
  MESSAGE := p_message;
  END IF;
END;
EXCEPTION WHEN OTHERS
THEN
raise_application_error(-20002,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;