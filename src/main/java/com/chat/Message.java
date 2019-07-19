package com.chat;

import com.alibaba.fastjson.JSON;

/**
 * WebSocket 聊天消息类
 */
public class Message {

    public static final String ENTER = "ENTER"; //连接
    public static final String SPEAK = "SPEAK"; //群聊
    public static final String ONESPEAK = "ONESPEAK"; //单聊
    public static final String QUIT = "QUIT";   //退出

    private String type;//消息类型

    private String username; //发送人

    private String msg; //发送消息

    private int onlineCount; //在线用户数

    private String receiveUser; //接收人

    private String userId; //接收人

    public static String jsonStr(String type, String username, String msg, int onlineTotal,String receiveUser,String userId) {
        return JSON.toJSONString(new Message(type, username, msg, onlineTotal,receiveUser,userId));
    }

    public Message(String type, String username, String msg, int onlineCount,String receiveUser,String userId) {
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
        this.receiveUser = receiveUser;
        this.userId = userId;
    }

    public static String getENTER() {
        return ENTER;
    }

    public static String getSPEAK() {
        return SPEAK;
    }

    public static String getQUIT() {
        return QUIT;
    }
    public static String getONESPEAK() {
        return ONESPEAK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
