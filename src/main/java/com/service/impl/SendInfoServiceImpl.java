package com.service.impl;

import com.dao.SendInfoDao;
import com.model.SendInfo;
import com.service.SendInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SendInfoServiceImpl implements SendInfoService {

    @Resource
    private SendInfoDao sendInfoDao;

    @Override
    public List<SendInfo> findList(SendInfo sendInfo) {
        return sendInfoDao.findList(sendInfo);
    }

    @Override
    public void insert(SendInfo sendInfo) {
        sendInfoDao.insert(sendInfo);
    }

    @Override
    public SendInfo getByBusinessId(String businessId) {
        return sendInfoDao.getByBusinessId(businessId);
    }

    @Override
    public SendInfo get(String id) {
        return sendInfoDao.get(id);
    }

    @Override
    public void update(SendInfo sendInfo) {
        sendInfoDao.update(sendInfo);
    }
}
