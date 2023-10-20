package com.example.product.app.utilmethods;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendFileViaEmail {

    /*
     * In this example we are using Gmail as our smtp server. This requires that
     * you have a Google account. You may use any smtp server you
     * want by changing the host properties
     */

    public static void main(String[] args) {

  //the path of the file e.g. : "c:/Users/nikos7/Desktop/myFile.txt"

  String file = "D:/DateLabel.txt";

  //destination email address

  String to = "nipun.sharma199177@gmail.com";

  //source email address

  String from = "bikku2b@gmail.com";

	    //Your gmail password

  String password = "bk2@1991";

  String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

  //hostname of the machine that has smtp server

  String host = "smtp.gmail.com";

  //either turn on or turns off debugging during sending

  boolean sessioDebug = true;

  String messageText = "Sending a file with FileDataSourcen";

  String messageSubject = "Sending a file using FileDataSource!";

  // To use a smtp host other than Gmail, simply change the following properties

  // according to the specifications of your host

  // set the smtp host properties

  Properties props = System.getProperties();

  props.put("mail.smtp.host", host);

  props.put("mail.transport.protocol.", "smtp");

  props.put("mail.smtp.auth", "true");

  props.put("mail.smtp.", "true");

  props.put("mail.smtp.port", "465");

  props.put("mail.smtp.socketFactory.fallback", "false");

  props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

  Session mailSession = Session.getInstance(props, null);

  mailSession.setDebug(sessioDebug);

  try {

// create a message

MimeMessage message = new MimeMessage(mailSession);

//set message source

message.setFrom(new InternetAddress(from));

InternetAddress[] address = {new InternetAddress(to)};

//set message recipients

message.setRecipients(Message.RecipientType.TO, address);

message.setSubject(messageSubject);

// create and fill the first message part

MimeBodyPart messageBodyPart1 = new MimeBodyPart();

messageBodyPart1.setText(messageText);

// create the second message part

MimeBodyPart messageBodyPart2 = new MimeBodyPart();

// attach the file to the message

FileDataSource fdatasource = new FileDataSource(file);

messageBodyPart2.setDataHandler(new DataHandler(fdatasource));

messageBodyPart2.setFileName(fdatasource.getName());

// create the Multipart and add its parts to it

Multipart mpart = new MimeMultipart();

mpart.addBodyPart(messageBodyPart1);

mpart.addBodyPart(messageBodyPart2);

// add the Multipart to the message

message.setContent(mpart);

// set the Date: header

message.setSentDate(new Date());

// send the message

Transport transport = mailSession.getTransport("smtp");

transport.connect(host, from, password);

transport.sendMessage(message, message.getAllRecipients());
    System.out.println("mail sent");

  } catch (MessagingException mex) {

mex.printStackTrace();

Exception ex = null;

if ((ex = mex.getNextException()) != null) {

    ex.printStackTrace();

}

  }

    }
}