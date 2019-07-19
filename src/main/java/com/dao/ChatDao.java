package com.dao;

import com.model.ChatRecord;
import org.apache.ibatis.annotations.Mapper;
/**
 * author：RhineDream
 */
@Mapper
public interface ChatDao {

    int saveChatRecord(ChatRecord chatRecord);

}
