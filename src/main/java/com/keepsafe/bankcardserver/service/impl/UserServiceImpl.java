package com.keepsafe.bankcardserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keepsafe.bankcardserver.data.dao.UserInfo;
import com.keepsafe.bankcardserver.data.dto.LoginRespDTO;
import com.keepsafe.bankcardserver.data.dto.UserDTO;
import com.keepsafe.bankcardserver.exception.BizException;
import com.keepsafe.bankcardserver.mapper.UserMapper;
import com.keepsafe.bankcardserver.service.UserService;
import com.keepsafe.bankcardserver.utils.JwtUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static com.keepsafe.bankcardserver.exception.BizCode.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    // 安全密码正则：至少8位，包含大小写、数字、特殊字符
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    private static final Pattern PASSWORD_REGEX = Pattern.compile(PASSWORD_PATTERN);

    @Autowired
    public UserServiceImpl(UserMapper mapper, JwtUtil jwtUtil) {
        this.userMapper = mapper;
        this.jwtUtil = jwtUtil;
    }

    @NotNull
    @Override
    public LoginRespDTO login(@NotNull String username, @NotNull String password) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        UserInfo user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BizException(AUTHORIZED_ERROR.getResultCode(), "用户不存在");
        }

        if (!password.equals(user.getPassword())) {
            throw new BizException(AUTHORIZED_ERROR.getResultCode(), "密码错误");
        }

        UserDTO userDTO = new UserDTO(user.getName(), user.getId());

        String token = jwtUtil.generateToken(userDTO);

        return new LoginRespDTO(token);
    }

    @Override
    public void register(@NotNull String username, @NotNull String password) {
        if (username.trim().isEmpty()) {
            throw new BizException(REQUEST_ERROR.getResultCode(), "用户名不能为空");
        }
        if (!PASSWORD_REGEX.matcher(password).matches()) {
            throw new BizException(REQUEST_ERROR.getResultCode(), "密码不符合安全要求：至少8位，包含大小写字母、数字和特殊字符（如@!%）");
        }

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", username);
        UserInfo existingUser = userMapper.selectOne(queryWrapper);
        if (existingUser != null) {
            throw new BizException(REQUEST_ERROR.getResultCode(), "用户名已存在");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setPassword(password);
        userInfo.setCreateDate(LocalDateTime.now());
        userInfo.setModifyDate(LocalDateTime.now());

        int result = userMapper.insert(userInfo);
        if (result > 0) {
            System.out.println("注册成功，用户ID: " + userInfo.getId());
        } else {
            throw new BizException(INTERNAL_SERVER_ERROR.getResultCode(), "注册失败");
        }
    }
}
