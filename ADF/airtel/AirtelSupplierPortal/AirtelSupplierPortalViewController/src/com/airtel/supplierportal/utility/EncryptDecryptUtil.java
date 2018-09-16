package com.airtel.supplierportal.utility;

import java.time.LocalDateTime;

import java.time.Month;

import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import oracle.adf.share.logging.ADFLogger;

public class EncryptDecryptUtil {
    private static final ADFLogger _logger = ADFLogger.createADFLogger(EncryptDecryptUtil.class);

    private static final String encryptionKey           = "airteldev1234567";
    private static final String characterEncoding       = "UTF-8";
    private static final String cipherTransformation    = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    
    
    /**
     * Method for Encrypt Plain String Data
     * @param plainText
     * @return encryptedText
     */
    public static String encrypt(String plainText) {
        _logger.config("Entering method encrypt");
        String encryptedText = "";
        try {
            Cipher cipher   = Cipher.getInstance(cipherTransformation);
            byte[] key      = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);

        } catch (Exception E) {
             _logger.severe("Encrypt Exception : "+E.getMessage());
             E.printStackTrace();
        }
        _logger.config("Exit method ecrypt");
        return encryptedText;
    }

    /**
     * Method For Get encryptedText and Decrypted provided String
     * @param encryptedText
     * @return decryptedText
     */
    public static String decrypt(String encryptedText) {
        _logger.config("Entering method decrypt");
        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            _logger.severe("decrypt Exception : "+E.getMessage());
            E.printStackTrace();
        }
        _logger.config("Exit mthod decrypt");
        return decryptedText;
    }
    
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        _logger.info("("Enter String : ");
//        String plainString = sc.nextLine();
        LocalDateTime today = LocalDateTime.now();
        _logger.info("Current DateTime="+today);
        String plainString = "1004"+"~"+ today.getYear()+"~"+today.getMonth()+"~"+today.getDayOfMonth()+"~"+today.getHour()+"~"+today.getMinute()+"~"+today.getSecond();

        String encyptStr   = encrypt(plainString);
        String decryptStr  = decrypt("SkFKTQx1aIckrj/jOGDTlg==");
        
        _logger.info("Plain   String  : "+plainString);
        _logger.info("Encrypt String  : "+encyptStr);
        _logger.info("Decrypt String  : "+decryptStr);
        
        
        String sampleText=String.format("%s","OK");
        _logger.info(sampleText);
        System.out.println(decryptStr);
    }   
}
