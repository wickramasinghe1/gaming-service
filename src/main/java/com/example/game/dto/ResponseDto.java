package com.example.game.dto;

import com.example.game.constants.AppConstants;

import java.util.Date;


public class ResponseDto {
    private String code = AppConstants.CODE_200;
    private String timestamp = new Date().toString();
    private Object result;
    private String desc;

    public ResponseDto() {
    }

    public ResponseDto(String desc, Object result) {
        this.desc = desc;
        this.result = result;
    }

    public ResponseDto(String desc, String code, Object result) {
        this.desc = desc;
        this.code = code;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "code='" + code + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", result=" + result +
                ", desc='" + desc + '\'' +
                '}';
    }
}
