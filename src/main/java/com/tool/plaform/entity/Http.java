package com.tool.plaform.entity;

public class Http {
    public  String url;
    public  String method;
    public  String desc;
    public String param;
    public String type;
    public  String header;
    public String ContentType;


    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getParam() {
        return param;
    }

    public String getType() {
        return type;
    }

    public String getHeader() {
        return header;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }
}
