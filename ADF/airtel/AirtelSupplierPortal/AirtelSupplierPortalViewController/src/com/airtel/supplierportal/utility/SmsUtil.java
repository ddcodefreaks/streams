package com.airtel.supplierportal.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.Arrays;

import oracle.adf.share.logging.ADFLogger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.json.simple.JSONObject;

public class SmsUtil {
    private static final ADFLogger _logger = ADFLogger.createADFLogger(SmsUtil.class);
    
    /**
     * defualt constructor.
     */
    public SmsUtil() {
        super();
    }

    /**
     * Method to send SMS
     * @param recipient
     * @param message
     * @return
     */
    public static Boolean sendSms(String recipient, String message){
        _logger.config("Entering method sendSms :: recipient :: "+ recipient + " :: message :: "+message);
        
        Boolean isSent=Boolean.FALSE;
        
        //Call SMS Wrapper Utility
        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://10.13.80.155:8888/AirtelWrapperRSProject/resources/sms");
//        HttpPost httpPost = new HttpPost("http://10.14.162.68:7777/AirtelWrapperRSProject/resources/sms");
        HttpPost httpPost = new HttpPost(CommonConstants.WRAPPER_SMS_API_URL);
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        obj.put("to", recipient);

        StringEntity input;
        try {
            input = new StringEntity(obj.toJSONString());
            httpPost.setEntity(input);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(httpPost);
            
            if (!Arrays.asList(200,201,202).contains(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            _logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                _logger.info("output");
            }
            isSent=Boolean.TRUE;
        } catch (UnsupportedEncodingException uee) {
            _logger.severe("Exception raised : "+uee.getMessage());
            uee.printStackTrace();
            isSent=Boolean.FALSE;
        } catch (ClientProtocolException cpe) {
            _logger.severe("Exception raised : "+cpe.getMessage());
            cpe.printStackTrace();
            isSent=Boolean.FALSE;
        } catch (IOException e) {
            _logger.severe("Exception raised : "+e.getMessage());
            e.printStackTrace();
            isSent=Boolean.FALSE;
        }
        _logger.config("Exit method sendSms");
        return isSent;
    }
}
