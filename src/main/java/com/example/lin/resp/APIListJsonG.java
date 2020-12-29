package com.example.lin.resp;

import com.google.common.collect.Lists;

import java.util.List;

public class APIListJsonG<E> extends APIObjectJsonG<APIListG<E>> {

    public APIListJsonG(Iterable<E> list) {
        this(Lists.newArrayList(list));
    }

    public APIListJsonG(List<E> list) {
        this(ResultCode.SUCC, list);
    }

    public APIListJsonG(int code, List<E> list) {
        super(code, new APIListG<E>(list));
    }
}
