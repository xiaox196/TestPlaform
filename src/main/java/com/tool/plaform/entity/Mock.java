package com.tool.plaform.entity;

public class Mock {

    public static final int GET_OPERATE = 0;
    public static final int POST_OPERATE = 1;

    public String project = "";
    public String name = "";
    public String Apidescribe = "";
    public String method = "";
    public String type = "";
    public String ApiPath = "";
    public long updateTime = 0;
    public String ischeck = "";
    public String stuts = "";
    public String creater = "";
    public String operator = "";
    public String returnUrl;
    public String resultMsg;
    public int operateCode = GET_OPERATE;

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApidescribe(String apidescribe) {
        Apidescribe = apidescribe;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setApiPath(String apiPath) {
        ApiPath = apiPath;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public void setStuts(String stuts) {
        this.stuts = stuts;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getProject() {
        return project;
    }

    public String getName() {
        return name;
    }

    public String getApidescribe() {
        return Apidescribe;
    }

    public String getMethod() {
        return method;
    }

    public String getType() {
        return type;
    }

    public String getApiPath() {
        return ApiPath;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getIscheck() {
        return ischeck;
    }

    public String getStuts() {
        return stuts;
    }

    public String getCreater() {
        return creater;
    }

    public String getOperator() {
        return operator;
    }
}
