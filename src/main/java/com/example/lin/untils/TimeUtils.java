package com.example.lin.untils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author LuLihong
 *
 */
public class TimeUtils {
    public static final DateFormat FMT_YMDHMS 	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat FMT_YMDHMS_S 	= new SimpleDateFormat("yyyyMMddHHmmss");
    public static final DateFormat FMT_YMDHM 		= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final DateFormat FMT_YMDHM_S 	= new SimpleDateFormat("yyyyMMddHHmm");
    public static final DateFormat FMT_YMD 		= new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat FMT_YM 		= new SimpleDateFormat("yyyy-MM");
    public static final DateFormat FMT_Y 			= new SimpleDateFormat("yyyy");
    public static final DateFormat FMT_HMS 		= new SimpleDateFormat("HH:mm:ss");
    public static final DateFormat FMT_HM 		= new SimpleDateFormat("HH:mm");

    /**
     * 一天的时长（毫秒）
     */
    public static final long DAY_LEN = 24 * 60 * 60 * 1000;
    /**
     * 一小时时长（毫秒）
     */
    public static final long HOUR_LEN = 60 * 60 * 1000;

    /**
     * 获取当前系统时间，格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String currTime() {
        return currTime(FMT_YMDHMS);
    }

    /**
     * 以指定的时间格式获取当前系统时间
     * @param dateFormatStr		时间格式，如:yyyy-MM-dd
     * @return
     */
    public static String currTime(String dateFormatStr) {
        DateFormat format = new SimpleDateFormat(dateFormatStr);
        return currTime(format);
    }

    /**
     * 格式化获取当前系统时间
     * @param dateFormat	时间格式
     * @return
     */
    public static String currTime(DateFormat dateFormat) {
        return dateFormat.format(new Date());
    }

    /**
     * 获取系统当前时间
     * @return  long型的时间
     */
    public static long currTimeLong() {
        return new Date().getTime();
    }

    /**
     * 获取long时间转换为Date
     * @param time
     * @return
     */
    public static Date toDate(long time) {
        return new Date(time);
    }
    /**
     * 将字符串时间转换为Date
     * @param timeStr   字符串时间，格式：yyyy-MM-dd HH:mm:ss
     * @return		Date
     * @throws ParseException
     */
    public static Date toDate(String timeStr) {
        return toDate(timeStr, FMT_YMDHMS);
    }

    /**
     * 将字符串时间转换为Date
     * @param timeStr		字符串时间
     * @param dateFormatStr  时间格式
     * @return
     * @throws ParseException
     */
    public static Date toDate(String timeStr, String dateFormatStr) {
        DateFormat format = new SimpleDateFormat(dateFormatStr);
        return toDate(timeStr, format);
    }

    /**
     * 将字符串时间转换为Date
     * @param timeStr		字符串时间
     * @param dateFormat	时间格式
     * @return
     */
    public static Date toDate(String timeStr, DateFormat dateFormat) {
        try {
            return dateFormat.parse(timeStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将Date转换为字符串时间， 格式：yyyy-MM-dd HH:mm:ss
     * @param time
     * @return
     */
    public static String toString(Date time) {
        return toString(time, FMT_YMDHMS);
    }
    /**
     * 将Date转换为字符串时间
     * @param time
     * @param dateFormatStr		时间格式
     * @return
     */
    public static String toString(Date time, String dateFormatStr) {
        DateFormat format = new SimpleDateFormat(dateFormatStr);
        return toString(time, format);
    }
    /**
     * 将Date转换为字符串时间
     * @param time
     * @param dateFormat		时间格式
     * @return
     */
    public static String toString(Date time, DateFormat dateFormat) {
        if (time == null) return "";
        return dateFormat.format(time);
    }

    /**
     * 获取当前时间secDiff秒之前的时间。
     * @param secDiff
     * @return
     */
    public static long beforeSecond(long secDiff) {
        return System.currentTimeMillis() / 1000 - secDiff;
    }


    /**
     * 获取当前java.sql.Timestamp时间
     * @return
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 下一天
     * @param d
     * @return
     */
    public static Date nextDay(Date d) {
        long nextDay = d.getTime() + DAY_LEN;
        return new Date(nextDay);
    }


    /**
     * 计算下一个固定时间点。
     * @param time   时间点，格式：11:30
     * @return
     */
    public static Date nextFixedTime(String time) {
        String dateStr = currTime("yyyy-MM-dd") + " " + time;
        Date dateTime = toDate(dateStr, FMT_YMDHM);
        if (dateTime.getTime() < System.currentTimeMillis()) {
            dateTime = nextDay(dateTime);
        }
        return dateTime;
    }

    /**
     * 下一个整点时间
     * @return
     */
    public static Date nextHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }

    /**
     * 在当前时间上添加或减去指定的天数
     *
     * @param amount    大于0为增加，小于0为减少
     * @return
     */
    public static Date addDay(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    public static Date addMonth(int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }

    public static Date addSecond(Date date, long amount) {
        long time = date.getTime() + amount * 1000;
        return new Date(time);
    }

    /******  add by xuyechun start  ******/
    /**
     * 时间类型
     */
    public static enum TimeType {
        /**年*/
        YEAR,
        /**月*/
        MONTH,
        /**日*/
        DAY,
        /**小时*/
        HOUR,
        /**分钟*/
        MINUTE,
        /**秒*/
        SECOND
    }
    public static final DateFormat FMT_YMDHMSS 		= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    public static final DateFormat FMT_YMDHMSS_S 	= new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static final DateFormat FMT_YMDH 		= new SimpleDateFormat("yyyy-MM-dd HH");
    public static final DateFormat FMT_YMDH_S 		= new SimpleDateFormat("yyyyMMddHH");
    public static final DateFormat FMT_YMD_S 		= new SimpleDateFormat("yyyyMMdd");
    public static final DateFormat FMT_YM_S 		= new SimpleDateFormat("yyyyMM");
    /**
     * 一秒时长（毫秒）
     */
    public static final long SECOND_LEN = 1000;

    /**
     * 一分钟时长（毫秒）
     */
    public static final long MINUTE_LEN = 60 * SECOND_LEN;

    /**
     * 计算两个日期之间相差的时间(初略计算)
     *  YEAR：指定年的开始时间（跨年视为1年）
     *  MONTH：指定月开始时间（跨月视为1月）
     *  DAY：指定日的开始时间（跨月视为1日）
     *  HOUR：指定小时的开始时间（跨小时视为1小时）
     *  MINUTE：指定分钟的开始时间（跨小分钟为1分钟）
     *  SECOND：指定秒的开始时间（跨小秒为1秒）
     * @param beginDate 开始的时间
     * @param endDate 结束的时间
     * @param timeType 返回时间类型(YEAR，MONTH，DAY，HOUR，MINUTE，SECOND)
     * @return 相差时间
     */
    public static long timeBetween(Date beginDate, Date endDate, TimeType timeType)
    {
        Date fromDate = null;
        Date toDate = null;
        //根据时间类型格式化日期后再计算
        switch (timeType){
            case YEAR:
                fromDate = toDate(toString(beginDate, FMT_Y), FMT_Y);
                toDate = toDate(toString(endDate, FMT_Y), FMT_Y);
                break;
            case MONTH:
                fromDate = toDate(toString(beginDate, FMT_YM), FMT_YM);
                toDate = toDate(toString(endDate, FMT_YM), FMT_YM);
                break;
            case DAY:
                fromDate = toDate(toString(beginDate, FMT_YMD), FMT_YMD);
                toDate = toDate(toString(endDate, FMT_YMD), FMT_YMD);
                break;
            case HOUR:
                fromDate = toDate(toString(beginDate, FMT_YMDH), FMT_YMDH);
                toDate = toDate(toString(endDate, FMT_YMDH), FMT_YMDH);
                break;
            case MINUTE:
                fromDate = toDate(toString(beginDate, FMT_YMDHM), FMT_YMDHM);
                toDate = toDate(toString(endDate, FMT_YMDHM), FMT_YMDHM);
                break;
            case SECOND:
                fromDate = toDate(toString(beginDate, FMT_YMDHMS), FMT_YMDHMS);
                toDate = toDate(toString(endDate, FMT_YMDHMS), FMT_YMDHMS);
                break;
            default:
                fromDate = toDate(toString(beginDate, FMT_YMDHMSS), FMT_YMDHMSS);
                toDate = toDate(toString(endDate, FMT_YMDHMSS), FMT_YMDHMSS);
                break;
        }
        //开始日期或结束日期一方不是有效日期的时候，返回0
        if(beginDate == null || endDate == null){
            return 0;
        }
        //计算时间差
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(fromDate);
        long timeFrom = calFrom.getTimeInMillis();
        Calendar calTo= Calendar.getInstance();
        calTo.setTime(toDate);
        long timeTo = calTo.getTimeInMillis();
        long time = timeTo-timeFrom;
        long result = 0;
        switch (timeType){
            case YEAR:
                //因为1年的时间不确认，对年进行计算
                result = calTo.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
                break;
            case MONTH:
                //因为1个月的时间不确定，直接计算
                int year = calTo.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
                int month = calTo.get(Calendar.MONTH) - calFrom.get(Calendar.MONTH);
                result = year * 12 + month;
                break;
            case DAY:
                result = time/DAY_LEN;
                break;
            case HOUR:
                result = time/HOUR_LEN;
                break;
            case MINUTE:
                result = time/MINUTE_LEN;
                break;
            case SECOND:
                result = time/SECOND_LEN;
                break;
            default:
                result = time;
                break;
        }

        return result;
    }

    /**
     * 计算两个日期之间相差的时间(准确计算)
     *  YEAR：指定年的开始时间（跨年但不满1年视为0年）
     *  MONTH：指定月开始时间（跨年但不满1月视为0月）
     *  DAY：指定日的开始时间（跨日但不满1日视为0日）
     *  HOUR：指定小时的开始时间（跨小时但不满1小时视为0小时）
     *  MINUTE：指定分钟的开始时间（跨分钟但不满1分钟视为0分钟）
     *  SECOND：指定秒的开始时间（跨秒但不满1秒视为0秒）
     * @param beginDate 开始的时间
     * @param endDate 结束的时间
     * @param timeType 返回时间类型(YEAR，MONTH，DAY，HOUR，MINUTE，SECOND)
     * @return 相差时间
     */
    public static long timeBetweenDown(Date beginDate, Date endDate, TimeType timeType)
    {

        //开始日期或结束日期一方不是有效日期的时候，返回0
        if(beginDate == null || endDate == null){
            return 0;
        }
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(beginDate);
        long timeFrom = calFrom.getTimeInMillis();
        Calendar calTo= Calendar.getInstance();
        calTo.setTime(endDate);
        long timeTo = calTo.getTimeInMillis();
        long time = timeTo-timeFrom;
        long result = 0;
        switch (timeType){
            case YEAR:
                //概算相差多少年
                int year = calTo.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
                //开始时间增加相差年数
                calFrom.add(Calendar.YEAR, year);
                //比较时间
                if(calFrom.compareTo(calTo) <= 0){
                    //开始时间小于等于结束时间时，返回相差年数
                    result = year;
                }else{
                    //开始时间大于结束时间时，返回相差年数-1
                    result = year - 1;
                }
                break;
            case MONTH:
                //概算相差月数
                int year2 = calTo.get(Calendar.YEAR) - calFrom.get(Calendar.YEAR);
                int month = calTo.get(Calendar.MONTH) - calFrom.get(Calendar.MONTH);
                month = year2 * 12 + month;
                //开始时间增加相差月数
                calFrom.add(Calendar.MONTH, month);
                //比较时间
                if(calFrom.compareTo(calTo) <= 0){
                    //开始时间小于等于结束时间时，返回相差月数
                    result = month;
                }else{
                    //开始时间大于结束时间时，返回相差月数-1
                    result = month - 1;
                }
                break;
            case DAY:
                result = time/DAY_LEN;
                break;
            case HOUR:
                result = time/HOUR_LEN;
                break;
            case MINUTE:
                result = time/MINUTE_LEN;
                break;
            case SECOND:
                result = time/SECOND_LEN;
                break;
            default:
                result = time;
                break;
        }

        return result;
    }

    /**
     * 取指定时间的开始
     *  YEAR：指定年的开始时间
     *  MONTH：指定月开始时间
     *  DAY：指定日的开始时间
     *  HOUR：指定小时的开始时间
     *  MINUTE：指定分钟的开始时间
     *  SECOND：指定秒的开始时间
     * @param date 时间
     * @param timeType 返回时间类型(YEAR，MONTH，DAY，HOUR，MINUTE，SECOND)
     * @return 开始时间
     */
    public static Date getTimeBegin(Date date, TimeType timeType)
    {
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        switch (timeType){
            case YEAR:
                cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);
                break;
            case MONTH:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
                break;
            case DAY:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                break;
            case HOUR:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), 0, 0);
                break;
            case MINUTE:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 0);
                break;
            case SECOND:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                break;
            default:
                break;
        }

        return cal.getTime();

    }

    /**
     * 取指定时间的结束(年：指定年的第一天，月：指定月第一天，周：指定周第一天，日：指定日的开始时间，小时：指定小时的开始分钟，分钟：指定分钟的开始秒，秒：指定秒的开始毫秒)
     *  YEAR：指定年的结束时间
     *  MONTH：指定月的结束时间
     *  DAY：指定日的结束时间
     *  HOUR：指定小时的结束时间
     *  MINUTE：指定分钟的结束时间
     *  SECOND：指定秒的结束时间
     * @param date 时间
     * @param timeType 返回时间类型(YEAR，MONTH，DAY，HOUR，MINUTE，SECOND)
     * @return 结束时间
     */
    public static Date getTimeEnd(Date date, TimeType timeType)
    {
        if(date == null){
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 999);
        switch (timeType){
            case YEAR:
                //明年的第一天，再减去一天
                cal.set(cal.get(Calendar.YEAR) + 1, 0, 1, 23, 59, 59);
                cal.add(Calendar.DAY_OF_YEAR, -1);
                break;
            case MONTH:
                //下个月的第一天，再减去一天
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 23, 59, 59);
                cal.add(Calendar.DAY_OF_YEAR, -1);
                break;
            case DAY:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
                break;
            case HOUR:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), 59, 59);
                break;
            case MINUTE:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 59);
                break;
            case SECOND:
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                break;
            default:
                break;
        }

        return cal.getTime();

    }

    /**
     * 获取两个日期之前所有的日期(包含开始和结束)
     * YEAR：获取年
     * MONTH：获取月
     * DAY:获取日
     * HOUR：获取小时
     * MINUTE:获取分钟
     * SECOND：获取秒
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @param timeType 获取的时间类型
     * @return 两个日期之前所有的日期
     */
    public static List<Date> getBetweenDates(Date beginDate,Date endDate, TimeType timeType){

        List<Date> result = new ArrayList<Date>();
        Date dtStart = null;
        Date dtEnd = null;
        switch (timeType){
            case YEAR:
                dtStart = toDate(toString(beginDate, FMT_Y), FMT_Y);
                dtEnd = toDate(toString(endDate, FMT_Y), FMT_Y);
                break;
            case MONTH:
                dtStart = toDate(toString(beginDate, FMT_YM), FMT_YM);
                dtEnd = toDate(toString(endDate, FMT_YM), FMT_YM);
                break;
            case DAY:
                dtStart = toDate(toString(beginDate, FMT_YMD), FMT_YMD);
                dtEnd = toDate(toString(endDate, FMT_YMD), FMT_YMD);
                break;
            case HOUR:
                dtStart = toDate(toString(beginDate, FMT_YMDH), FMT_YMDH);
                dtEnd = toDate(toString(endDate, FMT_YMDH), FMT_YMDH);
                break;
            case MINUTE:
                dtStart = toDate(toString(beginDate, FMT_YMDHM), FMT_YMDHM);
                dtEnd = toDate(toString(endDate, FMT_YMDHM), FMT_YMDHM);
                break;
            case SECOND:
                dtStart = toDate(toString(beginDate, FMT_YMDHMS), FMT_YMDHMS);
                dtEnd = toDate(toString(endDate, FMT_YMDHMS), FMT_YMDHMS);
                break;
            default:
                dtStart = toDate(toString(beginDate, FMT_YMDHMSS), FMT_YMDHMSS);
                dtEnd = toDate(toString(endDate, FMT_YMDHMSS), FMT_YMDHMSS);
                break;
        }
        if(dtStart == null || dtEnd == null){
            return result;
        }else if(dtStart.compareTo(dtEnd) > 0){
            return result;
        }else if(dtStart.compareTo(dtEnd) == 0){
            result.add(dtStart);
            return result;
        }
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(dtStart);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(dtEnd);
        do{
            result.add(tempStart.getTime());
            switch (timeType){
                case YEAR:
                    tempStart.add(Calendar.YEAR, 1);
                    break;
                case MONTH:
                    tempStart.add(Calendar.MONTH, 1);
                    break;
                case DAY:
                    tempStart.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case HOUR:
                    tempStart.add(Calendar.HOUR, 1);
                    break;
                case MINUTE:
                    tempStart.add(Calendar.MINUTE, 1);
                    break;
                case SECOND:
                    tempStart.add(Calendar.SECOND, 1);
                    break;
                default:
                    tempStart.add(Calendar.MILLISECOND, 1);
                    break;
            }
        }while(tempStart.compareTo(tempEnd) <= 0);
        return result;
    }

    /**
     * 获取当前星期
     * @param date 日期
     * @return 星期几
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 比较时间大小(左边和右边比较)，如果左边日历小于、等于、大于右边，则对应返回-1、0、1
     * @param dateLeft 左边的日期
     * @param dateRight 右边的日期
     * @return 比较结果 相等返回0，左边大于右边  返回1  小于返回-1
     */
    public static int compareDate(Date dateLeft,Date dateRight){
        if(dateLeft == null && dateRight == null){
            return 0;
        }else if(dateLeft != null && dateRight == null){
            return 1;
        }else if(dateLeft == null && dateRight != null){
            return -1;
        }

        return dateLeft.compareTo(dateRight);
    }

    /**
     * 时间比较（判断某个日期是否在一个区间内)
     * 日期有一个为空的时候返回false
     * @param checkDate 校验日期
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 比较结果
     */
    public static boolean dateBetweenCompare(Date checkDate, Date beginDate, Date endDate) {

        //日期有一个为空的时候返回false
        if(checkDate == null || beginDate == null || endDate == null){
            return false;
        }
        return (checkDate.getTime() - beginDate.getTime()) >= 0
                && (checkDate.getTime() - endDate.getTime() <= 0) ? true : false;

    }

    /**
     * 判断是否是周末
     * @param date 日期
     * @return 判断结果
     */
    public static boolean isWeekend(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(week ==6 || week==0){//0代表周日，6代表周六
            return true;
        }
        return false;
    }


    public static void main(String[] args) {

        Date beginDate = toDate("2017-11-26 16:11:12:011", FMT_YMDHMSS);
        Date endDate = toDate("2018-04-25 15:30:11:091", FMT_YMDHMSS);
        System.out.println("-------timeBetween--------");
        System.out.println(timeBetween(beginDate,endDate,TimeType.YEAR));
        System.out.println(timeBetween(beginDate,endDate,TimeType.MONTH));
        System.out.println(timeBetween(beginDate,endDate,TimeType.DAY));
        System.out.println(timeBetween(beginDate,endDate,TimeType.HOUR));
        System.out.println(timeBetween(beginDate,endDate,TimeType.MINUTE));
        System.out.println(timeBetween(beginDate,endDate,TimeType.SECOND));

        System.out.println("-------timeBetweenDown--------");
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.YEAR));
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.MONTH));
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.DAY));
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.HOUR));
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.MINUTE));
        System.out.println(timeBetweenDown(beginDate,endDate,TimeType.SECOND));
        System.out.println("-------getTimeBegin--------");
        System.out.println(getTimeBegin(toDate("2017", FMT_Y),TimeType.YEAR));
        System.out.println(getTimeBegin(beginDate,TimeType.YEAR));
        System.out.println(getTimeBegin(beginDate,TimeType.MONTH));
        System.out.println(getTimeBegin(beginDate,TimeType.DAY));
        System.out.println(getTimeBegin(beginDate,TimeType.HOUR));
        System.out.println(getTimeBegin(beginDate,TimeType.MINUTE));
        System.out.println(getTimeBegin(beginDate,TimeType.SECOND));
        System.out.println("-------getTimeEnd--------");
        System.out.println(getTimeEnd(beginDate,TimeType.YEAR));
        System.out.println(getTimeEnd(beginDate,TimeType.MONTH));
        System.out.println(getTimeEnd(beginDate,TimeType.DAY));
        System.out.println(getTimeEnd(beginDate,TimeType.HOUR));
        System.out.println(getTimeEnd(beginDate,TimeType.MINUTE));
        System.out.println(getTimeEnd(beginDate,TimeType.SECOND));
        System.out.println("-------getBetweenDates--------");
        System.out.println(getBetweenDates(beginDate, endDate,TimeType.YEAR));
        System.out.println(getBetweenDates(beginDate, endDate,TimeType.MONTH));
        Date beginDay = toDate("2018-04-29 16:11:12:011", FMT_YMDHMSS);
        Date endDay = toDate("2018-05-03 15:30:11:091", FMT_YMDHMSS);
        System.out.println(getBetweenDates(beginDay, endDay,TimeType.DAY));
        Date beginHour = toDate("2018-04-24 23:11:12:011", FMT_YMDHMSS);
        Date endHour = toDate("2018-04-25 15:30:11:091", FMT_YMDHMSS);
        System.out.println(getBetweenDates(beginHour, endHour,TimeType.HOUR));
        Date beginMin = toDate("2018-04-25 14:58:12:011", FMT_YMDHMSS);
        Date endMin = toDate("2018-04-25 15:03:11:091", FMT_YMDHMSS);
        System.out.println(getBetweenDates(beginMin, endMin,TimeType.MINUTE));
        Date beginSec = toDate("2018-04-25 14:29:59:011", FMT_YMDHMSS);
        Date endSec = toDate("2018-04-25 15:30:11:091", FMT_YMDHMSS);
        System.out.println(getBetweenDates(beginSec, endSec,TimeType.SECOND));
        System.out.println(getDayOfWeek(new Date()));
        System.out.println(compareDate(beginDate, endDate));
        System.out.println(compareDate(endDate, beginDate));
        Date checkDate1 = toDate("2017-11-25 16:11:12:011", FMT_YMDHMSS);
        System.out.println(dateBetweenCompare(checkDate1, beginDate, endDate));
        Date checkDate2 = toDate("2017-11-27 16:11:12:011", FMT_YMDHMSS);
        System.out.println(dateBetweenCompare(checkDate2, beginDate, endDate));

        System.out.println(isWeekend(new Date()));
        System.out.println(isWeekend(addDay(2)));
        System.out.println(isWeekend(addDay(3)));
        System.out.println(isWeekend(addDay(4)));
        System.out.println(isWeekend(addDay(5)));


    }
    /******  add by xuyechun end  ******/


}
