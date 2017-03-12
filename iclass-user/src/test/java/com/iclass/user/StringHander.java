package com.iclass.user;

import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void phoneHandler() {
        String phone = "18829344732";
        System.out.println(phone.matches("[\\d]{11}"));
        Pattern prefix = Pattern.compile("^[\\d]{4}");
        Pattern post = Pattern.compile("[\\d]{4}$");
        Matcher m = prefix.matcher(phone);
        m.find();
        System.out.println(m.group());
        m = post.matcher(phone);
        m.find();
        System.out.println(m.group());
    }

    @Test
    public void testInteger() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
    }
    @Test
    public void testDate() {
        Timestamp timestamp = new Timestamp(Long.valueOf("1489318426000"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(timestamp));
//        System.out.println(new Date(1489318426000));

    }

}
