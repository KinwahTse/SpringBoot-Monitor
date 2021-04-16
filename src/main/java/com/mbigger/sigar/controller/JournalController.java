package com.mbigger.sigar.controller;

import com.mbigger.sigar.bean.Journal;
import com.mbigger.sigar.bean.Strategy;
import com.mbigger.sigar.service.Impl.JournalServiceImpl;
import com.mbigger.sigar.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/Boot")
public class JournalController {

    @Autowired
    public JournalServiceImpl journalService;

    @RequestMapping(value = "/insertJournal", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object,Object> insertJournal(@RequestBody Map Amap) {
        Journal journal = new Journal();

        String username = (String)Amap.get("username");
        String status = (String)Amap.get("status");
        journal.setId(UUID.randomUUID().toString());
        journal.setUsername(username);
        journal.setTime(DateUtils.formatDateTime(new Date()));
        switch(status){
            case "1" :
                journal.setOperation("登录系统");
                break; //可选
            case "2" :
                String name1 = (String)Amap.get("name");
                journal.setOperation("添加策略：" + name1);
                break; //可选
            case "3" :
                String name2 = (String)Amap.get("name");
                journal.setOperation("开启策略：" + name2);
                break; //可选
            case "4" :
                String name3 = (String)Amap.get("name");
                journal.setOperation("停止策略：" + name3);
                break; //可选
            case "5" :
                String name4 = (String)Amap.get("name");
                journal.setOperation("删除策略：" + name4);
                break; //可选
            case "6" :
                journal.setOperation("删除已读信息");
                break; //可选
        }
        if(username.equals("admin")){
            journal.setPower("超级管理员");
        }
        else journal.setPower("普通用户");
        int result = journalService.insertJournal(journal);
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (result<=0){
            map.put("code",400);
        }
        else{
            map.put("code",200);
        }
        return map;
    }

    @RequestMapping("/findAllJournal")
    public List<Journal> ListJournal(){
        return journalService.findAllJournal();
    }
}
