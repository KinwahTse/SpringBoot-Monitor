package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.WarnMessage;

import java.util.List;

public interface WarnMessageService {

    int generateMsg(WarnMessage warnMessage);

    int startStrategy(WarnMessage warnMessage);

    List<WarnMessage> findAllunReadMsg(String ifread);

    List<WarnMessage> findAllReadMsg(String ifread);

    List<WarnMessage> findAllMsg();

    int updateMsgtoRead (String id,String ifread);

    int deleteMsgById (String id);

    int deleteAllRead (String ifread);
}
