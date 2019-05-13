package com.jiefeng.ssm.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
    private static final String HOST = MailConfig.host;
    private static final Integer PORT = MailConfig.port;
    private static final String USERNAME = MailConfig.userName;
    private static final String PASSWORD = MailConfig.passWord;
    private static final String emailForm = MailConfig.emailForm;
    private static final String timeout = MailConfig.timeout;
    private static final String personal = MailConfig.personal;
    private static final String subject = MailConfig.subject;
    private static final String Content = MailConfig.html;
    private static JavaMailSenderImpl mailSender = createMailSender();

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param emailAddress 接收人的邮箱
     * @param identifyCode 验证码
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String emailAddress,String username, int identifyCode) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailForm, personal);
        messageHelper.setTo(emailAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(MailUtil.contentTemplate(username,identifyCode), true);
//      messageHelper.addAttachment("", new File(""));//附件
        mailSender.send(mimeMessage);
    }



    //定义发送内容模板
    public static String contentTemplate(String username,int identifyCode){
        String template =
                "<sapn style=\"font-weight: bold;\">酸奶在线网课:<span><br><p style=\"font-size: 15px; font-weight: normal; margin-left: 50px;\"><span style=\"font-weight: bold;\">"
                        + username +"</span>,你好</p><br><span style=\"font-size: 15px; margin-left: 50px; font-weight: normal;\">感谢您注册酸奶在线网课，您的邮箱验证码为:<span style=\"color: green;font-weight: bold;\">"
                        + identifyCode +"</span><span><span>(5分钟内有效)</span>";

        return template;
    }

}
