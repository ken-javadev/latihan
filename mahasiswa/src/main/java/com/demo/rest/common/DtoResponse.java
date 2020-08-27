package com.demo.rest.common;

import java.util.List;
import java.util.Map;

public class DtoResponse {
    private Integer status;
    private List list;
    private Object object;
    private Map<String,String> messageField;
    private String message;

    public DtoResponse(Integer status, Map<String, String> messageField) {
        this.status = status;
        this.messageField = messageField;
    }

    public DtoResponse(Integer status, List list, String message) {
        this.status = status;
        this.list = list;
        this.message = message;
    }

    public DtoResponse(Integer status, Object object, String message) {
        this.status = status;
        this.object = object;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, String> getMessageField() {
        return messageField;
    }

    public void setMessageField(Map<String, String> messageField) {
        this.messageField = messageField;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
