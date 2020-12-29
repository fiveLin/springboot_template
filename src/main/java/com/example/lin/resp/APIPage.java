package com.example.lin.resp;

import java.util.List;

/**
 * 分页查询结果
 * @author jiangwenjing
 */
public class APIPage<E> extends APIList<E> {

    private static final long serialVersionUID = 1949526072549158586L;

    private final int pageNumber; // 页码，从1开始
    private final int pageSize; // 每页记录数

    public APIPage(List<E> list, long total, int pageNumber, int pageSize) {
        super(list, total);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

}
