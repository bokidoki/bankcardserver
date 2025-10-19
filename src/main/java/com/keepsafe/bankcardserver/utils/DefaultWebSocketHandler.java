package com.keepsafe.bankcardserver.utils;

import com.keepsafe.bankcardserver.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    private final WebSocketService service;

    @Autowired
    public DefaultWebSocketHandler(WebSocketService service) {
        this.service = service;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        service.handleOpen(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage textMessage) {
            service.handleMessage(session, textMessage.getPayload());
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        service.handleError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        service.handleClose(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
