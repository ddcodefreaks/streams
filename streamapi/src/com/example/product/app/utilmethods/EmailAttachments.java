package com.example.product.app.utilmethods;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Properties;

public class EmailAttachments {

   /* public Boolean mailMethod(){
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailProperties.put("mail.smtp.ssl.enable", true);
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", false);

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setJavaMailProperties(mailProperties);
        javaMailSenderImpl.setHost("smtp.gmail.com");
        javaMailSenderImpl.setPort(465);
        javaMailSenderImpl.setProtocol("smtp");
        javaMailSenderImpl.setUsername("*********@gmail.com");
        javaMailSenderImpl.setPassword("*******");
        javaMailSenderImpl.setDefaultEncoding("UTF-8");

        List<CommonsMultipartFile> attachments = new ArrayList<>();
        attachments.add(attachFile);
        attachments.add(attachFile2);

        javaMailSenderImpl.send(mimeMessage -> {

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setTo(emailTo);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);

            if (!attachments.equals("")) {
                for (CommonsMultipartFile file : attachments) {
                    messageHelper.addAttachment(file.getOriginalFilename(), file);
                }
            }
        });
    }*/
   private static void addAttachment(Multipart multipart, String filename)
   {
       javax.activation.DataSource source = new FileDataSource(filename);
       MimeBodyPart messageBodyPart = new MimeBodyPart();
       try {
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(filename);
           multipart.addBodyPart(messageBodyPart);
       } catch (MessagingException e) {
           e.printStackTrace();
       }
       addAttachment(multipart, "file1.txt");
       addAttachment(multipart, "file2.txt");

   }

}
