package com.aliyuncs.cloudapi.openapi.sdk.entity;

/**
 */
public class Tag {

    private String value;

    private String key;

    public Tag() {
    }

    public Tag(String key, String value) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
