package com.keepsafe.bankcardserver.controller;

import com.keepsafe.bankcardserver.controller.api.DeviceApi;
import com.keepsafe.bankcardserver.data.dto.BaseResp;
import com.keepsafe.bankcardserver.data.dto.DeviceReqDTO;
import com.keepsafe.bankcardserver.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.keepsafe.bankcardserver.exception.BizCode.SUCCESS;

@RestController
public class DeviceController implements DeviceApi {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @Override
    public BaseResp<String> bindDevice(DeviceReqDTO dto, long userId) {
        deviceService.bindDevice(dto, userId);
        BaseResp<String> resp = new BaseResp<>();
        resp.setData("绑定成功");
        resp.setCode(SUCCESS.getResultCode());
        return resp;
    }

    @Override
    public BaseResp<String> unbindDevice(String uuid, long userId) {
        deviceService.unbindDevice(uuid, userId);
        BaseResp<String> resp = new BaseResp<>();
        resp.setData("解绑成功");
        resp.setCode(SUCCESS.getResultCode());
        return resp;
    }
}
