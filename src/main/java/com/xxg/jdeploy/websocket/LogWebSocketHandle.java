package com.xxg.jdeploy.websocket;

import com.xxg.jdeploy.service.JavaDeployService;
import com.xxg.jdeploy.service.JavaWebDeployService;
import com.xxg.jdeploy.util.QueryStringParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wucao on 16-5-30.
 */
public class LogWebSocketHandle extends AbstractWebSocketHandler {

    private ConcurrentHashMap<String, LogThread> map = new ConcurrentHashMap<>();

    @Autowired
    private JavaWebDeployService javaWebDeployService;

    @Autowired
    private JavaDeployService javaDeployService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String type = QueryStringParser.parse(session.getUri().getQuery()).get("type");
        String uuid = QueryStringParser.parse(session.getUri().getQuery()).get("uuid");

        String command = "echo \"参数错误\"";
        if("java".equals(type)) {
            command = javaDeployService.showLog(uuid);
        } else if("javaweb".equals(type)) {
            command = javaWebDeployService.showLog(uuid);
        }

        Process process = Runtime.getRuntime().exec(command);
        LogThread thread = new LogThread(process, session);
        threadPoolTaskExecutor.execute(thread);

        map.put(session.getId(), thread);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LogThread thread = map.get(session.getId());
        map.remove(session.getId());
        thread.close();

        System.out.println("Now Websocket Connection: " + map.size());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }
}