package com.example.demo.onceTask.jupiter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.*;
import java.security.SecureRandom;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommonUtil {
    private static String BITS_TWO = "0.00";
    private static String BITS_FOUR = "0.00000";

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public CommonUtil() {
    }



    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        } else if (obj instanceof List) {
            return ((List) obj).size() <= 0;
        } else if (obj instanceof Map) {
            return ((Map) obj).size() <= 0;
        } else if (obj instanceof Set) {
            return ((Set) obj).size() <= 0;
        } else if (obj instanceof String) {
            return ((String) obj).trim().length() <= 0;
        } else if (obj instanceof StringBuffer) {
            return ((StringBuffer) obj).toString().length() <= 0;
        } else if (obj instanceof Double) {
            return ((Double) obj).isNaN();
        } else {
            return "".equals(obj.toString()) ? true : false;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static String formatMessage(String pattern, Object... objects) {
        String formatted = "";
        formatted = MessageFormat.format(pattern, objects);
        return formatted;
    }

    public static String getStackTrace(Throwable aThrowable) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

    public static String fixStringForCSVCell(String content) {
        return "\"".concat(content.replace("\"", "\"\"")).concat("\"");
    }

    public static String getNewCellForCSV() {
        return ",";
    }

    public static String getNewLineForCSV() {
        return "\r\n";
    }

    public static int convertPayCycle(String payCycle) {
        if ("Y".equals(payCycle)) {
            return 1;
        } else if ("S".equals(payCycle)) {
            return 2;
        } else if ("Q".equals(payCycle)) {
            return 4;
        } else {
            return "M".equals(payCycle) ? 12 : 0;
        }
    }

    public static String formatString(int m, BigDecimal deSource) {
        BigDecimal decValue = deSource;
        if (isEmpty(deSource)) {
            decValue = new BigDecimal(0);
        }

        return decValue.setScale(m, 4).toString();
    }

    public static BigDecimal formatBigDecimal(String deSource) {
        return isEmpty(deSource) ? BigDecimal.ZERO : new BigDecimal(deSource.replace(",", ""));
    }

    public static String round(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        BigDecimal deSource = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat(BITS_FOUR);
        double iRound = deSource.setScale(4, 4).doubleValue();
        return df.format(iRound);
    }

    public static String format(double data) {
        BigDecimal deSource = BigDecimal.valueOf(data);
        DecimalFormat df = new DecimalFormat(BITS_TWO);
        double iRound = deSource.setScale(2, 4).doubleValue();
        return df.format(iRound);
    }

    public static BigDecimal doubleToBigDecimal(double data) {
        return BigDecimal.valueOf(data);
    }

    public static String yield(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.000000000000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String yields(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.0000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String formatAcctate(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.00000000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String yields7(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.0000000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String accuir(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.00000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String yieldDec(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.000000000000");
        BigDecimal b1 = new BigDecimal(value);
        BigDecimal b2 = new BigDecimal(100);
        return df.format(b1.divide(b2));
    }

    public static String amt(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.0000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String qty(String data) {
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        DecimalFormat df = new DecimalFormat("0.0000");
        BigDecimal b1 = new BigDecimal(value);
        return df.format(b1);
    }

    public static String negativeRound(String data) {
        DecimalFormat df = new DecimalFormat(BITS_FOUR);
        String value = data;
        if (isEmpty(data)) {
            value = "0";
        }

        BigDecimal b1 = new BigDecimal(value);
        BigDecimal b2 = new BigDecimal("-1");
        return df.format(b1.multiply(b2));
    }

    public static String convertBasisFromAMSToAlg(String type, String base) {
        if ("sec".equals(type) && "ACTUAL".equals(base)) {
            return "A/A_B";
        } else if ("A360".equals(base)) {
            return "A/360";
        } else if ("A365".equals(base)) {
            return "A/365";
        } else {
            return "NL365".equals(base) ? "A/365F" : base;
        }
    }

    public static String compareValue(String value1, String value2) {
        if (value1 != null && value2 != null) {
            double parm1 = Double.valueOf(value1);
            double parm2 = Double.valueOf(value2);
            if (parm1 > parm2) {
                return "1";
            } else {
                return parm1 == parm2 ? "0" : "-1";
            }
        } else {
            return "";
        }
    }

    public static int compareStr(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return 1;
        } else if (str1 == null) {
            return 0;
        } else if (str2 == null) {
            return 0;
        } else {
            return str1.equals(str2) ? 1 : 0;
        }
    }

    public static int compareBigDecimal(BigDecimal b1, BigDecimal b2) {
        if (b1 == null && b2 == null) {
            return 0;
        }
        if (b1 == null || b2 == null) {
            return 0;
        }
        return b1.compareTo(b2);


    }





    public static Map<String, Object> objectFieldsMap(Object obj) {
        Map<String, Object> objMap = new HashMap(4);
        Field[] fs = obj.getClass().getDeclaredFields();
        String key = "";
        Object val = null;

        for (int i = 0; i < fs.length; ++i) {
            Field f = fs[i];

            try {
                f.setAccessible(true);
                key = f.getName();
                val = f.get(obj);
                objMap.put(key, val);
            } catch (IllegalArgumentException var8) {
                val = "属性转换异常类型：IllegalArgumentException";
                objMap.put(key, val);
            } catch (IllegalAccessException var9) {
                val = "属性转换异常类型：IllegalAccessException";
                objMap.put(key, val);
            }
        }

        return objMap;
    }

    public static Map<String, Object> objectMethodsMap(Object obj) {
        Map<String, Object> objMap = new HashMap(4);
        Method[] ms = obj.getClass().getMethods();
        String key = "";
        Object val = null;
        Object[] args = null;
        Method[] var6 = ms;
        int var7 = ms.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            Method m = var6[var8];
            key = m.getName();
            if (isSearch(key, "^get") && !"getClass".equals(key)) {
                try {
                    val = m.invoke(obj, (Object[]) args);
                    objMap.put(key.substring(3, 4).toLowerCase() + key.substring(4), val);
                } catch (IllegalArgumentException var11) {
                    val = "属性转换异常类型：IllegalArgumentException";
                    objMap.put(key, val);
                } catch (IllegalAccessException var12) {
                    val = "属性转换异常类型：IllegalAccessException";
                    objMap.put(key, val);
                } catch (InvocationTargetException var13) {
                    val = "属性转换异常类型：InvocationTargetException";
                    objMap.put(key, val);
                }
            }
        }

        return objMap;
    }

    public static int getIntByClassName(String className) {
        int strInt = 0;
        if (isNotEmpty(className)) {
            String storClassName = splitLast(className, ".").toLowerCase();
            char[] chars = storClassName.toCharArray();

            for (int i = 0; i < chars.length; ++i) {
                strInt += chars[i];
            }
        }

        return strInt;
    }

    public static boolean isNotEmpty(String s) {
        return null != s && s.length() > 0 && !"null".equalsIgnoreCase(s);
    }

    /**
     * @param splitor
     * @Description 字符串列表 拼接长字符串
     * @Author yujianwen
     * @Date 16:49 2021/12/14
     **/
    public static String list2String(List<String> stringList, String splitor) {
        StringBuilder sb = new StringBuilder();
        for (String str : stringList) {
            if (CommonUtil.isNotEmpty(str)) {
                sb.append(str).append(splitor);
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public static String list2StringWithLineBreak(List<String> stringList, String splitor) {
        StringBuilder sb = new StringBuilder();
        for (String str : stringList) {
            if (CommonUtil.isNotEmpty(str)) {
                sb.append(str).append(splitor).append(LINE_SEPARATOR);
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    public static String splitLast(String toSplit, String delimiter) {
        String[] s = delimitedListToStringArray(toSplit, delimiter);
        return s[s.length - 1];
    }

    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, (String) null);
    }

    public static BigDecimal getBigDec(BigDecimal big) {
        if (isEmpty(big)) {
            big = BigDecimal.ZERO;
        }

        return big;
    }



    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        } else if (delimiter == null) {
            return new String[]{str};
        } else {
            List<String> result = new ArrayList();
            int pos;
            if ("".equals(delimiter)) {
                for (pos = 0; pos < str.length(); ++pos) {
                    result.add(deleteAny(str.substring(pos, pos + 1), charsToDelete));
                }
            } else {
                pos = 0;

                int delPos;
                for (boolean var5 = false; (delPos = str.indexOf(delimiter, pos)) != -1; pos = delPos + delimiter
                        .length()) {
                    result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                }

                if (str.length() > 0 && pos <= str.length()) {
                    result.add(deleteAny(str.substring(pos), charsToDelete));
                }
            }

            return toStringArray(result);
        }
    }

    public static String[] toStringArray(Collection<String> collection) {
        return collection == null ? null : (String[]) ((String[]) collection.toArray(new String[collection.size()]));
    }

    public static String deleteAny(String inString, String charsToDelete) {
        if (hasLength(inString) && hasLength(charsToDelete)) {
            StringBuffer out = new StringBuffer();

            for (int i = 0; i < inString.length(); ++i) {
                char c = inString.charAt(i);
                if (charsToDelete.indexOf(c) == -1) {
                    out.append(c);
                }
            }

            return out.toString();
        } else {
            return inString;
        }
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static <T> String listToSqlStr(List<T> list) {
        if (isEmpty(list)) {
            return "";
        } else {
            String str = list.toString();
            str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
            str = "'" + str.replace(",", "','").replace(" ", "") + "'";
            return str;
        }
    }

    public static <T> String listToSqlStrNew(List<T> list) {
        if (isEmpty(list)) {
            return "";
        } else {
            String str = list.toString();
            str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
            str = "," + str.replace(" ", "") + ",";
            return str;
        }
    }

    public static <T> String listToSqlInt(List<T> list) {
        if (isEmpty(list)) {
            return "";
        } else {
            String str = list.toString();
            str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
            str = str.replace(" ", "");
            return str;
        }
    }

    public static boolean isSearch(String str, String pattern) {
        boolean r = false;
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.find()) {
            r = true;
        }

        return r;
    }

    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    public static String getStrAddZeroByLen(String strInit, int ilen) {
        String strRet = null;
        int itmp = strInit.length();
        if (itmp < ilen) {
            itmp = ilen - itmp;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < itmp; ++i) {
                sb.append("0");
            }

            strRet = sb.toString() + strInit;
        } else if (itmp == ilen) {
            strRet = strInit;
        }

        return strRet;
    }

    public static int parseInt(byte[] value) {
        int result = value[3] & 255;
        result |= value[2] << 8 & '\uff00';
        result |= value[1] << 16 & 16711680;
        result |= value[0] << 24 & -16777216;
        return result;
    }

    public static String appendLeftZero(int value, int length) {
        return String.format("%0" + length + "d", value);
    }

    public static String appendLeft4Zero(int value) {
        return String.format("%04d", value);
    }

    public static String appendLeft4Zero(String value) {
        int intValue = Integer.parseInt(value);
        return String.format("%04d", intValue);
    }






    public static BigDecimal add(BigDecimal add1, BigDecimal add2) {
        return getBigDec(add1).add(getBigDec(add2));
    }

    public static BigDecimal formatStrToBigDec(String big) {
        return isNotEmpty(big) ? new BigDecimal(big.replace(",", "")) : BigDecimal.ZERO;
    }

    public static String null2String(String value) {
        return value != null && !"null".equals(value) ? value : "";
    }

    public static boolean isZero(BigDecimal b) {
        return initValue(b).signum() == 0;
    }

    public static boolean isNull(BigDecimal big) {
        return null == big;
    }

    public static BigDecimal initValue(BigDecimal big) {
        return isNull(big) ? BigDecimal.ZERO : big;
    }

    /**
     * 字符串拼接
     *
     * @param name
     * @return
     */
    public static String concat(String name) {
        return "%" + name + "%";
    }



    /**
     * 精确除法
     *
     * @param scale 精度
     */
    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 列出时间段之内的所有日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getBetweenDate(Date startDate, Date endDate) {
        if (CommonUtil.isEmpty(startDate) || CommonUtil.isEmpty(endDate)) {
            return new ArrayList<Date>();
        }

        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(startDate);

        // 使用给定的 Date 设置此 Calendar 的时间
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(startDate);

        // 使用给定的 Date 设置此 Calendar 的时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);

        // 测试此日期是否在指定日期之后
        while (endDate.after(calStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calStart.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(calStart.getTime());
        }
        return dates;
    }

    /**
     * 获取某月的第一天，如06/01 00:00:00
     *
     * @return
     */
    public static Date getMonStartDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = instance.getTime();
        return startDate;
    }

    /**
     * 获取某月的最后一天，如获取6月最后一天,就是06/30 24:00:00 == 07/01 00:00:00   使用时应小于<，不能小于等于<=
     *
     * @return
     */
    public static Date getMonEndDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.add(Calendar.MONTH, 1);
        Date endDate = instance.getTime();
        return endDate;
    }

    /**
     * 获取该天的上一天, 如今天是06/01，那么就是获取05/31 24:00:00 = 06/01 00:00:00  使用时应小于<，不能小于等于<=
     *
     * @return
     */
    public static Date getYesterdayEndTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取该的上一天的开始，如今天是06/01 那么就是获取05/31 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getYesterdayStartTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        instance.add(Calendar.DATE, -1);
        return instance.getTime();
    }

    /**
     * 获取该天的前N天的结束, 如今天是06/01，那么就是获取前N=3天就是05/29 24:00:00 = 05/30 00:00:00
     *
     * @return
     */
    public static Date getBeforeNDaysEndTime(Date date, int N) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        instance.add(Calendar.DATE, -(N - 1));
        return instance.getTime();
    }

    /**
     * 获取该一天的前N天的开始，如今天是06/01 那么就是获取前N=3天就是05/29 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getBeforeNDaysStartTime(Date date, int N) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        //将小时至0
        instance.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        instance.set(Calendar.MINUTE, 0);
        //将秒至0
        instance.set(Calendar.SECOND, 0);
        //将毫秒至0
        instance.set(Calendar.MILLISECOND, 0);
        instance.add(Calendar.DATE, -N);
        return instance.getTime();
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    /**
     * 本地ip地址
     */
    public static final String LOCAL_IP = "127.0.0.1";
    /**
     * 默认ip地址
     */
    public static final String DEFAULT_IP = "0:0:0:0:0:0:0:1";
    /**
     * 默认ip地址长度
     */
    public static final int DEFAULT_IP_LENGTH = 15;



//    /**
//     * 获取本地ip地址
//     *
//     * @return
//     */
//    public static String getLocalIp() {
//        InetAddress iNet = null;
//        try {
//            iNet = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            Jlog.error("InetAddress getLocalHost error In HttpUtils getRealIpAddress: ", e);
//        }
//        if (iNet != null) {
//            return iNet.getHostAddress();
//        }
//        return null;
//    }





    // 获取第一个有效的IP地址
    private static String getAllValidIp() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface intf = interfaces.nextElement();
            if (intf.isLoopback() || intf.isVirtual() || !intf.isUp()) {
                continue;
            }

            Enumeration<InetAddress> addresses = intf.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                if (!addr.isLoopbackAddress() &&
                        addr.getHostAddress().contains(".") &&
                        !addr.getHostAddress().contains("::") &&
                        !addr.getHostAddress().contains("0:0:") &&
                        !addr.getHostAddress().contains("fe80")) {
                    return addr.getHostAddress();
                }
            }
        }
        return "127.0.0.1";
    }




    /**
     * 获取文件后缀 带上 .
     *
     * @return
     */
    public static String fileSuffix(String orginalName) {
        int index = orginalName.indexOf(".");
        if (index == -1) {
            return "";
        } else {
            return orginalName.substring(index);
        }
    }

    /**
     * 功能描述 文件夹后追加信息
     *
     * @param path 文件路径
     * @return java.lang.String
     * @author Lios
     * @date 2020/6/15
     */
    public static String fixFileSeperator(String path) {
        String pp = path.endsWith(File.separator) ? path :
                path + File.separator;
        return pp;
    }



    /**
     * 开始分页
     *
     * @param list
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static <T> List<T> startPage(List<T> list, Integer pageNum,
                                        Integer pageSize) {
        if (list == null) {
            return Collections.emptyList();
        }

        if (list.size() == 0) {
            return Collections.emptyList();
        }
        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount = 0;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        // 开始索引
        int fromIndex = 0;

        // 结束索引
        int toIndex = 0;

        if (pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
            if (toIndex > count) {
                toIndex = count;
            }
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        }
        List pageList = list.subList(fromIndex, toIndex);
        return pageList;
    }

    /**
     * 判断当前类型是否是 javabean
     *
     * @param type
     * @return
     */
    public static final boolean isJavaBean(Type type) {
        if (null == type) {
            throw new NullPointerException();
        }
        // 根据 getDeserializer 返回值类型判断是否为 java bean 类型
        return ParserConfig.global.getDeserializer(type) instanceof JavaBeanDeserializer;
    }



    private static String getSqlforDb2Oracle(String objectName, String sql, String colName, int colType) {
        if (Types.VARCHAR == colType) {
            if ("DEPMID".equals(colName)) {
                sql += "|| '(SELECT DEPMID FROM FLOW_DEPLOY where EFFECTFLAG = '''||'E'||''' and FLOWNAME = '" + "|| '''' || '" + objectName + "' || '''),'";
            } else if ("PROCDEFID".equals(colName)) {
                sql += "|| '(SELECT PROCDEFID FROM FLOW_DEPLOY where EFFECTFLAG = '''||'E'||''' and FLOWNAME = '" + "|| '''' ||'" + objectName + "' || '''),'";
            } else {
                sql += "||'''' ||" + "NVL(" + colName + ", '')" + "|| ''','";
            }
        } else if (Types.TIMESTAMP == colType || Types.DATE == colType) {
            sql += " ||'''' ||" + "SYSDATE" + "|| ''','";
        } else if (Types.DECIMAL == colType || Types.INTEGER == colType) {
            sql += "||'''' ||" + "NVL(" + colName + ", 0.0)" + "|| ''','";
        } else {
            sql += "||'''' ||" + "NVL(" + colName + ", null)" + "|| ''','";
        }
        return sql;
    }

    private static String getSqlforMysql(String objectName, String sql, String colName, int colType) {
        if (Types.VARCHAR == colType) {
            if ("DEPMID".equals(colName)) {
                sql += ",'(SELECT DEPMID FROM FLOW_DEPLOY where EFFECTFLAG = ''','E',''' and FLOWNAME = '" + ", '''' , '" + objectName + "' , '''),'";
            } else if ("PROCDEFID".equals(colName)) {
                sql += ",'(SELECT PROCDEFID FROM FLOW_DEPLOY where EFFECTFLAG = ''','E',''' and FLOWNAME = '" + ", '''' ,'" + objectName + "' , '''),'";
            } else {
                sql += ",'''' ," + "coalesce(" + colName + ", '')" + ", ''','";
            }
        } else if (Types.TIMESTAMP == colType || Types.DATE == colType) {
            sql += " ,'''' ," + "SYSDATE()" + ", ''','";
        } else if (Types.DECIMAL == colType || Types.INTEGER == colType) {
            sql += ",'''' ," + "coalesce(" + colName + ", 0)" + ", ''','";
        } else {
            sql += ",'''' ," + "coalesce(" + colName + ", '')" + ", ''','";
        }
        return sql;
    }



    /**
     * 判断字符串是否可以转化为json对象
     *
     * @param content
     * @return
     */
    public static boolean isJsonObject(String content) {
        if (CommonUtil.isEmpty(content))
            return false;
        try {
            JSONObject.parseObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 判断字符串是否可以转化为JSON数组
     *
     * @param content
     * @return
     */
    public static boolean isJsonArray(String content) {
        if (CommonUtil.isEmpty(content)) {
            return false;
        }
        try {
            JSONArray.parseArray(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
