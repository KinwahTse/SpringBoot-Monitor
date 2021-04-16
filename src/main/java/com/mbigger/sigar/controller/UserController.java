package com.mbigger.sigar.controller;

import com.mbigger.sigar.bean.User;
import com.mbigger.sigar.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/Boot")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public Map<Object,Object> registerUser(@RequestBody User user){
        List<User> listOrder;
        listOrder = userServiceImpl.registerJudge(user);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (listOrder.isEmpty()==true){
            user.setId(UUID.randomUUID().toString());
            int result = userServiceImpl.registerUser(user);
            if (result<=0){
                map.put("code",400);
                map.put("message","注册失败！");
            }
            else{
                map.put("code",200);
            }
        }
        else{
            map.put("code",401);
            map.put("message","该用户名已存在！");
        }
        return map;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<Object,Object> login(@RequestBody User user){
        List<User> listOrder;
        listOrder = userServiceImpl.login(user);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (listOrder.isEmpty()==true){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }
}
