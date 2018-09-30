package com.airtel.rs.wrapper.nsdl;

import com.airtel.rs.utility.CommonConstants;
import com.airtel.rs.wrapper.nsdl.jaxbobjects.req.ObjectFactory;
import com.airtel.rs.wrapper.nsdl.jaxbobjects.req.VerifyCustomerPanDetailsReqMsg;
import com.airtel.rs.wrapper.nsdl.jaxbobjects.res.VerifyCustomerPanDetailsResMsg;
import com.airtel.rs.wrapper.nsdl.pojo.PanVerificationReq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

@Path("nsdl")
@Consumes("application/json")
@Produces("application/json")
public class PanVerification {
    public PanVerification() {
        super();
    }

    @POST
    public VerifyCustomerPanDetailsResMsg verifyCustomerPANDetails(PanVerificationReq panVerificationReq) {
        System.out.println("Inside verifyCustomerPANDetails");
        List<String> panNumberList = new ArrayList<String>();

        VerifyCustomerPanDetailsResMsg verifyCustomerPanDetailsResMsg = null;

        panNumberList.add(panVerificationReq.getPanNumber());

        JAXBContext jaxbContext;
        ObjectFactory verifyCustomerPanDetailsReqMsgFact = new ObjectFactory();

        VerifyCustomerPanDetailsReqMsg.EbmHeader verifyCustomerPanDetailsReqMsgEbmHeader =
            verifyCustomerPanDetailsReqMsgFact.createVerifyCustomerPanDetailsReqMsgEbmHeader();
        verifyCustomerPanDetailsReqMsgEbmHeader.setLob(panVerificationReq.getLob());
        verifyCustomerPanDetailsReqMsgEbmHeader.setConsumerTransactionId(panVerificationReq.getConsumerTransactionId());
        verifyCustomerPanDetailsReqMsgEbmHeader.setConsumerName(panVerificationReq.getConsumerName());
        verifyCustomerPanDetailsReqMsgEbmHeader.setProgrammeName(panVerificationReq.getProgrammeName());

        VerifyCustomerPanDetailsReqMsg.DataArea.VerifyCustomerPanDetailsRequest verifyCustomerPanDetailsRequest =
            verifyCustomerPanDetailsReqMsgFact.createVerifyCustomerPanDetailsReqMsgDataAreaVerifyCustomerPanDetailsRequest();
        verifyCustomerPanDetailsRequest.setPanNumber(panNumberList);

        VerifyCustomerPanDetailsReqMsg.DataArea verifyCustomerPanDetailsReqMsgDataArea =
            verifyCustomerPanDetailsReqMsgFact.createVerifyCustomerPanDetailsReqMsgDataArea();
        verifyCustomerPanDetailsReqMsgDataArea.setVerifyCustomerPanDetailsRequest(verifyCustomerPanDetailsRequest);

        VerifyCustomerPanDetailsReqMsg verifyCustomerPanDetailsReqMsg =
            verifyCustomerPanDetailsReqMsgFact.createVerifyCustomerPanDetailsReqMsg();
        verifyCustomerPanDetailsReqMsg.setEbmHeader(verifyCustomerPanDetailsReqMsgEbmHeader);
        verifyCustomerPanDetailsReqMsg.setDataArea(verifyCustomerPanDetailsReqMsgDataArea);

        try {

            //            verifyCustomerPanDetailsReqMsg
            jaxbContext = JAXBContext.newInstance(VerifyCustomerPanDetailsReqMsg.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(verifyCustomerPanDetailsReqMsg, sw);
            String verifyCustomerPanDetailsReqMsgXmlString = sw.toString();
            System.out.println("::::Request object for PAN Verification::::");
            System.out.println(verifyCustomerPanDetailsReqMsgXmlString);
            System.out.println("::::Request object for PAN Verification::::");

            //            String verifyCustomerPanDetailsResMsgStr = callNsdlApi(verifyCustomerPanDetailsReqMsgXmlString);
            //calling nsdl appi
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startdate = new Date();
            System.out.println("Start Date Time:::"+dateFormat.format(startdate)); //2016-11-16-12-08-43
            
            int timeout = 15;
            RequestConfig config = RequestConfig.custom()
              .setConnectTimeout(timeout * 1000)
              .setConnectionRequestTimeout(timeout * 1000)
              .setSocketTimeout(timeout * 1000).build();
            
            
//            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpClient httpclient  = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            
            HttpPost httpPost = new HttpPost(CommonConstants.NSDL_API_URL);
            StringEntity input;
            String output = "";
            //            try {
            input = new StringEntity(verifyCustomerPanDetailsReqMsgXmlString);
            httpPost.setEntity(input);
            httpPost.setHeader("Content-Type", "application/xml");
            HttpResponse response = httpclient.execute(httpPost);
            
            Date endDate = new Date();
            System.out.println("End Date Time:::"+dateFormat.format(endDate)); //2016-11-16-12-08-43
            
            if (!Arrays.asList(200, 201, 202).contains(response.getStatusLine().getStatusCode())) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            output = "";
            String line = br.readLine();

            System.out.println("Output from NSDL Rest API .... \n");
            //            while ((output = br.readLine()) != null) {
            //                System.out.println(output);
            //            }

            while (line != null) {
                output += line;
                line = br.readLine();
            }

            //            String verifyCustomerPanDetailsResMsgStr = output;
            //Unmarshalling xml string to java object
            JAXBContext jaxbContextres = JAXBContext.newInstance(VerifyCustomerPanDetailsResMsg.class);
            Unmarshaller unmarshaller = jaxbContextres.createUnmarshaller();
            System.out.println("Response :::::" + output);
            StringReader reader = new StringReader(output);
            verifyCustomerPanDetailsResMsg = (VerifyCustomerPanDetailsResMsg) unmarshaller.unmarshal(reader);

        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (ClientProtocolException cpe) {
            cpe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Exit verifyCustomerPANDetails");
        return verifyCustomerPanDetailsResMsg;
    }

    //    public String callNsdlApi(String verifyCustomerPanDetailsReqMsgXmlString) {
    //        CloseableHttpClient httpclient = HttpClients.createDefault();
    //        HttpPost httpPost = new HttpPost(CommonConstants.NSDL_API_URL);
    //        StringEntity input;
    //        String output = "";
    //        try {
    //            input = new StringEntity(verifyCustomerPanDetailsReqMsgXmlString);
    //            httpPost.setEntity(input);
    //            httpPost.setHeader("Content-Type", "application/xml");
    //            HttpResponse response = httpclient.execute(httpPost);
    //
    //            if (!Arrays.asList(200, 201, 202).contains(response.getStatusLine().getStatusCode())) {
    //                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
    //            }
    //
    //            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
    //
    //            System.out.println("Output from NSDL Rest API .... \n");
    //            while ((output = br.readLine()) != null) {
    //                System.out.println(output);
    //            }
    //
    //        } catch (UnsupportedEncodingException e) {
    //        } catch (ClientProtocolException e) {
    //        } catch (IOException e) {
    //        }
    //        return output;
    //    }
}
