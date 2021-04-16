package com.mbigger.sigar.service.Impl;


import com.mbigger.sigar.bean.User;
import com.mbigger.sigar.mapper.UserMapper;
import com.mbigger.sigar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int registerUser(User user){
        return userMapper.registerUser(user);
    }

    @Override
    public List<User> login(User user){
        return userMapper.login(user);
    }

    @Override
    public List<User> registerJudge(User user){
        return userMapper.registerJudge(user);
    }
}
