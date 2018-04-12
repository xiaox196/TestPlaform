package com.tool.plaform.vo;

import java.util.Map;

public class ApiVo {

    private String rootUrl;

    private String url;
    private int id;

    private Map params;

    private String type;

    private String contentType;

    private String expect;
    private int port;
    private String method;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getPort() {
        return port;
    }

    public String getMethod() {
        return method;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }
}
