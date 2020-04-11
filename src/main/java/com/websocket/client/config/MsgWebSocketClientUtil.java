package com.websocket.client.config;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.enums.ReadyState;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 */
@Slf4j
public class MsgWebSocketClientUtil {
    private static MsgWebSocketClient client;

    private static ExecutorService execute = Executors.newFixedThreadPool(100);

    private MsgWebSocketClientUtil() {
    }

    public static synchronized MsgWebSocketClient getInstance(String url)
            throws URISyntaxException {
        if (client == null) {
            client = new MsgWebSocketClient(url);
        }
        return client;
    }

    /**
     * 连接
     * @param client .
     * @throws Exception .
     */
    public static void connect(MsgWebSocketClient client){
        client.connect();
        while(!client.getReadyState().equals(ReadyState.OPEN)){
            log.info("正在连接...");
        }
        log.info("连接成功...");
    }

    /**
     * 发送消息
     * @param client .
     * @param text .
     */
    public static void send(MsgWebSocketClient client,String text) {
        client.send(text);
    }

    /**
     * 重连方法
     * @param client .
     * @throws Exception .
     */
    public static void reconnect(MsgWebSocketClient client){
        execute.execute(()->{
            log.info("再次启动尝试连接...");
            client.reconnect();
            int count = 0;
            while(count<10 && !client.getReadyState().equals(ReadyState.OPEN)){
                log.info("正在连接第{}次...",count);
                count++;
            }
        });
    }
}