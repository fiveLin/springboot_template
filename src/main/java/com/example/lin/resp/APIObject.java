package com.example.lin.resp;

public class APIObject implements java.io.Serializable {

    private static final long serialVersionUID = -1057067344643346851L;

    private final Object obj;

    public APIObject(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }
}
