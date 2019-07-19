package com.service.impl;

import com.dao.ChatDao;
import com.model.ChatRecord;
import com.service.ChatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * author：RhineDream
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Resource
    private ChatDao chatDao;

    @Override
    public int saveChatRecord(ChatRecord chatRecord) {

        return chatDao.saveChatRecord(chatRecord);
    }
}
