package com.example.lin.resp;

import java.util.List;

/**
 * 分页查询结果
 * @author jiangwenjing
 * @param <E>
 */
public class APIPageJson<E> extends APIObjectJson {

    private static final long serialVersionUID = 2798126123091247602L;

    public APIPageJson(APIPage<E> page) {
        super(page);
    }

    public APIPageJson(List<E> list, long total, int pageNumber, int pageSize) {
        this(new APIPage<E>(list, total, pageNumber, pageSize));
    }

    public APIPageJson(int code) {
        this(code, "");
    }

    public APIPageJson(int code, String message) {
        super(code, message);
    }

}
