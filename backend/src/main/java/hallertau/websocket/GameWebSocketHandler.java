package bouldercow.websocket;

import org.springframework.web.socket.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class GameWebSocketHandler implements WebSocketHandler {
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
    }
    
    public static void broadcastUpdate(String message) {
        sessions.values().forEach(session -> {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                // Handle error
            }
        });
    }
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {}
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {}
    
    @Override
    public boolean supportsPartialMessages() { return false; }
}