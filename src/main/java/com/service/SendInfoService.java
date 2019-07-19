package com.service;

import com.model.SendInfo;

import java.util.List;

public interface SendInfoService {

    public List<SendInfo> findList(SendInfo sendInfo);

    void insert(SendInfo sendInfo);

    SendInfo getByBusinessId(String businessId);

    SendInfo get(String id);

    void update(SendInfo sendInfo);
}
