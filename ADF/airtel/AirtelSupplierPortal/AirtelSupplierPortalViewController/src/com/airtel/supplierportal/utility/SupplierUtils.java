package com.airtel.supplierportal.utility;

import com.airtel.supplierportal.bean.login.LoginBean;

import java.time.LocalDateTime;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.adf.share.logging.ADFLogger;

public class SupplierUtils {
    
    private static final ADFLogger _logger = ADFLogger.createADFLogger(SupplierUtils.class);
    
    /**
     * default constructor.
     */
    public SupplierUtils() {
        super();
    }


    /**
     * Method to generate OTP
     * @param len
     * @return
     */
    public static char[] generateOTP(int len) {
        _logger.config("Entering method generateOTP :: len ::"+len);
        _logger.info("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        _logger.config("Exit method generateOTP");
        return otp;
    }
    
    
    
    /**
     * Method to generate account activation code.
     * @param userId
     * @return
     */
    public static String generateActivationCode(String userId) {
        _logger.config("Entering method generateActivationCode :: userId :: "+ userId);
        LocalDateTime today = LocalDateTime.now();
        _logger.info("Current DateTime=" + today);
//        String plainString =
//            userId + "~" + today.getYear() + "~" + today.getMonth() + "~" + today.getDayOfMonth() + "~" +
//            today.getHour() + "~" + today.getMinute() + "~" + today.getSecond();
        String plainString = userId ;
        
//        return EncryptDecryptUtil.encrypt(plainString);
        _logger.config("Exit method generateActivationCode");
        return plainString;
    }
 
    /**
     * method to check PAN number pattern
     * @param panNumber
     * @return
     */
    public static Boolean matchPANPattern(String panNumber){
        _logger.config("Entering method matchPANPattern :: panNumber :: "+ panNumber);
        _logger.info("Validating PAN Number with Regex:::"+panNumber);
        Pattern panPattern = Pattern.compile(CommonConstants.PAN_REGEX);
        Matcher panMatcher = panPattern.matcher(panNumber);
        _logger.info("PAN Regex::" + panMatcher.matches());
        _logger.config("Exit method matchPANPattern");
        return panMatcher.matches();
    }
    
    
    /**
     * Method to check company url pattern
     * @param url
     * @return
     */
    public static Boolean matchUrlPattern(String url){
        _logger.config("Entering method matchUrlPattern :: url :: "+url);
        _logger.info("Validating url with regex ::::"+url);
        Pattern urlPattern = Pattern.compile(CommonConstants.COMPANY_URL_REGEX);
        Matcher urlMatcher = urlPattern.matcher(url);
        _logger.info("URL REGEX :::"+urlMatcher.matches());
        _logger.config("Exit method matchUrlPattern");
        return urlMatcher.matches();
    }

    public static void main(String[] args) {
        //        int length = 6;
        //        _logger.info(generateOTP(length));
        //        String v = "9643102523";
        //        Long t = Long.parseLong(v);
        //        _logger.info("(t);
        //        int code = 200;
        //        if(Arrays.asList(200,201,202).contains(code)){
        //            _logger.info("yes found");
        //        }
        
        String panNumber = "AAZCS7909L";
        _logger.info(""+matchPANPattern(panNumber));
        
    }
}
