package com;

import com.chat.WebSocketChatServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * author：RhineDream
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.dao")//将项目中对应的mapper类的路径加进来就可以了
public class WebSocketChatApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }
}
