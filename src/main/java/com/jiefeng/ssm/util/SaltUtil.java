package com.jiefeng.ssm.util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Random;

public class SaltUtil {

    public static String generateSalt() throws UnsupportedEncodingException {
        Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[2];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
