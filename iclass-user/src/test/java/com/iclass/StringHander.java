package com.iclass;

import org.junit.Test;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/14 10:52.
 */
public class StringHander {

    @Test
    public void stringHandler() {
        String src = "{\"username\":\"唐洋\",\"usercode\":\"1308030331\",\"userrole\":\"管理员\"}";
        String usercodedata = src.split(",")[1];
        String usercode = usercodedata.split(":")[1].replaceAll("\"","");
        System.out.println(usercode);
    }

    @Test
    public void dateHandler() {
        String src = "2016-12-23 12:02:23.0";
        System.out.println(src.split("\\.")[0]);
    }
}
