package com.keepsafe.bankcardserver.interceptor;

import com.keepsafe.bankcardserver.exception.BizException;
import com.keepsafe.bankcardserver.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.keepsafe.bankcardserver.exception.BizCode.AUTHORIZED_ERROR;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Autowired
    public TokenInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            throw new BizException(AUTHORIZED_ERROR.getResultCode(), "Authorization header is required");
        }

        String token = authorization.replace("Bearer ", "");
        boolean tokenValidated = jwtUtil.validateToken(token);
        if (tokenValidated) {
            request.setAttribute("token", token);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
