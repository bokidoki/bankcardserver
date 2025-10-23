package com.keepsafe.bankcardserver.exception;

public enum BizCode {

    SUCCESS(200),
    REQUEST_ERROR(400),
    AUTHORIZED_ERROR(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    SERVER_BUSY(503);

    /**
     * 错误码
     */
    private int resultCode;


    BizCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }
}
