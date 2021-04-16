package com.mbigger.sigar.service;

import com.mbigger.sigar.bean.User;

import java.util.List;

public interface UserService {

    int registerUser(User user);

    List<User> login(User user);

    List<User> registerJudge(User user);
}
