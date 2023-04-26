package com.alwaha.cafe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailUtils {

    @Autowired
    JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("***************");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        if(! (list == null) && list.size() > 0)
            message.setCc(getCcArray(list));
        emailSender.send(message);
    }
    public String[] getCcArray(List<String> ccList){
        String[] cc = new String[ccList.size()];
        for (int i=0;i<ccList.size();i++){
            cc[i] = ccList.get(i);
        }
        return cc;
    }

    public  void forgotMail(String to, String subject, String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("*********");
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Here is your new password: "+password);
        emailSender.send(message);
    }
}
