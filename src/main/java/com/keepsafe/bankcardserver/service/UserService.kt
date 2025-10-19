package com.keepsafe.bankcardserver.service

import com.keepsafe.bankcardserver.data.dto.LoginRespDTO

interface UserService {

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回token
     */
    fun login(username: String, password: String): LoginRespDTO

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     */
    fun register(username: String, password: String)
}