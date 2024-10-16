package com.example.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.MDC;

/**
 * Time - LEVEL - Thread - Package - Function - EntityId –TraceId - System - Type - Message
 * Time 由文件名和前置时间共同决定，文件名中包含YY-MM-DD，前置时间包括HH-MM-SS.sss
 * LEVEL - Thread - Package 在Appender中设置
 * Function: 第1个参数
 * EntityId: 第2个参数
 * TraceId: 第3个参数
 * System: 第4个参数
 * Type: 第5个参数
 * Message: 第6个参数
 *
 * 关于Type:
 *  0: Exception
 *  1: Params
 *  2: Return – Time Used
 *  3: Info
 */
public class LoggerUtil {
    private static ThreadLocal<String> entityId = new ThreadLocal<>();
    private static ThreadLocal<String> traceId = new ThreadLocal<>();
    private static ThreadLocal<String> system = new ThreadLocal<>();
    private static ThreadLocal<Long> time = new ThreadLocal<>();

    private LoggerUtil() {
        throw new IllegalStateException("Utility class for LogUtil");
    }

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggerUtil.class);

    public enum LogType {
        EXCEPTION,
        WARN,
        PARAMS,
        RETURN,
        INFO
    }

    public static void init() {
        String s = traceId.get();
        if (StringUtils.isEmpty(s)) {
            traceId.set("TRACE_ID:" + System.currentTimeMillis() + SaltUtil.generateSalt6());
        }
    }

    public static void init(String trace) {
        traceId.set("TRACE_ID:" + trace);
    }

    private static String genPrefix(String funcName, LogType logType) {
        String eid = entityId.get();
        String tid = traceId.get();
        String sys = system.get();
        return String.format(".%s | %s | %s | %s | %s | ",
                funcName, eid, tid, sys, logType.name());
    }

    /**
     * 交易rpg调用需要traceId从此获取
     * @return
     */
    public static String getTraceId(){
        return traceId.get();
    }

    public static void trace(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.trace( formatPrefix + format, arguments);
    }

    public static void debug(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.debug(formatPrefix + format, arguments);
    }

    public static void info(Logger log, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        infoCtx(log, "", formatPrefix, funcName + format, arguments);
    }

    public static void warn(Logger log, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        warnCtx(log, "", formatPrefix, format, arguments);
    }

    public static void error(Logger log, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        errorCtx(log, "", formatPrefix, funcName + format, arguments);
    }
    // -------------------------------  [INFO] ----------------------------------------------


    /**
     * 记录 info 日志 - (可记录事件)
     *
     * @param log       日志记录器
     * @param event     自定义事件(可中文，也可英文，枚举值)
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void info(Logger log, String event, String format, Object... arguments) {
        infoCtx(log, "", event, format, arguments);
    }


    /**
     * 记录 info 日志 - (可记录上下文和事件)
     *
     * @param log       日志记录器
     * @param ctx       上下文(bean， 实现toString JSON)
     * @param event     自定义事件(可中文，也可英文，枚举值)
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void infoCtx(Logger log, Object ctx, String event, String format, Object... arguments) {
        try {
            MDC.put("biz_monitor_ctx", ctx.toString());
            MDC.put("event", event);
            log.info(format, arguments);
        } catch (Exception e) {
            log.error("PrintInfoLog.exception", e);
        } finally {
            MDC.clear();
        }
    }

    // -------------------------------  [ERROR] ----------------------------------------------

    /**
     * 记录 error 日志 - (可记录事件)
     *
     * @param log       日志记录器
     * @param event     自定义事件(可中文，也可英文，枚举值)
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void error(Logger log, String event, String format, Object... arguments) {
        errorCtx(log, "", event, format, arguments);
    }

    /**
     * 记录 error 日志 - (可记录上下文和事件)
     *
     * @param log       日志记录器
     * @param ctx       上下文(bean， 实现toString JSON)
     * @param event     自定义事件(可中文，也可英文，枚举值)
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void errorCtx(Logger log, Object ctx, String event, String format, Object... arguments) {
        try {
            MDC.put("biz_monitor_ctx", ctx.toString());
            MDC.put("event", event);
            log.error(format, arguments);
        } catch (Exception e) {
            log.error("PrintErrorLog.exception", e);
        } finally {
            MDC.clear();
        }
    }


    // -------------------------------  [WARN] ----------------------------------------------

    public static void warn(Logger log, String event, String format, Object... arguments) {
        warnCtx(log, "", event, format, arguments);
    }

    /**
     * 记录 warn 日志
     *
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void warn(Logger log, String format, Object... arguments) {
        log.warn(format, arguments);
    }

    /**
     * 记录 info 日志 - (可记录上下文和事件)
     *
     * @param log       日志记录器
     * @param ctx       上下文(bean， 实现toString JSON)
     * @param event     自定义事件(可中文，也可英文，枚举值)
     * @param format    msg 格式
     * @param arguments msg 参数
     */
    public static void warnCtx(Logger log, Object ctx, String event, String format, Object... arguments) {
        try {
            MDC.put("biz_monitor_ctx", ctx.toString());
            MDC.put("event", event);
            log.warn(format, arguments);
        } catch (Exception e) {
            log.error("PrintInfoLog.exception", e);
        } finally {
            MDC.clear();
        }
    }

    /**
     * 清楚上下文数据
     */
    public static void end() {
        if (entityId != null) {
            entityId.remove();
            entityId = null;
        }

        if (traceId != null) {
            traceId.remove();
            traceId = null;
        }


        if (system != null) {
            system.remove();
            system = null;
        }

        if (time != null) {
            time.remove();
            time = null;
        }

    }
}
