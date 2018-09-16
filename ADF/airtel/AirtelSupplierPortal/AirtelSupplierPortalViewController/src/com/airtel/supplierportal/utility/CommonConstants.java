package com.airtel.supplierportal.utility;


public class CommonConstants {


    // Activation Email Constants
    public static final String VERIFICATION_SENDER_EMAIL="partnerregistration@airtel.com";
    public static final String VERIFICAION_EMAIL_SUBJECT="Please verify your email and activate your Partner A/C with Airtel";
    public static final String VERIFICATION_EMAIL_BODY="<p>Dear Partner,</p>\n" + 
        "<p>Thank you for showing your interest to do business with Airtel. Please click on the link below to verify your email ID and activate your account.</p>\n" + 
        "<p><a href='%s' target='_blank'>(Click to Verify)</a></p>\n" + 
        "<p>Thanks,</p>\n" + 
        "<p>Team Airtel</p>";
//       public static final String ACTIVATION_URL="http://devsupplier.airtel.in:7777/SupplierPortal/faces/pages/login/partnerLogin.jspx?actCode=%s";//for dev
//    public static final String ACTIVATION_URL="http://10.14.162.68:7777/SupplierPortal/faces/pages/login/partnerLogin.jspx?actCode=%s";//for uat
    public static final String ACTIVATION_URL="http://125.19.17.58:7777/SupplierPortal/faces/pages/login/partnerLogin.jspx?actCode=%s";//for uat with public ip
//       public static final String ACTIVATION_URL="https://partners.airtel.in/SupplierPortal/faces/pages/login/partnerLogin.jspx?actCode=%s";//for PROD
    
    
    // SEND OTP OVER MAIL CONSTANTS
    public static final String OTP_SENDER_EMAIL = "partnerregistration@airtel.com";
    public static final String OTP_EMAIL_SUBJECT = "OTP for resetting your Airtel Partner Account Password";
    public static final String OTP_EMAIL_BODY =
        "<p>Dear Partner,</p>\n" +
        "<p>Your one time password for resetting the password of your Airtel Partner profile is <b>%s</b></p>\n" +
        "<p>Please enter the code on the OTP code box listed on the Forget password page.</p>\n" +
        "<p>Note: Please note that the OTP will be valid for next 2 hours only.</p>\n" + "<p>Regards,</p>\n" +
        "<p>Team Airtel</p>";


    // SEND SIGNUP REQUEST TO RESPECTIVE BUYER BASED PON SELECTED PRODUCT CATEGORY
    public static final String SIGNUP_REQUEST_SENDER_EMAIL="partnerregistration@airtel.com";
    public static final String SIGNUP_REQUEST_EMAIL_SUBJECT="Signup request to become  Airtel Partner";
    public static final String SIGNUP_REQUEST_EMAIL_BODY="<p>Dear Team,,</p>\n" + 
        "<p>The below partner has shown interest in doing business with Airtel. Please find the details below:</p>\n"+
        "<p>Partner email ID : %s </p>\n" +
        "<p>Company Url : %s </p>\n" +
        "<p>Product and Service Category : %s </p>\n" +
        "<p>Regards,</p>\n" + 
        "<p>Team Airtel</p>";
    
    
    
    // SEND NOTIFICATION MAIL TO BUYER TO NOTIFY THAT REQUEST FROM XYZ SUPPLIER CAME UP FOR THE APPROVAL
    public static final String NOTIFY_BUYER_SENDER_EMAIL="partnerregistration@airtel.com";
    public static final String NOTIFY_BUYER_EMAIL_SUBJECT="Partner Account creation Approval Request";
    public static final String NOTIFY_BUYER_EMAIL_BODY="<p>Dear Buyer,</p>\n" + 
        "<p>The following request has been sent for your approval.</p>\n"+
        "<p>Type: Partner Registration Approval</p>\n"+
        "<p>Partner Name: %s</p>\n" +
        "<p>Contact Details: %s </p>\n" +
        "<p>Email ID: %s</p>\n" +
        "<p>Company URL: %s</p>\n"+
        "<p><a href='%s' target='_blank'>(Click to Approve)</a></p>\n" +
        "<p>Thanks,</p>\n" + 
        "<p>Team Airtel</p>";
    
//    public static final String BUYER_LOGIN_URL="http://devsupplier.airtel.in:7777/BuyerPortal/faces/";//for dev
    public static final String BUYER_LOGIN_URL="http://10.14.162.68:7777/BuyerPortal/faces/";    // for uat
//    public static final String BUYER_LOGIN_URL="https://partners.airtel.in/BuyerPortal/faces/";//for prod
    
    // SEND NOTIFICATION MAIL TO BUYER TO NOTIFY THAT REQUEST FROM XYZ SUPPLIER CAME UP FOR THE APPROVAL
    public static final String NOTIFY_SUPPLIER_SENDER_EMAIL="partnerregistration@airtel.com";
    public static final String NOTIFY_SUPPLIER_EMAIL_SUBJECT="Partner Registration Pending Buyer Approval Notification";
    public static final String NOTIFY_SUPPLIER_EMAIL_BODY="<p>Dear Partner,</p>\n" + 
        "<p>Your request for registering with Airtel  is Pending with Airtel Buyer. Your Vendor code will be created and you would be allowed to participate in sourcing process once approval from buyer is obtained.</p>\n"+
        "<p>Thanks,</p>\n" + 
        "<p>Team Airtel</p>";
    
    
    // SEND OTP MEESAGE TO SUPPLIER FOR THE LOGIN 
//    public static final String LOGIN_OTP_MSG = "Your One Time password %s";
    public static final String LOGIN_OTP_MSG = "Thankyou for using Airtel Partner Portal. Dear Partner, your OTP is %s. Please do not share OTP for security reasons.";
    
    //VALIDATION MESSAGE FOR MOBILE NOT REGISTERED
    public static final String MOBILE_NOT_REGISTERED = "Mobile number is not registered. Please signup";
    //VALIDATION MESSAGE FOR MOBILE  REGISTERED
    public static final String MOBILE_REGISTERED = "Mobile number is registered. Please Login";
    //INVALID OTP FROM MOBILE SIGNUP
    public static final String INVALID_OTP = "Incorrect OTP";


    // ERROR MESSAGE FOR INVALID PAN i.e IF PAN IS ALREADY EXIST IN THE SYSTEM
    public static final String INVALID_PAN_ERROR = "PAN provided is already registered with Airtel. Please begin using your Existing account. If you need any further help, please contact Airtel buyer.";

    //SEND ERROR MESSAGE IF EMAIL ID IS NOT REGISTERED IN FORGOT PASSWORD
    public static final String EMAIL_NOT_REGISTERED = "Email Id is not registered. Please signup";
    //SEND ERROR MESSAGE IF THE USER ENTERS THE WRONG OTP
    public static final String INCORRECT_OTP = "Incorrect OTP";
    //SET SIGNUPFORM PASSWORD VALIDATION LENGTH
    public static final int PASSWORD_LENGTH = 8;
    //SET ERROR MESSAGE IF THE PASSWORD LENGTH DOESN'T MATCH
    public static final String PASSWORD_LENGTH_ERROR_MESSAGE = "Password has to be minimum 8 character long";
    //SET ERROR MESSAGE IF THE PASSWORD AND RENETER PASSWORD DOESN'T MATCH
    public static final String PASSWORD_NOT_MATCHED_ERROR_MESSAGE="New and Confirm passwords do not match";
    
    public static final String UTF8="UTF-8";
    public static final Boolean BOOLEAN_FALSE=Boolean.FALSE;
    public static final Boolean BOOLEAN_TRUE=Boolean.TRUE;
    
    
    //SET ERROR MESSAGE FOR ALREDAY REGISTERED SUPPLIER
    public static final String ALREADY_REGISTERED_SUPPLIER =
        "You are already registered as a partner with Airtel. Please go to login using your existing account";
    public static final String REJECTED_SUPPLIER =
        "Dear Partner, your account registration application was rejected earlier. Please get in touch with your Airtel buyer to continue further";


    //PARTNER WORK FLOW STATUS
    //FOR SIGNUP
    public static final int PARTNER_STATUS_SIGNUP = 1;
    public static final int PARTNER_STATUS_ACTIVATED = 2;
    public static final int PARTNER_STATUS_SAVE = 3;
    public static final int PARTNER_STATUS_SUBMIT = 4;
    public static final int PARTNER_STATUS_ACCEPTANCE = 5;
    public static final int PARTNER_STATUS_REJECTED = 6;


    //SIGNUP YES/NO NAME PAGE
    public static final String BUYER_EMAIL_ID_NOT_VALID_FORMAT = "Please provide a valid Airtel ID";
    public static final String BUYER_EMAIL_ID_NOT_VALID = "The email address entered is not a valid Airtel buyer's ID";
//PARTNER LOGIN IF USER DOESN'T EXIST 
    public static final String PARTNER_NOT_REGISTERED="User does not exist. Please signup";
        

//Any Email Validation Regex
    public static final String EMAIL_FORMAT_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String EMAIL_NOT_VALID = "Invalid Email";
    
    
    
    // MANDATORY FIELD VALIDATION MESSAGE 
    public static final String FIELD_IS_MANDATORY ="Field is mandatory, Please provide mandatory fields.";
    public static final String UPLOADED_DOC_ERR_MSG ="Please upload PDF, PNG, JPG file only with max. file size 2mb";
    public static final String MOBILE_NUMBER_REGEX = "^[0-9]{1,10}$";
    public static final String MOBILE_NUMBER_NOT_VALID = "Invalid Mobile Number";

    public static final String PAN_REGEX="[A-Za-z]{5}\\d{4}[A-Za-z]{1}";
    public static final String INVALID_PINCODE= "Pincode Invalid";
    
    //login page
    public static final String RJECTED_SUPPLIER_MSG="Dear Supplier,\n" + 
    "your account registration application was rejected earlier. Please get in touch with your Airtel buyer to continue further.";
    
    public static final String SUPPLIER_EXISTS_MSG="The organization name entered by you is already registered with Airtel.";
    
//    public static final String UPLOAD_TMP_DIRECTORY = "E:\\rupak\\tempUpload\\";
//    public static final String UPLOAD_TMP_DIRECTORY = "/home/vrd_user/tmpUploadDir/";//for dev
    public static final String UPLOAD_TMP_DIRECTORY = "/ucmshare/tmpUploadDir/";//for uat
//    public static final String UPLOAD_TMP_DIRECTORY = "/ucmshare/tmpUploadDir/";//for prod
    
     public static final String UCM_URL="http://10.5.74.13:16200/_dav/cs/idcplg";// for dev / uat
     public static final String UCM_USERNAME="testuser";//for dev / uat
     public static final String UCM_PASSWORD="welcome123";//for dev / uat
     public static final String UCM_PRIVATE_SECURITY_GROUP="Airtel_Supplier"; // for dev / uat
    
//    public static final String UCM_URL="http://10.5.78.175:16200/_dav/cs/idcplg";// for prod
//    public static final String UCM_USERNAME="ebsmigrationuser";//for prod
//    public static final String UCM_PASSWORD="welcome123";//for prod
//    public static final String UCM_PRIVATE_SECURITY_GROUP="Airtel_Supplier"; // for PROD
     
     
    // ACCOUNT CREATION EXCEPTION FAILED STRING 
    public static final String ACCOUNT_CREATION_EXCEPTION = "Account creation failed, Please try after sometime.";
    
    // REGEX FOR OCMPANY URL
    public static final String COMPANY_URL_REGEX = "^[ A-Za-z.#]*$";
    
    // ERROR MESSAGE FOR NOT VALID URL 
    public static final String INVALID_URL = "Please enter valid url";
    
    // WRAPPER API URL FOR EMAIL
//    public static final String WRAPPER_EMAIL_API_URL = "https://10.14.13.30/AirtelWrapperRSProject/resources/email";//for PROD
//    public static final String WRAPPER_NSDL_API_URL = "https://10.14.13.30/AirtelWrapperRSProject/resources/nsdl";//for PROD
//    public static final String WRAPPER_SMS_API_URL = "https://10.14.13.30/AirtelWrapperRSProject/resources/sms";//for PROD
    
    public static final String WRAPPER_EMAIL_API_URL = "http://10.14.162.88:7777/AirtelWrapperRSProject/resources/email";//for PROD
    public static final String WRAPPER_NSDL_API_URL = "http://10.14.162.88:7777/AirtelWrapperRSProject/resources/nsdl";//for PROD
    public static final String WRAPPER_SMS_API_URL = "http://10.14.162.88:7777/AirtelWrapperRSProject/resources/sms";//for PROD
    
    // Pass phrase for AES Descryption
    public static final String AES_PASSPHRASE = "key123";
    
    public static final String DECRYPT_DELIMITER = "::";

   
}
