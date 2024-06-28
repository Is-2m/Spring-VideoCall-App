package com.ensamc.mbdio.VideoCallApp.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ResponseWrapper<T> {
    private String type;
    private T data;
    private Object add_info=null;

    public ResponseWrapper(String type, T data) {
        this.type = type;
        this.data = data;
    }
    public ResponseWrapper(String type, T data, Object additionalInfo) {
        this.type = type;
        this.data = data;
        this.add_info = additionalInfo;
    }
}