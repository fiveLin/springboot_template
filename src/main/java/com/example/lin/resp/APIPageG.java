package com.example.lin.resp;

import java.util.List;

public class APIPageG<E> extends APIListG<E> {

    private final int pageNumber;
    private final int pageSize;

    public APIPageG(List<E> list, long total, int pageNumber, int pageSize) {
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
