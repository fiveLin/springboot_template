package com.example.lin.resp;


public class APIObjectG<T> {
    private final T obj;

    public APIObjectG(T obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}
