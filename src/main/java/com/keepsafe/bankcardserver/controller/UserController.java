package com.keepsafe.bankcardserver.controller;

import com.keepsafe.bankcardserver.controller.api.UserApi;
import com.keepsafe.bankcardserver.data.dto.BaseResp;
import com.keepsafe.bankcardserver.data.dto.LoginRespDTO;
import com.keepsafe.bankcardserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static com.keepsafe.bankcardserver.exception.BizCode.SUCCESS;

@RestController
public class UserController implements UserApi {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public BaseResp<String> register(String username, String password) {
        userService.register(username, password);
        BaseResp<String> resp = new BaseResp<>();
        resp.setData("注册成功");
        resp.setCode(SUCCESS.getResultCode());
        return resp;
    }

    @Override
    public BaseResp<LoginRespDTO> login(String username, String password) {
        LoginRespDTO dto = userService.login(username, password);
        BaseResp<LoginRespDTO> resp = new BaseResp<>();
        resp.setData(dto);
        resp.setCode(SUCCESS.getResultCode());
        return resp;
    }
}
