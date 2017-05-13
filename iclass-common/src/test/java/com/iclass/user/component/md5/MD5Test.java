package com.iclass.user.component.md5;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * iclass
 * <p>
 * Created by yang.tang on 2017/2/10 20:35.
 */
public class MD5Test {

    private final static String[] days = {"", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    @Test
    public void testMD5() {
        String str = "361239731";
        String r = MD5.getPwd(str);
        System.out.println(r);
    }

    @Test
    public void testGetDevice() {
        String src = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
        String result = src.substring(0, src.indexOf(")") + 1);
        System.out.println(result);
    }

    @Test
    public void testDayOfWeek() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = sdf.parse("2017-04-08");
        calendar.setTime(date);
        System.out.println(days[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
    }

    @Test
    public void stringTest(){
        System.out.println(getAttendTime("1"));
    }

    public static String getAttendTime(String numberStr) {
        if(numberStr.length() <= 0) {
            return null;
        }
        String[] numStr = numberStr.split(",");
        if (numberStr == null || numberStr.length() == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String s : numStr) {
            switch (s){
                case "1": result.append("第一节,"); break;
                case "2": result.append("第二节,"); break;
                case "3": result.append("第三节,"); break;
                case "4": result.append("第四节,"); break;
            }
        }
        return result.deleteCharAt(result.length()-1).toString();
    }
}
