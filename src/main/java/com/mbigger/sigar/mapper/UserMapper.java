package com.mbigger.sigar.mapper;

import com.mbigger.sigar.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    /**
     *
     * @param user
     * @return
     */
    int registerUser(User user);

    /**
     *
     * @param user
     * @return
     */
    List<User> login(User user);

    /**
     *
     * @param user
     * @return
     */
    List<User> registerJudge(User user);
}
