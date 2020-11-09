package com.example.vegetables.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>
 * cn.gwssi.enterprise.common.utils
 * </p>
 * <p>
 * File: DateUtil.java 创建时间: 2019-12-30 14:37:48
 * </p>
 * <p>
 * Title: []_[]
 * </p>
 * <p>
 * Description: 日期工具类
 * </p>
 * <p>
 * 模块: -
 * </p>
 *
 * @author liu.yonghui
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 */
public class DateUtil {
    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String  YYYY_MM_DD = "yyyy-MM-dd";

    /** 年 */
    private final int year;

    /** 月 */
    private final int month;

    /** 日 */
    private final int date;

    private static final long nd = 1000 * 24 * 60 * 60;
    private static final long nh = 1000 * 60 * 60;
    private static final long nm = 1000 * 60;
    private static final long ns = 1000;

    public DateUtil() {
        GregorianCalendar gc = new GregorianCalendar();
        this.year = gc.get(Calendar.YEAR);
        this.month = gc.get(Calendar.MONTH) + 1;
        this.date = gc.get(Calendar.DATE);
    }



    /**
     * 获取日期
     * @author liu.yonghui
     * @return
     */
    public final String toLocaleString() {
        String strMonth = Integer.toString(month);
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }
        String strDate = Integer.toString(date);
        if (strDate.length() == 1) {
            strDate = "0" + strDate;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(year));
        sb.append("-");
        sb.append(strMonth);
        sb.append("-");
        sb.append(strDate);
        return sb.toString();
    }

    /**
     * 两个时间相差几分钟
     * @param start
     * @param end
     * @return
     */
    public static long diffMinute(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        long min = diff % nd % nh / nm;
        return min;
    }

    /**
     * 两个时间相差秒数
     * @param start
     * @param end
     * @return
     */
    public static long diffSec(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        long min = diff / ns;
        return min;
    }

    /**
     * 获得几分钟前
     * @author liu.yonghui
     * @param minute
     * @return
     */
    public static Date getMinuteAgo(int minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }
    /**
     * 获得特定时间几天之后
     * @param day
     * @param time
     * @return
     */
    public static Date addDay(int day,Date time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }
    // 获得某天最大时间 2017-10-15 23:59:59
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     * 根据指定日期格式格式化日期
     *
     * @author liu.yonghui
     * @param date
     * @param formater
     * @return
     */
    public static String format(Date date, String formater){
        SimpleDateFormat sdf = new SimpleDateFormat( formater );
        return sdf.format(date);
    }

    /**
     * 格式化时间
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date prase(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public static String changeDate(String date) throws ParseException {
        if (StringUtils.isNotEmpty(date)) {
            date = date.replace("Z", " UTC");//是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
            Date d = format.parse(date);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//格式化的表达式
            return format1.format(d);
        }
        return null;
    }

    public static String changeEndDate(String date) throws ParseException {
        if (StringUtils.isNotEmpty(date)) {
            date = date.replace("Z", " UTC");//是空格+UTC
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
            Date d = format.parse(date);
            Date date1 = getEndOfDay( d);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//格式化的表达式
            return format1.format(date1);
        }
        return null;
    }

    public static int getDayDiffer(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
        long endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }

    public static void main(String[] args) throws Exception {
//        Date date = getMinuteAgo(10);
//        System.out.println(format(date, "yyyy-MM-dd HH:mm:ss"));

//        String s = new DateUtil().toLocaleString().replace("-", "/");
//        System.out.println(s);
//        Date date = new Date();
//        Thread.sleep(3000);
//        Date date1 = new Date();
//        long l = diffSec(date, date1);
//        System.out.println(l);

        Date prase = prase("2020-06-02 00:00:00");

        String format = format(prase, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
    }
}
