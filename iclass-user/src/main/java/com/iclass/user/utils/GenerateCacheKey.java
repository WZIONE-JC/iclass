package com.iclass.user.utils;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 17:24.
 */
public class GenerateCacheKey {

    private static String spilt = ":";
    //生成key：sessionid:pagename
    public static String getKey(String sessionid, String pagename) {
        return sessionid + spilt + pagename;
    }
}
