package com.example.lin.resp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class APIPageJsonG<E> extends APIObjectJsonG<APIPageG<E>> {

    public APIPageJsonG(APIPageG<E> obj) {
        super(obj);
    }

    public APIPageJsonG(List<E> list, long total, int pageNumber, int pageSize) {
        super(new APIPageG<>(list, total, pageNumber, pageSize));
    }

    public APIPageJsonG(int code) {
        this(code, "");
    }

    public APIPageJsonG(int code, String message) {
        super(code, message);
    }

    public APIPageJsonG(PageInfo<E> pageInfo) {
        this(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    public APIPageJsonG(Page<E> page) {
        this(page.getResult(), page.getTotal(), page.getPageNum(), page.getPageSize());
    }

}
