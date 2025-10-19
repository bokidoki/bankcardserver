package com.keepsafe.bankcardserver.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public interface WebSocketService {

    void handleOpen(WebSocketSession session);

    void handleClose(WebSocketSession session);

    void handleMessage(WebSocketSession session, String message);

    void sendMessage(WebSocketSession session, String message) throws IOException;

    void sendMessage(Integer userId, String message) throws IOException;

    void broadcast(String message) throws IOException;

    void handleError(WebSocketSession session, Throwable error);
}
