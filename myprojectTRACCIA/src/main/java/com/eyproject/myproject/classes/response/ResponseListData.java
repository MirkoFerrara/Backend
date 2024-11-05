package com.eyproject.myproject.classes.response;

import java.util.List;

public class ResponseListData<T> extends Response{
    List<T> data ;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
