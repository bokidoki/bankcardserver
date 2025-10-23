package com.keepsafe.bankcardserver.config;

import com.keepsafe.bankcardserver.utils.DefaultWebSocketHandler;
import com.keepsafe.bankcardserver.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final DefaultWebSocketHandler handler;
    private final WebSocketInterceptor interceptor;

    @Autowired
    public WebSocketConfig(DefaultWebSocketHandler handler, WebSocketInterceptor interceptor) {
        this.handler = handler;
        this.interceptor = interceptor;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws").addInterceptors(interceptor).setAllowedOrigins("*");
    }
}
