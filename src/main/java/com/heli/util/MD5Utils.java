package com.heli.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: MD5
 * date: 2021/1/30 21:52
 * @author heli
 * @since JDK 1.8
 */
public class MD5Utils {

    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
    * Description: 测试加密效果
    * @author: heli
    */
    public static void main(String[] args) {
        System.out.println(code("123456"));
    }
}
