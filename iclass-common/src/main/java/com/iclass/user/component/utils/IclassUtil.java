package com.iclass.user.component.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/11/2017 3:14 PM.
 */
public class IclassUtil {

    private final static String[] days = {"", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};

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

    /**
     * 获取到的上课时间
     * 周一 第一 二节，第三 四节
     * @param data
     * @return
     */
    public static String getAttendTime(Map<Integer, String> data) {
        if(data == null) {
            return null;
        }
        Set<Integer> keys = data.keySet();
        StringBuilder result = new StringBuilder();
        for (Integer val : keys) {
            String day = getDaysString(val);
            if (day == null) {
                continue;
            }
            String time = getStringFromInteger(data.get(val));
            if (time == null) {
                continue;
            }
            result.append(day).append(time).append("<br>");
        }
        return result.toString();
    }

    /**
     * 获取周几的字符串表示
     * @param dayNumber
     * @return
     */
    private static String getDaysString(Integer dayNumber) {
        if (dayNumber < 1 || dayNumber > days.length) {
            return null;
        }
        return days[dayNumber];
    }

    /**
     * 获取第几节课的字符串表示
     * @param src
     * @return
     */
    private static String getStringFromInteger(String src) {
        String[] numStr = src.split(",");
        if (numStr == null || numStr.length == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String s : numStr) {
            switch (s){
                case "1": result.append("第1,2节 "); break;
                case "2": result.append("第3,4节 "); break;
                case "3": result.append("第5,6节 "); break;
                case "4": result.append("第7,8节 "); break;
            }
        }
        return result.deleteCharAt(result.length()-1).toString();
    }

    /**
     * 根据当前日期找出是一周中的那一天，即周几
     * @param date
     * @return
     */
    public static Integer getDayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK-1);
    }

    /**
     * 获取明天
     * @param today
     * @return
     */
    public static String getNextDay(String today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(today + " 00:00:00");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
