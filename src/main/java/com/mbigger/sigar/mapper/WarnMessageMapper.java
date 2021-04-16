package com.mbigger.sigar.mapper;

import com.mbigger.sigar.bean.WarnMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface WarnMessageMapper {
    /**
     *
     * @param warnMessage
     * @return
     */
    int generateMsg (WarnMessage warnMessage);

    /**
     *
     * @param warnMessage
     * @return
     */
    int startStrategy (WarnMessage warnMessage);

    /**
     *
     * @param ifread
     * @return
     */
    List<WarnMessage> findAllunReadMsg(String ifread);

    /**
     *
     * @param ifread
     * @return
     */
    List<WarnMessage> findAllReadMsg(String ifread);

    List<WarnMessage> findAllMsg();

    /**
     *
     * @param id
     * @param ifread
     * @return
     */
    int updateMsgtoRead (String id,String ifread);

    /**
     *
     * @param id
     * @return
     */
    int deleteMsgById (String id);

    /**
     *
     * @param ifread
     * @return
     */
    int deleteAllRead (String ifread);
}
