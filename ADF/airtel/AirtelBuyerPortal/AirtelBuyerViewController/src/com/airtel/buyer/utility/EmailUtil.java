package com.airtel.buyer.utility;

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

public class EmailUtil {

    private static final ADFLogger _logger = ADFLogger.createADFLogger(EmailUtil.class);

    public EmailUtil() {
        super();
    }

    public static Boolean sendEmail(String recipient, String sender, String subject, String content) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(CommonConstants.EMAIL_API_URL);
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

            if (!Arrays.asList(200, 201, 202).contains(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                //                return Boolean.FALSE;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            _logger.info("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }


        return Boolean.TRUE;
    }
}
