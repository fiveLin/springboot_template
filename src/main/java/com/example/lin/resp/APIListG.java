package com.example.lin.resp;

import java.util.List;

public class APIListG<E> {

    private static final long serialVersionUID = 4876647144775880803L;

    protected long total = 0;
    private List<E> list;

    public APIListG(List<E> list) {
        this(list, list == null ? 0 : list.size());
    }

    protected APIListG(List<E> list, long total) {
        this.list = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public List<E> getList() {
        return list;
    }
}
