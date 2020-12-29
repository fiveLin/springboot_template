/**
 * Powered by SCOOPER 2017
 */
package com.example.lin.exception;


import com.example.lin.resp.ResultCode;

/**
 * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
 *
 * <p>
 * 错误码来源于 {@link ResultCode} 及其扩展定义
 * </p>
 *
 * @author jiangwj
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6974481652781812964L;

    // 错误码，ResultCode 及其扩展定义
    private final int code;
    // 用于 i18n 的消息定义 key
    private final String messageKey;
    // 用于 i18n 的消息参数
    private final Object[] messageParams;

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者；<br/>
     * 错误码默认填充为 {@link ResultCode#FAIL}，其它参数默认填充为 null。
     */
    public BusinessException() {
        this(ResultCode.FAIL);
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     */
    public BusinessException(int code) {
        this(code, (String) null);
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public BusinessException(int code, String message) {
        this(code, null, null, message);
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param cause  the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BusinessException(int code, Throwable cause) {
        this(code, null, null, cause);
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BusinessException(int code, String message, Throwable cause) {
        this(code, null, null, message, cause);
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param messageKey 用于 i18n 的消息定义 key
     * @param messageParams 用于 i18n 的消息参数
     */
    public BusinessException(int code, String messageKey, Object[] messageParams) {
        super();
        this.code = code;
        this.messageKey = messageKey;
        this.messageParams = messageParams;
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param messageKey 用于 i18n 的消息定义 key
     * @param messageParams 用于 i18n 的消息参数
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public BusinessException(int code, String messageKey, Object[] messageParams, String message) {
        super(message == null ? ("BusinessException#"+code) : message);
        this.code = code;
        this.messageKey = messageKey;
        this.messageParams = messageParams;
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param messageKey 用于 i18n 的消息定义 key
     * @param messageParams 用于 i18n 的消息参数
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BusinessException(int code, String messageKey, Object[] messageParams, Throwable cause) {
        super(cause);
        this.code = code;
        this.messageKey = messageKey;
        this.messageParams = messageParams;
    }

    /**
     * 业务处理异常，用于将业务调用过程中出现的错误原因传递给上层调用者
     * @param code 错误码来源于 {@link ResultCode} 及其扩展定义
     * @param messageKey 用于 i18n 的消息定义 key
     * @param messageParams 用于 i18n 的消息参数
     * @param message the detail message. The detail message is saved for later retrieval by the getMessage() method.
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BusinessException(int code, String messageKey, Object[] messageParams, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.messageKey = messageKey;
        this.messageParams = messageParams;
    }

    /**
     * 错误码，ResultCode 及其扩展定义
     * @return
     * @see {@link ResultCode}
     */
    public int getCode() {
        return code;
    }

    /**
     * 用于 i18n 的消息定义 key
     * @return
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * 用于 i18n 的消息参数
     * @return
     */
    public Object[] getMessageParams() {
        return messageParams;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Throwable#getLocalizedMessage()
     */
    @Override
    public String getLocalizedMessage() {
        StringBuilder buf = new StringBuilder();
        buf.append(getMessage()).append('\n');
        buf.append("ResultCode#").append(code);
        if (messageKey != null) {
            buf.append(messageKey).append('\n');
        }
        if (messageParams != null) {
            buf.append('[');
            for (Object param : messageParams) {
                buf.append(param).append(',');
            }
            buf.append(']').append('\n');
        }
        return buf.toString();
    }

}
