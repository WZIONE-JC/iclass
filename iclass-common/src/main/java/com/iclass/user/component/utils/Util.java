package com.iclass.user.component.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 3:14 PM.
 */
public class Util {

    public static String getDateNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String getDateTimeNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String formatTime(String time) {
        String newTime = time.split("\\.")[0];
        return newTime;
    }
}
