package com.keepsafe.bankcardserver.controller;

import com.keepsafe.bankcardserver.controller.api.DeviceApi;
import com.keepsafe.bankcardserver.data.dto.DeviceReqDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController implements DeviceApi {
    @Override
    public void bindDevice(DeviceReqDTO dto, long userId) {

    }
}
