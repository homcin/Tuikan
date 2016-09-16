package com.grace.zhihunews.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hander on 16/2/27.
 * <p/>
 * Email : hander_wei@163.com
 */
public class DateUtil {

    /**
     * 获取最新日期，由于知乎URL的原因，比今天还延后了一天
     *
     * @return
     */
    public static String getLatestDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 7) {//知乎日报7点之前不更新，所以7点以前最新列表应该加载之前一天的内容
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取今天的日期
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前一天日期
     *
     * @param date
     * @return
     */
    public static String getPreviousDay(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        Date previousDate = null;
        try {
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            previousDate = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(previousDate);
    }

    public static String getDateDescription(String date) {
        String dateDescription;
        Date dateObject = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            dateObject = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        int todayRank = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(dateObject);
        int dateRank = calendar.get(Calendar.DAY_OF_YEAR);
        switch (todayRank - dateRank) {
            case 0: dateDescription =  "今天";
                break;
            case 1: dateDescription = "昨天";
                break;
            case 2: dateDescription = "前天";
                break;
            default: SimpleDateFormat monthDayFormat = new SimpleDateFormat("M月d日");
                dateDescription = monthDayFormat.format(dateObject);
                break;
        }
        return dateDescription;
    }
}
