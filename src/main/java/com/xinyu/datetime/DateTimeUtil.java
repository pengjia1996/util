package com.xinyu.datetime;

import com.xinyu.util.QwyUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 时间日期操作公共类
 *
 * @author pengjia
 */
public class DateTimeUtil {


    /**
     * 时间格式map
     * <时间格式字符串,时间格式对象>
     */
    private static final Map<String, SimpleDateFormat> formatMap = new ConcurrentHashMap<>();


    /**
     * 获取日期格式对象
     *
     * @param pattern 描述日期和时间格式的模式
     * @return SimpleDateFormat  日期格式化对象
     * @author pengjia
     * @date 2021年4月14日 下午2:06:57
     */
    public static synchronized SimpleDateFormat getSimpleDateFormat(String pattern) {
        if (!formatMap.containsKey(pattern)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            formatMap.put(pattern, format);
        }
        return formatMap.get(pattern);
    }

    /**
     * 时间格式: yyyy-MM-dd HH:mm:ssSSS
     */
    public static synchronized SimpleDateFormat fmDateyyyyMMddHHmmssSSS() {
        final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
        if (!formatMap.containsKey(pattern)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            formatMap.put(pattern, format);
        }
        return formatMap.get(pattern);
    }


    /**
     * 将要要计算的时间为空添加amount天数,并设置时间为一天的开始时间
     * 例如:
     * <pre>
     * //假设new的实际是 2022-01-01 11:12:23.234
     * Date time = new Date();
     * //调用该方法后时间变成 2022-01-02 00:00:00.000
     * time=addTimeByDayAndReset(time,1);
     * </pre>
     *
     * @param date   要计算的时间为空
     * @param amount 数额,可以为负数
     * @return Date
     * @author 彭嘉
     * @date 2021年4月14日 下午12:02:48
     */
    public static Date addTimeByDayAndReset(Date date, int amount) {
        if (QwyUtil.isNullAndEmpty(date)) {
            System.err.println("要计算的时间为空");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将日期时间的(时间部分) 时分秒毫秒清零
     *
     * @return Date
     * @author 彭嘉
     * @date 2021年4月14日 下午4:24:06
     */
    public static Date hourMinSecResetZero(Date date) {

        return addTimeByDayAndReset(date, 0);
    }

    /**
     * 将日期时间的(时间部分) 时分秒重置为最大值
     *
     * @param date 日期
     * @return Date
     * @author 彭嘉
     * @date 2021年4月15日 上午9:32:07
     */
    public static Date hourMinSecResetMax(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();

    }

    /**
     * 获取两个时间范围之间的日期,不包边界时间日期
     *
     * @param minTime 时间范围(最小时间)
     * @param maxTime 时间范围(最大时间)
     * @return List<Date>
     * @author 彭嘉
     * @date 2021年4月14日 下午3:16:14
     */
    public static List<Date> getBetweenDates(Date minTime, Date maxTime) {
        return getBetweenDates(minTime, maxTime, false);
    }

    /**
     * 获取两个时间范围之间的日期
     *
     * @param minTime 时间范围(最小时间)
     * @param maxTime 时间范围(最大时间)
     * @param isFlag  是否包含边界时间
     * @return List<Date>
     * @author 彭嘉
     * @date 2021年4月14日 下午4:27:48
     */
    public static List<Date> getBetweenDates(Date minTime, Date maxTime, boolean isFlag) {
        List<Date> dateList = new ArrayList<>();
        try {
            if (!minTime.before(maxTime)) {
                System.err.println("开始时间大于或等于结束时间 startTime>=endTime");
                return Collections.emptyList();
            }
            Date startDate = null;
            Date endDate = null;
            if (isFlag) {
                startDate = addTimeByDayAndReset(minTime, -1);
                endDate = addTimeByDayAndReset(maxTime, 1);
            } else {
                startDate = addTimeByDayAndReset(minTime, 0);
                endDate = addTimeByDayAndReset(maxTime, 0);
            }
            // 开始日期加1
            startDate = addTimeByDayAndReset(startDate, 1);
            while (startDate.before(endDate)) {
                // 开始时间<结束时间
                dateList.add(startDate);
                startDate = addTimeByDayAndReset(startDate, 1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateList;
    }


}



