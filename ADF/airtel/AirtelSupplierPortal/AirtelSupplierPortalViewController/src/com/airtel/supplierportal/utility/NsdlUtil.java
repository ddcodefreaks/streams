package com.airtel.supplierportal.utility;

import com.airtel.supplierportal.pojo.nsdl.VerifyCustomerPanResp;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;

import oracle.adf.share.logging.ADFLogger;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.simple.JSONObject;

public class NsdlUtil {
    private static final ADFLogger _logger = ADFLogger.createADFLogger(EncryptDecryptUtil.class);
    
    
    /**
     * Method to fetch pan details
     * @param consumerTransactionId
     * @param panNumber
     * @return
     */
    public static VerifyCustomerPanResp fetchPANDetails(String consumerTransactionId, String panNumber) {
        _logger.config("Entering method fetchPANDetails ::: consumerTransactionId :: "+ consumerTransactionId + " :: panNumber :: "+ panNumber);
        _logger.info("Calling api...");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate = new Date();
        _logger.info("Start Date Time:::"+dateFormat.format(startdate)); //2016-11-16-12-08-43
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
          .setConnectTimeout(timeout * 1000)
          .setConnectionRequestTimeout(timeout * 1000)
          .setSocketTimeout(timeout * 1000).build();
//        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
//       HttpPost httpPost = new HttpPost("http://10.13.80.155:8888/AirtelWrapperRSProject/resources/nsdl");
//        HttpPost httpPost = new HttpPost("http://10.14.162.68:7777/AirtelWrapperRSProject/resources/nsdl");
       HttpPost httpPost = new HttpPost(CommonConstants.WRAPPER_NSDL_API_URL);
       
        JSONObject obj = new JSONObject();
        obj.put("lob", "Mobility");
        obj.put("consumerTransactionId", consumerTransactionId);
        obj.put("consumerName", "Banks");
        obj.put("programmeName", "PAN");
        obj.put("panNumber", panNumber);
        StringEntity input;
        VerifyCustomerPanResp verifyCustomerPanRespObj=null;
        try {
            _logger.info("Input Object");
            _logger.info(obj.toJSONString());
            input = new StringEntity(obj.toJSONString());
            httpPost.setEntity(input);
            httpPost.setHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(httpPost);
            Date endDate = new Date();
            _logger.info("End Date Time:::"+dateFormat.format(endDate)); //2016-11-16-12-08-43

            if (!Arrays.asList(200, 201, 202).contains(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                //                return Boolean.FALSE;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output = "";
            String line = br.readLine();

            _logger.info("Output from NSDL Rest API .... \n");
            while (line != null) {
                output += line;
                line = br.readLine();
            }
            _logger.info(output);
            Gson gson = new Gson();
            verifyCustomerPanRespObj = gson.fromJson(output, VerifyCustomerPanResp.class);
            _logger.info("Status code :::" + verifyCustomerPanRespObj.getDataArea()
                                                       .getVerifyCustomerPanDetailsResponse()
                                                       .getStatus()
                                                       .getStatusCode());

        } catch (Exception e) {
            _logger.severe("Exception raised : "+e.getMessage());
            e.printStackTrace();
        }
        _logger.config("Exiting method fetchPANDetails");
        return verifyCustomerPanRespObj;
    }
}
