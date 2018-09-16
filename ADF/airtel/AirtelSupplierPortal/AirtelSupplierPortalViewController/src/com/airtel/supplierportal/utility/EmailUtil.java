package com.airtel.supplierportal.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.adf.share.logging.ADFLogger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.json.simple.JSONObject;

public class EmailUtil {
    private static final ADFLogger _logger = ADFLogger.createADFLogger(EmailUtil.class);
    
    /**
     * default constructor.
     */
    public EmailUtil() {
        super();
    }


    /**
     * methdo to send email
     * @param recipient
     * @param sender
     * @param subject
     * @param content
     * @return
     */
    public static Boolean sendEmail(String recipient, String sender, String subject, String content) {
        _logger.config("Entering method sendEmail :: recipient :: "+ recipient + " :: sender :: "+ sender + " :: subject :: "+ subject+" :: content :: "+ content );
        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://10.13.80.155:8888/AirtelWrapperRSProject/resources/email");
//        HttpPost httpPost = new HttpPost("http://10.14.162.68:7777/AirtelWrapperRSProject/resources/email");
        HttpPost httpPost = new HttpPost(CommonConstants.WRAPPER_EMAIL_API_URL);
        
        JSONObject obj = new JSONObject();
        obj.put("recipient", recipient);
        obj.put("sender", sender);
        obj.put("subject", subject);
        obj.put("content", content);

        StringEntity input;
        try {
            input = new StringEntity(obj.toJSONString());
            httpPost.setEntity(input);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(httpPost);
            
            if (!Arrays.asList(200,201,202).contains(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//                return Boolean.FALSE;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            _logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                _logger.info(output);
            }

        } catch (UnsupportedEncodingException e) {
            _logger.severe("Exception raised:::"+e.getMessage());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            _logger.severe("Exception raised:::"+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            _logger.severe("Exception raised:::"+e.getMessage());
            e.printStackTrace();
        }

        _logger.config("Exit method sendEmail");
        return Boolean.TRUE;
    }

    /**
     * method to validate email
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        _logger.config("Entering validate Email :: email Id :: " + email);
        String regex =CommonConstants.EMAIL_FORMAT_REGEX;
        
    //        String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


        Pattern pattern = Pattern.compile(regex);

        if (email != null) {
            Matcher matcher = pattern.matcher(email);
            _logger.info(email + " : " + matcher.matches());
            return matcher.matches();
        } else {
            _logger.info("email doesn't matched!");
            return false;
        }

    }
}
