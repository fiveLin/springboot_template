package com.example.lin.untils;


import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author LuLihong
 * @date 2013-4-27
 */
public class StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    private final static int[] LI_SECPOSVALUE = {1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5590};

    private final static String[] LC_FIRST_LETTER = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "w", "x", "y", "z"};

    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");

    private static final Pattern CHINESE_NUMBER_PATTERN = Pattern.compile("[\\u0030-\\u0039]+");


    /**
     * 是否为空字符串
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        return s == null || s.length() == 0 || "null".equalsIgnoreCase(s);
    }

    /**
     * 是否为空字符串
     *
     * @param s
     * @return
     */
    public static String replaceEmpty(String s, String askForEmpty){
        return (s == null || s.length() == 0 || "null".equalsIgnoreCase(s)) ? askForEmpty : s;
    }

    /**
     * 非空字符串
     *
     * @param s
     * @return
     */
    public static boolean notEmpty(String s){
        return !isEmpty(s);
    }

    public static boolean isBlank(String s){
        return org.apache.commons.lang3.StringUtils.isBlank(s);
    }

    public static boolean notBlank(String s){
        return org.apache.commons.lang3.StringUtils.isNotBlank(s);
    }

    /**
     * 去除字符串中分隔符之间重复的内容
     *
     * @param str 元字符串
     * @param fgf 分隔符
     * @return
     */
    public static String removeDuplicateChars(String str, String fgf){
        String[] strs = str.split(fgf);
        List<String> list = new ArrayList<String>();
        StringBuffer buffer = new StringBuffer();

        for(String s : strs) {
            if(!list.contains(s)) {
                list.add(s);
                buffer.append(s + fgf);
            }
        }
        return buffer.subSequence(0, buffer.length() - 1).toString();
    }

    public static String fmtEmpty(String s){
        return isEmpty(s) ? "" : s.trim();
    }

    public static boolean isNumber(String s){
        return notEmpty(s) && s.matches("\\d+");
    }

    /**
     * 是否属于回执编码，4位数字。
     *
     * @param s
     * @return
     */
    public static boolean isReceiptNumber(String s){
        s = s.trim();
        return notEmpty(s) && s.matches("\\d{4}");
    }

    public static int toInt(String s){
        return toInt(s, -1);
    }

    public static int toInt(String s, int defaultValue){
        if(isNumber(s)) {
            return Integer.parseInt(s);
        }
        return defaultValue;
    }

    public static boolean toBoolean(String s){
        return toBoolean(s, false);
    }

    public static boolean toBoolean(String s, boolean defaultValue){
        if(isEmpty(s)) {
            return defaultValue;
        }

        return "true".equalsIgnoreCase(s) || "1".equals(s);
    }

    public static long toLong(String s){
        return toLong(s, 0);
    }

    public static long toLong(String s, long defaultValue){
        if(isEmpty(s)) {
            return defaultValue;
        }

        if(isNumber(s)) {
            return Long.parseLong(s);
        }

        return defaultValue;
    }

    public static double toDouble(String s, double defaultValue){
        if(isEmpty(s)) {
            return defaultValue;
        }

        double ret = defaultValue;
        try {
            ret = Double.parseDouble(s);
        } catch(Exception e) {
            ret = defaultValue;
        }

        return ret;
    }


    /**
     * 计算字符串字节长度
     * <p>由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。</p>
     *
     * @param s
     * @return
     */
    public static int calByteLen(String s){
        int length = 0;
        for(int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 计算字符串字节长度
     *
     * @param s
     * @return
     */
    public static int calByteLenRegex(String s){
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        return s.length();
    }

    public static String[] stringToArr(String s, String regex){
        if(isEmpty(s)) {
            return new String[]{};
        }
        return s.split(regex);
    }

    public static int[] toIntArray(String s, String regex){
        if(isEmpty(s)) {
            return new int[]{};
        }
        String[] arr = s.split(regex);
        int[] intArr = new int[arr.length];
        int index = 0;
        for(String str : arr) {
            intArr[index++] = toInt(str, 0);
        }

        return intArr;

    }

    /**
     * 包装XML内容，避免特殊字符导致错误。
     *
     * @param content
     * @return
     */
    public static String wrapXMLContent(String content){
        if(content == null || content.trim().isEmpty()) {
            return "";
        }
        content = content.trim();
        int len = content.length();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            char ch = content.charAt(i);
            if(!isSpecialChar(ch)) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static String format(String f, String[] params){
        if(isEmpty(f)) {
            return "";
        }
        if(params == null || params.length == 0) {
            return f;
        }

        int index = f.indexOf("%s");
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while(index >= 0) {
            String param = (count < params.length) ? params[count++] : params[params.length - 1];
            builder.append(f, 0, index).append(param);
            f = f.substring(index + 2);
            index = f.indexOf("%s");
        }
        builder.append(f);
        return builder.toString();
    }


    public static boolean isSpecialChar(char ch){
        int ci = ch;
        return Character.isISOControl(ci) && ci != 9 && ci != 10 && ci != 13;
    }

    /**
     * 获取汉字的首字母
     *
     * @param str
     * @return
     */
    public static String getAllFirstLetter(String str){
        if(str == null || str.trim().length() == 0) {
            return "";
        }
        String strTemp = "";
        for(int i = 0; i < str.length(); i++) {
            strTemp = strTemp + getFirstLetter(strTemp.substring(i, i + 1));
        }
        return strTemp;
    }

    public static String getFirstLetter(String chinese){
        if(chinese == null || chinese.trim().length() == 0) {
            return "";
        }
        chinese = conversionStr(chinese, "GB2312", "ISO8859-1");
        if(chinese.length() > 1) {
            int liSectorCode = (int) chinese.charAt(0);
            int liPositionCode = (int) chinese.charAt(1);
            liSectorCode = liSectorCode - 160;
            liPositionCode = liPositionCode - 160;
            int liSecPosCode = liSectorCode * 100 + liPositionCode;
            if(liSecPosCode > 1600 && liSecPosCode < 5590) {
                for(int i = 0; i < 23; i++) {
                    if(liSecPosCode >= LI_SECPOSVALUE[i] && liSecPosCode < LI_SECPOSVALUE[i + 1]) {
                        chinese = LC_FIRST_LETTER[i];
                        break;
                    }
                }
            } else {
                chinese = conversionStr(chinese, "ISO8859-1", "GB2312");
                chinese = chinese.substring(0, 1);
            }
        }
        return chinese;
    }

    private static String conversionStr(String str, String charsetName, String toCharsetName){
        try {
            str = new String(str.getBytes(charsetName), toCharsetName);
        } catch(UnsupportedEncodingException ex) {
            System.out.println("×?·?′?±à??×a??òì3￡￡o" + ex.getMessage());
        }
        return str;
    }

    public static String fmtEmpty(String source, String aim){
        if(isEmpty(source)) {
            return aim;
        } else {
            return source;
        }
    }

    private StringUtils(){
    }

    public static String isEmpty(Object replace, String s){
        return s == null || s.length() == 0 || "null".equalsIgnoreCase(s) ? replace + "" : s;
    }


    public static String excelLocation(int rowNum, int cellNum){
        if(cellNum > 25) {
            return null;
        }
        if(cellNum < 0) {
            return null;
        }
        String letter = ((char) (cellNum + (int) 'A')) + "";
        return letter + (rowNum + 1);
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     *
     * @param no
     * @return
     */
    public static String autoGenericCode(int no, int num){
        String result = "";
        // 保留num的位数 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        result = String.format("%0" + num + "d", no);
        return result;
    }

    /**
     * @return java.lang.String
     * @Author wujm
     * @Description 楼层抽取
     * @Date 2019/3/22
     * @Param [floor]
     */
    public static String getFoorTrueName(String floor){
        Matcher number = NUMBER_PATTERN.matcher(floor);
        if(number.find()) {
            return floor;
        } else {
            Matcher chineseNum = CHINESE_NUMBER_PATTERN.matcher(floor);
            if(number.find()) {
                return chineseNum.group(0);
            } else {
                return "";
            }
        }
    }

    public static String getName(String gridAccount, int roleType){
        return getString(gridAccount, roleType, "/", 0);
    }

    @NotNull
    private static String getString(String gridAccount, int roleType, String symbol, int num){
        StringBuilder builder = new StringBuilder();
        if(!StringUtils.isBlank(gridAccount)) {
            String[] userArr = gridAccount.split(",");

            for(String user : userArr) {
                String[] uArr = user.split("-");
                try {
                    if(roleType == Integer.parseInt(uArr[1])) {
                        builder.append(uArr[num]).append(symbol);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
            if(builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }

        }
        return builder.toString();
    }

    public static String getId(String gridAccount, int roleType){
        return getString(gridAccount, roleType, ",", 2);
    }

    public static String concatSring(String initString){
        String concatSring = "";
        if(!StringUtils.isEmpty(initString)) {
            String[] split = initString.split(",");
            for(String staff : split) {
                if(!StringUtils.isEmpty(staff)) {
                    concatSring = concatSring + "/" + staff;
                }
            }
        }
        return concatSring;
    }

    public static String createUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */
    public static List<String> getSubUtil(String soap, String rgex){
        List<String> list = new ArrayList<String>();
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);

        while(m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    /**
     * @功能  精确计算base64字符串文件大小（单位：B）
     * @注意  base64字符串(不含data:audio/wav;base64,文件头)
     */
    public static double base64fileSize( String base64String )  {

        //1.获取base64字符串长度(不含data:audio/wav;base64,文件头)
        int size0 = base64String.length();

        //2.获取字符串的尾巴的最后10个字符，用于判断尾巴是否有等号，正常生成的base64文件'等号'不会超过4个
        String tail = base64String.substring(size0-10);

        //3.找到等号，把等号也去掉,(等号其实是空的意思,不能算在文件大小里面)
        int equalIndex = tail.indexOf("=");
        if(equalIndex > 0) {
            size0 = size0 - (10 - equalIndex);
        }

        //4.计算后得到的文件流大小，单位为字节
        return size0 -( (double)size0 / 8 ) * 2;
    }

    public static void main(String[] args) throws IOException{
        List<Integer> a = Lists.newArrayList();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);

        ttt(a);

    }
    private static void ttt(List<Integer> a){
        Iterator<Integer> it = a.iterator();
        while(it.hasNext() && a.size() > 0){
            Integer b = it.next();
            if(b == a.size()){
                it.remove();
                if(a.size() > 0){
                    ttt(a);
                }
            }
        }
        System.out.println(a);
    }

}
