package com.eyproject.myproject.classes.response;

public class Response {

    private boolean responseCode ;
    private String Msg ;

    public boolean isResponseCode() {
        return responseCode;
    }

    public void setResponseCode(boolean responseCode) {
        this.responseCode = responseCode;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
