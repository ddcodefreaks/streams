package com.airtel.rs.utility;


public class CommonConstants {
    public CommonConstants() {
        super();
    }
    
    public static final String SMS_API_URL = "http://10.5.176.22:55000";
    public static final String SMS_API_USERNAME = "fhcape";
    public static final String SMS_API_PASSWORD = "fhcape6";
    public static final String SMS_API_SOURCE_ADDRESS_NUMBER="51633";
    public static final String SMS_API_REQ_BODY_NATIONAL="<?xml version=\"1.0\" encoding=\"US-ASCII\"?>\n" + 
    "<message>\n" + 
    "	<sms type=\"mt\">\n" + 
    "		<destination messageid=\"PS0\">\n" + 
    "			<address>\n" + 
    "				<number type=\"national\">%s</number>\n" + 
    "			</address>\n" + 
    "		</destination>\n" + 
    "		<source>\n" + 
    "			<address>\n" + 
    "				<number type=\"unknown\">%s</number>\n" + 
    "			</address>\n" + 
    "		</source>\n" + 
    "		<rsr type=\"success_failure\"/>\n" + 
    "		<ud type=\"text\">%s</ud>\n" + 
    "	</sms>\n" + 
    "</message>";
    
    public static final String EMAIL_HOST="10.56.131.8";
    public static final String EMAIL_PORT="25";
    
//    public static final String DMS_USER="weblogic";
//    public static final String DMS_PASSWORD="airteldev123";
    
    
    //NSDL API CONSTANTS
    public static final String NSDL_API_URL="https://aadhaar.airtel.in:9443/nsdlPAN/customer/verifycustomer/verifycustomerpandetails/v1/verifyCustomerPanDetails";
    
    
}
