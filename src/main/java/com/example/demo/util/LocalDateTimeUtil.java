package com.example.demo.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * <p></p>
 *
 * @author znz
 * @version 2018/9/30
 */
public final class LocalDateTimeUtil {


    public static final ZoneId ZONE_ID = ZoneId.of("UTC");

    public static long toLongTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0L;
        } else {
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
        }
    }

    public static long toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0L;
        } else {
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, df);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime secondsToLocalDateTime(Long second) {
        return Instant.ofEpochSecond(second).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime secondsToLocalDateTime(Long second, ZoneId zoneId) {
        return Instant.ofEpochSecond(second).atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(Long second) {
        return Instant.ofEpochMilli(second).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime getLocalDateTimeForUTC() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    /**
     * 获取时间间隔（毫秒）
     *
     * @return
     */
    public static long millisDiff(LocalDateTime lt, LocalDateTime gt) {
        Duration d = Duration.between(lt, gt);
        return d.toMillis();
    }

    /**
     * 获取时间间隔（秒）
     *
     * @return
     */
    public static long secondDiff(LocalDateTime lt, LocalDateTime gt) {
        Duration d = Duration.between(lt, gt);
        return d.getSeconds();
    }

    public static String toDateString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }


    public static String toDateStringYYYYMMDDmmhhss(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return LocalDateTimeUtil.toDateString(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String toDateString(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }


    public static void main(String[] args) {
        System.out.println(toLocalDateTime(1675153969L));
        System.out.println(toDateString(toLocalDateTime(new Date()), DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(toDateString(addDay(toLocalDateTime(new Date()), 7), DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(secondsToLocalDateTime(1650874305L));
        System.out.println(toDateString(LocalDateTime.now(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(getDayStart(LocalDateTime.now()));
    }


    public static LocalDateTime addDay(LocalDateTime startTime, int day) {
        return startTime.plusDays(day);
    }

    public static LocalDateTime addMinutes(LocalDateTime startTime, int min) {
        return startTime.plusMinutes(min);
    }

    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.with(LocalTime.MIN);
    }
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.with(LocalTime.MAX);
    }
    public static LocalDateTime getMonthStart() {
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth())), LocalTime.MIN);
    }
    public static LocalDateTime getMonthEnd() {
        return LocalDateTime.of(LocalDate.from(LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth())), LocalTime.MAX);
    }

    public static LocalDateTime getSeasonStart() {
        LocalDate now = LocalDate.now();
        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue());
        return LocalDateTime.of(LocalDate.of(now.getYear(), month, 1), LocalTime.MIN);
    }

    public static LocalDateTime getSeasonEnd() {
        LocalDate now = LocalDate.now();
        Month month = Month.of(now.getMonth().firstMonthOfQuarter().getValue()).plus(2L);
        return LocalDateTime.of(LocalDate.of(now.getYear(), month, month.length(now.isLeapYear())), LocalTime.MAX);
    }

}
