package br.com.sinuqueiros.restaurant.config.websocket.handler;

import io.micrometer.common.lang.NonNullApi;
import lombok.SneakyThrows;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NonNullApi
public class WebSocketOrderHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    @Override
    @SneakyThrows
    public void afterConnectionEstablished(final WebSocketSession session) {
        super.afterConnectionEstablished(session);
        webSocketSessions.add(session);
    }

    @Override
    @SneakyThrows
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus status) {
        super.afterConnectionClosed(session, status);
        webSocketSessions.remove(session);
    }

    @Override
    @SneakyThrows
    public void handleMessage(final WebSocketSession session, final WebSocketMessage<?> message) {
        super.handleMessage(session, message);
        for (final var webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(message);
        }
    }
}
