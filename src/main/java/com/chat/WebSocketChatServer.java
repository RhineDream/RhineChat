package com.chat;

import com.alibaba.fastjson.JSON;
import com.model.ChatRecord;
import com.model.User;
import com.service.ChatService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 聊天服务端
 *
 * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
 * @see Session   WebSocket会话对象 通过它给客户端发送消息1
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

    public static ChatService chatService;
    public static UserService userService;

    /**
     * 全部在线会话  PS: 基于场景考虑 这里使用线程安全的Map存储会话对象。
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    /**
     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("username")String username) {
        User user = userService.getUserByName(username);
        onlineSessions.put(user.getId(), session);
        String msg = "[" + user.getName() + "]上线啦";
        System.out.println(msg);
        sendMessageToAll(Message.jsonStr(Message.ENTER, username, msg, onlineSessions.size(),null,null));
    }

    /**
     * 当客户端发送消息：1.获取它的用户名和消息 2.发送消息给所有人
     * <p>
     * PS: 这里约定传递的消息为JSON字符串 方便传递更多参数！
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr,@PathParam("username")String username) {

        Message message = JSON.parseObject(jsonStr, Message.class);
        String receiveUser = message.getReceiveUser();
        String userId = message.getUserId();

        receiveUser = receiveUser.substring(6, receiveUser.length());
        //保存到數據庫
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(UUID.randomUUID().toString());
        chatRecord.setMessage(message.getMsg());
        chatRecord.setSendUser(message.getUsername());
        chatRecord.setCreateDate(new Date());
        chatRecord.setIsRead("1");
        chatRecord.setGroupId("111");

        chatRecord.setReceiveUser(receiveUser);
        chatService.saveChatRecord(chatRecord);

        //sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineSessions.size(),null));


        if (message.getType().equals("1")) {  //1单聊


            sendMessageToOne(userId,receiveUser,Message.jsonStr(Message.ONESPEAK, message.getUsername(), message.getMsg(), onlineSessions.size(),receiveUser,userId));

        }else {  //2群聊

            sendMessageToAll(Message.jsonStr(Message.SPEAK, message.getUsername(), message.getMsg(), onlineSessions.size(),null,userId));

        }

    }

    /**
     * 当关闭连接：1.移除会话对象 2.更新在线人数
     */
    @OnClose
    public void onClose(Session session,@PathParam("username")String username) {
        User user = userService.getUserByName(username);
        onlineSessions.remove(user.getId());
        String msg = "[" + user.getName() + "]下线啦";
        System.out.println(msg);
        sendMessageToAll(Message.jsonStr(Message.QUIT, username, msg, onlineSessions.size(),null,null));
    }

    /**
     * 当通信发生异常：打印错误日志
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 公共方法：发送信息给所有人
     */
    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach((id, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 公共方法：发送信息给一个人
     */
    private static void sendMessageToOne(String userId, String receiveUser, String msg) {

        onlineSessions.forEach((id, session) -> {
            if(id.equals(receiveUser) || id.equals(userId)){
                try {

                    session.getBasicRemote().sendText(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

}
