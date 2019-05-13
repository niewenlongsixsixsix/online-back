package com.jiefeng.ssm.mail;

import com.jiefeng.ssm.BaseTest;
import com.jiefeng.ssm.util.MailUtil;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class MailTest extends BaseTest {


    @Test
    public void testSendMail() throws MessagingException, UnsupportedEncodingException {
        MailUtil.sendMail("1622629449@qq.com","聂文龙",12345);

    }


}
