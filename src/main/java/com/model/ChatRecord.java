package com.model;

import java.util.Date;
/**
 * author：RhineDream
 */
public class ChatRecord {
    private String id;
    private String message;
    private String sendUser;
    private String receiveUser;
    private Date createDate;
    private String isRead;
    private String groupId; //群聊id

    public ChatRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ChatRecord(String id, String message, String sendUser, String receiveUser, Date createDate, String isRead, String groupId) {
        this.id = id;
        this.message = message;
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.createDate = createDate;
        this.isRead = isRead;
        this.groupId = groupId;
    }
}
