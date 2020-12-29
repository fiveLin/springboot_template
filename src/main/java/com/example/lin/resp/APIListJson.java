package com.example.lin.resp;

import java.util.List;

/**
 * API响应List对象
 * @author LuLihong
 *
 */
public class APIListJson<E> extends APIObjectJson {

    private static final long serialVersionUID = -3939309217513820437L;

    public APIListJson(List<E> list) {
        this(ResultCode.SUCC, list);
    }

    public APIListJson(int code, List<E> list) {
        super(code, new APIList<E>(list));
    }
}
