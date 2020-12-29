package com.example.lin.resp;


import com.example.lin.untils.TimeUtils;

import java.io.Serializable;

public class APIRespJsonG<T> implements Serializable {
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
    private T data;

    public APIRespJsonG() {
        this(ResultCode.SUCC);
    }

    public APIRespJsonG(int code) {
        this.code = code;
        this.systemTime = TimeUtils.currTime();
    }

    public APIRespJsonG(int code, String message) {
        this(code);
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public APIRespJsonG setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public T getData() {
        return data;
    }

    public APIRespJsonG setData(T data) {
        this.data = data;
        return this;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public APIRespJsonG setMessageKey(String messageKey) {
        this.messageKey = messageKey;
        return this;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    public APIRespJsonG setMessageParams(Object[] messageParams) {
        this.messageParams = messageParams;
        return this;
    }

}
