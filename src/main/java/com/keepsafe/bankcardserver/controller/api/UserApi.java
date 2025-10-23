package com.keepsafe.bankcardserver.controller.api;

import com.keepsafe.bankcardserver.data.dto.BaseResp;
import com.keepsafe.bankcardserver.data.dto.LoginRespDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/user")
public interface UserApi {

    @PostMapping("register")
    BaseResp<String> register(String username, String password);

    @PostMapping("login")
    BaseResp<LoginRespDTO> login(String username, String password);
}
