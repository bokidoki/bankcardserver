package com.keepsafe.bankcardserver.controller.api;

import com.keepsafe.bankcardserver.data.dto.BaseResp;
import com.keepsafe.bankcardserver.data.dto.DeviceReqDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/device")
public interface DeviceApi {

    @PostMapping("bind")
    BaseResp<String> bindDevice(DeviceReqDTO dto, long userId);

    @PostMapping("unbind")
    BaseResp<String> unbindDevice(String uuid, long userId);
}
