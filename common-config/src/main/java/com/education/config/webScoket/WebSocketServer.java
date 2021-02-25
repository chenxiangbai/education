package com.education.config.webScoket;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author admin
 * @Date 2020-12-16 18:16
 * @Version 1.0
 * @Description
 */
@ServerEndpoint(value = "/ws/asset", configurator = HttpSessionConfigurator.class)
@Component
@Slf4j
public class WebSocketServer {
    @PostConstruct
    public void init() {
        log.info("websocket 加载");
    }

    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();
    private static Hashtable<String, Session> hashtable = new Hashtable<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        SessionSet.add(session);
        log.info("有连接加入，当前连接数为：{}", OnlineCount.incrementAndGet());
        log.info(session.getId());
        Map result = new HashMap();
        result.put("code", "000000");
        result.put("msg", "连接成功");
        sendMessage(session, JSON.toJSONString(result));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
//        if (session != null && hashtable != null && hashtable.containsValue(session)) {
//            hashtable.forEach((httpSession, sessions) -> {
//                if (session.equals(sessions)) {
//                    hashtable.remove(httpSession);
//                }
//            });
//        }
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Map map = (Map) JSON.parse(message);
        if (map != null && session != null) {
            String token = (String) map.get("token");
            if (StringUtils.isNotEmpty(token)) {
                hashtable.put(token, session);
            }
        }
        log.info("来自客户端的消息：{}", map);
        sendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    public static void sendMessage(Session session, String message) {
        try {
            //log.info("发送消息 Session ID: " + session.getId());
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param token
     * @param message
     */
    public static void sendMessageByToken(String token, String message) {
        sendMessage(hashtable.get(token), message);
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void broadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void sendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }

    /**
     * @param token
     * @return
     */
    public static Session getSession(String token) {
        return hashtable.get(token);
    }
}
