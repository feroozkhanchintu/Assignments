package com.cnu2016.ecommerce.pojo;

/**
 * Created by vipulj on 11/07/16.
 */

import java.util.Date;

public class LogPOJO {
    Date timestamp;
    String url;
    String parameters;
    Integer responseCode;
    String ipAddress;
    Long completionTime;

    public LogPOJO(Date timestamp, String url, String parameters, Integer responseCode, String ipAddress, Long completionTime) {
        this.timestamp = timestamp;
        this.url = url;
        this.parameters = parameters;
        this.responseCode = responseCode;
        this.ipAddress = ipAddress;
        this.completionTime = completionTime;
    }

    public Long getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Long completionTime) {
        this.completionTime = completionTime;
    }


    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
