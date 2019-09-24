package com.iclass.user.component.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:33.
 */
public class MD5 {
    /**
     * 获取MD5加密
     *
     * @param pwd
     *            需要加密的字符串
     * @return String字符串 加密后的字符串
     */
    public static String getPwd(String pwd) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            // 调用加密对象的方法，加密的动作已经完成
            byte[] bs = digest.digest(pwd.getBytes());
            String hexString = "";
            for (byte b : bs) {
                int temp = b & 255;
                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
