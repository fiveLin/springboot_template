package com.example.lin.resp;

public class APIObjectJsonG<T> extends APIRespJsonG<T> {

    private static final long serialVersionUID = -6748552684040052684L;

    public APIObjectJsonG(T obj) {
        this(ResultCode.SUCC, obj);
    }

    public APIObjectJsonG(int code, T obj) {
        super(code);
        ////no base type!!!
//        if (isBaseType(obj)) {
//            setData(new APIObjectG(obj));
//        } else {
        setData(obj);
//        }
    }

    public APIObjectJsonG(int code, String message) {
        super(code, message);
    }

    public static boolean isBaseType(Object obj) {
        return obj instanceof Integer || obj instanceof String
                || obj instanceof Character || obj instanceof Boolean
                || obj instanceof Byte || obj instanceof Long
                || obj instanceof Float || obj instanceof Double;
    }
}
