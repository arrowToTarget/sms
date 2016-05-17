package com.lewis.cms.common.base;

/**
 * Created by zhangminghua on 2016/5/17.
 */
public class RequestParam {

    private String sysCode = "cms";
    private String name;
    private String requestUrl;

    private String method;
    private String desc;
    private String returnValue;
    private String param;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public RequestParam() {
    }

    public RequestParam(String sysCode, String name, String requestUrl, String method, String desc, String returnValue, String param) {
        this.sysCode = sysCode;
        this.name = name;
        this.requestUrl = requestUrl;
        this.method = method;
        this.desc = desc;
        this.returnValue = returnValue;
        this.param = param;
    }

    @Override
    public String toString() {
        return "RequestParam{" +
                "sysCode='" + sysCode + '\'' +
                ", name='" + name + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", method='" + method + '\'' +
                ", desc='" + desc + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
