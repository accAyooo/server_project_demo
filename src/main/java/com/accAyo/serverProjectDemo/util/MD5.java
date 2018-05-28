package com.accAyo.serverProjectDemo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/5/28
 */
public class MD5 {
    //    private static MessageDigest md5 = null;
    private static char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};

    public static String MD5(String source){
        if(source == null)
            return null;
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] bs;
        char str[] = null;
        try {
            bs = md5.digest(source.getBytes("utf-8"));
            str = new char[32];
            int k = 0;                                // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = bs[i];                 // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(str);
    }
}
