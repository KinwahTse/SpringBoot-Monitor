package com.mbigger.sigar.service.Impl;

import com.mbigger.sigar.bean.WarnMessage;
import com.mbigger.sigar.mapper.WarnMessageMapper;
import com.mbigger.sigar.service.WarnMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WarnMessageService")
public class WarnMessageServiceImpl implements WarnMessageService{
    @Autowired
    WarnMessageMapper warnMessageMapper;

    @Override
    public int generateMsg(WarnMessage warnMessage){
        return warnMessageMapper.generateMsg(warnMessage);
    }

    @Override
    public int startStrategy(WarnMessage warnMessage){
        return warnMessageMapper.startStrategy(warnMessage);
    }

    @Override
    public List<WarnMessage> findAllunReadMsg(String ifread){
        return warnMessageMapper.findAllunReadMsg(ifread);
    }

    @Override
    public List<WarnMessage> findAllReadMsg(String ifread){
        return warnMessageMapper.findAllReadMsg(ifread);
    }

    @Override
    public List<WarnMessage> findAllMsg() {
        return warnMessageMapper.findAllMsg();
    }

    @Override
    public int updateMsgtoRead (String id,String ifread) {
        return warnMessageMapper.updateMsgtoRead(id,ifread);
    }

    @Override
    public int deleteMsgById (String id){
        return warnMessageMapper.deleteMsgById(id);
    }

    @Override
    public int deleteAllRead (String ifread){
        return warnMessageMapper.deleteAllRead(ifread);
    }
}
