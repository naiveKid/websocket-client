package com.websocket.client;

import com.websocket.client.config.MsgWebSocketClient;
import com.websocket.client.config.MsgWebSocketClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebsocketClientApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebsocketClientApplication.class, args);
        MsgWebSocketClient client = MsgWebSocketClientUtil.getInstance("ws://121.40.165.18:8800");
        MsgWebSocketClientUtil.connect(client);
        //连接成功,发送信息
        MsgWebSocketClientUtil.send(client,"哈喽,连接一下啊");
    }
}