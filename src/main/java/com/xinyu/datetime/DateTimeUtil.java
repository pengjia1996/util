package com.xinyu.datetime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.xinyu.util.QwyUtil;

/**   
 * 时间日期操作公共类   
 * @author:彭嘉
 * @date:2020年11月3日 上午9:08:07  
 */
public class DateTimeUtil {

    private static final Logger logger = Logger.getLogger(DateTimeUtil.class);

    private static Map<String, SimpleDateFormat> map = new ConcurrentHashMap<String, SimpleDateFormat>();


    /**
     * 获取日期格式对象
     * @author 彭嘉
     * @date 2021年4月14日 下午2:06:57
     * @param pattern  描述日期和时间格式的模式
     * @return SimpleDateFormat  日期格式化对象
     */
    public static synchronized SimpleDateFormat getSimpleDateFormat(String pattern) {
        if (!map.containsKey(pattern)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            map.put(pattern, format);
        }
        return map.get(pattern);
    }

    /**
     * 时间格式: yyyy-MM-dd HH:mm:ssSSS
     */
    public static synchronized SimpleDateFormat fmDateyyyyMMddHHmmssSSS() {
        String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
        if (!map.containsKey(pattern)) {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            map.put(pattern, format);
        }
        return map.get(pattern);
    }


    /**
     * 
     * 将要要计算的时间为空添加amount天数,并设置时间为一天的开始时间
     * 例如: 
     * <pre>
     * //假设new的实际是 2022-01-01 11:12:23.234
     * Date time = new Date();
     * //调用该方法后时间变成 2022-01-02 00:00:00.000
     * time=addTimeByDayAndReset(time,1);
     * </pre>
     * @author 彭嘉
     * @date 2021年4月14日 下午12:02:48
     * @param date 要计算的时间为空
     * @param amount 数额,可以为负数
     * @return Date
     * 
     */
    public static Date addTimeByDayAndReset(Date date, int amount) {
        if (QwyUtil.isNullAndEmpty(date)) {
            logger.info("要计算的时间为空");
            return null;
        }
        // SimpleDateFormat simpleDateFormat = fmDateyyyyMMddHHmmssSSS();
        // String dateStr = simpleDateFormat.format(date);
        // logger.info("into addTimeByDayAndReset(date,amount),params:date=" + dateStr + ",amount=" + amount);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();
        // logger.info("计算后的时间date=" + simpleDateFormat.format(time));
        return time;
    }

    /**
     * 将日期时间的(时间部分) 时分秒毫秒清零    
     * @author 彭嘉
     * @date 2021年4月14日 下午4:24:06
     * @return Date
     */
    public static Date hourMinSecResetZero(Date date) {

        return addTimeByDayAndReset(date, 0);
    }

    /**
     * 将日期时间的(时间部分) 时分秒重置为最大值
     * @author 彭嘉
     * @date 2021年4月15日 上午9:32:07
     * @param date
     * @return Date
     */
    public static Date hourMinSecResetMax(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date time = calendar.getTime();
        // logger.info("计算后的时间date=" + simpleDateFormat.format(time));
        return time;

    }

    /**
     * 获取两个时间范围之间的日期,不包边界时间的日期
     * @author 彭嘉
     * @date 2021年4月14日 下午3:16:14    
     * @param minTime 时间范围(最小时间)
     * @param maxTime 时间范围(最大时间)
     * @return List<Date> 
     */
    public static List<Date> getBetweenDates(Date minTime, Date maxTime) {
        return getBetweenDates(minTime, maxTime, false);
    }

    /**
     * 
     * @author 彭嘉
     * @date 2021年4月14日 下午4:27:48
     * @description 获取两个时间范围之间的日期  
     * @param minTime 时间范围(最小时间)
     * @param maxTime 时间范围(最大时间)
     * @param isFlag  是否包含边界时间
     * @return List<Date>
     * 
     */
    public static List<Date> getBetweenDates(Date minTime, Date maxTime, boolean isFlag) {
        List<Date> dateList = new ArrayList<Date>();
        try {
            if (!minTime.before(maxTime)) {
                logger.info("开始时间大于或等于结束时间 startTime>=endTime");
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
            logger.error("getBetweenDates方法出错", e);
        }
        return dateList;
    }



}



