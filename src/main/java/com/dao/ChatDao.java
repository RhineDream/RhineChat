package com.dao;

import com.model.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatDao {

    int saveChatRecord(ChatRecord chatRecord);

}
