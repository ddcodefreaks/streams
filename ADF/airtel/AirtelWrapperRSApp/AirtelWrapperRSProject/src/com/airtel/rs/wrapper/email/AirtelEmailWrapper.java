package com.airtel.rs.wrapper.email;

import com.airtel.rs.utility.CommonConstants;
import com.airtel.rs.wrapper.email.pojo.EmailMsgReq;
import com.airtel.rs.wrapper.email.pojo.EmailMsgResp;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

import javax.mail.Session;
import javax.mail.Transport;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("email")
@Consumes("application/json")
@Produces("application/json")
public class AirtelEmailWrapper {
    public AirtelEmailWrapper() {
        super();
    }

    @POST
    public EmailMsgResp sendEmail(EmailMsgReq emailMsgReq) {
        Boolean isSent = Boolean.FALSE;
        String error = "";
        // email ID of Recipient.
        String recipient = emailMsgReq.getRecipient();

        // email ID of  Sender.
        String sender = emailMsgReq.getSender();

        // using host as localhost
        String host = CommonConstants.EMAIL_HOST;

        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", CommonConstants.EMAIL_PORT);

        // creating session object to get properties
        Session session = Session.getDefaultInstance(properties);

        try {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject(emailMsgReq.getSubject());

            // set body of the email.
            message.setContent(emailMsgReq.getContent(), "text/html");

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
            isSent = Boolean.TRUE;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            isSent = Boolean.FALSE;
            error = mex.getMessage();
        } catch (Exception e){
            e.printStackTrace();
            isSent = Boolean.FALSE;
            error = e.getMessage();
        }
        EmailMsgResp emailResp = new EmailMsgResp();
        emailResp.setStatus(isSent?"success":"failed");
        emailResp.setError(error);
        return emailResp;
    }
}
