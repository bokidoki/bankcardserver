package com.keepsafe.bankcardserver.data.dto;

import lombok.Data;

@Data
public class BaseResp<T> {

    private int code;
    private T data;
}
