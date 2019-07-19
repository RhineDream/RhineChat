package com.dao;

import com.model.SendInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SendInfoDao {

    public List<SendInfo> findList(SendInfo sendInfo);

    public void insert(SendInfo sendInfo);

    SendInfo getByBusinessId(@Param("businessId") String businessId);

    SendInfo get(@Param("id") String id);

    void update(SendInfo sendInfo);
}
