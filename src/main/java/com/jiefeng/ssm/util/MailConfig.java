package com.jiefeng.ssm.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class MailConfig {
    private static final String PROPERTIES_DEFAULT = "mailConfig.properties";
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    public static String personal;
    public static String html;
    public static String subject;
    public static Properties properties;

    static{
        init();
    }

    /**
     * 初始化
     */
    private static void init() {
        properties = new Properties();
        try{
            properties.load(new InputStreamReader(MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT),"UTF-8"));
            host = properties.getProperty("mailHost");
            port = Integer.parseInt(properties.getProperty("mailPort"));
            userName = properties.getProperty("mailUsername");
            passWord = properties.getProperty("mailPassword");
            emailForm = properties.getProperty("mailFrom");
            timeout = properties.getProperty("mailTimeout");
            personal = properties.getProperty("personal");
            html = properties.getProperty("html");
            subject = properties.getProperty("subject");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
