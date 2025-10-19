package com.keepsafe.bankcardserver.service.impl;

import com.keepsafe.bankcardserver.data.dto.UserDTO;
import com.keepsafe.bankcardserver.service.WebSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketServiceImpl implements WebSocketService {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
    private final Map<Long, WebSocketSession> clients = new ConcurrentHashMap<>();

    private final static String USER_INFO = "userInfo";

    @Override
    public void handleOpen(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        UserDTO userInfo = (UserDTO) attributes.get(USER_INFO);
        clients.put(userInfo.getId(), session);

        log.info("a new client connected, current online users' count: {}", clients.size());
    }

    @Override
    public void handleClose(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        UserDTO userInfo = (UserDTO) attributes.get(USER_INFO);
        clients.remove(userInfo.getId());
        log.info("a client disconnected, current online users' count: {}", clients.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, String message) {
        log.info("a message received: {}", message);
    }

    @Override
    public void sendMessage(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

    @Override
    public void sendMessage(Integer userId, String message) throws IOException {
        WebSocketSession session = clients.get(userId);
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public void broadcast(String message) throws IOException {
        clients.values().forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void handleError(WebSocketSession session, Throwable error) {
        log.error("an error occurred: {}, session id: {}", error.getMessage(), session.getId());
        log.error("", error);
    }
}
