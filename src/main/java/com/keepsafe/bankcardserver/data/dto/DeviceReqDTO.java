package com.keepsafe.bankcardserver.data.dto;

import lombok.Data;

@Data
public class DeviceReqDTO {

    private String brand;

    private String model;

    private String uuid;
}
