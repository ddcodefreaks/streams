package com.airtel.rs.wrapper.sms;

import com.airtel.rs.utility.CommonConstants;
import com.airtel.rs.wrapper.sms.pojo.SMSMsgReq;

import com.airtel.rs.wrapper.sms.pojo.SMSMsgResp;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@Path("sms")
@Consumes("application/json")
//@Produces("application/json")
public class AirtelSMSWrapper {
    public AirtelSMSWrapper() {
        super();
    }

    @POST
    public SMSMsgResp sendNationalSMS(SMSMsgReq smsMsg) {

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(CommonConstants.SMS_API_URL);

            String auth = new StringBuffer(CommonConstants.SMS_API_USERNAME).append(":")
                                                                            .append(CommonConstants.SMS_API_PASSWORD)
                                                                            .toString();
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);

            String smsBody =
                String.format(CommonConstants.SMS_API_REQ_BODY_NATIONAL, smsMsg.getTo(),
                              CommonConstants.SMS_API_SOURCE_ADDRESS_NUMBER, smsMsg.getMessage());
            StringEntity input = new StringEntity(smsBody);
//            input.setContentType("text/xml");
            postRequest.setEntity(input);
            postRequest.setHeader("Authorization", authHeader);
            postRequest.setHeader("Content-Type", "text/xml");
            HttpResponse response = httpClient.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 202) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }


        SMSMsgResp smsRespObj = new SMSMsgResp(smsMsg.getMessage(), smsMsg.getTo(), null, "success");

        return smsRespObj;

    }
}
