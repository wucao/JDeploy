package com.xxg.jdeploy.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by wucao on 16-5-30.
 */
public class LogThread implements Runnable {


    private Process process;
    private BufferedReader reader;
    private WebSocketSession session;

    public LogThread(Process process, WebSocketSession session) throws UnsupportedEncodingException {
        this.process = process;
        this.reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        this.session = session;
    }

    public void close() throws IOException {
        try {
            reader.close();
        } finally {
            process.destroy();
        }
    }

    @Override
    public void run() {
        String line;
        try {
            while((line = reader.readLine()) != null) {
                // 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
                session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(line) + "<br>"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
