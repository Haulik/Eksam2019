package com.example.demo.Config;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class Mailer {

    public String mailTemplate(String name, String startTime, String endtime, int bookId){
        String template = "Hej "+name+"\n\n" +
                "Din booking af Steffen er nu blevet bekræftet.\n"+
                "Bookingen forgår i tidsrummet: "+startTime+" - "+endtime+"\n"+
                "Tak fordi du har booket hos Steffen!\n\n"+
                "Såfremt du ønsker at aflyse din booking bedes du følge nedenstående link: \n"+
                "localhost:8080/cancel/"+bookId;

        return template;
    }

    public String canceltemplate(String startTime, String endTime, int id, String name){

        String template = "Hej Steffen,\n"+

                "Din kunde: "+name+" har desværre aflyst sin tid: "+startTime+" - "+endTime+"\n" +
                "Tiden med id: "+id+" er nu blevet ledig igen";


        return template;
    }

    public void sendmail(String to, String details) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Steffens.biks@gmail.com", "GRP500o!");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("Steffens.biks@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject("Confirmation from Steffen");
        msg.setContent(details, "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(details, "text/html");


        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        //attachPart.attachFile("/var/tmp/image19.png");
        //multipart.addBodyPart(attachPart);
        //msg.setContent(multipart);
        Transport.send(msg);
    }

}

