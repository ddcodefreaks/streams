package com.airtel.buyer.utility;


/**
 * The Class CommonConstants.
 */
public class CommonConstants {

    /** The Constant EMAIL_API_URL. */
    //for dev
//    public static final String EMAIL_API_URL =
//            "http://10.13.80.155:8888/AirtelWrapperRSProject/resources/email"; 
    public static final String EMAIL_API_URL="http://10.14.162.68:7777/AirtelWrapperRSProject/resources/email";// for uat - need tgo be load balancer ip later
//    public static final String EMAIL_API_URL = "http://10.14.162.88:7777/AirtelWrapperRSProject/resources/email"; //for PROD - 

    /** The Constant EBS_DATA_SOURCE_JNDI. */
    public static final String EBS_DATA_SOURCE_JNDI = "jdbc/ebsdbds";

    /** The Constant PARTNER_CREATION_BUYER_EMAIL_SENDER. */
    public static final String PARTNER_CREATION_BUYER_EMAIL_SENDER = "partnerregistration@airtel.com";

    /** The Constant PARTNER_CREATION_BUYER_EMAIL_SUBJECT. */
    public static final String PARTNER_CREATION_BUYER_EMAIL_SUBJECT =
        "Confirmation of creation of Partner Code with Airtel";

    /** The Constant PARTNER_CREATION_BUYER_EMAIL_BODY. */
    public static final String PARTNER_CREATION_BUYER_EMAIL_BODY =
        "<p>Dear Buyer,</p>\n" + "<p>The Partner Code for the following buyer has been created.</p>\n" +
        "<p>Partner Name: %s</p>\n" + "<p>Partner Code: %s</p>\n" + "<p>Partner Contact Details: %s</p>\n" +
        "<p>Partner Email ID: %s</p>\n" + "<p>Thanks,</p>\n" + "<p>Team Airtel</p>";

    /** The Constant PARTNER_CREATION_PART_EMAIL_SUBJECT. */
    public static final String PARTNER_CREATION_PART_EMAIL_SUBJECT =
        "Confirmation of Creation of Partner Code with Airtel";

    /** The Constant PARTNER_CREATION_PARTN_EMAIL_BODY. */
    public static final String PARTNER_CREATION_PARTN_EMAIL_BODY =
        "<p>Dear Partner,</p>\n" +
        "<p>Thanks for showing interest for doing business with Airtel. Your Partner Code has been created and You may now participate in RFX (Sourcing Process) with Airtel. You&rsquo;re A/C details are provided below.</p>\n" +
        "<p>Partner Name: %s</p>\n" + "<p>Partner Code: %s</p>\n" + "<p>Registered Email: %s</p>\n" +
        "<p>Registerd Phone No. %s</p>\n" +
        "<p>You have been registered to participate in the sourcing process. You would need to complete the remaining registration process to begin receiving POs.</p>\n" +
        "<p>Thanks,</p>\n" + "<p>Team Airtel</p>";


    /** The Constant PARTNER_REJECTION_EMAIL_SENDER. */
    public static final String PARTNER_REJECTION_EMAIL_SENDER = "partnerregistration@airtel.com";

    /** The Constant PARTNER_REJECTION_EMAIL_SUBJECT. */
    public static final String PARTNER_REJECTION_EMAIL_SUBJECT =
        "Rejection Notification of Partner registration with Airtel";

    /** The Constant PARTNER_REJECTION_EMAIL_BODY. */
    public static final String PARTNER_REJECTION_EMAIL_BODY =
        "<p>Dear Partner,</p>\n" +
        "<p>Your request for Partner registration has been rejected by Airtel Buyer with below comments.</p>\n" +
        "<p><b>%s<b></p>\n" + "<p>In case you need further information, Please contact the buyer at %s</p>\n" +
        "<p>Thanks,</p>\n" + "<p>Team Airtel</p>";


    /** The Constant BOOLEAN_FALSE. */
    public static final Boolean BOOLEAN_FALSE = Boolean.FALSE;

    /** The Constant BOOLEAN_TRUE. */
    public static final Boolean BOOLEAN_TRUE = Boolean.TRUE;

    /** The Constant UPLOAD_TMP_DIRECTORY. */
        public static final String UPLOAD_TMP_DIRECTORY = "/home/vrd_user/tmpUploadDir/"; //for dev
//        public static final String UPLOAD_TMP_DIRECTORY = "/ucmshare/tmpUploadDir/";//for uat
//    public static final String UPLOAD_TMP_DIRECTORY = "/ucmshare/tmpUploadDir/"; //for PROD

    /** The Constant UCM_URL. */
        public static final String UCM_URL = "http://10.5.74.13:16200/_dav/cs/idcplg";//for dev , uat
//    public static final String UCM_URL = "http://10.5.78.175:16200/_dav/cs/idcplg";//for prod

    /** The Constant UCM_USERNAME. */
        public static final String UCM_USERNAME = "testuser";//for dev,uat
//    public static final String UCM_USERNAME = "ebsmigrationuser"; //for prod

    /** The Constant UCM_PASSWORD. */
        public static final String UCM_PASSWORD = "welcome123";//for dev, uat
//    public static final String UCM_PASSWORD = "welcome123"; //for prod
    
    public static final String UCM_PRIVATE_SECURITY_GROUP = "Airtel_Supplier";// for dev , uat
}
