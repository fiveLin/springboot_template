package com.example.lin.resp;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * API响应对象<br/>
 * 所有的返回对象都要继承该类
 * @author LuLihong
 *
 */
public class APIRespJson implements java.io.Serializable {

    private static final long serialVersionUID = 6354540642744803586L;

    /**
     * 返回值，请见类：ResultCode
     */
    private final int code;
    /**
     * 消息key（便于 i18n 解析）
     */
    private String messageKey = null;
    /**
     * 消息参数（便于 i18n 解析）
     */
    private Object[] messageParams = null;
    /**
     * 信息提示，code=0时可以不填写该字段。
     */
    private String message = "";
    /**
     * 服务器系统时间。格式：2016-09-02 12:02:02
     */
    private final String systemTime;
    /**
     * JSON数据部分。
     */
    private Object data;

    public APIRespJson() {
        this(ResultCode.SUCC);
    }

    public APIRespJson(int code) {
        this.code = code;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.systemTime = simpleDateFormat.format(new Date());
    }

    public APIRespJson(int code, String message) {
        this(code);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public APIRespJson setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public Object getData() {
        return data == null ? null : data;
    }

    public APIRespJson setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public APIRespJson setMessageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    public APIRespJson setMessageParams(Object[] messageParams) {
        this.messageParams = messageParams;
        return this;
    }
}
